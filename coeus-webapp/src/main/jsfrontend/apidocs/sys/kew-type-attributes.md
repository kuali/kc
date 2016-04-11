## Kew Type Attributes [/research-sys/api/v1/kew-type-attributes/]

### Get Kew Type Attributes by Key [GET /research-sys/api/v1/kew-type-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","sequenceNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Kew Type Attributes [GET /research-sys/api/v1/kew-type-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","sequenceNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sequenceNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kew Type Attributes with Filtering [GET /research-sys/api/v1/kew-type-attributes/]
    
+ Parameters

        + id
            + sequenceNumber
            + active

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","sequenceNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sequenceNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kew Type Attributes [GET /research-sys/api/v1/kew-type-attributes/]
	                                          
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
    
            {"columns":["id","sequenceNumber","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Kew Type Attributes [GET /research-sys/api/v1/kew-type-attributes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kew Type Attributes.md"
            transfer-encoding:chunked


### Update Kew Type Attributes [PUT /research-sys/api/v1/kew-type-attributes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","sequenceNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kew Type Attributes [PUT /research-sys/api/v1/kew-type-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","sequenceNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sequenceNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Kew Type Attributes [POST /research-sys/api/v1/kew-type-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","sequenceNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","sequenceNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kew Type Attributes [POST /research-sys/api/v1/kew-type-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","sequenceNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sequenceNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","sequenceNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","sequenceNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Kew Type Attributes by Key [DELETE /research-sys/api/v1/kew-type-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kew Type Attributes [DELETE /research-sys/api/v1/kew-type-attributes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kew Type Attributes with Matching [DELETE /research-sys/api/v1/kew-type-attributes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + sequenceNumber
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
