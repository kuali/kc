## Notifications [/research-sys/api/v1/notifications/]

### Get Notifications by Key [GET /research-sys/api/v1/notifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","deliveryType": "(val)","creationDateTimeValue": "(val)","sendDateTimeValue": "(val)","autoRemoveDateTimeValue": "(val)","title": "(val)","content": "(val)","processingFlag": "(val)","lockedDateValue": "(val)","docTypeName": "(val)","_primaryKey": "(val)"}

### Get All Notifications [GET /research-sys/api/v1/notifications/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","deliveryType": "(val)","creationDateTimeValue": "(val)","sendDateTimeValue": "(val)","autoRemoveDateTimeValue": "(val)","title": "(val)","content": "(val)","processingFlag": "(val)","lockedDateValue": "(val)","docTypeName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","deliveryType": "(val)","creationDateTimeValue": "(val)","sendDateTimeValue": "(val)","autoRemoveDateTimeValue": "(val)","title": "(val)","content": "(val)","processingFlag": "(val)","lockedDateValue": "(val)","docTypeName": "(val)","_primaryKey": "(val)"}
            ]

### Get All Notifications with Filtering [GET /research-sys/api/v1/notifications/]
    
+ Parameters

    + id (optional) - 
    + deliveryType (optional) - 
    + creationDateTimeValue (optional) - 
    + sendDateTimeValue (optional) - 
    + autoRemoveDateTimeValue (optional) - 
    + title (optional) - 
    + content (optional) - 
    + processingFlag (optional) - 
    + lockedDateValue (optional) - 
    + docTypeName (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","deliveryType": "(val)","creationDateTimeValue": "(val)","sendDateTimeValue": "(val)","autoRemoveDateTimeValue": "(val)","title": "(val)","content": "(val)","processingFlag": "(val)","lockedDateValue": "(val)","docTypeName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","deliveryType": "(val)","creationDateTimeValue": "(val)","sendDateTimeValue": "(val)","autoRemoveDateTimeValue": "(val)","title": "(val)","content": "(val)","processingFlag": "(val)","lockedDateValue": "(val)","docTypeName": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Notifications [GET /research-sys/api/v1/notifications/]
	                                          
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
    
            {"columns":["id","deliveryType","creationDateTimeValue","sendDateTimeValue","autoRemoveDateTimeValue","title","content","processingFlag","lockedDateValue","docTypeName"],"primaryKey":"id"}
		
### Get Blueprint API specification for Notifications [GET /research-sys/api/v1/notifications/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Notifications.md"
            transfer-encoding:chunked
### Update Notifications [PUT /research-sys/api/v1/notifications/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","deliveryType": "(val)","creationDateTimeValue": "(val)","sendDateTimeValue": "(val)","autoRemoveDateTimeValue": "(val)","title": "(val)","content": "(val)","processingFlag": "(val)","lockedDateValue": "(val)","docTypeName": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Notifications [PUT /research-sys/api/v1/notifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","deliveryType": "(val)","creationDateTimeValue": "(val)","sendDateTimeValue": "(val)","autoRemoveDateTimeValue": "(val)","title": "(val)","content": "(val)","processingFlag": "(val)","lockedDateValue": "(val)","docTypeName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","deliveryType": "(val)","creationDateTimeValue": "(val)","sendDateTimeValue": "(val)","autoRemoveDateTimeValue": "(val)","title": "(val)","content": "(val)","processingFlag": "(val)","lockedDateValue": "(val)","docTypeName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Notifications [POST /research-sys/api/v1/notifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","deliveryType": "(val)","creationDateTimeValue": "(val)","sendDateTimeValue": "(val)","autoRemoveDateTimeValue": "(val)","title": "(val)","content": "(val)","processingFlag": "(val)","lockedDateValue": "(val)","docTypeName": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","deliveryType": "(val)","creationDateTimeValue": "(val)","sendDateTimeValue": "(val)","autoRemoveDateTimeValue": "(val)","title": "(val)","content": "(val)","processingFlag": "(val)","lockedDateValue": "(val)","docTypeName": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Notifications [POST /research-sys/api/v1/notifications/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","deliveryType": "(val)","creationDateTimeValue": "(val)","sendDateTimeValue": "(val)","autoRemoveDateTimeValue": "(val)","title": "(val)","content": "(val)","processingFlag": "(val)","lockedDateValue": "(val)","docTypeName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","deliveryType": "(val)","creationDateTimeValue": "(val)","sendDateTimeValue": "(val)","autoRemoveDateTimeValue": "(val)","title": "(val)","content": "(val)","processingFlag": "(val)","lockedDateValue": "(val)","docTypeName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","deliveryType": "(val)","creationDateTimeValue": "(val)","sendDateTimeValue": "(val)","autoRemoveDateTimeValue": "(val)","title": "(val)","content": "(val)","processingFlag": "(val)","lockedDateValue": "(val)","docTypeName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","deliveryType": "(val)","creationDateTimeValue": "(val)","sendDateTimeValue": "(val)","autoRemoveDateTimeValue": "(val)","title": "(val)","content": "(val)","processingFlag": "(val)","lockedDateValue": "(val)","docTypeName": "(val)","_primaryKey": "(val)"}
            ]
### Delete Notifications by Key [DELETE /research-sys/api/v1/notifications/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notifications [DELETE /research-sys/api/v1/notifications/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notifications with Matching [DELETE /research-sys/api/v1/notifications/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + deliveryType (optional) - 
    + creationDateTimeValue (optional) - 
    + sendDateTimeValue (optional) - 
    + autoRemoveDateTimeValue (optional) - 
    + title (optional) - 
    + content (optional) - 
    + processingFlag (optional) - 
    + lockedDateValue (optional) - 
    + docTypeName (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
