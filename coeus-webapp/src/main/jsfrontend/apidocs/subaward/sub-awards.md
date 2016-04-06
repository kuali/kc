## Sub Awards [/research-sys/api/v1/sub-awards/]

### Get Sub Awards by Key [GET /research-sys/api/v1/sub-awards/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"}

### Get All Sub Awards [GET /research-sys/api/v1/sub-awards/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"},
              {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Awards with Filtering [GET /research-sys/api/v1/sub-awards/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + subAwardId
            + documentNumber
            + sequenceNumber
            + subAwardCode
            + organizationId
            + startDate
            + endDate
            + subAwardTypeCode
            + purchaseOrderNum
            + title
            + statusCode
            + accountNumber
            + vendorNumber
            + requisitionerId
            + requisitionerUnit
            + archiveLocation
            + closeoutDate
            + comments
            + siteInvestigator
            + costType
            + executionDate
            + requisitionId
            + fedAwardProjDesc
            + fAndARate
            + deMinimus
            + subAwardSequenceStatus
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"},
              {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Awards [GET /research-sys/api/v1/sub-awards/]
	 
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
		
### Get Blueprint API specification for Sub Awards [GET /research-sys/api/v1/sub-awards/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Awards.md"
            transfer-encoding:chunked


### Update Sub Awards [PUT /research-sys/api/v1/sub-awards/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Awards [PUT /research-sys/api/v1/sub-awards/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"},
              {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Sub Awards [POST /research-sys/api/v1/sub-awards/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Awards [POST /research-sys/api/v1/sub-awards/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"},
              {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"},
              {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Sub Awards by Key [DELETE /research-sys/api/v1/sub-awards/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Awards [DELETE /research-sys/api/v1/sub-awards/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Sub Awards with Matching [DELETE /research-sys/api/v1/sub-awards/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + subAwardId
            + documentNumber
            + sequenceNumber
            + subAwardCode
            + organizationId
            + startDate
            + endDate
            + subAwardTypeCode
            + purchaseOrderNum
            + title
            + statusCode
            + accountNumber
            + vendorNumber
            + requisitionerId
            + requisitionerUnit
            + archiveLocation
            + closeoutDate
            + comments
            + siteInvestigator
            + costType
            + executionDate
            + requisitionId
            + fedAwardProjDesc
            + fAndARate
            + deMinimus
            + subAwardSequenceStatus


+ Response 204
