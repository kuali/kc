## Entity Affiliations [/research-sys/api/v1/entity-affiliations/]

### Get Entity Affiliations by Key [GET /research-sys/api/v1/entity-affiliations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","campusCode": "(val)","defaultValue": "(val)","active": "(val)","affiliationTypeCode": "(val)","entityId": "(val)","_primaryKey": "(val)"}

### Get All Entity Affiliations [GET /research-sys/api/v1/entity-affiliations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","campusCode": "(val)","defaultValue": "(val)","active": "(val)","affiliationTypeCode": "(val)","entityId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","campusCode": "(val)","defaultValue": "(val)","active": "(val)","affiliationTypeCode": "(val)","entityId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Affiliations with Filtering [GET /research-sys/api/v1/entity-affiliations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + campusCode
            + defaultValue
            + active
            + affiliationTypeCode
            + entityId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","campusCode": "(val)","defaultValue": "(val)","active": "(val)","affiliationTypeCode": "(val)","entityId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","campusCode": "(val)","defaultValue": "(val)","active": "(val)","affiliationTypeCode": "(val)","entityId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Affiliations [GET /research-sys/api/v1/entity-affiliations/]
	 
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
		
### Get Blueprint API specification for Entity Affiliations [GET /research-sys/api/v1/entity-affiliations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Affiliations.md"
            transfer-encoding:chunked


### Update Entity Affiliations [PUT /research-sys/api/v1/entity-affiliations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","campusCode": "(val)","defaultValue": "(val)","active": "(val)","affiliationTypeCode": "(val)","entityId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Affiliations [PUT /research-sys/api/v1/entity-affiliations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","campusCode": "(val)","defaultValue": "(val)","active": "(val)","affiliationTypeCode": "(val)","entityId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","campusCode": "(val)","defaultValue": "(val)","active": "(val)","affiliationTypeCode": "(val)","entityId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Entity Affiliations [POST /research-sys/api/v1/entity-affiliations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","campusCode": "(val)","defaultValue": "(val)","active": "(val)","affiliationTypeCode": "(val)","entityId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","campusCode": "(val)","defaultValue": "(val)","active": "(val)","affiliationTypeCode": "(val)","entityId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Affiliations [POST /research-sys/api/v1/entity-affiliations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","campusCode": "(val)","defaultValue": "(val)","active": "(val)","affiliationTypeCode": "(val)","entityId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","campusCode": "(val)","defaultValue": "(val)","active": "(val)","affiliationTypeCode": "(val)","entityId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","campusCode": "(val)","defaultValue": "(val)","active": "(val)","affiliationTypeCode": "(val)","entityId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","campusCode": "(val)","defaultValue": "(val)","active": "(val)","affiliationTypeCode": "(val)","entityId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Entity Affiliations by Key [DELETE /research-sys/api/v1/entity-affiliations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Affiliations [DELETE /research-sys/api/v1/entity-affiliations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Entity Affiliations with Matching [DELETE /research-sys/api/v1/entity-affiliations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + campusCode
            + defaultValue
            + active
            + affiliationTypeCode
            + entityId


+ Response 204
