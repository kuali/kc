## Budget Category Mappings [/research-common/api/v1/budget-category-mappings/]

### Get Budget Category Mappings by Key [GET /research-common/api/v1/budget-category-mappings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"}

### Get All Budget Category Mappings [GET /research-common/api/v1/budget-category-mappings/]
	 
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

### Get All Budget Category Mappings with Filtering [GET /research-common/api/v1/budget-category-mappings/]
    
+ Parameters

    + budgetCategoryCode (optional) - Budget Category Code. Maximum length is 3.
    + mappingName (optional) - Mapping Name. Maximum length is 100.
    + targetCategoryCode (optional) - Target Category Code. Maximum length is 15.

            
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
			
### Get Schema for Budget Category Mappings [GET /research-common/api/v1/budget-category-mappings/]
	                                          
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
    
            {"columns":["budgetCategoryCode","mappingName","targetCategoryCode"],"primaryKey":"budgetCategoryCode:mappingName:targetCategoryCode"}
		
### Get Blueprint API specification for Budget Category Mappings [GET /research-common/api/v1/budget-category-mappings/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Category Mappings.md"
            transfer-encoding:chunked


### Update Budget Category Mappings [PUT /research-common/api/v1/budget-category-mappings/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Category Mappings [PUT /research-common/api/v1/budget-category-mappings/]

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

### Insert Budget Category Mappings [POST /research-common/api/v1/budget-category-mappings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"budgetCategoryCode": "(val)","mappingName": "(val)","targetCategoryCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Category Mappings [POST /research-common/api/v1/budget-category-mappings/]

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
            
### Delete Budget Category Mappings by Key [DELETE /research-common/api/v1/budget-category-mappings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Category Mappings [DELETE /research-common/api/v1/budget-category-mappings/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Category Mappings with Matching [DELETE /research-common/api/v1/budget-category-mappings/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + budgetCategoryCode (optional) - Budget Category Code. Maximum length is 3.
    + mappingName (optional) - Mapping Name. Maximum length is 100.
    + targetCategoryCode (optional) - Target Category Code. Maximum length is 15.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
