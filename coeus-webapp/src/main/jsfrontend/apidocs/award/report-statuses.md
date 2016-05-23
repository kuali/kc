## Report Statuses [/award/api/v1/report-statuses/]

### Get Report Statuses by Key [GET /award/api/v1/report-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Report Statuses [GET /award/api/v1/report-statuses/]
	 
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

### Get All Report Statuses with Filtering [GET /award/api/v1/report-statuses/]
    
+ Parameters

    + reportStatusCode (optional) - Report Status Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + active (optional) - Active. Maximum length is 1.

            
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
			
### Get Schema for Report Statuses [GET /award/api/v1/report-statuses/]
	                                          
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
		
### Get Blueprint API specification for Report Statuses [GET /award/api/v1/report-statuses/]
	 
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
### Update Report Statuses [PUT /award/api/v1/report-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Report Statuses [PUT /award/api/v1/report-statuses/]

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
### Insert Report Statuses [POST /award/api/v1/report-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"reportStatusCode": "(val)","description": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Report Statuses [POST /award/api/v1/report-statuses/]

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
### Delete Report Statuses by Key [DELETE /award/api/v1/report-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Report Statuses [DELETE /award/api/v1/report-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Report Statuses with Matching [DELETE /award/api/v1/report-statuses/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + reportStatusCode (optional) - Report Status Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
