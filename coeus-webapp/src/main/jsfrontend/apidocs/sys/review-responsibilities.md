## Review Responsibilities [/research-sys/api/v1/review-responsibilities/]

### Get Review Responsibilities by Key [GET /research-sys/api/v1/review-responsibilities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Review Responsibilities [GET /research-sys/api/v1/review-responsibilities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Review Responsibilities with Filtering [GET /research-sys/api/v1/review-responsibilities/]
    
+ Parameters

    + id (optional) - Responsibility Identifier. Maximum length is 10.
    + namespaceCode (optional) - Code identifying the namespace. Maximum length is 20.
    + name (optional) - Nm. Maximum length is 100.
    + description (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 400.
    + templateId (optional) - Template Id.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Review Responsibilities [GET /research-sys/api/v1/review-responsibilities/]
	                                          
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
    
            {"columns":["id","namespaceCode","name","description","templateId","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Review Responsibilities [GET /research-sys/api/v1/review-responsibilities/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Review Responsibilities.md"
            transfer-encoding:chunked


### Update Review Responsibilities [PUT /research-sys/api/v1/review-responsibilities/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Review Responsibilities [PUT /research-sys/api/v1/review-responsibilities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Review Responsibilities [POST /research-sys/api/v1/review-responsibilities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Review Responsibilities [POST /research-sys/api/v1/review-responsibilities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Review Responsibilities by Key [DELETE /research-sys/api/v1/review-responsibilities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Review Responsibilities [DELETE /research-sys/api/v1/review-responsibilities/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Review Responsibilities with Matching [DELETE /research-sys/api/v1/review-responsibilities/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Responsibility Identifier. Maximum length is 10.
    + namespaceCode (optional) - Code identifying the namespace. Maximum length is 20.
    + name (optional) - Nm. Maximum length is 100.
    + description (optional) - This attribute should always be overriden on the descriptive elements. Maximum length is 400.
    + templateId (optional) - Template Id.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
