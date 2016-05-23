## Iacuc Location Types [/iacuc/api/v1/iacuc-location-types/]

### Get Iacuc Location Types by Key [GET /iacuc/api/v1/iacuc-location-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Location Types [GET /iacuc/api/v1/iacuc-location-types/]
	 
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

### Get All Iacuc Location Types with Filtering [GET /iacuc/api/v1/iacuc-location-types/]
    
+ Parameters

    + locationTypeCode (optional) - Location Type Code. Maximum length is 3.
    + location (optional) - Location Type. Maximum length is 200.

            
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
			
### Get Schema for Iacuc Location Types [GET /iacuc/api/v1/iacuc-location-types/]
	                                          
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
    
            {"columns":["locationTypeCode","location"],"primaryKey":"locationTypeCode"}
		
### Get Blueprint API specification for Iacuc Location Types [GET /iacuc/api/v1/iacuc-location-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Location Types.md"
            transfer-encoding:chunked
### Update Iacuc Location Types [PUT /iacuc/api/v1/iacuc-location-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Location Types [PUT /iacuc/api/v1/iacuc-location-types/]

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
### Insert Iacuc Location Types [POST /iacuc/api/v1/iacuc-location-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"locationTypeCode": "(val)","location": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Location Types [POST /iacuc/api/v1/iacuc-location-types/]

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
### Delete Iacuc Location Types by Key [DELETE /iacuc/api/v1/iacuc-location-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Location Types [DELETE /iacuc/api/v1/iacuc-location-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Location Types with Matching [DELETE /iacuc/api/v1/iacuc-location-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + locationTypeCode (optional) - Location Type Code. Maximum length is 3.
    + location (optional) - Location Type. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
