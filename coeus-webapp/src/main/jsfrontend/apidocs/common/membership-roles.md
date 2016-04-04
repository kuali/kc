## Membership Roles [/research-sys/api/v1/membership-roles/]

### Get Membership Roles by Key [GET /research-sys/api/v1/membership-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Membership Roles [GET /research-sys/api/v1/membership-roles/]
	 
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

### Get All Membership Roles with Filtering [GET /research-sys/api/v1/membership-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + membershipRoleCode
            + description
            + committeeTypeCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"},
              {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Membership Roles [GET /research-sys/api/v1/membership-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Membership Roles [GET /research-sys/api/v1/membership-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Membership Roles.md"
            transfer-encoding:chunked


### Update Membership Roles [PUT /research-sys/api/v1/membership-roles/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Membership Roles [PUT /research-sys/api/v1/membership-roles/]

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

### Insert Membership Roles [POST /research-sys/api/v1/membership-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"membershipRoleCode": "(val)","description": "(val)","committeeTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Membership Roles [POST /research-sys/api/v1/membership-roles/]

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
            
### Delete Membership Roles by Key [DELETE /research-sys/api/v1/membership-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Membership Roles [DELETE /research-sys/api/v1/membership-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Membership Roles with Matching [DELETE /research-sys/api/v1/membership-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + membershipRoleCode
            + description
            + committeeTypeCode


+ Response 204
