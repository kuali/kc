## Branches [/research-sys/api/v1/branches/]

### Get Branches by Key [GET /research-sys/api/v1/branches/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}

### Get All Branches [GET /research-sys/api/v1/branches/]
	 
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

### Get All Branches with Filtering [GET /research-sys/api/v1/branches/]
	 
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
			
### Get Schema for Branches [GET /research-sys/api/v1/branches/]
	 
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
		
### Get Blueprint API specification for Branches [GET /research-sys/api/v1/branches/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Branches.md"
            transfer-encoding:chunked


### Update Branches [PUT /research-sys/api/v1/branches/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Branches [PUT /research-sys/api/v1/branches/]

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

### Insert Branches [POST /research-sys/api/v1/branches/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"branchId": "(val)","name": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Branches [POST /research-sys/api/v1/branches/]

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
            
### Delete Branches by Key [DELETE /research-sys/api/v1/branches/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Branches [DELETE /research-sys/api/v1/branches/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Branches with Matching [DELETE /research-sys/api/v1/branches/]
	 
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
