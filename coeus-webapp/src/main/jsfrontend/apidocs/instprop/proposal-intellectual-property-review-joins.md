## Proposal Intellectual Property Review Joins [/research-sys/api/v1/proposal-intellectual-property-review-joins/]

### Get Proposal Intellectual Property Review Joins by Key [GET /research-sys/api/v1/proposal-intellectual-property-review-joins/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"}

### Get All Proposal Intellectual Property Review Joins [GET /research-sys/api/v1/proposal-intellectual-property-review-joins/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"},
              {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Intellectual Property Review Joins with Filtering [GET /research-sys/api/v1/proposal-intellectual-property-review-joins/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + proposalIpReviewJoinId
            + proposalId
            + ipReviewId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"},
              {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Intellectual Property Review Joins [GET /research-sys/api/v1/proposal-intellectual-property-review-joins/]
	 
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
		
### Get Blueprint API specification for Proposal Intellectual Property Review Joins [GET /research-sys/api/v1/proposal-intellectual-property-review-joins/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Intellectual Property Review Joins.md"
            transfer-encoding:chunked


### Update Proposal Intellectual Property Review Joins [PUT /research-sys/api/v1/proposal-intellectual-property-review-joins/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Intellectual Property Review Joins [PUT /research-sys/api/v1/proposal-intellectual-property-review-joins/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"},
              {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Intellectual Property Review Joins [POST /research-sys/api/v1/proposal-intellectual-property-review-joins/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Intellectual Property Review Joins [POST /research-sys/api/v1/proposal-intellectual-property-review-joins/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"},
              {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"},
              {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Intellectual Property Review Joins by Key [DELETE /research-sys/api/v1/proposal-intellectual-property-review-joins/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Intellectual Property Review Joins [DELETE /research-sys/api/v1/proposal-intellectual-property-review-joins/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Intellectual Property Review Joins with Matching [DELETE /research-sys/api/v1/proposal-intellectual-property-review-joins/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + proposalIpReviewJoinId
            + proposalId
            + ipReviewId


+ Response 204
