## Maintenance Locks [/research-sys/api/v1/maintenance-locks/]

### Get Maintenance Locks by Key [GET /research-sys/api/v1/maintenance-locks/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"lockId": "(val)","lockingRepresentation": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}

### Get All Maintenance Locks [GET /research-sys/api/v1/maintenance-locks/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"lockId": "(val)","lockingRepresentation": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"lockId": "(val)","lockingRepresentation": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Maintenance Locks with Filtering [GET /research-sys/api/v1/maintenance-locks/]
    
+ Parameters

        + lockId
            + lockingRepresentation
            + documentNumber

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"lockId": "(val)","lockingRepresentation": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"lockId": "(val)","lockingRepresentation": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Maintenance Locks [GET /research-sys/api/v1/maintenance-locks/]
	                                          
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
    
            {"columns":["lockId","lockingRepresentation","documentNumber"],"primaryKey":"lockId"}
		
### Get Blueprint API specification for Maintenance Locks [GET /research-sys/api/v1/maintenance-locks/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Maintenance Locks.md"
            transfer-encoding:chunked


### Update Maintenance Locks [PUT /research-sys/api/v1/maintenance-locks/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"lockId": "(val)","lockingRepresentation": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Maintenance Locks [PUT /research-sys/api/v1/maintenance-locks/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"lockId": "(val)","lockingRepresentation": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"lockId": "(val)","lockingRepresentation": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Maintenance Locks [POST /research-sys/api/v1/maintenance-locks/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"lockId": "(val)","lockingRepresentation": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"lockId": "(val)","lockingRepresentation": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Maintenance Locks [POST /research-sys/api/v1/maintenance-locks/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"lockId": "(val)","lockingRepresentation": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"lockId": "(val)","lockingRepresentation": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"lockId": "(val)","lockingRepresentation": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"lockId": "(val)","lockingRepresentation": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Maintenance Locks by Key [DELETE /research-sys/api/v1/maintenance-locks/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Maintenance Locks [DELETE /research-sys/api/v1/maintenance-locks/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Maintenance Locks with Matching [DELETE /research-sys/api/v1/maintenance-locks/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + lockId
            + lockingRepresentation
            + documentNumber

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
