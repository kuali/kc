## Protocol Documents [/research-sys/api/v1/protocol-documents/]

### Get Protocol Documents by Key [GET /research-sys/api/v1/protocol-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"documentNumber": "(val)","protocolWorkflowType": "(val)","reRouted": "(val)","_primaryKey": "(val)"}

### Get All Protocol Documents [GET /research-sys/api/v1/protocol-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentNumber": "(val)","protocolWorkflowType": "(val)","reRouted": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","protocolWorkflowType": "(val)","reRouted": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Documents with Filtering [GET /research-sys/api/v1/protocol-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + documentNumber
            + protocolWorkflowType
            + reRouted
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentNumber": "(val)","protocolWorkflowType": "(val)","reRouted": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","protocolWorkflowType": "(val)","reRouted": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Documents [GET /research-sys/api/v1/protocol-documents/]
	 
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
		
### Get Blueprint API specification for Protocol Documents [GET /research-sys/api/v1/protocol-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Documents.md"
            transfer-encoding:chunked


### Update Protocol Documents [PUT /research-sys/api/v1/protocol-documents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentNumber": "(val)","protocolWorkflowType": "(val)","reRouted": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Documents [PUT /research-sys/api/v1/protocol-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentNumber": "(val)","protocolWorkflowType": "(val)","reRouted": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","protocolWorkflowType": "(val)","reRouted": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Documents [POST /research-sys/api/v1/protocol-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentNumber": "(val)","protocolWorkflowType": "(val)","reRouted": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"documentNumber": "(val)","protocolWorkflowType": "(val)","reRouted": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Documents [POST /research-sys/api/v1/protocol-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentNumber": "(val)","protocolWorkflowType": "(val)","reRouted": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","protocolWorkflowType": "(val)","reRouted": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"documentNumber": "(val)","protocolWorkflowType": "(val)","reRouted": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","protocolWorkflowType": "(val)","reRouted": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Documents by Key [DELETE /research-sys/api/v1/protocol-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Documents [DELETE /research-sys/api/v1/protocol-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Protocol Documents with Matching [DELETE /research-sys/api/v1/protocol-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + documentNumber
            + protocolWorkflowType
            + reRouted


+ Response 204
