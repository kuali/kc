## Released Sub Award Amounts [/subaward/api/v1/released-sub-award-amounts/]

### Get Released Sub Award Amounts by Key [GET /subaward/api/v1/released-sub-award-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"}

### Get All Released Sub Award Amounts [GET /subaward/api/v1/released-sub-award-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"},
              {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"}
            ]

### Get All Released Sub Award Amounts with Filtering [GET /subaward/api/v1/released-sub-award-amounts/]
    
+ Parameters

    + subAwardAmtReleasedId (optional) - Subaward Amt Released Id. Maximum length is 22.
    + documentNumber (optional) - 
    + subAwardId (optional) - 
    + sequenceNumber (optional) - 
    + subAwardCode (optional) - Subaward ID. Maximum length is 22.
    + amountReleased (optional) - Amount Released. Maximum length is 16.
    + effectiveDate (optional) - Effective Date. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 300.
    + invoiceNumber (optional) - Invoice ID. Maximum length is 10.
    + startDate (optional) - Start Date. Maximum length is 10.
    + endDate (optional) - End Date. Maximum length is 10.
    + document (optional) - Document. Maximum length is 4000.
    + fileName (optional) - File Name. Maximum length is 150.
    + createdBy (optional) - 
    + createdDate (optional) - 
    + mimeType (optional) - Mime Type. Maximum length is 4000.
    + invoiceStatus (optional) - Invoice Status. Maximum length is 20.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"},
              {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Released Sub Award Amounts [GET /subaward/api/v1/released-sub-award-amounts/]
	                                          
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
    
            {"columns":["subAwardAmtReleasedId","documentNumber","subAwardId","sequenceNumber","subAwardCode","amountReleased","effectiveDate","comments","invoiceNumber","startDate","endDate","document","fileName","createdBy","createdDate","mimeType","invoiceStatus"],"primaryKey":"subAwardAmtReleasedId"}
		
### Get Blueprint API specification for Released Sub Award Amounts [GET /subaward/api/v1/released-sub-award-amounts/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Released Sub Award Amounts.md"
            transfer-encoding:chunked
### Update Released Sub Award Amounts [PUT /subaward/api/v1/released-sub-award-amounts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Released Sub Award Amounts [PUT /subaward/api/v1/released-sub-award-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"},
              {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Released Sub Award Amounts [POST /subaward/api/v1/released-sub-award-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Released Sub Award Amounts [POST /subaward/api/v1/released-sub-award-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"},
              {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"},
              {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"}
            ]
### Delete Released Sub Award Amounts by Key [DELETE /subaward/api/v1/released-sub-award-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Released Sub Award Amounts [DELETE /subaward/api/v1/released-sub-award-amounts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Released Sub Award Amounts with Matching [DELETE /subaward/api/v1/released-sub-award-amounts/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + subAwardAmtReleasedId (optional) - Subaward Amt Released Id. Maximum length is 22.
    + documentNumber (optional) - 
    + subAwardId (optional) - 
    + sequenceNumber (optional) - 
    + subAwardCode (optional) - Subaward ID. Maximum length is 22.
    + amountReleased (optional) - Amount Released. Maximum length is 16.
    + effectiveDate (optional) - Effective Date. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 300.
    + invoiceNumber (optional) - Invoice ID. Maximum length is 10.
    + startDate (optional) - Start Date. Maximum length is 10.
    + endDate (optional) - End Date. Maximum length is 10.
    + document (optional) - Document. Maximum length is 4000.
    + fileName (optional) - File Name. Maximum length is 150.
    + createdBy (optional) - 
    + createdDate (optional) - 
    + mimeType (optional) - Mime Type. Maximum length is 4000.
    + invoiceStatus (optional) - Invoice Status. Maximum length is 20.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
