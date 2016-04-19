## B A M Target Entries [/research-sys/api/v1/b-a-m-target-entries/]

### Get B A M Target Entries by Key [GET /research-sys/api/v1/b-a-m-target-entries/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"bamId": "(val)","serviceName": "(val)","methodName": "(val)","threadName": "(val)","callDate": "(val)","serviceURL": "(val)","targetToString": "(val)","exceptionToString": "(val)","exceptionMessage": "(val)","serverInvocation": "(val)","_primaryKey": "(val)"}

### Get All B A M Target Entries [GET /research-sys/api/v1/b-a-m-target-entries/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"bamId": "(val)","serviceName": "(val)","methodName": "(val)","threadName": "(val)","callDate": "(val)","serviceURL": "(val)","targetToString": "(val)","exceptionToString": "(val)","exceptionMessage": "(val)","serverInvocation": "(val)","_primaryKey": "(val)"},
              {"bamId": "(val)","serviceName": "(val)","methodName": "(val)","threadName": "(val)","callDate": "(val)","serviceURL": "(val)","targetToString": "(val)","exceptionToString": "(val)","exceptionMessage": "(val)","serverInvocation": "(val)","_primaryKey": "(val)"}
            ]

### Get All B A M Target Entries with Filtering [GET /research-sys/api/v1/b-a-m-target-entries/]
    
+ Parameters

    + bamId (optional) - 
    + serviceName (optional) - 
    + methodName (optional) - 
    + threadName (optional) - 
    + callDate (optional) - 
    + serviceURL (optional) - 
    + targetToString (optional) - 
    + exceptionToString (optional) - 
    + exceptionMessage (optional) - 
    + serverInvocation (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"bamId": "(val)","serviceName": "(val)","methodName": "(val)","threadName": "(val)","callDate": "(val)","serviceURL": "(val)","targetToString": "(val)","exceptionToString": "(val)","exceptionMessage": "(val)","serverInvocation": "(val)","_primaryKey": "(val)"},
              {"bamId": "(val)","serviceName": "(val)","methodName": "(val)","threadName": "(val)","callDate": "(val)","serviceURL": "(val)","targetToString": "(val)","exceptionToString": "(val)","exceptionMessage": "(val)","serverInvocation": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for B A M Target Entries [GET /research-sys/api/v1/b-a-m-target-entries/]
	                                          
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
    
            {"columns":["bamId","serviceName","methodName","threadName","callDate","serviceURL","targetToString","exceptionToString","exceptionMessage","serverInvocation"],"primaryKey":"bamId"}
		
### Get Blueprint API specification for B A M Target Entries [GET /research-sys/api/v1/b-a-m-target-entries/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="B A M Target Entries.md"
            transfer-encoding:chunked


### Update B A M Target Entries [PUT /research-sys/api/v1/b-a-m-target-entries/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"bamId": "(val)","serviceName": "(val)","methodName": "(val)","threadName": "(val)","callDate": "(val)","serviceURL": "(val)","targetToString": "(val)","exceptionToString": "(val)","exceptionMessage": "(val)","serverInvocation": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple B A M Target Entries [PUT /research-sys/api/v1/b-a-m-target-entries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"bamId": "(val)","serviceName": "(val)","methodName": "(val)","threadName": "(val)","callDate": "(val)","serviceURL": "(val)","targetToString": "(val)","exceptionToString": "(val)","exceptionMessage": "(val)","serverInvocation": "(val)","_primaryKey": "(val)"},
              {"bamId": "(val)","serviceName": "(val)","methodName": "(val)","threadName": "(val)","callDate": "(val)","serviceURL": "(val)","targetToString": "(val)","exceptionToString": "(val)","exceptionMessage": "(val)","serverInvocation": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert B A M Target Entries [POST /research-sys/api/v1/b-a-m-target-entries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"bamId": "(val)","serviceName": "(val)","methodName": "(val)","threadName": "(val)","callDate": "(val)","serviceURL": "(val)","targetToString": "(val)","exceptionToString": "(val)","exceptionMessage": "(val)","serverInvocation": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"bamId": "(val)","serviceName": "(val)","methodName": "(val)","threadName": "(val)","callDate": "(val)","serviceURL": "(val)","targetToString": "(val)","exceptionToString": "(val)","exceptionMessage": "(val)","serverInvocation": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple B A M Target Entries [POST /research-sys/api/v1/b-a-m-target-entries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"bamId": "(val)","serviceName": "(val)","methodName": "(val)","threadName": "(val)","callDate": "(val)","serviceURL": "(val)","targetToString": "(val)","exceptionToString": "(val)","exceptionMessage": "(val)","serverInvocation": "(val)","_primaryKey": "(val)"},
              {"bamId": "(val)","serviceName": "(val)","methodName": "(val)","threadName": "(val)","callDate": "(val)","serviceURL": "(val)","targetToString": "(val)","exceptionToString": "(val)","exceptionMessage": "(val)","serverInvocation": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"bamId": "(val)","serviceName": "(val)","methodName": "(val)","threadName": "(val)","callDate": "(val)","serviceURL": "(val)","targetToString": "(val)","exceptionToString": "(val)","exceptionMessage": "(val)","serverInvocation": "(val)","_primaryKey": "(val)"},
              {"bamId": "(val)","serviceName": "(val)","methodName": "(val)","threadName": "(val)","callDate": "(val)","serviceURL": "(val)","targetToString": "(val)","exceptionToString": "(val)","exceptionMessage": "(val)","serverInvocation": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete B A M Target Entries by Key [DELETE /research-sys/api/v1/b-a-m-target-entries/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All B A M Target Entries [DELETE /research-sys/api/v1/b-a-m-target-entries/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All B A M Target Entries with Matching [DELETE /research-sys/api/v1/b-a-m-target-entries/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + bamId (optional) - 
    + serviceName (optional) - 
    + methodName (optional) - 
    + threadName (optional) - 
    + callDate (optional) - 
    + serviceURL (optional) - 
    + targetToString (optional) - 
    + exceptionToString (optional) - 
    + exceptionMessage (optional) - 
    + serverInvocation (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
