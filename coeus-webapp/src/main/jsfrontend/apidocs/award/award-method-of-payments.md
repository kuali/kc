## Award Method Of Payments [/award/api/v1/award-method-of-payments/]

### Get Award Method Of Payments by Key [GET /award/api/v1/award-method-of-payments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Award Method Of Payments [GET /award/api/v1/award-method-of-payments/]
	 
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

### Get All Award Method Of Payments with Filtering [GET /award/api/v1/award-method-of-payments/]
    
+ Parameters

    + methodOfPaymentCode (optional) - Method Of Payment Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

            
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
			
### Get Schema for Award Method Of Payments [GET /award/api/v1/award-method-of-payments/]
	                                          
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
    
            {"columns":["methodOfPaymentCode","description"],"primaryKey":"methodOfPaymentCode"}
		
### Get Blueprint API specification for Award Method Of Payments [GET /award/api/v1/award-method-of-payments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Method Of Payments.md"
            transfer-encoding:chunked


### Update Award Method Of Payments [PUT /award/api/v1/award-method-of-payments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Method Of Payments [PUT /award/api/v1/award-method-of-payments/]

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

### Insert Award Method Of Payments [POST /award/api/v1/award-method-of-payments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"methodOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Method Of Payments [POST /award/api/v1/award-method-of-payments/]

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
            
### Delete Award Method Of Payments by Key [DELETE /award/api/v1/award-method-of-payments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Method Of Payments [DELETE /award/api/v1/award-method-of-payments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Method Of Payments with Matching [DELETE /award/api/v1/award-method-of-payments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + methodOfPaymentCode (optional) - Method Of Payment Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
