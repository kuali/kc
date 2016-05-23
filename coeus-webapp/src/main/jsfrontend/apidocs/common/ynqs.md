## Ynqs [/research-common/api/v1/ynqs/]

### Get Ynqs by Key [GET /research-common/api/v1/ynqs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}

### Get All Ynqs [GET /research-common/api/v1/ynqs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Ynqs with Filtering [GET /research-common/api/v1/ynqs/]
    
+ Parameters

    + questionId (optional) - Question Id. Maximum length is 4.
    + dateRequiredFor (optional) - Date Required For. Maximum length is 3.
    + description (optional) - Description. Maximum length is 400.
    + effectiveDate (optional) - Effective Date. Maximum length is 21.
    + explanationRequiredFor (optional) - Explanation Required For. Maximum length is 3.
    + groupName (optional) - Group Name. Maximum length is 150.
    + noOfAnswers (optional) - No Of Answers. Maximum length is 2.
    + questionType (optional) - Question Type. Maximum length is 1.
    + status (optional) - Status. Maximum length is 1.
    + sortId (optional) - Sort ID. Maximum length is 12.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Ynqs [GET /research-common/api/v1/ynqs/]
	                                          
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
    
            {"columns":["questionId","dateRequiredFor","description","effectiveDate","explanationRequiredFor","groupName","noOfAnswers","questionType","status","sortId"],"primaryKey":"questionId"}
		
### Get Blueprint API specification for Ynqs [GET /research-common/api/v1/ynqs/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Ynqs.md"
            transfer-encoding:chunked
### Update Ynqs [PUT /research-common/api/v1/ynqs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Ynqs [PUT /research-common/api/v1/ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Ynqs [POST /research-common/api/v1/ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Ynqs [POST /research-common/api/v1/ynqs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"questionId": "(val)","dateRequiredFor": "(val)","description": "(val)","effectiveDate": "(val)","explanationRequiredFor": "(val)","groupName": "(val)","noOfAnswers": "(val)","questionType": "(val)","status": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
### Delete Ynqs by Key [DELETE /research-common/api/v1/ynqs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Ynqs [DELETE /research-common/api/v1/ynqs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Ynqs with Matching [DELETE /research-common/api/v1/ynqs/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + questionId (optional) - Question Id. Maximum length is 4.
    + dateRequiredFor (optional) - Date Required For. Maximum length is 3.
    + description (optional) - Description. Maximum length is 400.
    + effectiveDate (optional) - Effective Date. Maximum length is 21.
    + explanationRequiredFor (optional) - Explanation Required For. Maximum length is 3.
    + groupName (optional) - Group Name. Maximum length is 150.
    + noOfAnswers (optional) - No Of Answers. Maximum length is 2.
    + questionType (optional) - Question Type. Maximum length is 1.
    + status (optional) - Status. Maximum length is 1.
    + sortId (optional) - Sort ID. Maximum length is 12.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
