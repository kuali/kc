## Committee Membership Roles [/research-sys/api/v1/committee-membership-roles/]

### Get Committee Membership Roles by Key [GET /research-sys/api/v1/committee-membership-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}

### Get All Committee Membership Roles [GET /research-sys/api/v1/committee-membership-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"},
              {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Committee Membership Roles with Filtering [GET /research-sys/api/v1/committee-membership-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + committeeMembershipRoleId
            + committeeMembershipIdFk
            + membershipRoleCode
            + startDate
            + endDate
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"},
              {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Committee Membership Roles [GET /research-sys/api/v1/committee-membership-roles/]
	 
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
		
### Get Blueprint API specification for Committee Membership Roles [GET /research-sys/api/v1/committee-membership-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committee Membership Roles.md"
            transfer-encoding:chunked


### Update Committee Membership Roles [PUT /research-sys/api/v1/committee-membership-roles/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Membership Roles [PUT /research-sys/api/v1/committee-membership-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"},
              {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Committee Membership Roles [POST /research-sys/api/v1/committee-membership-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Membership Roles [POST /research-sys/api/v1/committee-membership-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"},
              {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"},
              {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Committee Membership Roles by Key [DELETE /research-sys/api/v1/committee-membership-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Membership Roles [DELETE /research-sys/api/v1/committee-membership-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Committee Membership Roles with Matching [DELETE /research-sys/api/v1/committee-membership-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + committeeMembershipRoleId
            + committeeMembershipIdFk
            + membershipRoleCode
            + startDate
            + endDate


+ Response 204
