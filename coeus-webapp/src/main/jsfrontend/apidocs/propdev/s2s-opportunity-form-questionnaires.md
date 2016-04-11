## S2s Opportunity Form Questionnaires [/research-sys/api/v1/s2s-opportunity-form-questionnaires/]

### Get S2s Opportunity Form Questionnaires by Key [GET /research-sys/api/v1/s2s-opportunity-form-questionnaires/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"}

### Get All S2s Opportunity Form Questionnaires [GET /research-sys/api/v1/s2s-opportunity-form-questionnaires/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"},
              {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"}
            ]

### Get All S2s Opportunity Form Questionnaires with Filtering [GET /research-sys/api/v1/s2s-opportunity-form-questionnaires/]
    
+ Parameters

        + s2sOppFormQuestionnaireId
            + oppNameSpace
            + formName
            + questionnaireId

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"},
              {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for S2s Opportunity Form Questionnaires [GET /research-sys/api/v1/s2s-opportunity-form-questionnaires/]
	                                          
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
    
            {"columns":["s2sOppFormQuestionnaireId","oppNameSpace","formName","questionnaireId"],"primaryKey":"s2sOppFormQuestionnaireId"}
		
### Get Blueprint API specification for S2s Opportunity Form Questionnaires [GET /research-sys/api/v1/s2s-opportunity-form-questionnaires/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="S2s Opportunity Form Questionnaires.md"
            transfer-encoding:chunked


### Update S2s Opportunity Form Questionnaires [PUT /research-sys/api/v1/s2s-opportunity-form-questionnaires/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s Opportunity Form Questionnaires [PUT /research-sys/api/v1/s2s-opportunity-form-questionnaires/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"},
              {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert S2s Opportunity Form Questionnaires [POST /research-sys/api/v1/s2s-opportunity-form-questionnaires/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s Opportunity Form Questionnaires [POST /research-sys/api/v1/s2s-opportunity-form-questionnaires/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"},
              {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"},
              {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete S2s Opportunity Form Questionnaires by Key [DELETE /research-sys/api/v1/s2s-opportunity-form-questionnaires/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Opportunity Form Questionnaires [DELETE /research-sys/api/v1/s2s-opportunity-form-questionnaires/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Opportunity Form Questionnaires with Matching [DELETE /research-sys/api/v1/s2s-opportunity-form-questionnaires/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + s2sOppFormQuestionnaireId
            + oppNameSpace
            + formName
            + questionnaireId

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
