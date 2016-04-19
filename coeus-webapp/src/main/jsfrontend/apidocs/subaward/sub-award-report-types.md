## Sub Award Report Types [/subaward/api/v1/sub-award-report-types/]

### Get Sub Award Report Types by Key [GET /subaward/api/v1/sub-award-report-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Report Types [GET /subaward/api/v1/sub-award-report-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Report Types with Filtering [GET /subaward/api/v1/sub-award-report-types/]
    
+ Parameters

    + subAwardReportTypeCode (optional) - Report Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + sortId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Report Types [GET /subaward/api/v1/sub-award-report-types/]
	                                          
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
    
            {"columns":["subAwardReportTypeCode","description","sortId"],"primaryKey":"subAwardReportTypeCode"}
		
### Get Blueprint API specification for Sub Award Report Types [GET /subaward/api/v1/sub-award-report-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Report Types.md"
            transfer-encoding:chunked


### Update Sub Award Report Types [PUT /subaward/api/v1/sub-award-report-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Report Types [PUT /subaward/api/v1/sub-award-report-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Sub Award Report Types [POST /subaward/api/v1/sub-award-report-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Report Types [POST /subaward/api/v1/sub-award-report-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Sub Award Report Types by Key [DELETE /subaward/api/v1/sub-award-report-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Report Types [DELETE /subaward/api/v1/sub-award-report-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Report Types with Matching [DELETE /subaward/api/v1/sub-award-report-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + subAwardReportTypeCode (optional) - Report Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + sortId (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
