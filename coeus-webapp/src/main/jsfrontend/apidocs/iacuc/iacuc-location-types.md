## Iacuc Location Types [/research-sys/api/v1/iacuc-location-types/]

### Get Iacuc Location Types by Key [GET /research-sys/api/v1/iacuc-location-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Location Types [GET /research-sys/api/v1/iacuc-location-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"},
              {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Location Types with Filtering [GET /research-sys/api/v1/iacuc-location-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + locationTypeCode
            + location
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"},
              {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Location Types [GET /research-sys/api/v1/iacuc-location-types/]
	 
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
		
### Get Blueprint API specification for Iacuc Location Types [GET /research-sys/api/v1/iacuc-location-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Location Types.md"
            transfer-encoding:chunked


### Update Iacuc Location Types [PUT /research-sys/api/v1/iacuc-location-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Location Types [PUT /research-sys/api/v1/iacuc-location-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"},
              {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Location Types [POST /research-sys/api/v1/iacuc-location-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Location Types [POST /research-sys/api/v1/iacuc-location-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"},
              {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"},
              {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Location Types by Key [DELETE /research-sys/api/v1/iacuc-location-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Location Types [DELETE /research-sys/api/v1/iacuc-location-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Location Types with Matching [DELETE /research-sys/api/v1/iacuc-location-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + locationTypeCode
            + location


+ Response 204
