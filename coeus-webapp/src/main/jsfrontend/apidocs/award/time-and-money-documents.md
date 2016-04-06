## Time And Money Documents [/research-sys/api/v1/time-and-money-documents/]

### Get Time And Money Documents by Key [GET /research-sys/api/v1/time-and-money-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"documentNumber": "(val)","rootAwardNumber": "(val)","documentStatus": "(val)","_primaryKey": "(val)"}

### Get All Time And Money Documents [GET /research-sys/api/v1/time-and-money-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentNumber": "(val)","rootAwardNumber": "(val)","documentStatus": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","rootAwardNumber": "(val)","documentStatus": "(val)","_primaryKey": "(val)"}
            ]

### Get All Time And Money Documents with Filtering [GET /research-sys/api/v1/time-and-money-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + documentNumber
            + rootAwardNumber
            + documentStatus
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"documentNumber": "(val)","rootAwardNumber": "(val)","documentStatus": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","rootAwardNumber": "(val)","documentStatus": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Time And Money Documents [GET /research-sys/api/v1/time-and-money-documents/]
	 
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
		
### Get Blueprint API specification for Time And Money Documents [GET /research-sys/api/v1/time-and-money-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Time And Money Documents.md"
            transfer-encoding:chunked


### Update Time And Money Documents [PUT /research-sys/api/v1/time-and-money-documents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentNumber": "(val)","rootAwardNumber": "(val)","documentStatus": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Time And Money Documents [PUT /research-sys/api/v1/time-and-money-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentNumber": "(val)","rootAwardNumber": "(val)","documentStatus": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","rootAwardNumber": "(val)","documentStatus": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Time And Money Documents [POST /research-sys/api/v1/time-and-money-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"documentNumber": "(val)","rootAwardNumber": "(val)","documentStatus": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"documentNumber": "(val)","rootAwardNumber": "(val)","documentStatus": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Time And Money Documents [POST /research-sys/api/v1/time-and-money-documents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"documentNumber": "(val)","rootAwardNumber": "(val)","documentStatus": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","rootAwardNumber": "(val)","documentStatus": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"documentNumber": "(val)","rootAwardNumber": "(val)","documentStatus": "(val)","_primaryKey": "(val)"},
              {"documentNumber": "(val)","rootAwardNumber": "(val)","documentStatus": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Time And Money Documents by Key [DELETE /research-sys/api/v1/time-and-money-documents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Time And Money Documents [DELETE /research-sys/api/v1/time-and-money-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Time And Money Documents with Matching [DELETE /research-sys/api/v1/time-and-money-documents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + documentNumber
            + rootAwardNumber
            + documentStatus


+ Response 204
