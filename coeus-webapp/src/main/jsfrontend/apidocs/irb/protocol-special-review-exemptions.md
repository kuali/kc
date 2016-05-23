## Protocol Special Review Exemptions [/irb/api/v1/protocol-special-review-exemptions/]

### Get Protocol Special Review Exemptions by Key [GET /irb/api/v1/protocol-special-review-exemptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolSpecialReviewExemptionId": "(val)","exemptionTypeCode": "(val)","protocolSpecialReview.protocolSpecialReviewId": "(val)","_primaryKey": "(val)"}

### Get All Protocol Special Review Exemptions [GET /irb/api/v1/protocol-special-review-exemptions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolSpecialReviewExemptionId": "(val)","exemptionTypeCode": "(val)","protocolSpecialReview.protocolSpecialReviewId": "(val)","_primaryKey": "(val)"},
              {"protocolSpecialReviewExemptionId": "(val)","exemptionTypeCode": "(val)","protocolSpecialReview.protocolSpecialReviewId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Special Review Exemptions with Filtering [GET /irb/api/v1/protocol-special-review-exemptions/]
    
+ Parameters

    + protocolSpecialReviewExemptionId (optional) - Protocol Special Review Exemption Id. Maximum length is 22.
    + exemptionTypeCode (optional) - Exemption #. Maximum length is 3.
    + protocolSpecialReview.protocolSpecialReviewId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolSpecialReviewExemptionId": "(val)","exemptionTypeCode": "(val)","protocolSpecialReview.protocolSpecialReviewId": "(val)","_primaryKey": "(val)"},
              {"protocolSpecialReviewExemptionId": "(val)","exemptionTypeCode": "(val)","protocolSpecialReview.protocolSpecialReviewId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Special Review Exemptions [GET /irb/api/v1/protocol-special-review-exemptions/]
	                                          
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
    
            {"columns":["protocolSpecialReviewExemptionId","exemptionTypeCode","protocolSpecialReview.protocolSpecialReviewId"],"primaryKey":"protocolSpecialReviewExemptionId"}
		
### Get Blueprint API specification for Protocol Special Review Exemptions [GET /irb/api/v1/protocol-special-review-exemptions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Special Review Exemptions.md"
            transfer-encoding:chunked
