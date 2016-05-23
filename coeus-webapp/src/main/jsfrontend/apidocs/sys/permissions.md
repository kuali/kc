## Permissions [/research-sys/api/v1/permissions/]

### Get Permissions by Key [GET /research-sys/api/v1/permissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Permissions [GET /research-sys/api/v1/permissions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Permissions with Filtering [GET /research-sys/api/v1/permissions/]
    
+ Parameters

    + id (optional) - Permission Identifier. Maximum length is 40.
    + namespaceCode (optional) - This value is used to categorize parameters by namespace. Maximum length is 20.
    + name (optional) - Nm. Maximum length is 100.
    + description (optional) - Permission Description. Maximum length is 400.
    + templateId (optional) - Template Id. Maximum length is 40.
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
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Permissions [GET /research-sys/api/v1/permissions/]
	                                          
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
    
            {"columns":["id","namespaceCode","name","description","templateId","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Permissions [GET /research-sys/api/v1/permissions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Permissions.md"
            transfer-encoding:chunked
### Update Permissions [PUT /research-sys/api/v1/permissions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Permissions [PUT /research-sys/api/v1/permissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Permissions [POST /research-sys/api/v1/permissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Permissions [POST /research-sys/api/v1/permissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Permissions by Key [DELETE /research-sys/api/v1/permissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Permissions [DELETE /research-sys/api/v1/permissions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Permissions with Matching [DELETE /research-sys/api/v1/permissions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Permission Identifier. Maximum length is 40.
    + namespaceCode (optional) - This value is used to categorize parameters by namespace. Maximum length is 20.
    + name (optional) - Nm. Maximum length is 100.
    + description (optional) - Permission Description. Maximum length is 400.
    + templateId (optional) - Template Id. Maximum length is 40.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
