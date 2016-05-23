## Protocol Submission Types [/irb/api/v1/protocol-submission-types/]

### Get Protocol Submission Types by Key [GET /irb/api/v1/protocol-submission-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Protocol Submission Types [GET /irb/api/v1/protocol-submission-types/]
	 
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

### Get All Protocol Submission Types with Filtering [GET /irb/api/v1/protocol-submission-types/]
    
+ Parameters

    + submissionTypeCode (optional) - Submission Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

            
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
			
### Get Schema for Protocol Submission Types [GET /irb/api/v1/protocol-submission-types/]
	                                          
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
    
            {"columns":["submissionTypeCode","description"],"primaryKey":"submissionTypeCode"}
		
### Get Blueprint API specification for Protocol Submission Types [GET /irb/api/v1/protocol-submission-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Submission Types.md"
            transfer-encoding:chunked
### Update Protocol Submission Types [PUT /irb/api/v1/protocol-submission-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Submission Types [PUT /irb/api/v1/protocol-submission-types/]

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
### Insert Protocol Submission Types [POST /irb/api/v1/protocol-submission-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Submission Types [POST /irb/api/v1/protocol-submission-types/]

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
### Delete Protocol Submission Types by Key [DELETE /irb/api/v1/protocol-submission-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Submission Types [DELETE /irb/api/v1/protocol-submission-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Submission Types with Matching [DELETE /irb/api/v1/protocol-submission-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + submissionTypeCode (optional) - Submission Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
