## Sub Award Amount Infos [/subaward/api/v1/sub-award-amount-infos/]

### Get Sub Award Amount Infos by Key [GET /subaward/api/v1/sub-award-amount-infos/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Amount Infos [GET /subaward/api/v1/sub-award-amount-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"},
              {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Amount Infos with Filtering [GET /subaward/api/v1/sub-award-amount-infos/]
    
+ Parameters

    + subAwardAmountInfoId (optional) - Subaward Amount Info Id. Maximum length is 22.
    + subAwardId (optional) - Subaward Id. Maximum length is 22.
    + sequenceNumber (optional) - 
    + subAwardCode (optional) - 
    + obligatedAmount (optional) - Obligated Amount. Maximum length is 22.
    + obligatedChange (optional) - Obligated Change. Maximum length is 12.
    + anticipatedAmount (optional) - Anticipated Amount. Maximum length is 22.
    + anticipatedChange (optional) - Anticipated Change. Maximum length is 12.
    + effectiveDate (optional) - Effective Date. Maximum length is 22.
    + comments (optional) - Comments. Maximum length is 4000.
    + fileName (optional) - File Name. Maximum length is 150.
    + mimeType (optional) - Mime Type. Maximum length is 4000.
    + modificationEffectiveDate (optional) - Modification Effective Date. Maximum length is 10.
    + modificationID (optional) - Modification ID. Maximum length is 50.
    + periodofPerformanceStartDate (optional) - Period of Performance Start Date. Maximum length is 10.
    + periodofPerformanceEndDate (optional) - Period of Performance End Date. Maximum length is 10.
    + fileDataId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"},
              {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Amount Infos [GET /subaward/api/v1/sub-award-amount-infos/]
	                                          
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
    
            {"columns":["subAwardAmountInfoId","subAwardId","sequenceNumber","subAwardCode","obligatedAmount","obligatedChange","anticipatedAmount","anticipatedChange","effectiveDate","comments","fileName","mimeType","modificationEffectiveDate","modificationID","periodofPerformanceStartDate","periodofPerformanceEndDate","fileDataId"],"primaryKey":"subAwardAmountInfoId"}
		
### Get Blueprint API specification for Sub Award Amount Infos [GET /subaward/api/v1/sub-award-amount-infos/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Amount Infos.md"
            transfer-encoding:chunked


### Update Sub Award Amount Infos [PUT /subaward/api/v1/sub-award-amount-infos/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Amount Infos [PUT /subaward/api/v1/sub-award-amount-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"},
              {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Sub Award Amount Infos [POST /subaward/api/v1/sub-award-amount-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Amount Infos [POST /subaward/api/v1/sub-award-amount-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"},
              {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"},
              {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Sub Award Amount Infos by Key [DELETE /subaward/api/v1/sub-award-amount-infos/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Amount Infos [DELETE /subaward/api/v1/sub-award-amount-infos/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Amount Infos with Matching [DELETE /subaward/api/v1/sub-award-amount-infos/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + subAwardAmountInfoId (optional) - Subaward Amount Info Id. Maximum length is 22.
    + subAwardId (optional) - Subaward Id. Maximum length is 22.
    + sequenceNumber (optional) - 
    + subAwardCode (optional) - 
    + obligatedAmount (optional) - Obligated Amount. Maximum length is 22.
    + obligatedChange (optional) - Obligated Change. Maximum length is 12.
    + anticipatedAmount (optional) - Anticipated Amount. Maximum length is 22.
    + anticipatedChange (optional) - Anticipated Change. Maximum length is 12.
    + effectiveDate (optional) - Effective Date. Maximum length is 22.
    + comments (optional) - Comments. Maximum length is 4000.
    + fileName (optional) - File Name. Maximum length is 150.
    + mimeType (optional) - Mime Type. Maximum length is 4000.
    + modificationEffectiveDate (optional) - Modification Effective Date. Maximum length is 10.
    + modificationID (optional) - Modification ID. Maximum length is 50.
    + periodofPerformanceStartDate (optional) - Period of Performance Start Date. Maximum length is 10.
    + periodofPerformanceEndDate (optional) - Period of Performance End Date. Maximum length is 10.
    + fileDataId (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
