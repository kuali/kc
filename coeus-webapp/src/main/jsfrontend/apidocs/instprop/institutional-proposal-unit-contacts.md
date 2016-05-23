## Institutional Proposal Unit Contacts [/instprop/api/v1/institutional-proposal-unit-contacts/]

### Get Institutional Proposal Unit Contacts by Key [GET /instprop/api/v1/institutional-proposal-unit-contacts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Unit Contacts [GET /instprop/api/v1/institutional-proposal-unit-contacts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Unit Contacts with Filtering [GET /instprop/api/v1/institutional-proposal-unit-contacts/]
    
+ Parameters

    + institutionalProposalContactId (optional) - 
    + personId (optional) - 
    + fullName (optional) - 
    + unitContactType (optional) - 
    + unitAdministratorTypeCode (optional) - Project Role. Maximum length is 3.
    + proposalNumber (optional) - 
    + sequenceNumber (optional) - 
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
              {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Unit Contacts [GET /instprop/api/v1/institutional-proposal-unit-contacts/]
	                                          
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
    
            {"columns":["institutionalProposalContactId","personId","fullName","unitContactType","unitAdministratorTypeCode","proposalNumber","sequenceNumber","institutionalProposal.proposalId"],"primaryKey":"institutionalProposalContactId"}
		
### Get Blueprint API specification for Institutional Proposal Unit Contacts [GET /instprop/api/v1/institutional-proposal-unit-contacts/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Unit Contacts.md"
            transfer-encoding:chunked
