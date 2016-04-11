## Valid Award Basis Payments [/research-sys/api/v1/valid-award-basis-payments/]

### Get Valid Award Basis Payments by Key [GET /research-sys/api/v1/valid-award-basis-payments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validAwardBasisPaymentId": "(val)","basisOfPaymentCode": "(val)","awardTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Valid Award Basis Payments [GET /research-sys/api/v1/valid-award-basis-payments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validAwardBasisPaymentId": "(val)","basisOfPaymentCode": "(val)","awardTypeCode": "(val)","_primaryKey": "(val)"},
              {"validAwardBasisPaymentId": "(val)","basisOfPaymentCode": "(val)","awardTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Valid Award Basis Payments with Filtering [GET /research-sys/api/v1/valid-award-basis-payments/]
    
+ Parameters

        + validAwardBasisPaymentId
            + basisOfPaymentCode
            + awardTypeCode

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validAwardBasisPaymentId": "(val)","basisOfPaymentCode": "(val)","awardTypeCode": "(val)","_primaryKey": "(val)"},
              {"validAwardBasisPaymentId": "(val)","basisOfPaymentCode": "(val)","awardTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Valid Award Basis Payments [GET /research-sys/api/v1/valid-award-basis-payments/]
	                                          
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
    
            {"columns":["validAwardBasisPaymentId","basisOfPaymentCode","awardTypeCode"],"primaryKey":"validAwardBasisPaymentId"}
		
### Get Blueprint API specification for Valid Award Basis Payments [GET /research-sys/api/v1/valid-award-basis-payments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Award Basis Payments.md"
            transfer-encoding:chunked


### Update Valid Award Basis Payments [PUT /research-sys/api/v1/valid-award-basis-payments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validAwardBasisPaymentId": "(val)","basisOfPaymentCode": "(val)","awardTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Award Basis Payments [PUT /research-sys/api/v1/valid-award-basis-payments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validAwardBasisPaymentId": "(val)","basisOfPaymentCode": "(val)","awardTypeCode": "(val)","_primaryKey": "(val)"},
              {"validAwardBasisPaymentId": "(val)","basisOfPaymentCode": "(val)","awardTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Valid Award Basis Payments [POST /research-sys/api/v1/valid-award-basis-payments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validAwardBasisPaymentId": "(val)","basisOfPaymentCode": "(val)","awardTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validAwardBasisPaymentId": "(val)","basisOfPaymentCode": "(val)","awardTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Award Basis Payments [POST /research-sys/api/v1/valid-award-basis-payments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validAwardBasisPaymentId": "(val)","basisOfPaymentCode": "(val)","awardTypeCode": "(val)","_primaryKey": "(val)"},
              {"validAwardBasisPaymentId": "(val)","basisOfPaymentCode": "(val)","awardTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"validAwardBasisPaymentId": "(val)","basisOfPaymentCode": "(val)","awardTypeCode": "(val)","_primaryKey": "(val)"},
              {"validAwardBasisPaymentId": "(val)","basisOfPaymentCode": "(val)","awardTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Valid Award Basis Payments by Key [DELETE /research-sys/api/v1/valid-award-basis-payments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Award Basis Payments [DELETE /research-sys/api/v1/valid-award-basis-payments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Award Basis Payments with Matching [DELETE /research-sys/api/v1/valid-award-basis-payments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + validAwardBasisPaymentId
            + basisOfPaymentCode
            + awardTypeCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
