## Award Closeouts [/research-sys/api/v1/award-closeouts/]

### Get Award Closeouts by Key [GET /research-sys/api/v1/award-closeouts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardCloseoutId": "(val)","awardId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","_primaryKey": "(val)"}

### Get All Award Closeouts [GET /research-sys/api/v1/award-closeouts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardCloseoutId": "(val)","awardId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","_primaryKey": "(val)"},
              {"awardCloseoutId": "(val)","awardId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Closeouts with Filtering [GET /research-sys/api/v1/award-closeouts/]
    
+ Parameters

        + awardCloseoutId
            + awardId
            + closeoutReportCode
            + closeoutReportName
            + awardNumber
            + sequenceNumber
            + finalSubmissionDate
            + dueDate
            + multiple

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardCloseoutId": "(val)","awardId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","_primaryKey": "(val)"},
              {"awardCloseoutId": "(val)","awardId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Closeouts [GET /research-sys/api/v1/award-closeouts/]
	                                          
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
    
            {"columns":["awardCloseoutId","awardId","closeoutReportCode","closeoutReportName","awardNumber","sequenceNumber","finalSubmissionDate","dueDate","multiple"],"primaryKey":"awardCloseoutId"}
		
### Get Blueprint API specification for Award Closeouts [GET /research-sys/api/v1/award-closeouts/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Closeouts.md"
            transfer-encoding:chunked


### Update Award Closeouts [PUT /research-sys/api/v1/award-closeouts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardCloseoutId": "(val)","awardId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Closeouts [PUT /research-sys/api/v1/award-closeouts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardCloseoutId": "(val)","awardId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","_primaryKey": "(val)"},
              {"awardCloseoutId": "(val)","awardId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Closeouts [POST /research-sys/api/v1/award-closeouts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardCloseoutId": "(val)","awardId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardCloseoutId": "(val)","awardId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Closeouts [POST /research-sys/api/v1/award-closeouts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardCloseoutId": "(val)","awardId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","_primaryKey": "(val)"},
              {"awardCloseoutId": "(val)","awardId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardCloseoutId": "(val)","awardId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","_primaryKey": "(val)"},
              {"awardCloseoutId": "(val)","awardId": "(val)","closeoutReportCode": "(val)","closeoutReportName": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","finalSubmissionDate": "(val)","dueDate": "(val)","multiple": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Closeouts by Key [DELETE /research-sys/api/v1/award-closeouts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Closeouts [DELETE /research-sys/api/v1/award-closeouts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Closeouts with Matching [DELETE /research-sys/api/v1/award-closeouts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + awardCloseoutId
            + awardId
            + closeoutReportCode
            + closeoutReportName
            + awardNumber
            + sequenceNumber
            + finalSubmissionDate
            + dueDate
            + multiple

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
