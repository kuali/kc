## Award Special Reviews [/research-sys/api/v1/award-special-reviews/]

### Get Award Special Reviews by Key [GET /research-sys/api/v1/award-special-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardSpecialReviewId": "(val)","awardId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Award Special Reviews [GET /research-sys/api/v1/award-special-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardSpecialReviewId": "(val)","awardId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardSpecialReviewId": "(val)","awardId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Special Reviews with Filtering [GET /research-sys/api/v1/award-special-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardSpecialReviewId
            + awardId
            + specialReviewNumber
            + specialReviewTypeCode
            + approvalTypeCode
            + protocolNumber
            + applicationDate
            + approvalDate
            + expirationDate
            + comments
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardSpecialReviewId": "(val)","awardId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardSpecialReviewId": "(val)","awardId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Special Reviews [GET /research-sys/api/v1/award-special-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Award Special Reviews [GET /research-sys/api/v1/award-special-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Special Reviews.md"
            transfer-encoding:chunked


### Update Award Special Reviews [PUT /research-sys/api/v1/award-special-reviews/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSpecialReviewId": "(val)","awardId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Special Reviews [PUT /research-sys/api/v1/award-special-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardSpecialReviewId": "(val)","awardId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardSpecialReviewId": "(val)","awardId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Special Reviews [POST /research-sys/api/v1/award-special-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardSpecialReviewId": "(val)","awardId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardSpecialReviewId": "(val)","awardId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Special Reviews [POST /research-sys/api/v1/award-special-reviews/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardSpecialReviewId": "(val)","awardId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardSpecialReviewId": "(val)","awardId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardSpecialReviewId": "(val)","awardId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardSpecialReviewId": "(val)","awardId": "(val)","specialReviewNumber": "(val)","specialReviewTypeCode": "(val)","approvalTypeCode": "(val)","protocolNumber": "(val)","applicationDate": "(val)","approvalDate": "(val)","expirationDate": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Special Reviews by Key [DELETE /research-sys/api/v1/award-special-reviews/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Special Reviews [DELETE /research-sys/api/v1/award-special-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Special Reviews with Matching [DELETE /research-sys/api/v1/award-special-reviews/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardSpecialReviewId
            + awardId
            + specialReviewNumber
            + specialReviewTypeCode
            + approvalTypeCode
            + protocolNumber
            + applicationDate
            + approvalDate
            + expirationDate
            + comments


+ Response 204
