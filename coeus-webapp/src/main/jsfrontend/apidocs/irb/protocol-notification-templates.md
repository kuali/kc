## Protocol Notification Templates [/irb/api/v1/protocol-notification-templates/]

### Get Protocol Notification Templates by Key [GET /irb/api/v1/protocol-notification-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"}

### Get All Protocol Notification Templates [GET /irb/api/v1/protocol-notification-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"},
              {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Notification Templates with Filtering [GET /irb/api/v1/protocol-notification-templates/]
    
+ Parameters

    + notificationTemplateId (optional) - Proto Notification Templ Id. Maximum length is 12.
    + actionTypeCode (optional) - Protocol Action Type Code. Maximum length is 3.
    + fileName (optional) - File. Maximum length is 150.
    + notificationTemplate (optional) - Notification Template. Maximum length is 4000.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"},
              {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Notification Templates [GET /irb/api/v1/protocol-notification-templates/]
	                                          
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
    
            {"columns":["notificationTemplateId","actionTypeCode","fileName","notificationTemplate"],"primaryKey":"notificationTemplateId"}
		
### Get Blueprint API specification for Protocol Notification Templates [GET /irb/api/v1/protocol-notification-templates/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Notification Templates.md"
            transfer-encoding:chunked
### Update Protocol Notification Templates [PUT /irb/api/v1/protocol-notification-templates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Notification Templates [PUT /irb/api/v1/protocol-notification-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"},
              {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Protocol Notification Templates [POST /irb/api/v1/protocol-notification-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Notification Templates [POST /irb/api/v1/protocol-notification-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"},
              {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"},
              {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"}
            ]
### Delete Protocol Notification Templates by Key [DELETE /irb/api/v1/protocol-notification-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Notification Templates [DELETE /irb/api/v1/protocol-notification-templates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Notification Templates with Matching [DELETE /irb/api/v1/protocol-notification-templates/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + notificationTemplateId (optional) - Proto Notification Templ Id. Maximum length is 12.
    + actionTypeCode (optional) - Protocol Action Type Code. Maximum length is 3.
    + fileName (optional) - File. Maximum length is 150.
    + notificationTemplate (optional) - Notification Template. Maximum length is 4000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
