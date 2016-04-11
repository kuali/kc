## Questionnaires [/research-sys/api/v1/questionnaires/]

### Get Questionnaires by Key [GET /research-sys/api/v1/questionnaires/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"}

### Get All Questionnaires [GET /research-sys/api/v1/questionnaires/]
	 
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

### Get All Questionnaires with Filtering [GET /research-sys/api/v1/questionnaires/]
    
+ Parameters

        + id
            + questionnaireSeqId
            + sequenceNumber
            + name
            + description
            + active
            + documentNumber
            + fileName
            + template

            
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
			
### Get Schema for Questionnaires [GET /research-sys/api/v1/questionnaires/]
	                                          
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
		
### Get Blueprint API specification for Questionnaires [GET /research-sys/api/v1/questionnaires/]
	 
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


### Update Questionnaires [PUT /research-sys/api/v1/questionnaires/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Questionnaires [PUT /research-sys/api/v1/questionnaires/]

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

### Insert Questionnaires [POST /research-sys/api/v1/questionnaires/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","questionnaireSeqId": "(val)","sequenceNumber": "(val)","name": "(val)","description": "(val)","active": "(val)","documentNumber": "(val)","fileName": "(val)","template": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Questionnaires [POST /research-sys/api/v1/questionnaires/]

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
            
### Delete Questionnaires by Key [DELETE /research-sys/api/v1/questionnaires/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Questionnaires [DELETE /research-sys/api/v1/questionnaires/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Questionnaires with Matching [DELETE /research-sys/api/v1/questionnaires/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + questionnaireSeqId
            + sequenceNumber
            + name
            + description
            + active
            + documentNumber
            + fileName
            + template

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
