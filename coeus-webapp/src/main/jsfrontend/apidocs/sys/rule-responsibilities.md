## Rule Responsibilities [/research-sys/api/v1/rule-responsibilities/]

### Get Rule Responsibilities by Key [GET /research-sys/api/v1/rule-responsibilities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","priority": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}

### Get All Rule Responsibilities [GET /research-sys/api/v1/rule-responsibilities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","priority": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","priority": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rule Responsibilities with Filtering [GET /research-sys/api/v1/rule-responsibilities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + responsibilityId
            + ruleBaseValuesId
            + actionRequestedCd
            + ruleResponsibilityName
            + ruleResponsibilityType
            + priority
            + approvePolicy
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","priority": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","priority": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rule Responsibilities [GET /research-sys/api/v1/rule-responsibilities/]
	 
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
		
### Get Blueprint API specification for Rule Responsibilities [GET /research-sys/api/v1/rule-responsibilities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rule Responsibilities.md"
            transfer-encoding:chunked


### Update Rule Responsibilities [PUT /research-sys/api/v1/rule-responsibilities/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","priority": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rule Responsibilities [PUT /research-sys/api/v1/rule-responsibilities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","priority": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","priority": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Rule Responsibilities [POST /research-sys/api/v1/rule-responsibilities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","priority": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","priority": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rule Responsibilities [POST /research-sys/api/v1/rule-responsibilities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","priority": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","priority": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","priority": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","priority": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Rule Responsibilities by Key [DELETE /research-sys/api/v1/rule-responsibilities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rule Responsibilities [DELETE /research-sys/api/v1/rule-responsibilities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Rule Responsibilities with Matching [DELETE /research-sys/api/v1/rule-responsibilities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + responsibilityId
            + ruleBaseValuesId
            + actionRequestedCd
            + ruleResponsibilityName
            + ruleResponsibilityType
            + priority
            + approvePolicy


+ Response 204
