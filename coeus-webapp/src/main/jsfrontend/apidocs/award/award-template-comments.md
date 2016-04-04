## Award Template Comments [/research-sys/api/v1/award-template-comments/]

### Get Award Template Comments by Key [GET /research-sys/api/v1/award-template-comments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"templateCommentsId": "(val)","templateCode": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Award Template Comments [GET /research-sys/api/v1/award-template-comments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"templateCommentsId": "(val)","templateCode": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"templateCommentsId": "(val)","templateCode": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Template Comments with Filtering [GET /research-sys/api/v1/award-template-comments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + templateCommentsId
            + templateCode
            + commentTypeCode
            + checklistPrintFlag
            + comments
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"templateCommentsId": "(val)","templateCode": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"templateCommentsId": "(val)","templateCode": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Template Comments [GET /research-sys/api/v1/award-template-comments/]
	 
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
		
### Get Blueprint API specification for Award Template Comments [GET /research-sys/api/v1/award-template-comments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Template Comments.md"
            transfer-encoding:chunked


### Update Award Template Comments [PUT /research-sys/api/v1/award-template-comments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"templateCommentsId": "(val)","templateCode": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Template Comments [PUT /research-sys/api/v1/award-template-comments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"templateCommentsId": "(val)","templateCode": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"templateCommentsId": "(val)","templateCode": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Template Comments [POST /research-sys/api/v1/award-template-comments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"templateCommentsId": "(val)","templateCode": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"templateCommentsId": "(val)","templateCode": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Template Comments [POST /research-sys/api/v1/award-template-comments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"templateCommentsId": "(val)","templateCode": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"templateCommentsId": "(val)","templateCode": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"templateCommentsId": "(val)","templateCode": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"templateCommentsId": "(val)","templateCode": "(val)","commentTypeCode": "(val)","checklistPrintFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Template Comments by Key [DELETE /research-sys/api/v1/award-template-comments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Template Comments [DELETE /research-sys/api/v1/award-template-comments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Template Comments with Matching [DELETE /research-sys/api/v1/award-template-comments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + templateCommentsId
            + templateCode
            + commentTypeCode
            + checklistPrintFlag
            + comments


+ Response 204
