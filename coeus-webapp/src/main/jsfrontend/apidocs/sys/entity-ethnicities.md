## Entity Ethnicities [/research-sys/api/v1/entity-ethnicities/]

### Get Entity Ethnicities by Key [GET /research-sys/api/v1/entity-ethnicities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","entityId": "(val)","ethnicityCode": "(val)","subEthnicityCode": "(val)","_primaryKey": "(val)"}

### Get All Entity Ethnicities [GET /research-sys/api/v1/entity-ethnicities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","entityId": "(val)","ethnicityCode": "(val)","subEthnicityCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","ethnicityCode": "(val)","subEthnicityCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Ethnicities with Filtering [GET /research-sys/api/v1/entity-ethnicities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + entityId
            + ethnicityCode
            + subEthnicityCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","entityId": "(val)","ethnicityCode": "(val)","subEthnicityCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","ethnicityCode": "(val)","subEthnicityCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Ethnicities [GET /research-sys/api/v1/entity-ethnicities/]
	 
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
		
### Get Blueprint API specification for Entity Ethnicities [GET /research-sys/api/v1/entity-ethnicities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Ethnicities.md"
            transfer-encoding:chunked


### Update Entity Ethnicities [PUT /research-sys/api/v1/entity-ethnicities/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","entityId": "(val)","ethnicityCode": "(val)","subEthnicityCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Ethnicities [PUT /research-sys/api/v1/entity-ethnicities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","entityId": "(val)","ethnicityCode": "(val)","subEthnicityCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","ethnicityCode": "(val)","subEthnicityCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Entity Ethnicities [POST /research-sys/api/v1/entity-ethnicities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","entityId": "(val)","ethnicityCode": "(val)","subEthnicityCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","entityId": "(val)","ethnicityCode": "(val)","subEthnicityCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Ethnicities [POST /research-sys/api/v1/entity-ethnicities/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","entityId": "(val)","ethnicityCode": "(val)","subEthnicityCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","ethnicityCode": "(val)","subEthnicityCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","entityId": "(val)","ethnicityCode": "(val)","subEthnicityCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","entityId": "(val)","ethnicityCode": "(val)","subEthnicityCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Entity Ethnicities by Key [DELETE /research-sys/api/v1/entity-ethnicities/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Ethnicities [DELETE /research-sys/api/v1/entity-ethnicities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Entity Ethnicities with Matching [DELETE /research-sys/api/v1/entity-ethnicities/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + entityId
            + ethnicityCode
            + subEthnicityCode


+ Response 204
