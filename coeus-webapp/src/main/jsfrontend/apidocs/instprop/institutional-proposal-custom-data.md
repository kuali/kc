## Institutional Proposal Custom Data [/research-sys/api/v1/institutional-proposal-custom-data/]

### Get Institutional Proposal Custom Data by Key [GET /research-sys/api/v1/institutional-proposal-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalCustomDataId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Custom Data [GET /research-sys/api/v1/institutional-proposal-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalCustomDataId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"proposalCustomDataId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Custom Data with Filtering [GET /research-sys/api/v1/institutional-proposal-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + proposalCustomDataId
            + proposalId
            + proposalNumber
            + sequenceNumber
            + customAttributeId
            + value
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalCustomDataId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"proposalCustomDataId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Custom Data [GET /research-sys/api/v1/institutional-proposal-custom-data/]
	 
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
		
### Get Blueprint API specification for Institutional Proposal Custom Data [GET /research-sys/api/v1/institutional-proposal-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Custom Data.md"
            transfer-encoding:chunked


### Update Institutional Proposal Custom Data [PUT /research-sys/api/v1/institutional-proposal-custom-data/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalCustomDataId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposal Custom Data [PUT /research-sys/api/v1/institutional-proposal-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalCustomDataId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"proposalCustomDataId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Institutional Proposal Custom Data [POST /research-sys/api/v1/institutional-proposal-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalCustomDataId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalCustomDataId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposal Custom Data [POST /research-sys/api/v1/institutional-proposal-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalCustomDataId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"proposalCustomDataId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalCustomDataId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"proposalCustomDataId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Institutional Proposal Custom Data by Key [DELETE /research-sys/api/v1/institutional-proposal-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Custom Data [DELETE /research-sys/api/v1/institutional-proposal-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Institutional Proposal Custom Data with Matching [DELETE /research-sys/api/v1/institutional-proposal-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + proposalCustomDataId
            + proposalId
            + proposalNumber
            + sequenceNumber
            + customAttributeId
            + value


+ Response 204
