## Questionnaire Usages [/research-common/api/v1/questionnaire-usages/]

### Get Questionnaire Usages by Key [GET /research-common/api/v1/questionnaire-usages/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","moduleItemCode": "(val)","moduleSubItemCode": "(val)","questionnaireSequenceNumber": "(val)","questionnaireId": "(val)","ruleId": "(val)","questionnaireLabel": "(val)","mandatory": "(val)","_primaryKey": "(val)"}

### Get All Questionnaire Usages [GET /research-common/api/v1/questionnaire-usages/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","moduleItemCode": "(val)","moduleSubItemCode": "(val)","questionnaireSequenceNumber": "(val)","questionnaireId": "(val)","ruleId": "(val)","questionnaireLabel": "(val)","mandatory": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","moduleItemCode": "(val)","moduleSubItemCode": "(val)","questionnaireSequenceNumber": "(val)","questionnaireId": "(val)","ruleId": "(val)","questionnaireLabel": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
            ]

### Get All Questionnaire Usages with Filtering [GET /research-common/api/v1/questionnaire-usages/]
    
+ Parameters

    + id (optional) - Questionnaire Usage Id. Maximum length is 12.
    + moduleItemCode (optional) - Module Item Code. Maximum length is 3.
    + moduleSubItemCode (optional) - Module Sub Item Code. Maximum length is 3.
    + questionnaireSequenceNumber (optional) - 
    + questionnaireId (optional) - Questionnaire Id. Maximum length is 6.
    + ruleId (optional) - Rule ID. Maximum length is 40.
    + questionnaireLabel (optional) - Questionnaire Label. Maximum length is 50.
    + mandatory (optional) - Is Mandatory. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","moduleItemCode": "(val)","moduleSubItemCode": "(val)","questionnaireSequenceNumber": "(val)","questionnaireId": "(val)","ruleId": "(val)","questionnaireLabel": "(val)","mandatory": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","moduleItemCode": "(val)","moduleSubItemCode": "(val)","questionnaireSequenceNumber": "(val)","questionnaireId": "(val)","ruleId": "(val)","questionnaireLabel": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Questionnaire Usages [GET /research-common/api/v1/questionnaire-usages/]
	                                          
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
    
            {"columns":["id","moduleItemCode","moduleSubItemCode","questionnaireSequenceNumber","questionnaireId","ruleId","questionnaireLabel","mandatory"],"primaryKey":"id"}
		
### Get Blueprint API specification for Questionnaire Usages [GET /research-common/api/v1/questionnaire-usages/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Questionnaire Usages.md"
            transfer-encoding:chunked
### Update Questionnaire Usages [PUT /research-common/api/v1/questionnaire-usages/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","moduleItemCode": "(val)","moduleSubItemCode": "(val)","questionnaireSequenceNumber": "(val)","questionnaireId": "(val)","ruleId": "(val)","questionnaireLabel": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Questionnaire Usages [PUT /research-common/api/v1/questionnaire-usages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","moduleItemCode": "(val)","moduleSubItemCode": "(val)","questionnaireSequenceNumber": "(val)","questionnaireId": "(val)","ruleId": "(val)","questionnaireLabel": "(val)","mandatory": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","moduleItemCode": "(val)","moduleSubItemCode": "(val)","questionnaireSequenceNumber": "(val)","questionnaireId": "(val)","ruleId": "(val)","questionnaireLabel": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Questionnaire Usages [POST /research-common/api/v1/questionnaire-usages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","moduleItemCode": "(val)","moduleSubItemCode": "(val)","questionnaireSequenceNumber": "(val)","questionnaireId": "(val)","ruleId": "(val)","questionnaireLabel": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","moduleItemCode": "(val)","moduleSubItemCode": "(val)","questionnaireSequenceNumber": "(val)","questionnaireId": "(val)","ruleId": "(val)","questionnaireLabel": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Questionnaire Usages [POST /research-common/api/v1/questionnaire-usages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","moduleItemCode": "(val)","moduleSubItemCode": "(val)","questionnaireSequenceNumber": "(val)","questionnaireId": "(val)","ruleId": "(val)","questionnaireLabel": "(val)","mandatory": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","moduleItemCode": "(val)","moduleSubItemCode": "(val)","questionnaireSequenceNumber": "(val)","questionnaireId": "(val)","ruleId": "(val)","questionnaireLabel": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","moduleItemCode": "(val)","moduleSubItemCode": "(val)","questionnaireSequenceNumber": "(val)","questionnaireId": "(val)","ruleId": "(val)","questionnaireLabel": "(val)","mandatory": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","moduleItemCode": "(val)","moduleSubItemCode": "(val)","questionnaireSequenceNumber": "(val)","questionnaireId": "(val)","ruleId": "(val)","questionnaireLabel": "(val)","mandatory": "(val)","_primaryKey": "(val)"}
            ]
### Delete Questionnaire Usages by Key [DELETE /research-common/api/v1/questionnaire-usages/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Questionnaire Usages [DELETE /research-common/api/v1/questionnaire-usages/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Questionnaire Usages with Matching [DELETE /research-common/api/v1/questionnaire-usages/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Questionnaire Usage Id. Maximum length is 12.
    + moduleItemCode (optional) - Module Item Code. Maximum length is 3.
    + moduleSubItemCode (optional) - Module Sub Item Code. Maximum length is 3.
    + questionnaireSequenceNumber (optional) - 
    + questionnaireId (optional) - Questionnaire Id. Maximum length is 6.
    + ruleId (optional) - Rule ID. Maximum length is 40.
    + questionnaireLabel (optional) - Questionnaire Label. Maximum length is 50.
    + mandatory (optional) - Is Mandatory. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
