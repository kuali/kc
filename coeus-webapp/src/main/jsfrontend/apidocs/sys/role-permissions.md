## Role Permissions [/research-sys/api/v1/role-permissions/]

### Get Role Permissions by Key [GET /research-sys/api/v1/role-permissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","roleId": "(val)","permissionId": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Role Permissions [GET /research-sys/api/v1/role-permissions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","roleId": "(val)","permissionId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","roleId": "(val)","permissionId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Role Permissions with Filtering [GET /research-sys/api/v1/role-permissions/]
    
+ Parameters

        + id
            + roleId
            + permissionId
            + active

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","roleId": "(val)","permissionId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","roleId": "(val)","permissionId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Role Permissions [GET /research-sys/api/v1/role-permissions/]
	                                          
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
    
            {"columns":["id","roleId","permissionId","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Role Permissions [GET /research-sys/api/v1/role-permissions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Role Permissions.md"
            transfer-encoding:chunked


### Update Role Permissions [PUT /research-sys/api/v1/role-permissions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","roleId": "(val)","permissionId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Role Permissions [PUT /research-sys/api/v1/role-permissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","roleId": "(val)","permissionId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","roleId": "(val)","permissionId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Role Permissions [POST /research-sys/api/v1/role-permissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","roleId": "(val)","permissionId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","roleId": "(val)","permissionId": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Role Permissions [POST /research-sys/api/v1/role-permissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","roleId": "(val)","permissionId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","roleId": "(val)","permissionId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","roleId": "(val)","permissionId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","roleId": "(val)","permissionId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Role Permissions by Key [DELETE /research-sys/api/v1/role-permissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Role Permissions [DELETE /research-sys/api/v1/role-permissions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Role Permissions with Matching [DELETE /research-sys/api/v1/role-permissions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + roleId
            + permissionId
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
