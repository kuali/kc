## S2s User Attached Form Files [/propdev/api/v1/s2s-user-attached-form-files/]

### Get S2s User Attached Form Files by Key [GET /propdev/api/v1/s2s-user-attached-form-files/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"}

### Get All S2s User Attached Form Files [GET /propdev/api/v1/s2s-user-attached-form-files/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"}
            ]

### Get All S2s User Attached Form Files with Filtering [GET /propdev/api/v1/s2s-user-attached-form-files/]
    
+ Parameters

    + id (optional) - 
    + formFile (optional) - 
    + xmlFile (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for S2s User Attached Form Files [GET /propdev/api/v1/s2s-user-attached-form-files/]
	                                          
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
    
            {"columns":["id","formFile","xmlFile"],"primaryKey":"id"}
		
### Get Blueprint API specification for S2s User Attached Form Files [GET /propdev/api/v1/s2s-user-attached-form-files/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="S2s User Attached Form Files.md"
            transfer-encoding:chunked


### Update S2s User Attached Form Files [PUT /propdev/api/v1/s2s-user-attached-form-files/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s User Attached Form Files [PUT /propdev/api/v1/s2s-user-attached-form-files/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert S2s User Attached Form Files [POST /propdev/api/v1/s2s-user-attached-form-files/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s User Attached Form Files [POST /propdev/api/v1/s2s-user-attached-form-files/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete S2s User Attached Form Files by Key [DELETE /propdev/api/v1/s2s-user-attached-form-files/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s User Attached Form Files [DELETE /propdev/api/v1/s2s-user-attached-form-files/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s User Attached Form Files with Matching [DELETE /propdev/api/v1/s2s-user-attached-form-files/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + formFile (optional) - 
    + xmlFile (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
