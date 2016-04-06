## Institutional Proposal Cost Shares [/research-sys/api/v1/institutional-proposal-cost-shares/]

### Get Institutional Proposal Cost Shares by Key [GET /research-sys/api/v1/institutional-proposal-cost-shares/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Cost Shares [GET /research-sys/api/v1/institutional-proposal-cost-shares/]
	 
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

### Get All Institutional Proposal Cost Shares with Filtering [GET /research-sys/api/v1/institutional-proposal-cost-shares/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + proposalCostShareId
            + proposalId
            + proposalNumber
            + sequenceNumber
            + projectPeriod
            + costSharePercentage
            + costShareTypeCode
            + sourceAccount
            + amount
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"},
              {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Cost Shares [GET /research-sys/api/v1/institutional-proposal-cost-shares/]
	 
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
		
### Get Blueprint API specification for Institutional Proposal Cost Shares [GET /research-sys/api/v1/institutional-proposal-cost-shares/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Cost Shares.md"
            transfer-encoding:chunked


### Update Institutional Proposal Cost Shares [PUT /research-sys/api/v1/institutional-proposal-cost-shares/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposal Cost Shares [PUT /research-sys/api/v1/institutional-proposal-cost-shares/]

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

### Insert Institutional Proposal Cost Shares [POST /research-sys/api/v1/institutional-proposal-cost-shares/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalCostShareId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","sourceAccount": "(val)","amount": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposal Cost Shares [POST /research-sys/api/v1/institutional-proposal-cost-shares/]

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
            
### Delete Institutional Proposal Cost Shares by Key [DELETE /research-sys/api/v1/institutional-proposal-cost-shares/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Cost Shares [DELETE /research-sys/api/v1/institutional-proposal-cost-shares/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Institutional Proposal Cost Shares with Matching [DELETE /research-sys/api/v1/institutional-proposal-cost-shares/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + proposalCostShareId
            + proposalId
            + proposalNumber
            + sequenceNumber
            + projectPeriod
            + costSharePercentage
            + costShareTypeCode
            + sourceAccount
            + amount


+ Response 204
