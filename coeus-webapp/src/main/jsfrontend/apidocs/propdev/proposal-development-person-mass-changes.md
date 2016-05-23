## Proposal Development Person Mass Changes [/propdev/api/v1/proposal-development-person-mass-changes/]

### Get Proposal Development Person Mass Changes by Key [GET /propdev/api/v1/proposal-development-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalDevelopmentPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","mailingInformation": "(val)","keyStudyPerson": "(val)","_primaryKey": "(val)"}

### Get All Proposal Development Person Mass Changes [GET /propdev/api/v1/proposal-development-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalDevelopmentPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","mailingInformation": "(val)","keyStudyPerson": "(val)","_primaryKey": "(val)"},
              {"proposalDevelopmentPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","mailingInformation": "(val)","keyStudyPerson": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Development Person Mass Changes with Filtering [GET /propdev/api/v1/proposal-development-person-mass-changes/]
    
+ Parameters

    + proposalDevelopmentPersonMassChangeId (optional) - Proposal Development Person Mass Change Id. Maximum length is 12.
    + personMassChangeId (optional) - Person Mass Change Id. Maximum length is 12.
    + investigator (optional) - Investigator. Maximum length is 1.
    + mailingInformation (optional) - Mailing Information. Maximum length is 1.
    + keyStudyPerson (optional) - Key Study Person. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalDevelopmentPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","mailingInformation": "(val)","keyStudyPerson": "(val)","_primaryKey": "(val)"},
              {"proposalDevelopmentPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","mailingInformation": "(val)","keyStudyPerson": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Development Person Mass Changes [GET /propdev/api/v1/proposal-development-person-mass-changes/]
	                                          
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
    
            {"columns":["proposalDevelopmentPersonMassChangeId","personMassChangeId","investigator","mailingInformation","keyStudyPerson"],"primaryKey":"proposalDevelopmentPersonMassChangeId"}
		
### Get Blueprint API specification for Proposal Development Person Mass Changes [GET /propdev/api/v1/proposal-development-person-mass-changes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Development Person Mass Changes.md"
            transfer-encoding:chunked
### Update Proposal Development Person Mass Changes [PUT /propdev/api/v1/proposal-development-person-mass-changes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalDevelopmentPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","mailingInformation": "(val)","keyStudyPerson": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Development Person Mass Changes [PUT /propdev/api/v1/proposal-development-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalDevelopmentPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","mailingInformation": "(val)","keyStudyPerson": "(val)","_primaryKey": "(val)"},
              {"proposalDevelopmentPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","mailingInformation": "(val)","keyStudyPerson": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Proposal Development Person Mass Changes [POST /propdev/api/v1/proposal-development-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalDevelopmentPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","mailingInformation": "(val)","keyStudyPerson": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalDevelopmentPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","mailingInformation": "(val)","keyStudyPerson": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Development Person Mass Changes [POST /propdev/api/v1/proposal-development-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalDevelopmentPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","mailingInformation": "(val)","keyStudyPerson": "(val)","_primaryKey": "(val)"},
              {"proposalDevelopmentPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","mailingInformation": "(val)","keyStudyPerson": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalDevelopmentPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","mailingInformation": "(val)","keyStudyPerson": "(val)","_primaryKey": "(val)"},
              {"proposalDevelopmentPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","mailingInformation": "(val)","keyStudyPerson": "(val)","_primaryKey": "(val)"}
            ]
### Delete Proposal Development Person Mass Changes by Key [DELETE /propdev/api/v1/proposal-development-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Development Person Mass Changes [DELETE /propdev/api/v1/proposal-development-person-mass-changes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Development Person Mass Changes with Matching [DELETE /propdev/api/v1/proposal-development-person-mass-changes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalDevelopmentPersonMassChangeId (optional) - Proposal Development Person Mass Change Id. Maximum length is 12.
    + personMassChangeId (optional) - Person Mass Change Id. Maximum length is 12.
    + investigator (optional) - Investigator. Maximum length is 1.
    + mailingInformation (optional) - Mailing Information. Maximum length is 1.
    + keyStudyPerson (optional) - Key Study Person. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
