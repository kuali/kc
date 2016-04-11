## Action Taken Values [/research-sys/api/v1/action-taken-values/]

### Get Action Taken Values by Key [GET /research-sys/api/v1/action-taken-values/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"actionTakenId": "(val)","documentId": "(val)","actionTaken": "(val)","actionDate": "(val)","annotation": "(val)","docVersion": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","delegatorGroupId": "(val)","currentIndicator": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}

### Get All Action Taken Values [GET /research-sys/api/v1/action-taken-values/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"actionTakenId": "(val)","documentId": "(val)","actionTaken": "(val)","actionDate": "(val)","annotation": "(val)","docVersion": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","delegatorGroupId": "(val)","currentIndicator": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"actionTakenId": "(val)","documentId": "(val)","actionTaken": "(val)","actionDate": "(val)","annotation": "(val)","docVersion": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","delegatorGroupId": "(val)","currentIndicator": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]

### Get All Action Taken Values with Filtering [GET /research-sys/api/v1/action-taken-values/]
    
+ Parameters

        + actionTakenId
            + documentId
            + actionTaken
            + actionDate
            + annotation
            + docVersion
            + principalId
            + delegatorPrincipalId
            + delegatorGroupId
            + currentIndicator
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
              {"actionTakenId": "(val)","documentId": "(val)","actionTaken": "(val)","actionDate": "(val)","annotation": "(val)","docVersion": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","delegatorGroupId": "(val)","currentIndicator": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"actionTakenId": "(val)","documentId": "(val)","actionTaken": "(val)","actionDate": "(val)","annotation": "(val)","docVersion": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","delegatorGroupId": "(val)","currentIndicator": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Action Taken Values [GET /research-sys/api/v1/action-taken-values/]
	                                          
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
    
            {"columns":["actionTakenId","documentId","actionTaken","actionDate","annotation","docVersion","principalId","delegatorPrincipalId","delegatorGroupId","currentIndicator","lockVerNbr"],"primaryKey":"actionTakenId"}
		
### Get Blueprint API specification for Action Taken Values [GET /research-sys/api/v1/action-taken-values/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Action Taken Values.md"
            transfer-encoding:chunked


### Update Action Taken Values [PUT /research-sys/api/v1/action-taken-values/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"actionTakenId": "(val)","documentId": "(val)","actionTaken": "(val)","actionDate": "(val)","annotation": "(val)","docVersion": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","delegatorGroupId": "(val)","currentIndicator": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Action Taken Values [PUT /research-sys/api/v1/action-taken-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"actionTakenId": "(val)","documentId": "(val)","actionTaken": "(val)","actionDate": "(val)","annotation": "(val)","docVersion": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","delegatorGroupId": "(val)","currentIndicator": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"actionTakenId": "(val)","documentId": "(val)","actionTaken": "(val)","actionDate": "(val)","annotation": "(val)","docVersion": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","delegatorGroupId": "(val)","currentIndicator": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Action Taken Values [POST /research-sys/api/v1/action-taken-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"actionTakenId": "(val)","documentId": "(val)","actionTaken": "(val)","actionDate": "(val)","annotation": "(val)","docVersion": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","delegatorGroupId": "(val)","currentIndicator": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"actionTakenId": "(val)","documentId": "(val)","actionTaken": "(val)","actionDate": "(val)","annotation": "(val)","docVersion": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","delegatorGroupId": "(val)","currentIndicator": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Action Taken Values [POST /research-sys/api/v1/action-taken-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"actionTakenId": "(val)","documentId": "(val)","actionTaken": "(val)","actionDate": "(val)","annotation": "(val)","docVersion": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","delegatorGroupId": "(val)","currentIndicator": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"actionTakenId": "(val)","documentId": "(val)","actionTaken": "(val)","actionDate": "(val)","annotation": "(val)","docVersion": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","delegatorGroupId": "(val)","currentIndicator": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"actionTakenId": "(val)","documentId": "(val)","actionTaken": "(val)","actionDate": "(val)","annotation": "(val)","docVersion": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","delegatorGroupId": "(val)","currentIndicator": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"},
              {"actionTakenId": "(val)","documentId": "(val)","actionTaken": "(val)","actionDate": "(val)","annotation": "(val)","docVersion": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","delegatorGroupId": "(val)","currentIndicator": "(val)","lockVerNbr": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Action Taken Values by Key [DELETE /research-sys/api/v1/action-taken-values/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Action Taken Values [DELETE /research-sys/api/v1/action-taken-values/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Action Taken Values with Matching [DELETE /research-sys/api/v1/action-taken-values/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + actionTakenId
            + documentId
            + actionTaken
            + actionDate
            + annotation
            + docVersion
            + principalId
            + delegatorPrincipalId
            + delegatorGroupId
            + currentIndicator
            + lockVerNbr

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
