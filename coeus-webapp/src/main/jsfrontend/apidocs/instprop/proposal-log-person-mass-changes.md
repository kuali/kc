## Proposal Log Person Mass Changes [/instprop/api/v1/proposal-log-person-mass-changes/]

### Get Proposal Log Person Mass Changes by Key [GET /instprop/api/v1/proposal-log-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"}

### Get All Proposal Log Person Mass Changes [GET /instprop/api/v1/proposal-log-person-mass-changes/]
	 
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

### Get All Proposal Log Person Mass Changes with Filtering [GET /instprop/api/v1/proposal-log-person-mass-changes/]
    
+ Parameters

    + proposalLogPersonMassChangeId (optional) - Proposal Log Person Mass Change Id. Maximum length is 12.
    + personMassChangeId (optional) - Person Mass Change Id. Maximum length is 12.
    + principalInvestigator (optional) - Principal Investigator. Maximum length is 1.

            
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
			
### Get Schema for Proposal Log Person Mass Changes [GET /instprop/api/v1/proposal-log-person-mass-changes/]
	                                          
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
    
            {"columns":["proposalLogPersonMassChangeId","personMassChangeId","principalInvestigator"],"primaryKey":"proposalLogPersonMassChangeId"}
		
### Get Blueprint API specification for Proposal Log Person Mass Changes [GET /instprop/api/v1/proposal-log-person-mass-changes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Log Person Mass Changes.md"
            transfer-encoding:chunked
### Update Proposal Log Person Mass Changes [PUT /instprop/api/v1/proposal-log-person-mass-changes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Log Person Mass Changes [PUT /instprop/api/v1/proposal-log-person-mass-changes/]

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
### Insert Proposal Log Person Mass Changes [POST /instprop/api/v1/proposal-log-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalLogPersonMassChangeId": "(val)","personMassChangeId": "(val)","principalInvestigator": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Log Person Mass Changes [POST /instprop/api/v1/proposal-log-person-mass-changes/]

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
### Delete Proposal Log Person Mass Changes by Key [DELETE /instprop/api/v1/proposal-log-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Log Person Mass Changes [DELETE /instprop/api/v1/proposal-log-person-mass-changes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Log Person Mass Changes with Matching [DELETE /instprop/api/v1/proposal-log-person-mass-changes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalLogPersonMassChangeId (optional) - Proposal Log Person Mass Change Id. Maximum length is 12.
    + personMassChangeId (optional) - Person Mass Change Id. Maximum length is 12.
    + principalInvestigator (optional) - Principal Investigator. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
