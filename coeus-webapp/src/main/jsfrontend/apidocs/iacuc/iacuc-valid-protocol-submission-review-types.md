## Iacuc Valid Protocol Submission Review Types [/iacuc/api/v1/iacuc-valid-protocol-submission-review-types/]

### Get Iacuc Valid Protocol Submission Review Types by Key [GET /iacuc/api/v1/iacuc-valid-protocol-submission-review-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Valid Protocol Submission Review Types [GET /iacuc/api/v1/iacuc-valid-protocol-submission-review-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"},
              {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Valid Protocol Submission Review Types with Filtering [GET /iacuc/api/v1/iacuc-valid-protocol-submission-review-types/]
    
+ Parameters

    + validProtoSubRevTypeId (optional) - Valid Protocol Submission Review Type Id. Maximum length is 12.
    + submissionTypeCode (optional) - Protocol Submission Type Code. Maximum length is 3.
    + protocolReviewTypeCode (optional) - IACUC Protocol Review Type Code. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"},
              {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Valid Protocol Submission Review Types [GET /iacuc/api/v1/iacuc-valid-protocol-submission-review-types/]
	                                          
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
    
            {"columns":["validProtoSubRevTypeId","submissionTypeCode","protocolReviewTypeCode"],"primaryKey":"validProtoSubRevTypeId"}
		
### Get Blueprint API specification for Iacuc Valid Protocol Submission Review Types [GET /iacuc/api/v1/iacuc-valid-protocol-submission-review-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Valid Protocol Submission Review Types.md"
            transfer-encoding:chunked


### Update Iacuc Valid Protocol Submission Review Types [PUT /iacuc/api/v1/iacuc-valid-protocol-submission-review-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Valid Protocol Submission Review Types [PUT /iacuc/api/v1/iacuc-valid-protocol-submission-review-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"},
              {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Valid Protocol Submission Review Types [POST /iacuc/api/v1/iacuc-valid-protocol-submission-review-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Valid Protocol Submission Review Types [POST /iacuc/api/v1/iacuc-valid-protocol-submission-review-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"},
              {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"},
              {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Valid Protocol Submission Review Types by Key [DELETE /iacuc/api/v1/iacuc-valid-protocol-submission-review-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Valid Protocol Submission Review Types [DELETE /iacuc/api/v1/iacuc-valid-protocol-submission-review-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Valid Protocol Submission Review Types with Matching [DELETE /iacuc/api/v1/iacuc-valid-protocol-submission-review-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + validProtoSubRevTypeId (optional) - Valid Protocol Submission Review Type Id. Maximum length is 12.
    + submissionTypeCode (optional) - Protocol Submission Type Code. Maximum length is 3.
    + protocolReviewTypeCode (optional) - IACUC Protocol Review Type Code. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
