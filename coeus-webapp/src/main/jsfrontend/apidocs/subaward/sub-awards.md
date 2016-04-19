## Sub Awards [/subaward/api/v1/sub-awards/]

### Get Sub Awards by Key [GET /subaward/api/v1/sub-awards/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"}

### Get All Sub Awards [GET /subaward/api/v1/sub-awards/]
	 
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

### Get All Sub Awards with Filtering [GET /subaward/api/v1/sub-awards/]
    
+ Parameters

    + subAwardId (optional) - Subaward ID. Maximum length is 22.
    + documentNumber (optional) - 
    + sequenceNumber (optional) - 
    + subAwardCode (optional) - Subaward ID. Maximum length is 22.
    + organizationId (optional) - Subrecipient. Maximum length is 60.
    + startDate (optional) - Start Date. Maximum length is 10.
    + endDate (optional) - End Date. Maximum length is 10.
    + subAwardTypeCode (optional) - Subaward Type. Maximum length is 22.
    + purchaseOrderNum (optional) - Purchase Order ID. Maximum length is 10.
    + title (optional) - Title. Maximum length is 200.
    + statusCode (optional) - Status Code. Maximum length is 22.
    + accountNumber (optional) - Account ID. Maximum length is 16.
    + vendorNumber (optional) - Vendor ID. Maximum length is 10.
    + requisitionerId (optional) - Requisitioner User Name. Maximum length is 40.
    + requisitionerUnit (optional) - Requisitioner Unit. Maximum length is 80.
    + archiveLocation (optional) - Archive Location. Maximum length is 50.
    + closeoutDate (optional) - Closeout Date. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 4000.
    + siteInvestigator (optional) - Site Investigator. Maximum length is 22.
    + costType (optional) - Cost Type. Maximum length is 22.
    + executionDate (optional) - Execution Date. Maximum length is 10.
    + requisitionId (optional) - Requisition ID. Maximum length is 50.
    + fedAwardProjDesc (optional) - Federal Award Project Description. Maximum length is 200.
    + fAndARate (optional) - F & A Rate. Maximum length is 5.
    + deMinimus (optional) - de Minimus. Maximum length is 1.
    + subAwardSequenceStatus (optional) - 

            
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
			
### Get Schema for Sub Awards [GET /subaward/api/v1/sub-awards/]
	                                          
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
    
            {"columns":["subAwardId","documentNumber","sequenceNumber","subAwardCode","organizationId","startDate","endDate","subAwardTypeCode","purchaseOrderNum","title","statusCode","accountNumber","vendorNumber","requisitionerId","requisitionerUnit","archiveLocation","closeoutDate","comments","siteInvestigator","costType","executionDate","requisitionId","fedAwardProjDesc","fAndARate","deMinimus","subAwardSequenceStatus"],"primaryKey":"subAwardId"}
		
### Get Blueprint API specification for Sub Awards [GET /subaward/api/v1/sub-awards/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Awards.md"
            transfer-encoding:chunked


### Update Sub Awards [PUT /subaward/api/v1/sub-awards/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Awards [PUT /subaward/api/v1/sub-awards/]

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

### Insert Sub Awards [POST /subaward/api/v1/sub-awards/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardId": "(val)","documentNumber": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","organizationId": "(val)","startDate": "(val)","endDate": "(val)","subAwardTypeCode": "(val)","purchaseOrderNum": "(val)","title": "(val)","statusCode": "(val)","accountNumber": "(val)","vendorNumber": "(val)","requisitionerId": "(val)","requisitionerUnit": "(val)","archiveLocation": "(val)","closeoutDate": "(val)","comments": "(val)","siteInvestigator": "(val)","costType": "(val)","executionDate": "(val)","requisitionId": "(val)","fedAwardProjDesc": "(val)","fAndARate": "(val)","deMinimus": "(val)","subAwardSequenceStatus": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Awards [POST /subaward/api/v1/sub-awards/]

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
            
### Delete Sub Awards by Key [DELETE /subaward/api/v1/sub-awards/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Awards [DELETE /subaward/api/v1/sub-awards/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Awards with Matching [DELETE /subaward/api/v1/sub-awards/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + subAwardId (optional) - Subaward ID. Maximum length is 22.
    + documentNumber (optional) - 
    + sequenceNumber (optional) - 
    + subAwardCode (optional) - Subaward ID. Maximum length is 22.
    + organizationId (optional) - Subrecipient. Maximum length is 60.
    + startDate (optional) - Start Date. Maximum length is 10.
    + endDate (optional) - End Date. Maximum length is 10.
    + subAwardTypeCode (optional) - Subaward Type. Maximum length is 22.
    + purchaseOrderNum (optional) - Purchase Order ID. Maximum length is 10.
    + title (optional) - Title. Maximum length is 200.
    + statusCode (optional) - Status Code. Maximum length is 22.
    + accountNumber (optional) - Account ID. Maximum length is 16.
    + vendorNumber (optional) - Vendor ID. Maximum length is 10.
    + requisitionerId (optional) - Requisitioner User Name. Maximum length is 40.
    + requisitionerUnit (optional) - Requisitioner Unit. Maximum length is 80.
    + archiveLocation (optional) - Archive Location. Maximum length is 50.
    + closeoutDate (optional) - Closeout Date. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 4000.
    + siteInvestigator (optional) - Site Investigator. Maximum length is 22.
    + costType (optional) - Cost Type. Maximum length is 22.
    + executionDate (optional) - Execution Date. Maximum length is 10.
    + requisitionId (optional) - Requisition ID. Maximum length is 50.
    + fedAwardProjDesc (optional) - Federal Award Project Description. Maximum length is 200.
    + fAndARate (optional) - F & A Rate. Maximum length is 5.
    + deMinimus (optional) - de Minimus. Maximum length is 1.
    + subAwardSequenceStatus (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
