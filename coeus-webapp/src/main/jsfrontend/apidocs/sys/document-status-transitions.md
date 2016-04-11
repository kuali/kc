## Document Status Transitions [/research-sys/api/v1/document-status-transitions/]

### Get Document Status Transitions by Key [GET /research-sys/api/v1/document-status-transitions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"statusTransitionId": "(val)","documentId": "(val)","oldAppDocStatus": "(val)","newAppDocStatus": "(val)","statusTransitionDate": "(val)","_primaryKey": "(val)"}

### Get All Document Status Transitions [GET /research-sys/api/v1/document-status-transitions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"statusTransitionId": "(val)","documentId": "(val)","oldAppDocStatus": "(val)","newAppDocStatus": "(val)","statusTransitionDate": "(val)","_primaryKey": "(val)"},
              {"statusTransitionId": "(val)","documentId": "(val)","oldAppDocStatus": "(val)","newAppDocStatus": "(val)","statusTransitionDate": "(val)","_primaryKey": "(val)"}
            ]

### Get All Document Status Transitions with Filtering [GET /research-sys/api/v1/document-status-transitions/]
    
+ Parameters

        + statusTransitionId
            + documentId
            + oldAppDocStatus
            + newAppDocStatus
            + statusTransitionDate

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"statusTransitionId": "(val)","documentId": "(val)","oldAppDocStatus": "(val)","newAppDocStatus": "(val)","statusTransitionDate": "(val)","_primaryKey": "(val)"},
              {"statusTransitionId": "(val)","documentId": "(val)","oldAppDocStatus": "(val)","newAppDocStatus": "(val)","statusTransitionDate": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Document Status Transitions [GET /research-sys/api/v1/document-status-transitions/]
	                                          
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
    
            {"columns":["statusTransitionId","documentId","oldAppDocStatus","newAppDocStatus","statusTransitionDate"],"primaryKey":"statusTransitionId"}
		
### Get Blueprint API specification for Document Status Transitions [GET /research-sys/api/v1/document-status-transitions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Document Status Transitions.md"
            transfer-encoding:chunked


### Update Document Status Transitions [PUT /research-sys/api/v1/document-status-transitions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"statusTransitionId": "(val)","documentId": "(val)","oldAppDocStatus": "(val)","newAppDocStatus": "(val)","statusTransitionDate": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Document Status Transitions [PUT /research-sys/api/v1/document-status-transitions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"statusTransitionId": "(val)","documentId": "(val)","oldAppDocStatus": "(val)","newAppDocStatus": "(val)","statusTransitionDate": "(val)","_primaryKey": "(val)"},
              {"statusTransitionId": "(val)","documentId": "(val)","oldAppDocStatus": "(val)","newAppDocStatus": "(val)","statusTransitionDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Document Status Transitions [POST /research-sys/api/v1/document-status-transitions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"statusTransitionId": "(val)","documentId": "(val)","oldAppDocStatus": "(val)","newAppDocStatus": "(val)","statusTransitionDate": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"statusTransitionId": "(val)","documentId": "(val)","oldAppDocStatus": "(val)","newAppDocStatus": "(val)","statusTransitionDate": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Document Status Transitions [POST /research-sys/api/v1/document-status-transitions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"statusTransitionId": "(val)","documentId": "(val)","oldAppDocStatus": "(val)","newAppDocStatus": "(val)","statusTransitionDate": "(val)","_primaryKey": "(val)"},
              {"statusTransitionId": "(val)","documentId": "(val)","oldAppDocStatus": "(val)","newAppDocStatus": "(val)","statusTransitionDate": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"statusTransitionId": "(val)","documentId": "(val)","oldAppDocStatus": "(val)","newAppDocStatus": "(val)","statusTransitionDate": "(val)","_primaryKey": "(val)"},
              {"statusTransitionId": "(val)","documentId": "(val)","oldAppDocStatus": "(val)","newAppDocStatus": "(val)","statusTransitionDate": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Document Status Transitions by Key [DELETE /research-sys/api/v1/document-status-transitions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Status Transitions [DELETE /research-sys/api/v1/document-status-transitions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Document Status Transitions with Matching [DELETE /research-sys/api/v1/document-status-transitions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + statusTransitionId
            + documentId
            + oldAppDocStatus
            + newAppDocStatus
            + statusTransitionDate

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
