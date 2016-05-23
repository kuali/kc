## Notification Module Roles [/research-common/api/v1/notification-module-roles/]

### Get Notification Module Roles by Key [GET /research-common/api/v1/notification-module-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"notificationModuleRoleId": "(val)","moduleCode": "(val)","roleName": "(val)","_primaryKey": "(val)"}

### Get All Notification Module Roles [GET /research-common/api/v1/notification-module-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"notificationModuleRoleId": "(val)","moduleCode": "(val)","roleName": "(val)","_primaryKey": "(val)"},
              {"notificationModuleRoleId": "(val)","moduleCode": "(val)","roleName": "(val)","_primaryKey": "(val)"}
            ]

### Get All Notification Module Roles with Filtering [GET /research-common/api/v1/notification-module-roles/]
    
+ Parameters

    + notificationModuleRoleId (optional) - Notification Module Role Id. Maximum length is 6.
    + moduleCode (optional) - Module Code. Maximum length is 3.
    + roleName (optional) - Role Name. Maximum length is 125.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"notificationModuleRoleId": "(val)","moduleCode": "(val)","roleName": "(val)","_primaryKey": "(val)"},
              {"notificationModuleRoleId": "(val)","moduleCode": "(val)","roleName": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Notification Module Roles [GET /research-common/api/v1/notification-module-roles/]
	                                          
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
    
            {"columns":["notificationModuleRoleId","moduleCode","roleName"],"primaryKey":"notificationModuleRoleId"}
		
### Get Blueprint API specification for Notification Module Roles [GET /research-common/api/v1/notification-module-roles/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Notification Module Roles.md"
            transfer-encoding:chunked
### Update Notification Module Roles [PUT /research-common/api/v1/notification-module-roles/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationModuleRoleId": "(val)","moduleCode": "(val)","roleName": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Notification Module Roles [PUT /research-common/api/v1/notification-module-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"notificationModuleRoleId": "(val)","moduleCode": "(val)","roleName": "(val)","_primaryKey": "(val)"},
              {"notificationModuleRoleId": "(val)","moduleCode": "(val)","roleName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Notification Module Roles [POST /research-common/api/v1/notification-module-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationModuleRoleId": "(val)","moduleCode": "(val)","roleName": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"notificationModuleRoleId": "(val)","moduleCode": "(val)","roleName": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Notification Module Roles [POST /research-common/api/v1/notification-module-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"notificationModuleRoleId": "(val)","moduleCode": "(val)","roleName": "(val)","_primaryKey": "(val)"},
              {"notificationModuleRoleId": "(val)","moduleCode": "(val)","roleName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"notificationModuleRoleId": "(val)","moduleCode": "(val)","roleName": "(val)","_primaryKey": "(val)"},
              {"notificationModuleRoleId": "(val)","moduleCode": "(val)","roleName": "(val)","_primaryKey": "(val)"}
            ]
### Delete Notification Module Roles by Key [DELETE /research-common/api/v1/notification-module-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Module Roles [DELETE /research-common/api/v1/notification-module-roles/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Module Roles with Matching [DELETE /research-common/api/v1/notification-module-roles/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + notificationModuleRoleId (optional) - Notification Module Role Id. Maximum length is 6.
    + moduleCode (optional) - Module Code. Maximum length is 3.
    + roleName (optional) - Role Name. Maximum length is 125.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
