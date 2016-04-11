## Actions [/research-sys/api/v1/actions/]

### Get Actions by Key [GET /research-sys/api/v1/actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","typeId": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}

### Get All Actions [GET /research-sys/api/v1/actions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","typeId": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","typeId": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Actions with Filtering [GET /research-sys/api/v1/actions/]
    
+ Parameters

        + id
            + namespace
            + name
            + description
            + typeId
            + sequenceNumber

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","typeId": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","typeId": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Actions [GET /research-sys/api/v1/actions/]
	                                          
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
    
            {"columns":["id","namespace","name","description","typeId","sequenceNumber"],"primaryKey":"id"}
		
### Get Blueprint API specification for Actions [GET /research-sys/api/v1/actions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Actions.md"
            transfer-encoding:chunked


### Update Actions [PUT /research-sys/api/v1/actions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","typeId": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Actions [PUT /research-sys/api/v1/actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","typeId": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","typeId": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Actions [POST /research-sys/api/v1/actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","typeId": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","typeId": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Actions [POST /research-sys/api/v1/actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","typeId": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","typeId": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","typeId": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","namespace": "(val)","name": "(val)","description": "(val)","typeId": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Actions by Key [DELETE /research-sys/api/v1/actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Actions [DELETE /research-sys/api/v1/actions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Actions with Matching [DELETE /research-sys/api/v1/actions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + namespace
            + name
            + description
            + typeId
            + sequenceNumber

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
