## Proposal Log Types [/instprop/api/v1/proposal-log-types/]

### Get Proposal Log Types by Key [GET /instprop/api/v1/proposal-log-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Proposal Log Types [GET /instprop/api/v1/proposal-log-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Log Types with Filtering [GET /instprop/api/v1/proposal-log-types/]
    
+ Parameters

    + proposalLogTypeCode (optional) - Proposal Log Type Code. Maximum length is 3.
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
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Log Types [GET /instprop/api/v1/proposal-log-types/]
	                                          
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
    
            {"columns":["proposalLogTypeCode","description"],"primaryKey":"proposalLogTypeCode"}
		
### Get Blueprint API specification for Proposal Log Types [GET /instprop/api/v1/proposal-log-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Log Types.md"
            transfer-encoding:chunked
### Update Proposal Log Types [PUT /instprop/api/v1/proposal-log-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Log Types [PUT /instprop/api/v1/proposal-log-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Proposal Log Types [POST /instprop/api/v1/proposal-log-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Log Types [POST /instprop/api/v1/proposal-log-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalLogTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Proposal Log Types by Key [DELETE /instprop/api/v1/proposal-log-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Log Types [DELETE /instprop/api/v1/proposal-log-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Log Types with Matching [DELETE /instprop/api/v1/proposal-log-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalLogTypeCode (optional) - Proposal Log Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
