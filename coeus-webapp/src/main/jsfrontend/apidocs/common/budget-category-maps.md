## Budget Category Maps [/research-sys/api/v1/budget-category-maps/]

### Get Budget Category Maps by Key [GET /research-sys/api/v1/budget-category-maps/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Budget Category Maps [GET /research-sys/api/v1/budget-category-maps/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Category Maps with Filtering [GET /research-sys/api/v1/budget-category-maps/]
    
+ Parameters

        + mappingName
            + targetCategoryCode
            + categoryType
            + description

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Category Maps [GET /research-sys/api/v1/budget-category-maps/]
	                                          
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
    
            {"columns":["mappingName","targetCategoryCode","categoryType","description"],"primaryKey":"mappingName:targetCategoryCode"}
		
### Get Blueprint API specification for Budget Category Maps [GET /research-sys/api/v1/budget-category-maps/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Category Maps.md"
            transfer-encoding:chunked


### Update Budget Category Maps [PUT /research-sys/api/v1/budget-category-maps/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Category Maps [PUT /research-sys/api/v1/budget-category-maps/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Category Maps [POST /research-sys/api/v1/budget-category-maps/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Category Maps [POST /research-sys/api/v1/budget-category-maps/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Category Maps by Key [DELETE /research-sys/api/v1/budget-category-maps/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Category Maps [DELETE /research-sys/api/v1/budget-category-maps/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Category Maps with Matching [DELETE /research-sys/api/v1/budget-category-maps/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + mappingName
            + targetCategoryCode
            + categoryType
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
