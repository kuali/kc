## Award Comments [/research-sys/api/v1/award-comments/]

### Get Award Comments by Key [GET /research-sys/api/v1/award-comments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Award Comments [GET /research-sys/api/v1/award-comments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Comments with Filtering [GET /research-sys/api/v1/award-comments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardCommentId
            + awardId
            + awardNumber
            + sequenceNumber
            + commentTypeCode
            + checklistPrintFlag
            + comments
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Comments [GET /research-sys/api/v1/award-comments/]
	 
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
		
### Get Blueprint API specification for Award Comments [GET /research-sys/api/v1/award-comments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Comments.md"
            transfer-encoding:chunked


### Update Award Comments [PUT /research-sys/api/v1/award-comments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Comments [PUT /research-sys/api/v1/award-comments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Comments [POST /research-sys/api/v1/award-comments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Comments [POST /research-sys/api/v1/award-comments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Comments by Key [DELETE /research-sys/api/v1/award-comments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Comments [DELETE /research-sys/api/v1/award-comments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Comments with Matching [DELETE /research-sys/api/v1/award-comments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardCommentId
            + awardId
            + awardNumber
            + sequenceNumber
            + commentTypeCode
            + checklistPrintFlag
            + comments


+ Response 204
