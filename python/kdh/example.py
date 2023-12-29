import OpenDartReader
from datetime import datetime

api_key = '5c8217b463e9d4d5eff07fefab80b9fd5e439060'

dart = OpenDartReader(api_key) 

today = datetime.now().date().strftime('%Y-%m-%d')

# kind = 'D' : 지분공시
# kind_detail = 'D001' : 주식등의대량보유상황보고서
# kind_detail = 'D002' : 임원ㆍ주요주주특정증권등소유상황보고서
# end 만 지정시 해당일만 조회함
# disclosure = dart.list('한화갤러리아')
# disclosure = dart.list('452260', start='2023-12-01', kind='D', kind_detail='D002')
# disclosure = dart.list('삼성전자', start='2023-12-08', kind='D', kind_detail='D002')
# print(disclosure)
# doc = dart.document('20231208000649')
# print(type(doc))
# print(doc)

'''
rcept_no	            접수번호	                   접수번호(14자리)
rcept_dt	            접수일자	                   공시 접수일자(YYYY-MM-DD)
corp_code	            고유번호	                   공시대상회사의 고유번호(8자리)
corp_name	            회사명	                       회사명
repror	                보고자  	                   보고자명
isu_exctv_rgist_at	    발행 회사 관계 임원(등기여부)   등기임원, 비등기임원 등
isu_exctv_ofcps	        발행 회사 관계 임원 직위	    대표이사, 이사, 전무 등
isu_main_shrholdr	    발행 회사 관계 주요 주주	    10%이상주주 등
sp_stock_lmp_cnt	    특정 증권 등 소유 수	        9,999,999,999
sp_stock_lmp_irds_cnt	특정 증권 등 소유 증감 수	    9,999,999,999
sp_stock_lmp_rate	    특정 증권 등 소유 비율	        0.00
sp_stock_lmp_irds_rate	특정 증권 등 소유 증감 비율	    0.00
'''
# df = dart.major_shareholders_exec('한화갤러리아')
# # df = dart.major_shareholders_exec('452260')
# if not df.empty:
#     df = df[df['rcept_dt'] >= '2023-12-01']
#     json_data = df.to_json(orient='records', date_format='iso', default_handler=str)
#     print(json_data)

# corp = dart.find_corp_code('한화갤러리아 주식회사')
# ...
# 삼선전자 : 00126380
# 한화갤러리아 : 00162364 이 아니라 01722437 이다.

test = dart.company_by_name('삼성전자')
# test = dart.company('삼성전자')
print(type(test))
print(test)
# 법인구분 : Y(유가), K(코스닥), N(코넥스), E(기타)
# 중에서 copr_cls(법인구분) 이 Y(유가)인 것을 선별

test = dart.find_corp_code("한화갤러리아")
print(test)