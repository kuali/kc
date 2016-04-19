## Iacuc Location Names [/iacuc/api/v1/iacuc-location-names/]

### Get Iacuc Location Names by Key [GET /iacuc/api/v1/iacuc-location-names/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Location Names [GET /iacuc/api/v1/iacuc-location-names/]
	 
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

### Get All Iacuc Location Names with Filtering [GET /iacuc/api/v1/iacuc-location-names/]
    
+ Parameters

    + locationId (optional) - Location Name Code. Maximum length is 3.
    + locationName (optional) - Location Name. Maximum length is 200.
    + locationTypeCode (optional) - Location Type Code. Maximum length is 3.

            
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
			
### Get Schema for Iacuc Location Names [GET /iacuc/api/v1/iacuc-location-names/]
	                                          
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
    
            {"columns":["locationId","locationName","locationTypeCode"],"primaryKey":"locationId"}
		
### Get Blueprint API specification for Iacuc Location Names [GET /iacuc/api/v1/iacuc-location-names/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Location Names.md"
            transfer-encoding:chunked


### Update Iacuc Location Names [PUT /iacuc/api/v1/iacuc-location-names/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Location Names [PUT /iacuc/api/v1/iacuc-location-names/]

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

### Insert Iacuc Location Names [POST /iacuc/api/v1/iacuc-location-names/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"locationId": "(val)","locationName": "(val)","locationTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Location Names [POST /iacuc/api/v1/iacuc-location-names/]

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
            
### Delete Iacuc Location Names by Key [DELETE /iacuc/api/v1/iacuc-location-names/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Location Names [DELETE /iacuc/api/v1/iacuc-location-names/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Location Names with Matching [DELETE /iacuc/api/v1/iacuc-location-names/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + locationId (optional) - Location Name Code. Maximum length is 3.
    + locationName (optional) - Location Name. Maximum length is 200.
    + locationTypeCode (optional) - Location Type Code. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
