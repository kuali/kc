## Notification Module Role Qualifiers [/research-sys/api/v1/notification-module-role-qualifiers/]

### Get Notification Module Role Qualifiers by Key [GET /research-sys/api/v1/notification-module-role-qualifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"}

### Get All Notification Module Role Qualifiers [GET /research-sys/api/v1/notification-module-role-qualifiers/]
	 
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

### Get All Notification Module Role Qualifiers with Filtering [GET /research-sys/api/v1/notification-module-role-qualifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + notificationModuleRoleQualifierId
            + notificationModuleRoleId
            + qualifier
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"},
              {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Notification Module Role Qualifiers [GET /research-sys/api/v1/notification-module-role-qualifiers/]
	 
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
		
### Get Blueprint API specification for Notification Module Role Qualifiers [GET /research-sys/api/v1/notification-module-role-qualifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Notification Module Role Qualifiers.md"
            transfer-encoding:chunked


### Update Notification Module Role Qualifiers [PUT /research-sys/api/v1/notification-module-role-qualifiers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Notification Module Role Qualifiers [PUT /research-sys/api/v1/notification-module-role-qualifiers/]

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

### Insert Notification Module Role Qualifiers [POST /research-sys/api/v1/notification-module-role-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"notificationModuleRoleQualifierId": "(val)","notificationModuleRoleId": "(val)","qualifier": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Notification Module Role Qualifiers [POST /research-sys/api/v1/notification-module-role-qualifiers/]

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
            
### Delete Notification Module Role Qualifiers by Key [DELETE /research-sys/api/v1/notification-module-role-qualifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Module Role Qualifiers [DELETE /research-sys/api/v1/notification-module-role-qualifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Notification Module Role Qualifiers with Matching [DELETE /research-sys/api/v1/notification-module-role-qualifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + notificationModuleRoleQualifierId
            + notificationModuleRoleId
            + qualifier


+ Response 204
