## Iacuc Protocol Reviewers [/iacuc/api/v1/iacuc-protocol-reviewers/]

### Get Iacuc Protocol Reviewers by Key [GET /iacuc/api/v1/iacuc-protocol-reviewers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Reviewers [GET /iacuc/api/v1/iacuc-protocol-reviewers/]
	 
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

### Get All Iacuc Protocol Reviewers with Filtering [GET /iacuc/api/v1/iacuc-protocol-reviewers/]
    
+ Parameters

    + protocolReviewerId (optional) - 
    + protocolIdFk (optional) - 
    + submissionIdFk (optional) - 
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + rolodexId (optional) - 
    + submissionNumber (optional) - 
    + reviewerTypeCode (optional) - 
    + personId (optional) - 
    + nonEmployeeFlag (optional) - 

            
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
			
### Get Schema for Iacuc Protocol Reviewers [GET /iacuc/api/v1/iacuc-protocol-reviewers/]
	                                          
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
    
            {"columns":["protocolReviewerId","protocolIdFk","submissionIdFk","protocolNumber","sequenceNumber","rolodexId","submissionNumber","reviewerTypeCode","personId","nonEmployeeFlag"],"primaryKey":"protocolReviewerId"}
		
### Get Blueprint API specification for Iacuc Protocol Reviewers [GET /iacuc/api/v1/iacuc-protocol-reviewers/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Reviewers.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Reviewers [PUT /iacuc/api/v1/iacuc-protocol-reviewers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Reviewers [PUT /iacuc/api/v1/iacuc-protocol-reviewers/]

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

### Insert Iacuc Protocol Reviewers [POST /iacuc/api/v1/iacuc-protocol-reviewers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolReviewerId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","rolodexId": "(val)","submissionNumber": "(val)","reviewerTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Reviewers [POST /iacuc/api/v1/iacuc-protocol-reviewers/]

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
            
### Delete Iacuc Protocol Reviewers by Key [DELETE /iacuc/api/v1/iacuc-protocol-reviewers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Reviewers [DELETE /iacuc/api/v1/iacuc-protocol-reviewers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Reviewers with Matching [DELETE /iacuc/api/v1/iacuc-protocol-reviewers/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolReviewerId (optional) - 
    + protocolIdFk (optional) - 
    + submissionIdFk (optional) - 
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + rolodexId (optional) - 
    + submissionNumber (optional) - 
    + reviewerTypeCode (optional) - 
    + personId (optional) - 
    + nonEmployeeFlag (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
