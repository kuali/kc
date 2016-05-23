## Term Specifications [/research-sys/api/v1/term-specifications/]

### Get Term Specifications by Key [GET /research-sys/api/v1/term-specifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","namespace": "(val)","type": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Term Specifications [GET /research-sys/api/v1/term-specifications/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","type": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","type": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Term Specifications with Filtering [GET /research-sys/api/v1/term-specifications/]
    
+ Parameters

    + id (optional) - ID.
    + name (optional) - Name.
    + namespace (optional) - Namespace.
    + type (optional) - Data Type.
    + description (optional) - Description.
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
              {"id": "(val)","name": "(val)","namespace": "(val)","type": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","type": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Term Specifications [GET /research-sys/api/v1/term-specifications/]
	                                          
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
    
            {"columns":["id","name","namespace","type","description","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Term Specifications [GET /research-sys/api/v1/term-specifications/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Term Specifications.md"
            transfer-encoding:chunked
### Update Term Specifications [PUT /research-sys/api/v1/term-specifications/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","namespace": "(val)","type": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Term Specifications [PUT /research-sys/api/v1/term-specifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","type": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","type": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Term Specifications [POST /research-sys/api/v1/term-specifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","namespace": "(val)","type": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","namespace": "(val)","type": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Term Specifications [POST /research-sys/api/v1/term-specifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","type": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","type": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","type": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","type": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Term Specifications by Key [DELETE /research-sys/api/v1/term-specifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Term Specifications [DELETE /research-sys/api/v1/term-specifications/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Term Specifications with Matching [DELETE /research-sys/api/v1/term-specifications/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - ID.
    + name (optional) - Name.
    + namespace (optional) - Namespace.
    + type (optional) - Data Type.
    + description (optional) - Description.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
