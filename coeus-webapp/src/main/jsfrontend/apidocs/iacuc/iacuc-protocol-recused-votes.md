## Iacuc Protocol Recused Votes [/iacuc/api/v1/iacuc-protocol-recused-votes/]

### Get Iacuc Protocol Recused Votes by Key [GET /iacuc/api/v1/iacuc-protocol-recused-votes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Recused Votes [GET /iacuc/api/v1/iacuc-protocol-recused-votes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Recused Votes with Filtering [GET /iacuc/api/v1/iacuc-protocol-recused-votes/]
    
+ Parameters

    + protocolVoteRecusedId (optional) - 
    + protocolIdFk (optional) - 
    + submissionIdFk (optional) - 
    + personId (optional) - 
    + rolodexId (optional) - 
    + nonEmployeeFlag (optional) - 
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
              {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Recused Votes [GET /iacuc/api/v1/iacuc-protocol-recused-votes/]
	                                          
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
    
            {"columns":["protocolVoteRecusedId","protocolIdFk","submissionIdFk","personId","rolodexId","nonEmployeeFlag","comments"],"primaryKey":"protocolVoteRecusedId"}
		
### Get Blueprint API specification for Iacuc Protocol Recused Votes [GET /iacuc/api/v1/iacuc-protocol-recused-votes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Recused Votes.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Recused Votes [PUT /iacuc/api/v1/iacuc-protocol-recused-votes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Recused Votes [PUT /iacuc/api/v1/iacuc-protocol-recused-votes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Protocol Recused Votes [POST /iacuc/api/v1/iacuc-protocol-recused-votes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Recused Votes [POST /iacuc/api/v1/iacuc-protocol-recused-votes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"},
              {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Protocol Recused Votes by Key [DELETE /iacuc/api/v1/iacuc-protocol-recused-votes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Recused Votes [DELETE /iacuc/api/v1/iacuc-protocol-recused-votes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Recused Votes with Matching [DELETE /iacuc/api/v1/iacuc-protocol-recused-votes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolVoteRecusedId (optional) - 
    + protocolIdFk (optional) - 
    + submissionIdFk (optional) - 
    + personId (optional) - 
    + rolodexId (optional) - 
    + nonEmployeeFlag (optional) - 
    + comments (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
