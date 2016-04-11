## Question Categories [/research-sys/api/v1/question-categories/]

### Get Question Categories by Key [GET /research-sys/api/v1/question-categories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","_primaryKey": "(val)"}

### Get All Question Categories [GET /research-sys/api/v1/question-categories/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]

### Get All Question Categories with Filtering [GET /research-sys/api/v1/question-categories/]
    
+ Parameters

        + id
            + name

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Question Categories [GET /research-sys/api/v1/question-categories/]
	                                          
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
    
            {"columns":["id","name"],"primaryKey":"id"}
		
### Get Blueprint API specification for Question Categories [GET /research-sys/api/v1/question-categories/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Question Categories.md"
            transfer-encoding:chunked


### Update Question Categories [PUT /research-sys/api/v1/question-categories/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Question Categories [PUT /research-sys/api/v1/question-categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Question Categories [POST /research-sys/api/v1/question-categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Question Categories [POST /research-sys/api/v1/question-categories/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Question Categories by Key [DELETE /research-sys/api/v1/question-categories/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Question Categories [DELETE /research-sys/api/v1/question-categories/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Question Categories with Matching [DELETE /research-sys/api/v1/question-categories/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + name

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
