## Notification Channel Reviewers [/research-sys/api/v1/notification-channel-reviewers/]

### Get Notification Channel Reviewers by Key [GET /research-sys/api/v1/notification-channel-reviewers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","reviewerType": "(val)","reviewerId": "(val)","_primaryKey": "(val)"}

### Get All Notification Channel Reviewers [GET /research-sys/api/v1/notification-channel-reviewers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","reviewerType": "(val)","reviewerId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","reviewerType": "(val)","reviewerId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Notification Channel Reviewers with Filtering [GET /research-sys/api/v1/notification-channel-reviewers/]
    
+ Parameters

    + id (optional) - 
    + reviewerType (optional) - 
    + reviewerId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","reviewerType": "(val)","reviewerId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","reviewerType": "(val)","reviewerId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Notification Channel Reviewers [GET /research-sys/api/v1/notification-channel-reviewers/]
	                                          
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
    
            {"columns":["id","reviewerType","reviewerId"],"primaryKey":"id"}
		
### Get Blueprint API specification for Notification Channel Reviewers [GET /research-sys/api/v1/notification-channel-reviewers/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Notification Channel Reviewers.md"
            transfer-encoding:chunked


### Update Notification Channel Reviewers [PUT /research-sys/api/v1/notification-channel-reviewers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","reviewerType": "(val)","reviewerId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Notification Channel Reviewers [PUT /research-sys/api/v1/notification-channel-reviewers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","reviewerType": "(val)","reviewerId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","reviewerType": "(val)","reviewerId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Notification Channel Reviewers [POST /research-sys/api/v1/notification-channel-reviewers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","reviewerType": "(val)","reviewerId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","reviewerType": "(val)","reviewerId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Notification Channel Reviewers [POST /research-sys/api/v1/notification-channel-reviewers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","reviewerType": "(val)","reviewerId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","reviewerType": "(val)","reviewerId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","reviewerType": "(val)","reviewerId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","reviewerType": "(val)","reviewerId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Notification Channel Reviewers by Key [DELETE /research-sys/api/v1/notification-channel-reviewers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Channel Reviewers [DELETE /research-sys/api/v1/notification-channel-reviewers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Notification Channel Reviewers with Matching [DELETE /research-sys/api/v1/notification-channel-reviewers/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + reviewerType (optional) - 
    + reviewerId (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
