## Unit Administrator Person Mass Changes [/research-common/api/v1/unit-administrator-person-mass-changes/]

### Get Unit Administrator Person Mass Changes by Key [GET /research-common/api/v1/unit-administrator-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"}

### Get All Unit Administrator Person Mass Changes [GET /research-common/api/v1/unit-administrator-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"},
              {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"}
            ]

### Get All Unit Administrator Person Mass Changes with Filtering [GET /research-common/api/v1/unit-administrator-person-mass-changes/]
    
+ Parameters

    + unitAdministratorPersonMassChangeId (optional) - Unit Administrator Person Mass Change Id. Maximum length is 12.
    + personMassChangeId (optional) - Person Mass Change Id. Maximum length is 12.
    + administrativeOfficer (optional) - Administrative Officer. Maximum length is 1.
    + ospAdministrator (optional) - OSP Administrator. Maximum length is 1.
    + unitHead (optional) - Unit Head. Maximum length is 1.
    + deanVP (optional) - Dean VP. Maximum length is 1.
    + otherIndividualToNotify (optional) - Other Individual to Notify. Maximum length is 1.
    + administrativeContact (optional) - Administrative Contact. Maximum length is 1.
    + financialContact (optional) - Financial Contact. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"},
              {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Unit Administrator Person Mass Changes [GET /research-common/api/v1/unit-administrator-person-mass-changes/]
	                                          
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
    
            {"columns":["unitAdministratorPersonMassChangeId","personMassChangeId","administrativeOfficer","ospAdministrator","unitHead","deanVP","otherIndividualToNotify","administrativeContact","financialContact"],"primaryKey":"unitAdministratorPersonMassChangeId"}
		
### Get Blueprint API specification for Unit Administrator Person Mass Changes [GET /research-common/api/v1/unit-administrator-person-mass-changes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Unit Administrator Person Mass Changes.md"
            transfer-encoding:chunked
### Update Unit Administrator Person Mass Changes [PUT /research-common/api/v1/unit-administrator-person-mass-changes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Unit Administrator Person Mass Changes [PUT /research-common/api/v1/unit-administrator-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"},
              {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Unit Administrator Person Mass Changes [POST /research-common/api/v1/unit-administrator-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Unit Administrator Person Mass Changes [POST /research-common/api/v1/unit-administrator-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"},
              {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"},
              {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"}
            ]
### Delete Unit Administrator Person Mass Changes by Key [DELETE /research-common/api/v1/unit-administrator-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Unit Administrator Person Mass Changes [DELETE /research-common/api/v1/unit-administrator-person-mass-changes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Unit Administrator Person Mass Changes with Matching [DELETE /research-common/api/v1/unit-administrator-person-mass-changes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + unitAdministratorPersonMassChangeId (optional) - Unit Administrator Person Mass Change Id. Maximum length is 12.
    + personMassChangeId (optional) - Person Mass Change Id. Maximum length is 12.
    + administrativeOfficer (optional) - Administrative Officer. Maximum length is 1.
    + ospAdministrator (optional) - OSP Administrator. Maximum length is 1.
    + unitHead (optional) - Unit Head. Maximum length is 1.
    + deanVP (optional) - Dean VP. Maximum length is 1.
    + otherIndividualToNotify (optional) - Other Individual to Notify. Maximum length is 1.
    + administrativeContact (optional) - Administrative Contact. Maximum length is 1.
    + financialContact (optional) - Financial Contact. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
