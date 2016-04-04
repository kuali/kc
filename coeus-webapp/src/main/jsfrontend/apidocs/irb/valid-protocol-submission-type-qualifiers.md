## Valid Protocol Submission Type Qualifiers [/research-sys/api/v1/valid-protocol-submission-type-qualifiers/]

### Get Valid Protocol Submission Type Qualifiers by Key [GET /research-sys/api/v1/valid-protocol-submission-type-qualifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}

### Get All Valid Protocol Submission Type Qualifiers [GET /research-sys/api/v1/valid-protocol-submission-type-qualifiers/]
	 
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

### Get All Valid Protocol Submission Type Qualifiers with Filtering [GET /research-sys/api/v1/valid-protocol-submission-type-qualifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + validProtoSubTypeQualId
            + submissionTypeCode
            + submissionTypeQualCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"},
              {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Valid Protocol Submission Type Qualifiers [GET /research-sys/api/v1/valid-protocol-submission-type-qualifiers/]
	 
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
		
### Get Blueprint API specification for Valid Protocol Submission Type Qualifiers [GET /research-sys/api/v1/valid-protocol-submission-type-qualifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Protocol Submission Type Qualifiers.md"
            transfer-encoding:chunked


### Update Valid Protocol Submission Type Qualifiers [PUT /research-sys/api/v1/valid-protocol-submission-type-qualifiers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Protocol Submission Type Qualifiers [PUT /research-sys/api/v1/valid-protocol-submission-type-qualifiers/]

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

### Insert Valid Protocol Submission Type Qualifiers [POST /research-sys/api/v1/valid-protocol-submission-type-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validProtoSubTypeQualId": "(val)","submissionTypeCode": "(val)","submissionTypeQualCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Protocol Submission Type Qualifiers [POST /research-sys/api/v1/valid-protocol-submission-type-qualifiers/]

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
            
### Delete Valid Protocol Submission Type Qualifiers by Key [DELETE /research-sys/api/v1/valid-protocol-submission-type-qualifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Protocol Submission Type Qualifiers [DELETE /research-sys/api/v1/valid-protocol-submission-type-qualifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Valid Protocol Submission Type Qualifiers with Matching [DELETE /research-sys/api/v1/valid-protocol-submission-type-qualifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + validProtoSubTypeQualId
            + submissionTypeCode
            + submissionTypeQualCode


+ Response 204
