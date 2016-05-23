## Protocol Recused Votes [/research-common/api/v1/protocol-recused-votes/]

### Get Protocol Recused Votes by Key [GET /research-common/api/v1/protocol-recused-votes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Protocol Recused Votes [GET /research-common/api/v1/protocol-recused-votes/]
	 
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

### Get All Protocol Recused Votes with Filtering [GET /research-common/api/v1/protocol-recused-votes/]
    
+ Parameters

    + protocolVoteRecusedId (optional) - Protocol Vote Recused Id. Maximum length is 22.
    + protocolIdFk (optional) - Protocol Id. Maximum length is 22.
    + submissionIdFk (optional) - Schedule Id. Maximum length is 10.
    + personId (optional) - Person Id. Maximum length is 40.
    + rolodexId (optional) - 
    + nonEmployeeFlag (optional) - Non Employee Flag. Maximum length is 1.
    + comments (optional) - Comments. Maximum length is 2000.

            
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
			
### Get Schema for Protocol Recused Votes [GET /research-common/api/v1/protocol-recused-votes/]
	                                          
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
		
### Get Blueprint API specification for Protocol Recused Votes [GET /research-common/api/v1/protocol-recused-votes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Recused Votes.md"
            transfer-encoding:chunked
### Update Protocol Recused Votes [PUT /research-common/api/v1/protocol-recused-votes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Recused Votes [PUT /research-common/api/v1/protocol-recused-votes/]

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
### Insert Protocol Recused Votes [POST /research-common/api/v1/protocol-recused-votes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Recused Votes [POST /research-common/api/v1/protocol-recused-votes/]

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
### Delete Protocol Recused Votes by Key [DELETE /research-common/api/v1/protocol-recused-votes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Recused Votes [DELETE /research-common/api/v1/protocol-recused-votes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Recused Votes with Matching [DELETE /research-common/api/v1/protocol-recused-votes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolVoteRecusedId (optional) - Protocol Vote Recused Id. Maximum length is 22.
    + protocolIdFk (optional) - Protocol Id. Maximum length is 22.
    + submissionIdFk (optional) - Schedule Id. Maximum length is 10.
    + personId (optional) - Person Id. Maximum length is 40.
    + rolodexId (optional) - 
    + nonEmployeeFlag (optional) - Non Employee Flag. Maximum length is 1.
    + comments (optional) - Comments. Maximum length is 2000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
