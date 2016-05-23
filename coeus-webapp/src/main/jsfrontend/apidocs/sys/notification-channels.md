## Notification Channels [/research-sys/api/v1/notification-channels/]

### Get Notification Channels by Key [GET /research-sys/api/v1/notification-channels/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","subscribable": "(val)","_primaryKey": "(val)"}

### Get All Notification Channels [GET /research-sys/api/v1/notification-channels/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","subscribable": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","subscribable": "(val)","_primaryKey": "(val)"}
            ]

### Get All Notification Channels with Filtering [GET /research-sys/api/v1/notification-channels/]
    
+ Parameters

    + id (optional) - 
    + name (optional) - 
    + description (optional) - 
    + subscribable (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","subscribable": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","subscribable": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Notification Channels [GET /research-sys/api/v1/notification-channels/]
	                                          
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
    
            {"columns":["id","name","description","subscribable"],"primaryKey":"id"}
		
### Get Blueprint API specification for Notification Channels [GET /research-sys/api/v1/notification-channels/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Notification Channels.md"
            transfer-encoding:chunked
### Update Notification Channels [PUT /research-sys/api/v1/notification-channels/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","subscribable": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Notification Channels [PUT /research-sys/api/v1/notification-channels/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","subscribable": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","subscribable": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Notification Channels [POST /research-sys/api/v1/notification-channels/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","description": "(val)","subscribable": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","description": "(val)","subscribable": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Notification Channels [POST /research-sys/api/v1/notification-channels/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","description": "(val)","subscribable": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","subscribable": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","description": "(val)","subscribable": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","description": "(val)","subscribable": "(val)","_primaryKey": "(val)"}
            ]
### Delete Notification Channels by Key [DELETE /research-sys/api/v1/notification-channels/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Channels [DELETE /research-sys/api/v1/notification-channels/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Channels with Matching [DELETE /research-sys/api/v1/notification-channels/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + name (optional) - 
    + description (optional) - 
    + subscribable (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
