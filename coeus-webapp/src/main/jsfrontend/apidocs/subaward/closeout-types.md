## Closeout Types [/subaward/api/v1/closeout-types/]

### Get Closeout Types by Key [GET /subaward/api/v1/closeout-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"closeoutTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Closeout Types [GET /subaward/api/v1/closeout-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"closeoutTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"closeoutTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Closeout Types with Filtering [GET /subaward/api/v1/closeout-types/]
    
+ Parameters

    + closeoutTypeCode (optional) - Closeout Type. Maximum length is 22.
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
              {"closeoutTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"closeoutTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Closeout Types [GET /subaward/api/v1/closeout-types/]
	                                          
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
    
            {"columns":["closeoutTypeCode","description"],"primaryKey":"closeoutTypeCode"}
		
### Get Blueprint API specification for Closeout Types [GET /subaward/api/v1/closeout-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Closeout Types.md"
            transfer-encoding:chunked
### Update Closeout Types [PUT /subaward/api/v1/closeout-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"closeoutTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Closeout Types [PUT /subaward/api/v1/closeout-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"closeoutTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"closeoutTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Closeout Types [POST /subaward/api/v1/closeout-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"closeoutTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"closeoutTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Closeout Types [POST /subaward/api/v1/closeout-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"closeoutTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"closeoutTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"closeoutTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"closeoutTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Closeout Types by Key [DELETE /subaward/api/v1/closeout-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Closeout Types [DELETE /subaward/api/v1/closeout-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Closeout Types with Matching [DELETE /subaward/api/v1/closeout-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + closeoutTypeCode (optional) - Closeout Type. Maximum length is 22.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
