## Proposition Parameters [/research-sys/api/v1/proposition-parameters/]

### Get Proposition Parameters by Key [GET /research-sys/api/v1/proposition-parameters/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","value": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}

### Get All Proposition Parameters [GET /research-sys/api/v1/proposition-parameters/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","value": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","value": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposition Parameters with Filtering [GET /research-sys/api/v1/proposition-parameters/]
    
+ Parameters

    + id (optional) - Proposition Parameter Id.
    + value (optional) - Value.
    + parameterType (optional) - Parameter Type Code.
    + sequenceNumber (optional) - Sequence Number.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","value": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","value": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposition Parameters [GET /research-sys/api/v1/proposition-parameters/]
	                                          
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
    
            {"columns":["id","value","parameterType","sequenceNumber"],"primaryKey":"id"}
		
### Get Blueprint API specification for Proposition Parameters [GET /research-sys/api/v1/proposition-parameters/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposition Parameters.md"
            transfer-encoding:chunked
### Update Proposition Parameters [PUT /research-sys/api/v1/proposition-parameters/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","value": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposition Parameters [PUT /research-sys/api/v1/proposition-parameters/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","value": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","value": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Proposition Parameters [POST /research-sys/api/v1/proposition-parameters/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","value": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","value": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposition Parameters [POST /research-sys/api/v1/proposition-parameters/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","value": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","value": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","value": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","value": "(val)","parameterType": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
### Delete Proposition Parameters by Key [DELETE /research-sys/api/v1/proposition-parameters/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposition Parameters [DELETE /research-sys/api/v1/proposition-parameters/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposition Parameters with Matching [DELETE /research-sys/api/v1/proposition-parameters/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Proposition Parameter Id.
    + value (optional) - Value.
    + parameterType (optional) - Parameter Type Code.
    + sequenceNumber (optional) - Sequence Number.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
