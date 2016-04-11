## Entity Phones [/research-sys/api/v1/entity-phones/]

### Get Entity Phones by Key [GET /research-sys/api/v1/entity-phones/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","phoneNumber": "(val)","countryCode": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","extensionNumber": "(val)","active": "(val)","entityId": "(val)","phoneTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Entity Phones [GET /research-sys/api/v1/entity-phones/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","phoneNumber": "(val)","countryCode": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","extensionNumber": "(val)","active": "(val)","entityId": "(val)","phoneTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","phoneNumber": "(val)","countryCode": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","extensionNumber": "(val)","active": "(val)","entityId": "(val)","phoneTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Phones with Filtering [GET /research-sys/api/v1/entity-phones/]
    
+ Parameters

        + id
            + phoneNumber
            + countryCode
            + defaultValue
            + entityTypeCode
            + extensionNumber
            + active
            + entityId
            + phoneTypeCode

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","phoneNumber": "(val)","countryCode": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","extensionNumber": "(val)","active": "(val)","entityId": "(val)","phoneTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","phoneNumber": "(val)","countryCode": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","extensionNumber": "(val)","active": "(val)","entityId": "(val)","phoneTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Phones [GET /research-sys/api/v1/entity-phones/]
	                                          
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
    
            {"columns":["id","phoneNumber","countryCode","defaultValue","entityTypeCode","extensionNumber","active","entityId","phoneTypeCode"],"primaryKey":"id"}
		
### Get Blueprint API specification for Entity Phones [GET /research-sys/api/v1/entity-phones/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Phones.md"
            transfer-encoding:chunked


### Update Entity Phones [PUT /research-sys/api/v1/entity-phones/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","phoneNumber": "(val)","countryCode": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","extensionNumber": "(val)","active": "(val)","entityId": "(val)","phoneTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Phones [PUT /research-sys/api/v1/entity-phones/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","phoneNumber": "(val)","countryCode": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","extensionNumber": "(val)","active": "(val)","entityId": "(val)","phoneTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","phoneNumber": "(val)","countryCode": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","extensionNumber": "(val)","active": "(val)","entityId": "(val)","phoneTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Entity Phones [POST /research-sys/api/v1/entity-phones/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","phoneNumber": "(val)","countryCode": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","extensionNumber": "(val)","active": "(val)","entityId": "(val)","phoneTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","phoneNumber": "(val)","countryCode": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","extensionNumber": "(val)","active": "(val)","entityId": "(val)","phoneTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Phones [POST /research-sys/api/v1/entity-phones/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","phoneNumber": "(val)","countryCode": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","extensionNumber": "(val)","active": "(val)","entityId": "(val)","phoneTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","phoneNumber": "(val)","countryCode": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","extensionNumber": "(val)","active": "(val)","entityId": "(val)","phoneTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","phoneNumber": "(val)","countryCode": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","extensionNumber": "(val)","active": "(val)","entityId": "(val)","phoneTypeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","phoneNumber": "(val)","countryCode": "(val)","defaultValue": "(val)","entityTypeCode": "(val)","extensionNumber": "(val)","active": "(val)","entityId": "(val)","phoneTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Entity Phones by Key [DELETE /research-sys/api/v1/entity-phones/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Phones [DELETE /research-sys/api/v1/entity-phones/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Phones with Matching [DELETE /research-sys/api/v1/entity-phones/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + phoneNumber
            + countryCode
            + defaultValue
            + entityTypeCode
            + extensionNumber
            + active
            + entityId
            + phoneTypeCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
