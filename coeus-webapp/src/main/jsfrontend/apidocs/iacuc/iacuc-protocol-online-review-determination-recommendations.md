## Iacuc Protocol Online Review Determination Recommendations [/iacuc/api/v1/iacuc-protocol-online-review-determination-recommendations/]

### Get Iacuc Protocol Online Review Determination Recommendations by Key [GET /iacuc/api/v1/iacuc-protocol-online-review-determination-recommendations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","iacucProtocolReviewTypeCode": "(val)","iacucProtocolActionTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Online Review Determination Recommendations [GET /iacuc/api/v1/iacuc-protocol-online-review-determination-recommendations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","iacucProtocolReviewTypeCode": "(val)","iacucProtocolActionTypeCode": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","iacucProtocolReviewTypeCode": "(val)","iacucProtocolActionTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Online Review Determination Recommendations with Filtering [GET /iacuc/api/v1/iacuc-protocol-online-review-determination-recommendations/]
    
+ Parameters

    + protocolOnlineReviewDeterminationRecommendationCode (optional) - Status Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 300.
    + iacucProtocolReviewTypeCode (optional) - Review Type. Maximum length is 3.
    + iacucProtocolActionTypeCode (optional) - Recommended Action. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","iacucProtocolReviewTypeCode": "(val)","iacucProtocolActionTypeCode": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","iacucProtocolReviewTypeCode": "(val)","iacucProtocolActionTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Online Review Determination Recommendations [GET /iacuc/api/v1/iacuc-protocol-online-review-determination-recommendations/]
	                                          
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
    
            {"columns":["protocolOnlineReviewDeterminationRecommendationCode","description","iacucProtocolReviewTypeCode","iacucProtocolActionTypeCode"],"primaryKey":"protocolOnlineReviewDeterminationRecommendationCode"}
		
### Get Blueprint API specification for Iacuc Protocol Online Review Determination Recommendations [GET /iacuc/api/v1/iacuc-protocol-online-review-determination-recommendations/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Online Review Determination Recommendations.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Online Review Determination Recommendations [PUT /iacuc/api/v1/iacuc-protocol-online-review-determination-recommendations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","iacucProtocolReviewTypeCode": "(val)","iacucProtocolActionTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Online Review Determination Recommendations [PUT /iacuc/api/v1/iacuc-protocol-online-review-determination-recommendations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","iacucProtocolReviewTypeCode": "(val)","iacucProtocolActionTypeCode": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","iacucProtocolReviewTypeCode": "(val)","iacucProtocolActionTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Protocol Online Review Determination Recommendations [POST /iacuc/api/v1/iacuc-protocol-online-review-determination-recommendations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","iacucProtocolReviewTypeCode": "(val)","iacucProtocolActionTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","iacucProtocolReviewTypeCode": "(val)","iacucProtocolActionTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Online Review Determination Recommendations [POST /iacuc/api/v1/iacuc-protocol-online-review-determination-recommendations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","iacucProtocolReviewTypeCode": "(val)","iacucProtocolActionTypeCode": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","iacucProtocolReviewTypeCode": "(val)","iacucProtocolActionTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","iacucProtocolReviewTypeCode": "(val)","iacucProtocolActionTypeCode": "(val)","_primaryKey": "(val)"},
              {"protocolOnlineReviewDeterminationRecommendationCode": "(val)","description": "(val)","iacucProtocolReviewTypeCode": "(val)","iacucProtocolActionTypeCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Protocol Online Review Determination Recommendations by Key [DELETE /iacuc/api/v1/iacuc-protocol-online-review-determination-recommendations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Online Review Determination Recommendations [DELETE /iacuc/api/v1/iacuc-protocol-online-review-determination-recommendations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Online Review Determination Recommendations with Matching [DELETE /iacuc/api/v1/iacuc-protocol-online-review-determination-recommendations/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolOnlineReviewDeterminationRecommendationCode (optional) - Status Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 300.
    + iacucProtocolReviewTypeCode (optional) - Review Type. Maximum length is 3.
    + iacucProtocolActionTypeCode (optional) - Recommended Action. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
