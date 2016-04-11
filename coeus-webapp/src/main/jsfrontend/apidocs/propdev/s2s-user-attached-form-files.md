## S2s User Attached Form Files [/research-sys/api/v1/s2s-user-attached-form-files/]

### Get S2s User Attached Form Files by Key [GET /research-sys/api/v1/s2s-user-attached-form-files/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"}

### Get All S2s User Attached Form Files [GET /research-sys/api/v1/s2s-user-attached-form-files/]
	 
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

### Get All S2s User Attached Form Files with Filtering [GET /research-sys/api/v1/s2s-user-attached-form-files/]
    
+ Parameters

        + id
            + formFile
            + xmlFile

            
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
			
### Get Schema for S2s User Attached Form Files [GET /research-sys/api/v1/s2s-user-attached-form-files/]
	                                          
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
		
### Get Blueprint API specification for S2s User Attached Form Files [GET /research-sys/api/v1/s2s-user-attached-form-files/]
	 
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


### Update S2s User Attached Form Files [PUT /research-sys/api/v1/s2s-user-attached-form-files/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s User Attached Form Files [PUT /research-sys/api/v1/s2s-user-attached-form-files/]

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

### Insert S2s User Attached Form Files [POST /research-sys/api/v1/s2s-user-attached-form-files/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","formFile": "(val)","xmlFile": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s User Attached Form Files [POST /research-sys/api/v1/s2s-user-attached-form-files/]

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
            
### Delete S2s User Attached Form Files by Key [DELETE /research-sys/api/v1/s2s-user-attached-form-files/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s User Attached Form Files [DELETE /research-sys/api/v1/s2s-user-attached-form-files/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s User Attached Form Files with Matching [DELETE /research-sys/api/v1/s2s-user-attached-form-files/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + formFile
            + xmlFile

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
