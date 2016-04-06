## Schedule Act Item Types [/research-sys/api/v1/schedule-act-item-types/]

### Get Schedule Act Item Types by Key [GET /research-sys/api/v1/schedule-act-item-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"scheduleActItemTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Schedule Act Item Types [GET /research-sys/api/v1/schedule-act-item-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"scheduleActItemTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"scheduleActItemTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Schedule Act Item Types with Filtering [GET /research-sys/api/v1/schedule-act-item-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + scheduleActItemTypeCode
            + description
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"scheduleActItemTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"scheduleActItemTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Schedule Act Item Types [GET /research-sys/api/v1/schedule-act-item-types/]
	 
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
		
### Get Blueprint API specification for Schedule Act Item Types [GET /research-sys/api/v1/schedule-act-item-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Schedule Act Item Types.md"
            transfer-encoding:chunked


### Update Schedule Act Item Types [PUT /research-sys/api/v1/schedule-act-item-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"scheduleActItemTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Schedule Act Item Types [PUT /research-sys/api/v1/schedule-act-item-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"scheduleActItemTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"scheduleActItemTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Schedule Act Item Types [POST /research-sys/api/v1/schedule-act-item-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"scheduleActItemTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"scheduleActItemTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Schedule Act Item Types [POST /research-sys/api/v1/schedule-act-item-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"scheduleActItemTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"scheduleActItemTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"scheduleActItemTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"scheduleActItemTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Schedule Act Item Types by Key [DELETE /research-sys/api/v1/schedule-act-item-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Schedule Act Item Types [DELETE /research-sys/api/v1/schedule-act-item-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Schedule Act Item Types with Matching [DELETE /research-sys/api/v1/schedule-act-item-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + scheduleActItemTypeCode
            + description


+ Response 204
