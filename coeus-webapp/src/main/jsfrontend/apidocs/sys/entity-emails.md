## Entity Emails [/research-sys/api/v1/entity-emails/]

### Get Entity Emails by Key [GET /research-sys/api/v1/entity-emails/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","emailAddress": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","active": "(val)","entityId": "(val)","emailTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Entity Emails [GET /research-sys/api/v1/entity-emails/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","emailAddress": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","active": "(val)","entityId": "(val)","emailTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","emailAddress": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","active": "(val)","entityId": "(val)","emailTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Emails with Filtering [GET /research-sys/api/v1/entity-emails/]
    
+ Parameters

    + id (optional) - 
    + emailAddress (optional) - 
    + defaultValue (optional) - 
    + entityTypeCode (optional) - 
    + active (optional) - 
    + entityId (optional) - 
    + emailTypeCode (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","emailAddress": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","active": "(val)","entityId": "(val)","emailTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","emailAddress": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","active": "(val)","entityId": "(val)","emailTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Emails [GET /research-sys/api/v1/entity-emails/]
	                                          
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
    
            {"columns":["id","emailAddress","defaultValue","entityTypeCode","active","entityId","emailTypeCode"],"primaryKey":"id"}
		
### Get Blueprint API specification for Entity Emails [GET /research-sys/api/v1/entity-emails/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Emails.md"
            transfer-encoding:chunked


### Update Entity Emails [PUT /research-sys/api/v1/entity-emails/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","emailAddress": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","active": "(val)","entityId": "(val)","emailTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Emails [PUT /research-sys/api/v1/entity-emails/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","emailAddress": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","active": "(val)","entityId": "(val)","emailTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","emailAddress": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","active": "(val)","entityId": "(val)","emailTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Entity Emails [POST /research-sys/api/v1/entity-emails/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","emailAddress": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","active": "(val)","entityId": "(val)","emailTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","emailAddress": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","active": "(val)","entityId": "(val)","emailTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Emails [POST /research-sys/api/v1/entity-emails/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","emailAddress": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","active": "(val)","entityId": "(val)","emailTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","emailAddress": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","active": "(val)","entityId": "(val)","emailTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","emailAddress": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","active": "(val)","entityId": "(val)","emailTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","emailAddress": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","active": "(val)","entityId": "(val)","emailTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Entity Emails by Key [DELETE /research-sys/api/v1/entity-emails/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Emails [DELETE /research-sys/api/v1/entity-emails/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Emails with Matching [DELETE /research-sys/api/v1/entity-emails/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + emailAddress (optional) - 
    + defaultValue (optional) - 
    + entityTypeCode (optional) - 
    + active (optional) - 
    + entityId (optional) - 
    + emailTypeCode (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
