## Institutional Proposal Science Keywords [/instprop/api/v1/institutional-proposal-science-keywords/]

### Get Institutional Proposal Science Keywords by Key [GET /instprop/api/v1/institutional-proposal-science-keywords/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalScienceKeywordId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Science Keywords [GET /instprop/api/v1/institutional-proposal-science-keywords/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalScienceKeywordId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"},
              {"proposalScienceKeywordId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Science Keywords with Filtering [GET /instprop/api/v1/institutional-proposal-science-keywords/]
    
+ Parameters

    + proposalScienceKeywordId (optional) - Proposal Science Code Id. Maximum length is 22.
    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + scienceKeywordCode (optional) - Science Code. Maximum length is 15.
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
              {"proposalScienceKeywordId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"},
              {"proposalScienceKeywordId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","scienceKeywordCode": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Science Keywords [GET /instprop/api/v1/institutional-proposal-science-keywords/]
	                                          
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
    
            {"columns":["proposalScienceKeywordId","proposalNumber","sequenceNumber","scienceKeywordCode","institutionalProposal.proposalId"],"primaryKey":"proposalScienceKeywordId"}
		
### Get Blueprint API specification for Institutional Proposal Science Keywords [GET /instprop/api/v1/institutional-proposal-science-keywords/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Science Keywords.md"
            transfer-encoding:chunked
