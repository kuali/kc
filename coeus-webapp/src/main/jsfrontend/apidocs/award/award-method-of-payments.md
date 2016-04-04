## Award Method Of Payments [/research-sys/api/v1/award-method-of-payments/]

### Get Award Method Of Payments by Key [GET /research-sys/api/v1/award-method-of-payments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Award Method Of Payments [GET /research-sys/api/v1/award-method-of-payments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Method Of Payments with Filtering [GET /research-sys/api/v1/award-method-of-payments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + methodOfPaymentCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Method Of Payments [GET /research-sys/api/v1/award-method-of-payments/]
	 
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
		
### Get Blueprint API specification for Award Method Of Payments [GET /research-sys/api/v1/award-method-of-payments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Method Of Payments.md"
            transfer-encoding:chunked


### Update Award Method Of Payments [PUT /research-sys/api/v1/award-method-of-payments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Method Of Payments [PUT /research-sys/api/v1/award-method-of-payments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Method Of Payments [POST /research-sys/api/v1/award-method-of-payments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Method Of Payments [POST /research-sys/api/v1/award-method-of-payments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Method Of Payments by Key [DELETE /research-sys/api/v1/award-method-of-payments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Method Of Payments [DELETE /research-sys/api/v1/award-method-of-payments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Method Of Payments with Matching [DELETE /research-sys/api/v1/award-method-of-payments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + methodOfPaymentCode
            + description


+ Response 204
