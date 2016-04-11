## Coeus Modules [/research-sys/api/v1/coeus-modules/]

### Get Coeus Modules by Key [GET /research-sys/api/v1/coeus-modules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"moduleCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Coeus Modules [GET /research-sys/api/v1/coeus-modules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"moduleCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"moduleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Coeus Modules with Filtering [GET /research-sys/api/v1/coeus-modules/]
    
+ Parameters

        + moduleCode
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
              {"moduleCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"moduleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Coeus Modules [GET /research-sys/api/v1/coeus-modules/]
	                                          
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
    
            {"columns":["moduleCode","description"],"primaryKey":"moduleCode"}
		
### Get Blueprint API specification for Coeus Modules [GET /research-sys/api/v1/coeus-modules/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Coeus Modules.md"
            transfer-encoding:chunked


### Update Coeus Modules [PUT /research-sys/api/v1/coeus-modules/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"moduleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Coeus Modules [PUT /research-sys/api/v1/coeus-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"moduleCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"moduleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Coeus Modules [POST /research-sys/api/v1/coeus-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"moduleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"moduleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Coeus Modules [POST /research-sys/api/v1/coeus-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"moduleCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"moduleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"moduleCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"moduleCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Coeus Modules by Key [DELETE /research-sys/api/v1/coeus-modules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Coeus Modules [DELETE /research-sys/api/v1/coeus-modules/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Coeus Modules with Matching [DELETE /research-sys/api/v1/coeus-modules/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + moduleCode
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
