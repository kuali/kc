## Notification Types [/research-common/api/v1/notification-types/]

### Get Notification Types by Key [GET /research-common/api/v1/notification-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"notificationTypeId": "(val)","moduleCode": "(val)","actionCode": "(val)","description": "(val)","subject": "(val)","message": "(val)","promptUser": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Notification Types [GET /research-common/api/v1/notification-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"notificationTypeId": "(val)","moduleCode": "(val)","actionCode": "(val)","description": "(val)","subject": "(val)","message": "(val)","promptUser": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"notificationTypeId": "(val)","moduleCode": "(val)","actionCode": "(val)","description": "(val)","subject": "(val)","message": "(val)","promptUser": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Notification Types with Filtering [GET /research-common/api/v1/notification-types/]
    
+ Parameters

    + notificationTypeId (optional) - Notification Id. Maximum length is 6.
    + moduleCode (optional) - Module Code. Maximum length is 3.
    + actionCode (optional) - Action Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + subject (optional) - Subject. Maximum length is 1000.
    + message (optional) - Message. Maximum length is 4000.
    + promptUser (optional) - Prompt User. Maximum length is 1.
    + active (optional) - Active. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"notificationTypeId": "(val)","moduleCode": "(val)","actionCode": "(val)","description": "(val)","subject": "(val)","message": "(val)","promptUser": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"notificationTypeId": "(val)","moduleCode": "(val)","actionCode": "(val)","description": "(val)","subject": "(val)","message": "(val)","promptUser": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Notification Types [GET /research-common/api/v1/notification-types/]
	                                          
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
    
            {"columns":["notificationTypeId","moduleCode","actionCode","description","subject","message","promptUser","active"],"primaryKey":"notificationTypeId"}
		
### Get Blueprint API specification for Notification Types [GET /research-common/api/v1/notification-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Notification Types.md"
            transfer-encoding:chunked
### Update Notification Types [PUT /research-common/api/v1/notification-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationTypeId": "(val)","moduleCode": "(val)","actionCode": "(val)","description": "(val)","subject": "(val)","message": "(val)","promptUser": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Notification Types [PUT /research-common/api/v1/notification-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"notificationTypeId": "(val)","moduleCode": "(val)","actionCode": "(val)","description": "(val)","subject": "(val)","message": "(val)","promptUser": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"notificationTypeId": "(val)","moduleCode": "(val)","actionCode": "(val)","description": "(val)","subject": "(val)","message": "(val)","promptUser": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Notification Types [POST /research-common/api/v1/notification-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationTypeId": "(val)","moduleCode": "(val)","actionCode": "(val)","description": "(val)","subject": "(val)","message": "(val)","promptUser": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"notificationTypeId": "(val)","moduleCode": "(val)","actionCode": "(val)","description": "(val)","subject": "(val)","message": "(val)","promptUser": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Notification Types [POST /research-common/api/v1/notification-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"notificationTypeId": "(val)","moduleCode": "(val)","actionCode": "(val)","description": "(val)","subject": "(val)","message": "(val)","promptUser": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"notificationTypeId": "(val)","moduleCode": "(val)","actionCode": "(val)","description": "(val)","subject": "(val)","message": "(val)","promptUser": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"notificationTypeId": "(val)","moduleCode": "(val)","actionCode": "(val)","description": "(val)","subject": "(val)","message": "(val)","promptUser": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"notificationTypeId": "(val)","moduleCode": "(val)","actionCode": "(val)","description": "(val)","subject": "(val)","message": "(val)","promptUser": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Notification Types by Key [DELETE /research-common/api/v1/notification-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Types [DELETE /research-common/api/v1/notification-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Types with Matching [DELETE /research-common/api/v1/notification-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + notificationTypeId (optional) - Notification Id. Maximum length is 6.
    + moduleCode (optional) - Module Code. Maximum length is 3.
    + actionCode (optional) - Action Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + subject (optional) - Subject. Maximum length is 1000.
    + message (optional) - Message. Maximum length is 4000.
    + promptUser (optional) - Prompt User. Maximum length is 1.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
