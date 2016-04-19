## Budget Category Maps [/research-common/api/v1/budget-category-maps/]

### Get Budget Category Maps by Key [GET /research-common/api/v1/budget-category-maps/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Budget Category Maps [GET /research-common/api/v1/budget-category-maps/]
	 
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

### Get All Budget Category Maps with Filtering [GET /research-common/api/v1/budget-category-maps/]
    
+ Parameters

    + mappingName (optional) - Mapping Name. Maximum length is 100.
    + targetCategoryCode (optional) - Target Category Code. Maximum length is 15.
    + categoryType (optional) - Category Type. Maximum length is 200.
    + description (optional) - Description. Maximum length is 200.

            
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
			
### Get Schema for Budget Category Maps [GET /research-common/api/v1/budget-category-maps/]
	                                          
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
		
### Get Blueprint API specification for Budget Category Maps [GET /research-common/api/v1/budget-category-maps/]
	 
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


### Update Budget Category Maps [PUT /research-common/api/v1/budget-category-maps/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Category Maps [PUT /research-common/api/v1/budget-category-maps/]

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

### Insert Budget Category Maps [POST /research-common/api/v1/budget-category-maps/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"mappingName": "(val)","targetCategoryCode": "(val)","categoryType": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Category Maps [POST /research-common/api/v1/budget-category-maps/]

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
            
### Delete Budget Category Maps by Key [DELETE /research-common/api/v1/budget-category-maps/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Category Maps [DELETE /research-common/api/v1/budget-category-maps/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Category Maps with Matching [DELETE /research-common/api/v1/budget-category-maps/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + mappingName (optional) - Mapping Name. Maximum length is 100.
    + targetCategoryCode (optional) - Target Category Code. Maximum length is 15.
    + categoryType (optional) - Category Type. Maximum length is 200.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
