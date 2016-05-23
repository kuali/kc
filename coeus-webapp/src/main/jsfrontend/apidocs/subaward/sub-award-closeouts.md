## Sub Award Closeouts [/subaward/api/v1/sub-award-closeouts/]

### Get Sub Award Closeouts by Key [GET /subaward/api/v1/sub-award-closeouts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardCloseoutId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","closeoutNumber": "(val)","closeoutTypeCode": "(val)","dateRequested": "(val)","dateFollowup": "(val)","dateReceived": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Closeouts [GET /subaward/api/v1/sub-award-closeouts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardCloseoutId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","closeoutNumber": "(val)","closeoutTypeCode": "(val)","dateRequested": "(val)","dateFollowup": "(val)","dateReceived": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"subAwardCloseoutId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","closeoutNumber": "(val)","closeoutTypeCode": "(val)","dateRequested": "(val)","dateFollowup": "(val)","dateReceived": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Closeouts with Filtering [GET /subaward/api/v1/sub-award-closeouts/]
    
+ Parameters

    + subAwardCloseoutId (optional) - Subaward Closeout Id. Maximum length is 22.
    + subAwardId (optional) - Subaward Id. Maximum length is 22.
    + sequenceNumber (optional) - 
    + subAwardCode (optional) - 
    + closeoutNumber (optional) - Closeout Number. Maximum length is 22.
    + closeoutTypeCode (optional) - Closeout Type Code. Maximum length is 22.
    + dateRequested (optional) - Date Requested. Maximum length is 10.
    + dateFollowup (optional) - Date Followup. Maximum length is 10.
    + dateReceived (optional) - Date Received. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 4000.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardCloseoutId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","closeoutNumber": "(val)","closeoutTypeCode": "(val)","dateRequested": "(val)","dateFollowup": "(val)","dateReceived": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"subAwardCloseoutId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","closeoutNumber": "(val)","closeoutTypeCode": "(val)","dateRequested": "(val)","dateFollowup": "(val)","dateReceived": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Closeouts [GET /subaward/api/v1/sub-award-closeouts/]
	                                          
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
    
            {"columns":["subAwardCloseoutId","subAwardId","sequenceNumber","subAwardCode","closeoutNumber","closeoutTypeCode","dateRequested","dateFollowup","dateReceived","comments"],"primaryKey":"subAwardCloseoutId"}
		
### Get Blueprint API specification for Sub Award Closeouts [GET /subaward/api/v1/sub-award-closeouts/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Closeouts.md"
            transfer-encoding:chunked
### Update Sub Award Closeouts [PUT /subaward/api/v1/sub-award-closeouts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardCloseoutId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","closeoutNumber": "(val)","closeoutTypeCode": "(val)","dateRequested": "(val)","dateFollowup": "(val)","dateReceived": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Closeouts [PUT /subaward/api/v1/sub-award-closeouts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardCloseoutId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","closeoutNumber": "(val)","closeoutTypeCode": "(val)","dateRequested": "(val)","dateFollowup": "(val)","dateReceived": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"subAwardCloseoutId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","closeoutNumber": "(val)","closeoutTypeCode": "(val)","dateRequested": "(val)","dateFollowup": "(val)","dateReceived": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Sub Award Closeouts [POST /subaward/api/v1/sub-award-closeouts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardCloseoutId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","closeoutNumber": "(val)","closeoutTypeCode": "(val)","dateRequested": "(val)","dateFollowup": "(val)","dateReceived": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardCloseoutId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","closeoutNumber": "(val)","closeoutTypeCode": "(val)","dateRequested": "(val)","dateFollowup": "(val)","dateReceived": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Closeouts [POST /subaward/api/v1/sub-award-closeouts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardCloseoutId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","closeoutNumber": "(val)","closeoutTypeCode": "(val)","dateRequested": "(val)","dateFollowup": "(val)","dateReceived": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"subAwardCloseoutId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","closeoutNumber": "(val)","closeoutTypeCode": "(val)","dateRequested": "(val)","dateFollowup": "(val)","dateReceived": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"subAwardCloseoutId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","closeoutNumber": "(val)","closeoutTypeCode": "(val)","dateRequested": "(val)","dateFollowup": "(val)","dateReceived": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"subAwardCloseoutId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","closeoutNumber": "(val)","closeoutTypeCode": "(val)","dateRequested": "(val)","dateFollowup": "(val)","dateReceived": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
### Delete Sub Award Closeouts by Key [DELETE /subaward/api/v1/sub-award-closeouts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Closeouts [DELETE /subaward/api/v1/sub-award-closeouts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Closeouts with Matching [DELETE /subaward/api/v1/sub-award-closeouts/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + subAwardCloseoutId (optional) - Subaward Closeout Id. Maximum length is 22.
    + subAwardId (optional) - Subaward Id. Maximum length is 22.
    + sequenceNumber (optional) - 
    + subAwardCode (optional) - 
    + closeoutNumber (optional) - Closeout Number. Maximum length is 22.
    + closeoutTypeCode (optional) - Closeout Type Code. Maximum length is 22.
    + dateRequested (optional) - Date Requested. Maximum length is 10.
    + dateFollowup (optional) - Date Followup. Maximum length is 10.
    + dateReceived (optional) - Date Received. Maximum length is 10.
    + comments (optional) - Comments. Maximum length is 4000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
