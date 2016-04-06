## Rule Base Values [/research-sys/api/v1/rule-base-values/]

### Get Rule Base Values by Key [GET /research-sys/api/v1/rule-base-values/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","ruleTemplateId": "(val)","previousRuleId": "(val)","active": "(val)","description": "(val)","docTypeName": "(val)","documentId": "(val)","fromDateValue": "(val)","toDateValue": "(val)","deactivationDate": "(val)","currentInd": "(val)","versionNbr": "(val)","forceAction": "(val)","activationDate": "(val)","delegateRule": "(val)","templateRuleInd": "(val)","_primaryKey": "(val)"}

### Get All Rule Base Values [GET /research-sys/api/v1/rule-base-values/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","ruleTemplateId": "(val)","previousRuleId": "(val)","active": "(val)","description": "(val)","docTypeName": "(val)","documentId": "(val)","fromDateValue": "(val)","toDateValue": "(val)","deactivationDate": "(val)","currentInd": "(val)","versionNbr": "(val)","forceAction": "(val)","activationDate": "(val)","delegateRule": "(val)","templateRuleInd": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","ruleTemplateId": "(val)","previousRuleId": "(val)","active": "(val)","description": "(val)","docTypeName": "(val)","documentId": "(val)","fromDateValue": "(val)","toDateValue": "(val)","deactivationDate": "(val)","currentInd": "(val)","versionNbr": "(val)","forceAction": "(val)","activationDate": "(val)","delegateRule": "(val)","templateRuleInd": "(val)","_primaryKey": "(val)"}
            ]

### Get All Rule Base Values with Filtering [GET /research-sys/api/v1/rule-base-values/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + name
            + ruleTemplateId
            + previousRuleId
            + active
            + description
            + docTypeName
            + documentId
            + fromDateValue
            + toDateValue
            + deactivationDate
            + currentInd
            + versionNbr
            + forceAction
            + activationDate
            + delegateRule
            + templateRuleInd
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","ruleTemplateId": "(val)","previousRuleId": "(val)","active": "(val)","description": "(val)","docTypeName": "(val)","documentId": "(val)","fromDateValue": "(val)","toDateValue": "(val)","deactivationDate": "(val)","currentInd": "(val)","versionNbr": "(val)","forceAction": "(val)","activationDate": "(val)","delegateRule": "(val)","templateRuleInd": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","ruleTemplateId": "(val)","previousRuleId": "(val)","active": "(val)","description": "(val)","docTypeName": "(val)","documentId": "(val)","fromDateValue": "(val)","toDateValue": "(val)","deactivationDate": "(val)","currentInd": "(val)","versionNbr": "(val)","forceAction": "(val)","activationDate": "(val)","delegateRule": "(val)","templateRuleInd": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Rule Base Values [GET /research-sys/api/v1/rule-base-values/]
	 
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
		
### Get Blueprint API specification for Rule Base Values [GET /research-sys/api/v1/rule-base-values/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Rule Base Values.md"
            transfer-encoding:chunked


### Update Rule Base Values [PUT /research-sys/api/v1/rule-base-values/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","ruleTemplateId": "(val)","previousRuleId": "(val)","active": "(val)","description": "(val)","docTypeName": "(val)","documentId": "(val)","fromDateValue": "(val)","toDateValue": "(val)","deactivationDate": "(val)","currentInd": "(val)","versionNbr": "(val)","forceAction": "(val)","activationDate": "(val)","delegateRule": "(val)","templateRuleInd": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Rule Base Values [PUT /research-sys/api/v1/rule-base-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","ruleTemplateId": "(val)","previousRuleId": "(val)","active": "(val)","description": "(val)","docTypeName": "(val)","documentId": "(val)","fromDateValue": "(val)","toDateValue": "(val)","deactivationDate": "(val)","currentInd": "(val)","versionNbr": "(val)","forceAction": "(val)","activationDate": "(val)","delegateRule": "(val)","templateRuleInd": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","ruleTemplateId": "(val)","previousRuleId": "(val)","active": "(val)","description": "(val)","docTypeName": "(val)","documentId": "(val)","fromDateValue": "(val)","toDateValue": "(val)","deactivationDate": "(val)","currentInd": "(val)","versionNbr": "(val)","forceAction": "(val)","activationDate": "(val)","delegateRule": "(val)","templateRuleInd": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Rule Base Values [POST /research-sys/api/v1/rule-base-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","ruleTemplateId": "(val)","previousRuleId": "(val)","active": "(val)","description": "(val)","docTypeName": "(val)","documentId": "(val)","fromDateValue": "(val)","toDateValue": "(val)","deactivationDate": "(val)","currentInd": "(val)","versionNbr": "(val)","forceAction": "(val)","activationDate": "(val)","delegateRule": "(val)","templateRuleInd": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","ruleTemplateId": "(val)","previousRuleId": "(val)","active": "(val)","description": "(val)","docTypeName": "(val)","documentId": "(val)","fromDateValue": "(val)","toDateValue": "(val)","deactivationDate": "(val)","currentInd": "(val)","versionNbr": "(val)","forceAction": "(val)","activationDate": "(val)","delegateRule": "(val)","templateRuleInd": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Rule Base Values [POST /research-sys/api/v1/rule-base-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","ruleTemplateId": "(val)","previousRuleId": "(val)","active": "(val)","description": "(val)","docTypeName": "(val)","documentId": "(val)","fromDateValue": "(val)","toDateValue": "(val)","deactivationDate": "(val)","currentInd": "(val)","versionNbr": "(val)","forceAction": "(val)","activationDate": "(val)","delegateRule": "(val)","templateRuleInd": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","ruleTemplateId": "(val)","previousRuleId": "(val)","active": "(val)","description": "(val)","docTypeName": "(val)","documentId": "(val)","fromDateValue": "(val)","toDateValue": "(val)","deactivationDate": "(val)","currentInd": "(val)","versionNbr": "(val)","forceAction": "(val)","activationDate": "(val)","delegateRule": "(val)","templateRuleInd": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","ruleTemplateId": "(val)","previousRuleId": "(val)","active": "(val)","description": "(val)","docTypeName": "(val)","documentId": "(val)","fromDateValue": "(val)","toDateValue": "(val)","deactivationDate": "(val)","currentInd": "(val)","versionNbr": "(val)","forceAction": "(val)","activationDate": "(val)","delegateRule": "(val)","templateRuleInd": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","ruleTemplateId": "(val)","previousRuleId": "(val)","active": "(val)","description": "(val)","docTypeName": "(val)","documentId": "(val)","fromDateValue": "(val)","toDateValue": "(val)","deactivationDate": "(val)","currentInd": "(val)","versionNbr": "(val)","forceAction": "(val)","activationDate": "(val)","delegateRule": "(val)","templateRuleInd": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Rule Base Values by Key [DELETE /research-sys/api/v1/rule-base-values/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Rule Base Values [DELETE /research-sys/api/v1/rule-base-values/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Rule Base Values with Matching [DELETE /research-sys/api/v1/rule-base-values/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + name
            + ruleTemplateId
            + previousRuleId
            + active
            + description
            + docTypeName
            + documentId
            + fromDateValue
            + toDateValue
            + deactivationDate
            + currentInd
            + versionNbr
            + forceAction
            + activationDate
            + delegateRule
            + templateRuleInd


+ Response 204
