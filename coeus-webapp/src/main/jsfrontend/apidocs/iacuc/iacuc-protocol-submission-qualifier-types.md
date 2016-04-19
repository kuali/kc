## Iacuc Protocol Submission Qualifier Types [/iacuc/api/v1/iacuc-protocol-submission-qualifier-types/]

### Get Iacuc Protocol Submission Qualifier Types by Key [GET /iacuc/api/v1/iacuc-protocol-submission-qualifier-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Submission Qualifier Types [GET /iacuc/api/v1/iacuc-protocol-submission-qualifier-types/]
	 
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

### Get All Iacuc Protocol Submission Qualifier Types with Filtering [GET /iacuc/api/v1/iacuc-protocol-submission-qualifier-types/]
    
+ Parameters

    + submissionQualifierTypeCode (optional) - Submission Qualifier Type Code. Maximum length is 3.
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
              {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Submission Qualifier Types [GET /iacuc/api/v1/iacuc-protocol-submission-qualifier-types/]
	                                          
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
    
            {"columns":["submissionQualifierTypeCode","description"],"primaryKey":"submissionQualifierTypeCode"}
		
### Get Blueprint API specification for Iacuc Protocol Submission Qualifier Types [GET /iacuc/api/v1/iacuc-protocol-submission-qualifier-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Submission Qualifier Types.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Submission Qualifier Types [PUT /iacuc/api/v1/iacuc-protocol-submission-qualifier-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Submission Qualifier Types [PUT /iacuc/api/v1/iacuc-protocol-submission-qualifier-types/]

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

### Insert Iacuc Protocol Submission Qualifier Types [POST /iacuc/api/v1/iacuc-protocol-submission-qualifier-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"submissionQualifierTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Submission Qualifier Types [POST /iacuc/api/v1/iacuc-protocol-submission-qualifier-types/]

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
            
### Delete Iacuc Protocol Submission Qualifier Types by Key [DELETE /iacuc/api/v1/iacuc-protocol-submission-qualifier-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Submission Qualifier Types [DELETE /iacuc/api/v1/iacuc-protocol-submission-qualifier-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Submission Qualifier Types with Matching [DELETE /iacuc/api/v1/iacuc-protocol-submission-qualifier-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + submissionQualifierTypeCode (optional) - Submission Qualifier Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
