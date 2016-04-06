## Award Funding Proposals [/research-sys/api/v1/award-funding-proposals/]

### Get Award Funding Proposals by Key [GET /research-sys/api/v1/award-funding-proposals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Award Funding Proposals [GET /research-sys/api/v1/award-funding-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Funding Proposals with Filtering [GET /research-sys/api/v1/award-funding-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardFundingProposalId
            + awardId
            + proposalId
            + active
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Funding Proposals [GET /research-sys/api/v1/award-funding-proposals/]
	 
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
		
### Get Blueprint API specification for Award Funding Proposals [GET /research-sys/api/v1/award-funding-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Funding Proposals.md"
            transfer-encoding:chunked


### Update Award Funding Proposals [PUT /research-sys/api/v1/award-funding-proposals/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Funding Proposals [PUT /research-sys/api/v1/award-funding-proposals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Funding Proposals [POST /research-sys/api/v1/award-funding-proposals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Funding Proposals [POST /research-sys/api/v1/award-funding-proposals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"awardFundingProposalId": "(val)","awardId": "(val)","proposalId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Funding Proposals by Key [DELETE /research-sys/api/v1/award-funding-proposals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Funding Proposals [DELETE /research-sys/api/v1/award-funding-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Funding Proposals with Matching [DELETE /research-sys/api/v1/award-funding-proposals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardFundingProposalId
            + awardId
            + proposalId
            + active


+ Response 204
