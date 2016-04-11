## Kim Document Role Permissions [/research-sys/api/v1/kim-document-role-permissions/]

### Get Kim Document Role Permissions by Key [GET /research-sys/api/v1/kim-document-role-permissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"rolePermissionId": "(val)","roleId": "(val)","permissionId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Kim Document Role Permissions [GET /research-sys/api/v1/kim-document-role-permissions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"rolePermissionId": "(val)","roleId": "(val)","permissionId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"rolePermissionId": "(val)","roleId": "(val)","permissionId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kim Document Role Permissions with Filtering [GET /research-sys/api/v1/kim-document-role-permissions/]
    
+ Parameters

        + rolePermissionId
            + roleId
            + permissionId
            + documentNumber
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
              {"rolePermissionId": "(val)","roleId": "(val)","permissionId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"rolePermissionId": "(val)","roleId": "(val)","permissionId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kim Document Role Permissions [GET /research-sys/api/v1/kim-document-role-permissions/]
	                                          
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
    
            {"columns":["rolePermissionId","roleId","permissionId","documentNumber","active"],"primaryKey":"rolePermissionId"}
		
### Get Blueprint API specification for Kim Document Role Permissions [GET /research-sys/api/v1/kim-document-role-permissions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kim Document Role Permissions.md"
            transfer-encoding:chunked


### Update Kim Document Role Permissions [PUT /research-sys/api/v1/kim-document-role-permissions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"rolePermissionId": "(val)","roleId": "(val)","permissionId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kim Document Role Permissions [PUT /research-sys/api/v1/kim-document-role-permissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"rolePermissionId": "(val)","roleId": "(val)","permissionId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"rolePermissionId": "(val)","roleId": "(val)","permissionId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Kim Document Role Permissions [POST /research-sys/api/v1/kim-document-role-permissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"rolePermissionId": "(val)","roleId": "(val)","permissionId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"rolePermissionId": "(val)","roleId": "(val)","permissionId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kim Document Role Permissions [POST /research-sys/api/v1/kim-document-role-permissions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"rolePermissionId": "(val)","roleId": "(val)","permissionId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"rolePermissionId": "(val)","roleId": "(val)","permissionId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"rolePermissionId": "(val)","roleId": "(val)","permissionId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"rolePermissionId": "(val)","roleId": "(val)","permissionId": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Kim Document Role Permissions by Key [DELETE /research-sys/api/v1/kim-document-role-permissions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kim Document Role Permissions [DELETE /research-sys/api/v1/kim-document-role-permissions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kim Document Role Permissions with Matching [DELETE /research-sys/api/v1/kim-document-role-permissions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + rolePermissionId
            + roleId
            + permissionId
            + documentNumber
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
