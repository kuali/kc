## Iacuc Batch Correspondence Details [/iacuc/api/v1/iacuc-batch-correspondence-details/]

### Get Iacuc Batch Correspondence Details by Key [GET /iacuc/api/v1/iacuc-batch-correspondence-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Batch Correspondence Details [GET /iacuc/api/v1/iacuc-batch-correspondence-details/]
	 
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

### Get All Iacuc Batch Correspondence Details with Filtering [GET /iacuc/api/v1/iacuc-batch-correspondence-details/]
    
+ Parameters

    + batchCorrespondenceDetailId (optional) - Batch Corresponcence Detail Id. Maximum length is 12.
    + batchCorrespondenceTypeCode (optional) - Batch Corresponcence Type Code. Maximum length is 3.
    + protoCorrespTypeCode (optional) - Protocol Correspondence Type Code. Maximum length is 3.
    + daysToEvent (optional) - The days before/after the event. Maximum length is 3.

            
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
			
### Get Schema for Iacuc Batch Correspondence Details [GET /iacuc/api/v1/iacuc-batch-correspondence-details/]
	                                          
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
    
            {"columns":["batchCorrespondenceDetailId","batchCorrespondenceTypeCode","protoCorrespTypeCode","daysToEvent"],"primaryKey":"batchCorrespondenceDetailId"}
		
### Get Blueprint API specification for Iacuc Batch Correspondence Details [GET /iacuc/api/v1/iacuc-batch-correspondence-details/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Batch Correspondence Details.md"
            transfer-encoding:chunked
### Update Iacuc Batch Correspondence Details [PUT /iacuc/api/v1/iacuc-batch-correspondence-details/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Batch Correspondence Details [PUT /iacuc/api/v1/iacuc-batch-correspondence-details/]

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
### Insert Iacuc Batch Correspondence Details [POST /iacuc/api/v1/iacuc-batch-correspondence-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"batchCorrespondenceDetailId": "(val)","batchCorrespondenceTypeCode": "(val)","protoCorrespTypeCode": "(val)","daysToEvent": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Batch Correspondence Details [POST /iacuc/api/v1/iacuc-batch-correspondence-details/]

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
### Delete Iacuc Batch Correspondence Details by Key [DELETE /iacuc/api/v1/iacuc-batch-correspondence-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Batch Correspondence Details [DELETE /iacuc/api/v1/iacuc-batch-correspondence-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Batch Correspondence Details with Matching [DELETE /iacuc/api/v1/iacuc-batch-correspondence-details/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + batchCorrespondenceDetailId (optional) - Batch Corresponcence Detail Id. Maximum length is 12.
    + batchCorrespondenceTypeCode (optional) - Batch Corresponcence Type Code. Maximum length is 3.
    + protoCorrespTypeCode (optional) - Protocol Correspondence Type Code. Maximum length is 3.
    + daysToEvent (optional) - The days before/after the event. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
