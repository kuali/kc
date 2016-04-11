## Institutional Proposal Special Reviews [/research-sys/api/v1/institutional-proposal-special-reviews/]

### Get Institutional Proposal Special Reviews by Key [GET /research-sys/api/v1/institutional-proposal-special-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalSpecialReviewId": "(val)","proposalId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Special Reviews [GET /research-sys/api/v1/institutional-proposal-special-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalSpecialReviewId": "(val)","proposalId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"proposalSpecialReviewId": "(val)","proposalId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Special Reviews with Filtering [GET /research-sys/api/v1/institutional-proposal-special-reviews/]
    
+ Parameters

        + proposalSpecialReviewId
            + proposalId
            + specialReviewNumber
            + specialReviewTypeCode
            + approvalTypeCode
            + protocolNumber
            + applicationDate
            + approvalDate
            + expirationDate
            + comments

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalSpecialReviewId": "(val)","proposalId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"proposalSpecialReviewId": "(val)","proposalId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Special Reviews [GET /research-sys/api/v1/institutional-proposal-special-reviews/]
	                                          
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
    
            {"columns":["proposalSpecialReviewId","proposalId","specialReviewNumber","specialReviewTypeCode","approvalTypeCode","protocolNumber","applicationDate","approvalDate","expirationDate","comments"],"primaryKey":"proposalSpecialReviewId"}
		
### Get Blueprint API specification for Institutional Proposal Special Reviews [GET /research-sys/api/v1/institutional-proposal-special-reviews/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Special Reviews.md"
            transfer-encoding:chunked


### Update Institutional Proposal Special Reviews [PUT /research-sys/api/v1/institutional-proposal-special-reviews/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalSpecialReviewId": "(val)","proposalId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposal Special Reviews [PUT /research-sys/api/v1/institutional-proposal-special-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalSpecialReviewId": "(val)","proposalId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"proposalSpecialReviewId": "(val)","proposalId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Institutional Proposal Special Reviews [POST /research-sys/api/v1/institutional-proposal-special-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalSpecialReviewId": "(val)","proposalId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalSpecialReviewId": "(val)","proposalId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposal Special Reviews [POST /research-sys/api/v1/institutional-proposal-special-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalSpecialReviewId": "(val)","proposalId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"proposalSpecialReviewId": "(val)","proposalId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalSpecialReviewId": "(val)","proposalId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"proposalSpecialReviewId": "(val)","proposalId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Institutional Proposal Special Reviews by Key [DELETE /research-sys/api/v1/institutional-proposal-special-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Special Reviews [DELETE /research-sys/api/v1/institutional-proposal-special-reviews/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Special Reviews with Matching [DELETE /research-sys/api/v1/institutional-proposal-special-reviews/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + proposalSpecialReviewId
            + proposalId
            + specialReviewNumber
            + specialReviewTypeCode
            + approvalTypeCode
            + protocolNumber
            + applicationDate
            + approvalDate
            + expirationDate
            + comments

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
