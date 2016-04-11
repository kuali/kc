## Sub Award Template Types [/research-sys/api/v1/sub-award-template-types/]

### Get Sub Award Template Types by Key [GET /research-sys/api/v1/sub-award-template-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"templateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Template Types [GET /research-sys/api/v1/sub-award-template-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"templateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"templateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Template Types with Filtering [GET /research-sys/api/v1/sub-award-template-types/]
    
+ Parameters

        + templateTypeCode
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
              {"templateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"templateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Template Types [GET /research-sys/api/v1/sub-award-template-types/]
	                                          
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
    
            {"columns":["templateTypeCode","description"],"primaryKey":"templateTypeCode"}
		
### Get Blueprint API specification for Sub Award Template Types [GET /research-sys/api/v1/sub-award-template-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Template Types.md"
            transfer-encoding:chunked


### Update Sub Award Template Types [PUT /research-sys/api/v1/sub-award-template-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"templateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Template Types [PUT /research-sys/api/v1/sub-award-template-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"templateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"templateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Sub Award Template Types [POST /research-sys/api/v1/sub-award-template-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"templateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"templateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Template Types [POST /research-sys/api/v1/sub-award-template-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"templateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"templateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"templateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"templateTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Sub Award Template Types by Key [DELETE /research-sys/api/v1/sub-award-template-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Template Types [DELETE /research-sys/api/v1/sub-award-template-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Template Types with Matching [DELETE /research-sys/api/v1/sub-award-template-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + templateTypeCode
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
