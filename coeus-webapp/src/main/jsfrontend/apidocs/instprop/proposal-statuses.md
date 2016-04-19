## Proposal Statuses [/instprop/api/v1/proposal-statuses/]

### Get Proposal Statuses by Key [GET /instprop/api/v1/proposal-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Proposal Statuses [GET /instprop/api/v1/proposal-statuses/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Statuses with Filtering [GET /instprop/api/v1/proposal-statuses/]
    
+ Parameters

    + proposalStatusCode (optional) - Proposal Status Code. Maximum length is 22.
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
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Statuses [GET /instprop/api/v1/proposal-statuses/]
	                                          
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
    
            {"columns":["proposalStatusCode","description"],"primaryKey":"proposalStatusCode"}
		
### Get Blueprint API specification for Proposal Statuses [GET /instprop/api/v1/proposal-statuses/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Statuses.md"
            transfer-encoding:chunked


### Update Proposal Statuses [PUT /instprop/api/v1/proposal-statuses/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Statuses [PUT /instprop/api/v1/proposal-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Statuses [POST /instprop/api/v1/proposal-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Statuses [POST /instprop/api/v1/proposal-statuses/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"proposalStatusCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Statuses by Key [DELETE /instprop/api/v1/proposal-statuses/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Statuses [DELETE /instprop/api/v1/proposal-statuses/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Statuses with Matching [DELETE /instprop/api/v1/proposal-statuses/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalStatusCode (optional) - Proposal Status Code. Maximum length is 22.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
