## Sub Award Reports [/subaward/api/v1/sub-award-reports/]

### Get Sub Award Reports by Key [GET /subaward/api/v1/sub-award-reports/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Reports [GET /subaward/api/v1/sub-award-reports/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"},
              {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Reports with Filtering [GET /subaward/api/v1/sub-award-reports/]
    
+ Parameters

    + subAwardReportId (optional) - Report ID. Maximum length is 15.
    + subAwardId (optional) - Subaward ID. Maximum length is 22.
    + subAwardCode (optional) - Subaward ID. Maximum length is 22.
    + sequenceNumber (optional) - 
    + subAwardReportTypeCode (optional) - Report Type. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"},
              {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Reports [GET /subaward/api/v1/sub-award-reports/]
	                                          
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
    
            {"columns":["subAwardReportId","subAwardId","subAwardCode","sequenceNumber","subAwardReportTypeCode"],"primaryKey":"subAwardReportId"}
		
### Get Blueprint API specification for Sub Award Reports [GET /subaward/api/v1/sub-award-reports/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Reports.md"
            transfer-encoding:chunked


### Update Sub Award Reports [PUT /subaward/api/v1/sub-award-reports/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Reports [PUT /subaward/api/v1/sub-award-reports/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"},
              {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Sub Award Reports [POST /subaward/api/v1/sub-award-reports/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Reports [POST /subaward/api/v1/sub-award-reports/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"},
              {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"},
              {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Sub Award Reports by Key [DELETE /subaward/api/v1/sub-award-reports/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Reports [DELETE /subaward/api/v1/sub-award-reports/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Reports with Matching [DELETE /subaward/api/v1/sub-award-reports/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + subAwardReportId (optional) - Report ID. Maximum length is 15.
    + subAwardId (optional) - Subaward ID. Maximum length is 22.
    + subAwardCode (optional) - Subaward ID. Maximum length is 22.
    + sequenceNumber (optional) - 
    + subAwardReportTypeCode (optional) - Report Type. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
