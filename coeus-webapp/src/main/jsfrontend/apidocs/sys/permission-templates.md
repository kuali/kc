## Permission Templates [/research-sys/api/v1/permission-templates/]

### Get Permission Templates by Key [GET /research-sys/api/v1/permission-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}

### Get All Permission Templates [GET /research-sys/api/v1/permission-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Permission Templates with Filtering [GET /research-sys/api/v1/permission-templates/]
    
+ Parameters

    + id (optional) - Template Id. Maximum length is 40.
    + kimTypeId (optional) - Kim Type Id.
    + name (optional) - Template Name. Maximum length is 40.
    + active (optional) - Active.
    + description (optional) - Permission Template Description. Maximum length is 40.
    + namespaceCode (optional) - Nmspc Cd. Maximum length is 40.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Permission Templates [GET /research-sys/api/v1/permission-templates/]
	                                          
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
    
            {"columns":["id","kimTypeId","name","active","description","namespaceCode"],"primaryKey":"id"}
		
### Get Blueprint API specification for Permission Templates [GET /research-sys/api/v1/permission-templates/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Permission Templates.md"
            transfer-encoding:chunked
### Update Permission Templates [PUT /research-sys/api/v1/permission-templates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Permission Templates [PUT /research-sys/api/v1/permission-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Permission Templates [POST /research-sys/api/v1/permission-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Permission Templates [POST /research-sys/api/v1/permission-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","kimTypeId": "(val)","name": "(val)","active": "(val)","description": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Permission Templates by Key [DELETE /research-sys/api/v1/permission-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Permission Templates [DELETE /research-sys/api/v1/permission-templates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Permission Templates with Matching [DELETE /research-sys/api/v1/permission-templates/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Template Id. Maximum length is 40.
    + kimTypeId (optional) - Kim Type Id.
    + name (optional) - Template Name. Maximum length is 40.
    + active (optional) - Active.
    + description (optional) - Permission Template Description. Maximum length is 40.
    + namespaceCode (optional) - Nmspc Cd. Maximum length is 40.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
