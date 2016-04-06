## Iacuc Location Names [/research-sys/api/v1/iacuc-location-names/]

### Get Iacuc Location Names by Key [GET /research-sys/api/v1/iacuc-location-names/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Location Names [GET /research-sys/api/v1/iacuc-location-names/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"},
              {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Location Names with Filtering [GET /research-sys/api/v1/iacuc-location-names/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + locationId
            + locationName
            + locationTypeCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"},
              {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Location Names [GET /research-sys/api/v1/iacuc-location-names/]
	 
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
		
### Get Blueprint API specification for Iacuc Location Names [GET /research-sys/api/v1/iacuc-location-names/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Location Names.md"
            transfer-encoding:chunked


### Update Iacuc Location Names [PUT /research-sys/api/v1/iacuc-location-names/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Location Names [PUT /research-sys/api/v1/iacuc-location-names/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"},
              {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Location Names [POST /research-sys/api/v1/iacuc-location-names/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Location Names [POST /research-sys/api/v1/iacuc-location-names/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"},
              {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"},
              {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Location Names by Key [DELETE /research-sys/api/v1/iacuc-location-names/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Location Names [DELETE /research-sys/api/v1/iacuc-location-names/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Location Names with Matching [DELETE /research-sys/api/v1/iacuc-location-names/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + locationId
            + locationName
            + locationTypeCode


+ Response 204
