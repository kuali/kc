## Valid Class Report Frequencies [/award/api/v1/valid-class-report-frequencies/]

### Get Valid Class Report Frequencies by Key [GET /award/api/v1/valid-class-report-frequencies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"}

### Get All Valid Class Report Frequencies [GET /award/api/v1/valid-class-report-frequencies/]
	 
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

### Get All Valid Class Report Frequencies with Filtering [GET /award/api/v1/valid-class-report-frequencies/]
    
+ Parameters

    + validClassReportFreqId (optional) - Valid Class Report Freq Id. Maximum length is 22.
    + reportClassCode (optional) - Report Class Code. Maximum length is 3.
    + reportCode (optional) - Report Code. Maximum length is 3.
    + frequencyCode (optional) - Frequency Code. Maximum length is 3.

            
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
			
### Get Schema for Valid Class Report Frequencies [GET /award/api/v1/valid-class-report-frequencies/]
	                                          
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
		
### Get Blueprint API specification for Valid Class Report Frequencies [GET /award/api/v1/valid-class-report-frequencies/]
	 
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


### Update Valid Class Report Frequencies [PUT /award/api/v1/valid-class-report-frequencies/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Valid Class Report Frequencies [PUT /award/api/v1/valid-class-report-frequencies/]

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

### Insert Valid Class Report Frequencies [POST /award/api/v1/valid-class-report-frequencies/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"validClassReportFreqId": "(val)","reportClassCode": "(val)","reportCode": "(val)","frequencyCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Valid Class Report Frequencies [POST /award/api/v1/valid-class-report-frequencies/]

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
            
### Delete Valid Class Report Frequencies by Key [DELETE /award/api/v1/valid-class-report-frequencies/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Class Report Frequencies [DELETE /award/api/v1/valid-class-report-frequencies/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Valid Class Report Frequencies with Matching [DELETE /award/api/v1/valid-class-report-frequencies/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + validClassReportFreqId (optional) - Valid Class Report Freq Id. Maximum length is 22.
    + reportClassCode (optional) - Report Class Code. Maximum length is 3.
    + reportCode (optional) - Report Code. Maximum length is 3.
    + frequencyCode (optional) - Frequency Code. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
