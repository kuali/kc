## Entity Citizenships [/research-sys/api/v1/entity-citizenships/]

### Get Entity Citizenships by Key [GET /research-sys/api/v1/entity-citizenships/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","startDateValue": "(val)","countryCode": "(val)","endDateValue": "(val)","active": "(val)","entityId": "(val)","statusCode": "(val)","_primaryKey": "(val)"}

### Get All Entity Citizenships [GET /research-sys/api/v1/entity-citizenships/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","startDateValue": "(val)","countryCode": "(val)","endDateValue": "(val)","active": "(val)","entityId": "(val)","statusCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","startDateValue": "(val)","countryCode": "(val)","endDateValue": "(val)","active": "(val)","entityId": "(val)","statusCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Citizenships with Filtering [GET /research-sys/api/v1/entity-citizenships/]
    
+ Parameters

    + id (optional) - 
    + startDateValue (optional) - 
    + countryCode (optional) - 
    + endDateValue (optional) - 
    + active (optional) - 
    + entityId (optional) - 
    + statusCode (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","startDateValue": "(val)","countryCode": "(val)","endDateValue": "(val)","active": "(val)","entityId": "(val)","statusCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","startDateValue": "(val)","countryCode": "(val)","endDateValue": "(val)","active": "(val)","entityId": "(val)","statusCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Citizenships [GET /research-sys/api/v1/entity-citizenships/]
	                                          
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
    
            {"columns":["id","startDateValue","countryCode","endDateValue","active","entityId","statusCode"],"primaryKey":"id"}
		
### Get Blueprint API specification for Entity Citizenships [GET /research-sys/api/v1/entity-citizenships/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Citizenships.md"
            transfer-encoding:chunked
### Update Entity Citizenships [PUT /research-sys/api/v1/entity-citizenships/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","startDateValue": "(val)","countryCode": "(val)","endDateValue": "(val)","active": "(val)","entityId": "(val)","statusCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Citizenships [PUT /research-sys/api/v1/entity-citizenships/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","startDateValue": "(val)","countryCode": "(val)","endDateValue": "(val)","active": "(val)","entityId": "(val)","statusCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","startDateValue": "(val)","countryCode": "(val)","endDateValue": "(val)","active": "(val)","entityId": "(val)","statusCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Entity Citizenships [POST /research-sys/api/v1/entity-citizenships/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","startDateValue": "(val)","countryCode": "(val)","endDateValue": "(val)","active": "(val)","entityId": "(val)","statusCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","startDateValue": "(val)","countryCode": "(val)","endDateValue": "(val)","active": "(val)","entityId": "(val)","statusCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Citizenships [POST /research-sys/api/v1/entity-citizenships/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","startDateValue": "(val)","countryCode": "(val)","endDateValue": "(val)","active": "(val)","entityId": "(val)","statusCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","startDateValue": "(val)","countryCode": "(val)","endDateValue": "(val)","active": "(val)","entityId": "(val)","statusCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","startDateValue": "(val)","countryCode": "(val)","endDateValue": "(val)","active": "(val)","entityId": "(val)","statusCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","startDateValue": "(val)","countryCode": "(val)","endDateValue": "(val)","active": "(val)","entityId": "(val)","statusCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Entity Citizenships by Key [DELETE /research-sys/api/v1/entity-citizenships/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Citizenships [DELETE /research-sys/api/v1/entity-citizenships/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Citizenships with Matching [DELETE /research-sys/api/v1/entity-citizenships/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + startDateValue (optional) - 
    + countryCode (optional) - 
    + endDateValue (optional) - 
    + active (optional) - 
    + entityId (optional) - 
    + statusCode (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
