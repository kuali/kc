## Award Special Review Exemptions [/award/api/v1/award-special-review-exemptions/]

### Get Award Special Review Exemptions by Key [GET /award/api/v1/award-special-review-exemptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Award Special Review Exemptions [GET /award/api/v1/award-special-review-exemptions/]
	 
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

### Get All Award Special Review Exemptions with Filtering [GET /award/api/v1/award-special-review-exemptions/]
    
+ Parameters

    + awardSpecialReviewExemptionId (optional) - Award Special Review Exemption Id. Maximum length is 22.
    + awardSpecialReviewId (optional) - 
    + exemptionTypeCode (optional) - Exemption #. Maximum length is 3.

            
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
			
### Get Schema for Award Special Review Exemptions [GET /award/api/v1/award-special-review-exemptions/]
	                                          
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
		
### Get Blueprint API specification for Award Special Review Exemptions [GET /award/api/v1/award-special-review-exemptions/]
	 
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


### Update Award Special Review Exemptions [PUT /award/api/v1/award-special-review-exemptions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Special Review Exemptions [PUT /award/api/v1/award-special-review-exemptions/]

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

### Insert Award Special Review Exemptions [POST /award/api/v1/award-special-review-exemptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardSpecialReviewExemptionId": "(val)","awardSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Special Review Exemptions [POST /award/api/v1/award-special-review-exemptions/]

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
            
### Delete Award Special Review Exemptions by Key [DELETE /award/api/v1/award-special-review-exemptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Special Review Exemptions [DELETE /award/api/v1/award-special-review-exemptions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Special Review Exemptions with Matching [DELETE /award/api/v1/award-special-review-exemptions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardSpecialReviewExemptionId (optional) - Award Special Review Exemption Id. Maximum length is 22.
    + awardSpecialReviewId (optional) - 
    + exemptionTypeCode (optional) - Exemption #. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
