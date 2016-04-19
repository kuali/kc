## Identity Management Role Documents [/research-sys/api/v1/identity-management-role-documents/]

### Get Identity Management Role Documents by Key [GET /research-sys/api/v1/identity-management-role-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"roleId": "(val)","roleTypeId": "(val)","roleNamespace": "(val)","roleName": "(val)","roleDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}

### Get All Identity Management Role Documents [GET /research-sys/api/v1/identity-management-role-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"roleId": "(val)","roleTypeId": "(val)","roleNamespace": "(val)","roleName": "(val)","roleDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"roleId": "(val)","roleTypeId": "(val)","roleNamespace": "(val)","roleName": "(val)","roleDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Identity Management Role Documents with Filtering [GET /research-sys/api/v1/identity-management-role-documents/]
    
+ Parameters

    + roleId (optional) - Role Id. Maximum length is 40.
    + roleTypeId (optional) - Role Type Id.
    + roleNamespace (optional) - Nmspc Cd. Maximum length is 40.
    + roleName (optional) - Role Name. Maximum length is 100.
    + roleDescription (optional) - Role Description.
    + active (optional) - Active. Maximum length is 1.
    + documentNumber (optional) - Document Number. Maximum length is 14.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"roleId": "(val)","roleTypeId": "(val)","roleNamespace": "(val)","roleName": "(val)","roleDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"roleId": "(val)","roleTypeId": "(val)","roleNamespace": "(val)","roleName": "(val)","roleDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Identity Management Role Documents [GET /research-sys/api/v1/identity-management-role-documents/]
	                                          
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
    
            {"columns":["roleId","roleTypeId","roleNamespace","roleName","roleDescription","active","documentNumber"],"primaryKey":"documentNumber"}
		
### Get Blueprint API specification for Identity Management Role Documents [GET /research-sys/api/v1/identity-management-role-documents/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Identity Management Role Documents.md"
            transfer-encoding:chunked


### Update Identity Management Role Documents [PUT /research-sys/api/v1/identity-management-role-documents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleId": "(val)","roleTypeId": "(val)","roleNamespace": "(val)","roleName": "(val)","roleDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Identity Management Role Documents [PUT /research-sys/api/v1/identity-management-role-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"roleId": "(val)","roleTypeId": "(val)","roleNamespace": "(val)","roleName": "(val)","roleDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"roleId": "(val)","roleTypeId": "(val)","roleNamespace": "(val)","roleName": "(val)","roleDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Identity Management Role Documents [POST /research-sys/api/v1/identity-management-role-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleId": "(val)","roleTypeId": "(val)","roleNamespace": "(val)","roleName": "(val)","roleDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"roleId": "(val)","roleTypeId": "(val)","roleNamespace": "(val)","roleName": "(val)","roleDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Identity Management Role Documents [POST /research-sys/api/v1/identity-management-role-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"roleId": "(val)","roleTypeId": "(val)","roleNamespace": "(val)","roleName": "(val)","roleDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"roleId": "(val)","roleTypeId": "(val)","roleNamespace": "(val)","roleName": "(val)","roleDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"roleId": "(val)","roleTypeId": "(val)","roleNamespace": "(val)","roleName": "(val)","roleDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"roleId": "(val)","roleTypeId": "(val)","roleNamespace": "(val)","roleName": "(val)","roleDescription": "(val)","active": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Identity Management Role Documents by Key [DELETE /research-sys/api/v1/identity-management-role-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Identity Management Role Documents [DELETE /research-sys/api/v1/identity-management-role-documents/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Identity Management Role Documents with Matching [DELETE /research-sys/api/v1/identity-management-role-documents/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + roleId (optional) - Role Id. Maximum length is 40.
    + roleTypeId (optional) - Role Type Id.
    + roleNamespace (optional) - Nmspc Cd. Maximum length is 40.
    + roleName (optional) - Role Name. Maximum length is 100.
    + roleDescription (optional) - Role Description.
    + active (optional) - Active. Maximum length is 1.
    + documentNumber (optional) - Document Number. Maximum length is 14.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
