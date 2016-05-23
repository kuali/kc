## Questions [/research-common/api/v1/questions/]

### Get Questions by Key [GET /research-common/api/v1/questions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"}

### Get All Questions [GET /research-common/api/v1/questions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"}
            ]

### Get All Questions with Filtering [GET /research-common/api/v1/questions/]
    
+ Parameters

    + id (optional) - Question Ref Id. Maximum length is 12.
    + documentNumber (optional) - Document Number. Maximum length is 10.
    + questionSeqId (optional) - Question Id. Maximum length is 6.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + sequenceStatus (optional) - 
    + question (optional) - Question. Maximum length is 2000.
    + status (optional) - Status. Maximum length is 1.
    + categoryTypeCode (optional) - Category Type Code. Maximum length is 3.
    + questionTypeId (optional) - Question Type Id. Maximum length is 12.
    + lookupClass (optional) - Lookup Class. Maximum length is 100.
    + lookupReturn (optional) - Lookup Return. Maximum length is 30.
    + displayedAnswers (optional) - Displayed Answers. Maximum length is 2.
    + maxAnswers (optional) - Max Answers. Maximum length is 2.
    + answerMaxLength (optional) - Answer Max Length. Maximum length is 4.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Questions [GET /research-common/api/v1/questions/]
	                                          
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
    
            {"columns":["id","documentNumber","questionSeqId","sequenceNumber","sequenceStatus","question","status","categoryTypeCode","questionTypeId","lookupClass","lookupReturn","displayedAnswers","maxAnswers","answerMaxLength"],"primaryKey":"id"}
		
### Get Blueprint API specification for Questions [GET /research-common/api/v1/questions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Questions.md"
            transfer-encoding:chunked
### Update Questions [PUT /research-common/api/v1/questions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Questions [PUT /research-common/api/v1/questions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Questions [POST /research-common/api/v1/questions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Questions [POST /research-common/api/v1/questions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"}
            ]
### Delete Questions by Key [DELETE /research-common/api/v1/questions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Questions [DELETE /research-common/api/v1/questions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Questions with Matching [DELETE /research-common/api/v1/questions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Question Ref Id. Maximum length is 12.
    + documentNumber (optional) - Document Number. Maximum length is 10.
    + questionSeqId (optional) - Question Id. Maximum length is 6.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + sequenceStatus (optional) - 
    + question (optional) - Question. Maximum length is 2000.
    + status (optional) - Status. Maximum length is 1.
    + categoryTypeCode (optional) - Category Type Code. Maximum length is 3.
    + questionTypeId (optional) - Question Type Id. Maximum length is 12.
    + lookupClass (optional) - Lookup Class. Maximum length is 100.
    + lookupReturn (optional) - Lookup Return. Maximum length is 30.
    + displayedAnswers (optional) - Displayed Answers. Maximum length is 2.
    + maxAnswers (optional) - Max Answers. Maximum length is 2.
    + answerMaxLength (optional) - Answer Max Length. Maximum length is 4.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
