## School Codes [/research-sys/api/v1/school-codes/]

### Get School Codes by Key [GET /research-sys/api/v1/school-codes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"schoolCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All School Codes [GET /research-sys/api/v1/school-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"schoolCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"schoolCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All School Codes with Filtering [GET /research-sys/api/v1/school-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + schoolCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"schoolCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"schoolCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for School Codes [GET /research-sys/api/v1/school-codes/]
	 
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
		
### Get Blueprint API specification for School Codes [GET /research-sys/api/v1/school-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="School Codes.md"
            transfer-encoding:chunked


### Update School Codes [PUT /research-sys/api/v1/school-codes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"schoolCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple School Codes [PUT /research-sys/api/v1/school-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"schoolCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"schoolCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert School Codes [POST /research-sys/api/v1/school-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"schoolCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"schoolCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple School Codes [POST /research-sys/api/v1/school-codes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"schoolCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"schoolCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"schoolCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"schoolCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete School Codes by Key [DELETE /research-sys/api/v1/school-codes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All School Codes [DELETE /research-sys/api/v1/school-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All School Codes with Matching [DELETE /research-sys/api/v1/school-codes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + schoolCode
            + description


+ Response 204
