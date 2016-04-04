## Valid Ce Job Codes [/research-sys/api/v1/valid-ce-job-codes/]

### Get Valid Ce Job Codes by Key [GET /research-sys/api/v1/valid-ce-job-codes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"costElement": "(val)","jobCode": "(val)","_primaryKey": "(val)"}

### Get All Valid Ce Job Codes [GET /research-sys/api/v1/valid-ce-job-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"costElement": "(val)","jobCode": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Valid Ce Job Codes with Filtering [GET /research-sys/api/v1/valid-ce-job-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + costElement
            + jobCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"costElement": "(val)","jobCode": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Valid Ce Job Codes [GET /research-sys/api/v1/valid-ce-job-codes/]
	 
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
		
### Get Blueprint API specification for Valid Ce Job Codes [GET /research-sys/api/v1/valid-ce-job-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Ce Job Codes.md"
            transfer-encoding:chunked


### Update Valid Ce Job Codes [PUT /research-sys/api/v1/valid-ce-job-codes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costElement": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Ce Job Codes [PUT /research-sys/api/v1/valid-ce-job-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"costElement": "(val)","jobCode": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Valid Ce Job Codes [POST /research-sys/api/v1/valid-ce-job-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"costElement": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"costElement": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Ce Job Codes [POST /research-sys/api/v1/valid-ce-job-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"costElement": "(val)","jobCode": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"costElement": "(val)","jobCode": "(val)","_primaryKey": "(val)"},
              {"costElement": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Valid Ce Job Codes by Key [DELETE /research-sys/api/v1/valid-ce-job-codes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Ce Job Codes [DELETE /research-sys/api/v1/valid-ce-job-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Valid Ce Job Codes with Matching [DELETE /research-sys/api/v1/valid-ce-job-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + costElement
            + jobCode


+ Response 204
