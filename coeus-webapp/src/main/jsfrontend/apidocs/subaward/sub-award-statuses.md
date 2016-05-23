## Sub Award Statuses [/subaward/api/v1/sub-award-statuses/]

### Get Sub Award Statuses by Key [GET /subaward/api/v1/sub-award-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Statuses [GET /subaward/api/v1/sub-award-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Statuses with Filtering [GET /subaward/api/v1/sub-award-statuses/]
    
+ Parameters

    + subAwardStatusCode (optional) - Subaward Status Code. Maximum length is 22.
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
              {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Statuses [GET /subaward/api/v1/sub-award-statuses/]
	                                          
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
    
            {"columns":["subAwardStatusCode","description"],"primaryKey":"subAwardStatusCode"}
		
### Get Blueprint API specification for Sub Award Statuses [GET /subaward/api/v1/sub-award-statuses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Statuses.md"
            transfer-encoding:chunked
### Update Sub Award Statuses [PUT /subaward/api/v1/sub-award-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Statuses [PUT /subaward/api/v1/sub-award-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Sub Award Statuses [POST /subaward/api/v1/sub-award-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Statuses [POST /subaward/api/v1/sub-award-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Sub Award Statuses by Key [DELETE /subaward/api/v1/sub-award-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Statuses [DELETE /subaward/api/v1/sub-award-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Statuses with Matching [DELETE /subaward/api/v1/sub-award-statuses/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + subAwardStatusCode (optional) - Subaward Status Code. Maximum length is 22.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
