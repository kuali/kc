## Committee Membership Roles [/research-common/api/v1/committee-membership-roles/]

### Get Committee Membership Roles by Key [GET /research-common/api/v1/committee-membership-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}

### Get All Committee Membership Roles [GET /research-common/api/v1/committee-membership-roles/]
	 
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

### Get All Committee Membership Roles with Filtering [GET /research-common/api/v1/committee-membership-roles/]
    
+ Parameters

    + committeeMembershipRoleId (optional) - Committee Member Role Id. Maximum length is 22.
    + committeeMembershipIdFk (optional) - Committee Membership Id Fk. Maximum length is 22.
    + membershipRoleCode (optional) - Membership Role Code. Maximum length is 3.
    + startDate (optional) - Start Date. Maximum length is 10.
    + endDate (optional) - End Date. Maximum length is 10.

            
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
			
### Get Schema for Committee Membership Roles [GET /research-common/api/v1/committee-membership-roles/]
	                                          
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
    
            {"columns":["committeeMembershipRoleId","committeeMembershipIdFk","membershipRoleCode","startDate","endDate"],"primaryKey":"committeeMembershipRoleId"}
		
### Get Blueprint API specification for Committee Membership Roles [GET /research-common/api/v1/committee-membership-roles/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committee Membership Roles.md"
            transfer-encoding:chunked


### Update Committee Membership Roles [PUT /research-common/api/v1/committee-membership-roles/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Membership Roles [PUT /research-common/api/v1/committee-membership-roles/]

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

### Insert Committee Membership Roles [POST /research-common/api/v1/committee-membership-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"committeeMembershipRoleId": "(val)","committeeMembershipIdFk": "(val)","membershipRoleCode": "(val)","startDate": "(val)","endDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Membership Roles [POST /research-common/api/v1/committee-membership-roles/]

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
            
### Delete Committee Membership Roles by Key [DELETE /research-common/api/v1/committee-membership-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Membership Roles [DELETE /research-common/api/v1/committee-membership-roles/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Membership Roles with Matching [DELETE /research-common/api/v1/committee-membership-roles/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + committeeMembershipRoleId (optional) - Committee Member Role Id. Maximum length is 22.
    + committeeMembershipIdFk (optional) - Committee Membership Id Fk. Maximum length is 22.
    + membershipRoleCode (optional) - Membership Role Code. Maximum length is 3.
    + startDate (optional) - Start Date. Maximum length is 10.
    + endDate (optional) - End Date. Maximum length is 10.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
