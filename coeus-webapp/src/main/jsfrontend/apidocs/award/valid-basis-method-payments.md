## Valid Basis Method Payments [/award/api/v1/valid-basis-method-payments/]

### Get Valid Basis Method Payments by Key [GET /award/api/v1/valid-basis-method-payments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"}

### Get All Valid Basis Method Payments [GET /award/api/v1/valid-basis-method-payments/]
	 
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

### Get All Valid Basis Method Payments with Filtering [GET /award/api/v1/valid-basis-method-payments/]
    
+ Parameters

    + validBasisMethodPaymentId (optional) - Valid Basis Method Pmt Id. Maximum length is 22.
    + basisOfPaymentCode (optional) - Basis Of Payment Code. Maximum length is 22.
    + methodOfPaymentCode (optional) - Method Of Payment Code. Maximum length is 22.
    + invInstructionsIndicator (optional) - Inv Instructions Indicator. Maximum length is 1.

            
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
			
### Get Schema for Valid Basis Method Payments [GET /award/api/v1/valid-basis-method-payments/]
	                                          
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
    
            {"columns":["validBasisMethodPaymentId","basisOfPaymentCode","methodOfPaymentCode","invInstructionsIndicator"],"primaryKey":"validBasisMethodPaymentId"}
		
### Get Blueprint API specification for Valid Basis Method Payments [GET /award/api/v1/valid-basis-method-payments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Basis Method Payments.md"
            transfer-encoding:chunked
### Update Valid Basis Method Payments [PUT /award/api/v1/valid-basis-method-payments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Basis Method Payments [PUT /award/api/v1/valid-basis-method-payments/]

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
### Insert Valid Basis Method Payments [POST /award/api/v1/valid-basis-method-payments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validBasisMethodPaymentId": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","invInstructionsIndicator": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Basis Method Payments [POST /award/api/v1/valid-basis-method-payments/]

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
### Delete Valid Basis Method Payments by Key [DELETE /award/api/v1/valid-basis-method-payments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Basis Method Payments [DELETE /award/api/v1/valid-basis-method-payments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Basis Method Payments with Matching [DELETE /award/api/v1/valid-basis-method-payments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + validBasisMethodPaymentId (optional) - Valid Basis Method Pmt Id. Maximum length is 22.
    + basisOfPaymentCode (optional) - Basis Of Payment Code. Maximum length is 22.
    + methodOfPaymentCode (optional) - Method Of Payment Code. Maximum length is 22.
    + invInstructionsIndicator (optional) - Inv Instructions Indicator. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
