## Proposal Statuses [/research-sys/api/v1/proposal-statuses/]

### Get Proposal Statuses by Key [GET /research-sys/api/v1/proposal-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Proposal Statuses [GET /research-sys/api/v1/proposal-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Statuses with Filtering [GET /research-sys/api/v1/proposal-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + proposalStatusCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Statuses [GET /research-sys/api/v1/proposal-statuses/]
	 
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
		
### Get Blueprint API specification for Proposal Statuses [GET /research-sys/api/v1/proposal-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Statuses.md"
            transfer-encoding:chunked


### Update Proposal Statuses [PUT /research-sys/api/v1/proposal-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Statuses [PUT /research-sys/api/v1/proposal-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Statuses [POST /research-sys/api/v1/proposal-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Statuses [POST /research-sys/api/v1/proposal-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Statuses by Key [DELETE /research-sys/api/v1/proposal-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Statuses [DELETE /research-sys/api/v1/proposal-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Statuses with Matching [DELETE /research-sys/api/v1/proposal-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + proposalStatusCode
            + description


+ Response 204
