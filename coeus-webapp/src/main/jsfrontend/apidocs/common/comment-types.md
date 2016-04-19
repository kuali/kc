## Comment Types [/research-common/api/v1/comment-types/]

### Get Comment Types by Key [GET /research-common/api/v1/comment-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"commentTypeCode": "(val)","description": "(val)","templateFlag": "(val)","checklistFlag": "(val)","awardCommentScreenFlag": "(val)","_primaryKey": "(val)"}

### Get All Comment Types [GET /research-common/api/v1/comment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"commentTypeCode": "(val)","description": "(val)","templateFlag": "(val)","checklistFlag": "(val)","awardCommentScreenFlag": "(val)","_primaryKey": "(val)"},
              {"commentTypeCode": "(val)","description": "(val)","templateFlag": "(val)","checklistFlag": "(val)","awardCommentScreenFlag": "(val)","_primaryKey": "(val)"}
            ]

### Get All Comment Types with Filtering [GET /research-common/api/v1/comment-types/]
    
+ Parameters

    + commentTypeCode (optional) - Comment Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + templateFlag (optional) - Template Flag. Maximum length is 1.
    + checklistFlag (optional) - Checklist Flag. Maximum length is 1.
    + awardCommentScreenFlag (optional) - Award Comment Screen Flag. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"commentTypeCode": "(val)","description": "(val)","templateFlag": "(val)","checklistFlag": "(val)","awardCommentScreenFlag": "(val)","_primaryKey": "(val)"},
              {"commentTypeCode": "(val)","description": "(val)","templateFlag": "(val)","checklistFlag": "(val)","awardCommentScreenFlag": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Comment Types [GET /research-common/api/v1/comment-types/]
	                                          
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
    
            {"columns":["commentTypeCode","description","templateFlag","checklistFlag","awardCommentScreenFlag"],"primaryKey":"commentTypeCode"}
		
### Get Blueprint API specification for Comment Types [GET /research-common/api/v1/comment-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Comment Types.md"
            transfer-encoding:chunked


### Update Comment Types [PUT /research-common/api/v1/comment-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commentTypeCode": "(val)","description": "(val)","templateFlag": "(val)","checklistFlag": "(val)","awardCommentScreenFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Comment Types [PUT /research-common/api/v1/comment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"commentTypeCode": "(val)","description": "(val)","templateFlag": "(val)","checklistFlag": "(val)","awardCommentScreenFlag": "(val)","_primaryKey": "(val)"},
              {"commentTypeCode": "(val)","description": "(val)","templateFlag": "(val)","checklistFlag": "(val)","awardCommentScreenFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Comment Types [POST /research-common/api/v1/comment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"commentTypeCode": "(val)","description": "(val)","templateFlag": "(val)","checklistFlag": "(val)","awardCommentScreenFlag": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"commentTypeCode": "(val)","description": "(val)","templateFlag": "(val)","checklistFlag": "(val)","awardCommentScreenFlag": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Comment Types [POST /research-common/api/v1/comment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"commentTypeCode": "(val)","description": "(val)","templateFlag": "(val)","checklistFlag": "(val)","awardCommentScreenFlag": "(val)","_primaryKey": "(val)"},
              {"commentTypeCode": "(val)","description": "(val)","templateFlag": "(val)","checklistFlag": "(val)","awardCommentScreenFlag": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"commentTypeCode": "(val)","description": "(val)","templateFlag": "(val)","checklistFlag": "(val)","awardCommentScreenFlag": "(val)","_primaryKey": "(val)"},
              {"commentTypeCode": "(val)","description": "(val)","templateFlag": "(val)","checklistFlag": "(val)","awardCommentScreenFlag": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Comment Types by Key [DELETE /research-common/api/v1/comment-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Comment Types [DELETE /research-common/api/v1/comment-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Comment Types with Matching [DELETE /research-common/api/v1/comment-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + commentTypeCode (optional) - Comment Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + templateFlag (optional) - Template Flag. Maximum length is 1.
    + checklistFlag (optional) - Checklist Flag. Maximum length is 1.
    + awardCommentScreenFlag (optional) - Award Comment Screen Flag. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
