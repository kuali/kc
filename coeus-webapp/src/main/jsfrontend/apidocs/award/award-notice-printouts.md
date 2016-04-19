## Award Notice Printouts [/award/api/v1/award-notice-printouts/]

### Get Award Notice Printouts by Key [GET /award/api/v1/award-notice-printouts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"}

### Get All Award Notice Printouts [GET /award/api/v1/award-notice-printouts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"},
              {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Notice Printouts with Filtering [GET /award/api/v1/award-notice-printouts/]
    
+ Parameters

    + awardNoticeId (optional) - 
    + awardId (optional) - 
    + awardNumber (optional) - 
    + unitNumber (optional) - 
    + pdfContent (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"},
              {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Notice Printouts [GET /award/api/v1/award-notice-printouts/]
	                                          
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
    
            {"columns":["awardNoticeId","awardId","awardNumber","unitNumber","pdfContent"],"primaryKey":"awardNoticeId"}
		
### Get Blueprint API specification for Award Notice Printouts [GET /award/api/v1/award-notice-printouts/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Notice Printouts.md"
            transfer-encoding:chunked


### Update Award Notice Printouts [PUT /award/api/v1/award-notice-printouts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Notice Printouts [PUT /award/api/v1/award-notice-printouts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"},
              {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Notice Printouts [POST /award/api/v1/award-notice-printouts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Notice Printouts [POST /award/api/v1/award-notice-printouts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"},
              {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"},
              {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Notice Printouts by Key [DELETE /award/api/v1/award-notice-printouts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Notice Printouts [DELETE /award/api/v1/award-notice-printouts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Notice Printouts with Matching [DELETE /award/api/v1/award-notice-printouts/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardNoticeId (optional) - 
    + awardId (optional) - 
    + awardNumber (optional) - 
    + unitNumber (optional) - 
    + pdfContent (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
