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
    
            {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}

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
              {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Special Review Exemptions with Filtering [GET /irb/api/v1/protocol-special-review-exemptions/]
    
+ Parameters

    + protocolSpecialReviewExemptionId (optional) - Protocol Special Review Exemption Id. Maximum length is 22.
    + protocolSpecialReviewId (optional) - 
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
              {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
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
    
            {"columns":["protocolSpecialReviewExemptionId","protocolSpecialReviewId","exemptionTypeCode"],"primaryKey":"protocolSpecialReviewExemptionId"}
		
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


### Update Protocol Special Review Exemptions [PUT /irb/api/v1/protocol-special-review-exemptions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Special Review Exemptions [PUT /irb/api/v1/protocol-special-review-exemptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Special Review Exemptions [POST /irb/api/v1/protocol-special-review-exemptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Special Review Exemptions [POST /irb/api/v1/protocol-special-review-exemptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Special Review Exemptions by Key [DELETE /irb/api/v1/protocol-special-review-exemptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Special Review Exemptions [DELETE /irb/api/v1/protocol-special-review-exemptions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Special Review Exemptions with Matching [DELETE /irb/api/v1/protocol-special-review-exemptions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolSpecialReviewExemptionId (optional) - Protocol Special Review Exemption Id. Maximum length is 22.
    + protocolSpecialReviewId (optional) - 
    + exemptionTypeCode (optional) - Exemption #. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
