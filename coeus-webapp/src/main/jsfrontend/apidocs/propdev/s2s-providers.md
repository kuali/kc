## S2s Providers [/research-sys/api/v1/s2s-providers/]

### Get S2s Providers by Key [GET /research-sys/api/v1/s2s-providers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All S2s Providers [GET /research-sys/api/v1/s2s-providers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All S2s Providers with Filtering [GET /research-sys/api/v1/s2s-providers/]
    
+ Parameters

        + code
            + description
            + connectorServiceName
            + active

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for S2s Providers [GET /research-sys/api/v1/s2s-providers/]
	                                          
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
    
            {"columns":["code","description","connectorServiceName","active"],"primaryKey":"code"}
		
### Get Blueprint API specification for S2s Providers [GET /research-sys/api/v1/s2s-providers/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="S2s Providers.md"
            transfer-encoding:chunked


### Update S2s Providers [PUT /research-sys/api/v1/s2s-providers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s Providers [PUT /research-sys/api/v1/s2s-providers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert S2s Providers [POST /research-sys/api/v1/s2s-providers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s Providers [POST /research-sys/api/v1/s2s-providers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete S2s Providers by Key [DELETE /research-sys/api/v1/s2s-providers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Providers [DELETE /research-sys/api/v1/s2s-providers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Providers with Matching [DELETE /research-sys/api/v1/s2s-providers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + code
            + description
            + connectorServiceName
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
