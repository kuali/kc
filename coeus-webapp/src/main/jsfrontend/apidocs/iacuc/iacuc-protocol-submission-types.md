## Iacuc Protocol Submission Types [/research-sys/api/v1/iacuc-protocol-submission-types/]

### Get Iacuc Protocol Submission Types by Key [GET /research-sys/api/v1/iacuc-protocol-submission-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Submission Types [GET /research-sys/api/v1/iacuc-protocol-submission-types/]
	 
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

### Get All Iacuc Protocol Submission Types with Filtering [GET /research-sys/api/v1/iacuc-protocol-submission-types/]
	 
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
			
### Get Schema for Iacuc Protocol Submission Types [GET /research-sys/api/v1/iacuc-protocol-submission-types/]
	 
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
		
### Get Blueprint API specification for Iacuc Protocol Submission Types [GET /research-sys/api/v1/iacuc-protocol-submission-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Submission Types.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Submission Types [PUT /research-sys/api/v1/iacuc-protocol-submission-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Submission Types [PUT /research-sys/api/v1/iacuc-protocol-submission-types/]

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

### Insert Iacuc Protocol Submission Types [POST /research-sys/api/v1/iacuc-protocol-submission-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Submission Types [POST /research-sys/api/v1/iacuc-protocol-submission-types/]

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
            
### Delete Iacuc Protocol Submission Types by Key [DELETE /research-sys/api/v1/iacuc-protocol-submission-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Submission Types [DELETE /research-sys/api/v1/iacuc-protocol-submission-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol Submission Types with Matching [DELETE /research-sys/api/v1/iacuc-protocol-submission-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + submissionTypeCode
            + description


+ Response 204
