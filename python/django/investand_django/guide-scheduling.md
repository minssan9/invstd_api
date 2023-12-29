[https://gr-st-dev.tistory.com/948]

```shell
pip install apscheduler
pip install django-apscheduler

```

settings.py
```python

INSTALLED_APPS = [
    # ...
    "django_apscheduler",
    # ...
]


```

wsgi.py
```python 
from .tasks import scheduler 


# 스케줄링 작업 실행
scheduler.start()
```