## Principals [/research-sys/api/v1/principals/]

### Get Principals by Key [GET /research-sys/api/v1/principals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Principals [GET /research-sys/api/v1/principals/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Principals with Filtering [GET /research-sys/api/v1/principals/]
    
+ Parameters

    + principalId (optional) - 
    + principalName (optional) - 
    + entityId (optional) - 
    + password (optional) - 
    + active (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Principals [GET /research-sys/api/v1/principals/]
	                                          
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
    
            {"columns":["principalId","principalName","entityId","password","active"],"primaryKey":"principalId"}
		
### Get Blueprint API specification for Principals [GET /research-sys/api/v1/principals/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Principals.md"
            transfer-encoding:chunked


### Update Principals [PUT /research-sys/api/v1/principals/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Principals [PUT /research-sys/api/v1/principals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Principals [POST /research-sys/api/v1/principals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Principals [POST /research-sys/api/v1/principals/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","password": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Principals by Key [DELETE /research-sys/api/v1/principals/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Principals [DELETE /research-sys/api/v1/principals/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Principals with Matching [DELETE /research-sys/api/v1/principals/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + principalId (optional) - 
    + principalName (optional) - 
    + entityId (optional) - 
    + password (optional) - 
    + active (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
