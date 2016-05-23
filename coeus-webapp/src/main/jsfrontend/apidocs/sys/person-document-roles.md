## Person Document Roles [/research-sys/api/v1/person-document-roles/]

### Get Person Document Roles by Key [GET /research-sys/api/v1/person-document-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"roleId": "(val)","kimTypeId": "(val)","roleName": "(val)","namespaceCode": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Person Document Roles [GET /research-sys/api/v1/person-document-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"roleId": "(val)","kimTypeId": "(val)","roleName": "(val)","namespaceCode": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleId": "(val)","kimTypeId": "(val)","roleName": "(val)","namespaceCode": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Document Roles with Filtering [GET /research-sys/api/v1/person-document-roles/]
    
+ Parameters

    + roleId (optional) - Role. Maximum length is 40.
    + kimTypeId (optional) - Type. Maximum length is 40.
    + roleName (optional) - Name. Maximum length is 80.
    + namespaceCode (optional) - Namespace Code. Maximum length is 40.
    + edit (optional) - Edit.
    + documentNumber (optional) - Document Number.
    + active (optional) - Active.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"roleId": "(val)","kimTypeId": "(val)","roleName": "(val)","namespaceCode": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleId": "(val)","kimTypeId": "(val)","roleName": "(val)","namespaceCode": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Document Roles [GET /research-sys/api/v1/person-document-roles/]
	                                          
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
    
            {"columns":["roleId","kimTypeId","roleName","namespaceCode","edit","documentNumber","active"],"primaryKey":"roleId"}
		
### Get Blueprint API specification for Person Document Roles [GET /research-sys/api/v1/person-document-roles/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Document Roles.md"
            transfer-encoding:chunked
### Update Person Document Roles [PUT /research-sys/api/v1/person-document-roles/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleId": "(val)","kimTypeId": "(val)","roleName": "(val)","namespaceCode": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Document Roles [PUT /research-sys/api/v1/person-document-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"roleId": "(val)","kimTypeId": "(val)","roleName": "(val)","namespaceCode": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleId": "(val)","kimTypeId": "(val)","roleName": "(val)","namespaceCode": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Person Document Roles [POST /research-sys/api/v1/person-document-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleId": "(val)","kimTypeId": "(val)","roleName": "(val)","namespaceCode": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"roleId": "(val)","kimTypeId": "(val)","roleName": "(val)","namespaceCode": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Document Roles [POST /research-sys/api/v1/person-document-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"roleId": "(val)","kimTypeId": "(val)","roleName": "(val)","namespaceCode": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleId": "(val)","kimTypeId": "(val)","roleName": "(val)","namespaceCode": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"roleId": "(val)","kimTypeId": "(val)","roleName": "(val)","namespaceCode": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"roleId": "(val)","kimTypeId": "(val)","roleName": "(val)","namespaceCode": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Person Document Roles by Key [DELETE /research-sys/api/v1/person-document-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Roles [DELETE /research-sys/api/v1/person-document-roles/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Roles with Matching [DELETE /research-sys/api/v1/person-document-roles/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + roleId (optional) - Role. Maximum length is 40.
    + kimTypeId (optional) - Type. Maximum length is 40.
    + roleName (optional) - Name. Maximum length is 80.
    + namespaceCode (optional) - Namespace Code. Maximum length is 40.
    + edit (optional) - Edit.
    + documentNumber (optional) - Document Number.
    + active (optional) - Active.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
