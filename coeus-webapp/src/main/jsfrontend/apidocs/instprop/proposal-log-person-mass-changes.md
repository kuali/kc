## Proposal Log Person Mass Changes [/research-sys/api/v1/proposal-log-person-mass-changes/]

### Get Proposal Log Person Mass Changes by Key [GET /research-sys/api/v1/proposal-log-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"}

### Get All Proposal Log Person Mass Changes [GET /research-sys/api/v1/proposal-log-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"},
              {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Log Person Mass Changes with Filtering [GET /research-sys/api/v1/proposal-log-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + proposalLogPersonMassChangeId
            + personMassChangeId
            + principalInvestigator
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"},
              {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Log Person Mass Changes [GET /research-sys/api/v1/proposal-log-person-mass-changes/]
	 
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
		
### Get Blueprint API specification for Proposal Log Person Mass Changes [GET /research-sys/api/v1/proposal-log-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Log Person Mass Changes.md"
            transfer-encoding:chunked


### Update Proposal Log Person Mass Changes [PUT /research-sys/api/v1/proposal-log-person-mass-changes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Log Person Mass Changes [PUT /research-sys/api/v1/proposal-log-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"},
              {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Log Person Mass Changes [POST /research-sys/api/v1/proposal-log-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Log Person Mass Changes [POST /research-sys/api/v1/proposal-log-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"},
              {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"},
              {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Log Person Mass Changes by Key [DELETE /research-sys/api/v1/proposal-log-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Log Person Mass Changes [DELETE /research-sys/api/v1/proposal-log-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Log Person Mass Changes with Matching [DELETE /research-sys/api/v1/proposal-log-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + proposalLogPersonMassChangeId
            + personMassChangeId
            + principalInvestigator


+ Response 204
