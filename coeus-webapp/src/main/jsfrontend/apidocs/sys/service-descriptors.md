## Service Descriptors [/research-sys/api/v1/service-descriptors/]

### Get Service Descriptors by Key [GET /research-sys/api/v1/service-descriptors/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","descriptor": "(val)","_primaryKey": "(val)"}

### Get All Service Descriptors [GET /research-sys/api/v1/service-descriptors/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","descriptor": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","descriptor": "(val)","_primaryKey": "(val)"}
            ]

### Get All Service Descriptors with Filtering [GET /research-sys/api/v1/service-descriptors/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + descriptor
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","descriptor": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","descriptor": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Service Descriptors [GET /research-sys/api/v1/service-descriptors/]
	 
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
		
### Get Blueprint API specification for Service Descriptors [GET /research-sys/api/v1/service-descriptors/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Service Descriptors.md"
            transfer-encoding:chunked


### Update Service Descriptors [PUT /research-sys/api/v1/service-descriptors/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","descriptor": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Service Descriptors [PUT /research-sys/api/v1/service-descriptors/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","descriptor": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","descriptor": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Service Descriptors [POST /research-sys/api/v1/service-descriptors/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","descriptor": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","descriptor": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Service Descriptors [POST /research-sys/api/v1/service-descriptors/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","descriptor": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","descriptor": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","descriptor": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","descriptor": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Service Descriptors by Key [DELETE /research-sys/api/v1/service-descriptors/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Service Descriptors [DELETE /research-sys/api/v1/service-descriptors/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Service Descriptors with Matching [DELETE /research-sys/api/v1/service-descriptors/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + descriptor


+ Response 204
