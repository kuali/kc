## Minute Entry Types [/research-sys/api/v1/minute-entry-types/]

### Get Minute Entry Types by Key [GET /research-sys/api/v1/minute-entry-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Minute Entry Types [GET /research-sys/api/v1/minute-entry-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Minute Entry Types with Filtering [GET /research-sys/api/v1/minute-entry-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + minuteEntryTypeCode
            + sortId
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Minute Entry Types [GET /research-sys/api/v1/minute-entry-types/]
	 
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
		
### Get Blueprint API specification for Minute Entry Types [GET /research-sys/api/v1/minute-entry-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Minute Entry Types.md"
            transfer-encoding:chunked


### Update Minute Entry Types [PUT /research-sys/api/v1/minute-entry-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Minute Entry Types [PUT /research-sys/api/v1/minute-entry-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Minute Entry Types [POST /research-sys/api/v1/minute-entry-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Minute Entry Types [POST /research-sys/api/v1/minute-entry-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"minuteEntryTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Minute Entry Types by Key [DELETE /research-sys/api/v1/minute-entry-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Minute Entry Types [DELETE /research-sys/api/v1/minute-entry-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Minute Entry Types with Matching [DELETE /research-sys/api/v1/minute-entry-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + minuteEntryTypeCode
            + sortId
            + description


+ Response 204
