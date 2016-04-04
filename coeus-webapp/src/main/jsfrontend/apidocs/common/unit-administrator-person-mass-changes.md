## Unit Administrator Person Mass Changes [/research-sys/api/v1/unit-administrator-person-mass-changes/]

### Get Unit Administrator Person Mass Changes by Key [GET /research-sys/api/v1/unit-administrator-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"}

### Get All Unit Administrator Person Mass Changes [GET /research-sys/api/v1/unit-administrator-person-mass-changes/]
	 
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

### Get All Unit Administrator Person Mass Changes with Filtering [GET /research-sys/api/v1/unit-administrator-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + unitAdministratorPersonMassChangeId
            + personMassChangeId
            + administrativeOfficer
            + ospAdministrator
            + unitHead
            + deanVP
            + otherIndividualToNotify
            + administrativeContact
            + financialContact
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"},
              {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Unit Administrator Person Mass Changes [GET /research-sys/api/v1/unit-administrator-person-mass-changes/]
	 
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
		
### Get Blueprint API specification for Unit Administrator Person Mass Changes [GET /research-sys/api/v1/unit-administrator-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Unit Administrator Person Mass Changes.md"
            transfer-encoding:chunked


### Update Unit Administrator Person Mass Changes [PUT /research-sys/api/v1/unit-administrator-person-mass-changes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Unit Administrator Person Mass Changes [PUT /research-sys/api/v1/unit-administrator-person-mass-changes/]

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

### Insert Unit Administrator Person Mass Changes [POST /research-sys/api/v1/unit-administrator-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"unitAdministratorPersonMassChangeId": "(val)","personMassChangeId": "(val)","administrativeOfficer": "(val)","ospAdministrator": "(val)","unitHead": "(val)","deanVP": "(val)","otherIndividualToNotify": "(val)","administrativeContact": "(val)","financialContact": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Unit Administrator Person Mass Changes [POST /research-sys/api/v1/unit-administrator-person-mass-changes/]

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
            
### Delete Unit Administrator Person Mass Changes by Key [DELETE /research-sys/api/v1/unit-administrator-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Unit Administrator Person Mass Changes [DELETE /research-sys/api/v1/unit-administrator-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Unit Administrator Person Mass Changes with Matching [DELETE /research-sys/api/v1/unit-administrator-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + unitAdministratorPersonMassChangeId
            + personMassChangeId
            + administrativeOfficer
            + ospAdministrator
            + unitHead
            + deanVP
            + otherIndividualToNotify
            + administrativeContact
            + financialContact


+ Response 204
