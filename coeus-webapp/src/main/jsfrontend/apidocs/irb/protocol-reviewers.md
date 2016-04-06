## Protocol Reviewers [/research-sys/api/v1/protocol-reviewers/]

### Get Protocol Reviewers by Key [GET /research-sys/api/v1/protocol-reviewers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"}

### Get All Protocol Reviewers [GET /research-sys/api/v1/protocol-reviewers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"},
              {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Reviewers with Filtering [GET /research-sys/api/v1/protocol-reviewers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + protocolReviewerId
            + protocolIdFk
            + submissionIdFk
            + protocolNumber
            + sequenceNumber
            + rolodexId
            + submissionNumber
            + reviewerTypeCode
            + personId
            + nonEmployeeFlag
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"},
              {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Reviewers [GET /research-sys/api/v1/protocol-reviewers/]
	 
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
		
### Get Blueprint API specification for Protocol Reviewers [GET /research-sys/api/v1/protocol-reviewers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Reviewers.md"
            transfer-encoding:chunked


### Update Protocol Reviewers [PUT /research-sys/api/v1/protocol-reviewers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Reviewers [PUT /research-sys/api/v1/protocol-reviewers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"},
              {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Reviewers [POST /research-sys/api/v1/protocol-reviewers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Reviewers [POST /research-sys/api/v1/protocol-reviewers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"},
              {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"},
              {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Reviewers by Key [DELETE /research-sys/api/v1/protocol-reviewers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Reviewers [DELETE /research-sys/api/v1/protocol-reviewers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Protocol Reviewers with Matching [DELETE /research-sys/api/v1/protocol-reviewers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + protocolReviewerId
            + protocolIdFk
            + submissionIdFk
            + protocolNumber
            + sequenceNumber
            + rolodexId
            + submissionNumber
            + reviewerTypeCode
            + personId
            + nonEmployeeFlag


+ Response 204
