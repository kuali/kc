## Award Budget Personnel Details [/research-sys/api/v1/award-budget-personnel-details/]

### Get Award Budget Personnel Details by Key [GET /research-sys/api/v1/award-budget-personnel-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}

### Get All Award Budget Personnel Details [GET /research-sys/api/v1/award-budget-personnel-details/]
	 
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

### Get All Award Budget Personnel Details with Filtering [GET /research-sys/api/v1/award-budget-personnel-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + budgetPersonnelLineItemId
            + obligatedAmount
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"},
              {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Budget Personnel Details [GET /research-sys/api/v1/award-budget-personnel-details/]
	 
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
		
### Get Blueprint API specification for Award Budget Personnel Details [GET /research-sys/api/v1/award-budget-personnel-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Budget Personnel Details.md"
            transfer-encoding:chunked


### Update Award Budget Personnel Details [PUT /research-sys/api/v1/award-budget-personnel-details/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Budget Personnel Details [PUT /research-sys/api/v1/award-budget-personnel-details/]

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

### Insert Award Budget Personnel Details [POST /research-sys/api/v1/award-budget-personnel-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetPersonnelLineItemId": "(val)","obligatedAmount": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Budget Personnel Details [POST /research-sys/api/v1/award-budget-personnel-details/]

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
            
### Delete Award Budget Personnel Details by Key [DELETE /research-sys/api/v1/award-budget-personnel-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Budget Personnel Details [DELETE /research-sys/api/v1/award-budget-personnel-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Budget Personnel Details with Matching [DELETE /research-sys/api/v1/award-budget-personnel-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + budgetPersonnelLineItemId
            + obligatedAmount


+ Response 204
