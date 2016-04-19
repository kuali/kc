## Counties [/research-sys/api/v1/counties/]

### Get Counties by Key [GET /research-sys/api/v1/counties/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","countryCode": "(val)","stateCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Counties [GET /research-sys/api/v1/counties/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","countryCode": "(val)","stateCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","countryCode": "(val)","stateCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Counties with Filtering [GET /research-sys/api/v1/counties/]
    
+ Parameters

    + code (optional) - The ten digit code for a County. Maximum length is 10.
    + countryCode (optional) - The code uniquely identify a country. Maximum length is 2.
    + stateCode (optional) - Postal State Code. Maximum length is 2.
    + name (optional) - The name assigned to this County. Maximum length is 100.
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
              {"code": "(val)","countryCode": "(val)","stateCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","countryCode": "(val)","stateCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Counties [GET /research-sys/api/v1/counties/]
	                                          
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
    
            {"columns":["code","countryCode","stateCode","name","active"],"primaryKey":"code:countryCode:stateCode"}
		
### Get Blueprint API specification for Counties [GET /research-sys/api/v1/counties/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Counties.md"
            transfer-encoding:chunked


### Update Counties [PUT /research-sys/api/v1/counties/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","countryCode": "(val)","stateCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Counties [PUT /research-sys/api/v1/counties/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","countryCode": "(val)","stateCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","countryCode": "(val)","stateCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Counties [POST /research-sys/api/v1/counties/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","countryCode": "(val)","stateCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","countryCode": "(val)","stateCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Counties [POST /research-sys/api/v1/counties/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","countryCode": "(val)","stateCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","countryCode": "(val)","stateCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","countryCode": "(val)","stateCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","countryCode": "(val)","stateCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Counties by Key [DELETE /research-sys/api/v1/counties/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Counties [DELETE /research-sys/api/v1/counties/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Counties with Matching [DELETE /research-sys/api/v1/counties/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - The ten digit code for a County. Maximum length is 10.
    + countryCode (optional) - The code uniquely identify a country. Maximum length is 2.
    + stateCode (optional) - Postal State Code. Maximum length is 2.
    + name (optional) - The name assigned to this County. Maximum length is 100.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
