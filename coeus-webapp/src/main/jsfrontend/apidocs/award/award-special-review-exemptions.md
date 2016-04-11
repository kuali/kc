## Award Special Review Exemptions [/research-sys/api/v1/award-special-review-exemptions/]

### Get Award Special Review Exemptions by Key [GET /research-sys/api/v1/award-special-review-exemptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Award Special Review Exemptions [GET /research-sys/api/v1/award-special-review-exemptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Special Review Exemptions with Filtering [GET /research-sys/api/v1/award-special-review-exemptions/]
    
+ Parameters

        + awardSpecialReviewExemptionId
            + awardSpecialReviewId
            + exemptionTypeCode

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Special Review Exemptions [GET /research-sys/api/v1/award-special-review-exemptions/]
	                                          
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
    
            {"columns":["awardSpecialReviewExemptionId","awardSpecialReviewId","exemptionTypeCode"],"primaryKey":"awardSpecialReviewExemptionId"}
		
### Get Blueprint API specification for Award Special Review Exemptions [GET /research-sys/api/v1/award-special-review-exemptions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Special Review Exemptions.md"
            transfer-encoding:chunked


### Update Award Special Review Exemptions [PUT /research-sys/api/v1/award-special-review-exemptions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Special Review Exemptions [PUT /research-sys/api/v1/award-special-review-exemptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Special Review Exemptions [POST /research-sys/api/v1/award-special-review-exemptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Special Review Exemptions [POST /research-sys/api/v1/award-special-review-exemptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Special Review Exemptions by Key [DELETE /research-sys/api/v1/award-special-review-exemptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Special Review Exemptions [DELETE /research-sys/api/v1/award-special-review-exemptions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Special Review Exemptions with Matching [DELETE /research-sys/api/v1/award-special-review-exemptions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + awardSpecialReviewExemptionId
            + awardSpecialReviewId
            + exemptionTypeCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
