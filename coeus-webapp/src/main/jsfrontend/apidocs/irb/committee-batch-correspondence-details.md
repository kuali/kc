## Committee Batch Correspondence Details [/research-sys/api/v1/committee-batch-correspondence-details/]

### Get Committee Batch Correspondence Details by Key [GET /research-sys/api/v1/committee-batch-correspondence-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"committeeBatchCorrespondenceDetailId": "(val)","committeeBatchCorrespondenceId": "(val)","protocolActionId": "(val)","protocolCorrespondenceId": "(val)","_primaryKey": "(val)"}

### Get All Committee Batch Correspondence Details [GET /research-sys/api/v1/committee-batch-correspondence-details/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"committeeBatchCorrespondenceDetailId": "(val)","committeeBatchCorrespondenceId": "(val)","protocolActionId": "(val)","protocolCorrespondenceId": "(val)","_primaryKey": "(val)"},
              {"committeeBatchCorrespondenceDetailId": "(val)","committeeBatchCorrespondenceId": "(val)","protocolActionId": "(val)","protocolCorrespondenceId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Committee Batch Correspondence Details with Filtering [GET /research-sys/api/v1/committee-batch-correspondence-details/]
    
+ Parameters

        + committeeBatchCorrespondenceDetailId
            + committeeBatchCorrespondenceId
            + protocolActionId
            + protocolCorrespondenceId

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"committeeBatchCorrespondenceDetailId": "(val)","committeeBatchCorrespondenceId": "(val)","protocolActionId": "(val)","protocolCorrespondenceId": "(val)","_primaryKey": "(val)"},
              {"committeeBatchCorrespondenceDetailId": "(val)","committeeBatchCorrespondenceId": "(val)","protocolActionId": "(val)","protocolCorrespondenceId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Committee Batch Correspondence Details [GET /research-sys/api/v1/committee-batch-correspondence-details/]
	                                          
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
    
            {"columns":["committeeBatchCorrespondenceDetailId","committeeBatchCorrespondenceId","protocolActionId","protocolCorrespondenceId"],"primaryKey":"committeeBatchCorrespondenceDetailId"}
		
### Get Blueprint API specification for Committee Batch Correspondence Details [GET /research-sys/api/v1/committee-batch-correspondence-details/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committee Batch Correspondence Details.md"
            transfer-encoding:chunked


### Update Committee Batch Correspondence Details [PUT /research-sys/api/v1/committee-batch-correspondence-details/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeBatchCorrespondenceDetailId": "(val)","committeeBatchCorrespondenceId": "(val)","protocolActionId": "(val)","protocolCorrespondenceId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Batch Correspondence Details [PUT /research-sys/api/v1/committee-batch-correspondence-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"committeeBatchCorrespondenceDetailId": "(val)","committeeBatchCorrespondenceId": "(val)","protocolActionId": "(val)","protocolCorrespondenceId": "(val)","_primaryKey": "(val)"},
              {"committeeBatchCorrespondenceDetailId": "(val)","committeeBatchCorrespondenceId": "(val)","protocolActionId": "(val)","protocolCorrespondenceId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Committee Batch Correspondence Details [POST /research-sys/api/v1/committee-batch-correspondence-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeBatchCorrespondenceDetailId": "(val)","committeeBatchCorrespondenceId": "(val)","protocolActionId": "(val)","protocolCorrespondenceId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"committeeBatchCorrespondenceDetailId": "(val)","committeeBatchCorrespondenceId": "(val)","protocolActionId": "(val)","protocolCorrespondenceId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Batch Correspondence Details [POST /research-sys/api/v1/committee-batch-correspondence-details/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"committeeBatchCorrespondenceDetailId": "(val)","committeeBatchCorrespondenceId": "(val)","protocolActionId": "(val)","protocolCorrespondenceId": "(val)","_primaryKey": "(val)"},
              {"committeeBatchCorrespondenceDetailId": "(val)","committeeBatchCorrespondenceId": "(val)","protocolActionId": "(val)","protocolCorrespondenceId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"committeeBatchCorrespondenceDetailId": "(val)","committeeBatchCorrespondenceId": "(val)","protocolActionId": "(val)","protocolCorrespondenceId": "(val)","_primaryKey": "(val)"},
              {"committeeBatchCorrespondenceDetailId": "(val)","committeeBatchCorrespondenceId": "(val)","protocolActionId": "(val)","protocolCorrespondenceId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Committee Batch Correspondence Details by Key [DELETE /research-sys/api/v1/committee-batch-correspondence-details/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Batch Correspondence Details [DELETE /research-sys/api/v1/committee-batch-correspondence-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Batch Correspondence Details with Matching [DELETE /research-sys/api/v1/committee-batch-correspondence-details/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + committeeBatchCorrespondenceDetailId
            + committeeBatchCorrespondenceId
            + protocolActionId
            + protocolCorrespondenceId

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
