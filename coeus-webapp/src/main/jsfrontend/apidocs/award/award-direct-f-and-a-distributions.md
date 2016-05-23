## Award Direct F And A Distributions [/award/api/v1/award-direct-f-and-a-distributions/]

### Get Award Direct F And A Distributions by Key [GET /award/api/v1/award-direct-f-and-a-distributions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardDirectFandADistributionId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}

### Get All Award Direct F And A Distributions [GET /award/api/v1/award-direct-f-and-a-distributions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardDirectFandADistributionId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","award.awardId": "(val)","_primaryKey": "(val)"},
              {"awardDirectFandADistributionId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Direct F And A Distributions with Filtering [GET /award/api/v1/award-direct-f-and-a-distributions/]
    
+ Parameters

    + awardDirectFandADistributionId (optional) - Award Direct F and A Distribution ID. Maximum length is 8.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + amountSequenceNumber (optional) - Amount Sequence Number. Maximum length is 4.
    + awardAmountInfoId (optional) - 
    + budgetPeriod (optional) - Budget Period. Maximum length is 3.
    + startDate (optional) - Start Date. Maximum length is 21.
    + endDate (optional) - End Date. Maximum length is 21.
    + directCost (optional) - Direct Cost. Maximum length is 12.
    + indirectCost (optional) - F&A Cost. Maximum length is 12.
    + award.awardId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardDirectFandADistributionId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","award.awardId": "(val)","_primaryKey": "(val)"},
              {"awardDirectFandADistributionId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","amountSequenceNumber": "(val)","awardAmountInfoId": "(val)","budgetPeriod": "(val)","startDate": "(val)","endDate": "(val)","directCost": "(val)","indirectCost": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Direct F And A Distributions [GET /award/api/v1/award-direct-f-and-a-distributions/]
	                                          
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
    
            {"columns":["awardDirectFandADistributionId","awardNumber","sequenceNumber","amountSequenceNumber","awardAmountInfoId","budgetPeriod","startDate","endDate","directCost","indirectCost","award.awardId"],"primaryKey":"awardDirectFandADistributionId"}
		
### Get Blueprint API specification for Award Direct F And A Distributions [GET /award/api/v1/award-direct-f-and-a-distributions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Direct F And A Distributions.md"
            transfer-encoding:chunked
