## Iacuc Protocol Online Reviews [/research-sys/api/v1/iacuc-protocol-online-reviews/]

### Get Iacuc Protocol Online Reviews by Key [GET /research-sys/api/v1/iacuc-protocol-online-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","determinationReviewTypeCode": "(val)","determinationReviewDateDue": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Online Reviews [GET /research-sys/api/v1/iacuc-protocol-online-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","determinationReviewTypeCode": "(val)","determinationReviewDateDue": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","determinationReviewTypeCode": "(val)","determinationReviewDateDue": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Online Reviews with Filtering [GET /research-sys/api/v1/iacuc-protocol-online-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + protocolOnlineReviewId
            + documentNumber
            + protocolId
            + submissionIdFk
            + protocolReviewerId
            + protocolOnlineReviewStatusCode
            + protocolOnlineReviewDeterminationRecommendationCode
            + dateDue
            + dateRequested
            + actionsPerformed
            + reviewerApproved
            + adminAccepted
            + determinationReviewTypeCode
            + determinationReviewDateDue
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","determinationReviewTypeCode": "(val)","determinationReviewDateDue": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","determinationReviewTypeCode": "(val)","determinationReviewDateDue": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Online Reviews [GET /research-sys/api/v1/iacuc-protocol-online-reviews/]
	 
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
		
### Get Blueprint API specification for Iacuc Protocol Online Reviews [GET /research-sys/api/v1/iacuc-protocol-online-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Online Reviews.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Online Reviews [PUT /research-sys/api/v1/iacuc-protocol-online-reviews/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","determinationReviewTypeCode": "(val)","determinationReviewDateDue": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Online Reviews [PUT /research-sys/api/v1/iacuc-protocol-online-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","determinationReviewTypeCode": "(val)","determinationReviewDateDue": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","determinationReviewTypeCode": "(val)","determinationReviewDateDue": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Online Reviews [POST /research-sys/api/v1/iacuc-protocol-online-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","determinationReviewTypeCode": "(val)","determinationReviewDateDue": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","determinationReviewTypeCode": "(val)","determinationReviewDateDue": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Online Reviews [POST /research-sys/api/v1/iacuc-protocol-online-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","determinationReviewTypeCode": "(val)","determinationReviewDateDue": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","determinationReviewTypeCode": "(val)","determinationReviewDateDue": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","determinationReviewTypeCode": "(val)","determinationReviewDateDue": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewId": "(val)","documentNumber": "(val)","protocolId": "(val)","submissionIdFk": "(val)","protocolReviewerId": "(val)","protocolOnlineReviewStatusCode": "(val)","protocolOnlineReviewDeterminationRecommendationCode": "(val)","dateDue": "(val)","dateRequested": "(val)","actionsPerformed": "(val)","reviewerApproved": "(val)","adminAccepted": "(val)","determinationReviewTypeCode": "(val)","determinationReviewDateDue": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Online Reviews by Key [DELETE /research-sys/api/v1/iacuc-protocol-online-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Online Reviews [DELETE /research-sys/api/v1/iacuc-protocol-online-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol Online Reviews with Matching [DELETE /research-sys/api/v1/iacuc-protocol-online-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + protocolOnlineReviewId
            + documentNumber
            + protocolId
            + submissionIdFk
            + protocolReviewerId
            + protocolOnlineReviewStatusCode
            + protocolOnlineReviewDeterminationRecommendationCode
            + dateDue
            + dateRequested
            + actionsPerformed
            + reviewerApproved
            + adminAccepted
            + determinationReviewTypeCode
            + determinationReviewDateDue


+ Response 204
