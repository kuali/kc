## Institutional Proposal Persons [/instprop/api/v1/institutional-proposal-persons/]

### Get Institutional Proposal Persons by Key [GET /instprop/api/v1/institutional-proposal-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Persons [GET /instprop/api/v1/institutional-proposal-persons/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Persons with Filtering [GET /instprop/api/v1/institutional-proposal-persons/]
    
+ Parameters

    + institutionalProposalContactId (optional) - InstitutionalProposal Contact ID. Maximum length is 8.
    + personId (optional) - 
    + rolodexId (optional) - 
    + fullName (optional) - Full Name. Maximum length is 90.
    + academicYearEffort (optional) - Academic Year Effort. Maximum length is 7.
    + calendarYearEffort (optional) - Calendar Year Effort. Maximum length is 7.
    + summerEffort (optional) - Summer Effort. Maximum length is 7.
    + totalEffort (optional) - Total Effort. Maximum length is 7.
    + faculty (optional) - Faculty flag. Maximum length is 1.
    + roleCode (optional) - 
    + keyPersonRole (optional) - Project Role. Maximum length is 60.
    + proposalNumber (optional) - Institutional Proposal Number. Maximum length is 8.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + institutionalProposal.proposalId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Persons [GET /instprop/api/v1/institutional-proposal-persons/]
	                                          
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
    
            {"columns":["institutionalProposalContactId","personId","rolodexId","fullName","academicYearEffort","calendarYearEffort","summerEffort","totalEffort","faculty","roleCode","keyPersonRole","proposalNumber","sequenceNumber","institutionalProposal.proposalId"],"primaryKey":"institutionalProposalContactId"}
		
### Get Blueprint API specification for Institutional Proposal Persons [GET /instprop/api/v1/institutional-proposal-persons/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Persons.md"
            transfer-encoding:chunked
