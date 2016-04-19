## Iacuc Protocol Special Review Exemptions [/iacuc/api/v1/iacuc-protocol-special-review-exemptions/]

### Get Iacuc Protocol Special Review Exemptions by Key [GET /iacuc/api/v1/iacuc-protocol-special-review-exemptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Special Review Exemptions [GET /iacuc/api/v1/iacuc-protocol-special-review-exemptions/]
	 
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

### Get All Iacuc Protocol Special Review Exemptions with Filtering [GET /iacuc/api/v1/iacuc-protocol-special-review-exemptions/]
    
+ Parameters

    + protocolSpecialReviewExemptionId (optional) - IACUC Protocol Special Review Exemption Id. Maximum length is 22.
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
			
### Get Schema for Iacuc Protocol Special Review Exemptions [GET /iacuc/api/v1/iacuc-protocol-special-review-exemptions/]
	                                          
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
		
### Get Blueprint API specification for Iacuc Protocol Special Review Exemptions [GET /iacuc/api/v1/iacuc-protocol-special-review-exemptions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Special Review Exemptions.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Special Review Exemptions [PUT /iacuc/api/v1/iacuc-protocol-special-review-exemptions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Special Review Exemptions [PUT /iacuc/api/v1/iacuc-protocol-special-review-exemptions/]

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

### Insert Iacuc Protocol Special Review Exemptions [POST /iacuc/api/v1/iacuc-protocol-special-review-exemptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Special Review Exemptions [POST /iacuc/api/v1/iacuc-protocol-special-review-exemptions/]

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
            
### Delete Iacuc Protocol Special Review Exemptions by Key [DELETE /iacuc/api/v1/iacuc-protocol-special-review-exemptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Special Review Exemptions [DELETE /iacuc/api/v1/iacuc-protocol-special-review-exemptions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Special Review Exemptions with Matching [DELETE /iacuc/api/v1/iacuc-protocol-special-review-exemptions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolSpecialReviewExemptionId (optional) - IACUC Protocol Special Review Exemption Id. Maximum length is 22.
    + protocolSpecialReviewId (optional) - 
    + exemptionTypeCode (optional) - Exemption #. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
