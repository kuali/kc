## Financial Indirect Cost Recovery Type Codes [/award/api/v1/financial-indirect-cost-recovery-type-codes/]

### Get Financial Indirect Cost Recovery Type Codes by Key [GET /award/api/v1/financial-indirect-cost-recovery-type-codes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Financial Indirect Cost Recovery Type Codes [GET /award/api/v1/financial-indirect-cost-recovery-type-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"},
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Financial Indirect Cost Recovery Type Codes with Filtering [GET /award/api/v1/financial-indirect-cost-recovery-type-codes/]
    
+ Parameters

    + icrTypeCode (optional) - Icr Type Code. Maximum length is 8.
    + rateClassCode (optional) - Rate Class Code. Maximum length is 3.
    + rateTypeCode (optional) - Rate Type Code. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"},
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Financial Indirect Cost Recovery Type Codes [GET /award/api/v1/financial-indirect-cost-recovery-type-codes/]
	                                          
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
    
            {"columns":["icrTypeCode","rateClassCode","rateTypeCode"],"primaryKey":"rateClassCode:rateTypeCode"}
		
### Get Blueprint API specification for Financial Indirect Cost Recovery Type Codes [GET /award/api/v1/financial-indirect-cost-recovery-type-codes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Financial Indirect Cost Recovery Type Codes.md"
            transfer-encoding:chunked
### Update Financial Indirect Cost Recovery Type Codes [PUT /award/api/v1/financial-indirect-cost-recovery-type-codes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Financial Indirect Cost Recovery Type Codes [PUT /award/api/v1/financial-indirect-cost-recovery-type-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"},
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Financial Indirect Cost Recovery Type Codes [POST /award/api/v1/financial-indirect-cost-recovery-type-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Financial Indirect Cost Recovery Type Codes [POST /award/api/v1/financial-indirect-cost-recovery-type-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"},
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"},
              {"icrTypeCode": "(val)","rateClassCode": "(val)","rateTypeCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Financial Indirect Cost Recovery Type Codes by Key [DELETE /award/api/v1/financial-indirect-cost-recovery-type-codes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Financial Indirect Cost Recovery Type Codes [DELETE /award/api/v1/financial-indirect-cost-recovery-type-codes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Financial Indirect Cost Recovery Type Codes with Matching [DELETE /award/api/v1/financial-indirect-cost-recovery-type-codes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + icrTypeCode (optional) - Icr Type Code. Maximum length is 8.
    + rateClassCode (optional) - Rate Class Code. Maximum length is 3.
    + rateTypeCode (optional) - Rate Type Code. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
