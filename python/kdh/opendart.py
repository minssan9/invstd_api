"""
Module Name: opendart
Author: Donghyeon Kim
Date: 2023-12-29

Description:
Convenient disclosure inquiry using dart API.
"""

from typing import Optional
from datetime import datetime
import OpenDartReader   # pip install opendartreader

api_key = "5c8217b463e9d4d5eff07fefab80b9fd5e439060"

class OpenDartWrapper:
    def __init__(self) -> None:
        self._dart = OpenDartReader(api_key)
    
    def get_corp_code(self, corp_name: str, corp_cls: str="Y") -> Optional[str]:
        """
        Retrieve the corporate code for a given company name and corporate class.
        
        Args:
            corp_name (str): The name of the company for which the corporate code is to be retrieved.
            corp_cls (str, optional): The corporate class to filter the search. Default is "Y".
                For more information about "corp_cls", please refer to the link.
                https://opendart.fss.or.kr/guide/detail.do?apiGrpCd=DS001&apiId=2019002
            
        Returns:
            Optional[str]: The corporate code if a matching company is found, otherwise None.
            
        The function utilizes the Dart API to search for the company by name and filter
        the results based on the corporate class. If a matching company is found, it checks its status,
        and if the status is "000" (Normal), it retrieves and returns the corporate code. If no matching
        company is found, it prints a message indicating that no matching corporate code was found.
        
        Example:
            Assuming instance_name is an instance of the class containing this method:
            corp_code = instance_name.get_corp_code("Example Company")
        """
        try:
            corp_code = None
            corp_code_list = self._dart.company_by_name(corp_name)
            if len(corp_code_list) == 0:
                return None
            
            # corp_cls 필터링
            corp_code_list = [corp for corp in corp_code_list if corp and corp.get("corp_cls", None) == corp_cls]
            if len(corp_code_list) > 0:
                # 이름은 유일하므로 첫 번째 아이템으로 함.
                corp_found = corp_code_list[0]
            else:
                # 찾지 못할 경우에 매칭되는 이름을 가져옴
                corp_found = self._dart.company(corp_name)
            
            if corp_found is not None:
                status = corp_found.get("status", None)  
                if status == "000":  # 정상
                    corp_code = corp_found.get("corp_code", None)
        
        except Exception as e:
            print(f"exception occer: {str(e)}")
            
        finally:
            if corp_code is None:
                print(f"No matching corp code found: {corp_name}")
            return corp_code
    
    def get_major_shareholders_executives(self, corp: str, start_date: Optional[str] = None, end_date: Optional[str] = None):
        """
        Retrieve major shareholders and executives disclosure information for a given corporation.

        Args:
            corp (str): The name or code of the corporation for which disclosure information is to be retrieved.
            start_date (str, optional): The start date of the disclosure period. If not provided, defaults to today.
            end_date (str, optional): The end date of the disclosure period. If not provided, defaults to today.

        Returns:
            Optional[str]: JSON-formatted disclosure information for major shareholders and executives if found, otherwise None.

        This function retrieves major shareholders and executives disclosure information for a corporation identified
        by its name or code. It first obtains the corporate code using the get_corp_code method. If the corporate code
        is not found, it returns None. It then queries the DART API to get major shareholders and executives data.
        The function allows specifying a date range for the disclosure period, defaulting to today if not provided.
        After filtering the data based on the date range, it converts the resulting DataFrame to a JSON format.

        Example:
            Assuming instance_name is an instance of the class containing this method:
            disclosure_data = instance_name.get_major_shareholders_executives("Example Corporation", start_date="2023-01-01", end_date="2023-12-31")
        """
        try:
            disclosure = None
            corp_code = corp if corp.isdigit() else self.get_corp_code(corp)
            if corp_code is None:
                return None
            
            df = self._dart.major_shareholders_exec(corp_code)
            if not df.empty:
                today = datetime.now().date().strftime('%Y-%m-%d')
                # 날짜 범위를 입력하지 않을 경우 오늘로 함. (당일 공시만 조회)
                start_date = today if start_date is None else start_date
                end_date   = today if end_date is None else end_date
                
                # 날짜 범위에 대한 유효성 검사 필요..
                
                df = df[(df['rcept_dt'] >= start_date) & (df['rcept_dt'] <= end_date)]
                if not df.empty:
                    disclosure = df.to_json(orient='records', date_format='iso', default_handler=str)
            
        except Exception as e:
            print(f"exception occer: {str(e)}")
            
        finally:
            if disclosure is None:
                print(f"No matching disclosure found: {corp}")
            return disclosure