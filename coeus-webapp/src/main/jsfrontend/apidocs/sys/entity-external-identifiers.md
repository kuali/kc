## Entity External Identifiers [/research-sys/api/v1/entity-external-identifiers/]

### Get Entity External Identifiers by Key [GET /research-sys/api/v1/entity-external-identifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","entityId": "(val)","externalIdentifierTypeCode": "(val)","externalId": "(val)","_primaryKey": "(val)"}

### Get All Entity External Identifiers [GET /research-sys/api/v1/entity-external-identifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","entityId": "(val)","externalIdentifierTypeCode": "(val)","externalId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","externalIdentifierTypeCode": "(val)","externalId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity External Identifiers with Filtering [GET /research-sys/api/v1/entity-external-identifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + entityId
            + externalIdentifierTypeCode
            + externalId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","entityId": "(val)","externalIdentifierTypeCode": "(val)","externalId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","externalIdentifierTypeCode": "(val)","externalId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity External Identifiers [GET /research-sys/api/v1/entity-external-identifiers/]
	 
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
		
### Get Blueprint API specification for Entity External Identifiers [GET /research-sys/api/v1/entity-external-identifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity External Identifiers.md"
            transfer-encoding:chunked


### Update Entity External Identifiers [PUT /research-sys/api/v1/entity-external-identifiers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","entityId": "(val)","externalIdentifierTypeCode": "(val)","externalId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity External Identifiers [PUT /research-sys/api/v1/entity-external-identifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","entityId": "(val)","externalIdentifierTypeCode": "(val)","externalId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","externalIdentifierTypeCode": "(val)","externalId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Entity External Identifiers [POST /research-sys/api/v1/entity-external-identifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","entityId": "(val)","externalIdentifierTypeCode": "(val)","externalId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","entityId": "(val)","externalIdentifierTypeCode": "(val)","externalId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity External Identifiers [POST /research-sys/api/v1/entity-external-identifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","entityId": "(val)","externalIdentifierTypeCode": "(val)","externalId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","externalIdentifierTypeCode": "(val)","externalId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","entityId": "(val)","externalIdentifierTypeCode": "(val)","externalId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","externalIdentifierTypeCode": "(val)","externalId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Entity External Identifiers by Key [DELETE /research-sys/api/v1/entity-external-identifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity External Identifiers [DELETE /research-sys/api/v1/entity-external-identifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Entity External Identifiers with Matching [DELETE /research-sys/api/v1/entity-external-identifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + entityId
            + externalIdentifierTypeCode
            + externalId


+ Response 204
