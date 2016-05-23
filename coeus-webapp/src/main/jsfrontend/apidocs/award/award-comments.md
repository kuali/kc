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
    
            {"awardCommentId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}

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
              {"awardCommentId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardCommentId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Comments with Filtering [GET /award/api/v1/award-comments/]
    
+ Parameters

    + awardCommentId (optional) - Award Comment ID. Maximum length is 8.
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
              {"awardCommentId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"awardCommentId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
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
    
            {"columns":["awardCommentId","awardNumber","sequenceNumber","commentTypeCode","checklistPrintFlag","comments"],"primaryKey":"awardCommentId"}
		
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
