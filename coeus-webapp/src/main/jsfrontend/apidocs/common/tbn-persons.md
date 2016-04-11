## Tbn Persons [/research-sys/api/v1/tbn-persons/]

### Get Tbn Persons by Key [GET /research-sys/api/v1/tbn-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"}

### Get All Tbn Persons [GET /research-sys/api/v1/tbn-persons/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"},
              {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Tbn Persons with Filtering [GET /research-sys/api/v1/tbn-persons/]
    
+ Parameters

        + tbnId
            + personName
            + jobCode

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"},
              {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Tbn Persons [GET /research-sys/api/v1/tbn-persons/]
	                                          
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
    
            {"columns":["tbnId","personName","jobCode"],"primaryKey":"tbnId"}
		
### Get Blueprint API specification for Tbn Persons [GET /research-sys/api/v1/tbn-persons/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Tbn Persons.md"
            transfer-encoding:chunked


### Update Tbn Persons [PUT /research-sys/api/v1/tbn-persons/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Tbn Persons [PUT /research-sys/api/v1/tbn-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"},
              {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Tbn Persons [POST /research-sys/api/v1/tbn-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Tbn Persons [POST /research-sys/api/v1/tbn-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"},
              {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"},
              {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Tbn Persons by Key [DELETE /research-sys/api/v1/tbn-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Tbn Persons [DELETE /research-sys/api/v1/tbn-persons/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Tbn Persons with Matching [DELETE /research-sys/api/v1/tbn-persons/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + tbnId
            + personName
            + jobCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
