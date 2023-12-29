from apscheduler.schedulers.background import BackgroundScheduler

scheduler = BackgroundScheduler()

def my_scheduled_task():
    # 여기에 작업 내용을 작성합니다.
    print("Scheduled task executed!")

# 작업을 스케줄러에 추가
scheduler.add_job(my_scheduled_task, 'interval', minutes=30)