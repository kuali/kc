## Message Of The Days [/research-sys/api/v1/message-of-the-days/]

### Get Message Of The Days by Key [GET /research-sys/api/v1/message-of-the-days/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"messageOfTheDayId": "(val)","message": "(val)","active": "(val)","displayOrder": "(val)","_primaryKey": "(val)"}

### Get All Message Of The Days [GET /research-sys/api/v1/message-of-the-days/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"messageOfTheDayId": "(val)","message": "(val)","active": "(val)","displayOrder": "(val)","_primaryKey": "(val)"},
              {"messageOfTheDayId": "(val)","message": "(val)","active": "(val)","displayOrder": "(val)","_primaryKey": "(val)"}
            ]

### Get All Message Of The Days with Filtering [GET /research-sys/api/v1/message-of-the-days/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + messageOfTheDayId
            + message
            + active
            + displayOrder
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"messageOfTheDayId": "(val)","message": "(val)","active": "(val)","displayOrder": "(val)","_primaryKey": "(val)"},
              {"messageOfTheDayId": "(val)","message": "(val)","active": "(val)","displayOrder": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Message Of The Days [GET /research-sys/api/v1/message-of-the-days/]
	 
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
		
### Get Blueprint API specification for Message Of The Days [GET /research-sys/api/v1/message-of-the-days/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Message Of The Days.md"
            transfer-encoding:chunked


### Update Message Of The Days [PUT /research-sys/api/v1/message-of-the-days/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"messageOfTheDayId": "(val)","message": "(val)","active": "(val)","displayOrder": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Message Of The Days [PUT /research-sys/api/v1/message-of-the-days/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"messageOfTheDayId": "(val)","message": "(val)","active": "(val)","displayOrder": "(val)","_primaryKey": "(val)"},
              {"messageOfTheDayId": "(val)","message": "(val)","active": "(val)","displayOrder": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Message Of The Days [POST /research-sys/api/v1/message-of-the-days/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"messageOfTheDayId": "(val)","message": "(val)","active": "(val)","displayOrder": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"messageOfTheDayId": "(val)","message": "(val)","active": "(val)","displayOrder": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Message Of The Days [POST /research-sys/api/v1/message-of-the-days/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"messageOfTheDayId": "(val)","message": "(val)","active": "(val)","displayOrder": "(val)","_primaryKey": "(val)"},
              {"messageOfTheDayId": "(val)","message": "(val)","active": "(val)","displayOrder": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"messageOfTheDayId": "(val)","message": "(val)","active": "(val)","displayOrder": "(val)","_primaryKey": "(val)"},
              {"messageOfTheDayId": "(val)","message": "(val)","active": "(val)","displayOrder": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Message Of The Days by Key [DELETE /research-sys/api/v1/message-of-the-days/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Message Of The Days [DELETE /research-sys/api/v1/message-of-the-days/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Message Of The Days with Matching [DELETE /research-sys/api/v1/message-of-the-days/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + messageOfTheDayId
            + message
            + active
            + displayOrder


+ Response 204
