## Questionnaire Questions [/research-sys/api/v1/questionnaire-questions/]

### Get Questionnaire Questions by Key [GET /research-sys/api/v1/questionnaire-questions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"}

### Get All Questionnaire Questions [GET /research-sys/api/v1/questionnaire-questions/]
	 
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

### Get All Questionnaire Questions with Filtering [GET /research-sys/api/v1/questionnaire-questions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + questionnaireId
            + questionId
            + questionNumber
            + parentQuestionNumber
            + conditionFlag
            + condition
            + conditionValue
            + questionSeqNumber
            + ruleId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Questionnaire Questions [GET /research-sys/api/v1/questionnaire-questions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Questionnaire Questions [GET /research-sys/api/v1/questionnaire-questions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Questionnaire Questions.md"
            transfer-encoding:chunked


### Update Questionnaire Questions [PUT /research-sys/api/v1/questionnaire-questions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Questionnaire Questions [PUT /research-sys/api/v1/questionnaire-questions/]

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

### Insert Questionnaire Questions [POST /research-sys/api/v1/questionnaire-questions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","questionnaireId": "(val)","questionId": "(val)","questionNumber": "(val)","parentQuestionNumber": "(val)","conditionFlag": "(val)","condition": "(val)","conditionValue": "(val)","questionSeqNumber": "(val)","ruleId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Questionnaire Questions [POST /research-sys/api/v1/questionnaire-questions/]

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
            
### Delete Questionnaire Questions by Key [DELETE /research-sys/api/v1/questionnaire-questions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Questionnaire Questions [DELETE /research-sys/api/v1/questionnaire-questions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Questionnaire Questions with Matching [DELETE /research-sys/api/v1/questionnaire-questions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + questionnaireId
            + questionId
            + questionNumber
            + parentQuestionNumber
            + conditionFlag
            + condition
            + conditionValue
            + questionSeqNumber
            + ruleId


+ Response 204
