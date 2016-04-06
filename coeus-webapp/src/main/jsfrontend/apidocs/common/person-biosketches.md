## Person Biosketches [/research-sys/api/v1/person-biosketches/]

### Get Person Biosketches by Key [GET /research-sys/api/v1/person-biosketches/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}

### Get All Person Biosketches [GET /research-sys/api/v1/person-biosketches/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"},
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Biosketches with Filtering [GET /research-sys/api/v1/person-biosketches/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + personBiosketchId
            + personId
            + description
            + fileName
            + contentType
            + attachmentContent
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"},
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Biosketches [GET /research-sys/api/v1/person-biosketches/]
	 
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
		
### Get Blueprint API specification for Person Biosketches [GET /research-sys/api/v1/person-biosketches/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Biosketches.md"
            transfer-encoding:chunked


### Update Person Biosketches [PUT /research-sys/api/v1/person-biosketches/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Biosketches [PUT /research-sys/api/v1/person-biosketches/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"},
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Person Biosketches [POST /research-sys/api/v1/person-biosketches/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Biosketches [POST /research-sys/api/v1/person-biosketches/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"},
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"},
              {"personBiosketchId": "(val)","personId": "(val)","description": "(val)","fileName": "(val)","contentType": "(val)","attachmentContent": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Person Biosketches by Key [DELETE /research-sys/api/v1/person-biosketches/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Biosketches [DELETE /research-sys/api/v1/person-biosketches/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Person Biosketches with Matching [DELETE /research-sys/api/v1/person-biosketches/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + personBiosketchId
            + personId
            + description
            + fileName
            + contentType
            + attachmentContent


+ Response 204
