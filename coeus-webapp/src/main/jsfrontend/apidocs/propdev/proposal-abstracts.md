## Proposal Abstracts [/research-sys/api/v1/proposal-abstracts/]

### Get Proposal Abstracts by Key [GET /research-sys/api/v1/proposal-abstracts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}

### Get All Proposal Abstracts [GET /research-sys/api/v1/proposal-abstracts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Abstracts with Filtering [GET /research-sys/api/v1/proposal-abstracts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + proposalNumber
            + abstractTypeCode
            + abstractDetails
            + timestampDisplay
            + uploadUserDisplay
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Abstracts [GET /research-sys/api/v1/proposal-abstracts/]
	 
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
		
### Get Blueprint API specification for Proposal Abstracts [GET /research-sys/api/v1/proposal-abstracts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Abstracts.md"
            transfer-encoding:chunked


### Update Proposal Abstracts [PUT /research-sys/api/v1/proposal-abstracts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Abstracts [PUT /research-sys/api/v1/proposal-abstracts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Abstracts [POST /research-sys/api/v1/proposal-abstracts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Abstracts [POST /research-sys/api/v1/proposal-abstracts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Abstracts by Key [DELETE /research-sys/api/v1/proposal-abstracts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Abstracts [DELETE /research-sys/api/v1/proposal-abstracts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Abstracts with Matching [DELETE /research-sys/api/v1/proposal-abstracts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + proposalNumber
            + abstractTypeCode
            + abstractDetails
            + timestampDisplay
            + uploadUserDisplay


+ Response 204
