## Fanda Rate Types [/award/api/v1/fanda-rate-types/]

### Get Fanda Rate Types by Key [GET /award/api/v1/fanda-rate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Fanda Rate Types [GET /award/api/v1/fanda-rate-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Fanda Rate Types with Filtering [GET /award/api/v1/fanda-rate-types/]
    
+ Parameters

    + fandaRateTypeCode (optional) - F&A Rate Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Fanda Rate Types [GET /award/api/v1/fanda-rate-types/]
	                                          
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
    
            {"columns":["fandaRateTypeCode","description"],"primaryKey":"fandaRateTypeCode"}
		
### Get Blueprint API specification for Fanda Rate Types [GET /award/api/v1/fanda-rate-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Fanda Rate Types.md"
            transfer-encoding:chunked


### Update Fanda Rate Types [PUT /award/api/v1/fanda-rate-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Fanda Rate Types [PUT /award/api/v1/fanda-rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Fanda Rate Types [POST /award/api/v1/fanda-rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Fanda Rate Types [POST /award/api/v1/fanda-rate-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"fandaRateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Fanda Rate Types by Key [DELETE /award/api/v1/fanda-rate-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Fanda Rate Types [DELETE /award/api/v1/fanda-rate-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Fanda Rate Types with Matching [DELETE /award/api/v1/fanda-rate-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + fandaRateTypeCode (optional) - F&A Rate Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
