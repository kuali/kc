## Common Committee Documents [/research-sys/api/v1/common-committee-documents/]

### Get Common Committee Documents by Key [GET /research-sys/api/v1/common-committee-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"}

### Get All Common Committee Documents [GET /research-sys/api/v1/common-committee-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Common Committee Documents with Filtering [GET /research-sys/api/v1/common-committee-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + documentNumber
            + committeeId
            + docStatusCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Common Committee Documents [GET /research-sys/api/v1/common-committee-documents/]
	 
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
		
### Get Blueprint API specification for Common Committee Documents [GET /research-sys/api/v1/common-committee-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Common Committee Documents.md"
            transfer-encoding:chunked


### Update Common Committee Documents [PUT /research-sys/api/v1/common-committee-documents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Common Committee Documents [PUT /research-sys/api/v1/common-committee-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Common Committee Documents [POST /research-sys/api/v1/common-committee-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Common Committee Documents [POST /research-sys/api/v1/common-committee-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Common Committee Documents by Key [DELETE /research-sys/api/v1/common-committee-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Common Committee Documents [DELETE /research-sys/api/v1/common-committee-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Common Committee Documents with Matching [DELETE /research-sys/api/v1/common-committee-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + documentNumber
            + committeeId
            + docStatusCode


+ Response 204
