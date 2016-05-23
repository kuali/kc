## Proposal Logs [/instprop/api/v1/proposal-logs/]

### Get Proposal Logs by Key [GET /instprop/api/v1/proposal-logs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"}

### Get All Proposal Logs [GET /instprop/api/v1/proposal-logs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Logs with Filtering [GET /instprop/api/v1/proposal-logs/]
    
+ Parameters

    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + proposalTypeCode (optional) - Proposal Type Code. Maximum length is 3.
    + proposalLogTypeCode (optional) - Proposal Log Type Code. Maximum length is 3.
    + title (optional) - Title. Maximum length is 200.
    + piId (optional) - Principal Investigator (Employee). Maximum length is 40.
    + mergedWith (optional) - Proposal Merged With. Maximum length is 8.
    + instProposalNumber (optional) - Created Institutional Proposal. Maximum length is 200.
    + rolodexId (optional) - Rolodex Id. Maximum length is 6.
    + piName (optional) - Principal Investigator. Maximum length is 123.
    + leadUnit (optional) - Lead Unit. Maximum length is 8.
    + sponsorCode (optional) - Sponsor. Maximum length is 6.
    + sponsorName (optional) - Sponsor Name. Maximum length is 200.
    + logStatus (optional) - Log Status. Maximum length is 3.
    + comments (optional) - Comments. Maximum length is 300.
    + createTimestamp (optional) - The creation timestamp. Maximum length is 21.
    + createUser (optional) - The user who created the object. Maximum length is 60.
    + deadlineDate (optional) - Deadline Date. Maximum length is 10.
    + deadlineTime (optional) - The time in which the proposal is due to the sponsoring agency.  Additional deadlines may be set by the office authorized to submit proposals on behalf of the institution (e.g. sponsored programs, etc.). Maximum length is 15.
    + fiscalMonth (optional) - Fiscal Month. Maximum length is 2.
    + fiscalYear (optional) - Fiscal Year. Maximum length is 4.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Logs [GET /instprop/api/v1/proposal-logs/]
	                                          
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
    
            {"columns":["proposalNumber","proposalTypeCode","proposalLogTypeCode","title","piId","mergedWith","instProposalNumber","rolodexId","piName","leadUnit","sponsorCode","sponsorName","logStatus","comments","createTimestamp","createUser","deadlineDate","deadlineTime","fiscalMonth","fiscalYear"],"primaryKey":"proposalNumber"}
		
### Get Blueprint API specification for Proposal Logs [GET /instprop/api/v1/proposal-logs/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Logs.md"
            transfer-encoding:chunked
### Update Proposal Logs [PUT /instprop/api/v1/proposal-logs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Logs [PUT /instprop/api/v1/proposal-logs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Proposal Logs [POST /instprop/api/v1/proposal-logs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Logs [POST /instprop/api/v1/proposal-logs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"}
            ]
### Delete Proposal Logs by Key [DELETE /instprop/api/v1/proposal-logs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Logs [DELETE /instprop/api/v1/proposal-logs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Logs with Matching [DELETE /instprop/api/v1/proposal-logs/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + proposalTypeCode (optional) - Proposal Type Code. Maximum length is 3.
    + proposalLogTypeCode (optional) - Proposal Log Type Code. Maximum length is 3.
    + title (optional) - Title. Maximum length is 200.
    + piId (optional) - Principal Investigator (Employee). Maximum length is 40.
    + mergedWith (optional) - Proposal Merged With. Maximum length is 8.
    + instProposalNumber (optional) - Created Institutional Proposal. Maximum length is 200.
    + rolodexId (optional) - Rolodex Id. Maximum length is 6.
    + piName (optional) - Principal Investigator. Maximum length is 123.
    + leadUnit (optional) - Lead Unit. Maximum length is 8.
    + sponsorCode (optional) - Sponsor. Maximum length is 6.
    + sponsorName (optional) - Sponsor Name. Maximum length is 200.
    + logStatus (optional) - Log Status. Maximum length is 3.
    + comments (optional) - Comments. Maximum length is 300.
    + createTimestamp (optional) - The creation timestamp. Maximum length is 21.
    + createUser (optional) - The user who created the object. Maximum length is 60.
    + deadlineDate (optional) - Deadline Date. Maximum length is 10.
    + deadlineTime (optional) - The time in which the proposal is due to the sponsoring agency.  Additional deadlines may be set by the office authorized to submit proposals on behalf of the institution (e.g. sponsored programs, etc.). Maximum length is 15.
    + fiscalMonth (optional) - Fiscal Month. Maximum length is 2.
    + fiscalYear (optional) - Fiscal Year. Maximum length is 4.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
