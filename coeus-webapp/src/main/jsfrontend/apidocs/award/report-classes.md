## Report Classes [/research-sys/api/v1/report-classes/]

### Get Report Classes by Key [GET /research-sys/api/v1/report-classes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"reportClassCode": "(val)","description": "(val)","generateReportRequirements": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Report Classes [GET /research-sys/api/v1/report-classes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"reportClassCode": "(val)","description": "(val)","generateReportRequirements": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"reportClassCode": "(val)","description": "(val)","generateReportRequirements": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Report Classes with Filtering [GET /research-sys/api/v1/report-classes/]
    
+ Parameters

        + reportClassCode
            + description
            + generateReportRequirements
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
              {"reportClassCode": "(val)","description": "(val)","generateReportRequirements": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"reportClassCode": "(val)","description": "(val)","generateReportRequirements": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Report Classes [GET /research-sys/api/v1/report-classes/]
	                                          
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
    
            {"columns":["reportClassCode","description","generateReportRequirements","active"],"primaryKey":"reportClassCode"}
		
### Get Blueprint API specification for Report Classes [GET /research-sys/api/v1/report-classes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Report Classes.md"
            transfer-encoding:chunked


### Update Report Classes [PUT /research-sys/api/v1/report-classes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportClassCode": "(val)","description": "(val)","generateReportRequirements": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Report Classes [PUT /research-sys/api/v1/report-classes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reportClassCode": "(val)","description": "(val)","generateReportRequirements": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"reportClassCode": "(val)","description": "(val)","generateReportRequirements": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Report Classes [POST /research-sys/api/v1/report-classes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"reportClassCode": "(val)","description": "(val)","generateReportRequirements": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"reportClassCode": "(val)","description": "(val)","generateReportRequirements": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Report Classes [POST /research-sys/api/v1/report-classes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"reportClassCode": "(val)","description": "(val)","generateReportRequirements": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"reportClassCode": "(val)","description": "(val)","generateReportRequirements": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"reportClassCode": "(val)","description": "(val)","generateReportRequirements": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"reportClassCode": "(val)","description": "(val)","generateReportRequirements": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Report Classes by Key [DELETE /research-sys/api/v1/report-classes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Report Classes [DELETE /research-sys/api/v1/report-classes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Report Classes with Matching [DELETE /research-sys/api/v1/report-classes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + reportClassCode
            + description
            + generateReportRequirements
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
