import requests
import json
import datetime
import pandas as pd

stock_list = ['삼성전자','SK하이닉스','삼성바이오로직스','LG화학']

bgn_date = '20201201'
today = datetime.date.today().strftime("%Y%m%d")

for stock in stock_list:
    corp_code = df_corp[df_corp['종목명'] == stock]['고유번호'].values[0]
    print(stock, corp_code)
    url = "https://opendart.fss.or.kr/api/list.json?crtfc_key=%s&corp_code=%s&bgn_de=%s&end_de=%s&page_count=100" %(crtfc_key, corp_code, bgn_date, today)

    response = requests.get(url)
    data_json = json.loads(response.text)

    items = ['corp_cls','corp_name','corp_code','stock_code','report_nm', 'rcept_no','flr_nm','rcept_dt','rm']
    cols = ['법인구분', '종목명','고유번호','종목코드','보고서명','보고서링크','공시제출인명','접수일자','공'] 
    link_base = 'http://m.dart.fss.or.kr/html_mdart/MD1007.html?rcpNo='
    result=[]
    if data_json['status'] == '000':
        for data in data_json['list']:
            result.append([])
            for item in items:
                if item in data.keys():
                    if item == 'rcept_no': result[-1].append(link_base + data[item])
                    else: result[-1].append(data[item])
                else:
                    result[-1].append('')

        df = pd.DataFrame(result,columns=cols)

        print(df.head())
        print('\n')
    else:
        print('%s : 데이터없음'%stock)
        print('\n')