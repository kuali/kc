## Closeout Report Types [/award/api/v1/closeout-report-types/]

### Get Closeout Report Types by Key [GET /award/api/v1/closeout-report-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"closeoutReportCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Closeout Report Types [GET /award/api/v1/closeout-report-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"closeoutReportCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"closeoutReportCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Closeout Report Types with Filtering [GET /award/api/v1/closeout-report-types/]
    
+ Parameters

    + closeoutReportCode (optional) - Closeout Report Code. Maximum length is 3.
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
              {"closeoutReportCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"closeoutReportCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Closeout Report Types [GET /award/api/v1/closeout-report-types/]
	                                          
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
    
            {"columns":["closeoutReportCode","description"],"primaryKey":"closeoutReportCode"}
		
### Get Blueprint API specification for Closeout Report Types [GET /award/api/v1/closeout-report-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Closeout Report Types.md"
            transfer-encoding:chunked
### Update Closeout Report Types [PUT /award/api/v1/closeout-report-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"closeoutReportCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Closeout Report Types [PUT /award/api/v1/closeout-report-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"closeoutReportCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"closeoutReportCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Closeout Report Types [POST /award/api/v1/closeout-report-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"closeoutReportCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"closeoutReportCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Closeout Report Types [POST /award/api/v1/closeout-report-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"closeoutReportCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"closeoutReportCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"closeoutReportCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"closeoutReportCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Closeout Report Types by Key [DELETE /award/api/v1/closeout-report-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Closeout Report Types [DELETE /award/api/v1/closeout-report-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Closeout Report Types with Matching [DELETE /award/api/v1/closeout-report-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + closeoutReportCode (optional) - Closeout Report Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
