## Iacuc Protocol Special Reviews [/research-sys/api/v1/iacuc-protocol-special-reviews/]

### Get Iacuc Protocol Special Reviews by Key [GET /research-sys/api/v1/iacuc-protocol-special-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolSpecialReviewId": "(val)","protocolId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Special Reviews [GET /research-sys/api/v1/iacuc-protocol-special-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolSpecialReviewId": "(val)","protocolId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"protocolSpecialReviewId": "(val)","protocolId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Special Reviews with Filtering [GET /research-sys/api/v1/iacuc-protocol-special-reviews/]
    
+ Parameters

        + protocolSpecialReviewId
            + protocolId
            + specialReviewNumber
            + specialReviewTypeCode
            + approvalTypeCode
            + protocolNumber
            + applicationDate
            + approvalDate
            + expirationDate
            + comments

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolSpecialReviewId": "(val)","protocolId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"protocolSpecialReviewId": "(val)","protocolId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Special Reviews [GET /research-sys/api/v1/iacuc-protocol-special-reviews/]
	                                          
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
    
            {"columns":["protocolSpecialReviewId","protocolId","specialReviewNumber","specialReviewTypeCode","approvalTypeCode","protocolNumber","applicationDate","approvalDate","expirationDate","comments"],"primaryKey":"protocolSpecialReviewId"}
		
### Get Blueprint API specification for Iacuc Protocol Special Reviews [GET /research-sys/api/v1/iacuc-protocol-special-reviews/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Special Reviews.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Special Reviews [PUT /research-sys/api/v1/iacuc-protocol-special-reviews/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolSpecialReviewId": "(val)","protocolId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Special Reviews [PUT /research-sys/api/v1/iacuc-protocol-special-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolSpecialReviewId": "(val)","protocolId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"protocolSpecialReviewId": "(val)","protocolId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Special Reviews [POST /research-sys/api/v1/iacuc-protocol-special-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolSpecialReviewId": "(val)","protocolId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolSpecialReviewId": "(val)","protocolId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Special Reviews [POST /research-sys/api/v1/iacuc-protocol-special-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolSpecialReviewId": "(val)","protocolId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"protocolSpecialReviewId": "(val)","protocolId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolSpecialReviewId": "(val)","protocolId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"protocolSpecialReviewId": "(val)","protocolId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Special Reviews by Key [DELETE /research-sys/api/v1/iacuc-protocol-special-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Special Reviews [DELETE /research-sys/api/v1/iacuc-protocol-special-reviews/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Special Reviews with Matching [DELETE /research-sys/api/v1/iacuc-protocol-special-reviews/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + protocolSpecialReviewId
            + protocolId
            + specialReviewNumber
            + specialReviewTypeCode
            + approvalTypeCode
            + protocolNumber
            + applicationDate
            + approvalDate
            + expirationDate
            + comments

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
