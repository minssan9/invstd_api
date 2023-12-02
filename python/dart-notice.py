from telegram.ext import Updater, MessageHandler, Filters, CommandHandler, CallbackContext
import pandas as pd
import json

def convertFnltt(self, url, items, item_names, params):
  res = requests.get(url, params)
  json_dict = json.loads(res.text)
  data = []
  if json_dict['status'] == "000":
    for line in json_dict['list']:
      data.append([])
      for itm in items:
        if itm in line.keys():
          data[-1].append(line[itm])
        else: data[-1].append("")
  else:
    return False
  df = pd.DataFrame(data,columns=item_names)
  return df

def get_list(crtfc_key,**kwargs):
  keys = ['corp_code','bgn_de','end_de','last_reprt_at','pblntf_ty',
          'pblntf_detail_ty','corp_cls''sort','sort_mth','page_no','page_count']
  for key in kwargs.keys():
    if not key in keys:
      print("get_list() has no parameter \'"+key+"\'")
      return False
  params = {**{'crtfc_key':crtfc_key},**kwargs}
  items = ['corp_cls','corp_name','corp_code','stock_code','report_nm',
          'rcept_no','flr_nm','rcept_dt','rm']
  item_names = ['법인구분','종목명','고유번호','종목코드','보고서명','접수번호',
               '공시제출인명','접수일자','비고']
  url = "https://opendart.fss.or.kr/api/list.json"
  return convertFnltt(url,items,item_names,params)

def get_new_notices():
  interest_list = ['삼성전자','LG화학','씨젠']
  new_noti_list = []
  page = 1
  df = None
  while True:
    cur_df = get_list(dart_crtf_key,page_no=str(page),page_count="100")
    if len(cur_df) > 0:
      df = cur_df if (page == 1) else pd.concat([df,cur_df], ignore_index=True)
    if len(cur_df) < 100:
      break
    page += 1
  for oneCorp in interest_list:
    idx_list = df.index[df['종목명']==oneCorp].tolist()
    if len(idx_list) > 0:
      for idx in idx_list:
        text = "▶ "+df.loc[idx,'종목명']+"\n"
        text += "▶ "+df.loc[idx,'보고서명']+"\n"
        text += "▶ http://m.dart.fss.or.kr/html_mdart/MD1007.html?rcpNo="+\
                df.loc[idx,'접수번호']+"\n"
        new_noti_list.append(text)
  return new_noti_list

def check_dart(context: CallbackContext):
  new_noti_list = get_new_notices()
  if len(new_noti_list) > 0:
    for text in new_noti_list:
      context.bot.send_message(chat_id=chat_id, text=text)

dart_crtf_key = "1163b3817fe2c7d71e6865119b8010dc345880aa"
token = '6699620556:AAHeKhWXwiWaCCS2GgPMZEZ1ts_H6vFQBeM'
chat_id = 'minssan9'

updater = Updater(token=token, use_context=True)
dispatcher = updater.dispatcher
job = updater.job_queue

job_repeating = job.run_repeating(check_dart, interval=300, first=0)

updater.start_polling(timeout=3, clean=True)
updater.idle()