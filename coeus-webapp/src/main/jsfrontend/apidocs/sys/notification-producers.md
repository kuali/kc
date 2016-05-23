## Notification Producers [/research-sys/api/v1/notification-producers/]

### Get Notification Producers by Key [GET /research-sys/api/v1/notification-producers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","contactInfo": "(val)","_primaryKey": "(val)"}

### Get All Notification Producers [GET /research-sys/api/v1/notification-producers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","contactInfo": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","contactInfo": "(val)","_primaryKey": "(val)"}
            ]

### Get All Notification Producers with Filtering [GET /research-sys/api/v1/notification-producers/]
    
+ Parameters

    + id (optional) - 
    + name (optional) - 
    + description (optional) - 
    + contactInfo (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","contactInfo": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","contactInfo": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Notification Producers [GET /research-sys/api/v1/notification-producers/]
	                                          
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
    
            {"columns":["id","name","description","contactInfo"],"primaryKey":"id"}
		
### Get Blueprint API specification for Notification Producers [GET /research-sys/api/v1/notification-producers/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Notification Producers.md"
            transfer-encoding:chunked
### Update Notification Producers [PUT /research-sys/api/v1/notification-producers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","contactInfo": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Notification Producers [PUT /research-sys/api/v1/notification-producers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","contactInfo": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","contactInfo": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Notification Producers [POST /research-sys/api/v1/notification-producers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","contactInfo": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","description": "(val)","contactInfo": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Notification Producers [POST /research-sys/api/v1/notification-producers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","contactInfo": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","contactInfo": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","description": "(val)","contactInfo": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","contactInfo": "(val)","_primaryKey": "(val)"}
            ]
### Delete Notification Producers by Key [DELETE /research-sys/api/v1/notification-producers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Producers [DELETE /research-sys/api/v1/notification-producers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Producers with Matching [DELETE /research-sys/api/v1/notification-producers/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + name (optional) - 
    + description (optional) - 
    + contactInfo (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
