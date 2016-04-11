## Question Explanations [/research-sys/api/v1/question-explanations/]

### Get Question Explanations by Key [GET /research-sys/api/v1/question-explanations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"}

### Get All Question Explanations [GET /research-sys/api/v1/question-explanations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            ]

### Get All Question Explanations with Filtering [GET /research-sys/api/v1/question-explanations/]
    
+ Parameters

        + id
            + questionId
            + explanationType
            + explanation

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Question Explanations [GET /research-sys/api/v1/question-explanations/]
	                                          
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
    
            {"columns":["id","questionId","explanationType","explanation"],"primaryKey":"id"}
		
### Get Blueprint API specification for Question Explanations [GET /research-sys/api/v1/question-explanations/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Question Explanations.md"
            transfer-encoding:chunked


### Update Question Explanations [PUT /research-sys/api/v1/question-explanations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Question Explanations [PUT /research-sys/api/v1/question-explanations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Question Explanations [POST /research-sys/api/v1/question-explanations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Question Explanations [POST /research-sys/api/v1/question-explanations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Question Explanations by Key [DELETE /research-sys/api/v1/question-explanations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Question Explanations [DELETE /research-sys/api/v1/question-explanations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Question Explanations with Matching [DELETE /research-sys/api/v1/question-explanations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + questionId
            + explanationType
            + explanation

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
