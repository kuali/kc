## Kc Person Extended Attributes [/research-sys/api/v1/kc-person-extended-attributes/]

### Get Kc Person Extended Attributes by Key [GET /research-sys/api/v1/kc-person-extended-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"}

### Get All Kc Person Extended Attributes [GET /research-sys/api/v1/kc-person-extended-attributes/]
	 
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

### Get All Kc Person Extended Attributes with Filtering [GET /research-sys/api/v1/kc-person-extended-attributes/]
    
+ Parameters

        + personId
            + ageByFiscalYear
            + race
            + educationLevel
            + degree
            + major
            + handicappedFlag
            + handicapType
            + veteranFlag
            + veteranType
            + visaCode
            + visaType
            + visaRenewalDate
            + hasVisa
            + officeLocation
            + secondaryOfficeLocation
            + school
            + yearGraduated
            + directoryDepartment
            + primaryTitle
            + directoryTitle
            + vacationAccrualFlag
            + onSabbaticalFlag
            + idProvided
            + idVerified
            + county
            + citizenshipTypeCode
            + multiCampusPrincipalId
            + multiCampusPrincipalName
            + salaryAnniversaryDate
            + eraCommonUserName

            
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
			
### Get Schema for Kc Person Extended Attributes [GET /research-sys/api/v1/kc-person-extended-attributes/]
	                                          
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
		
### Get Blueprint API specification for Kc Person Extended Attributes [GET /research-sys/api/v1/kc-person-extended-attributes/]
	 
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


### Update Kc Person Extended Attributes [PUT /research-sys/api/v1/kc-person-extended-attributes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kc Person Extended Attributes [PUT /research-sys/api/v1/kc-person-extended-attributes/]

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

### Insert Kc Person Extended Attributes [POST /research-sys/api/v1/kc-person-extended-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personId": "(val)","ageByFiscalYear": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","secondaryOfficeLocation": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","county": "(val)","citizenshipTypeCode": "(val)","multiCampusPrincipalId": "(val)","multiCampusPrincipalName": "(val)","salaryAnniversaryDate": "(val)","eraCommonUserName": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kc Person Extended Attributes [POST /research-sys/api/v1/kc-person-extended-attributes/]

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
            
### Delete Kc Person Extended Attributes by Key [DELETE /research-sys/api/v1/kc-person-extended-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kc Person Extended Attributes [DELETE /research-sys/api/v1/kc-person-extended-attributes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kc Person Extended Attributes with Matching [DELETE /research-sys/api/v1/kc-person-extended-attributes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + personId
            + ageByFiscalYear
            + race
            + educationLevel
            + degree
            + major
            + handicappedFlag
            + handicapType
            + veteranFlag
            + veteranType
            + visaCode
            + visaType
            + visaRenewalDate
            + hasVisa
            + officeLocation
            + secondaryOfficeLocation
            + school
            + yearGraduated
            + directoryDepartment
            + primaryTitle
            + directoryTitle
            + vacationAccrualFlag
            + onSabbaticalFlag
            + idProvided
            + idVerified
            + county
            + citizenshipTypeCode
            + multiCampusPrincipalId
            + multiCampusPrincipalName
            + salaryAnniversaryDate
            + eraCommonUserName

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
