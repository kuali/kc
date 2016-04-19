## Notification Type Recipients [/research-common/api/v1/notification-type-recipients/]

### Get Notification Type Recipients by Key [GET /research-common/api/v1/notification-type-recipients/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"}

### Get All Notification Type Recipients [GET /research-common/api/v1/notification-type-recipients/]
	 
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

### Get All Notification Type Recipients with Filtering [GET /research-common/api/v1/notification-type-recipients/]
    
+ Parameters

    + notificationTypeRecipientId (optional) - Notification Recipient Id. Maximum length is 6.
    + notificationTypeId (optional) - Notification Id. Maximum length is 6.
    + roleName (optional) - Role. Maximum length is 125.
    + roleSubQualifier (optional) - Sub Role. Maximum length is 125.

            
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
			
### Get Schema for Notification Type Recipients [GET /research-common/api/v1/notification-type-recipients/]
	                                          
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
    
            {"columns":["notificationTypeRecipientId","notificationTypeId","roleName","roleSubQualifier"],"primaryKey":"notificationTypeRecipientId"}
		
### Get Blueprint API specification for Notification Type Recipients [GET /research-common/api/v1/notification-type-recipients/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Notification Type Recipients.md"
            transfer-encoding:chunked


### Update Notification Type Recipients [PUT /research-common/api/v1/notification-type-recipients/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Notification Type Recipients [PUT /research-common/api/v1/notification-type-recipients/]

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

### Insert Notification Type Recipients [POST /research-common/api/v1/notification-type-recipients/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"notificationTypeRecipientId": "(val)","notificationTypeId": "(val)","roleName": "(val)","roleSubQualifier": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Notification Type Recipients [POST /research-common/api/v1/notification-type-recipients/]

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
            
### Delete Notification Type Recipients by Key [DELETE /research-common/api/v1/notification-type-recipients/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Type Recipients [DELETE /research-common/api/v1/notification-type-recipients/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Type Recipients with Matching [DELETE /research-common/api/v1/notification-type-recipients/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + notificationTypeRecipientId (optional) - Notification Recipient Id. Maximum length is 6.
    + notificationTypeId (optional) - Notification Id. Maximum length is 6.
    + roleName (optional) - Role. Maximum length is 125.
    + roleSubQualifier (optional) - Sub Role. Maximum length is 125.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
