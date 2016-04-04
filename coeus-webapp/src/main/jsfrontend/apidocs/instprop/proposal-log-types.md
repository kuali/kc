## Proposal Log Types [/research-sys/api/v1/proposal-log-types/]

### Get Proposal Log Types by Key [GET /research-sys/api/v1/proposal-log-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Proposal Log Types [GET /research-sys/api/v1/proposal-log-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Log Types with Filtering [GET /research-sys/api/v1/proposal-log-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + proposalLogTypeCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Log Types [GET /research-sys/api/v1/proposal-log-types/]
	 
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
		
### Get Blueprint API specification for Proposal Log Types [GET /research-sys/api/v1/proposal-log-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Log Types.md"
            transfer-encoding:chunked


### Update Proposal Log Types [PUT /research-sys/api/v1/proposal-log-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Log Types [PUT /research-sys/api/v1/proposal-log-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Log Types [POST /research-sys/api/v1/proposal-log-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Log Types [POST /research-sys/api/v1/proposal-log-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Log Types by Key [DELETE /research-sys/api/v1/proposal-log-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Log Types [DELETE /research-sys/api/v1/proposal-log-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Log Types with Matching [DELETE /research-sys/api/v1/proposal-log-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + proposalLogTypeCode
            + description


+ Response 204
