## Notification Type Recipients [/research-sys/api/v1/notification-type-recipients/]

### Get Notification Type Recipients by Key [GET /research-sys/api/v1/notification-type-recipients/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"}

### Get All Notification Type Recipients [GET /research-sys/api/v1/notification-type-recipients/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"},
              {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"}
            ]

### Get All Notification Type Recipients with Filtering [GET /research-sys/api/v1/notification-type-recipients/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + notificationTypeRecipientId
            + notificationTypeId
            + roleName
            + roleSubQualifier
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"},
              {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Notification Type Recipients [GET /research-sys/api/v1/notification-type-recipients/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Notification Type Recipients [GET /research-sys/api/v1/notification-type-recipients/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Notification Type Recipients.md"
            transfer-encoding:chunked


### Update Notification Type Recipients [PUT /research-sys/api/v1/notification-type-recipients/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Notification Type Recipients [PUT /research-sys/api/v1/notification-type-recipients/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"},
              {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Notification Type Recipients [POST /research-sys/api/v1/notification-type-recipients/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Notification Type Recipients [POST /research-sys/api/v1/notification-type-recipients/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"},
              {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"},
              {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Notification Type Recipients by Key [DELETE /research-sys/api/v1/notification-type-recipients/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Type Recipients [DELETE /research-sys/api/v1/notification-type-recipients/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Notification Type Recipients with Matching [DELETE /research-sys/api/v1/notification-type-recipients/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + notificationTypeRecipientId
            + notificationTypeId
            + roleName
            + roleSubQualifier


+ Response 204
