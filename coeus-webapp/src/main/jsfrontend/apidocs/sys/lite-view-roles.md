## Lite View Roles [/research-sys/api/v1/lite-view-roles/]

### Get Lite View Roles by Key [GET /research-sys/api/v1/lite-view-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}

### Get All Lite View Roles [GET /research-sys/api/v1/lite-view-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Lite View Roles with Filtering [GET /research-sys/api/v1/lite-view-roles/]
    
+ Parameters

        + id
            + name
            + description
            + active
            + kimTypeId
            + namespaceCode

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Lite View Roles [GET /research-sys/api/v1/lite-view-roles/]
	                                          
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
    
            {"columns":["id","name","description","active","kimTypeId","namespaceCode"],"primaryKey":"id"}
		
### Get Blueprint API specification for Lite View Roles [GET /research-sys/api/v1/lite-view-roles/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Lite View Roles.md"
            transfer-encoding:chunked


### Update Lite View Roles [PUT /research-sys/api/v1/lite-view-roles/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Lite View Roles [PUT /research-sys/api/v1/lite-view-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Lite View Roles [POST /research-sys/api/v1/lite-view-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Lite View Roles [POST /research-sys/api/v1/lite-view-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Lite View Roles by Key [DELETE /research-sys/api/v1/lite-view-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Lite View Roles [DELETE /research-sys/api/v1/lite-view-roles/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Lite View Roles with Matching [DELETE /research-sys/api/v1/lite-view-roles/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + name
            + description
            + active
            + kimTypeId
            + namespaceCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
