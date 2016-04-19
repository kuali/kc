## Iacuc Protocol Submission Types [/iacuc/api/v1/iacuc-protocol-submission-types/]

### Get Iacuc Protocol Submission Types by Key [GET /iacuc/api/v1/iacuc-protocol-submission-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Submission Types [GET /iacuc/api/v1/iacuc-protocol-submission-types/]
	 
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

### Get All Iacuc Protocol Submission Types with Filtering [GET /iacuc/api/v1/iacuc-protocol-submission-types/]
    
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
			
### Get Schema for Iacuc Protocol Submission Types [GET /iacuc/api/v1/iacuc-protocol-submission-types/]
	                                          
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
		
### Get Blueprint API specification for Iacuc Protocol Submission Types [GET /iacuc/api/v1/iacuc-protocol-submission-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Submission Types.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Submission Types [PUT /iacuc/api/v1/iacuc-protocol-submission-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Submission Types [PUT /iacuc/api/v1/iacuc-protocol-submission-types/]

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

### Insert Iacuc Protocol Submission Types [POST /iacuc/api/v1/iacuc-protocol-submission-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"submissionTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Submission Types [POST /iacuc/api/v1/iacuc-protocol-submission-types/]

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
            
### Delete Iacuc Protocol Submission Types by Key [DELETE /iacuc/api/v1/iacuc-protocol-submission-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Submission Types [DELETE /iacuc/api/v1/iacuc-protocol-submission-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Submission Types with Matching [DELETE /iacuc/api/v1/iacuc-protocol-submission-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + submissionTypeCode (optional) - Submission Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
