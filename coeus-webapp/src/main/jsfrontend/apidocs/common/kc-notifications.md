## Kc Notifications [/research-common/api/v1/kc-notifications/]

### Get Kc Notifications by Key [GET /research-common/api/v1/kc-notifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}

### Get All Kc Notifications [GET /research-common/api/v1/kc-notifications/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"},
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kc Notifications with Filtering [GET /research-common/api/v1/kc-notifications/]
    
+ Parameters

    + notificationId (optional) - Notification Id. Maximum length is 20.
    + notificationTypeId (optional) - Notification Type. Maximum length is 6.
    + documentNumber (optional) - Document Number. Maximum length is 10.
    + subject (optional) - Subject. Maximum length is 1000.
    + message (optional) - Message. Maximum length is 4000.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"},
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kc Notifications [GET /research-common/api/v1/kc-notifications/]
	                                          
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
    
            {"columns":["notificationId","notificationTypeId","documentNumber","subject","message"],"primaryKey":"notificationId"}
		
### Get Blueprint API specification for Kc Notifications [GET /research-common/api/v1/kc-notifications/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kc Notifications.md"
            transfer-encoding:chunked


### Update Kc Notifications [PUT /research-common/api/v1/kc-notifications/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kc Notifications [PUT /research-common/api/v1/kc-notifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"},
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Kc Notifications [POST /research-common/api/v1/kc-notifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kc Notifications [POST /research-common/api/v1/kc-notifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"},
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"},
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Kc Notifications by Key [DELETE /research-common/api/v1/kc-notifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kc Notifications [DELETE /research-common/api/v1/kc-notifications/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kc Notifications with Matching [DELETE /research-common/api/v1/kc-notifications/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + notificationId (optional) - Notification Id. Maximum length is 20.
    + notificationTypeId (optional) - Notification Type. Maximum length is 6.
    + documentNumber (optional) - Document Number. Maximum length is 10.
    + subject (optional) - Subject. Maximum length is 1000.
    + message (optional) - Message. Maximum length is 4000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
