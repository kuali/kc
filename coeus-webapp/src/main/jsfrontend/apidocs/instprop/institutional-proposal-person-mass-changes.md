## Institutional Proposal Person Mass Changes [/research-sys/api/v1/institutional-proposal-person-mass-changes/]

### Get Institutional Proposal Person Mass Changes by Key [GET /research-sys/api/v1/institutional-proposal-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"institutionalProposalPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","mailingInformation": "(val)","ipReviewer": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Person Mass Changes [GET /research-sys/api/v1/institutional-proposal-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"institutionalProposalPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","mailingInformation": "(val)","ipReviewer": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","mailingInformation": "(val)","ipReviewer": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Person Mass Changes with Filtering [GET /research-sys/api/v1/institutional-proposal-person-mass-changes/]
    
+ Parameters

        + institutionalProposalPersonMassChangeId
            + personMassChangeId
            + investigator
            + keyStudyPerson
            + unitContact
            + mailingInformation
            + ipReviewer

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"institutionalProposalPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","mailingInformation": "(val)","ipReviewer": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","mailingInformation": "(val)","ipReviewer": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Person Mass Changes [GET /research-sys/api/v1/institutional-proposal-person-mass-changes/]
	                                          
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
    
            {"columns":["institutionalProposalPersonMassChangeId","personMassChangeId","investigator","keyStudyPerson","unitContact","mailingInformation","ipReviewer"],"primaryKey":"institutionalProposalPersonMassChangeId"}
		
### Get Blueprint API specification for Institutional Proposal Person Mass Changes [GET /research-sys/api/v1/institutional-proposal-person-mass-changes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Person Mass Changes.md"
            transfer-encoding:chunked


### Update Institutional Proposal Person Mass Changes [PUT /research-sys/api/v1/institutional-proposal-person-mass-changes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"institutionalProposalPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","mailingInformation": "(val)","ipReviewer": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposal Person Mass Changes [PUT /research-sys/api/v1/institutional-proposal-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"institutionalProposalPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","mailingInformation": "(val)","ipReviewer": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","mailingInformation": "(val)","ipReviewer": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Institutional Proposal Person Mass Changes [POST /research-sys/api/v1/institutional-proposal-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"institutionalProposalPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","mailingInformation": "(val)","ipReviewer": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"institutionalProposalPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","mailingInformation": "(val)","ipReviewer": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposal Person Mass Changes [POST /research-sys/api/v1/institutional-proposal-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"institutionalProposalPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","mailingInformation": "(val)","ipReviewer": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","mailingInformation": "(val)","ipReviewer": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"institutionalProposalPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","mailingInformation": "(val)","ipReviewer": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalPersonMassChangeId": "(val)","personMassChangeId": "(val)","investigator": "(val)","keyStudyPerson": "(val)","unitContact": "(val)","mailingInformation": "(val)","ipReviewer": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Institutional Proposal Person Mass Changes by Key [DELETE /research-sys/api/v1/institutional-proposal-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Person Mass Changes [DELETE /research-sys/api/v1/institutional-proposal-person-mass-changes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Person Mass Changes with Matching [DELETE /research-sys/api/v1/institutional-proposal-person-mass-changes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + institutionalProposalPersonMassChangeId
            + personMassChangeId
            + investigator
            + keyStudyPerson
            + unitContact
            + mailingInformation
            + ipReviewer

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
