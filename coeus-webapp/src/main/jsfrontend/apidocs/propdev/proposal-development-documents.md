## Proposal Development Documents [/propdev/api/v1/proposal-development-documents/]

### Get Proposal Development Documents by Key [GET /propdev/api/v1/proposal-development-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}

### Get All Proposal Development Documents [GET /propdev/api/v1/proposal-development-documents/]
	 
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

### Get All Proposal Development Documents with Filtering [GET /propdev/api/v1/proposal-development-documents/]
    
+ Parameters

    + proposalDeleted (optional) - Proposal Deleted.
    + documentNumber (optional) - Document Number. Maximum length is 14.

            
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
			
### Get Schema for Proposal Development Documents [GET /propdev/api/v1/proposal-development-documents/]
	                                          
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
    
            {"columns":["proposalDeleted","documentNumber"],"primaryKey":"documentNumber"}
		
### Get Blueprint API specification for Proposal Development Documents [GET /propdev/api/v1/proposal-development-documents/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Development Documents.md"
            transfer-encoding:chunked


### Update Proposal Development Documents [PUT /propdev/api/v1/proposal-development-documents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Development Documents [PUT /propdev/api/v1/proposal-development-documents/]

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

### Insert Proposal Development Documents [POST /propdev/api/v1/proposal-development-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalDeleted": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Development Documents [POST /propdev/api/v1/proposal-development-documents/]

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
            
### Delete Proposal Development Documents by Key [DELETE /propdev/api/v1/proposal-development-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Development Documents [DELETE /propdev/api/v1/proposal-development-documents/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Development Documents with Matching [DELETE /propdev/api/v1/proposal-development-documents/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalDeleted (optional) - Proposal Deleted.
    + documentNumber (optional) - Document Number. Maximum length is 14.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
