## Released Sub Award Amounts [/research-sys/api/v1/released-sub-award-amounts/]

### Get Released Sub Award Amounts by Key [GET /research-sys/api/v1/released-sub-award-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"}

### Get All Released Sub Award Amounts [GET /research-sys/api/v1/released-sub-award-amounts/]
	 
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

### Get All Released Sub Award Amounts with Filtering [GET /research-sys/api/v1/released-sub-award-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + subAwardAmtReleasedId
            + documentNumber
            + subAwardId
            + sequenceNumber
            + subAwardCode
            + amountReleased
            + effectiveDate
            + comments
            + invoiceNumber
            + startDate
            + endDate
            + document
            + fileName
            + createdBy
            + createdDate
            + mimeType
            + invoiceStatus
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"},
              {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Released Sub Award Amounts [GET /research-sys/api/v1/released-sub-award-amounts/]
	 
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
		
### Get Blueprint API specification for Released Sub Award Amounts [GET /research-sys/api/v1/released-sub-award-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Released Sub Award Amounts.md"
            transfer-encoding:chunked


### Update Released Sub Award Amounts [PUT /research-sys/api/v1/released-sub-award-amounts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Released Sub Award Amounts [PUT /research-sys/api/v1/released-sub-award-amounts/]

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

### Insert Released Sub Award Amounts [POST /research-sys/api/v1/released-sub-award-amounts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardAmtReleasedId": "(val)","documentNumber": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","amountReleased": "(val)","effectiveDate": "(val)","comments": "(val)","invoiceNumber": "(val)","startDate": "(val)","endDate": "(val)","document": "(val)","fileName": "(val)","createdBy": "(val)","createdDate": "(val)","mimeType": "(val)","invoiceStatus": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Released Sub Award Amounts [POST /research-sys/api/v1/released-sub-award-amounts/]

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
            
### Delete Released Sub Award Amounts by Key [DELETE /research-sys/api/v1/released-sub-award-amounts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Released Sub Award Amounts [DELETE /research-sys/api/v1/released-sub-award-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Released Sub Award Amounts with Matching [DELETE /research-sys/api/v1/released-sub-award-amounts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + subAwardAmtReleasedId
            + documentNumber
            + subAwardId
            + sequenceNumber
            + subAwardCode
            + amountReleased
            + effectiveDate
            + comments
            + invoiceNumber
            + startDate
            + endDate
            + document
            + fileName
            + createdBy
            + createdDate
            + mimeType
            + invoiceStatus


+ Response 204
