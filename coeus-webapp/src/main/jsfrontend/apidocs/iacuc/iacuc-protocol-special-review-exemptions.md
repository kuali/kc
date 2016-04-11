## Iacuc Protocol Special Review Exemptions [/research-sys/api/v1/iacuc-protocol-special-review-exemptions/]

### Get Iacuc Protocol Special Review Exemptions by Key [GET /research-sys/api/v1/iacuc-protocol-special-review-exemptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Special Review Exemptions [GET /research-sys/api/v1/iacuc-protocol-special-review-exemptions/]
	 
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

### Get All Iacuc Protocol Special Review Exemptions with Filtering [GET /research-sys/api/v1/iacuc-protocol-special-review-exemptions/]
    
+ Parameters

        + protocolSpecialReviewExemptionId
            + protocolSpecialReviewId
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
              {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"},
              {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Special Review Exemptions [GET /research-sys/api/v1/iacuc-protocol-special-review-exemptions/]
	                                          
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
		
### Get Blueprint API specification for Iacuc Protocol Special Review Exemptions [GET /research-sys/api/v1/iacuc-protocol-special-review-exemptions/]
	 
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


### Update Iacuc Protocol Special Review Exemptions [PUT /research-sys/api/v1/iacuc-protocol-special-review-exemptions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Special Review Exemptions [PUT /research-sys/api/v1/iacuc-protocol-special-review-exemptions/]

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

### Insert Iacuc Protocol Special Review Exemptions [POST /research-sys/api/v1/iacuc-protocol-special-review-exemptions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolSpecialReviewExemptionId": "(val)","protocolSpecialReviewId": "(val)","exemptionTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Special Review Exemptions [POST /research-sys/api/v1/iacuc-protocol-special-review-exemptions/]

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
            
### Delete Iacuc Protocol Special Review Exemptions by Key [DELETE /research-sys/api/v1/iacuc-protocol-special-review-exemptions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Special Review Exemptions [DELETE /research-sys/api/v1/iacuc-protocol-special-review-exemptions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Special Review Exemptions with Matching [DELETE /research-sys/api/v1/iacuc-protocol-special-review-exemptions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + protocolSpecialReviewExemptionId
            + protocolSpecialReviewId
            + exemptionTypeCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
