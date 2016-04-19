## Schedule Statuses [/research-common/api/v1/schedule-statuses/]

### Get Schedule Statuses by Key [GET /research-common/api/v1/schedule-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"scheduleStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Schedule Statuses [GET /research-common/api/v1/schedule-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"scheduleStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"scheduleStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Schedule Statuses with Filtering [GET /research-common/api/v1/schedule-statuses/]
    
+ Parameters

    + scheduleStatusCode (optional) - Schedule Status Code. Maximum length is 22.
    + description (optional) - Description. Maximum length is 200.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"scheduleStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"scheduleStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Schedule Statuses [GET /research-common/api/v1/schedule-statuses/]
	                                          
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
    
            {"columns":["scheduleStatusCode","description"],"primaryKey":"scheduleStatusCode"}
		
### Get Blueprint API specification for Schedule Statuses [GET /research-common/api/v1/schedule-statuses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Schedule Statuses.md"
            transfer-encoding:chunked


### Update Schedule Statuses [PUT /research-common/api/v1/schedule-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"scheduleStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Schedule Statuses [PUT /research-common/api/v1/schedule-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"scheduleStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"scheduleStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Schedule Statuses [POST /research-common/api/v1/schedule-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"scheduleStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"scheduleStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Schedule Statuses [POST /research-common/api/v1/schedule-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"scheduleStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"scheduleStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"scheduleStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"scheduleStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Schedule Statuses by Key [DELETE /research-common/api/v1/schedule-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Schedule Statuses [DELETE /research-common/api/v1/schedule-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Schedule Statuses with Matching [DELETE /research-common/api/v1/schedule-statuses/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + scheduleStatusCode (optional) - Schedule Status Code. Maximum length is 22.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
