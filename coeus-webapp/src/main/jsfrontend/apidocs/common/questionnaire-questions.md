## Questionnaire Questions [/research-common/api/v1/questionnaire-questions/]

### Get Questionnaire Questions by Key [GET /research-common/api/v1/questionnaire-questions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"}

### Get All Questionnaire Questions [GET /research-common/api/v1/questionnaire-questions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Questionnaire Questions with Filtering [GET /research-common/api/v1/questionnaire-questions/]
    
+ Parameters

    + id (optional) - Questionnaire Questions Id. Maximum length is 12.
    + questionnaireId (optional) - Questionnaire Id. Maximum length is 10.
    + questionId (optional) - Question Ref Id. Maximum length is 6.
    + questionNumber (optional) - Question Number. Maximum length is 6.
    + parentQuestionNumber (optional) - Parent Question Number. Maximum length is 6.
    + conditionFlag (optional) - Condition Flag. Maximum length is 1.
    + condition (optional) - Condition. Maximum length is 50.
    + conditionValue (optional) - Condition Value. Maximum length is 2000.
    + questionSeqNumber (optional) - Question Seq Number. Maximum length is 3.
    + ruleId (optional) - Question Seq Number. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Questionnaire Questions [GET /research-common/api/v1/questionnaire-questions/]
	                                          
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
    
            {"columns":["id","questionnaireId","questionId","questionNumber","parentQuestionNumber","conditionFlag","condition","conditionValue","questionSeqNumber","ruleId"],"primaryKey":"id"}
		
### Get Blueprint API specification for Questionnaire Questions [GET /research-common/api/v1/questionnaire-questions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Questionnaire Questions.md"
            transfer-encoding:chunked


### Update Questionnaire Questions [PUT /research-common/api/v1/questionnaire-questions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Questionnaire Questions [PUT /research-common/api/v1/questionnaire-questions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Questionnaire Questions [POST /research-common/api/v1/questionnaire-questions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Questionnaire Questions [POST /research-common/api/v1/questionnaire-questions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Questionnaire Questions by Key [DELETE /research-common/api/v1/questionnaire-questions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Questionnaire Questions [DELETE /research-common/api/v1/questionnaire-questions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Questionnaire Questions with Matching [DELETE /research-common/api/v1/questionnaire-questions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Questionnaire Questions Id. Maximum length is 12.
    + questionnaireId (optional) - Questionnaire Id. Maximum length is 10.
    + questionId (optional) - Question Ref Id. Maximum length is 6.
    + questionNumber (optional) - Question Number. Maximum length is 6.
    + parentQuestionNumber (optional) - Parent Question Number. Maximum length is 6.
    + conditionFlag (optional) - Condition Flag. Maximum length is 1.
    + condition (optional) - Condition. Maximum length is 50.
    + conditionValue (optional) - Condition Value. Maximum length is 2000.
    + questionSeqNumber (optional) - Question Seq Number. Maximum length is 3.
    + ruleId (optional) - Question Seq Number. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
