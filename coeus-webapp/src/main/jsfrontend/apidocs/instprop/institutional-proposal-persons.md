## Institutional Proposal Persons [/research-sys/api/v1/institutional-proposal-persons/]

### Get Institutional Proposal Persons by Key [GET /research-sys/api/v1/institutional-proposal-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Persons [GET /research-sys/api/v1/institutional-proposal-persons/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Persons with Filtering [GET /research-sys/api/v1/institutional-proposal-persons/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + institutionalProposalContactId
            + personId
            + rolodexId
            + fullName
            + academicYearEffort
            + calendarYearEffort
            + summerEffort
            + totalEffort
            + faculty
            + roleCode
            + keyPersonRole
            + proposalId
            + proposalNumber
            + sequenceNumber
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Persons [GET /research-sys/api/v1/institutional-proposal-persons/]
	 
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
		
### Get Blueprint API specification for Institutional Proposal Persons [GET /research-sys/api/v1/institutional-proposal-persons/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Persons.md"
            transfer-encoding:chunked


### Update Institutional Proposal Persons [PUT /research-sys/api/v1/institutional-proposal-persons/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposal Persons [PUT /research-sys/api/v1/institutional-proposal-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Institutional Proposal Persons [POST /research-sys/api/v1/institutional-proposal-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposal Persons [POST /research-sys/api/v1/institutional-proposal-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Institutional Proposal Persons by Key [DELETE /research-sys/api/v1/institutional-proposal-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Persons [DELETE /research-sys/api/v1/institutional-proposal-persons/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Institutional Proposal Persons with Matching [DELETE /research-sys/api/v1/institutional-proposal-persons/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + institutionalProposalContactId
            + personId
            + rolodexId
            + fullName
            + academicYearEffort
            + calendarYearEffort
            + summerEffort
            + totalEffort
            + faculty
            + roleCode
            + keyPersonRole
            + proposalId
            + proposalNumber
            + sequenceNumber


+ Response 204
