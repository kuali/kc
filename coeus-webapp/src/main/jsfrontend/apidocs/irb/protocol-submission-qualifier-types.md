## Protocol Submission Qualifier Types [/research-sys/api/v1/protocol-submission-qualifier-types/]

### Get Protocol Submission Qualifier Types by Key [GET /research-sys/api/v1/protocol-submission-qualifier-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Protocol Submission Qualifier Types [GET /research-sys/api/v1/protocol-submission-qualifier-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Submission Qualifier Types with Filtering [GET /research-sys/api/v1/protocol-submission-qualifier-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + submissionQualifierTypeCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Submission Qualifier Types [GET /research-sys/api/v1/protocol-submission-qualifier-types/]
	 
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
		
### Get Blueprint API specification for Protocol Submission Qualifier Types [GET /research-sys/api/v1/protocol-submission-qualifier-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Submission Qualifier Types.md"
            transfer-encoding:chunked


### Update Protocol Submission Qualifier Types [PUT /research-sys/api/v1/protocol-submission-qualifier-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Submission Qualifier Types [PUT /research-sys/api/v1/protocol-submission-qualifier-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Submission Qualifier Types [POST /research-sys/api/v1/protocol-submission-qualifier-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Submission Qualifier Types [POST /research-sys/api/v1/protocol-submission-qualifier-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Submission Qualifier Types by Key [DELETE /research-sys/api/v1/protocol-submission-qualifier-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Submission Qualifier Types [DELETE /research-sys/api/v1/protocol-submission-qualifier-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Protocol Submission Qualifier Types with Matching [DELETE /research-sys/api/v1/protocol-submission-qualifier-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + submissionQualifierTypeCode
            + description


+ Response 204
