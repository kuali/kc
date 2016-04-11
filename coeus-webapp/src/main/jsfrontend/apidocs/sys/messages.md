## Messages [/research-sys/api/v1/messages/]

### Get Messages by Key [GET /research-sys/api/v1/messages/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","originId": "(val)","deliveryType": "(val)","channel": "(val)","producer": "(val)","creationDateTime": "(val)","title": "(val)","content": "(val)","contentType": "(val)","url": "(val)","recipient": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}

### Get All Messages [GET /research-sys/api/v1/messages/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","originId": "(val)","deliveryType": "(val)","channel": "(val)","producer": "(val)","creationDateTime": "(val)","title": "(val)","content": "(val)","contentType": "(val)","url": "(val)","recipient": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","originId": "(val)","deliveryType": "(val)","channel": "(val)","producer": "(val)","creationDateTime": "(val)","title": "(val)","content": "(val)","contentType": "(val)","url": "(val)","recipient": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]

### Get All Messages with Filtering [GET /research-sys/api/v1/messages/]
    
+ Parameters

        + id
            + originId
            + deliveryType
            + channel
            + producer
            + creationDateTime
            + title
            + content
            + contentType
            + url
            + recipient
            + lockVerNbr

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","originId": "(val)","deliveryType": "(val)","channel": "(val)","producer": "(val)","creationDateTime": "(val)","title": "(val)","content": "(val)","contentType": "(val)","url": "(val)","recipient": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","originId": "(val)","deliveryType": "(val)","channel": "(val)","producer": "(val)","creationDateTime": "(val)","title": "(val)","content": "(val)","contentType": "(val)","url": "(val)","recipient": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Messages [GET /research-sys/api/v1/messages/]
	                                          
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
    
            {"columns":["id","originId","deliveryType","channel","producer","creationDateTime","title","content","contentType","url","recipient","lockVerNbr"],"primaryKey":"id"}
		
### Get Blueprint API specification for Messages [GET /research-sys/api/v1/messages/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Messages.md"
            transfer-encoding:chunked


### Update Messages [PUT /research-sys/api/v1/messages/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","originId": "(val)","deliveryType": "(val)","channel": "(val)","producer": "(val)","creationDateTime": "(val)","title": "(val)","content": "(val)","contentType": "(val)","url": "(val)","recipient": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Messages [PUT /research-sys/api/v1/messages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","originId": "(val)","deliveryType": "(val)","channel": "(val)","producer": "(val)","creationDateTime": "(val)","title": "(val)","content": "(val)","contentType": "(val)","url": "(val)","recipient": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","originId": "(val)","deliveryType": "(val)","channel": "(val)","producer": "(val)","creationDateTime": "(val)","title": "(val)","content": "(val)","contentType": "(val)","url": "(val)","recipient": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Messages [POST /research-sys/api/v1/messages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","originId": "(val)","deliveryType": "(val)","channel": "(val)","producer": "(val)","creationDateTime": "(val)","title": "(val)","content": "(val)","contentType": "(val)","url": "(val)","recipient": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","originId": "(val)","deliveryType": "(val)","channel": "(val)","producer": "(val)","creationDateTime": "(val)","title": "(val)","content": "(val)","contentType": "(val)","url": "(val)","recipient": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Messages [POST /research-sys/api/v1/messages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","originId": "(val)","deliveryType": "(val)","channel": "(val)","producer": "(val)","creationDateTime": "(val)","title": "(val)","content": "(val)","contentType": "(val)","url": "(val)","recipient": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","originId": "(val)","deliveryType": "(val)","channel": "(val)","producer": "(val)","creationDateTime": "(val)","title": "(val)","content": "(val)","contentType": "(val)","url": "(val)","recipient": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","originId": "(val)","deliveryType": "(val)","channel": "(val)","producer": "(val)","creationDateTime": "(val)","title": "(val)","content": "(val)","contentType": "(val)","url": "(val)","recipient": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","originId": "(val)","deliveryType": "(val)","channel": "(val)","producer": "(val)","creationDateTime": "(val)","title": "(val)","content": "(val)","contentType": "(val)","url": "(val)","recipient": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Messages by Key [DELETE /research-sys/api/v1/messages/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Messages [DELETE /research-sys/api/v1/messages/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Messages with Matching [DELETE /research-sys/api/v1/messages/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + originId
            + deliveryType
            + channel
            + producer
            + creationDateTime
            + title
            + content
            + contentType
            + url
            + recipient
            + lockVerNbr

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
