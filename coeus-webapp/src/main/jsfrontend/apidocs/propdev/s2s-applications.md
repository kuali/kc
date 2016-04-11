## S2s Applications [/research-sys/api/v1/s2s-applications/]

### Get S2s Applications by Key [GET /research-sys/api/v1/s2s-applications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalNumber": "(val)","application": "(val)","_primaryKey": "(val)"}

### Get All S2s Applications [GET /research-sys/api/v1/s2s-applications/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","application": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","application": "(val)","_primaryKey": "(val)"}
            ]

### Get All S2s Applications with Filtering [GET /research-sys/api/v1/s2s-applications/]
    
+ Parameters

        + proposalNumber
            + application

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","application": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","application": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for S2s Applications [GET /research-sys/api/v1/s2s-applications/]
	                                          
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
    
            {"columns":["proposalNumber","application"],"primaryKey":"proposalNumber"}
		
### Get Blueprint API specification for S2s Applications [GET /research-sys/api/v1/s2s-applications/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="S2s Applications.md"
            transfer-encoding:chunked


### Update S2s Applications [PUT /research-sys/api/v1/s2s-applications/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","application": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s Applications [PUT /research-sys/api/v1/s2s-applications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalNumber": "(val)","application": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","application": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert S2s Applications [POST /research-sys/api/v1/s2s-applications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","application": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalNumber": "(val)","application": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s Applications [POST /research-sys/api/v1/s2s-applications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalNumber": "(val)","application": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","application": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalNumber": "(val)","application": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","application": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete S2s Applications by Key [DELETE /research-sys/api/v1/s2s-applications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Applications [DELETE /research-sys/api/v1/s2s-applications/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Applications with Matching [DELETE /research-sys/api/v1/s2s-applications/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + proposalNumber
            + application

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
