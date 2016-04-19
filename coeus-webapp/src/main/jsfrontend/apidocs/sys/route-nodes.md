## Route Nodes [/research-sys/api/v1/route-nodes/]

### Get Route Nodes by Key [GET /research-sys/api/v1/route-nodes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"routeNodeId": "(val)","documentTypeId": "(val)","routeNodeName": "(val)","routeMethodName": "(val)","finalApprovalInd": "(val)","mandatoryRouteInd": "(val)","exceptionWorkgroupId": "(val)","routeMethodCode": "(val)","activationType": "(val)","nextDocStatus": "(val)","lockVerNbr": "(val)","nodeType": "(val)","_primaryKey": "(val)"}

### Get All Route Nodes [GET /research-sys/api/v1/route-nodes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"routeNodeId": "(val)","documentTypeId": "(val)","routeNodeName": "(val)","routeMethodName": "(val)","finalApprovalInd": "(val)","mandatoryRouteInd": "(val)","exceptionWorkgroupId": "(val)","routeMethodCode": "(val)","activationType": "(val)","nextDocStatus": "(val)","lockVerNbr": "(val)","nodeType": "(val)","_primaryKey": "(val)"},
              {"routeNodeId": "(val)","documentTypeId": "(val)","routeNodeName": "(val)","routeMethodName": "(val)","finalApprovalInd": "(val)","mandatoryRouteInd": "(val)","exceptionWorkgroupId": "(val)","routeMethodCode": "(val)","activationType": "(val)","nextDocStatus": "(val)","lockVerNbr": "(val)","nodeType": "(val)","_primaryKey": "(val)"}
            ]

### Get All Route Nodes with Filtering [GET /research-sys/api/v1/route-nodes/]
    
