## Proposal Changed Data [/propdev/api/v1/proposal-changed-data/]

### Get Proposal Changed Data by Key [GET /propdev/api/v1/proposal-changed-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}

### Get All Proposal Changed Data [GET /propdev/api/v1/proposal-changed-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"},
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Changed Data with Filtering [GET /propdev/api/v1/proposal-changed-data/]
    
+ Parameters

    + changeNumber (optional) - Change Number. Maximum length is 3.
    + columnName (optional) - Field. Maximum length is 30.
    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + changedValue (optional) - Changed Value. Maximum length is 200.
    + comments (optional) - Comments. Maximum length is 300.
    + displayValue (optional) - Display Value. Maximum length is 200.
    + oldDisplayValue (optional) - Old Display Value. Maximum length is 200.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"},
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Changed Data [GET /propdev/api/v1/proposal-changed-data/]
	                                          
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
    
            {"columns":["changeNumber","columnName","proposalNumber","changedValue","comments","displayValue","oldDisplayValue"],"primaryKey":"changeNumber:columnName:proposalNumber"}
		
### Get Blueprint API specification for Proposal Changed Data [GET /propdev/api/v1/proposal-changed-data/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Changed Data.md"
            transfer-encoding:chunked
### Update Proposal Changed Data [PUT /propdev/api/v1/proposal-changed-data/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Changed Data [PUT /propdev/api/v1/proposal-changed-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"},
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Proposal Changed Data [POST /propdev/api/v1/proposal-changed-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Changed Data [POST /propdev/api/v1/proposal-changed-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"},
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"},
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
            ]
### Delete Proposal Changed Data by Key [DELETE /propdev/api/v1/proposal-changed-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Changed Data [DELETE /propdev/api/v1/proposal-changed-data/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Changed Data with Matching [DELETE /propdev/api/v1/proposal-changed-data/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + changeNumber (optional) - Change Number. Maximum length is 3.
    + columnName (optional) - Field. Maximum length is 30.
    + proposalNumber (optional) - Proposal Number. Maximum length is 8.
    + changedValue (optional) - Changed Value. Maximum length is 200.
    + comments (optional) - Comments. Maximum length is 300.
    + displayValue (optional) - Display Value. Maximum length is 200.
    + oldDisplayValue (optional) - Old Display Value. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
