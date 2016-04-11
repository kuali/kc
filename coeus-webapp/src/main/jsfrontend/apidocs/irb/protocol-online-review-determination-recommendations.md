## Protocol Online Review Determination Recommendations [/research-sys/api/v1/protocol-online-review-determination-recommendations/]

### Get Protocol Online Review Determination Recommendations by Key [GET /research-sys/api/v1/protocol-online-review-determination-recommendations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Protocol Online Review Determination Recommendations [GET /research-sys/api/v1/protocol-online-review-determination-recommendations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Online Review Determination Recommendations with Filtering [GET /research-sys/api/v1/protocol-online-review-determination-recommendations/]
    
+ Parameters

        + protocolOnlineReviewDeterminationRecommendationCode
            + description

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Online Review Determination Recommendations [GET /research-sys/api/v1/protocol-online-review-determination-recommendations/]
	                                          
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
    
            {"columns":["protocolOnlineReviewDeterminationRecommendationCode","description"],"primaryKey":"protocolOnlineReviewDeterminationRecommendationCode"}
		
### Get Blueprint API specification for Protocol Online Review Determination Recommendations [GET /research-sys/api/v1/protocol-online-review-determination-recommendations/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Online Review Determination Recommendations.md"
            transfer-encoding:chunked


### Update Protocol Online Review Determination Recommendations [PUT /research-sys/api/v1/protocol-online-review-determination-recommendations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Online Review Determination Recommendations [PUT /research-sys/api/v1/protocol-online-review-determination-recommendations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Online Review Determination Recommendations [POST /research-sys/api/v1/protocol-online-review-determination-recommendations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Online Review Determination Recommendations [POST /research-sys/api/v1/protocol-online-review-determination-recommendations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Online Review Determination Recommendations by Key [DELETE /research-sys/api/v1/protocol-online-review-determination-recommendations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Online Review Determination Recommendations [DELETE /research-sys/api/v1/protocol-online-review-determination-recommendations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Online Review Determination Recommendations with Matching [DELETE /research-sys/api/v1/protocol-online-review-determination-recommendations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + protocolOnlineReviewDeterminationRecommendationCode
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
