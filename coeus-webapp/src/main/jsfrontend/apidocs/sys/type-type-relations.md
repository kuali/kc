## Type Type Relations [/research-sys/api/v1/type-type-relations/]

### Get Type Type Relations by Key [GET /research-sys/api/v1/type-type-relations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"fromTypeId": "(val)","toTypeId": "(val)","relationshipType": "(val)","sequenceNumber": "(val)","id": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Type Type Relations [GET /research-sys/api/v1/type-type-relations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"fromTypeId": "(val)","toTypeId": "(val)","relationshipType": "(val)","sequenceNumber": "(val)","id": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"fromTypeId": "(val)","toTypeId": "(val)","relationshipType": "(val)","sequenceNumber": "(val)","id": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Type Type Relations with Filtering [GET /research-sys/api/v1/type-type-relations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + fromTypeId
            + toTypeId
            + relationshipType
            + sequenceNumber
            + id
            + active
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"fromTypeId": "(val)","toTypeId": "(val)","relationshipType": "(val)","sequenceNumber": "(val)","id": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"fromTypeId": "(val)","toTypeId": "(val)","relationshipType": "(val)","sequenceNumber": "(val)","id": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Type Type Relations [GET /research-sys/api/v1/type-type-relations/]
	 
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
		
### Get Blueprint API specification for Type Type Relations [GET /research-sys/api/v1/type-type-relations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Type Type Relations.md"
            transfer-encoding:chunked


### Update Type Type Relations [PUT /research-sys/api/v1/type-type-relations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"fromTypeId": "(val)","toTypeId": "(val)","relationshipType": "(val)","sequenceNumber": "(val)","id": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Type Type Relations [PUT /research-sys/api/v1/type-type-relations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"fromTypeId": "(val)","toTypeId": "(val)","relationshipType": "(val)","sequenceNumber": "(val)","id": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"fromTypeId": "(val)","toTypeId": "(val)","relationshipType": "(val)","sequenceNumber": "(val)","id": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Type Type Relations [POST /research-sys/api/v1/type-type-relations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"fromTypeId": "(val)","toTypeId": "(val)","relationshipType": "(val)","sequenceNumber": "(val)","id": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"fromTypeId": "(val)","toTypeId": "(val)","relationshipType": "(val)","sequenceNumber": "(val)","id": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Type Type Relations [POST /research-sys/api/v1/type-type-relations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"fromTypeId": "(val)","toTypeId": "(val)","relationshipType": "(val)","sequenceNumber": "(val)","id": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"fromTypeId": "(val)","toTypeId": "(val)","relationshipType": "(val)","sequenceNumber": "(val)","id": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"fromTypeId": "(val)","toTypeId": "(val)","relationshipType": "(val)","sequenceNumber": "(val)","id": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"fromTypeId": "(val)","toTypeId": "(val)","relationshipType": "(val)","sequenceNumber": "(val)","id": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Type Type Relations by Key [DELETE /research-sys/api/v1/type-type-relations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Type Type Relations [DELETE /research-sys/api/v1/type-type-relations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Type Type Relations with Matching [DELETE /research-sys/api/v1/type-type-relations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + fromTypeId
            + toTypeId
            + relationshipType
            + sequenceNumber
            + id
            + active


+ Response 204
