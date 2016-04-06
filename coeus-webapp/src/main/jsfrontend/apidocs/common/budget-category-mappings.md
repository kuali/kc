## Budget Category Mappings [/research-sys/api/v1/budget-category-mappings/]

### Get Budget Category Mappings by Key [GET /research-sys/api/v1/budget-category-mappings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"}

### Get All Budget Category Mappings [GET /research-sys/api/v1/budget-category-mappings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"},
              {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Category Mappings with Filtering [GET /research-sys/api/v1/budget-category-mappings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + budgetCategoryCode
            + mappingName
            + targetCategoryCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"},
              {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Category Mappings [GET /research-sys/api/v1/budget-category-mappings/]
	 
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
		
### Get Blueprint API specification for Budget Category Mappings [GET /research-sys/api/v1/budget-category-mappings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Category Mappings.md"
            transfer-encoding:chunked


### Update Budget Category Mappings [PUT /research-sys/api/v1/budget-category-mappings/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Category Mappings [PUT /research-sys/api/v1/budget-category-mappings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"},
              {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Category Mappings [POST /research-sys/api/v1/budget-category-mappings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Category Mappings [POST /research-sys/api/v1/budget-category-mappings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"},
              {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"},
              {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Category Mappings by Key [DELETE /research-sys/api/v1/budget-category-mappings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Category Mappings [DELETE /research-sys/api/v1/budget-category-mappings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Budget Category Mappings with Matching [DELETE /research-sys/api/v1/budget-category-mappings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + budgetCategoryCode
            + mappingName
            + targetCategoryCode


+ Response 204
