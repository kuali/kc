## Iacuc Protocol Contingencies [/research-sys/api/v1/iacuc-protocol-contingencies/]

### Get Iacuc Protocol Contingencies by Key [GET /research-sys/api/v1/iacuc-protocol-contingencies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Contingencies [GET /research-sys/api/v1/iacuc-protocol-contingencies/]
	 
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

### Get All Iacuc Protocol Contingencies with Filtering [GET /research-sys/api/v1/iacuc-protocol-contingencies/]
    
+ Parameters

        + protocolContingencyCode
            + description

            
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
			
### Get Schema for Iacuc Protocol Contingencies [GET /research-sys/api/v1/iacuc-protocol-contingencies/]
	                                          
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
    
            {"columns":["protocolContingencyCode","description"],"primaryKey":"protocolContingencyCode"}
		
### Get Blueprint API specification for Iacuc Protocol Contingencies [GET /research-sys/api/v1/iacuc-protocol-contingencies/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Contingencies.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Contingencies [PUT /research-sys/api/v1/iacuc-protocol-contingencies/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Contingencies [PUT /research-sys/api/v1/iacuc-protocol-contingencies/]

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

### Insert Iacuc Protocol Contingencies [POST /research-sys/api/v1/iacuc-protocol-contingencies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Contingencies [POST /research-sys/api/v1/iacuc-protocol-contingencies/]

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
            
### Delete Iacuc Protocol Contingencies by Key [DELETE /research-sys/api/v1/iacuc-protocol-contingencies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Contingencies [DELETE /research-sys/api/v1/iacuc-protocol-contingencies/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Contingencies with Matching [DELETE /research-sys/api/v1/iacuc-protocol-contingencies/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + protocolContingencyCode
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
