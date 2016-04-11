## Responsibilities [/research-sys/api/v1/responsibilities/]

### Get Responsibilities by Key [GET /research-sys/api/v1/responsibilities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Responsibilities [GET /research-sys/api/v1/responsibilities/]
	 
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

### Get All Responsibilities with Filtering [GET /research-sys/api/v1/responsibilities/]
    
+ Parameters

        + id
            + namespaceCode
            + name
            + description
            + templateId
            + active

            
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
			
### Get Schema for Responsibilities [GET /research-sys/api/v1/responsibilities/]
	                                          
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
		
### Get Blueprint API specification for Responsibilities [GET /research-sys/api/v1/responsibilities/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Responsibilities.md"
            transfer-encoding:chunked


### Update Responsibilities [PUT /research-sys/api/v1/responsibilities/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Responsibilities [PUT /research-sys/api/v1/responsibilities/]

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

### Insert Responsibilities [POST /research-sys/api/v1/responsibilities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","namespaceCode": "(val)","name": "(val)","description": "(val)","templateId": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Responsibilities [POST /research-sys/api/v1/responsibilities/]

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
            
### Delete Responsibilities by Key [DELETE /research-sys/api/v1/responsibilities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Responsibilities [DELETE /research-sys/api/v1/responsibilities/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Responsibilities with Matching [DELETE /research-sys/api/v1/responsibilities/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + namespaceCode
            + name
            + description
            + templateId
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
