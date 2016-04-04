## Valid Basis Method Payments [/research-sys/api/v1/valid-basis-method-payments/]

### Get Valid Basis Method Payments by Key [GET /research-sys/api/v1/valid-basis-method-payments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"}

### Get All Valid Basis Method Payments [GET /research-sys/api/v1/valid-basis-method-payments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"},
              {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"}
            ]

### Get All Valid Basis Method Payments with Filtering [GET /research-sys/api/v1/valid-basis-method-payments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + validBasisMethodPaymentId
            + basisOfPaymentCode
            + methodOfPaymentCode
            + invInstructionsIndicator
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"},
              {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Valid Basis Method Payments [GET /research-sys/api/v1/valid-basis-method-payments/]
	 
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
		
### Get Blueprint API specification for Valid Basis Method Payments [GET /research-sys/api/v1/valid-basis-method-payments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Basis Method Payments.md"
            transfer-encoding:chunked


### Update Valid Basis Method Payments [PUT /research-sys/api/v1/valid-basis-method-payments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Basis Method Payments [PUT /research-sys/api/v1/valid-basis-method-payments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"},
              {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Valid Basis Method Payments [POST /research-sys/api/v1/valid-basis-method-payments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Basis Method Payments [POST /research-sys/api/v1/valid-basis-method-payments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"},
              {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"},
              {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Valid Basis Method Payments by Key [DELETE /research-sys/api/v1/valid-basis-method-payments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Basis Method Payments [DELETE /research-sys/api/v1/valid-basis-method-payments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Valid Basis Method Payments with Matching [DELETE /research-sys/api/v1/valid-basis-method-payments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + validBasisMethodPaymentId
            + basisOfPaymentCode
            + methodOfPaymentCode
            + invInstructionsIndicator


+ Response 204
