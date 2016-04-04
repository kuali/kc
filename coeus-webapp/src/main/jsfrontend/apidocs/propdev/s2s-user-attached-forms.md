## S2s User Attached Forms [/research-sys/api/v1/s2s-user-attached-forms/]

### Get S2s User Attached Forms by Key [GET /research-sys/api/v1/s2s-user-attached-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All S2s User Attached Forms [GET /research-sys/api/v1/s2s-user-attached-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All S2s User Attached Forms with Filtering [GET /research-sys/api/v1/s2s-user-attached-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + proposalNumber
            + namespace
            + formName
            + formFileName
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for S2s User Attached Forms [GET /research-sys/api/v1/s2s-user-attached-forms/]
	 
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
		
### Get Blueprint API specification for S2s User Attached Forms [GET /research-sys/api/v1/s2s-user-attached-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="S2s User Attached Forms.md"
            transfer-encoding:chunked


### Update S2s User Attached Forms [PUT /research-sys/api/v1/s2s-user-attached-forms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s User Attached Forms [PUT /research-sys/api/v1/s2s-user-attached-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert S2s User Attached Forms [POST /research-sys/api/v1/s2s-user-attached-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s User Attached Forms [POST /research-sys/api/v1/s2s-user-attached-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete S2s User Attached Forms by Key [DELETE /research-sys/api/v1/s2s-user-attached-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s User Attached Forms [DELETE /research-sys/api/v1/s2s-user-attached-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All S2s User Attached Forms with Matching [DELETE /research-sys/api/v1/s2s-user-attached-forms/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + proposalNumber
            + namespace
            + formName
            + formFileName
            + description


+ Response 204
