## People Flow Delegates [/research-sys/api/v1/people-flow-delegates/]

### Get People Flow Delegates by Key [GET /research-sys/api/v1/people-flow-delegates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","delegationTypeCode": "(val)","responsibilityId": "(val)","_primaryKey": "(val)"}

### Get All People Flow Delegates [GET /research-sys/api/v1/people-flow-delegates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","delegationTypeCode": "(val)","responsibilityId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","delegationTypeCode": "(val)","responsibilityId": "(val)","_primaryKey": "(val)"}
            ]

### Get All People Flow Delegates with Filtering [GET /research-sys/api/v1/people-flow-delegates/]
    
+ Parameters

        + id
            + memberId
            + memberTypeCode
            + actionRequestPolicyCode
            + delegationTypeCode
            + responsibilityId

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","delegationTypeCode": "(val)","responsibilityId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","delegationTypeCode": "(val)","responsibilityId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for People Flow Delegates [GET /research-sys/api/v1/people-flow-delegates/]
	                                          
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
    
            {"columns":["id","memberId","memberTypeCode","actionRequestPolicyCode","delegationTypeCode","responsibilityId"],"primaryKey":"id"}
		
### Get Blueprint API specification for People Flow Delegates [GET /research-sys/api/v1/people-flow-delegates/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="People Flow Delegates.md"
            transfer-encoding:chunked


### Update People Flow Delegates [PUT /research-sys/api/v1/people-flow-delegates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","delegationTypeCode": "(val)","responsibilityId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple People Flow Delegates [PUT /research-sys/api/v1/people-flow-delegates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","delegationTypeCode": "(val)","responsibilityId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","delegationTypeCode": "(val)","responsibilityId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert People Flow Delegates [POST /research-sys/api/v1/people-flow-delegates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","delegationTypeCode": "(val)","responsibilityId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","delegationTypeCode": "(val)","responsibilityId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple People Flow Delegates [POST /research-sys/api/v1/people-flow-delegates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","delegationTypeCode": "(val)","responsibilityId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","delegationTypeCode": "(val)","responsibilityId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","delegationTypeCode": "(val)","responsibilityId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","memberId": "(val)","memberTypeCode": "(val)","actionRequestPolicyCode": "(val)","delegationTypeCode": "(val)","responsibilityId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete People Flow Delegates by Key [DELETE /research-sys/api/v1/people-flow-delegates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All People Flow Delegates [DELETE /research-sys/api/v1/people-flow-delegates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All People Flow Delegates with Matching [DELETE /research-sys/api/v1/people-flow-delegates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + memberId
            + memberTypeCode
            + actionRequestPolicyCode
            + delegationTypeCode
            + responsibilityId

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
