from opendart import OpenDartWrapper



if __name__ == '__main__':
    dart = OpenDartWrapper()
    # ret = dart.get_major_shareholders_executives("삼성전자")
    # ret = dart.get_major_shareholders_executives("삼성전자", start_date="2023-12-01")
    # ret = dart.get_major_shareholders_executives("삼성전자", start_date="2023-12-01", end_date="2023-12-31")
    
    ret = dart.get_major_shareholders_executives("한화갤러리아", start_date="2023-12-01")
    
    print(ret)