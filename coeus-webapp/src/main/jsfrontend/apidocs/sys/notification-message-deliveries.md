## Notification Message Deliveries [/research-sys/api/v1/notification-message-deliveries/]

### Get Notification Message Deliveries by Key [GET /research-sys/api/v1/notification-message-deliveries/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","messageDeliveryStatus": "(val)","userRecipientId": "(val)","deliverySystemId": "(val)","lockedDateValue": "(val)","_primaryKey": "(val)"}

### Get All Notification Message Deliveries [GET /research-sys/api/v1/notification-message-deliveries/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","messageDeliveryStatus": "(val)","userRecipientId": "(val)","deliverySystemId": "(val)","lockedDateValue": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","messageDeliveryStatus": "(val)","userRecipientId": "(val)","deliverySystemId": "(val)","lockedDateValue": "(val)","_primaryKey": "(val)"}
            ]

### Get All Notification Message Deliveries with Filtering [GET /research-sys/api/v1/notification-message-deliveries/]
    
+ Parameters

    + id (optional) - 
    + messageDeliveryStatus (optional) - 
    + userRecipientId (optional) - 
    + deliverySystemId (optional) - 
    + lockedDateValue (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","messageDeliveryStatus": "(val)","userRecipientId": "(val)","deliverySystemId": "(val)","lockedDateValue": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","messageDeliveryStatus": "(val)","userRecipientId": "(val)","deliverySystemId": "(val)","lockedDateValue": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Notification Message Deliveries [GET /research-sys/api/v1/notification-message-deliveries/]
	                                          
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
    
            {"columns":["id","messageDeliveryStatus","userRecipientId","deliverySystemId","lockedDateValue"],"primaryKey":"id"}
		
### Get Blueprint API specification for Notification Message Deliveries [GET /research-sys/api/v1/notification-message-deliveries/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Notification Message Deliveries.md"
            transfer-encoding:chunked


### Update Notification Message Deliveries [PUT /research-sys/api/v1/notification-message-deliveries/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","messageDeliveryStatus": "(val)","userRecipientId": "(val)","deliverySystemId": "(val)","lockedDateValue": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Notification Message Deliveries [PUT /research-sys/api/v1/notification-message-deliveries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","messageDeliveryStatus": "(val)","userRecipientId": "(val)","deliverySystemId": "(val)","lockedDateValue": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","messageDeliveryStatus": "(val)","userRecipientId": "(val)","deliverySystemId": "(val)","lockedDateValue": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Notification Message Deliveries [POST /research-sys/api/v1/notification-message-deliveries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","messageDeliveryStatus": "(val)","userRecipientId": "(val)","deliverySystemId": "(val)","lockedDateValue": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","messageDeliveryStatus": "(val)","userRecipientId": "(val)","deliverySystemId": "(val)","lockedDateValue": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Notification Message Deliveries [POST /research-sys/api/v1/notification-message-deliveries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","messageDeliveryStatus": "(val)","userRecipientId": "(val)","deliverySystemId": "(val)","lockedDateValue": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","messageDeliveryStatus": "(val)","userRecipientId": "(val)","deliverySystemId": "(val)","lockedDateValue": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","messageDeliveryStatus": "(val)","userRecipientId": "(val)","deliverySystemId": "(val)","lockedDateValue": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","messageDeliveryStatus": "(val)","userRecipientId": "(val)","deliverySystemId": "(val)","lockedDateValue": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Notification Message Deliveries by Key [DELETE /research-sys/api/v1/notification-message-deliveries/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Message Deliveries [DELETE /research-sys/api/v1/notification-message-deliveries/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Message Deliveries with Matching [DELETE /research-sys/api/v1/notification-message-deliveries/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + messageDeliveryStatus (optional) - 
    + userRecipientId (optional) - 
    + deliverySystemId (optional) - 
    + lockedDateValue (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
