## Branch Prototypes [/research-sys/api/v1/branch-prototypes/]

### Get Branch Prototypes by Key [GET /research-sys/api/v1/branch-prototypes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}

### Get All Branch Prototypes [GET /research-sys/api/v1/branch-prototypes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]

### Get All Branch Prototypes with Filtering [GET /research-sys/api/v1/branch-prototypes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + branchId
            + name
            + lockVerNbr
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Branch Prototypes [GET /research-sys/api/v1/branch-prototypes/]
	 
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
		
### Get Blueprint API specification for Branch Prototypes [GET /research-sys/api/v1/branch-prototypes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Branch Prototypes.md"
            transfer-encoding:chunked


### Update Branch Prototypes [PUT /research-sys/api/v1/branch-prototypes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Branch Prototypes [PUT /research-sys/api/v1/branch-prototypes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Branch Prototypes [POST /research-sys/api/v1/branch-prototypes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Branch Prototypes [POST /research-sys/api/v1/branch-prototypes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Branch Prototypes by Key [DELETE /research-sys/api/v1/branch-prototypes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Branch Prototypes [DELETE /research-sys/api/v1/branch-prototypes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Branch Prototypes with Matching [DELETE /research-sys/api/v1/branch-prototypes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + branchId
            + name
            + lockVerNbr


+ Response 204
