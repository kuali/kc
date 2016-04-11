## Group Rule Responsibilities [/research-sys/api/v1/group-rule-responsibilities/]

### Get Group Rule Responsibilities by Key [GET /research-sys/api/v1/group-rule-responsibilities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","priority": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}

### Get All Group Rule Responsibilities [GET /research-sys/api/v1/group-rule-responsibilities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","priority": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","priority": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}
            ]

### Get All Group Rule Responsibilities with Filtering [GET /research-sys/api/v1/group-rule-responsibilities/]
    
+ Parameters

        + id
            + responsibilityId
            + ruleBaseValuesId
            + priority
            + actionRequestedCd
            + ruleResponsibilityName
            + ruleResponsibilityType
            + approvePolicy

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","priority": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","priority": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Group Rule Responsibilities [GET /research-sys/api/v1/group-rule-responsibilities/]
	                                          
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
    
            {"columns":["id","responsibilityId","ruleBaseValuesId","priority","actionRequestedCd","ruleResponsibilityName","ruleResponsibilityType","approvePolicy"],"primaryKey":"id"}
		
### Get Blueprint API specification for Group Rule Responsibilities [GET /research-sys/api/v1/group-rule-responsibilities/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Group Rule Responsibilities.md"
            transfer-encoding:chunked


### Update Group Rule Responsibilities [PUT /research-sys/api/v1/group-rule-responsibilities/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","priority": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Group Rule Responsibilities [PUT /research-sys/api/v1/group-rule-responsibilities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","priority": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","priority": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Group Rule Responsibilities [POST /research-sys/api/v1/group-rule-responsibilities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","priority": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","priority": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Group Rule Responsibilities [POST /research-sys/api/v1/group-rule-responsibilities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","priority": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","priority": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","priority": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","responsibilityId": "(val)","ruleBaseValuesId": "(val)","priority": "(val)","actionRequestedCd": "(val)","ruleResponsibilityName": "(val)","ruleResponsibilityType": "(val)","approvePolicy": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Group Rule Responsibilities by Key [DELETE /research-sys/api/v1/group-rule-responsibilities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Group Rule Responsibilities [DELETE /research-sys/api/v1/group-rule-responsibilities/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Group Rule Responsibilities with Matching [DELETE /research-sys/api/v1/group-rule-responsibilities/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + responsibilityId
            + ruleBaseValuesId
            + priority
            + actionRequestedCd
            + ruleResponsibilityName
            + ruleResponsibilityType
            + approvePolicy

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
