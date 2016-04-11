## Route Node Instances [/research-sys/api/v1/route-node-instances/]

### Get Route Node Instances by Key [GET /research-sys/api/v1/route-node-instances/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"routeNodeInstanceId": "(val)","documentId": "(val)","active": "(val)","complete": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}

### Get All Route Node Instances [GET /research-sys/api/v1/route-node-instances/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"routeNodeInstanceId": "(val)","documentId": "(val)","active": "(val)","complete": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"routeNodeInstanceId": "(val)","documentId": "(val)","active": "(val)","complete": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]

### Get All Route Node Instances with Filtering [GET /research-sys/api/v1/route-node-instances/]
    
+ Parameters

        + routeNodeInstanceId
            + documentId
            + active
            + complete
            + initial
            + lockVerNbr

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"routeNodeInstanceId": "(val)","documentId": "(val)","active": "(val)","complete": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"routeNodeInstanceId": "(val)","documentId": "(val)","active": "(val)","complete": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Route Node Instances [GET /research-sys/api/v1/route-node-instances/]
	                                          
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
    
            {"columns":["routeNodeInstanceId","documentId","active","complete","initial","lockVerNbr"],"primaryKey":"routeNodeInstanceId"}
		
### Get Blueprint API specification for Route Node Instances [GET /research-sys/api/v1/route-node-instances/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Route Node Instances.md"
            transfer-encoding:chunked


### Update Route Node Instances [PUT /research-sys/api/v1/route-node-instances/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"routeNodeInstanceId": "(val)","documentId": "(val)","active": "(val)","complete": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Route Node Instances [PUT /research-sys/api/v1/route-node-instances/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"routeNodeInstanceId": "(val)","documentId": "(val)","active": "(val)","complete": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"routeNodeInstanceId": "(val)","documentId": "(val)","active": "(val)","complete": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Route Node Instances [POST /research-sys/api/v1/route-node-instances/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"routeNodeInstanceId": "(val)","documentId": "(val)","active": "(val)","complete": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"routeNodeInstanceId": "(val)","documentId": "(val)","active": "(val)","complete": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Route Node Instances [POST /research-sys/api/v1/route-node-instances/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"routeNodeInstanceId": "(val)","documentId": "(val)","active": "(val)","complete": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"routeNodeInstanceId": "(val)","documentId": "(val)","active": "(val)","complete": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"routeNodeInstanceId": "(val)","documentId": "(val)","active": "(val)","complete": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"routeNodeInstanceId": "(val)","documentId": "(val)","active": "(val)","complete": "(val)","initial": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Route Node Instances by Key [DELETE /research-sys/api/v1/route-node-instances/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Route Node Instances [DELETE /research-sys/api/v1/route-node-instances/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Route Node Instances with Matching [DELETE /research-sys/api/v1/route-node-instances/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + routeNodeInstanceId
            + documentId
            + active
            + complete
            + initial
            + lockVerNbr

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
