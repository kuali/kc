## Notification Content Types [/research-sys/api/v1/notification-content-types/]

### Get Notification Content Types by Key [GET /research-sys/api/v1/notification-content-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","name": "(val)","current": "(val)","version": "(val)","description": "(val)","namespace": "(val)","xsd": "(val)","xsl": "(val)","_primaryKey": "(val)"}

### Get All Notification Content Types [GET /research-sys/api/v1/notification-content-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","current": "(val)","version": "(val)","description": "(val)","namespace": "(val)","xsd": "(val)","xsl": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","current": "(val)","version": "(val)","description": "(val)","namespace": "(val)","xsd": "(val)","xsl": "(val)","_primaryKey": "(val)"}
            ]

### Get All Notification Content Types with Filtering [GET /research-sys/api/v1/notification-content-types/]
    
+ Parameters

    + id (optional) - 
    + name (optional) - 
    + current (optional) - 
    + version (optional) - 
    + description (optional) - 
    + namespace (optional) - 
    + xsd (optional) - 
    + xsl (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","name": "(val)","current": "(val)","version": "(val)","description": "(val)","namespace": "(val)","xsd": "(val)","xsl": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","current": "(val)","version": "(val)","description": "(val)","namespace": "(val)","xsd": "(val)","xsl": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Notification Content Types [GET /research-sys/api/v1/notification-content-types/]
	                                          
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
    
            {"columns":["id","name","current","version","description","namespace","xsd","xsl"],"primaryKey":"id"}
		
### Get Blueprint API specification for Notification Content Types [GET /research-sys/api/v1/notification-content-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Notification Content Types.md"
            transfer-encoding:chunked
### Update Notification Content Types [PUT /research-sys/api/v1/notification-content-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","current": "(val)","version": "(val)","description": "(val)","namespace": "(val)","xsd": "(val)","xsl": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Notification Content Types [PUT /research-sys/api/v1/notification-content-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","current": "(val)","version": "(val)","description": "(val)","namespace": "(val)","xsd": "(val)","xsl": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","current": "(val)","version": "(val)","description": "(val)","namespace": "(val)","xsd": "(val)","xsl": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Notification Content Types [POST /research-sys/api/v1/notification-content-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","name": "(val)","current": "(val)","version": "(val)","description": "(val)","namespace": "(val)","xsd": "(val)","xsl": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","name": "(val)","current": "(val)","version": "(val)","description": "(val)","namespace": "(val)","xsd": "(val)","xsl": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Notification Content Types [POST /research-sys/api/v1/notification-content-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","name": "(val)","current": "(val)","version": "(val)","description": "(val)","namespace": "(val)","xsd": "(val)","xsl": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","current": "(val)","version": "(val)","description": "(val)","namespace": "(val)","xsd": "(val)","xsl": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","name": "(val)","current": "(val)","version": "(val)","description": "(val)","namespace": "(val)","xsd": "(val)","xsl": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","name": "(val)","current": "(val)","version": "(val)","description": "(val)","namespace": "(val)","xsd": "(val)","xsl": "(val)","_primaryKey": "(val)"}
            ]
### Delete Notification Content Types by Key [DELETE /research-sys/api/v1/notification-content-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Content Types [DELETE /research-sys/api/v1/notification-content-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Content Types with Matching [DELETE /research-sys/api/v1/notification-content-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + name (optional) - 
    + current (optional) - 
    + version (optional) - 
    + description (optional) - 
    + namespace (optional) - 
    + xsd (optional) - 
    + xsl (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
