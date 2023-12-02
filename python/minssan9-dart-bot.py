#텔레그램 모듈 설치 (pip와 conda 두 가지중에 하나 선택해서 설치)
# pip install python-telegram-bot --upgrade 
# conda install -c conda-forge python-telegram-bot

import telegram #텔레그램 모듈 import

bot_token = '6699620556:AAHeKhWXwiWaCCS2GgPMZEZ1ts_H6vFQBeM' #토큰을 변수에 저장

bot = telegram.Bot(token = bot_token) # telegram 모듈의 Bot 함수를 사용하여 bot_token으로 접근 가능한 bot 생성

updates = bot.getUpdates() #bot과의 채팅 정보 및 메세지 업데이트

for i in updates :
    print(i) #update_id와 message로 크게 두 가지 정보가 딕셔너리 형태로 저장
    print(i.message) # 내역중 message 정보를 출력

chat_id = bot.getUpdates()[-1].message.chat.id

# 앞에서 사용한 bot.getUpdates 함수는 list 형식을 리턴
# list 문법에 의해 [-1]은 가장 마지막에 저장된, 즉, 가장 최근에 온 메세지의 정보를 가리킴
#message 정보 안에서 chat id만 가져옴

bot.sendMessage(chat_id = chat_id, text="야, 아무말이나 던져봐") #위에서 얻은 chat id로 메세지를 보냄.