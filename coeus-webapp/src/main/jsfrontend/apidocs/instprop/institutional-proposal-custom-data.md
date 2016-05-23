## Institutional Proposal Custom Data [/instprop/api/v1/institutional-proposal-custom-data/]

### Get Institutional Proposal Custom Data by Key [GET /instprop/api/v1/institutional-proposal-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalCustomDataId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Custom Data [GET /instprop/api/v1/institutional-proposal-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalCustomDataId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"},
              {"proposalCustomDataId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Custom Data with Filtering [GET /instprop/api/v1/institutional-proposal-custom-data/]
    
+ Parameters

    + proposalCustomDataId (optional) - Proposal Custom Data Id. Maximum length is 22.
    + proposalNumber (optional) - Proposal Number. Maximum length is 10.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + customAttributeId (optional) - Custom Attribute Id. Maximum length is 22.
    + value (optional) - Value. Maximum length is 2000.
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
              {"proposalCustomDataId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"},
              {"proposalCustomDataId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","institutionalProposal.proposalId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Custom Data [GET /instprop/api/v1/institutional-proposal-custom-data/]
	                                          
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
    
            {"columns":["proposalCustomDataId","proposalNumber","sequenceNumber","customAttributeId","value","institutionalProposal.proposalId"],"primaryKey":"proposalCustomDataId"}
		
### Get Blueprint API specification for Institutional Proposal Custom Data [GET /instprop/api/v1/institutional-proposal-custom-data/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Custom Data.md"
            transfer-encoding:chunked
