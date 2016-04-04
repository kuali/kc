## Protocol Contingencies [/research-sys/api/v1/protocol-contingencies/]

### Get Protocol Contingencies by Key [GET /research-sys/api/v1/protocol-contingencies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Protocol Contingencies [GET /research-sys/api/v1/protocol-contingencies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Contingencies with Filtering [GET /research-sys/api/v1/protocol-contingencies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + protocolContingencyCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Contingencies [GET /research-sys/api/v1/protocol-contingencies/]
	 
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
		
### Get Blueprint API specification for Protocol Contingencies [GET /research-sys/api/v1/protocol-contingencies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Contingencies.md"
            transfer-encoding:chunked


### Update Protocol Contingencies [PUT /research-sys/api/v1/protocol-contingencies/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Contingencies [PUT /research-sys/api/v1/protocol-contingencies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Contingencies [POST /research-sys/api/v1/protocol-contingencies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Contingencies [POST /research-sys/api/v1/protocol-contingencies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Contingencies by Key [DELETE /research-sys/api/v1/protocol-contingencies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Contingencies [DELETE /research-sys/api/v1/protocol-contingencies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Protocol Contingencies with Matching [DELETE /research-sys/api/v1/protocol-contingencies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + protocolContingencyCode
            + description


+ Response 204
