## Valid Class Report Frequencies [/research-sys/api/v1/valid-class-report-frequencies/]

### Get Valid Class Report Frequencies by Key [GET /research-sys/api/v1/valid-class-report-frequencies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"}

### Get All Valid Class Report Frequencies [GET /research-sys/api/v1/valid-class-report-frequencies/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"},
              {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Valid Class Report Frequencies with Filtering [GET /research-sys/api/v1/valid-class-report-frequencies/]
    
+ Parameters

        + validClassReportFreqId
            + reportClassCode
            + reportCode
            + frequencyCode

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"},
              {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Valid Class Report Frequencies [GET /research-sys/api/v1/valid-class-report-frequencies/]
	                                          
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
    
            {"columns":["validClassReportFreqId","reportClassCode","reportCode","frequencyCode"],"primaryKey":"validClassReportFreqId"}
		
### Get Blueprint API specification for Valid Class Report Frequencies [GET /research-sys/api/v1/valid-class-report-frequencies/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Valid Class Report Frequencies.md"
            transfer-encoding:chunked


### Update Valid Class Report Frequencies [PUT /research-sys/api/v1/valid-class-report-frequencies/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Class Report Frequencies [PUT /research-sys/api/v1/valid-class-report-frequencies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"},
              {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Valid Class Report Frequencies [POST /research-sys/api/v1/valid-class-report-frequencies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Class Report Frequencies [POST /research-sys/api/v1/valid-class-report-frequencies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"},
              {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"},
              {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Valid Class Report Frequencies by Key [DELETE /research-sys/api/v1/valid-class-report-frequencies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Class Report Frequencies [DELETE /research-sys/api/v1/valid-class-report-frequencies/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Class Report Frequencies with Matching [DELETE /research-sys/api/v1/valid-class-report-frequencies/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + validClassReportFreqId
            + reportClassCode
            + reportCode
            + frequencyCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
