## Question Types [/research-common/api/v1/question-types/]

### Get Question Types by Key [GET /research-common/api/v1/question-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","_primaryKey": "(val)"}

### Get All Question Types [GET /research-common/api/v1/question-types/]
	 
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

### Get All Question Types with Filtering [GET /research-common/api/v1/question-types/]
    
+ Parameters

    + id (optional) - Question Type Id. Maximum length is 3.
    + name (optional) - Question Type Name. Maximum length is 30.

            
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
			
### Get Schema for Question Types [GET /research-common/api/v1/question-types/]
	                                          
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
		
### Get Blueprint API specification for Question Types [GET /research-common/api/v1/question-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Question Types.md"
            transfer-encoding:chunked
### Update Question Types [PUT /research-common/api/v1/question-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Question Types [PUT /research-common/api/v1/question-types/]

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
### Insert Question Types [POST /research-common/api/v1/question-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Question Types [POST /research-common/api/v1/question-types/]

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
### Delete Question Types by Key [DELETE /research-common/api/v1/question-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Question Types [DELETE /research-common/api/v1/question-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Question Types with Matching [DELETE /research-common/api/v1/question-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Question Type Id. Maximum length is 3.
    + name (optional) - Question Type Name. Maximum length is 30.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
