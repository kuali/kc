## Proposal Log Statuses [/research-sys/api/v1/proposal-log-statuses/]

### Get Proposal Log Statuses by Key [GET /research-sys/api/v1/proposal-log-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalLogStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Proposal Log Statuses [GET /research-sys/api/v1/proposal-log-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalLogStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalLogStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Log Statuses with Filtering [GET /research-sys/api/v1/proposal-log-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + proposalLogStatusCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalLogStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalLogStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Log Statuses [GET /research-sys/api/v1/proposal-log-statuses/]
	 
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
		
### Get Blueprint API specification for Proposal Log Statuses [GET /research-sys/api/v1/proposal-log-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Log Statuses.md"
            transfer-encoding:chunked


### Update Proposal Log Statuses [PUT /research-sys/api/v1/proposal-log-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalLogStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Log Statuses [PUT /research-sys/api/v1/proposal-log-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalLogStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalLogStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Log Statuses [POST /research-sys/api/v1/proposal-log-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalLogStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalLogStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Log Statuses [POST /research-sys/api/v1/proposal-log-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalLogStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalLogStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalLogStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalLogStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Log Statuses by Key [DELETE /research-sys/api/v1/proposal-log-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Log Statuses [DELETE /research-sys/api/v1/proposal-log-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Log Statuses with Matching [DELETE /research-sys/api/v1/proposal-log-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + proposalLogStatusCode
            + description


+ Response 204
