## Branch States [/research-sys/api/v1/branch-states/]

### Get Branch States by Key [GET /research-sys/api/v1/branch-states/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"lockVerNbr": "(val)","value": "(val)","key": "(val)","stateId": "(val)","_primaryKey": "(val)"}

### Get All Branch States [GET /research-sys/api/v1/branch-states/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"lockVerNbr": "(val)","value": "(val)","key": "(val)","stateId": "(val)","_primaryKey": "(val)"},
              {"lockVerNbr": "(val)","value": "(val)","key": "(val)","stateId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Branch States with Filtering [GET /research-sys/api/v1/branch-states/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + lockVerNbr
            + value
            + key
            + stateId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"lockVerNbr": "(val)","value": "(val)","key": "(val)","stateId": "(val)","_primaryKey": "(val)"},
              {"lockVerNbr": "(val)","value": "(val)","key": "(val)","stateId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Branch States [GET /research-sys/api/v1/branch-states/]
	 
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
		
### Get Blueprint API specification for Branch States [GET /research-sys/api/v1/branch-states/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Branch States.md"
            transfer-encoding:chunked


### Update Branch States [PUT /research-sys/api/v1/branch-states/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"lockVerNbr": "(val)","value": "(val)","key": "(val)","stateId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Branch States [PUT /research-sys/api/v1/branch-states/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"lockVerNbr": "(val)","value": "(val)","key": "(val)","stateId": "(val)","_primaryKey": "(val)"},
              {"lockVerNbr": "(val)","value": "(val)","key": "(val)","stateId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Branch States [POST /research-sys/api/v1/branch-states/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"lockVerNbr": "(val)","value": "(val)","key": "(val)","stateId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"lockVerNbr": "(val)","value": "(val)","key": "(val)","stateId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Branch States [POST /research-sys/api/v1/branch-states/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"lockVerNbr": "(val)","value": "(val)","key": "(val)","stateId": "(val)","_primaryKey": "(val)"},
              {"lockVerNbr": "(val)","value": "(val)","key": "(val)","stateId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"lockVerNbr": "(val)","value": "(val)","key": "(val)","stateId": "(val)","_primaryKey": "(val)"},
              {"lockVerNbr": "(val)","value": "(val)","key": "(val)","stateId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Branch States by Key [DELETE /research-sys/api/v1/branch-states/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Branch States [DELETE /research-sys/api/v1/branch-states/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Branch States with Matching [DELETE /research-sys/api/v1/branch-states/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + lockVerNbr
            + value
            + key
            + stateId


+ Response 204
