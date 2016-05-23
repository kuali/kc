## Membership Roles [/research-common/api/v1/membership-roles/]

### Get Membership Roles by Key [GET /research-common/api/v1/membership-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Membership Roles [GET /research-common/api/v1/membership-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"},
              {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Membership Roles with Filtering [GET /research-common/api/v1/membership-roles/]
    
+ Parameters

    + membershipRoleCode (optional) - Membership Role Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + committeeTypeCode (optional) - Committee Type Code. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"},
              {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Membership Roles [GET /research-common/api/v1/membership-roles/]
	                                          
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
    
            {"columns":["membershipRoleCode","description","committeeTypeCode"],"primaryKey":"membershipRoleCode"}
		
### Get Blueprint API specification for Membership Roles [GET /research-common/api/v1/membership-roles/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Membership Roles.md"
            transfer-encoding:chunked
### Update Membership Roles [PUT /research-common/api/v1/membership-roles/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Membership Roles [PUT /research-common/api/v1/membership-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"},
              {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Membership Roles [POST /research-common/api/v1/membership-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Membership Roles [POST /research-common/api/v1/membership-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"},
              {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"},
              {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Membership Roles by Key [DELETE /research-common/api/v1/membership-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Membership Roles [DELETE /research-common/api/v1/membership-roles/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Membership Roles with Matching [DELETE /research-common/api/v1/membership-roles/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + membershipRoleCode (optional) - Membership Role Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + committeeTypeCode (optional) - Committee Type Code. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
