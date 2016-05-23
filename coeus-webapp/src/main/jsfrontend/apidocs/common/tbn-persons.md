## Tbn Persons [/research-common/api/v1/tbn-persons/]

### Get Tbn Persons by Key [GET /research-common/api/v1/tbn-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"}

### Get All Tbn Persons [GET /research-common/api/v1/tbn-persons/]
	 
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

### Get All Tbn Persons with Filtering [GET /research-common/api/v1/tbn-persons/]
    
+ Parameters

    + tbnId (optional) - TBN Id. Maximum length is 9.
    + personName (optional) - Person Name. Maximum length is 90.
    + jobCode (optional) - Job Code. Maximum length is 6.

            
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
			
### Get Schema for Tbn Persons [GET /research-common/api/v1/tbn-persons/]
	                                          
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
		
### Get Blueprint API specification for Tbn Persons [GET /research-common/api/v1/tbn-persons/]
	 
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
### Update Tbn Persons [PUT /research-common/api/v1/tbn-persons/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Tbn Persons [PUT /research-common/api/v1/tbn-persons/]

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
### Insert Tbn Persons [POST /research-common/api/v1/tbn-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"tbnId": "(val)","personName": "(val)","jobCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Tbn Persons [POST /research-common/api/v1/tbn-persons/]

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
### Delete Tbn Persons by Key [DELETE /research-common/api/v1/tbn-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Tbn Persons [DELETE /research-common/api/v1/tbn-persons/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Tbn Persons with Matching [DELETE /research-common/api/v1/tbn-persons/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + tbnId (optional) - TBN Id. Maximum length is 9.
    + personName (optional) - Person Name. Maximum length is 90.
    + jobCode (optional) - Job Code. Maximum length is 6.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
