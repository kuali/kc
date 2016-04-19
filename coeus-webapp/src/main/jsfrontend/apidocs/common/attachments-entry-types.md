## Attachments Entry Types [/research-common/api/v1/attachments-entry-types/]

### Get Attachments Entry Types by Key [GET /research-common/api/v1/attachments-entry-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"attachmentsTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Attachments Entry Types [GET /research-common/api/v1/attachments-entry-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"attachmentsTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"attachmentsTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Attachments Entry Types with Filtering [GET /research-common/api/v1/attachments-entry-types/]
    
+ Parameters

    + attachmentsTypeCode (optional) - Attachments Type Code. Maximum length is 3.
    + sortId (optional) - Sort Id. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"attachmentsTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"attachmentsTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Attachments Entry Types [GET /research-common/api/v1/attachments-entry-types/]
	                                          
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
    
            {"columns":["attachmentsTypeCode","sortId","description"],"primaryKey":"attachmentsTypeCode"}
		
### Get Blueprint API specification for Attachments Entry Types [GET /research-common/api/v1/attachments-entry-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Attachments Entry Types.md"
            transfer-encoding:chunked


### Update Attachments Entry Types [PUT /research-common/api/v1/attachments-entry-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"attachmentsTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Attachments Entry Types [PUT /research-common/api/v1/attachments-entry-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"attachmentsTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"attachmentsTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Attachments Entry Types [POST /research-common/api/v1/attachments-entry-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"attachmentsTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"attachmentsTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Attachments Entry Types [POST /research-common/api/v1/attachments-entry-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"attachmentsTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"attachmentsTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"attachmentsTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"attachmentsTypeCode": "(val)","sortId": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Attachments Entry Types by Key [DELETE /research-common/api/v1/attachments-entry-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Attachments Entry Types [DELETE /research-common/api/v1/attachments-entry-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Attachments Entry Types with Matching [DELETE /research-common/api/v1/attachments-entry-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + attachmentsTypeCode (optional) - Attachments Type Code. Maximum length is 3.
    + sortId (optional) - Sort Id. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
