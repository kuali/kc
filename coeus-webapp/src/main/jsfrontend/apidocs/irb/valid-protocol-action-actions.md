## Valid Protocol Action Actions [/irb/api/v1/valid-protocol-action-actions/]

### Get Valid Protocol Action Actions by Key [GET /irb/api/v1/valid-protocol-action-actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"}

### Get All Valid Protocol Action Actions [GET /irb/api/v1/valid-protocol-action-actions/]
	 
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

### Get All Valid Protocol Action Actions with Filtering [GET /irb/api/v1/valid-protocol-action-actions/]
    
+ Parameters

    + validProtocolActionActionId (optional) - Valid Protocol Action Action Id. Maximum length is 12.
    + protocolActionTypeCode (optional) - Protocol Action Type. Maximum length is 3.
    + motionTypeCode (optional) - Committee Motion Type. Maximum length is 3.
    + actionNumber (optional) - Action Number. Maximum length is 3.
    + followupActionCode (optional) - Follow-up Action Type. Maximum length is 3.
    + userPromptFlag (optional) - User Prompt Flag. Maximum length is 1.
    + userPrompt (optional) - Comments. Maximum length is 2000.

            
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
			
### Get Schema for Valid Protocol Action Actions [GET /irb/api/v1/valid-protocol-action-actions/]
	                                          
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
    
            {"columns":["validProtocolActionActionId","protocolActionTypeCode","motionTypeCode","actionNumber","followupActionCode","userPromptFlag","userPrompt"],"primaryKey":"validProtocolActionActionId"}
		
### Get Blueprint API specification for Valid Protocol Action Actions [GET /irb/api/v1/valid-protocol-action-actions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Protocol Action Actions.md"
            transfer-encoding:chunked
### Update Valid Protocol Action Actions [PUT /irb/api/v1/valid-protocol-action-actions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Protocol Action Actions [PUT /irb/api/v1/valid-protocol-action-actions/]

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
### Insert Valid Protocol Action Actions [POST /irb/api/v1/valid-protocol-action-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validProtocolActionActionId": "(val)","protocolActionTypeCode": "(val)","motionTypeCode": "(val)","actionNumber": "(val)","followupActionCode": "(val)","userPromptFlag": "(val)","userPrompt": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Protocol Action Actions [POST /irb/api/v1/valid-protocol-action-actions/]

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
### Delete Valid Protocol Action Actions by Key [DELETE /irb/api/v1/valid-protocol-action-actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Protocol Action Actions [DELETE /irb/api/v1/valid-protocol-action-actions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Protocol Action Actions with Matching [DELETE /irb/api/v1/valid-protocol-action-actions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + validProtocolActionActionId (optional) - Valid Protocol Action Action Id. Maximum length is 12.
    + protocolActionTypeCode (optional) - Protocol Action Type. Maximum length is 3.
    + motionTypeCode (optional) - Committee Motion Type. Maximum length is 3.
    + actionNumber (optional) - Action Number. Maximum length is 3.
    + followupActionCode (optional) - Follow-up Action Type. Maximum length is 3.
    + userPromptFlag (optional) - User Prompt Flag. Maximum length is 1.
    + userPrompt (optional) - Comments. Maximum length is 2000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
