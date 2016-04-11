## Notification Recipients [/research-sys/api/v1/notification-recipients/]

### Get Notification Recipients by Key [GET /research-sys/api/v1/notification-recipients/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"}

### Get All Notification Recipients [GET /research-sys/api/v1/notification-recipients/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Notification Recipients with Filtering [GET /research-sys/api/v1/notification-recipients/]
    
+ Parameters

        + id
            + recipientType
            + recipientId

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Notification Recipients [GET /research-sys/api/v1/notification-recipients/]
	                                          
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
    
            {"columns":["id","recipientType","recipientId"],"primaryKey":"id"}
		
### Get Blueprint API specification for Notification Recipients [GET /research-sys/api/v1/notification-recipients/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Notification Recipients.md"
            transfer-encoding:chunked


### Update Notification Recipients [PUT /research-sys/api/v1/notification-recipients/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Notification Recipients [PUT /research-sys/api/v1/notification-recipients/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Notification Recipients [POST /research-sys/api/v1/notification-recipients/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Notification Recipients [POST /research-sys/api/v1/notification-recipients/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Notification Recipients by Key [DELETE /research-sys/api/v1/notification-recipients/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Recipients [DELETE /research-sys/api/v1/notification-recipients/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Recipients with Matching [DELETE /research-sys/api/v1/notification-recipients/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + recipientType
            + recipientId

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
