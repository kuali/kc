## Node States [/research-sys/api/v1/node-states/]

### Get Node States by Key [GET /research-sys/api/v1/node-states/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"routeNodeInstanceId": "(val)","lockVerNbr": "(val)","stateId": "(val)","value": "(val)","key": "(val)","_primaryKey": "(val)"}

### Get All Node States [GET /research-sys/api/v1/node-states/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"routeNodeInstanceId": "(val)","lockVerNbr": "(val)","stateId": "(val)","value": "(val)","key": "(val)","_primaryKey": "(val)"},
              {"routeNodeInstanceId": "(val)","lockVerNbr": "(val)","stateId": "(val)","value": "(val)","key": "(val)","_primaryKey": "(val)"}
            ]

### Get All Node States with Filtering [GET /research-sys/api/v1/node-states/]
    
+ Parameters

    + routeNodeInstanceId (optional) - 
    + lockVerNbr (optional) - 
    + stateId (optional) - 
    + value (optional) - 
    + key (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"routeNodeInstanceId": "(val)","lockVerNbr": "(val)","stateId": "(val)","value": "(val)","key": "(val)","_primaryKey": "(val)"},
              {"routeNodeInstanceId": "(val)","lockVerNbr": "(val)","stateId": "(val)","value": "(val)","key": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Node States [GET /research-sys/api/v1/node-states/]
	                                          
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
    
            {"columns":["routeNodeInstanceId","lockVerNbr","stateId","value","key"],"primaryKey":"stateId"}
		
### Get Blueprint API specification for Node States [GET /research-sys/api/v1/node-states/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Node States.md"
            transfer-encoding:chunked


### Update Node States [PUT /research-sys/api/v1/node-states/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"routeNodeInstanceId": "(val)","lockVerNbr": "(val)","stateId": "(val)","value": "(val)","key": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Node States [PUT /research-sys/api/v1/node-states/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"routeNodeInstanceId": "(val)","lockVerNbr": "(val)","stateId": "(val)","value": "(val)","key": "(val)","_primaryKey": "(val)"},
              {"routeNodeInstanceId": "(val)","lockVerNbr": "(val)","stateId": "(val)","value": "(val)","key": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Node States [POST /research-sys/api/v1/node-states/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"routeNodeInstanceId": "(val)","lockVerNbr": "(val)","stateId": "(val)","value": "(val)","key": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"routeNodeInstanceId": "(val)","lockVerNbr": "(val)","stateId": "(val)","value": "(val)","key": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Node States [POST /research-sys/api/v1/node-states/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"routeNodeInstanceId": "(val)","lockVerNbr": "(val)","stateId": "(val)","value": "(val)","key": "(val)","_primaryKey": "(val)"},
              {"routeNodeInstanceId": "(val)","lockVerNbr": "(val)","stateId": "(val)","value": "(val)","key": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"routeNodeInstanceId": "(val)","lockVerNbr": "(val)","stateId": "(val)","value": "(val)","key": "(val)","_primaryKey": "(val)"},
              {"routeNodeInstanceId": "(val)","lockVerNbr": "(val)","stateId": "(val)","value": "(val)","key": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Node States by Key [DELETE /research-sys/api/v1/node-states/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Node States [DELETE /research-sys/api/v1/node-states/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Node States with Matching [DELETE /research-sys/api/v1/node-states/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + routeNodeInstanceId (optional) - 
    + lockVerNbr (optional) - 
    + stateId (optional) - 
    + value (optional) - 
    + key (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
