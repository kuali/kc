## Institutional Proposal Comments [/instprop/api/v1/institutional-proposal-comments/]

### Get Institutional Proposal Comments by Key [GET /instprop/api/v1/institutional-proposal-comments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Comments [GET /instprop/api/v1/institutional-proposal-comments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Comments with Filtering [GET /instprop/api/v1/institutional-proposal-comments/]
    
+ Parameters

    + proposalCommentsId (optional) - Proposal Comments Id. Maximum length is 22.
    + proposalId (optional) - Proposal Id. Maximum length is 22.
    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + commentTypeCode (optional) - Comment Type Code. Maximum length is 22.
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
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Comments [GET /instprop/api/v1/institutional-proposal-comments/]
	                                          
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
    
            {"columns":["proposalCommentsId","proposalId","proposalNumber","sequenceNumber","commentTypeCode","comments"],"primaryKey":"proposalCommentsId"}
		
### Get Blueprint API specification for Institutional Proposal Comments [GET /instprop/api/v1/institutional-proposal-comments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Comments.md"
            transfer-encoding:chunked
### Update Institutional Proposal Comments [PUT /instprop/api/v1/institutional-proposal-comments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposal Comments [PUT /instprop/api/v1/institutional-proposal-comments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Institutional Proposal Comments [POST /instprop/api/v1/institutional-proposal-comments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposal Comments [POST /instprop/api/v1/institutional-proposal-comments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
### Delete Institutional Proposal Comments by Key [DELETE /instprop/api/v1/institutional-proposal-comments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Comments [DELETE /instprop/api/v1/institutional-proposal-comments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Comments with Matching [DELETE /instprop/api/v1/institutional-proposal-comments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalCommentsId (optional) - Proposal Comments Id. Maximum length is 22.
    + proposalId (optional) - Proposal Id. Maximum length is 22.
    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + commentTypeCode (optional) - Comment Type Code. Maximum length is 22.
    + comments (optional) - Comments. Maximum length is 4000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
