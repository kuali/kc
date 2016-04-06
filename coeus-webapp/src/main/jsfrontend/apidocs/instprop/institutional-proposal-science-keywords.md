## Institutional Proposal Science Keywords [/research-sys/api/v1/institutional-proposal-science-keywords/]

### Get Institutional Proposal Science Keywords by Key [GET /research-sys/api/v1/institutional-proposal-science-keywords/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalScienceKeywordId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Science Keywords [GET /research-sys/api/v1/institutional-proposal-science-keywords/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalScienceKeywordId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"},
              {"proposalScienceKeywordId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Science Keywords with Filtering [GET /research-sys/api/v1/institutional-proposal-science-keywords/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + proposalScienceKeywordId
            + proposalId
            + proposalNumber
            + sequenceNumber
            + scienceKeywordCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalScienceKeywordId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"},
              {"proposalScienceKeywordId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Science Keywords [GET /research-sys/api/v1/institutional-proposal-science-keywords/]
	 
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
		
### Get Blueprint API specification for Institutional Proposal Science Keywords [GET /research-sys/api/v1/institutional-proposal-science-keywords/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Science Keywords.md"
            transfer-encoding:chunked


### Update Institutional Proposal Science Keywords [PUT /research-sys/api/v1/institutional-proposal-science-keywords/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalScienceKeywordId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposal Science Keywords [PUT /research-sys/api/v1/institutional-proposal-science-keywords/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalScienceKeywordId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"},
              {"proposalScienceKeywordId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Institutional Proposal Science Keywords [POST /research-sys/api/v1/institutional-proposal-science-keywords/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalScienceKeywordId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalScienceKeywordId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposal Science Keywords [POST /research-sys/api/v1/institutional-proposal-science-keywords/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalScienceKeywordId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"},
              {"proposalScienceKeywordId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalScienceKeywordId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"},
              {"proposalScienceKeywordId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Institutional Proposal Science Keywords by Key [DELETE /research-sys/api/v1/institutional-proposal-science-keywords/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Science Keywords [DELETE /research-sys/api/v1/institutional-proposal-science-keywords/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Institutional Proposal Science Keywords with Matching [DELETE /research-sys/api/v1/institutional-proposal-science-keywords/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + proposalScienceKeywordId
            + proposalId
            + proposalNumber
            + sequenceNumber
            + scienceKeywordCode


+ Response 204
