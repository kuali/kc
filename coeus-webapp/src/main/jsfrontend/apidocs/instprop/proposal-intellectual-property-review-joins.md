## Proposal Intellectual Property Review Joins [/instprop/api/v1/proposal-intellectual-property-review-joins/]

### Get Proposal Intellectual Property Review Joins by Key [GET /instprop/api/v1/proposal-intellectual-property-review-joins/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"}

### Get All Proposal Intellectual Property Review Joins [GET /instprop/api/v1/proposal-intellectual-property-review-joins/]
	 
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

### Get All Proposal Intellectual Property Review Joins with Filtering [GET /instprop/api/v1/proposal-intellectual-property-review-joins/]
    
+ Parameters

    + proposalIpReviewJoinId (optional) - 
    + proposalId (optional) - 
    + ipReviewId (optional) - 

            
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
			
### Get Schema for Proposal Intellectual Property Review Joins [GET /instprop/api/v1/proposal-intellectual-property-review-joins/]
	                                          
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
    
            {"columns":["proposalIpReviewJoinId","proposalId","ipReviewId"],"primaryKey":"proposalIpReviewJoinId"}
		
### Get Blueprint API specification for Proposal Intellectual Property Review Joins [GET /instprop/api/v1/proposal-intellectual-property-review-joins/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Intellectual Property Review Joins.md"
            transfer-encoding:chunked
### Update Proposal Intellectual Property Review Joins [PUT /instprop/api/v1/proposal-intellectual-property-review-joins/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Intellectual Property Review Joins [PUT /instprop/api/v1/proposal-intellectual-property-review-joins/]

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
### Insert Proposal Intellectual Property Review Joins [POST /instprop/api/v1/proposal-intellectual-property-review-joins/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalIpReviewJoinId": "(val)","proposalId": "(val)","ipReviewId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Intellectual Property Review Joins [POST /instprop/api/v1/proposal-intellectual-property-review-joins/]

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
### Delete Proposal Intellectual Property Review Joins by Key [DELETE /instprop/api/v1/proposal-intellectual-property-review-joins/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Intellectual Property Review Joins [DELETE /instprop/api/v1/proposal-intellectual-property-review-joins/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Intellectual Property Review Joins with Matching [DELETE /instprop/api/v1/proposal-intellectual-property-review-joins/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalIpReviewJoinId (optional) - 
    + proposalId (optional) - 
    + ipReviewId (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
