## Job Codes [/research-sys/api/v1/job-codes/]

### Get Job Codes by Key [GET /research-sys/api/v1/job-codes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"jobCode": "(val)","jobTitle": "(val)","_primaryKey": "(val)"}

### Get All Job Codes [GET /research-sys/api/v1/job-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"jobCode": "(val)","jobTitle": "(val)","_primaryKey": "(val)"},
              {"jobCode": "(val)","jobTitle": "(val)","_primaryKey": "(val)"}
            ]

### Get All Job Codes with Filtering [GET /research-sys/api/v1/job-codes/]
    
+ Parameters

        + jobCode
            + jobTitle

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"jobCode": "(val)","jobTitle": "(val)","_primaryKey": "(val)"},
              {"jobCode": "(val)","jobTitle": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Job Codes [GET /research-sys/api/v1/job-codes/]
	                                          
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
    
            {"columns":["jobCode","jobTitle"],"primaryKey":"jobCode"}
		
### Get Blueprint API specification for Job Codes [GET /research-sys/api/v1/job-codes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Job Codes.md"
            transfer-encoding:chunked


### Update Job Codes [PUT /research-sys/api/v1/job-codes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"jobCode": "(val)","jobTitle": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Job Codes [PUT /research-sys/api/v1/job-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"jobCode": "(val)","jobTitle": "(val)","_primaryKey": "(val)"},
              {"jobCode": "(val)","jobTitle": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Job Codes [POST /research-sys/api/v1/job-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"jobCode": "(val)","jobTitle": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"jobCode": "(val)","jobTitle": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Job Codes [POST /research-sys/api/v1/job-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"jobCode": "(val)","jobTitle": "(val)","_primaryKey": "(val)"},
              {"jobCode": "(val)","jobTitle": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"jobCode": "(val)","jobTitle": "(val)","_primaryKey": "(val)"},
              {"jobCode": "(val)","jobTitle": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Job Codes by Key [DELETE /research-sys/api/v1/job-codes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Job Codes [DELETE /research-sys/api/v1/job-codes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Job Codes with Matching [DELETE /research-sys/api/v1/job-codes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + jobCode
            + jobTitle

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
