## Valid Protocol Submission Review Types [/irb/api/v1/valid-protocol-submission-review-types/]

### Get Valid Protocol Submission Review Types by Key [GET /irb/api/v1/valid-protocol-submission-review-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Valid Protocol Submission Review Types [GET /irb/api/v1/valid-protocol-submission-review-types/]
	 
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

### Get All Valid Protocol Submission Review Types with Filtering [GET /irb/api/v1/valid-protocol-submission-review-types/]
    
+ Parameters

    + validProtoSubRevTypeId (optional) - Valid Proto Sub Rev Type Id. Maximum length is 12.
    + submissionTypeCode (optional) - Submission Type Code. Maximum length is 3.
    + protocolReviewTypeCode (optional) - Protocol Review Type Code. Maximum length is 3.

            
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
			
### Get Schema for Valid Protocol Submission Review Types [GET /irb/api/v1/valid-protocol-submission-review-types/]
	                                          
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
		
### Get Blueprint API specification for Valid Protocol Submission Review Types [GET /irb/api/v1/valid-protocol-submission-review-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Protocol Submission Review Types.md"
            transfer-encoding:chunked


### Update Valid Protocol Submission Review Types [PUT /irb/api/v1/valid-protocol-submission-review-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Protocol Submission Review Types [PUT /irb/api/v1/valid-protocol-submission-review-types/]

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

### Insert Valid Protocol Submission Review Types [POST /irb/api/v1/valid-protocol-submission-review-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validProtoSubRevTypeId": "(val)","submissionTypeCode": "(val)","protocolReviewTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Protocol Submission Review Types [POST /irb/api/v1/valid-protocol-submission-review-types/]

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
            
### Delete Valid Protocol Submission Review Types by Key [DELETE /irb/api/v1/valid-protocol-submission-review-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Protocol Submission Review Types [DELETE /irb/api/v1/valid-protocol-submission-review-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Protocol Submission Review Types with Matching [DELETE /irb/api/v1/valid-protocol-submission-review-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + validProtoSubRevTypeId (optional) - Valid Proto Sub Rev Type Id. Maximum length is 12.
    + submissionTypeCode (optional) - Submission Type Code. Maximum length is 3.
    + protocolReviewTypeCode (optional) - Protocol Review Type Code. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
