## Notification Recipient Lists [/research-sys/api/v1/notification-recipient-lists/]

### Get Notification Recipient Lists by Key [GET /research-sys/api/v1/notification-recipient-lists/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"}

### Get All Notification Recipient Lists [GET /research-sys/api/v1/notification-recipient-lists/]
	 
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

### Get All Notification Recipient Lists with Filtering [GET /research-sys/api/v1/notification-recipient-lists/]
    
+ Parameters

    + id (optional) - 
    + recipientType (optional) - 
    + recipientId (optional) - 

            
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
			
### Get Schema for Notification Recipient Lists [GET /research-sys/api/v1/notification-recipient-lists/]
	                                          
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
		
### Get Blueprint API specification for Notification Recipient Lists [GET /research-sys/api/v1/notification-recipient-lists/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Notification Recipient Lists.md"
            transfer-encoding:chunked
### Update Notification Recipient Lists [PUT /research-sys/api/v1/notification-recipient-lists/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Notification Recipient Lists [PUT /research-sys/api/v1/notification-recipient-lists/]

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
### Insert Notification Recipient Lists [POST /research-sys/api/v1/notification-recipient-lists/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","recipientType": "(val)","recipientId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Notification Recipient Lists [POST /research-sys/api/v1/notification-recipient-lists/]

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
### Delete Notification Recipient Lists by Key [DELETE /research-sys/api/v1/notification-recipient-lists/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Recipient Lists [DELETE /research-sys/api/v1/notification-recipient-lists/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Recipient Lists with Matching [DELETE /research-sys/api/v1/notification-recipient-lists/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + recipientType (optional) - 
    + recipientId (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
