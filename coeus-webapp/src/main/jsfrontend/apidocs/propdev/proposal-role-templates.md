## Proposal Role Templates [/propdev/api/v1/proposal-role-templates/]

### Get Proposal Role Templates by Key [GET /propdev/api/v1/proposal-role-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Proposal Role Templates [GET /propdev/api/v1/proposal-role-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Role Templates with Filtering [GET /propdev/api/v1/proposal-role-templates/]
    
+ Parameters

    + id (optional) - Id. Maximum length is 8.
    + personId (optional) - KcPerson Id. Maximum length is 40.
    + roleName (optional) - Role Name. Maximum length is 500.
    + unitNumber (optional) - Unit Number. Maximum length is 8.
    + active (optional) - Active. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Role Templates [GET /propdev/api/v1/proposal-role-templates/]
	                                          
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
    
            {"columns":["id","personId","roleName","unitNumber","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Proposal Role Templates [GET /propdev/api/v1/proposal-role-templates/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Role Templates.md"
            transfer-encoding:chunked
### Update Proposal Role Templates [PUT /propdev/api/v1/proposal-role-templates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Role Templates [PUT /propdev/api/v1/proposal-role-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Proposal Role Templates [POST /propdev/api/v1/proposal-role-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Role Templates [POST /propdev/api/v1/proposal-role-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","personId": "(val)","roleName": "(val)","unitNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Proposal Role Templates by Key [DELETE /propdev/api/v1/proposal-role-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Role Templates [DELETE /propdev/api/v1/proposal-role-templates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Role Templates with Matching [DELETE /propdev/api/v1/proposal-role-templates/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Id. Maximum length is 8.
    + personId (optional) - KcPerson Id. Maximum length is 40.
    + roleName (optional) - Role Name. Maximum length is 500.
    + unitNumber (optional) - Unit Number. Maximum length is 8.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
