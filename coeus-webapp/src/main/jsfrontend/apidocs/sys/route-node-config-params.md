## Route Node Config Params [/research-sys/api/v1/route-node-config-params/]

### Get Route Node Config Params by Key [GET /research-sys/api/v1/route-node-config-params/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","key": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Route Node Config Params [GET /research-sys/api/v1/route-node-config-params/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","key": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","key": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]

### Get All Route Node Config Params with Filtering [GET /research-sys/api/v1/route-node-config-params/]
    
+ Parameters

    + id (optional) - 
    + key (optional) - 
    + value (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","key": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","key": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Route Node Config Params [GET /research-sys/api/v1/route-node-config-params/]
	                                          
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
    
            {"columns":["id","key","value"],"primaryKey":"id"}
		
### Get Blueprint API specification for Route Node Config Params [GET /research-sys/api/v1/route-node-config-params/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Route Node Config Params.md"
            transfer-encoding:chunked


### Update Route Node Config Params [PUT /research-sys/api/v1/route-node-config-params/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","key": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Route Node Config Params [PUT /research-sys/api/v1/route-node-config-params/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","key": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","key": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Route Node Config Params [POST /research-sys/api/v1/route-node-config-params/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","key": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","key": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Route Node Config Params [POST /research-sys/api/v1/route-node-config-params/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","key": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","key": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","key": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","key": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Route Node Config Params by Key [DELETE /research-sys/api/v1/route-node-config-params/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Route Node Config Params [DELETE /research-sys/api/v1/route-node-config-params/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Route Node Config Params with Matching [DELETE /research-sys/api/v1/route-node-config-params/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + key (optional) - 
    + value (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
