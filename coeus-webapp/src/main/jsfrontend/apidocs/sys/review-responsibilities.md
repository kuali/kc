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
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + namespaceCode
            + name
            + description
            + templateId
            + active
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Review Responsibilities [GET /research-sys/api/v1/review-responsibilities/]
	 
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
		
### Get Blueprint API specification for Review Responsibilities [GET /research-sys/api/v1/review-responsibilities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

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
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Review Responsibilities with Matching [DELETE /research-sys/api/v1/review-responsibilities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + namespaceCode
            + name
            + description
            + templateId
            + active


+ Response 204
