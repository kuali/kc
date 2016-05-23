## S2s Providers [/propdev/api/v1/s2s-providers/]

### Get S2s Providers by Key [GET /propdev/api/v1/s2s-providers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All S2s Providers [GET /propdev/api/v1/s2s-providers/]
	 
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

### Get All S2s Providers with Filtering [GET /propdev/api/v1/s2s-providers/]
    
+ Parameters

    + code (optional) - S2S Provider Code. Maximum length is 4.
    + description (optional) - Description. Maximum length is 4000.
    + connectorServiceName (optional) - S2S Connector Spring Service Name. Maximum length is 1000.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.

            
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
			
### Get Schema for S2s Providers [GET /propdev/api/v1/s2s-providers/]
	                                          
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
		
### Get Blueprint API specification for S2s Providers [GET /propdev/api/v1/s2s-providers/]
	 
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
### Update S2s Providers [PUT /propdev/api/v1/s2s-providers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple S2s Providers [PUT /propdev/api/v1/s2s-providers/]

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
### Insert S2s Providers [POST /propdev/api/v1/s2s-providers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","connectorServiceName": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple S2s Providers [POST /propdev/api/v1/s2s-providers/]

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
### Delete S2s Providers by Key [DELETE /propdev/api/v1/s2s-providers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Providers [DELETE /propdev/api/v1/s2s-providers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All S2s Providers with Matching [DELETE /propdev/api/v1/s2s-providers/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - S2S Provider Code. Maximum length is 4.
    + description (optional) - Description. Maximum length is 4000.
    + connectorServiceName (optional) - S2S Connector Spring Service Name. Maximum length is 1000.
    + active (optional) - This attribute is used to describe whether the associated object is active or inactive. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
