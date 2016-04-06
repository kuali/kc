## Iacuc Valid Protocol Action Actions [/research-sys/api/v1/iacuc-valid-protocol-action-actions/]

### Get Iacuc Valid Protocol Action Actions by Key [GET /research-sys/api/v1/iacuc-valid-protocol-action-actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Valid Protocol Action Actions [GET /research-sys/api/v1/iacuc-valid-protocol-action-actions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"},
              {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Valid Protocol Action Actions with Filtering [GET /research-sys/api/v1/iacuc-valid-protocol-action-actions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + validProtocolActionActionId
            + protocolActionTypeCode
            + motionTypeCode
            + actionNumber
            + followupActionCode
            + userPromptFlag
            + userPrompt
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"},
              {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Valid Protocol Action Actions [GET /research-sys/api/v1/iacuc-valid-protocol-action-actions/]
	 
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
		
### Get Blueprint API specification for Iacuc Valid Protocol Action Actions [GET /research-sys/api/v1/iacuc-valid-protocol-action-actions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Valid Protocol Action Actions.md"
            transfer-encoding:chunked


### Update Iacuc Valid Protocol Action Actions [PUT /research-sys/api/v1/iacuc-valid-protocol-action-actions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Valid Protocol Action Actions [PUT /research-sys/api/v1/iacuc-valid-protocol-action-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"},
              {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Valid Protocol Action Actions [POST /research-sys/api/v1/iacuc-valid-protocol-action-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Valid Protocol Action Actions [POST /research-sys/api/v1/iacuc-valid-protocol-action-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"},
              {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"},
              {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Valid Protocol Action Actions by Key [DELETE /research-sys/api/v1/iacuc-valid-protocol-action-actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Valid Protocol Action Actions [DELETE /research-sys/api/v1/iacuc-valid-protocol-action-actions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Valid Protocol Action Actions with Matching [DELETE /research-sys/api/v1/iacuc-valid-protocol-action-actions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + validProtocolActionActionId
            + protocolActionTypeCode
            + motionTypeCode
            + actionNumber
            + followupActionCode
            + userPromptFlag
            + userPrompt


+ Response 204
