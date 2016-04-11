## Protocol Submission Statuses [/research-sys/api/v1/protocol-submission-statuses/]

### Get Protocol Submission Statuses by Key [GET /research-sys/api/v1/protocol-submission-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Protocol Submission Statuses [GET /research-sys/api/v1/protocol-submission-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Submission Statuses with Filtering [GET /research-sys/api/v1/protocol-submission-statuses/]
    
+ Parameters

        + protocolSubmissionStatusCode
            + description

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Submission Statuses [GET /research-sys/api/v1/protocol-submission-statuses/]
	                                          
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
    
            {"columns":["protocolSubmissionStatusCode","description"],"primaryKey":"protocolSubmissionStatusCode"}
		
### Get Blueprint API specification for Protocol Submission Statuses [GET /research-sys/api/v1/protocol-submission-statuses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Submission Statuses.md"
            transfer-encoding:chunked


### Update Protocol Submission Statuses [PUT /research-sys/api/v1/protocol-submission-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Submission Statuses [PUT /research-sys/api/v1/protocol-submission-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Submission Statuses [POST /research-sys/api/v1/protocol-submission-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Submission Statuses [POST /research-sys/api/v1/protocol-submission-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"protocolSubmissionStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Submission Statuses by Key [DELETE /research-sys/api/v1/protocol-submission-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Submission Statuses [DELETE /research-sys/api/v1/protocol-submission-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Submission Statuses with Matching [DELETE /research-sys/api/v1/protocol-submission-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + protocolSubmissionStatusCode
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
