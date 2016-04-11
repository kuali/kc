## Proposal Logs [/research-sys/api/v1/proposal-logs/]

### Get Proposal Logs by Key [GET /research-sys/api/v1/proposal-logs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"}

### Get All Proposal Logs [GET /research-sys/api/v1/proposal-logs/]
	 
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

### Get All Proposal Logs with Filtering [GET /research-sys/api/v1/proposal-logs/]
    
+ Parameters

        + proposalNumber
            + proposalTypeCode
            + proposalLogTypeCode
            + title
            + piId
            + mergedWith
            + instProposalNumber
            + rolodexId
            + piName
            + leadUnit
            + sponsorCode
            + sponsorName
            + logStatus
            + comments
            + createTimestamp
            + createUser
            + deadlineDate
            + deadlineTime
            + fiscalMonth
            + fiscalYear

            
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
			
### Get Schema for Proposal Logs [GET /research-sys/api/v1/proposal-logs/]
	                                          
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
		
### Get Blueprint API specification for Proposal Logs [GET /research-sys/api/v1/proposal-logs/]
	 
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


### Update Proposal Logs [PUT /research-sys/api/v1/proposal-logs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Logs [PUT /research-sys/api/v1/proposal-logs/]

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

### Insert Proposal Logs [POST /research-sys/api/v1/proposal-logs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalNumber": "(val)","proposalTypeCode": "(val)","proposalLogTypeCode": "(val)","title": "(val)","piId": "(val)","mergedWith": "(val)","instProposalNumber": "(val)","rolodexId": "(val)","piName": "(val)","leadUnit": "(val)","sponsorCode": "(val)","sponsorName": "(val)","logStatus": "(val)","comments": "(val)","createTimestamp": "(val)","createUser": "(val)","deadlineDate": "(val)","deadlineTime": "(val)","fiscalMonth": "(val)","fiscalYear": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Logs [POST /research-sys/api/v1/proposal-logs/]

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
            
### Delete Proposal Logs by Key [DELETE /research-sys/api/v1/proposal-logs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Logs [DELETE /research-sys/api/v1/proposal-logs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Logs with Matching [DELETE /research-sys/api/v1/proposal-logs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + proposalNumber
            + proposalTypeCode
            + proposalLogTypeCode
            + title
            + piId
            + mergedWith
            + instProposalNumber
            + rolodexId
            + piName
            + leadUnit
            + sponsorCode
            + sponsorName
            + logStatus
            + comments
            + createTimestamp
            + createUser
            + deadlineDate
            + deadlineTime
            + fiscalMonth
            + fiscalYear

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
