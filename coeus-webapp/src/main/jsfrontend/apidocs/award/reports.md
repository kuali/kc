## Reports [/research-sys/api/v1/reports/]

### Get Reports by Key [GET /research-sys/api/v1/reports/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Reports [GET /research-sys/api/v1/reports/]
	 
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

### Get All Reports with Filtering [GET /research-sys/api/v1/reports/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + reportCode
            + description
            + finalReportFlag
            + active
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Reports [GET /research-sys/api/v1/reports/]
	 
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
		
### Get Blueprint API specification for Reports [GET /research-sys/api/v1/reports/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Reports.md"
            transfer-encoding:chunked


### Update Reports [PUT /research-sys/api/v1/reports/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Reports [PUT /research-sys/api/v1/reports/]

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

### Insert Reports [POST /research-sys/api/v1/reports/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"reportCode": "(val)","description": "(val)","finalReportFlag": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Reports [POST /research-sys/api/v1/reports/]

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
            
### Delete Reports by Key [DELETE /research-sys/api/v1/reports/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Reports [DELETE /research-sys/api/v1/reports/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Reports with Matching [DELETE /research-sys/api/v1/reports/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + reportCode
            + description
            + finalReportFlag
            + active


+ Response 204
