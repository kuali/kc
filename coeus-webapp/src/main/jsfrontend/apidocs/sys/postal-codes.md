## Postal Codes [/research-sys/api/v1/postal-codes/]

### Get Postal Codes by Key [GET /research-sys/api/v1/postal-codes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","countryCode": "(val)","cityName": "(val)","stateCode": "(val)","countyCode": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Postal Codes [GET /research-sys/api/v1/postal-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","countryCode": "(val)","cityName": "(val)","stateCode": "(val)","countyCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","countryCode": "(val)","cityName": "(val)","stateCode": "(val)","countyCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Postal Codes with Filtering [GET /research-sys/api/v1/postal-codes/]
    
+ Parameters

    + code (optional) - Postal Code either in 5-4 format or just 5 digits. Maximum length is 20.
    + countryCode (optional) - The code uniquely identify a country. Maximum length is 2.
    + cityName (optional) - City Name. Maximum length is 30.
    + stateCode (optional) - Postal State Code. Maximum length is 2.
    + countyCode (optional) - County Code. Maximum length is 10.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","countryCode": "(val)","cityName": "(val)","stateCode": "(val)","countyCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","countryCode": "(val)","cityName": "(val)","stateCode": "(val)","countyCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Postal Codes [GET /research-sys/api/v1/postal-codes/]
	                                          
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
    
            {"columns":["code","countryCode","cityName","stateCode","countyCode","active"],"primaryKey":"code:countryCode"}
		
### Get Blueprint API specification for Postal Codes [GET /research-sys/api/v1/postal-codes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Postal Codes.md"
            transfer-encoding:chunked


### Update Postal Codes [PUT /research-sys/api/v1/postal-codes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","countryCode": "(val)","cityName": "(val)","stateCode": "(val)","countyCode": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Postal Codes [PUT /research-sys/api/v1/postal-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","countryCode": "(val)","cityName": "(val)","stateCode": "(val)","countyCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","countryCode": "(val)","cityName": "(val)","stateCode": "(val)","countyCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Postal Codes [POST /research-sys/api/v1/postal-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","countryCode": "(val)","cityName": "(val)","stateCode": "(val)","countyCode": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","countryCode": "(val)","cityName": "(val)","stateCode": "(val)","countyCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Postal Codes [POST /research-sys/api/v1/postal-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","countryCode": "(val)","cityName": "(val)","stateCode": "(val)","countyCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","countryCode": "(val)","cityName": "(val)","stateCode": "(val)","countyCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","countryCode": "(val)","cityName": "(val)","stateCode": "(val)","countyCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","countryCode": "(val)","cityName": "(val)","stateCode": "(val)","countyCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Postal Codes by Key [DELETE /research-sys/api/v1/postal-codes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Postal Codes [DELETE /research-sys/api/v1/postal-codes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Postal Codes with Matching [DELETE /research-sys/api/v1/postal-codes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - Postal Code either in 5-4 format or just 5 digits. Maximum length is 20.
    + countryCode (optional) - The code uniquely identify a country. Maximum length is 2.
    + cityName (optional) - City Name. Maximum length is 30.
    + stateCode (optional) - Postal State Code. Maximum length is 2.
    + countyCode (optional) - County Code. Maximum length is 10.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
