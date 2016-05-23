## Kim Types [/research-sys/api/v1/kim-types/]

### Get Kim Types by Key [GET /research-sys/api/v1/kim-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","serviceName": "(val)","namespaceCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Kim Types [GET /research-sys/api/v1/kim-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","serviceName": "(val)","namespaceCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","serviceName": "(val)","namespaceCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kim Types with Filtering [GET /research-sys/api/v1/kim-types/]
    
+ Parameters

    + id (optional) - Type Identifier. Maximum length is 40.
    + serviceName (optional) - Srvc Nm. Maximum length is 200.
    + namespaceCode (optional) - Nmspc Cd. Maximum length is 20.
    + name (optional) - Nm. Maximum length is 100.
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
              {"id": "(val)","serviceName": "(val)","namespaceCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","serviceName": "(val)","namespaceCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kim Types [GET /research-sys/api/v1/kim-types/]
	                                          
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
    
            {"columns":["id","serviceName","namespaceCode","name","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Kim Types [GET /research-sys/api/v1/kim-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kim Types.md"
            transfer-encoding:chunked
### Update Kim Types [PUT /research-sys/api/v1/kim-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","serviceName": "(val)","namespaceCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kim Types [PUT /research-sys/api/v1/kim-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","serviceName": "(val)","namespaceCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","serviceName": "(val)","namespaceCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Kim Types [POST /research-sys/api/v1/kim-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","serviceName": "(val)","namespaceCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","serviceName": "(val)","namespaceCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kim Types [POST /research-sys/api/v1/kim-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","serviceName": "(val)","namespaceCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","serviceName": "(val)","namespaceCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","serviceName": "(val)","namespaceCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","serviceName": "(val)","namespaceCode": "(val)","name": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Kim Types by Key [DELETE /research-sys/api/v1/kim-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kim Types [DELETE /research-sys/api/v1/kim-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kim Types with Matching [DELETE /research-sys/api/v1/kim-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Type Identifier. Maximum length is 40.
    + serviceName (optional) - Srvc Nm. Maximum length is 200.
    + namespaceCode (optional) - Nmspc Cd. Maximum length is 20.
    + name (optional) - Nm. Maximum length is 100.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
