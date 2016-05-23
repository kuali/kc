## People Flow Members [/research-sys/api/v1/people-flow-members/]

### Get People Flow Members by Key [GET /research-sys/api/v1/people-flow-members/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","responsibilityId": "(val)","priority": "(val)","forceAction": "(val)","_primaryKey": "(val)"}

### Get All People Flow Members [GET /research-sys/api/v1/people-flow-members/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","responsibilityId": "(val)","priority": "(val)","forceAction": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","responsibilityId": "(val)","priority": "(val)","forceAction": "(val)","_primaryKey": "(val)"}
            ]

### Get All People Flow Members with Filtering [GET /research-sys/api/v1/people-flow-members/]
    
+ Parameters

    + id (optional) - Id.
    + memberId (optional) - Member id for the map stop. Maximum length is 40.
    + memberTypeCode (optional) - Type of the member the stop will go to. Maximum length is 1.
    + actionRequestPolicyCode (optional) - For role members determines whether all members must take action or just the first.
    + responsibilityId (optional) - Responsibility Id.
    + priority (optional) - Stop number that the member should receive the request.
    + forceAction (optional) - Specifies whether requests for this member must be acted on even if they have already taken that action in the workflow.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","responsibilityId": "(val)","priority": "(val)","forceAction": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","responsibilityId": "(val)","priority": "(val)","forceAction": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for People Flow Members [GET /research-sys/api/v1/people-flow-members/]
	                                          
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
    
            {"columns":["id","memberId","memberTypeCode","actionRequestPolicyCode","responsibilityId","priority","forceAction"],"primaryKey":"id"}
		
### Get Blueprint API specification for People Flow Members [GET /research-sys/api/v1/people-flow-members/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="People Flow Members.md"
            transfer-encoding:chunked
### Update People Flow Members [PUT /research-sys/api/v1/people-flow-members/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","responsibilityId": "(val)","priority": "(val)","forceAction": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple People Flow Members [PUT /research-sys/api/v1/people-flow-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","responsibilityId": "(val)","priority": "(val)","forceAction": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","responsibilityId": "(val)","priority": "(val)","forceAction": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert People Flow Members [POST /research-sys/api/v1/people-flow-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","responsibilityId": "(val)","priority": "(val)","forceAction": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","responsibilityId": "(val)","priority": "(val)","forceAction": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple People Flow Members [POST /research-sys/api/v1/people-flow-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","responsibilityId": "(val)","priority": "(val)","forceAction": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","responsibilityId": "(val)","priority": "(val)","forceAction": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","responsibilityId": "(val)","priority": "(val)","forceAction": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","responsibilityId": "(val)","priority": "(val)","forceAction": "(val)","_primaryKey": "(val)"}
            ]
### Delete People Flow Members by Key [DELETE /research-sys/api/v1/people-flow-members/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All People Flow Members [DELETE /research-sys/api/v1/people-flow-members/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All People Flow Members with Matching [DELETE /research-sys/api/v1/people-flow-members/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Id.
    + memberId (optional) - Member id for the map stop. Maximum length is 40.
    + memberTypeCode (optional) - Type of the member the stop will go to. Maximum length is 1.
    + actionRequestPolicyCode (optional) - For role members determines whether all members must take action or just the first.
    + responsibilityId (optional) - Responsibility Id.
    + priority (optional) - Stop number that the member should receive the request.
    + forceAction (optional) - Specifies whether requests for this member must be acted on even if they have already taken that action in the workflow.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
