## Iacuc Batch Correspondence Details [/research-sys/api/v1/iacuc-batch-correspondence-details/]

### Get Iacuc Batch Correspondence Details by Key [GET /research-sys/api/v1/iacuc-batch-correspondence-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Batch Correspondence Details [GET /research-sys/api/v1/iacuc-batch-correspondence-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"},
              {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Batch Correspondence Details with Filtering [GET /research-sys/api/v1/iacuc-batch-correspondence-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + batchCorrespondenceDetailId
            + batchCorrespondenceTypeCode
            + protoCorrespTypeCode
            + daysToEvent
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"},
              {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Batch Correspondence Details [GET /research-sys/api/v1/iacuc-batch-correspondence-details/]
	 
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
		
### Get Blueprint API specification for Iacuc Batch Correspondence Details [GET /research-sys/api/v1/iacuc-batch-correspondence-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Batch Correspondence Details.md"
            transfer-encoding:chunked


### Update Iacuc Batch Correspondence Details [PUT /research-sys/api/v1/iacuc-batch-correspondence-details/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Batch Correspondence Details [PUT /research-sys/api/v1/iacuc-batch-correspondence-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"},
              {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Batch Correspondence Details [POST /research-sys/api/v1/iacuc-batch-correspondence-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Batch Correspondence Details [POST /research-sys/api/v1/iacuc-batch-correspondence-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"},
              {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"},
              {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Batch Correspondence Details by Key [DELETE /research-sys/api/v1/iacuc-batch-correspondence-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Batch Correspondence Details [DELETE /research-sys/api/v1/iacuc-batch-correspondence-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Batch Correspondence Details with Matching [DELETE /research-sys/api/v1/iacuc-batch-correspondence-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + batchCorrespondenceDetailId
            + batchCorrespondenceTypeCode
            + protoCorrespTypeCode
            + daysToEvent


+ Response 204
