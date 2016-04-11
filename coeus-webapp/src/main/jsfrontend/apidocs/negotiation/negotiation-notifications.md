## Negotiation Notifications [/research-sys/api/v1/negotiation-notifications/]

### Get Negotiation Notifications by Key [GET /research-sys/api/v1/negotiation-notifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}

### Get All Negotiation Notifications [GET /research-sys/api/v1/negotiation-notifications/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"},
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
            ]

### Get All Negotiation Notifications with Filtering [GET /research-sys/api/v1/negotiation-notifications/]
    
+ Parameters

        + notificationId
            + notificationTypeId
            + documentNumber
            + owningDocumentIdFk
            + recipients
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
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"},
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Negotiation Notifications [GET /research-sys/api/v1/negotiation-notifications/]
	                                          
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
    
            {"columns":["notificationId","notificationTypeId","documentNumber","owningDocumentIdFk","recipients","subject","message"],"primaryKey":"notificationId"}
		
### Get Blueprint API specification for Negotiation Notifications [GET /research-sys/api/v1/negotiation-notifications/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Negotiation Notifications.md"
            transfer-encoding:chunked


### Update Negotiation Notifications [PUT /research-sys/api/v1/negotiation-notifications/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Negotiation Notifications [PUT /research-sys/api/v1/negotiation-notifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"},
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Negotiation Notifications [POST /research-sys/api/v1/negotiation-notifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Negotiation Notifications [POST /research-sys/api/v1/negotiation-notifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"},
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"},
              {"notificationId": "(val)","notificationTypeId": "(val)","documentNumber": "(val)","owningDocumentIdFk": "(val)","recipients": "(val)","subject": "(val)","message": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Negotiation Notifications by Key [DELETE /research-sys/api/v1/negotiation-notifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Notifications [DELETE /research-sys/api/v1/negotiation-notifications/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Negotiation Notifications with Matching [DELETE /research-sys/api/v1/negotiation-notifications/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + notificationId
            + notificationTypeId
            + documentNumber
            + owningDocumentIdFk
            + recipients
            + subject
            + message

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
