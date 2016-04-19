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
    
            {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}

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
              {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Unit Contacts with Filtering [GET /instprop/api/v1/institutional-proposal-unit-contacts/]
    
+ Parameters

    + institutionalProposalContactId (optional) - 
    + personId (optional) - 
    + fullName (optional) - 
    + unitContactType (optional) - 
    + unitAdministratorTypeCode (optional) - Project Role. Maximum length is 3.
    + proposalId (optional) - 
    + proposalNumber (optional) - 
    + sequenceNumber (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
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
    
            {"columns":["institutionalProposalContactId","personId","fullName","unitContactType","unitAdministratorTypeCode","proposalId","proposalNumber","sequenceNumber"],"primaryKey":"institutionalProposalContactId"}
		
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


### Update Institutional Proposal Unit Contacts [PUT /instprop/api/v1/institutional-proposal-unit-contacts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposal Unit Contacts [PUT /instprop/api/v1/institutional-proposal-unit-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Institutional Proposal Unit Contacts [POST /instprop/api/v1/institutional-proposal-unit-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposal Unit Contacts [POST /instprop/api/v1/institutional-proposal-unit-contacts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"institutionalProposalContactId": "(val)","personId": "(val)","fullName": "(val)","unitContactType": "(val)","unitAdministratorTypeCode": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Institutional Proposal Unit Contacts by Key [DELETE /instprop/api/v1/institutional-proposal-unit-contacts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Unit Contacts [DELETE /instprop/api/v1/institutional-proposal-unit-contacts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Unit Contacts with Matching [DELETE /instprop/api/v1/institutional-proposal-unit-contacts/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + institutionalProposalContactId (optional) - 
    + personId (optional) - 
    + fullName (optional) - 
    + unitContactType (optional) - 
    + unitAdministratorTypeCode (optional) - Project Role. Maximum length is 3.
    + proposalId (optional) - 
    + proposalNumber (optional) - 
    + sequenceNumber (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
