## Institutional Proposal Attachment Types [/research-sys/api/v1/institutional-proposal-attachment-types/]

### Get Institutional Proposal Attachment Types by Key [GET /research-sys/api/v1/institutional-proposal-attachment-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"}

### Get All Institutional Proposal Attachment Types [GET /research-sys/api/v1/institutional-proposal-attachment-types/]
	 
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

### Get All Institutional Proposal Attachment Types with Filtering [GET /research-sys/api/v1/institutional-proposal-attachment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + attachmentTypeCode
            + description
            + allowMultiple
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"},
              {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Institutional Proposal Attachment Types [GET /research-sys/api/v1/institutional-proposal-attachment-types/]
	 
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
		
### Get Blueprint API specification for Institutional Proposal Attachment Types [GET /research-sys/api/v1/institutional-proposal-attachment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Institutional Proposal Attachment Types.md"
            transfer-encoding:chunked


### Update Institutional Proposal Attachment Types [PUT /research-sys/api/v1/institutional-proposal-attachment-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Institutional Proposal Attachment Types [PUT /research-sys/api/v1/institutional-proposal-attachment-types/]

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

### Insert Institutional Proposal Attachment Types [POST /research-sys/api/v1/institutional-proposal-attachment-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"attachmentTypeCode": "(val)","description": "(val)","allowMultiple": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Institutional Proposal Attachment Types [POST /research-sys/api/v1/institutional-proposal-attachment-types/]

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
            
### Delete Institutional Proposal Attachment Types by Key [DELETE /research-sys/api/v1/institutional-proposal-attachment-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Institutional Proposal Attachment Types [DELETE /research-sys/api/v1/institutional-proposal-attachment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Institutional Proposal Attachment Types with Matching [DELETE /research-sys/api/v1/institutional-proposal-attachment-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + attachmentTypeCode
            + description
            + allowMultiple


+ Response 204
