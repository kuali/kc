## Proposal Development Documents [/research-sys/api/v1/proposal-development-documents/]

### Get Proposal Development Documents by Key [GET /research-sys/api/v1/proposal-development-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}

### Get All Proposal Development Documents [GET /research-sys/api/v1/proposal-development-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Development Documents with Filtering [GET /research-sys/api/v1/proposal-development-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + proposalDeleted
            + documentNumber
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Development Documents [GET /research-sys/api/v1/proposal-development-documents/]
	 
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
		
### Get Blueprint API specification for Proposal Development Documents [GET /research-sys/api/v1/proposal-development-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Development Documents.md"
            transfer-encoding:chunked


### Update Proposal Development Documents [PUT /research-sys/api/v1/proposal-development-documents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Development Documents [PUT /research-sys/api/v1/proposal-development-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Development Documents [POST /research-sys/api/v1/proposal-development-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Development Documents [POST /research-sys/api/v1/proposal-development-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Development Documents by Key [DELETE /research-sys/api/v1/proposal-development-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Development Documents [DELETE /research-sys/api/v1/proposal-development-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Development Documents with Matching [DELETE /research-sys/api/v1/proposal-development-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + proposalDeleted
            + documentNumber


+ Response 204
