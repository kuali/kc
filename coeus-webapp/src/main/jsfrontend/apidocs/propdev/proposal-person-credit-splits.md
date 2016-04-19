## Proposal Person Credit Splits [/propdev/api/v1/proposal-person-credit-splits/]

### Get Proposal Person Credit Splits by Key [GET /propdev/api/v1/proposal-person-credit-splits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}

### Get All Proposal Person Credit Splits [GET /propdev/api/v1/proposal-person-credit-splits/]
	 
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

### Get All Proposal Person Credit Splits with Filtering [GET /propdev/api/v1/proposal-person-credit-splits/]
    
+ Parameters

    + invCreditTypeCode (optional) - Investigator Credit Type Code. Maximum length is 3.
    + credit (optional) - Credit. Maximum length is 6.

            
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
			
### Get Schema for Proposal Person Credit Splits [GET /propdev/api/v1/proposal-person-credit-splits/]
	                                          
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
    
            {"columns":["invCreditTypeCode","credit"],"primaryKey":"invCreditTypeCode:proposalPerson"}
		
### Get Blueprint API specification for Proposal Person Credit Splits [GET /propdev/api/v1/proposal-person-credit-splits/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Person Credit Splits.md"
            transfer-encoding:chunked


### Update Proposal Person Credit Splits [PUT /propdev/api/v1/proposal-person-credit-splits/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Person Credit Splits [PUT /propdev/api/v1/proposal-person-credit-splits/]

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

### Insert Proposal Person Credit Splits [POST /propdev/api/v1/proposal-person-credit-splits/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"invCreditTypeCode": "(val)","credit": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Person Credit Splits [POST /propdev/api/v1/proposal-person-credit-splits/]

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
            
### Delete Proposal Person Credit Splits by Key [DELETE /propdev/api/v1/proposal-person-credit-splits/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Person Credit Splits [DELETE /propdev/api/v1/proposal-person-credit-splits/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Person Credit Splits with Matching [DELETE /propdev/api/v1/proposal-person-credit-splits/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + invCreditTypeCode (optional) - Investigator Credit Type Code. Maximum length is 3.
    + credit (optional) - Credit. Maximum length is 6.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
