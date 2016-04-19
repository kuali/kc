## Questionnaires [/research-common/api/v1/questionnaires/]

### Get Questionnaires by Key [GET /research-common/api/v1/questionnaires/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"}

### Get All Questionnaires [GET /research-common/api/v1/questionnaires/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"}
            ]

### Get All Questionnaires with Filtering [GET /research-common/api/v1/questionnaires/]
    
+ Parameters

    + id (optional) - Questionnaire Ref Id. Maximum length is 10.
    + questionnaireSeqId (optional) - Questionnaire Id. Maximum length is 6.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + name (optional) - Name. Maximum length is 50.
    + description (optional) - Description. Maximum length is 2000.
    + active (optional) - Is Active. Maximum length is 1.
    + documentNumber (optional) - Document Number. Maximum length is 10.
    + fileName (optional) - Template Name. Maximum length is 1000.
    + template (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Questionnaires [GET /research-common/api/v1/questionnaires/]
	                                          
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
    
            {"columns":["id","questionnaireSeqId","sequenceNumber","name","description","active","documentNumber","fileName","template"],"primaryKey":"id"}
		
### Get Blueprint API specification for Questionnaires [GET /research-common/api/v1/questionnaires/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Questionnaires.md"
            transfer-encoding:chunked


### Update Questionnaires [PUT /research-common/api/v1/questionnaires/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Questionnaires [PUT /research-common/api/v1/questionnaires/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Questionnaires [POST /research-common/api/v1/questionnaires/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Questionnaires [POST /research-common/api/v1/questionnaires/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Questionnaires by Key [DELETE /research-common/api/v1/questionnaires/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Questionnaires [DELETE /research-common/api/v1/questionnaires/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Questionnaires with Matching [DELETE /research-common/api/v1/questionnaires/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Questionnaire Ref Id. Maximum length is 10.
    + questionnaireSeqId (optional) - Questionnaire Id. Maximum length is 6.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + name (optional) - Name. Maximum length is 50.
    + description (optional) - Description. Maximum length is 2000.
    + active (optional) - Is Active. Maximum length is 1.
    + documentNumber (optional) - Document Number. Maximum length is 10.
    + fileName (optional) - Template Name. Maximum length is 1000.
    + template (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
