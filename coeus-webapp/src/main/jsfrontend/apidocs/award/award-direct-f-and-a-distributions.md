## Award Direct F And A Distributions [/research-sys/api/v1/award-direct-f-and-a-distributions/]

### Get Award Direct F And A Distributions by Key [GET /research-sys/api/v1/award-direct-f-and-a-distributions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardDirectFandADistributionId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","_primaryKey": "(val)"}

### Get All Award Direct F And A Distributions [GET /research-sys/api/v1/award-direct-f-and-a-distributions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardDirectFandADistributionId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","_primaryKey": "(val)"},
              {"awardDirectFandADistributionId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Direct F And A Distributions with Filtering [GET /research-sys/api/v1/award-direct-f-and-a-distributions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardDirectFandADistributionId
            + awardId
            + awardNumber
            + sequenceNumber
            + amountSequenceNumber
            + awardAmountInfoId
            + budgetPeriod
            + startDate
            + endDate
            + directCost
            + indirectCost
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardDirectFandADistributionId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","_primaryKey": "(val)"},
              {"awardDirectFandADistributionId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Direct F And A Distributions [GET /research-sys/api/v1/award-direct-f-and-a-distributions/]
	 
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
		
### Get Blueprint API specification for Award Direct F And A Distributions [GET /research-sys/api/v1/award-direct-f-and-a-distributions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Direct F And A Distributions.md"
            transfer-encoding:chunked


### Update Award Direct F And A Distributions [PUT /research-sys/api/v1/award-direct-f-and-a-distributions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardDirectFandADistributionId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Direct F And A Distributions [PUT /research-sys/api/v1/award-direct-f-and-a-distributions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardDirectFandADistributionId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","_primaryKey": "(val)"},
              {"awardDirectFandADistributionId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Direct F And A Distributions [POST /research-sys/api/v1/award-direct-f-and-a-distributions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardDirectFandADistributionId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardDirectFandADistributionId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Direct F And A Distributions [POST /research-sys/api/v1/award-direct-f-and-a-distributions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardDirectFandADistributionId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","_primaryKey": "(val)"},
              {"awardDirectFandADistributionId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardDirectFandADistributionId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","_primaryKey": "(val)"},
              {"awardDirectFandADistributionId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Direct F And A Distributions by Key [DELETE /research-sys/api/v1/award-direct-f-and-a-distributions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Direct F And A Distributions [DELETE /research-sys/api/v1/award-direct-f-and-a-distributions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Direct F And A Distributions with Matching [DELETE /research-sys/api/v1/award-direct-f-and-a-distributions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardDirectFandADistributionId
            + awardId
            + awardNumber
            + sequenceNumber
            + amountSequenceNumber
            + awardAmountInfoId
            + budgetPeriod
            + startDate
            + endDate
            + directCost
            + indirectCost


+ Response 204
