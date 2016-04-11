## Questions [/research-sys/api/v1/questions/]

### Get Questions by Key [GET /research-sys/api/v1/questions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"}

### Get All Questions [GET /research-sys/api/v1/questions/]
	 
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

### Get All Questions with Filtering [GET /research-sys/api/v1/questions/]
    
+ Parameters

        + id
            + documentNumber
            + questionSeqId
            + sequenceNumber
            + sequenceStatus
            + question
            + status
            + categoryTypeCode
            + questionTypeId
            + lookupClass
            + lookupReturn
            + displayedAnswers
            + maxAnswers
            + answerMaxLength

            
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
			
### Get Schema for Questions [GET /research-sys/api/v1/questions/]
	                                          
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
		
### Get Blueprint API specification for Questions [GET /research-sys/api/v1/questions/]
	 
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


### Update Questions [PUT /research-sys/api/v1/questions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Questions [PUT /research-sys/api/v1/questions/]

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

### Insert Questions [POST /research-sys/api/v1/questions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","documentNumber": "(val)","questionSeqId": "(val)","sequenceNumber": "(val)","sequenceStatus": "(val)","question": "(val)","status": "(val)","categoryTypeCode": "(val)","questionTypeId": "(val)","lookupClass": "(val)","lookupReturn": "(val)","displayedAnswers": "(val)","maxAnswers": "(val)","answerMaxLength": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Questions [POST /research-sys/api/v1/questions/]

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
            
### Delete Questions by Key [DELETE /research-sys/api/v1/questions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Questions [DELETE /research-sys/api/v1/questions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Questions with Matching [DELETE /research-sys/api/v1/questions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + documentNumber
            + questionSeqId
            + sequenceNumber
            + sequenceStatus
            + question
            + status
            + categoryTypeCode
            + questionTypeId
            + lookupClass
            + lookupReturn
            + displayedAnswers
            + maxAnswers
            + answerMaxLength

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
