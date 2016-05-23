## Irb Protocol Notifications [/irb/api/v1/irb-protocol-notifications/]

### Get Irb Protocol Notifications by Key [GET /irb/api/v1/irb-protocol-notifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","createUser": "(val)","createTimestamp": "(val)","_primaryKey": "(val)"}

### Get All Irb Protocol Notifications [GET /irb/api/v1/irb-protocol-notifications/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","createUser": "(val)","createTimestamp": "(val)","_primaryKey": "(val)"},
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","createUser": "(val)","createTimestamp": "(val)","_primaryKey": "(val)"}
            ]

### Get All Irb Protocol Notifications with Filtering [GET /irb/api/v1/irb-protocol-notifications/]
    
+ Parameters

    + notificationId (optional) - 
    + notificationTypeId (optional) - 
    + documentNumber (optional) - 
    + owningDocumentIdFk (optional) - 
    + recipients (optional) - 
    + subject (optional) - 
    + message (optional) - 
    + createUser (optional) - 
    + createTimestamp (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","createUser": "(val)","createTimestamp": "(val)","_primaryKey": "(val)"},
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","createUser": "(val)","createTimestamp": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Irb Protocol Notifications [GET /irb/api/v1/irb-protocol-notifications/]
	                                          
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
    
            {"columns":["notificationId","notificationTypeId","documentNumber","owningDocumentIdFk","recipients","subject","message","createUser","createTimestamp"],"primaryKey":"notificationId"}
		
### Get Blueprint API specification for Irb Protocol Notifications [GET /irb/api/v1/irb-protocol-notifications/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Irb Protocol Notifications.md"
            transfer-encoding:chunked
### Update Irb Protocol Notifications [PUT /irb/api/v1/irb-protocol-notifications/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","createUser": "(val)","createTimestamp": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Irb Protocol Notifications [PUT /irb/api/v1/irb-protocol-notifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","createUser": "(val)","createTimestamp": "(val)","_primaryKey": "(val)"},
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","createUser": "(val)","createTimestamp": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Irb Protocol Notifications [POST /irb/api/v1/irb-protocol-notifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","createUser": "(val)","createTimestamp": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","createUser": "(val)","createTimestamp": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Irb Protocol Notifications [POST /irb/api/v1/irb-protocol-notifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","createUser": "(val)","createTimestamp": "(val)","_primaryKey": "(val)"},
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","createUser": "(val)","createTimestamp": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","createUser": "(val)","createTimestamp": "(val)","_primaryKey": "(val)"},
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","createUser": "(val)","createTimestamp": "(val)","_primaryKey": "(val)"}
            ]
### Delete Irb Protocol Notifications by Key [DELETE /irb/api/v1/irb-protocol-notifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Irb Protocol Notifications [DELETE /irb/api/v1/irb-protocol-notifications/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Irb Protocol Notifications with Matching [DELETE /irb/api/v1/irb-protocol-notifications/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + notificationId (optional) - 
    + notificationTypeId (optional) - 
    + documentNumber (optional) - 
    + owningDocumentIdFk (optional) - 
    + recipients (optional) - 
    + subject (optional) - 
    + message (optional) - 
    + createUser (optional) - 
    + createTimestamp (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
