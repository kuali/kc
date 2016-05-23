## Recipient Preferences [/research-sys/api/v1/recipient-preferences/]

### Get Recipient Preferences by Key [GET /research-sys/api/v1/recipient-preferences/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","recipientId": "(val)","property": "(val)","value": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}

### Get All Recipient Preferences [GET /research-sys/api/v1/recipient-preferences/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","recipientId": "(val)","property": "(val)","value": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","recipientId": "(val)","property": "(val)","value": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]

### Get All Recipient Preferences with Filtering [GET /research-sys/api/v1/recipient-preferences/]
    
+ Parameters

    + id (optional) - 
    + recipientId (optional) - 
    + property (optional) - 
    + value (optional) - 
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
              {"id": "(val)","recipientId": "(val)","property": "(val)","value": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","recipientId": "(val)","property": "(val)","value": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Recipient Preferences [GET /research-sys/api/v1/recipient-preferences/]
	                                          
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
    
            {"columns":["id","recipientId","property","value","lockVerNbr"],"primaryKey":"id"}
		
### Get Blueprint API specification for Recipient Preferences [GET /research-sys/api/v1/recipient-preferences/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Recipient Preferences.md"
            transfer-encoding:chunked
### Update Recipient Preferences [PUT /research-sys/api/v1/recipient-preferences/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","recipientId": "(val)","property": "(val)","value": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Recipient Preferences [PUT /research-sys/api/v1/recipient-preferences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","recipientId": "(val)","property": "(val)","value": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","recipientId": "(val)","property": "(val)","value": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Recipient Preferences [POST /research-sys/api/v1/recipient-preferences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","recipientId": "(val)","property": "(val)","value": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","recipientId": "(val)","property": "(val)","value": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Recipient Preferences [POST /research-sys/api/v1/recipient-preferences/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","recipientId": "(val)","property": "(val)","value": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","recipientId": "(val)","property": "(val)","value": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","recipientId": "(val)","property": "(val)","value": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","recipientId": "(val)","property": "(val)","value": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
### Delete Recipient Preferences by Key [DELETE /research-sys/api/v1/recipient-preferences/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Recipient Preferences [DELETE /research-sys/api/v1/recipient-preferences/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Recipient Preferences with Matching [DELETE /research-sys/api/v1/recipient-preferences/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + recipientId (optional) - 
    + property (optional) - 
    + value (optional) - 
    + lockVerNbr (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
