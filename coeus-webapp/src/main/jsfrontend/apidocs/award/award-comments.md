## Award Comments [/award/api/v1/award-comments/]

### Get Award Comments by Key [GET /award/api/v1/award-comments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Award Comments [GET /award/api/v1/award-comments/]
	 
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

### Get All Award Comments with Filtering [GET /award/api/v1/award-comments/]
    
+ Parameters

    + awardCommentId (optional) - Award Comment ID. Maximum length is 8.
    + awardId (optional) - Award Id. Maximum length is 12.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + commentTypeCode (optional) - 
    + checklistPrintFlag (optional) - Checklist Print Flag. Maximum length is 1.
    + comments (optional) - Comments. Maximum length is 999999999.

            
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
			
### Get Schema for Award Comments [GET /award/api/v1/award-comments/]
	                                          
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
    
            {"columns":["awardCommentId","awardId","awardNumber","sequenceNumber","commentTypeCode","checklistPrintFlag","comments"],"primaryKey":"awardCommentId"}
		
### Get Blueprint API specification for Award Comments [GET /award/api/v1/award-comments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Comments.md"
            transfer-encoding:chunked


### Update Award Comments [PUT /award/api/v1/award-comments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Comments [PUT /award/api/v1/award-comments/]

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

### Insert Award Comments [POST /award/api/v1/award-comments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardCommentId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Comments [POST /award/api/v1/award-comments/]

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
            
### Delete Award Comments by Key [DELETE /award/api/v1/award-comments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Comments [DELETE /award/api/v1/award-comments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Comments with Matching [DELETE /award/api/v1/award-comments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + awardCommentId (optional) - Award Comment ID. Maximum length is 8.
    + awardId (optional) - Award Id. Maximum length is 12.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + commentTypeCode (optional) - 
    + checklistPrintFlag (optional) - Checklist Print Flag. Maximum length is 1.
    + comments (optional) - Comments. Maximum length is 999999999.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
