## Terms [/research-sys/api/v1/terms/]

### Get Terms by Key [GET /research-sys/api/v1/terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","specificationId": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Terms [GET /research-sys/api/v1/terms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","specificationId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","specificationId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Terms with Filtering [GET /research-sys/api/v1/terms/]
    
+ Parameters

        + id
            + specificationId
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
              {"id": "(val)","specificationId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","specificationId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Terms [GET /research-sys/api/v1/terms/]
	                                          
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
    
            {"columns":["id","specificationId","description"],"primaryKey":"id"}
		
### Get Blueprint API specification for Terms [GET /research-sys/api/v1/terms/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Terms.md"
            transfer-encoding:chunked


### Update Terms [PUT /research-sys/api/v1/terms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","specificationId": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Terms [PUT /research-sys/api/v1/terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","specificationId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","specificationId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Terms [POST /research-sys/api/v1/terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","specificationId": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","specificationId": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Terms [POST /research-sys/api/v1/terms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","specificationId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","specificationId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","specificationId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","specificationId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Terms by Key [DELETE /research-sys/api/v1/terms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Terms [DELETE /research-sys/api/v1/terms/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Terms with Matching [DELETE /research-sys/api/v1/terms/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + specificationId
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
