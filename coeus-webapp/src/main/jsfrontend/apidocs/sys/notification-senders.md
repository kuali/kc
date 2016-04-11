## Notification Senders [/research-sys/api/v1/notification-senders/]

### Get Notification Senders by Key [GET /research-sys/api/v1/notification-senders/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","senderName": "(val)","_primaryKey": "(val)"}

### Get All Notification Senders [GET /research-sys/api/v1/notification-senders/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","senderName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","senderName": "(val)","_primaryKey": "(val)"}
            ]

### Get All Notification Senders with Filtering [GET /research-sys/api/v1/notification-senders/]
    
+ Parameters

        + id
            + senderName

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","senderName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","senderName": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Notification Senders [GET /research-sys/api/v1/notification-senders/]
	                                          
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
    
            {"columns":["id","senderName"],"primaryKey":"id"}
		
### Get Blueprint API specification for Notification Senders [GET /research-sys/api/v1/notification-senders/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Notification Senders.md"
            transfer-encoding:chunked


### Update Notification Senders [PUT /research-sys/api/v1/notification-senders/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","senderName": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Notification Senders [PUT /research-sys/api/v1/notification-senders/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","senderName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","senderName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Notification Senders [POST /research-sys/api/v1/notification-senders/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","senderName": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","senderName": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Notification Senders [POST /research-sys/api/v1/notification-senders/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","senderName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","senderName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","senderName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","senderName": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Notification Senders by Key [DELETE /research-sys/api/v1/notification-senders/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Senders [DELETE /research-sys/api/v1/notification-senders/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Senders with Matching [DELETE /research-sys/api/v1/notification-senders/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + senderName

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
