## Cost Elements [/research-sys/api/v1/cost-elements/]

### Get Cost Elements by Key [GET /research-sys/api/v1/cost-elements/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}

### Get All Cost Elements [GET /research-sys/api/v1/cost-elements/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Cost Elements with Filtering [GET /research-sys/api/v1/cost-elements/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + costElement
            + budgetCategoryCode
            + description
            + onOffCampusFlag
            + active
            + financialObjectCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Cost Elements [GET /research-sys/api/v1/cost-elements/]
	 
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
		
### Get Blueprint API specification for Cost Elements [GET /research-sys/api/v1/cost-elements/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Cost Elements.md"
            transfer-encoding:chunked


### Update Cost Elements [PUT /research-sys/api/v1/cost-elements/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Cost Elements [PUT /research-sys/api/v1/cost-elements/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Cost Elements [POST /research-sys/api/v1/cost-elements/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Cost Elements [POST /research-sys/api/v1/cost-elements/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","budgetCategoryCode": "(val)","description": "(val)","onOffCampusFlag": "(val)","active": "(val)","financialObjectCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Cost Elements by Key [DELETE /research-sys/api/v1/cost-elements/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Cost Elements [DELETE /research-sys/api/v1/cost-elements/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Cost Elements with Matching [DELETE /research-sys/api/v1/cost-elements/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + costElement
            + budgetCategoryCode
            + description
            + onOffCampusFlag
            + active
            + financialObjectCode


+ Response 204
