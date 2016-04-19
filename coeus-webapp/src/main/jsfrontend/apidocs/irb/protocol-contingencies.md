## Protocol Contingencies [/irb/api/v1/protocol-contingencies/]

### Get Protocol Contingencies by Key [GET /irb/api/v1/protocol-contingencies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Protocol Contingencies [GET /irb/api/v1/protocol-contingencies/]
	 
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

### Get All Protocol Contingencies with Filtering [GET /irb/api/v1/protocol-contingencies/]
    
+ Parameters

    + protocolContingencyCode (optional) - Protocol Contingency Code. Maximum length is 4.
    + description (optional) - Description. Maximum length is 2000.

            
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
			
### Get Schema for Protocol Contingencies [GET /irb/api/v1/protocol-contingencies/]
	                                          
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
		
### Get Blueprint API specification for Protocol Contingencies [GET /irb/api/v1/protocol-contingencies/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Contingencies.md"
            transfer-encoding:chunked


### Update Protocol Contingencies [PUT /irb/api/v1/protocol-contingencies/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Contingencies [PUT /irb/api/v1/protocol-contingencies/]

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

### Insert Protocol Contingencies [POST /irb/api/v1/protocol-contingencies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolContingencyCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Contingencies [POST /irb/api/v1/protocol-contingencies/]

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
            
### Delete Protocol Contingencies by Key [DELETE /irb/api/v1/protocol-contingencies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Contingencies [DELETE /irb/api/v1/protocol-contingencies/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Contingencies with Matching [DELETE /irb/api/v1/protocol-contingencies/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolContingencyCode (optional) - Protocol Contingency Code. Maximum length is 4.
    + description (optional) - Description. Maximum length is 2000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
