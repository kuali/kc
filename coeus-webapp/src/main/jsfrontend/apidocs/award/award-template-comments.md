## Award Template Comments [/award/api/v1/award-template-comments/]

### Get Award Template Comments by Key [GET /award/api/v1/award-template-comments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"templateCommentsId": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","template.templateCode": "(val)","_primaryKey": "(val)"}

### Get All Award Template Comments [GET /award/api/v1/award-template-comments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"templateCommentsId": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","template.templateCode": "(val)","_primaryKey": "(val)"},
              {"templateCommentsId": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","template.templateCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Template Comments with Filtering [GET /award/api/v1/award-template-comments/]
    
+ Parameters

    + templateCommentsId (optional) - Template Comments Id. Maximum length is 22.
    + commentTypeCode (optional) - Comment Type. Maximum length is 3.
    + checklistPrintFlag (optional) - Checklist Print Flag. Maximum length is 1.
    + comments (optional) - Comments. Maximum length is 2000.
    + template.templateCode (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"templateCommentsId": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","template.templateCode": "(val)","_primaryKey": "(val)"},
              {"templateCommentsId": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","template.templateCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Template Comments [GET /award/api/v1/award-template-comments/]
	                                          
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
    
            {"columns":["templateCommentsId","commentTypeCode","checklistPrintFlag","comments","template.templateCode"],"primaryKey":"templateCommentsId"}
		
### Get Blueprint API specification for Award Template Comments [GET /award/api/v1/award-template-comments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Template Comments.md"
            transfer-encoding:chunked
### Update Award Template Comments [PUT /award/api/v1/award-template-comments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"templateCommentsId": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","template.templateCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Template Comments [PUT /award/api/v1/award-template-comments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"templateCommentsId": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","template.templateCode": "(val)","_primaryKey": "(val)"},
              {"templateCommentsId": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","template.templateCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Award Template Comments [POST /award/api/v1/award-template-comments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"templateCommentsId": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","template.templateCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"templateCommentsId": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","template.templateCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Template Comments [POST /award/api/v1/award-template-comments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"templateCommentsId": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","template.templateCode": "(val)","_primaryKey": "(val)"},
              {"templateCommentsId": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","template.templateCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"templateCommentsId": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","template.templateCode": "(val)","_primaryKey": "(val)"},
              {"templateCommentsId": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","template.templateCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Award Template Comments by Key [DELETE /award/api/v1/award-template-comments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Template Comments [DELETE /award/api/v1/award-template-comments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Template Comments with Matching [DELETE /award/api/v1/award-template-comments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + templateCommentsId (optional) - Template Comments Id. Maximum length is 22.
    + commentTypeCode (optional) - Comment Type. Maximum length is 3.
    + checklistPrintFlag (optional) - Checklist Print Flag. Maximum length is 1.
    + comments (optional) - Comments. Maximum length is 2000.
    + template.templateCode (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
