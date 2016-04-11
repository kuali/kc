## Kc Notifications [/research-sys/api/v1/kc-notifications/]

### Get Kc Notifications by Key [GET /research-sys/api/v1/kc-notifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}

### Get All Kc Notifications [GET /research-sys/api/v1/kc-notifications/]
	 
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

### Get All Kc Notifications with Filtering [GET /research-sys/api/v1/kc-notifications/]
    
+ Parameters

        + notificationId
            + notificationTypeId
            + documentNumber
            + subject
            + message

            
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
			
### Get Schema for Kc Notifications [GET /research-sys/api/v1/kc-notifications/]
	                                          
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
		
### Get Blueprint API specification for Kc Notifications [GET /research-sys/api/v1/kc-notifications/]
	 
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


### Update Kc Notifications [PUT /research-sys/api/v1/kc-notifications/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kc Notifications [PUT /research-sys/api/v1/kc-notifications/]

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

### Insert Kc Notifications [POST /research-sys/api/v1/kc-notifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kc Notifications [POST /research-sys/api/v1/kc-notifications/]

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
            
### Delete Kc Notifications by Key [DELETE /research-sys/api/v1/kc-notifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kc Notifications [DELETE /research-sys/api/v1/kc-notifications/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kc Notifications with Matching [DELETE /research-sys/api/v1/kc-notifications/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + notificationId
            + notificationTypeId
            + documentNumber
            + subject
            + message

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
