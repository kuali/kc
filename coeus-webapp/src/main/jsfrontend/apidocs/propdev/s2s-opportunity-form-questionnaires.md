## S2s Opportunity Form Questionnaires [/propdev/api/v1/s2s-opportunity-form-questionnaires/]

### Get S2s Opportunity Form Questionnaires by Key [GET /propdev/api/v1/s2s-opportunity-form-questionnaires/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"}

### Get All S2s Opportunity Form Questionnaires [GET /propdev/api/v1/s2s-opportunity-form-questionnaires/]
	 
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

### Get All S2s Opportunity Form Questionnaires with Filtering [GET /propdev/api/v1/s2s-opportunity-form-questionnaires/]
    
+ Parameters

    + s2sOppFormQuestionnaireId (optional) - S2S Opportunity Form to Questionnaire Mapping Id. Maximum length is 12.
    + oppNameSpace (optional) - Namespace. Maximum length is 200.
    + formName (optional) - Form Name. Maximum length is 100.
    + questionnaireId (optional) - Questionnaire Ref Id. Maximum length is 10.

            
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
			
### Get Schema for S2s Opportunity Form Questionnaires [GET /propdev/api/v1/s2s-opportunity-form-questionnaires/]
	                                          
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
		
### Get Blueprint API specification for S2s Opportunity Form Questionnaires [GET /propdev/api/v1/s2s-opportunity-form-questionnaires/]
	 
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
### Update S2s Opportunity Form Questionnaires [PUT /propdev/api/v1/s2s-opportunity-form-questionnaires/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s Opportunity Form Questionnaires [PUT /propdev/api/v1/s2s-opportunity-form-questionnaires/]

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
### Insert S2s Opportunity Form Questionnaires [POST /propdev/api/v1/s2s-opportunity-form-questionnaires/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"s2sOppFormQuestionnaireId": "(val)","oppNameSpace": "(val)","formName": "(val)","questionnaireId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s Opportunity Form Questionnaires [POST /propdev/api/v1/s2s-opportunity-form-questionnaires/]

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
### Delete S2s Opportunity Form Questionnaires by Key [DELETE /propdev/api/v1/s2s-opportunity-form-questionnaires/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Opportunity Form Questionnaires [DELETE /propdev/api/v1/s2s-opportunity-form-questionnaires/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Opportunity Form Questionnaires with Matching [DELETE /propdev/api/v1/s2s-opportunity-form-questionnaires/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + s2sOppFormQuestionnaireId (optional) - S2S Opportunity Form to Questionnaire Mapping Id. Maximum length is 12.
    + oppNameSpace (optional) - Namespace. Maximum length is 200.
    + formName (optional) - Form Name. Maximum length is 100.
    + questionnaireId (optional) - Questionnaire Ref Id. Maximum length is 10.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
