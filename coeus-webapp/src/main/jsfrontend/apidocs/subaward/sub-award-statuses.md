## Sub Award Statuses [/research-sys/api/v1/sub-award-statuses/]

### Get Sub Award Statuses by Key [GET /research-sys/api/v1/sub-award-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Statuses [GET /research-sys/api/v1/sub-award-statuses/]
	 
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

### Get All Sub Award Statuses with Filtering [GET /research-sys/api/v1/sub-award-statuses/]
    
+ Parameters

        + subAwardStatusCode
            + description

            
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
			
### Get Schema for Sub Award Statuses [GET /research-sys/api/v1/sub-award-statuses/]
	                                          
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
		
### Get Blueprint API specification for Sub Award Statuses [GET /research-sys/api/v1/sub-award-statuses/]
	 
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


### Update Sub Award Statuses [PUT /research-sys/api/v1/sub-award-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Statuses [PUT /research-sys/api/v1/sub-award-statuses/]

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

### Insert Sub Award Statuses [POST /research-sys/api/v1/sub-award-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Statuses [POST /research-sys/api/v1/sub-award-statuses/]

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
            
### Delete Sub Award Statuses by Key [DELETE /research-sys/api/v1/sub-award-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Statuses [DELETE /research-sys/api/v1/sub-award-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Statuses with Matching [DELETE /research-sys/api/v1/sub-award-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + subAwardStatusCode
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
