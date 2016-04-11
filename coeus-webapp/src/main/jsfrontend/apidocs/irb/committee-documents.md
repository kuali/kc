## Committee Documents [/research-sys/api/v1/committee-documents/]

### Get Committee Documents by Key [GET /research-sys/api/v1/committee-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"}

### Get All Committee Documents [GET /research-sys/api/v1/committee-documents/]
	 
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

### Get All Committee Documents with Filtering [GET /research-sys/api/v1/committee-documents/]
    
+ Parameters

        + documentNumber
            + committeeId
            + docStatusCode

            
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
			
### Get Schema for Committee Documents [GET /research-sys/api/v1/committee-documents/]
	                                          
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
    
            {"columns":["documentNumber","committeeId","docStatusCode"],"primaryKey":"documentNumber"}
		
### Get Blueprint API specification for Committee Documents [GET /research-sys/api/v1/committee-documents/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committee Documents.md"
            transfer-encoding:chunked


### Update Committee Documents [PUT /research-sys/api/v1/committee-documents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Documents [PUT /research-sys/api/v1/committee-documents/]

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

### Insert Committee Documents [POST /research-sys/api/v1/committee-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"documentNumber": "(val)","committeeId": "(val)","docStatusCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Documents [POST /research-sys/api/v1/committee-documents/]

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
            
### Delete Committee Documents by Key [DELETE /research-sys/api/v1/committee-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Documents [DELETE /research-sys/api/v1/committee-documents/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Documents with Matching [DELETE /research-sys/api/v1/committee-documents/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + documentNumber
            + committeeId
            + docStatusCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
