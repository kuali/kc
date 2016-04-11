## Persisted Messages [/research-sys/api/v1/persisted-messages/]

### Get Persisted Messages by Key [GET /research-sys/api/v1/persisted-messages/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"routeQueueId": "(val)","queuePriority": "(val)","queueStatus": "(val)","queueDate": "(val)","expirationDate": "(val)","retryCount": "(val)","lockVerNbr": "(val)","ipNumber": "(val)","serviceName": "(val)","applicationId": "(val)","methodName": "(val)","value1": "(val)","value2": "(val)","_primaryKey": "(val)"}

### Get All Persisted Messages [GET /research-sys/api/v1/persisted-messages/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"routeQueueId": "(val)","queuePriority": "(val)","queueStatus": "(val)","queueDate": "(val)","expirationDate": "(val)","retryCount": "(val)","lockVerNbr": "(val)","ipNumber": "(val)","serviceName": "(val)","applicationId": "(val)","methodName": "(val)","value1": "(val)","value2": "(val)","_primaryKey": "(val)"},
              {"routeQueueId": "(val)","queuePriority": "(val)","queueStatus": "(val)","queueDate": "(val)","expirationDate": "(val)","retryCount": "(val)","lockVerNbr": "(val)","ipNumber": "(val)","serviceName": "(val)","applicationId": "(val)","methodName": "(val)","value1": "(val)","value2": "(val)","_primaryKey": "(val)"}
            ]

### Get All Persisted Messages with Filtering [GET /research-sys/api/v1/persisted-messages/]
    
+ Parameters

        + routeQueueId
            + queuePriority
            + queueStatus
            + queueDate
            + expirationDate
            + retryCount
            + lockVerNbr
            + ipNumber
            + serviceName
            + applicationId
            + methodName
            + value1
            + value2

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"routeQueueId": "(val)","queuePriority": "(val)","queueStatus": "(val)","queueDate": "(val)","expirationDate": "(val)","retryCount": "(val)","lockVerNbr": "(val)","ipNumber": "(val)","serviceName": "(val)","applicationId": "(val)","methodName": "(val)","value1": "(val)","value2": "(val)","_primaryKey": "(val)"},
              {"routeQueueId": "(val)","queuePriority": "(val)","queueStatus": "(val)","queueDate": "(val)","expirationDate": "(val)","retryCount": "(val)","lockVerNbr": "(val)","ipNumber": "(val)","serviceName": "(val)","applicationId": "(val)","methodName": "(val)","value1": "(val)","value2": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Persisted Messages [GET /research-sys/api/v1/persisted-messages/]
	                                          
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
    
            {"columns":["routeQueueId","queuePriority","queueStatus","queueDate","expirationDate","retryCount","lockVerNbr","ipNumber","serviceName","applicationId","methodName","value1","value2"],"primaryKey":"routeQueueId"}
		
### Get Blueprint API specification for Persisted Messages [GET /research-sys/api/v1/persisted-messages/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Persisted Messages.md"
            transfer-encoding:chunked


### Update Persisted Messages [PUT /research-sys/api/v1/persisted-messages/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"routeQueueId": "(val)","queuePriority": "(val)","queueStatus": "(val)","queueDate": "(val)","expirationDate": "(val)","retryCount": "(val)","lockVerNbr": "(val)","ipNumber": "(val)","serviceName": "(val)","applicationId": "(val)","methodName": "(val)","value1": "(val)","value2": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Persisted Messages [PUT /research-sys/api/v1/persisted-messages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"routeQueueId": "(val)","queuePriority": "(val)","queueStatus": "(val)","queueDate": "(val)","expirationDate": "(val)","retryCount": "(val)","lockVerNbr": "(val)","ipNumber": "(val)","serviceName": "(val)","applicationId": "(val)","methodName": "(val)","value1": "(val)","value2": "(val)","_primaryKey": "(val)"},
              {"routeQueueId": "(val)","queuePriority": "(val)","queueStatus": "(val)","queueDate": "(val)","expirationDate": "(val)","retryCount": "(val)","lockVerNbr": "(val)","ipNumber": "(val)","serviceName": "(val)","applicationId": "(val)","methodName": "(val)","value1": "(val)","value2": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Persisted Messages [POST /research-sys/api/v1/persisted-messages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"routeQueueId": "(val)","queuePriority": "(val)","queueStatus": "(val)","queueDate": "(val)","expirationDate": "(val)","retryCount": "(val)","lockVerNbr": "(val)","ipNumber": "(val)","serviceName": "(val)","applicationId": "(val)","methodName": "(val)","value1": "(val)","value2": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"routeQueueId": "(val)","queuePriority": "(val)","queueStatus": "(val)","queueDate": "(val)","expirationDate": "(val)","retryCount": "(val)","lockVerNbr": "(val)","ipNumber": "(val)","serviceName": "(val)","applicationId": "(val)","methodName": "(val)","value1": "(val)","value2": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Persisted Messages [POST /research-sys/api/v1/persisted-messages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"routeQueueId": "(val)","queuePriority": "(val)","queueStatus": "(val)","queueDate": "(val)","expirationDate": "(val)","retryCount": "(val)","lockVerNbr": "(val)","ipNumber": "(val)","serviceName": "(val)","applicationId": "(val)","methodName": "(val)","value1": "(val)","value2": "(val)","_primaryKey": "(val)"},
              {"routeQueueId": "(val)","queuePriority": "(val)","queueStatus": "(val)","queueDate": "(val)","expirationDate": "(val)","retryCount": "(val)","lockVerNbr": "(val)","ipNumber": "(val)","serviceName": "(val)","applicationId": "(val)","methodName": "(val)","value1": "(val)","value2": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"routeQueueId": "(val)","queuePriority": "(val)","queueStatus": "(val)","queueDate": "(val)","expirationDate": "(val)","retryCount": "(val)","lockVerNbr": "(val)","ipNumber": "(val)","serviceName": "(val)","applicationId": "(val)","methodName": "(val)","value1": "(val)","value2": "(val)","_primaryKey": "(val)"},
              {"routeQueueId": "(val)","queuePriority": "(val)","queueStatus": "(val)","queueDate": "(val)","expirationDate": "(val)","retryCount": "(val)","lockVerNbr": "(val)","ipNumber": "(val)","serviceName": "(val)","applicationId": "(val)","methodName": "(val)","value1": "(val)","value2": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Persisted Messages by Key [DELETE /research-sys/api/v1/persisted-messages/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Persisted Messages [DELETE /research-sys/api/v1/persisted-messages/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Persisted Messages with Matching [DELETE /research-sys/api/v1/persisted-messages/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + routeQueueId
            + queuePriority
            + queueStatus
            + queueDate
            + expirationDate
            + retryCount
            + lockVerNbr
            + ipNumber
            + serviceName
            + applicationId
            + methodName
            + value1
            + value2

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
