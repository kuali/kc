## Unit Formulated Costs [/research-sys/api/v1/unit-formulated-costs/]

### Get Unit Formulated Costs by Key [GET /research-sys/api/v1/unit-formulated-costs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"unitFormulatedCostId": "(val)","formulatedTypeCode": "(val)","unitNumber": "(val)","unitCost": "(val)","_primaryKey": "(val)"}

### Get All Unit Formulated Costs [GET /research-sys/api/v1/unit-formulated-costs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"unitFormulatedCostId": "(val)","formulatedTypeCode": "(val)","unitNumber": "(val)","unitCost": "(val)","_primaryKey": "(val)"},
              {"unitFormulatedCostId": "(val)","formulatedTypeCode": "(val)","unitNumber": "(val)","unitCost": "(val)","_primaryKey": "(val)"}
            ]

### Get All Unit Formulated Costs with Filtering [GET /research-sys/api/v1/unit-formulated-costs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + unitFormulatedCostId
            + formulatedTypeCode
            + unitNumber
            + unitCost
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"unitFormulatedCostId": "(val)","formulatedTypeCode": "(val)","unitNumber": "(val)","unitCost": "(val)","_primaryKey": "(val)"},
              {"unitFormulatedCostId": "(val)","formulatedTypeCode": "(val)","unitNumber": "(val)","unitCost": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Unit Formulated Costs [GET /research-sys/api/v1/unit-formulated-costs/]
	 
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
		
### Get Blueprint API specification for Unit Formulated Costs [GET /research-sys/api/v1/unit-formulated-costs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Unit Formulated Costs.md"
            transfer-encoding:chunked


### Update Unit Formulated Costs [PUT /research-sys/api/v1/unit-formulated-costs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"unitFormulatedCostId": "(val)","formulatedTypeCode": "(val)","unitNumber": "(val)","unitCost": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Unit Formulated Costs [PUT /research-sys/api/v1/unit-formulated-costs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"unitFormulatedCostId": "(val)","formulatedTypeCode": "(val)","unitNumber": "(val)","unitCost": "(val)","_primaryKey": "(val)"},
              {"unitFormulatedCostId": "(val)","formulatedTypeCode": "(val)","unitNumber": "(val)","unitCost": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Unit Formulated Costs [POST /research-sys/api/v1/unit-formulated-costs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"unitFormulatedCostId": "(val)","formulatedTypeCode": "(val)","unitNumber": "(val)","unitCost": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"unitFormulatedCostId": "(val)","formulatedTypeCode": "(val)","unitNumber": "(val)","unitCost": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Unit Formulated Costs [POST /research-sys/api/v1/unit-formulated-costs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"unitFormulatedCostId": "(val)","formulatedTypeCode": "(val)","unitNumber": "(val)","unitCost": "(val)","_primaryKey": "(val)"},
              {"unitFormulatedCostId": "(val)","formulatedTypeCode": "(val)","unitNumber": "(val)","unitCost": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"unitFormulatedCostId": "(val)","formulatedTypeCode": "(val)","unitNumber": "(val)","unitCost": "(val)","_primaryKey": "(val)"},
              {"unitFormulatedCostId": "(val)","formulatedTypeCode": "(val)","unitNumber": "(val)","unitCost": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Unit Formulated Costs by Key [DELETE /research-sys/api/v1/unit-formulated-costs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Unit Formulated Costs [DELETE /research-sys/api/v1/unit-formulated-costs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Unit Formulated Costs with Matching [DELETE /research-sys/api/v1/unit-formulated-costs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + unitFormulatedCostId
            + formulatedTypeCode
            + unitNumber
            + unitCost


+ Response 204
