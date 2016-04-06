## Iacuc Protocol Submission Statuses [/research-sys/api/v1/iacuc-protocol-submission-statuses/]

### Get Iacuc Protocol Submission Statuses by Key [GET /research-sys/api/v1/iacuc-protocol-submission-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Submission Statuses [GET /research-sys/api/v1/iacuc-protocol-submission-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Submission Statuses with Filtering [GET /research-sys/api/v1/iacuc-protocol-submission-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + protocolSubmissionStatusCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Submission Statuses [GET /research-sys/api/v1/iacuc-protocol-submission-statuses/]
	 
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
		
### Get Blueprint API specification for Iacuc Protocol Submission Statuses [GET /research-sys/api/v1/iacuc-protocol-submission-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Submission Statuses.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Submission Statuses [PUT /research-sys/api/v1/iacuc-protocol-submission-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Submission Statuses [PUT /research-sys/api/v1/iacuc-protocol-submission-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Submission Statuses [POST /research-sys/api/v1/iacuc-protocol-submission-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Submission Statuses [POST /research-sys/api/v1/iacuc-protocol-submission-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Submission Statuses by Key [DELETE /research-sys/api/v1/iacuc-protocol-submission-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Submission Statuses [DELETE /research-sys/api/v1/iacuc-protocol-submission-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol Submission Statuses with Matching [DELETE /research-sys/api/v1/iacuc-protocol-submission-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + protocolSubmissionStatusCode
            + description


+ Response 204
