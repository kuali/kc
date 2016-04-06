## Protocol Submission Types [/research-sys/api/v1/protocol-submission-types/]

### Get Protocol Submission Types by Key [GET /research-sys/api/v1/protocol-submission-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Protocol Submission Types [GET /research-sys/api/v1/protocol-submission-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Submission Types with Filtering [GET /research-sys/api/v1/protocol-submission-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + submissionTypeCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Submission Types [GET /research-sys/api/v1/protocol-submission-types/]
	 
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
		
### Get Blueprint API specification for Protocol Submission Types [GET /research-sys/api/v1/protocol-submission-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Submission Types.md"
            transfer-encoding:chunked


### Update Protocol Submission Types [PUT /research-sys/api/v1/protocol-submission-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Submission Types [PUT /research-sys/api/v1/protocol-submission-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Submission Types [POST /research-sys/api/v1/protocol-submission-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Submission Types [POST /research-sys/api/v1/protocol-submission-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Submission Types by Key [DELETE /research-sys/api/v1/protocol-submission-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Submission Types [DELETE /research-sys/api/v1/protocol-submission-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Protocol Submission Types with Matching [DELETE /research-sys/api/v1/protocol-submission-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + submissionTypeCode
            + description


+ Response 204
