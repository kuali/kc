## Protocol Special Reviews [/irb/api/v1/protocol-special-reviews/]

### Get Protocol Special Reviews by Key [GET /irb/api/v1/protocol-special-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolSpecialReviewId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","sequenceOwner.protocolId": "(val)","_primaryKey": "(val)"}

### Get All Protocol Special Reviews [GET /irb/api/v1/protocol-special-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolSpecialReviewId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","sequenceOwner.protocolId": "(val)","_primaryKey": "(val)"},
              {"protocolSpecialReviewId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","sequenceOwner.protocolId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Special Reviews with Filtering [GET /irb/api/v1/protocol-special-reviews/]
    
+ Parameters

    + protocolSpecialReviewId (optional) - Protocol Special Review Id. Maximum length is 22.
    + specialReviewNumber (optional) - Special Review Number. Maximum length is 22.
    + specialReviewTypeCode (optional) - Special Review Type Code. Maximum length is 3.
    + approvalTypeCode (optional) - Approval Status Type Code. Maximum length is 3.
    + protocolNumber (optional) - Protocol Number. Maximum length is 20.
    + applicationDate (optional) - Application Date. Maximum length is 10.
    + approvalDate (optional) - Approval Date. Maximum length is 10.
    + expirationDate (optional) - Expiration Date. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 2000.
    + sequenceOwner.protocolId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolSpecialReviewId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","sequenceOwner.protocolId": "(val)","_primaryKey": "(val)"},
              {"protocolSpecialReviewId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","sequenceOwner.protocolId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Special Reviews [GET /irb/api/v1/protocol-special-reviews/]
	                                          
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
    
            {"columns":["protocolSpecialReviewId","specialReviewNumber","specialReviewTypeCode","approvalTypeCode","protocolNumber","applicationDate","approvalDate","expirationDate","comments","sequenceOwner.protocolId"],"primaryKey":"protocolSpecialReviewId"}
		
### Get Blueprint API specification for Protocol Special Reviews [GET /irb/api/v1/protocol-special-reviews/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Special Reviews.md"
            transfer-encoding:chunked
