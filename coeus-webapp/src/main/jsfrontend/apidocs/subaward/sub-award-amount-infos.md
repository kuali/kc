## Sub Award Amount Infos [/research-sys/api/v1/sub-award-amount-infos/]

### Get Sub Award Amount Infos by Key [GET /research-sys/api/v1/sub-award-amount-infos/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Amount Infos [GET /research-sys/api/v1/sub-award-amount-infos/]
	 
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

### Get All Sub Award Amount Infos with Filtering [GET /research-sys/api/v1/sub-award-amount-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + subAwardAmountInfoId
            + subAwardId
            + sequenceNumber
            + subAwardCode
            + obligatedAmount
            + obligatedChange
            + anticipatedAmount
            + anticipatedChange
            + effectiveDate
            + comments
            + fileName
            + mimeType
            + modificationEffectiveDate
            + modificationID
            + periodofPerformanceStartDate
            + periodofPerformanceEndDate
            + fileDataId
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"},
              {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Amount Infos [GET /research-sys/api/v1/sub-award-amount-infos/]
	 
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
		
### Get Blueprint API specification for Sub Award Amount Infos [GET /research-sys/api/v1/sub-award-amount-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Amount Infos.md"
            transfer-encoding:chunked


### Update Sub Award Amount Infos [PUT /research-sys/api/v1/sub-award-amount-infos/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Amount Infos [PUT /research-sys/api/v1/sub-award-amount-infos/]

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

### Insert Sub Award Amount Infos [POST /research-sys/api/v1/sub-award-amount-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardAmountInfoId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","obligatedAmount": "(val)","obligatedChange": "(val)","anticipatedAmount": "(val)","anticipatedChange": "(val)","effectiveDate": "(val)","comments": "(val)","fileName": "(val)","mimeType": "(val)","modificationEffectiveDate": "(val)","modificationID": "(val)","periodofPerformanceStartDate": "(val)","periodofPerformanceEndDate": "(val)","fileDataId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Amount Infos [POST /research-sys/api/v1/sub-award-amount-infos/]

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
            
### Delete Sub Award Amount Infos by Key [DELETE /research-sys/api/v1/sub-award-amount-infos/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Amount Infos [DELETE /research-sys/api/v1/sub-award-amount-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Sub Award Amount Infos with Matching [DELETE /research-sys/api/v1/sub-award-amount-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + subAwardAmountInfoId
            + subAwardId
            + sequenceNumber
            + subAwardCode
            + obligatedAmount
            + obligatedChange
            + anticipatedAmount
            + anticipatedChange
            + effectiveDate
            + comments
            + fileName
            + mimeType
            + modificationEffectiveDate
            + modificationID
            + periodofPerformanceStartDate
            + periodofPerformanceEndDate
            + fileDataId


+ Response 204
