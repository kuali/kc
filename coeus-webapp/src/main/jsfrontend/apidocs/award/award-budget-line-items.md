## Award Budget Line Items [/research-sys/api/v1/award-budget-line-items/]

### Get Award Budget Line Items by Key [GET /research-sys/api/v1/award-budget-line-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}

### Get All Award Budget Line Items [GET /research-sys/api/v1/award-budget-line-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"},
              {"budgetLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Budget Line Items with Filtering [GET /research-sys/api/v1/award-budget-line-items/]
    
+ Parameters

        + budgetLineItemId
            + obligatedAmount

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"},
              {"budgetLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Budget Line Items [GET /research-sys/api/v1/award-budget-line-items/]
	                                          
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
    
            {"columns":["budgetLineItemId","obligatedAmount"],"primaryKey":"budgetLineItemId"}
		
### Get Blueprint API specification for Award Budget Line Items [GET /research-sys/api/v1/award-budget-line-items/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Budget Line Items.md"
            transfer-encoding:chunked


### Update Award Budget Line Items [PUT /research-sys/api/v1/award-budget-line-items/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Budget Line Items [PUT /research-sys/api/v1/award-budget-line-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"},
              {"budgetLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Budget Line Items [POST /research-sys/api/v1/award-budget-line-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Budget Line Items [POST /research-sys/api/v1/award-budget-line-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"},
              {"budgetLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"},
              {"budgetLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Budget Line Items by Key [DELETE /research-sys/api/v1/award-budget-line-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Line Items [DELETE /research-sys/api/v1/award-budget-line-items/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Line Items with Matching [DELETE /research-sys/api/v1/award-budget-line-items/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + budgetLineItemId
            + obligatedAmount

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
