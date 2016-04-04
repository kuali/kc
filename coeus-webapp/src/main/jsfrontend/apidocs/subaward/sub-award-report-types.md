## Sub Award Report Types [/research-sys/api/v1/sub-award-report-types/]

### Get Sub Award Report Types by Key [GET /research-sys/api/v1/sub-award-report-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Report Types [GET /research-sys/api/v1/sub-award-report-types/]
	 
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

### Get All Sub Award Report Types with Filtering [GET /research-sys/api/v1/sub-award-report-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + subAwardReportTypeCode
            + description
            + sortId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Report Types [GET /research-sys/api/v1/sub-award-report-types/]
	 
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
		
### Get Blueprint API specification for Sub Award Report Types [GET /research-sys/api/v1/sub-award-report-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Report Types.md"
            transfer-encoding:chunked


### Update Sub Award Report Types [PUT /research-sys/api/v1/sub-award-report-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Report Types [PUT /research-sys/api/v1/sub-award-report-types/]

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

### Insert Sub Award Report Types [POST /research-sys/api/v1/sub-award-report-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardReportTypeCode": "(val)","description": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Report Types [POST /research-sys/api/v1/sub-award-report-types/]

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
            
### Delete Sub Award Report Types by Key [DELETE /research-sys/api/v1/sub-award-report-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Report Types [DELETE /research-sys/api/v1/sub-award-report-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Sub Award Report Types with Matching [DELETE /research-sys/api/v1/sub-award-report-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + subAwardReportTypeCode
            + description
            + sortId


+ Response 204
