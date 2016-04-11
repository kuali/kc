## Committee Batch Correspondences [/research-sys/api/v1/committee-batch-correspondences/]

### Get Committee Batch Correspondences by Key [GET /research-sys/api/v1/committee-batch-correspondences/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"committeeBatchCorrespondenceId": "(val)","committeeId": "(val)","batchCorrespondenceTypeCode": "(val)","batchRunDate": "(val)","timeWindowStart": "(val)","timeWindowEnd": "(val)","_primaryKey": "(val)"}

### Get All Committee Batch Correspondences [GET /research-sys/api/v1/committee-batch-correspondences/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"committeeBatchCorrespondenceId": "(val)","committeeId": "(val)","batchCorrespondenceTypeCode": "(val)","batchRunDate": "(val)","timeWindowStart": "(val)","timeWindowEnd": "(val)","_primaryKey": "(val)"},
              {"committeeBatchCorrespondenceId": "(val)","committeeId": "(val)","batchCorrespondenceTypeCode": "(val)","batchRunDate": "(val)","timeWindowStart": "(val)","timeWindowEnd": "(val)","_primaryKey": "(val)"}
            ]

### Get All Committee Batch Correspondences with Filtering [GET /research-sys/api/v1/committee-batch-correspondences/]
    
+ Parameters

        + committeeBatchCorrespondenceId
            + committeeId
            + batchCorrespondenceTypeCode
            + batchRunDate
            + timeWindowStart
            + timeWindowEnd

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"committeeBatchCorrespondenceId": "(val)","committeeId": "(val)","batchCorrespondenceTypeCode": "(val)","batchRunDate": "(val)","timeWindowStart": "(val)","timeWindowEnd": "(val)","_primaryKey": "(val)"},
              {"committeeBatchCorrespondenceId": "(val)","committeeId": "(val)","batchCorrespondenceTypeCode": "(val)","batchRunDate": "(val)","timeWindowStart": "(val)","timeWindowEnd": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Committee Batch Correspondences [GET /research-sys/api/v1/committee-batch-correspondences/]
	                                          
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
    
            {"columns":["committeeBatchCorrespondenceId","committeeId","batchCorrespondenceTypeCode","batchRunDate","timeWindowStart","timeWindowEnd"],"primaryKey":"committeeBatchCorrespondenceId"}
		
### Get Blueprint API specification for Committee Batch Correspondences [GET /research-sys/api/v1/committee-batch-correspondences/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committee Batch Correspondences.md"
            transfer-encoding:chunked


### Update Committee Batch Correspondences [PUT /research-sys/api/v1/committee-batch-correspondences/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeBatchCorrespondenceId": "(val)","committeeId": "(val)","batchCorrespondenceTypeCode": "(val)","batchRunDate": "(val)","timeWindowStart": "(val)","timeWindowEnd": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Batch Correspondences [PUT /research-sys/api/v1/committee-batch-correspondences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"committeeBatchCorrespondenceId": "(val)","committeeId": "(val)","batchCorrespondenceTypeCode": "(val)","batchRunDate": "(val)","timeWindowStart": "(val)","timeWindowEnd": "(val)","_primaryKey": "(val)"},
              {"committeeBatchCorrespondenceId": "(val)","committeeId": "(val)","batchCorrespondenceTypeCode": "(val)","batchRunDate": "(val)","timeWindowStart": "(val)","timeWindowEnd": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Committee Batch Correspondences [POST /research-sys/api/v1/committee-batch-correspondences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeBatchCorrespondenceId": "(val)","committeeId": "(val)","batchCorrespondenceTypeCode": "(val)","batchRunDate": "(val)","timeWindowStart": "(val)","timeWindowEnd": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"committeeBatchCorrespondenceId": "(val)","committeeId": "(val)","batchCorrespondenceTypeCode": "(val)","batchRunDate": "(val)","timeWindowStart": "(val)","timeWindowEnd": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Batch Correspondences [POST /research-sys/api/v1/committee-batch-correspondences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"committeeBatchCorrespondenceId": "(val)","committeeId": "(val)","batchCorrespondenceTypeCode": "(val)","batchRunDate": "(val)","timeWindowStart": "(val)","timeWindowEnd": "(val)","_primaryKey": "(val)"},
              {"committeeBatchCorrespondenceId": "(val)","committeeId": "(val)","batchCorrespondenceTypeCode": "(val)","batchRunDate": "(val)","timeWindowStart": "(val)","timeWindowEnd": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"committeeBatchCorrespondenceId": "(val)","committeeId": "(val)","batchCorrespondenceTypeCode": "(val)","batchRunDate": "(val)","timeWindowStart": "(val)","timeWindowEnd": "(val)","_primaryKey": "(val)"},
              {"committeeBatchCorrespondenceId": "(val)","committeeId": "(val)","batchCorrespondenceTypeCode": "(val)","batchRunDate": "(val)","timeWindowStart": "(val)","timeWindowEnd": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Committee Batch Correspondences by Key [DELETE /research-sys/api/v1/committee-batch-correspondences/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Batch Correspondences [DELETE /research-sys/api/v1/committee-batch-correspondences/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Batch Correspondences with Matching [DELETE /research-sys/api/v1/committee-batch-correspondences/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + committeeBatchCorrespondenceId
            + committeeId
            + batchCorrespondenceTypeCode
            + batchRunDate
            + timeWindowStart
            + timeWindowEnd

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
