## Answers [/research-sys/api/v1/answers/]

### Get Answers by Key [GET /research-sys/api/v1/answers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","answerHeaderId": "(val)","questionId": "(val)","questionnaireQuestionsId": "(val)","questionNumber": "(val)","answerNumber": "(val)","answer": "(val)","_primaryKey": "(val)"}

### Get All Answers [GET /research-sys/api/v1/answers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","answerHeaderId": "(val)","questionId": "(val)","questionnaireQuestionsId": "(val)","questionNumber": "(val)","answerNumber": "(val)","answer": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","answerHeaderId": "(val)","questionId": "(val)","questionnaireQuestionsId": "(val)","questionNumber": "(val)","answerNumber": "(val)","answer": "(val)","_primaryKey": "(val)"}
            ]

### Get All Answers with Filtering [GET /research-sys/api/v1/answers/]
    
+ Parameters

        + id
            + answerHeaderId
            + questionId
            + questionnaireQuestionsId
            + questionNumber
            + answerNumber
            + answer

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","answerHeaderId": "(val)","questionId": "(val)","questionnaireQuestionsId": "(val)","questionNumber": "(val)","answerNumber": "(val)","answer": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","answerHeaderId": "(val)","questionId": "(val)","questionnaireQuestionsId": "(val)","questionNumber": "(val)","answerNumber": "(val)","answer": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Answers [GET /research-sys/api/v1/answers/]
	                                          
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
    
            {"columns":["id","answerHeaderId","questionId","questionnaireQuestionsId","questionNumber","answerNumber","answer"],"primaryKey":"id"}
		
### Get Blueprint API specification for Answers [GET /research-sys/api/v1/answers/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Answers.md"
            transfer-encoding:chunked


### Update Answers [PUT /research-sys/api/v1/answers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","answerHeaderId": "(val)","questionId": "(val)","questionnaireQuestionsId": "(val)","questionNumber": "(val)","answerNumber": "(val)","answer": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Answers [PUT /research-sys/api/v1/answers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","answerHeaderId": "(val)","questionId": "(val)","questionnaireQuestionsId": "(val)","questionNumber": "(val)","answerNumber": "(val)","answer": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","answerHeaderId": "(val)","questionId": "(val)","questionnaireQuestionsId": "(val)","questionNumber": "(val)","answerNumber": "(val)","answer": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Answers [POST /research-sys/api/v1/answers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","answerHeaderId": "(val)","questionId": "(val)","questionnaireQuestionsId": "(val)","questionNumber": "(val)","answerNumber": "(val)","answer": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","answerHeaderId": "(val)","questionId": "(val)","questionnaireQuestionsId": "(val)","questionNumber": "(val)","answerNumber": "(val)","answer": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Answers [POST /research-sys/api/v1/answers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","answerHeaderId": "(val)","questionId": "(val)","questionnaireQuestionsId": "(val)","questionNumber": "(val)","answerNumber": "(val)","answer": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","answerHeaderId": "(val)","questionId": "(val)","questionnaireQuestionsId": "(val)","questionNumber": "(val)","answerNumber": "(val)","answer": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","answerHeaderId": "(val)","questionId": "(val)","questionnaireQuestionsId": "(val)","questionNumber": "(val)","answerNumber": "(val)","answer": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","answerHeaderId": "(val)","questionId": "(val)","questionnaireQuestionsId": "(val)","questionNumber": "(val)","answerNumber": "(val)","answer": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Answers by Key [DELETE /research-sys/api/v1/answers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Answers [DELETE /research-sys/api/v1/answers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Answers with Matching [DELETE /research-sys/api/v1/answers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + answerHeaderId
            + questionId
            + questionnaireQuestionsId
            + questionNumber
            + answerNumber
            + answer

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
