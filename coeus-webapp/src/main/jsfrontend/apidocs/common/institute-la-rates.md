## Institute La Rates [/research-common/api/v1/institute-la-rates/]

### Get Institute La Rates by Key [GET /research-common/api/v1/institute-la-rates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Institute La Rates [GET /research-common/api/v1/institute-la-rates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institute La Rates with Filtering [GET /research-common/api/v1/institute-la-rates/]
    
+ Parameters

    + fiscalYear (optional) - Fiscal Year. Maximum length is 4.
    + onOffCampusFlag (optional) - On Off CampusContractContract Flag. Maximum length is 1.
    + rateClassCode (optional) - Rate Class Code. Maximum length is 3.
    + rateTypeCode (optional) - Rate Type Code. Maximum length is 3.
    + startDate (optional) - Start Date. Maximum length is 10.
    + unitNumber (optional) - Unit Number. Maximum length is 8.
    + instituteRate (optional) - Rate. Maximum length is 5.
    + active (optional) - Active. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institute La Rates [GET /research-common/api/v1/institute-la-rates/]
	                                          
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
    
            {"columns":["fiscalYear","onOffCampusFlag","rateClassCode","rateTypeCode","startDate","unitNumber","instituteRate","active"],"primaryKey":"fiscalYear:onOffCampusFlag:rateClassCode:rateTypeCode:startDate:unitNumber"}
		
### Get Blueprint API specification for Institute La Rates [GET /research-common/api/v1/institute-la-rates/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institute La Rates.md"
            transfer-encoding:chunked
### Update Institute La Rates [PUT /research-common/api/v1/institute-la-rates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institute La Rates [PUT /research-common/api/v1/institute-la-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Institute La Rates [POST /research-common/api/v1/institute-la-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institute La Rates [POST /research-common/api/v1/institute-la-rates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"fiscalYear": "(val)","onOffCampusFlag": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","startDate": "(val)","unitNumber": "(val)","instituteRate": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Institute La Rates by Key [DELETE /research-common/api/v1/institute-la-rates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institute La Rates [DELETE /research-common/api/v1/institute-la-rates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institute La Rates with Matching [DELETE /research-common/api/v1/institute-la-rates/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + fiscalYear (optional) - Fiscal Year. Maximum length is 4.
    + onOffCampusFlag (optional) - On Off CampusContractContract Flag. Maximum length is 1.
    + rateClassCode (optional) - Rate Class Code. Maximum length is 3.
    + rateTypeCode (optional) - Rate Type Code. Maximum length is 3.
    + startDate (optional) - Start Date. Maximum length is 10.
    + unitNumber (optional) - Unit Number. Maximum length is 8.
    + instituteRate (optional) - Rate. Maximum length is 5.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
