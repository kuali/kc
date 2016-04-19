## Institutional Proposal Cost Shares [/instprop/api/v1/institutional-proposal-cost-shares/]

### Get Institutional Proposal Cost Shares by Key [GET /instprop/api/v1/institutional-proposal-cost-shares/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Cost Shares [GET /instprop/api/v1/institutional-proposal-cost-shares/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"},
              {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Cost Shares with Filtering [GET /instprop/api/v1/institutional-proposal-cost-shares/]
    
+ Parameters

    + proposalCostShareId (optional) - Proposal Cost Share Id. Maximum length is 22.
    + proposalId (optional) - 
    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + projectPeriod (optional) - Project Period. Maximum length is 4.
    + costSharePercentage (optional) - Cost Share Percentage. Maximum length is 10.
    + costShareTypeCode (optional) - Cost Share Type Code. Maximum length is 3.
    + sourceAccount (optional) - Source Account. Maximum length is 32.
    + amount (optional) - Amount. Maximum length is 12.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"},
              {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Cost Shares [GET /instprop/api/v1/institutional-proposal-cost-shares/]
	                                          
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
    
            {"columns":["proposalCostShareId","proposalId","proposalNumber","sequenceNumber","projectPeriod","costSharePercentage","costShareTypeCode","sourceAccount","amount"],"primaryKey":"proposalCostShareId"}
		
### Get Blueprint API specification for Institutional Proposal Cost Shares [GET /instprop/api/v1/institutional-proposal-cost-shares/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Cost Shares.md"
            transfer-encoding:chunked


### Update Institutional Proposal Cost Shares [PUT /instprop/api/v1/institutional-proposal-cost-shares/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposal Cost Shares [PUT /instprop/api/v1/institutional-proposal-cost-shares/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"},
              {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Institutional Proposal Cost Shares [POST /instprop/api/v1/institutional-proposal-cost-shares/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposal Cost Shares [POST /instprop/api/v1/institutional-proposal-cost-shares/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"},
              {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"},
              {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Institutional Proposal Cost Shares by Key [DELETE /instprop/api/v1/institutional-proposal-cost-shares/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Cost Shares [DELETE /instprop/api/v1/institutional-proposal-cost-shares/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Cost Shares with Matching [DELETE /instprop/api/v1/institutional-proposal-cost-shares/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalCostShareId (optional) - Proposal Cost Share Id. Maximum length is 22.
    + proposalId (optional) - 
    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + projectPeriod (optional) - Project Period. Maximum length is 4.
    + costSharePercentage (optional) - Cost Share Percentage. Maximum length is 10.
    + costShareTypeCode (optional) - Cost Share Type Code. Maximum length is 3.
    + sourceAccount (optional) - Source Account. Maximum length is 32.
    + amount (optional) - Amount. Maximum length is 12.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
