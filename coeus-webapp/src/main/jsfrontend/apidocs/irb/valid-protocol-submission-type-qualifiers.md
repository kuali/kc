## Valid Protocol Submission Type Qualifiers [/irb/api/v1/valid-protocol-submission-type-qualifiers/]

### Get Valid Protocol Submission Type Qualifiers by Key [GET /irb/api/v1/valid-protocol-submission-type-qualifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}

### Get All Valid Protocol Submission Type Qualifiers [GET /irb/api/v1/valid-protocol-submission-type-qualifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"},
              {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Valid Protocol Submission Type Qualifiers with Filtering [GET /irb/api/v1/valid-protocol-submission-type-qualifiers/]
    
+ Parameters

    + validProtoSubTypeQualId (optional) - Valid Proto Sub Type Qual Id. Maximum length is 12.
    + submissionTypeCode (optional) - Submission Type Code. Maximum length is 3.
    + submissionTypeQualCode (optional) - Submission Type Qual Code. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"},
              {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Valid Protocol Submission Type Qualifiers [GET /irb/api/v1/valid-protocol-submission-type-qualifiers/]
	                                          
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
    
            {"columns":["validProtoSubTypeQualId","submissionTypeCode","submissionTypeQualCode"],"primaryKey":"validProtoSubTypeQualId"}
		
### Get Blueprint API specification for Valid Protocol Submission Type Qualifiers [GET /irb/api/v1/valid-protocol-submission-type-qualifiers/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Protocol Submission Type Qualifiers.md"
            transfer-encoding:chunked
### Update Valid Protocol Submission Type Qualifiers [PUT /irb/api/v1/valid-protocol-submission-type-qualifiers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Protocol Submission Type Qualifiers [PUT /irb/api/v1/valid-protocol-submission-type-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"},
              {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Valid Protocol Submission Type Qualifiers [POST /irb/api/v1/valid-protocol-submission-type-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Protocol Submission Type Qualifiers [POST /irb/api/v1/valid-protocol-submission-type-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"},
              {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"},
              {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Valid Protocol Submission Type Qualifiers by Key [DELETE /irb/api/v1/valid-protocol-submission-type-qualifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Protocol Submission Type Qualifiers [DELETE /irb/api/v1/valid-protocol-submission-type-qualifiers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Protocol Submission Type Qualifiers with Matching [DELETE /irb/api/v1/valid-protocol-submission-type-qualifiers/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + validProtoSubTypeQualId (optional) - Valid Proto Sub Type Qual Id. Maximum length is 12.
    + submissionTypeCode (optional) - Submission Type Code. Maximum length is 3.
    + submissionTypeQualCode (optional) - Submission Type Qual Code. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
