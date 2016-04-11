## Protocol Notification Templates [/research-sys/api/v1/protocol-notification-templates/]

### Get Protocol Notification Templates by Key [GET /research-sys/api/v1/protocol-notification-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"}

### Get All Protocol Notification Templates [GET /research-sys/api/v1/protocol-notification-templates/]
	 
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

### Get All Protocol Notification Templates with Filtering [GET /research-sys/api/v1/protocol-notification-templates/]
    
+ Parameters

        + notificationTemplateId
            + actionTypeCode
            + fileName
            + notificationTemplate

            
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
			
### Get Schema for Protocol Notification Templates [GET /research-sys/api/v1/protocol-notification-templates/]
	                                          
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
		
### Get Blueprint API specification for Protocol Notification Templates [GET /research-sys/api/v1/protocol-notification-templates/]
	 
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


### Update Protocol Notification Templates [PUT /research-sys/api/v1/protocol-notification-templates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Notification Templates [PUT /research-sys/api/v1/protocol-notification-templates/]

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

### Insert Protocol Notification Templates [POST /research-sys/api/v1/protocol-notification-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"notificationTemplateId": "(val)","actionTypeCode": "(val)","fileName": "(val)","notificationTemplate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Notification Templates [POST /research-sys/api/v1/protocol-notification-templates/]

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
            
### Delete Protocol Notification Templates by Key [DELETE /research-sys/api/v1/protocol-notification-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Notification Templates [DELETE /research-sys/api/v1/protocol-notification-templates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Notification Templates with Matching [DELETE /research-sys/api/v1/protocol-notification-templates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + notificationTemplateId
            + actionTypeCode
            + fileName
            + notificationTemplate

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
