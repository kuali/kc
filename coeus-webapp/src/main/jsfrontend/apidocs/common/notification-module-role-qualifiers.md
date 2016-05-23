## Notification Module Role Qualifiers [/research-common/api/v1/notification-module-role-qualifiers/]

### Get Notification Module Role Qualifiers by Key [GET /research-common/api/v1/notification-module-role-qualifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"}

### Get All Notification Module Role Qualifiers [GET /research-common/api/v1/notification-module-role-qualifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"},
              {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            ]

### Get All Notification Module Role Qualifiers with Filtering [GET /research-common/api/v1/notification-module-role-qualifiers/]
    
+ Parameters

    + notificationModuleRoleQualifierId (optional) - Notification Module Role Qualifier Id. Maximum length is 6.
    + notificationModuleRoleId (optional) - Notification Module Role Id. Maximum length is 6.
    + qualifier (optional) - Role Qualifier. Maximum length is 200.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"},
              {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Notification Module Role Qualifiers [GET /research-common/api/v1/notification-module-role-qualifiers/]
	                                          
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
    
            {"columns":["notificationModuleRoleQualifierId","notificationModuleRoleId","qualifier"],"primaryKey":"notificationModuleRoleQualifierId"}
		
### Get Blueprint API specification for Notification Module Role Qualifiers [GET /research-common/api/v1/notification-module-role-qualifiers/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Notification Module Role Qualifiers.md"
            transfer-encoding:chunked
### Update Notification Module Role Qualifiers [PUT /research-common/api/v1/notification-module-role-qualifiers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Notification Module Role Qualifiers [PUT /research-common/api/v1/notification-module-role-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"},
              {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Notification Module Role Qualifiers [POST /research-common/api/v1/notification-module-role-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Notification Module Role Qualifiers [POST /research-common/api/v1/notification-module-role-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"},
              {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"},
              {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            ]
### Delete Notification Module Role Qualifiers by Key [DELETE /research-common/api/v1/notification-module-role-qualifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Module Role Qualifiers [DELETE /research-common/api/v1/notification-module-role-qualifiers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Module Role Qualifiers with Matching [DELETE /research-common/api/v1/notification-module-role-qualifiers/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + notificationModuleRoleQualifierId (optional) - Notification Module Role Qualifier Id. Maximum length is 6.
    + notificationModuleRoleId (optional) - Notification Module Role Id. Maximum length is 6.
    + qualifier (optional) - Role Qualifier. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
