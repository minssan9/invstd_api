from apscheduler.schedulers.background import BackgroundScheduler

scheduler = BackgroundScheduler()


def update_data():
    print('scheduler')
    # 데이터 업데이트 작업 수행
    pass


# 스케줄링 작업 등록
scheduler.add_job(update_data, 'interval', seconds=3)


# 스케줄링 작업 실행
scheduler.start()