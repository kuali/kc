## Protocol Online Reviews [/irb/api/v1/protocol-online-reviews/]

### Get Protocol Online Reviews by Key [GET /irb/api/v1/protocol-online-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","_primaryKey": "(val)"}

### Get All Protocol Online Reviews [GET /irb/api/v1/protocol-online-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Online Reviews with Filtering [GET /irb/api/v1/protocol-online-reviews/]
    
+ Parameters

    + protocolOnlineReviewId (optional) - Id. Maximum length is 10.
    + documentNumber (optional) - 
    + protocolId (optional) - Protocol Id. Maximum length is 10.
    + submissionIdFk (optional) - Submission Id. Maximum length is 10.
    + protocolReviewerId (optional) - Protocol Reviewer Id. Maximum length is 10.
    + protocolOnlineReviewStatusCode (optional) - The status of the protocol review. Maximum length is 3.
    + protocolOnlineReviewDeterminationRecommendationCode (optional) - Determination Recommendation. Maximum length is 3.
    + dateDue (optional) - The date the online review is to be completed by. Maximum length is 10.
    + dateRequested (optional) - The date the online review was requested. Maximum length is 10.
    + actionsPerformed (optional) - 
    + reviewerApproved (optional) - 
    + adminAccepted (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Online Reviews [GET /irb/api/v1/protocol-online-reviews/]
	                                          
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
    
            {"columns":["protocolOnlineReviewId","documentNumber","protocolId","submissionIdFk","protocolReviewerId","protocolOnlineReviewStatusCode","protocolOnlineReviewDeterminationRecommendationCode","dateDue","dateRequested","actionsPerformed","reviewerApproved","adminAccepted"],"primaryKey":"protocolOnlineReviewId"}
		
### Get Blueprint API specification for Protocol Online Reviews [GET /irb/api/v1/protocol-online-reviews/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Online Reviews.md"
            transfer-encoding:chunked


### Update Protocol Online Reviews [PUT /irb/api/v1/protocol-online-reviews/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Online Reviews [PUT /irb/api/v1/protocol-online-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Online Reviews [POST /irb/api/v1/protocol-online-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Online Reviews [POST /irb/api/v1/protocol-online-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Online Reviews by Key [DELETE /irb/api/v1/protocol-online-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Online Reviews [DELETE /irb/api/v1/protocol-online-reviews/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Online Reviews with Matching [DELETE /irb/api/v1/protocol-online-reviews/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolOnlineReviewId (optional) - Id. Maximum length is 10.
    + documentNumber (optional) - 
    + protocolId (optional) - Protocol Id. Maximum length is 10.
    + submissionIdFk (optional) - Submission Id. Maximum length is 10.
    + protocolReviewerId (optional) - Protocol Reviewer Id. Maximum length is 10.
    + protocolOnlineReviewStatusCode (optional) - The status of the protocol review. Maximum length is 3.
    + protocolOnlineReviewDeterminationRecommendationCode (optional) - Determination Recommendation. Maximum length is 3.
    + dateDue (optional) - The date the online review is to be completed by. Maximum length is 10.
    + dateRequested (optional) - The date the online review was requested. Maximum length is 10.
    + actionsPerformed (optional) - 
    + reviewerApproved (optional) - 
    + adminAccepted (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
