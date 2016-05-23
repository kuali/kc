## Reports [/award/api/v1/reports/]

### Get Reports by Key [GET /award/api/v1/reports/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Reports [GET /award/api/v1/reports/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Reports with Filtering [GET /award/api/v1/reports/]
    
+ Parameters

    + reportCode (optional) - Report Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + finalReportFlag (optional) - Final Report Flag. Maximum length is 1.
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
              {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Reports [GET /award/api/v1/reports/]
	                                          
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
    
            {"columns":["reportCode","description","finalReportFlag","active"],"primaryKey":"reportCode"}
		
### Get Blueprint API specification for Reports [GET /award/api/v1/reports/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Reports.md"
            transfer-encoding:chunked
### Update Reports [PUT /award/api/v1/reports/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Reports [PUT /award/api/v1/reports/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Reports [POST /award/api/v1/reports/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Reports [POST /award/api/v1/reports/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Reports by Key [DELETE /award/api/v1/reports/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Reports [DELETE /award/api/v1/reports/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Reports with Matching [DELETE /award/api/v1/reports/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + reportCode (optional) - Report Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + finalReportFlag (optional) - Final Report Flag. Maximum length is 1.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
