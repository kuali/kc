## Proposal Unit Credit Splits [/research-sys/api/v1/proposal-unit-credit-splits/]

### Get Proposal Unit Credit Splits by Key [GET /research-sys/api/v1/proposal-unit-credit-splits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}

### Get All Proposal Unit Credit Splits [GET /research-sys/api/v1/proposal-unit-credit-splits/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Unit Credit Splits with Filtering [GET /research-sys/api/v1/proposal-unit-credit-splits/]
    
+ Parameters

        + invCreditTypeCode
            + credit

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Unit Credit Splits [GET /research-sys/api/v1/proposal-unit-credit-splits/]
	                                          
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
    
            {"columns":["invCreditTypeCode","credit"],"primaryKey":"invCreditTypeCode:proposalPersonUnit"}
		
### Get Blueprint API specification for Proposal Unit Credit Splits [GET /research-sys/api/v1/proposal-unit-credit-splits/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Unit Credit Splits.md"
            transfer-encoding:chunked


### Update Proposal Unit Credit Splits [PUT /research-sys/api/v1/proposal-unit-credit-splits/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Unit Credit Splits [PUT /research-sys/api/v1/proposal-unit-credit-splits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Unit Credit Splits [POST /research-sys/api/v1/proposal-unit-credit-splits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Unit Credit Splits [POST /research-sys/api/v1/proposal-unit-credit-splits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"},
              {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Unit Credit Splits by Key [DELETE /research-sys/api/v1/proposal-unit-credit-splits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Unit Credit Splits [DELETE /research-sys/api/v1/proposal-unit-credit-splits/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Unit Credit Splits with Matching [DELETE /research-sys/api/v1/proposal-unit-credit-splits/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + invCreditTypeCode
            + credit

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
