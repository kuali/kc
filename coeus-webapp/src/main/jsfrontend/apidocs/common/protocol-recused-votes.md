## Protocol Recused Votes [/research-sys/api/v1/protocol-recused-votes/]

### Get Protocol Recused Votes by Key [GET /research-sys/api/v1/protocol-recused-votes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}

### Get All Protocol Recused Votes [GET /research-sys/api/v1/protocol-recused-votes/]
	 
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

### Get All Protocol Recused Votes with Filtering [GET /research-sys/api/v1/protocol-recused-votes/]
    
+ Parameters

        + protocolVoteRecusedId
            + protocolIdFk
            + submissionIdFk
            + personId
            + rolodexId
            + nonEmployeeFlag
            + comments

            
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
			
### Get Schema for Protocol Recused Votes [GET /research-sys/api/v1/protocol-recused-votes/]
	                                          
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
		
### Get Blueprint API specification for Protocol Recused Votes [GET /research-sys/api/v1/protocol-recused-votes/]
	 
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


### Update Protocol Recused Votes [PUT /research-sys/api/v1/protocol-recused-votes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Recused Votes [PUT /research-sys/api/v1/protocol-recused-votes/]

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

### Insert Protocol Recused Votes [POST /research-sys/api/v1/protocol-recused-votes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolVoteRecusedId": "(val)","protocolIdFk": "(val)","submissionIdFk": "(val)","personId": "(val)","rolodexId": "(val)","nonEmployeeFlag": "(val)","comments": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Recused Votes [POST /research-sys/api/v1/protocol-recused-votes/]

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
            
### Delete Protocol Recused Votes by Key [DELETE /research-sys/api/v1/protocol-recused-votes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Recused Votes [DELETE /research-sys/api/v1/protocol-recused-votes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Recused Votes with Matching [DELETE /research-sys/api/v1/protocol-recused-votes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + protocolVoteRecusedId
            + protocolIdFk
            + submissionIdFk
            + personId
            + rolodexId
            + nonEmployeeFlag
            + comments

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
