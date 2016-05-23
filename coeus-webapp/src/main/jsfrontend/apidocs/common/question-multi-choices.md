## Question Multi Choices [/research-common/api/v1/question-multi-choices/]

### Get Question Multi Choices by Key [GET /research-common/api/v1/question-multi-choices/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","questionId": "(val)","prompt": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Question Multi Choices [GET /research-common/api/v1/question-multi-choices/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","questionId": "(val)","prompt": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionId": "(val)","prompt": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Question Multi Choices with Filtering [GET /research-common/api/v1/question-multi-choices/]
    
+ Parameters

    + id (optional) - Question Multi-Choice Id. Maximum length is 12.
    + questionId (optional) - Question Ref Id. Maximum length is 12.
    + prompt (optional) - Prompt. Maximum length is 200.
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
              {"id": "(val)","questionId": "(val)","prompt": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionId": "(val)","prompt": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Question Multi Choices [GET /research-common/api/v1/question-multi-choices/]
	                                          
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
    
            {"columns":["id","questionId","prompt","description"],"primaryKey":"id"}
		
### Get Blueprint API specification for Question Multi Choices [GET /research-common/api/v1/question-multi-choices/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Question Multi Choices.md"
            transfer-encoding:chunked
### Update Question Multi Choices [PUT /research-common/api/v1/question-multi-choices/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","questionId": "(val)","prompt": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Question Multi Choices [PUT /research-common/api/v1/question-multi-choices/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","questionId": "(val)","prompt": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionId": "(val)","prompt": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Question Multi Choices [POST /research-common/api/v1/question-multi-choices/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","questionId": "(val)","prompt": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","questionId": "(val)","prompt": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Question Multi Choices [POST /research-common/api/v1/question-multi-choices/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","questionId": "(val)","prompt": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionId": "(val)","prompt": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","questionId": "(val)","prompt": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionId": "(val)","prompt": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Question Multi Choices by Key [DELETE /research-common/api/v1/question-multi-choices/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Question Multi Choices [DELETE /research-common/api/v1/question-multi-choices/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Question Multi Choices with Matching [DELETE /research-common/api/v1/question-multi-choices/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Question Multi-Choice Id. Maximum length is 12.
    + questionId (optional) - Question Ref Id. Maximum length is 12.
    + prompt (optional) - Prompt. Maximum length is 200.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
