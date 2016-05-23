## Question Explanations [/research-common/api/v1/question-explanations/]

### Get Question Explanations by Key [GET /research-common/api/v1/question-explanations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"}

### Get All Question Explanations [GET /research-common/api/v1/question-explanations/]
	 
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

### Get All Question Explanations with Filtering [GET /research-common/api/v1/question-explanations/]
    
+ Parameters

    + id (optional) - Question Explanation Id. Maximum length is 12.
    + questionId (optional) - Question Ref Id. Maximum length is 12.
    + explanationType (optional) - Explanation Type. Maximum length is 1.
    + explanation (optional) - Explanation. Maximum length is 4000.

            
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
			
### Get Schema for Question Explanations [GET /research-common/api/v1/question-explanations/]
	                                          
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
		
### Get Blueprint API specification for Question Explanations [GET /research-common/api/v1/question-explanations/]
	 
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
### Update Question Explanations [PUT /research-common/api/v1/question-explanations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Question Explanations [PUT /research-common/api/v1/question-explanations/]

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
### Insert Question Explanations [POST /research-common/api/v1/question-explanations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","questionId": "(val)","explanationType": "(val)","explanation": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Question Explanations [POST /research-common/api/v1/question-explanations/]

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
### Delete Question Explanations by Key [DELETE /research-common/api/v1/question-explanations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Question Explanations [DELETE /research-common/api/v1/question-explanations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Question Explanations with Matching [DELETE /research-common/api/v1/question-explanations/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Question Explanation Id. Maximum length is 12.
    + questionId (optional) - Question Ref Id. Maximum length is 12.
    + explanationType (optional) - Explanation Type. Maximum length is 1.
    + explanation (optional) - Explanation. Maximum length is 4000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
