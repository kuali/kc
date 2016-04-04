## Protocol Action Types [/research-sys/api/v1/protocol-action-types/]

### Get Protocol Action Types by Key [GET /research-sys/api/v1/protocol-action-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"}

### Get All Protocol Action Types [GET /research-sys/api/v1/protocol-action-types/]
	 
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

### Get All Protocol Action Types with Filtering [GET /research-sys/api/v1/protocol-action-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + protocolActionTypeCode
            + description
            + triggerSubmission
            + triggerCorrespondence
            + finalActionForBatchCorrespondence
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"},
              {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Action Types [GET /research-sys/api/v1/protocol-action-types/]
	 
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
		
### Get Blueprint API specification for Protocol Action Types [GET /research-sys/api/v1/protocol-action-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Action Types.md"
            transfer-encoding:chunked


### Update Protocol Action Types [PUT /research-sys/api/v1/protocol-action-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Action Types [PUT /research-sys/api/v1/protocol-action-types/]

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

### Insert Protocol Action Types [POST /research-sys/api/v1/protocol-action-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolActionTypeCode": "(val)","description": "(val)","triggerSubmission": "(val)","triggerCorrespondence": "(val)","finalActionForBatchCorrespondence": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Action Types [POST /research-sys/api/v1/protocol-action-types/]

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
            
### Delete Protocol Action Types by Key [DELETE /research-sys/api/v1/protocol-action-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Action Types [DELETE /research-sys/api/v1/protocol-action-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Protocol Action Types with Matching [DELETE /research-sys/api/v1/protocol-action-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + protocolActionTypeCode
            + description
            + triggerSubmission
            + triggerCorrespondence
            + finalActionForBatchCorrespondence


+ Response 204
