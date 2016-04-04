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
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + routeNodeId
            + documentTypeId
            + routeNodeName
            + routeMethodName
            + finalApprovalInd
            + mandatoryRouteInd
            + exceptionWorkgroupId
            + routeMethodCode
            + activationType
            + nextDocStatus
            + lockVerNbr
            + nodeType
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"routeNodeId": "(val)","documentTypeId": "(val)","routeNodeName": "(val)","routeMethodName": "(val)","finalApprovalInd": "(val)","mandatoryRouteInd": "(val)","exceptionWorkgroupId": "(val)","routeMethodCode": "(val)","activationType": "(val)","nextDocStatus": "(val)","lockVerNbr": "(val)","nodeType": "(val)","_primaryKey": "(val)"},
              {"routeNodeId": "(val)","documentTypeId": "(val)","routeNodeName": "(val)","routeMethodName": "(val)","finalApprovalInd": "(val)","mandatoryRouteInd": "(val)","exceptionWorkgroupId": "(val)","routeMethodCode": "(val)","activationType": "(val)","nextDocStatus": "(val)","lockVerNbr": "(val)","nodeType": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Route Nodes [GET /research-sys/api/v1/route-nodes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Route Nodes [GET /research-sys/api/v1/route-nodes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

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
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Route Nodes with Matching [DELETE /research-sys/api/v1/route-nodes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + routeNodeId
            + documentTypeId
            + routeNodeName
            + routeMethodName
            + finalApprovalInd
            + mandatoryRouteInd
            + exceptionWorkgroupId
            + routeMethodCode
            + activationType
            + nextDocStatus
            + lockVerNbr
            + nodeType


+ Response 204
