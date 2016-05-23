## Proposal Abstracts [/propdev/api/v1/proposal-abstracts/]

### Get Proposal Abstracts by Key [GET /propdev/api/v1/proposal-abstracts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}

### Get All Proposal Abstracts [GET /propdev/api/v1/proposal-abstracts/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Abstracts with Filtering [GET /propdev/api/v1/proposal-abstracts/]
    
+ Parameters

    + proposalNumber (optional) - Proposal Number. Maximum length is 12.
    + abstractTypeCode (optional) - Abstract Type. Maximum length is 3.
    + abstractDetails (optional) - Abstract Details. Maximum length is 49000.
    + timestampDisplay (optional) - Timestamp Display.
    + uploadUserDisplay (optional) - Upload User Display.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Abstracts [GET /propdev/api/v1/proposal-abstracts/]
	                                          
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
    
            {"columns":["proposalNumber","abstractTypeCode","abstractDetails","timestampDisplay","uploadUserDisplay"],"primaryKey":"abstractTypeCode:proposalNumber"}
		
### Get Blueprint API specification for Proposal Abstracts [GET /propdev/api/v1/proposal-abstracts/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Abstracts.md"
            transfer-encoding:chunked
### Update Proposal Abstracts [PUT /propdev/api/v1/proposal-abstracts/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Abstracts [PUT /propdev/api/v1/proposal-abstracts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Proposal Abstracts [POST /propdev/api/v1/proposal-abstracts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Abstracts [POST /propdev/api/v1/proposal-abstracts/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"},
              {"proposalNumber": "(val)","abstractTypeCode": "(val)","abstractDetails": "(val)","timestampDisplay": "(val)","uploadUserDisplay": "(val)","_primaryKey": "(val)"}
            ]
### Delete Proposal Abstracts by Key [DELETE /propdev/api/v1/proposal-abstracts/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Abstracts [DELETE /propdev/api/v1/proposal-abstracts/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Abstracts with Matching [DELETE /propdev/api/v1/proposal-abstracts/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + proposalNumber (optional) - Proposal Number. Maximum length is 12.
    + abstractTypeCode (optional) - Abstract Type. Maximum length is 3.
    + abstractDetails (optional) - Abstract Details. Maximum length is 49000.
    + timestampDisplay (optional) - Timestamp Display.
    + uploadUserDisplay (optional) - Upload User Display.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
