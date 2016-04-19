## Categories [/research-sys/api/v1/categories/]

### Get Categories by Key [GET /research-sys/api/v1/categories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","namespace": "(val)","_primaryKey": "(val)"}

### Get All Categories [GET /research-sys/api/v1/categories/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","_primaryKey": "(val)"}
            ]

### Get All Categories with Filtering [GET /research-sys/api/v1/categories/]
    
+ Parameters

    + id (optional) - ID.
    + name (optional) - Name.
    + namespace (optional) - Namespace.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Categories [GET /research-sys/api/v1/categories/]
	                                          
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
    
            {"columns":["id","name","namespace"],"primaryKey":"id"}
		
### Get Blueprint API specification for Categories [GET /research-sys/api/v1/categories/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Categories.md"
            transfer-encoding:chunked


### Update Categories [PUT /research-sys/api/v1/categories/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","namespace": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Categories [PUT /research-sys/api/v1/categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Categories [POST /research-sys/api/v1/categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","namespace": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","namespace": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Categories [POST /research-sys/api/v1/categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","namespace": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","namespace": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Categories by Key [DELETE /research-sys/api/v1/categories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Categories [DELETE /research-sys/api/v1/categories/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Categories with Matching [DELETE /research-sys/api/v1/categories/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - ID.
    + name (optional) - Name.
    + namespace (optional) - Namespace.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
