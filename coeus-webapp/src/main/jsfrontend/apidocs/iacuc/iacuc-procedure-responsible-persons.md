## Iacuc Procedure Responsible Persons [/iacuc/api/v1/iacuc-procedure-responsible-persons/]

### Get Iacuc Procedure Responsible Persons by Key [GET /iacuc/api/v1/iacuc-procedure-responsible-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucProcedurePersonResponsibleId": "(val)","iacucProtocolStudyGroupId": "(val)","protocolPersonId": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Procedure Responsible Persons [GET /iacuc/api/v1/iacuc-procedure-responsible-persons/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProcedurePersonResponsibleId": "(val)","iacucProtocolStudyGroupId": "(val)","protocolPersonId": "(val)","_primaryKey": "(val)"},
              {"iacucProcedurePersonResponsibleId": "(val)","iacucProtocolStudyGroupId": "(val)","protocolPersonId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Procedure Responsible Persons with Filtering [GET /iacuc/api/v1/iacuc-procedure-responsible-persons/]
    
+ Parameters

    + iacucProcedurePersonResponsibleId (optional) - Procedure Person Responsible Id. Maximum length is 22.
    + iacucProtocolStudyGroupId (optional) - Protocol Study Group Id. Maximum length is 22.
    + protocolPersonId (optional) - IACUC Protocol Person Id. Maximum length is 12.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProcedurePersonResponsibleId": "(val)","iacucProtocolStudyGroupId": "(val)","protocolPersonId": "(val)","_primaryKey": "(val)"},
              {"iacucProcedurePersonResponsibleId": "(val)","iacucProtocolStudyGroupId": "(val)","protocolPersonId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Procedure Responsible Persons [GET /iacuc/api/v1/iacuc-procedure-responsible-persons/]
	                                          
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
    
            {"columns":["iacucProcedurePersonResponsibleId","iacucProtocolStudyGroupId","protocolPersonId"],"primaryKey":"iacucProcedurePersonResponsibleId"}
		
### Get Blueprint API specification for Iacuc Procedure Responsible Persons [GET /iacuc/api/v1/iacuc-procedure-responsible-persons/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Procedure Responsible Persons.md"
            transfer-encoding:chunked
### Update Iacuc Procedure Responsible Persons [PUT /iacuc/api/v1/iacuc-procedure-responsible-persons/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProcedurePersonResponsibleId": "(val)","iacucProtocolStudyGroupId": "(val)","protocolPersonId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Procedure Responsible Persons [PUT /iacuc/api/v1/iacuc-procedure-responsible-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProcedurePersonResponsibleId": "(val)","iacucProtocolStudyGroupId": "(val)","protocolPersonId": "(val)","_primaryKey": "(val)"},
              {"iacucProcedurePersonResponsibleId": "(val)","iacucProtocolStudyGroupId": "(val)","protocolPersonId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Procedure Responsible Persons [POST /iacuc/api/v1/iacuc-procedure-responsible-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProcedurePersonResponsibleId": "(val)","iacucProtocolStudyGroupId": "(val)","protocolPersonId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucProcedurePersonResponsibleId": "(val)","iacucProtocolStudyGroupId": "(val)","protocolPersonId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Procedure Responsible Persons [POST /iacuc/api/v1/iacuc-procedure-responsible-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProcedurePersonResponsibleId": "(val)","iacucProtocolStudyGroupId": "(val)","protocolPersonId": "(val)","_primaryKey": "(val)"},
              {"iacucProcedurePersonResponsibleId": "(val)","iacucProtocolStudyGroupId": "(val)","protocolPersonId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"iacucProcedurePersonResponsibleId": "(val)","iacucProtocolStudyGroupId": "(val)","protocolPersonId": "(val)","_primaryKey": "(val)"},
              {"iacucProcedurePersonResponsibleId": "(val)","iacucProtocolStudyGroupId": "(val)","protocolPersonId": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Procedure Responsible Persons by Key [DELETE /iacuc/api/v1/iacuc-procedure-responsible-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Procedure Responsible Persons [DELETE /iacuc/api/v1/iacuc-procedure-responsible-persons/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Procedure Responsible Persons with Matching [DELETE /iacuc/api/v1/iacuc-procedure-responsible-persons/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + iacucProcedurePersonResponsibleId (optional) - Procedure Person Responsible Id. Maximum length is 22.
    + iacucProtocolStudyGroupId (optional) - Protocol Study Group Id. Maximum length is 22.
    + protocolPersonId (optional) - IACUC Protocol Person Id. Maximum length is 12.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
