## Roles [/research-sys/api/v1/roles/]

### Get Roles by Key [GET /research-sys/api/v1/roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}

### Get All Roles [GET /research-sys/api/v1/roles/]
	 
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

### Get All Roles with Filtering [GET /research-sys/api/v1/roles/]
    
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
			
### Get Schema for Roles [GET /research-sys/api/v1/roles/]
	                                          
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
		
### Get Blueprint API specification for Roles [GET /research-sys/api/v1/roles/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Roles.md"
            transfer-encoding:chunked


### Update Roles [PUT /research-sys/api/v1/roles/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Roles [PUT /research-sys/api/v1/roles/]

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

### Insert Roles [POST /research-sys/api/v1/roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","description": "(val)","active": "(val)","kimTypeId": "(val)","namespaceCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Roles [POST /research-sys/api/v1/roles/]

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
            
### Delete Roles by Key [DELETE /research-sys/api/v1/roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Roles [DELETE /research-sys/api/v1/roles/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Roles with Matching [DELETE /research-sys/api/v1/roles/]

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
