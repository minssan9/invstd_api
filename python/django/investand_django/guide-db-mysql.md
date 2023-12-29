```shell
pip install mysqlclient


```

```python

DATABASES = {
  'default': {
    'ENGINE': 'django.db.backends.mysql',
    'NAME': 'investand',
    'USER': 'investand',
    'PASSWORD': 'xnwk123$',
    'HOST': 'db.en9doors.com',  # Or the host where your MySQL server runs
    'PORT': '3306',  # Change to your MySQL port if different
    'OPTIONS': {
      'charset': 'utf8mb4',
    },
  }
}

```