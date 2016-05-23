## Kc Person Extended Attributes [/research-common/api/v1/kc-person-extended-attributes/]

### Get Kc Person Extended Attributes by Key [GET /research-common/api/v1/kc-person-extended-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"}

### Get All Kc Person Extended Attributes [GET /research-common/api/v1/kc-person-extended-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"},
              {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kc Person Extended Attributes with Filtering [GET /research-common/api/v1/kc-person-extended-attributes/]
    
+ Parameters

    + personId (optional) - KcPersonExtendedAttributes Id. Maximum length is 40.
    + ageByFiscalYear (optional) - Age by Fiscal Year. Maximum length is 3.
    + race (optional) - Race. Maximum length is 30.
    + educationLevel (optional) - Education Level. Maximum length is 30.
    + degree (optional) - Degree. Maximum length is 11.
    + major (optional) - Major. Maximum length is 30.
    + handicappedFlag (optional) - Is Handicapped. Maximum length is 1.
    + handicapType (optional) - Handicap Type. Maximum length is 30.
    + veteranFlag (optional) - Veteran. Maximum length is 1.
    + veteranType (optional) - Veteran Type. Maximum length is 30.
    + visaCode (optional) - Visa Code. Maximum length is 20.
    + visaType (optional) - Visa Type. Maximum length is 30.
    + visaRenewalDate (optional) - Visa Renewal Date. Maximum length is 10.
    + hasVisa (optional) - Has Visa. Maximum length is 1.
    + officeLocation (optional) - Office Location. Maximum length is 30.
    + secondaryOfficeLocation (optional) - Secondary Office Location. Maximum length is 30.
    + school (optional) - School. Maximum length is 50.
    + yearGraduated (optional) - Year Graduated. Maximum length is 30.
    + directoryDepartment (optional) - Directory Department. Maximum length is 30.
    + primaryTitle (optional) - PrimaryTitle. Maximum length is 51.
    + directoryTitle (optional) - Directory Title. Maximum length is 50.
    + vacationAccrualFlag (optional) - Is Vacation Accrual. Maximum length is 1.
    + onSabbaticalFlag (optional) - Is on Sabbatical. Maximum length is 1.
    + idProvided (optional) - Id Provided. Maximum length is 30.
    + idVerified (optional) - Id Verified. Maximum length is 30.
    + county (optional) - County. Maximum length is 30.
    + citizenshipTypeCode (optional) - Citzenship Type. Maximum length is 3.
    + multiCampusPrincipalId (optional) - Multi-Campus Principal Id. Maximum length is 40.
    + multiCampusPrincipalName (optional) - Multi-Campus Principal Name. Maximum length is 100.
    + salaryAnniversaryDate (optional) - Salary Anniversary Date. Maximum length is 10.
    + eraCommonUserName (optional) - Era Commons User Name. Maximum length is 20.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"},
              {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kc Person Extended Attributes [GET /research-common/api/v1/kc-person-extended-attributes/]
	                                          
+ Parameters

      + _schema (required) - will instruct the endpoint to return a schema data structure for the resource
      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"columns":["personId","ageByFiscalYear","race","educationLevel","degree","major","handicappedFlag","handicapType","veteranFlag","veteranType","visaCode","visaType","visaRenewalDate","hasVisa","officeLocation","secondaryOfficeLocation","school","yearGraduated","directoryDepartment","primaryTitle","directoryTitle","vacationAccrualFlag","onSabbaticalFlag","idProvided","idVerified","county","citizenshipTypeCode","multiCampusPrincipalId","multiCampusPrincipalName","salaryAnniversaryDate","eraCommonUserName"],"primaryKey":"personId"}
		
### Get Blueprint API specification for Kc Person Extended Attributes [GET /research-common/api/v1/kc-person-extended-attributes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kc Person Extended Attributes.md"
            transfer-encoding:chunked
### Update Kc Person Extended Attributes [PUT /research-common/api/v1/kc-person-extended-attributes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kc Person Extended Attributes [PUT /research-common/api/v1/kc-person-extended-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"},
              {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Kc Person Extended Attributes [POST /research-common/api/v1/kc-person-extended-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kc Person Extended Attributes [POST /research-common/api/v1/kc-person-extended-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"},
              {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"},
              {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"}
            ]
### Delete Kc Person Extended Attributes by Key [DELETE /research-common/api/v1/kc-person-extended-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kc Person Extended Attributes [DELETE /research-common/api/v1/kc-person-extended-attributes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kc Person Extended Attributes with Matching [DELETE /research-common/api/v1/kc-person-extended-attributes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + personId (optional) - KcPersonExtendedAttributes Id. Maximum length is 40.
    + ageByFiscalYear (optional) - Age by Fiscal Year. Maximum length is 3.
    + race (optional) - Race. Maximum length is 30.
    + educationLevel (optional) - Education Level. Maximum length is 30.
    + degree (optional) - Degree. Maximum length is 11.
    + major (optional) - Major. Maximum length is 30.
    + handicappedFlag (optional) - Is Handicapped. Maximum length is 1.
    + handicapType (optional) - Handicap Type. Maximum length is 30.
    + veteranFlag (optional) - Veteran. Maximum length is 1.
    + veteranType (optional) - Veteran Type. Maximum length is 30.
    + visaCode (optional) - Visa Code. Maximum length is 20.
    + visaType (optional) - Visa Type. Maximum length is 30.
    + visaRenewalDate (optional) - Visa Renewal Date. Maximum length is 10.
    + hasVisa (optional) - Has Visa. Maximum length is 1.
    + officeLocation (optional) - Office Location. Maximum length is 30.
    + secondaryOfficeLocation (optional) - Secondary Office Location. Maximum length is 30.
    + school (optional) - School. Maximum length is 50.
    + yearGraduated (optional) - Year Graduated. Maximum length is 30.
    + directoryDepartment (optional) - Directory Department. Maximum length is 30.
    + primaryTitle (optional) - PrimaryTitle. Maximum length is 51.
    + directoryTitle (optional) - Directory Title. Maximum length is 50.
    + vacationAccrualFlag (optional) - Is Vacation Accrual. Maximum length is 1.
    + onSabbaticalFlag (optional) - Is on Sabbatical. Maximum length is 1.
    + idProvided (optional) - Id Provided. Maximum length is 30.
    + idVerified (optional) - Id Verified. Maximum length is 30.
    + county (optional) - County. Maximum length is 30.
    + citizenshipTypeCode (optional) - Citzenship Type. Maximum length is 3.
    + multiCampusPrincipalId (optional) - Multi-Campus Principal Id. Maximum length is 40.
    + multiCampusPrincipalName (optional) - Multi-Campus Principal Name. Maximum length is 100.
    + salaryAnniversaryDate (optional) - Salary Anniversary Date. Maximum length is 10.
    + eraCommonUserName (optional) - Era Commons User Name. Maximum length is 20.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
