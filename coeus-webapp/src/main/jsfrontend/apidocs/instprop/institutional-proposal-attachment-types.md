## Institutional Proposal Attachment Types [/instprop/api/v1/institutional-proposal-attachment-types/]

### Get Institutional Proposal Attachment Types by Key [GET /instprop/api/v1/institutional-proposal-attachment-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Attachment Types [GET /instprop/api/v1/institutional-proposal-attachment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"},
              {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"}
            ]

### Get All Institutional Proposal Attachment Types with Filtering [GET /instprop/api/v1/institutional-proposal-attachment-types/]
    
+ Parameters

    + attachmentTypeCode (optional) - Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + allowMultiple (optional) - allowMultiple. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"},
              {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Attachment Types [GET /instprop/api/v1/institutional-proposal-attachment-types/]
	                                          
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
    
            {"columns":["attachmentTypeCode","description","allowMultiple"],"primaryKey":"attachmentTypeCode"}
		
### Get Blueprint API specification for Institutional Proposal Attachment Types [GET /instprop/api/v1/institutional-proposal-attachment-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Attachment Types.md"
            transfer-encoding:chunked


### Update Institutional Proposal Attachment Types [PUT /instprop/api/v1/institutional-proposal-attachment-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposal Attachment Types [PUT /instprop/api/v1/institutional-proposal-attachment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"},
              {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Institutional Proposal Attachment Types [POST /instprop/api/v1/institutional-proposal-attachment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposal Attachment Types [POST /instprop/api/v1/institutional-proposal-attachment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"},
              {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"},
              {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Institutional Proposal Attachment Types by Key [DELETE /instprop/api/v1/institutional-proposal-attachment-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Attachment Types [DELETE /instprop/api/v1/institutional-proposal-attachment-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Attachment Types with Matching [DELETE /instprop/api/v1/institutional-proposal-attachment-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + attachmentTypeCode (optional) - Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + allowMultiple (optional) - allowMultiple. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
