## Rule Delegations [/research-sys/api/v1/rule-delegations/]

### Get Rule Delegations by Key [GET /research-sys/api/v1/rule-delegations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"ruleDelegationId": "(val)","responsibilityId": "(val)","delegateRuleId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Rule Delegations [GET /research-sys/api/v1/rule-delegations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"ruleDelegationId": "(val)","responsibilityId": "(val)","delegateRuleId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"},
              {"ruleDelegationId": "(val)","responsibilityId": "(val)","delegateRuleId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rule Delegations with Filtering [GET /research-sys/api/v1/rule-delegations/]
    
+ Parameters

        + ruleDelegationId
            + responsibilityId
            + delegateRuleId
            + delegationTypeCode

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"ruleDelegationId": "(val)","responsibilityId": "(val)","delegateRuleId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"},
              {"ruleDelegationId": "(val)","responsibilityId": "(val)","delegateRuleId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rule Delegations [GET /research-sys/api/v1/rule-delegations/]
	                                          
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
    
            {"columns":["ruleDelegationId","responsibilityId","delegateRuleId","delegationTypeCode"],"primaryKey":"ruleDelegationId"}
		
### Get Blueprint API specification for Rule Delegations [GET /research-sys/api/v1/rule-delegations/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rule Delegations.md"
            transfer-encoding:chunked


### Update Rule Delegations [PUT /research-sys/api/v1/rule-delegations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ruleDelegationId": "(val)","responsibilityId": "(val)","delegateRuleId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rule Delegations [PUT /research-sys/api/v1/rule-delegations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"ruleDelegationId": "(val)","responsibilityId": "(val)","delegateRuleId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"},
              {"ruleDelegationId": "(val)","responsibilityId": "(val)","delegateRuleId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Rule Delegations [POST /research-sys/api/v1/rule-delegations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"ruleDelegationId": "(val)","responsibilityId": "(val)","delegateRuleId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"ruleDelegationId": "(val)","responsibilityId": "(val)","delegateRuleId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rule Delegations [POST /research-sys/api/v1/rule-delegations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"ruleDelegationId": "(val)","responsibilityId": "(val)","delegateRuleId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"},
              {"ruleDelegationId": "(val)","responsibilityId": "(val)","delegateRuleId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"ruleDelegationId": "(val)","responsibilityId": "(val)","delegateRuleId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"},
              {"ruleDelegationId": "(val)","responsibilityId": "(val)","delegateRuleId": "(val)","delegationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Rule Delegations by Key [DELETE /research-sys/api/v1/rule-delegations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rule Delegations [DELETE /research-sys/api/v1/rule-delegations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rule Delegations with Matching [DELETE /research-sys/api/v1/rule-delegations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + ruleDelegationId
            + responsibilityId
            + delegateRuleId
            + delegationTypeCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
