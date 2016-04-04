## Sub Award Reports [/research-sys/api/v1/sub-award-reports/]

### Get Sub Award Reports by Key [GET /research-sys/api/v1/sub-award-reports/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Reports [GET /research-sys/api/v1/sub-award-reports/]
	 
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

### Get All Sub Award Reports with Filtering [GET /research-sys/api/v1/sub-award-reports/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + subAwardReportId
            + subAwardId
            + subAwardCode
            + sequenceNumber
            + subAwardReportTypeCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"},
              {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Reports [GET /research-sys/api/v1/sub-award-reports/]
	 
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
		
### Get Blueprint API specification for Sub Award Reports [GET /research-sys/api/v1/sub-award-reports/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Reports.md"
            transfer-encoding:chunked


### Update Sub Award Reports [PUT /research-sys/api/v1/sub-award-reports/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Reports [PUT /research-sys/api/v1/sub-award-reports/]

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

### Insert Sub Award Reports [POST /research-sys/api/v1/sub-award-reports/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardReportId": "(val)","subAwardId": "(val)","subAwardCode": "(val)","sequenceNumber": "(val)","subAwardReportTypeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Reports [POST /research-sys/api/v1/sub-award-reports/]

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
            
### Delete Sub Award Reports by Key [DELETE /research-sys/api/v1/sub-award-reports/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Reports [DELETE /research-sys/api/v1/sub-award-reports/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Sub Award Reports with Matching [DELETE /research-sys/api/v1/sub-award-reports/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + subAwardReportId
            + subAwardId
            + subAwardCode
            + sequenceNumber
            + subAwardReportTypeCode


+ Response 204
