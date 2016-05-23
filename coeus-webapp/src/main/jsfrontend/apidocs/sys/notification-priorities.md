## Notification Priorities [/research-sys/api/v1/notification-priorities/]

### Get Notification Priorities by Key [GET /research-sys/api/v1/notification-priorities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","order": "(val)","_primaryKey": "(val)"}

### Get All Notification Priorities [GET /research-sys/api/v1/notification-priorities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","order": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","order": "(val)","_primaryKey": "(val)"}
            ]

### Get All Notification Priorities with Filtering [GET /research-sys/api/v1/notification-priorities/]
    
+ Parameters

    + id (optional) - 
    + name (optional) - 
    + description (optional) - 
    + order (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","order": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","order": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Notification Priorities [GET /research-sys/api/v1/notification-priorities/]
	                                          
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
    
            {"columns":["id","name","description","order"],"primaryKey":"id"}
		
### Get Blueprint API specification for Notification Priorities [GET /research-sys/api/v1/notification-priorities/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Notification Priorities.md"
            transfer-encoding:chunked
### Update Notification Priorities [PUT /research-sys/api/v1/notification-priorities/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","order": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Notification Priorities [PUT /research-sys/api/v1/notification-priorities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","order": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","order": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Notification Priorities [POST /research-sys/api/v1/notification-priorities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","order": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","description": "(val)","order": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Notification Priorities [POST /research-sys/api/v1/notification-priorities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","order": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","order": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","description": "(val)","order": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","order": "(val)","_primaryKey": "(val)"}
            ]
### Delete Notification Priorities by Key [DELETE /research-sys/api/v1/notification-priorities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Priorities [DELETE /research-sys/api/v1/notification-priorities/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Priorities with Matching [DELETE /research-sys/api/v1/notification-priorities/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + name (optional) - 
    + description (optional) - 
    + order (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
