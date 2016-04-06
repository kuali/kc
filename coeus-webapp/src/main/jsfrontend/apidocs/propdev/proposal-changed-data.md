## Proposal Changed Data [/research-sys/api/v1/proposal-changed-data/]

### Get Proposal Changed Data by Key [GET /research-sys/api/v1/proposal-changed-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}

### Get All Proposal Changed Data [GET /research-sys/api/v1/proposal-changed-data/]
	 
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

### Get All Proposal Changed Data with Filtering [GET /research-sys/api/v1/proposal-changed-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + changeNumber
            + columnName
            + proposalNumber
            + changedValue
            + comments
            + displayValue
            + oldDisplayValue
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"},
              {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Changed Data [GET /research-sys/api/v1/proposal-changed-data/]
	 
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
		
### Get Blueprint API specification for Proposal Changed Data [GET /research-sys/api/v1/proposal-changed-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Changed Data.md"
            transfer-encoding:chunked


### Update Proposal Changed Data [PUT /research-sys/api/v1/proposal-changed-data/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Changed Data [PUT /research-sys/api/v1/proposal-changed-data/]

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

### Insert Proposal Changed Data [POST /research-sys/api/v1/proposal-changed-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"changeNumber": "(val)","columnName": "(val)","proposalNumber": "(val)","changedValue": "(val)","comments": "(val)","displayValue": "(val)","oldDisplayValue": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Changed Data [POST /research-sys/api/v1/proposal-changed-data/]

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
            
### Delete Proposal Changed Data by Key [DELETE /research-sys/api/v1/proposal-changed-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Changed Data [DELETE /research-sys/api/v1/proposal-changed-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Changed Data with Matching [DELETE /research-sys/api/v1/proposal-changed-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + changeNumber
            + columnName
            + proposalNumber
            + changedValue
            + comments
            + displayValue
            + oldDisplayValue


+ Response 204