+ Parameters

    + routeNodeId (optional) - 
    + documentTypeId (optional) - 
    + routeNodeName (optional) - 
    + routeMethodName (optional) - 
    + finalApprovalInd (optional) - 
    + mandatoryRouteInd (optional) - 
    + exceptionWorkgroupId (optional) - 
    + routeMethodCode (optional) - 
    + activationType (optional) - 
    + nextDocStatus (optional) - 
    + lockVerNbr (optional) - 
    + nodeType (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"routeNodeId": "(val)","documentTypeId": "(val)","routeNodeName": "(val)","routeMethodName": "(val)","finalApprovalInd": "(val)","mandatoryRouteInd": "(val)","exceptionWorkgroupId": "(val)","routeMethodCode": "(val)","activationType": "(val)","nextDocStatus": "(val)","lockVerNbr": "(val)","nodeType": "(val)","_primaryKey": "(val)"},
              {"routeNodeId": "(val)","documentTypeId": "(val)","routeNodeName": "(val)","routeMethodName": "(val)","finalApprovalInd": "(val)","mandatoryRouteInd": "(val)","exceptionWorkgroupId": "(val)","routeMethodCode": "(val)","activationType": "(val)","nextDocStatus": "(val)","lockVerNbr": "(val)","nodeType": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Route Nodes [GET /research-sys/api/v1/route-nodes/]
	                                          
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
    
            {"columns":["routeNodeId","documentTypeId","routeNodeName","routeMethodName","finalApprovalInd","mandatoryRouteInd","exceptionWorkgroupId","routeMethodCode","activationType","nextDocStatus","lockVerNbr","nodeType"],"primaryKey":"routeNodeId"}
		
### Get Blueprint API specification for Route Nodes [GET /research-sys/api/v1/route-nodes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Route Nodes.md"
            transfer-encoding:chunked


### Update Route Nodes [PUT /research-sys/api/v1/route-nodes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"routeNodeId": "(val)","documentTypeId": "(val)","routeNodeName": "(val)","routeMethodName": "(val)","finalApprovalInd": "(val)","mandatoryRouteInd": "(val)","exceptionWorkgroupId": "(val)","routeMethodCode": "(val)","activationType": "(val)","nextDocStatus": "(val)","lockVerNbr": "(val)","nodeType": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Route Nodes [PUT /research-sys/api/v1/route-nodes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"routeNodeId": "(val)","documentTypeId": "(val)","routeNodeName": "(val)","routeMethodName": "(val)","finalApprovalInd": "(val)","mandatoryRouteInd": "(val)","exceptionWorkgroupId": "(val)","routeMethodCode": "(val)","activationType": "(val)","nextDocStatus": "(val)","lockVerNbr": "(val)","nodeType": "(val)","_primaryKey": "(val)"},
              {"routeNodeId": "(val)","documentTypeId": "(val)","routeNodeName": "(val)","routeMethodName": "(val)","finalApprovalInd": "(val)","mandatoryRouteInd": "(val)","exceptionWorkgroupId": "(val)","routeMethodCode": "(val)","activationType": "(val)","nextDocStatus": "(val)","lockVerNbr": "(val)","nodeType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Route Nodes [POST /research-sys/api/v1/route-nodes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"routeNodeId": "(val)","documentTypeId": "(val)","routeNodeName": "(val)","routeMethodName": "(val)","finalApprovalInd": "(val)","mandatoryRouteInd": "(val)","exceptionWorkgroupId": "(val)","routeMethodCode": "(val)","activationType": "(val)","nextDocStatus": "(val)","lockVerNbr": "(val)","nodeType": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"routeNodeId": "(val)","documentTypeId": "(val)","routeNodeName": "(val)","routeMethodName": "(val)","finalApprovalInd": "(val)","mandatoryRouteInd": "(val)","exceptionWorkgroupId": "(val)","routeMethodCode": "(val)","activationType": "(val)","nextDocStatus": "(val)","lockVerNbr": "(val)","nodeType": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Route Nodes [POST /research-sys/api/v1/route-nodes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"routeNodeId": "(val)","documentTypeId": "(val)","routeNodeName": "(val)","routeMethodName": "(val)","finalApprovalInd": "(val)","mandatoryRouteInd": "(val)","exceptionWorkgroupId": "(val)","routeMethodCode": "(val)","activationType": "(val)","nextDocStatus": "(val)","lockVerNbr": "(val)","nodeType": "(val)","_primaryKey": "(val)"},
              {"routeNodeId": "(val)","documentTypeId": "(val)","routeNodeName": "(val)","routeMethodName": "(val)","finalApprovalInd": "(val)","mandatoryRouteInd": "(val)","exceptionWorkgroupId": "(val)","routeMethodCode": "(val)","activationType": "(val)","nextDocStatus": "(val)","lockVerNbr": "(val)","nodeType": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"routeNodeId": "(val)","documentTypeId": "(val)","routeNodeName": "(val)","routeMethodName": "(val)","finalApprovalInd": "(val)","mandatoryRouteInd": "(val)","exceptionWorkgroupId": "(val)","routeMethodCode": "(val)","activationType": "(val)","nextDocStatus": "(val)","lockVerNbr": "(val)","nodeType": "(val)","_primaryKey": "(val)"},
              {"routeNodeId": "(val)","documentTypeId": "(val)","routeNodeName": "(val)","routeMethodName": "(val)","finalApprovalInd": "(val)","mandatoryRouteInd": "(val)","exceptionWorkgroupId": "(val)","routeMethodCode": "(val)","activationType": "(val)","nextDocStatus": "(val)","lockVerNbr": "(val)","nodeType": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Route Nodes by Key [DELETE /research-sys/api/v1/route-nodes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Route Nodes [DELETE /research-sys/api/v1/route-nodes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Route Nodes with Matching [DELETE /research-sys/api/v1/route-nodes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + routeNodeId (optional) - 
    + documentTypeId (optional) - 
    + routeNodeName (optional) - 
    + routeMethodName (optional) - 
    + finalApprovalInd (optional) - 
    + mandatoryRouteInd (optional) - 
    + exceptionWorkgroupId (optional) - 
    + routeMethodCode (optional) - 
    + activationType (optional) - 
    + nextDocStatus (optional) - 
    + lockVerNbr (optional) - 
    + nodeType (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
