## Proposal Comments [/instprop/api/v1/proposal-comments/]

### Get Proposal Comments by Key [GET /instprop/api/v1/proposal-comments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Proposal Comments [GET /instprop/api/v1/proposal-comments/]
	 
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

### Get All Proposal Comments with Filtering [GET /instprop/api/v1/proposal-comments/]
    
+ Parameters

    + proposalCommentsId (optional) - 
    + proposalId (optional) - 
    + proposalNumber (optional) - 
    + sequenceNumber (optional) - 
    + commentTypeCode (optional) - 
    + comments (optional) - 

            
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
			
### Get Schema for Proposal Comments [GET /instprop/api/v1/proposal-comments/]
	                                          
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
		
### Get Blueprint API specification for Proposal Comments [GET /instprop/api/v1/proposal-comments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Comments.md"
            transfer-encoding:chunked


### Update Proposal Comments [PUT /instprop/api/v1/proposal-comments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Comments [PUT /instprop/api/v1/proposal-comments/]

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

### Insert Proposal Comments [POST /instprop/api/v1/proposal-comments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalCommentsId": "(val)","proposalId": "(val)","proposalNumber": "(val)","sequenceNumber": "(val)","commentTypeCode": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Comments [POST /instprop/api/v1/proposal-comments/]

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
            
### Delete Proposal Comments by Key [DELETE /instprop/api/v1/proposal-comments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Comments [DELETE /instprop/api/v1/proposal-comments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Comments with Matching [DELETE /instprop/api/v1/proposal-comments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalCommentsId (optional) - 
    + proposalId (optional) - 
    + proposalNumber (optional) - 
    + sequenceNumber (optional) - 
    + commentTypeCode (optional) - 
    + comments (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
