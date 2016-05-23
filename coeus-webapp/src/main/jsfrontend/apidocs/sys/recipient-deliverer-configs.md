## Recipient Deliverer Configs [/research-sys/api/v1/recipient-deliverer-configs/]

### Get Recipient Deliverer Configs by Key [GET /research-sys/api/v1/recipient-deliverer-configs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","recipientId": "(val)","delivererName": "(val)","channel": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}

### Get All Recipient Deliverer Configs [GET /research-sys/api/v1/recipient-deliverer-configs/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","recipientId": "(val)","delivererName": "(val)","channel": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","recipientId": "(val)","delivererName": "(val)","channel": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]

### Get All Recipient Deliverer Configs with Filtering [GET /research-sys/api/v1/recipient-deliverer-configs/]
    
+ Parameters

    + id (optional) - 
    + recipientId (optional) - 
    + delivererName (optional) - 
    + channel (optional) - 
    + lockVerNbr (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","recipientId": "(val)","delivererName": "(val)","channel": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","recipientId": "(val)","delivererName": "(val)","channel": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Recipient Deliverer Configs [GET /research-sys/api/v1/recipient-deliverer-configs/]
	                                          
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
    
            {"columns":["id","recipientId","delivererName","channel","lockVerNbr"],"primaryKey":"id"}
		
### Get Blueprint API specification for Recipient Deliverer Configs [GET /research-sys/api/v1/recipient-deliverer-configs/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Recipient Deliverer Configs.md"
            transfer-encoding:chunked
### Update Recipient Deliverer Configs [PUT /research-sys/api/v1/recipient-deliverer-configs/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","recipientId": "(val)","delivererName": "(val)","channel": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Recipient Deliverer Configs [PUT /research-sys/api/v1/recipient-deliverer-configs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","recipientId": "(val)","delivererName": "(val)","channel": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","recipientId": "(val)","delivererName": "(val)","channel": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Recipient Deliverer Configs [POST /research-sys/api/v1/recipient-deliverer-configs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","recipientId": "(val)","delivererName": "(val)","channel": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","recipientId": "(val)","delivererName": "(val)","channel": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Recipient Deliverer Configs [POST /research-sys/api/v1/recipient-deliverer-configs/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","recipientId": "(val)","delivererName": "(val)","channel": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","recipientId": "(val)","delivererName": "(val)","channel": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","recipientId": "(val)","delivererName": "(val)","channel": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","recipientId": "(val)","delivererName": "(val)","channel": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
### Delete Recipient Deliverer Configs by Key [DELETE /research-sys/api/v1/recipient-deliverer-configs/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Recipient Deliverer Configs [DELETE /research-sys/api/v1/recipient-deliverer-configs/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Recipient Deliverer Configs with Matching [DELETE /research-sys/api/v1/recipient-deliverer-configs/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + recipientId (optional) - 
    + delivererName (optional) - 
    + channel (optional) - 
    + lockVerNbr (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
