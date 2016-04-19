## Entity Type Contact Infos [/research-sys/api/v1/entity-type-contact-infos/]

### Get Entity Type Contact Infos by Key [GET /research-sys/api/v1/entity-type-contact-infos/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"entityId": "(val)","entityTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Entity Type Contact Infos [GET /research-sys/api/v1/entity-type-contact-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"entityId": "(val)","entityTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityId": "(val)","entityTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Type Contact Infos with Filtering [GET /research-sys/api/v1/entity-type-contact-infos/]
    
+ Parameters

    + entityId (optional) - 
    + entityTypeCode (optional) - 
    + active (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"entityId": "(val)","entityTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityId": "(val)","entityTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Type Contact Infos [GET /research-sys/api/v1/entity-type-contact-infos/]
	                                          
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
    
            {"columns":["entityId","entityTypeCode","active"],"primaryKey":"entityId:entityTypeCode"}
		
### Get Blueprint API specification for Entity Type Contact Infos [GET /research-sys/api/v1/entity-type-contact-infos/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Type Contact Infos.md"
            transfer-encoding:chunked


### Update Entity Type Contact Infos [PUT /research-sys/api/v1/entity-type-contact-infos/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityId": "(val)","entityTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Type Contact Infos [PUT /research-sys/api/v1/entity-type-contact-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityId": "(val)","entityTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityId": "(val)","entityTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Entity Type Contact Infos [POST /research-sys/api/v1/entity-type-contact-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityId": "(val)","entityTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"entityId": "(val)","entityTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Type Contact Infos [POST /research-sys/api/v1/entity-type-contact-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityId": "(val)","entityTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityId": "(val)","entityTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"entityId": "(val)","entityTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityId": "(val)","entityTypeCode": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Entity Type Contact Infos by Key [DELETE /research-sys/api/v1/entity-type-contact-infos/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Type Contact Infos [DELETE /research-sys/api/v1/entity-type-contact-infos/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Type Contact Infos with Matching [DELETE /research-sys/api/v1/entity-type-contact-infos/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + entityId (optional) - 
    + entityTypeCode (optional) - 
    + active (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
