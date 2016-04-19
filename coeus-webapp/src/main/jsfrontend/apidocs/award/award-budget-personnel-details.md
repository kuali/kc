## Award Budget Personnel Details [/award/api/v1/award-budget-personnel-details/]

### Get Award Budget Personnel Details by Key [GET /award/api/v1/award-budget-personnel-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}

### Get All Award Budget Personnel Details [GET /award/api/v1/award-budget-personnel-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"},
              {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Budget Personnel Details with Filtering [GET /award/api/v1/award-budget-personnel-details/]
    
+ Parameters

    + budgetPersonnelLineItemId (optional) - Budget Id. Maximum length is 22.
    + obligatedAmount (optional) - Obligated amount from award amount info. Maximum length is 15.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"},
              {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Budget Personnel Details [GET /award/api/v1/award-budget-personnel-details/]
	                                          
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
    
            {"columns":["budgetPersonnelLineItemId","obligatedAmount"],"primaryKey":"budgetPersonnelLineItemId"}
		
### Get Blueprint API specification for Award Budget Personnel Details [GET /award/api/v1/award-budget-personnel-details/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Budget Personnel Details.md"
            transfer-encoding:chunked


### Update Award Budget Personnel Details [PUT /award/api/v1/award-budget-personnel-details/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Budget Personnel Details [PUT /award/api/v1/award-budget-personnel-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"},
              {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Budget Personnel Details [POST /award/api/v1/award-budget-personnel-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Budget Personnel Details [POST /award/api/v1/award-budget-personnel-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"},
              {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"},
              {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Budget Personnel Details by Key [DELETE /award/api/v1/award-budget-personnel-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Personnel Details [DELETE /award/api/v1/award-budget-personnel-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Personnel Details with Matching [DELETE /award/api/v1/award-budget-personnel-details/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + budgetPersonnelLineItemId (optional) - Budget Id. Maximum length is 22.
    + obligatedAmount (optional) - Obligated amount from award amount info. Maximum length is 15.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
