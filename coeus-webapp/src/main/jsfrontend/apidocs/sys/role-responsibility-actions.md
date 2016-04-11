## Role Responsibility Actions [/research-sys/api/v1/role-responsibility-actions/]

### Get Role Responsibility Actions by Key [GET /research-sys/api/v1/role-responsibility-actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","forceAction": "(val)","priorityNumber": "(val)","_primaryKey": "(val)"}

### Get All Role Responsibility Actions [GET /research-sys/api/v1/role-responsibility-actions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","forceAction": "(val)","priorityNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","forceAction": "(val)","priorityNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Role Responsibility Actions with Filtering [GET /research-sys/api/v1/role-responsibility-actions/]
    
+ Parameters

        + id
            + roleResponsibilityId
            + roleMemberId
            + actionTypeCode
            + actionPolicyCode
            + forceAction
            + priorityNumber

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","forceAction": "(val)","priorityNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","forceAction": "(val)","priorityNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Role Responsibility Actions [GET /research-sys/api/v1/role-responsibility-actions/]
	                                          
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
    
            {"columns":["id","roleResponsibilityId","roleMemberId","actionTypeCode","actionPolicyCode","forceAction","priorityNumber"],"primaryKey":"id"}
		
### Get Blueprint API specification for Role Responsibility Actions [GET /research-sys/api/v1/role-responsibility-actions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Role Responsibility Actions.md"
            transfer-encoding:chunked


### Update Role Responsibility Actions [PUT /research-sys/api/v1/role-responsibility-actions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","forceAction": "(val)","priorityNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Role Responsibility Actions [PUT /research-sys/api/v1/role-responsibility-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","forceAction": "(val)","priorityNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","forceAction": "(val)","priorityNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Role Responsibility Actions [POST /research-sys/api/v1/role-responsibility-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","forceAction": "(val)","priorityNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","forceAction": "(val)","priorityNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Role Responsibility Actions [POST /research-sys/api/v1/role-responsibility-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","forceAction": "(val)","priorityNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","forceAction": "(val)","priorityNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","forceAction": "(val)","priorityNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","forceAction": "(val)","priorityNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Role Responsibility Actions by Key [DELETE /research-sys/api/v1/role-responsibility-actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Role Responsibility Actions [DELETE /research-sys/api/v1/role-responsibility-actions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Role Responsibility Actions with Matching [DELETE /research-sys/api/v1/role-responsibility-actions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + roleResponsibilityId
            + roleMemberId
            + actionTypeCode
            + actionPolicyCode
            + forceAction
            + priorityNumber

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
