## Report Statuses [/research-sys/api/v1/report-statuses/]

### Get Report Statuses by Key [GET /research-sys/api/v1/report-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Report Statuses [GET /research-sys/api/v1/report-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Report Statuses with Filtering [GET /research-sys/api/v1/report-statuses/]
    
+ Parameters

        + reportStatusCode
            + description
            + active

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Report Statuses [GET /research-sys/api/v1/report-statuses/]
	                                          
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
    
            {"columns":["reportStatusCode","description","active"],"primaryKey":"reportStatusCode"}
		
### Get Blueprint API specification for Report Statuses [GET /research-sys/api/v1/report-statuses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Report Statuses.md"
            transfer-encoding:chunked


### Update Report Statuses [PUT /research-sys/api/v1/report-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Report Statuses [PUT /research-sys/api/v1/report-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Report Statuses [POST /research-sys/api/v1/report-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Report Statuses [POST /research-sys/api/v1/report-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Report Statuses by Key [DELETE /research-sys/api/v1/report-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Report Statuses [DELETE /research-sys/api/v1/report-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Report Statuses with Matching [DELETE /research-sys/api/v1/report-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + reportStatusCode
            + description
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
