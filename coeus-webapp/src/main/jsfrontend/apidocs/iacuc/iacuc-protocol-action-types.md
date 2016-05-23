## Iacuc Protocol Action Types [/iacuc/api/v1/iacuc-protocol-action-types/]

### Get Iacuc Protocol Action Types by Key [GET /iacuc/api/v1/iacuc-protocol-action-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Action Types [GET /iacuc/api/v1/iacuc-protocol-action-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"},
              {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Action Types with Filtering [GET /iacuc/api/v1/iacuc-protocol-action-types/]
    
+ Parameters

    + protocolActionTypeCode (optional) - Protocol Action Type Code. Maximum length is 22.
    + description (optional) - Description. Maximum length is 200.
    + triggerSubmission (optional) - Trigger Submission. Maximum length is 1.
    + triggerCorrespondence (optional) - Trigger Correspondence. Maximum length is 1.
    + finalActionForBatchCorrespondence (optional) - Final Action for Batch Correspondence. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"},
              {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Action Types [GET /iacuc/api/v1/iacuc-protocol-action-types/]
	                                          
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
    
            {"columns":["protocolActionTypeCode","description","triggerSubmission","triggerCorrespondence","finalActionForBatchCorrespondence"],"primaryKey":"protocolActionTypeCode"}
		
### Get Blueprint API specification for Iacuc Protocol Action Types [GET /iacuc/api/v1/iacuc-protocol-action-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Action Types.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Action Types [PUT /iacuc/api/v1/iacuc-protocol-action-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Action Types [PUT /iacuc/api/v1/iacuc-protocol-action-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"},
              {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Protocol Action Types [POST /iacuc/api/v1/iacuc-protocol-action-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Action Types [POST /iacuc/api/v1/iacuc-protocol-action-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"},
              {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"},
              {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Protocol Action Types by Key [DELETE /iacuc/api/v1/iacuc-protocol-action-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Action Types [DELETE /iacuc/api/v1/iacuc-protocol-action-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Action Types with Matching [DELETE /iacuc/api/v1/iacuc-protocol-action-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolActionTypeCode (optional) - Protocol Action Type Code. Maximum length is 22.
    + description (optional) - Description. Maximum length is 200.
    + triggerSubmission (optional) - Trigger Submission. Maximum length is 1.
    + triggerCorrespondence (optional) - Trigger Correspondence. Maximum length is 1.
    + finalActionForBatchCorrespondence (optional) - Final Action for Batch Correspondence. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
