import telegram
 
def get_bot_id():
    bot_token = '6699620556:AAHeKhWXwiWaCCS2GgPMZEZ1ts_H6vFQBeM'
    bot = telegram.Bot(token=bot_token)
    me = bot.getMe()
    bot_id = me['504019572']
 
    return bot_id