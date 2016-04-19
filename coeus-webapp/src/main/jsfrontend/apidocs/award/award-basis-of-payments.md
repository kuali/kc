## Award Basis Of Payments [/award/api/v1/award-basis-of-payments/]

### Get Award Basis Of Payments by Key [GET /award/api/v1/award-basis-of-payments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"basisOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Award Basis Of Payments [GET /award/api/v1/award-basis-of-payments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"basisOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"basisOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Basis Of Payments with Filtering [GET /award/api/v1/award-basis-of-payments/]
    
+ Parameters

    + basisOfPaymentCode (optional) - Basis Of Payment Code. Maximum length is 3.
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
              {"basisOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"basisOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Basis Of Payments [GET /award/api/v1/award-basis-of-payments/]
	                                          
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
    
            {"columns":["basisOfPaymentCode","description"],"primaryKey":"basisOfPaymentCode"}
		
### Get Blueprint API specification for Award Basis Of Payments [GET /award/api/v1/award-basis-of-payments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Basis Of Payments.md"
            transfer-encoding:chunked


### Update Award Basis Of Payments [PUT /award/api/v1/award-basis-of-payments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"basisOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Basis Of Payments [PUT /award/api/v1/award-basis-of-payments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"basisOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"basisOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Basis Of Payments [POST /award/api/v1/award-basis-of-payments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"basisOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"basisOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Basis Of Payments [POST /award/api/v1/award-basis-of-payments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"basisOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"basisOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"basisOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"basisOfPaymentCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Basis Of Payments by Key [DELETE /award/api/v1/award-basis-of-payments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Basis Of Payments [DELETE /award/api/v1/award-basis-of-payments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Basis Of Payments with Matching [DELETE /award/api/v1/award-basis-of-payments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + basisOfPaymentCode (optional) - Basis Of Payment Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
