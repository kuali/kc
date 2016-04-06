## Award Notice Printouts [/research-sys/api/v1/award-notice-printouts/]

### Get Award Notice Printouts by Key [GET /research-sys/api/v1/award-notice-printouts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"}

### Get All Award Notice Printouts [GET /research-sys/api/v1/award-notice-printouts/]
	 
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

### Get All Award Notice Printouts with Filtering [GET /research-sys/api/v1/award-notice-printouts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardNoticeId
            + awardId
            + awardNumber
            + unitNumber
            + pdfContent
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"},
              {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Notice Printouts [GET /research-sys/api/v1/award-notice-printouts/]
	 
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
		
### Get Blueprint API specification for Award Notice Printouts [GET /research-sys/api/v1/award-notice-printouts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Notice Printouts.md"
            transfer-encoding:chunked


### Update Award Notice Printouts [PUT /research-sys/api/v1/award-notice-printouts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Notice Printouts [PUT /research-sys/api/v1/award-notice-printouts/]

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

### Insert Award Notice Printouts [POST /research-sys/api/v1/award-notice-printouts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardNoticeId": "(val)","awardId": "(val)","awardNumber": "(val)","unitNumber": "(val)","pdfContent": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Notice Printouts [POST /research-sys/api/v1/award-notice-printouts/]

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
            
### Delete Award Notice Printouts by Key [DELETE /research-sys/api/v1/award-notice-printouts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Notice Printouts [DELETE /research-sys/api/v1/award-notice-printouts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Notice Printouts with Matching [DELETE /research-sys/api/v1/award-notice-printouts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardNoticeId
            + awardId
            + awardNumber
            + unitNumber
            + pdfContent


+ Response 204
