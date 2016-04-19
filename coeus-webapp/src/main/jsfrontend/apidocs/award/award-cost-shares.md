## Award Cost Shares [/award/api/v1/award-cost-shares/]

### Get Award Cost Shares by Key [GET /award/api/v1/award-cost-shares/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardCostShareId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","source": "(val)","destination": "(val)","commitmentAmount": "(val)","costShareMet": "(val)","verificationDate": "(val)","_primaryKey": "(val)"}

### Get All Award Cost Shares [GET /award/api/v1/award-cost-shares/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardCostShareId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","source": "(val)","destination": "(val)","commitmentAmount": "(val)","costShareMet": "(val)","verificationDate": "(val)","_primaryKey": "(val)"},
              {"awardCostShareId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","source": "(val)","destination": "(val)","commitmentAmount": "(val)","costShareMet": "(val)","verificationDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Cost Shares with Filtering [GET /award/api/v1/award-cost-shares/]
    
+ Parameters

    + awardCostShareId (optional) - Award Cost Share ID. Maximum length is 8.
    + awardId (optional) - 
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + projectPeriod (optional) - Project Period. Maximum length is 4.
    + costSharePercentage (optional) - Cost Share Percentage. Maximum length is 10.
    + costShareTypeCode (optional) - Cost Share Type Code. Maximum length is 3.
    + source (optional) - Source. Maximum length is 32.
    + destination (optional) - Destination. Maximum length is 32.
    + commitmentAmount (optional) - Commitment Amount. Maximum length is 12.
    + costShareMet (optional) - Cost Share Met. Maximum length is 12.
    + verificationDate (optional) - Verification Date. Maximum length is 21.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardCostShareId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","source": "(val)","destination": "(val)","commitmentAmount": "(val)","costShareMet": "(val)","verificationDate": "(val)","_primaryKey": "(val)"},
              {"awardCostShareId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","source": "(val)","destination": "(val)","commitmentAmount": "(val)","costShareMet": "(val)","verificationDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Cost Shares [GET /award/api/v1/award-cost-shares/]
	                                          
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
    
            {"columns":["awardCostShareId","awardId","awardNumber","sequenceNumber","projectPeriod","costSharePercentage","costShareTypeCode","source","destination","commitmentAmount","costShareMet","verificationDate"],"primaryKey":"awardCostShareId"}
		
### Get Blueprint API specification for Award Cost Shares [GET /award/api/v1/award-cost-shares/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Cost Shares.md"
            transfer-encoding:chunked


### Update Award Cost Shares [PUT /award/api/v1/award-cost-shares/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardCostShareId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","source": "(val)","destination": "(val)","commitmentAmount": "(val)","costShareMet": "(val)","verificationDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Cost Shares [PUT /award/api/v1/award-cost-shares/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardCostShareId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","source": "(val)","destination": "(val)","commitmentAmount": "(val)","costShareMet": "(val)","verificationDate": "(val)","_primaryKey": "(val)"},
              {"awardCostShareId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","source": "(val)","destination": "(val)","commitmentAmount": "(val)","costShareMet": "(val)","verificationDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Cost Shares [POST /award/api/v1/award-cost-shares/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardCostShareId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","source": "(val)","destination": "(val)","commitmentAmount": "(val)","costShareMet": "(val)","verificationDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardCostShareId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","source": "(val)","destination": "(val)","commitmentAmount": "(val)","costShareMet": "(val)","verificationDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Cost Shares [POST /award/api/v1/award-cost-shares/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardCostShareId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","source": "(val)","destination": "(val)","commitmentAmount": "(val)","costShareMet": "(val)","verificationDate": "(val)","_primaryKey": "(val)"},
              {"awardCostShareId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","source": "(val)","destination": "(val)","commitmentAmount": "(val)","costShareMet": "(val)","verificationDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardCostShareId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","source": "(val)","destination": "(val)","commitmentAmount": "(val)","costShareMet": "(val)","verificationDate": "(val)","_primaryKey": "(val)"},
              {"awardCostShareId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","projectPeriod": "(val)","costSharePercentage": "(val)","costShareTypeCode": "(val)","source": "(val)","destination": "(val)","commitmentAmount": "(val)","costShareMet": "(val)","verificationDate": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Cost Shares by Key [DELETE /award/api/v1/award-cost-shares/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Cost Shares [DELETE /award/api/v1/award-cost-shares/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Cost Shares with Matching [DELETE /award/api/v1/award-cost-shares/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardCostShareId (optional) - Award Cost Share ID. Maximum length is 8.
    + awardId (optional) - 
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + projectPeriod (optional) - Project Period. Maximum length is 4.
    + costSharePercentage (optional) - Cost Share Percentage. Maximum length is 10.
    + costShareTypeCode (optional) - Cost Share Type Code. Maximum length is 3.
    + source (optional) - Source. Maximum length is 32.
    + destination (optional) - Destination. Maximum length is 32.
    + commitmentAmount (optional) - Commitment Amount. Maximum length is 12.
    + costShareMet (optional) - Cost Share Met. Maximum length is 12.
    + verificationDate (optional) - Verification Date. Maximum length is 21.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
