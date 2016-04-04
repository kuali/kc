## Proposal Comments [/research-sys/api/v1/proposal-comments/]

### Get Proposal Comments by Key [GET /research-sys/api/v1/proposal-comments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Proposal Comments [GET /research-sys/api/v1/proposal-comments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Comments with Filtering [GET /research-sys/api/v1/proposal-comments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + proposalCommentsId
            + proposalId
            + proposalNumber
            + sequenceNumber
            + commentTypeCode
            + comments
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Comments [GET /research-sys/api/v1/proposal-comments/]
	 
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
		
### Get Blueprint API specification for Proposal Comments [GET /research-sys/api/v1/proposal-comments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Comments.md"
            transfer-encoding:chunked


### Update Proposal Comments [PUT /research-sys/api/v1/proposal-comments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Comments [PUT /research-sys/api/v1/proposal-comments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Comments [POST /research-sys/api/v1/proposal-comments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Comments [POST /research-sys/api/v1/proposal-comments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Comments by Key [DELETE /research-sys/api/v1/proposal-comments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Comments [DELETE /research-sys/api/v1/proposal-comments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Comments with Matching [DELETE /research-sys/api/v1/proposal-comments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + proposalCommentsId
            + proposalId
            + proposalNumber
            + sequenceNumber
            + commentTypeCode
            + comments


+ Response 204
