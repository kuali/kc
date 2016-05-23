## Iacuc Valid Protocol Submission Type Qualifiers [/iacuc/api/v1/iacuc-valid-protocol-submission-type-qualifiers/]

### Get Iacuc Valid Protocol Submission Type Qualifiers by Key [GET /iacuc/api/v1/iacuc-valid-protocol-submission-type-qualifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Valid Protocol Submission Type Qualifiers [GET /iacuc/api/v1/iacuc-valid-protocol-submission-type-qualifiers/]
	 
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

### Get All Iacuc Valid Protocol Submission Type Qualifiers with Filtering [GET /iacuc/api/v1/iacuc-valid-protocol-submission-type-qualifiers/]
    
+ Parameters

    + validProtoSubTypeQualId (optional) - Valid Protocol Submission Type Qualifier Id. Maximum length is 12.
    + submissionTypeCode (optional) - Protocol Submission Type Code. Maximum length is 3.
    + submissionTypeQualCode (optional) - IACUC Submission Type Qual Code. Maximum length is 3.

            
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
			
### Get Schema for Iacuc Valid Protocol Submission Type Qualifiers [GET /iacuc/api/v1/iacuc-valid-protocol-submission-type-qualifiers/]
	                                          
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
		
### Get Blueprint API specification for Iacuc Valid Protocol Submission Type Qualifiers [GET /iacuc/api/v1/iacuc-valid-protocol-submission-type-qualifiers/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Valid Protocol Submission Type Qualifiers.md"
            transfer-encoding:chunked
### Update Iacuc Valid Protocol Submission Type Qualifiers [PUT /iacuc/api/v1/iacuc-valid-protocol-submission-type-qualifiers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Valid Protocol Submission Type Qualifiers [PUT /iacuc/api/v1/iacuc-valid-protocol-submission-type-qualifiers/]

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
### Insert Iacuc Valid Protocol Submission Type Qualifiers [POST /iacuc/api/v1/iacuc-valid-protocol-submission-type-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Valid Protocol Submission Type Qualifiers [POST /iacuc/api/v1/iacuc-valid-protocol-submission-type-qualifiers/]

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
### Delete Iacuc Valid Protocol Submission Type Qualifiers by Key [DELETE /iacuc/api/v1/iacuc-valid-protocol-submission-type-qualifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Valid Protocol Submission Type Qualifiers [DELETE /iacuc/api/v1/iacuc-valid-protocol-submission-type-qualifiers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Valid Protocol Submission Type Qualifiers with Matching [DELETE /iacuc/api/v1/iacuc-valid-protocol-submission-type-qualifiers/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + validProtoSubTypeQualId (optional) - Valid Protocol Submission Type Qualifier Id. Maximum length is 12.
    + submissionTypeCode (optional) - Protocol Submission Type Code. Maximum length is 3.
    + submissionTypeQualCode (optional) - IACUC Submission Type Qual Code. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
