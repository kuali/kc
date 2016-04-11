## Mail Types [/research-sys/api/v1/mail-types/]

### Get Mail Types by Key [GET /research-sys/api/v1/mail-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"mailType": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Mail Types [GET /research-sys/api/v1/mail-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"mailType": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"mailType": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Mail Types with Filtering [GET /research-sys/api/v1/mail-types/]
    
+ Parameters

        + mailType
            + description

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"mailType": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"mailType": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Mail Types [GET /research-sys/api/v1/mail-types/]
	                                          
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
    
            {"columns":["mailType","description"],"primaryKey":"mailType"}
		
### Get Blueprint API specification for Mail Types [GET /research-sys/api/v1/mail-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Mail Types.md"
            transfer-encoding:chunked


### Update Mail Types [PUT /research-sys/api/v1/mail-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"mailType": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Mail Types [PUT /research-sys/api/v1/mail-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"mailType": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"mailType": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Mail Types [POST /research-sys/api/v1/mail-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"mailType": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"mailType": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Mail Types [POST /research-sys/api/v1/mail-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"mailType": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"mailType": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"mailType": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"mailType": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Mail Types by Key [DELETE /research-sys/api/v1/mail-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Mail Types [DELETE /research-sys/api/v1/mail-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Mail Types with Matching [DELETE /research-sys/api/v1/mail-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + mailType
            + description

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
