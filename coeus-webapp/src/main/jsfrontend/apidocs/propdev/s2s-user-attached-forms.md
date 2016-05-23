## S2s User Attached Forms [/propdev/api/v1/s2s-user-attached-forms/]

### Get S2s User Attached Forms by Key [GET /propdev/api/v1/s2s-user-attached-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All S2s User Attached Forms [GET /propdev/api/v1/s2s-user-attached-forms/]
	 
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

### Get All S2s User Attached Forms with Filtering [GET /propdev/api/v1/s2s-user-attached-forms/]
    
+ Parameters

    + id (optional) - 
    + proposalNumber (optional) - 
    + namespace (optional) - 
    + formName (optional) - 
    + formFileName (optional) - 
    + description (optional) - 

            
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
			
### Get Schema for S2s User Attached Forms [GET /propdev/api/v1/s2s-user-attached-forms/]
	                                          
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
    
            {"columns":["id","proposalNumber","namespace","formName","formFileName","description"],"primaryKey":"id"}
		
### Get Blueprint API specification for S2s User Attached Forms [GET /propdev/api/v1/s2s-user-attached-forms/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="S2s User Attached Forms.md"
            transfer-encoding:chunked
### Update S2s User Attached Forms [PUT /propdev/api/v1/s2s-user-attached-forms/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s User Attached Forms [PUT /propdev/api/v1/s2s-user-attached-forms/]

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
### Insert S2s User Attached Forms [POST /propdev/api/v1/s2s-user-attached-forms/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","proposalNumber": "(val)","namespace": "(val)","formName": "(val)","formFileName": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s User Attached Forms [POST /propdev/api/v1/s2s-user-attached-forms/]

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
### Delete S2s User Attached Forms by Key [DELETE /propdev/api/v1/s2s-user-attached-forms/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s User Attached Forms [DELETE /propdev/api/v1/s2s-user-attached-forms/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s User Attached Forms with Matching [DELETE /propdev/api/v1/s2s-user-attached-forms/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + proposalNumber (optional) - 
    + namespace (optional) - 
    + formName (optional) - 
    + formFileName (optional) - 
    + description (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
