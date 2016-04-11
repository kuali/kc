## Outbox Items [/research-sys/api/v1/outbox-items/]

### Get Outbox Items by Key [GET /research-sys/api/v1/outbox-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"docHandlerURL": "(val)","groupId": "(val)","delegatorGroupId": "(val)","docLabel": "(val)","responsibilityId": "(val)","docTitle": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","actionRequestCd": "(val)","docName": "(val)","delegationType": "(val)","roleName": "(val)","documentId": "(val)","id": "(val)","requestLabel": "(val)","dateAssigned": "(val)","actionRequestId": "(val)","_primaryKey": "(val)"}

### Get All Outbox Items [GET /research-sys/api/v1/outbox-items/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"docHandlerURL": "(val)","groupId": "(val)","delegatorGroupId": "(val)","docLabel": "(val)","responsibilityId": "(val)","docTitle": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","actionRequestCd": "(val)","docName": "(val)","delegationType": "(val)","roleName": "(val)","documentId": "(val)","id": "(val)","requestLabel": "(val)","dateAssigned": "(val)","actionRequestId": "(val)","_primaryKey": "(val)"},
              {"docHandlerURL": "(val)","groupId": "(val)","delegatorGroupId": "(val)","docLabel": "(val)","responsibilityId": "(val)","docTitle": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","actionRequestCd": "(val)","docName": "(val)","delegationType": "(val)","roleName": "(val)","documentId": "(val)","id": "(val)","requestLabel": "(val)","dateAssigned": "(val)","actionRequestId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Outbox Items with Filtering [GET /research-sys/api/v1/outbox-items/]
    
+ Parameters

        + docHandlerURL
            + groupId
            + delegatorGroupId
            + docLabel
            + responsibilityId
            + docTitle
            + principalId
            + delegatorPrincipalId
            + actionRequestCd
            + docName
            + delegationType
            + roleName
            + documentId
            + id
            + requestLabel
            + dateAssigned
            + actionRequestId

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"docHandlerURL": "(val)","groupId": "(val)","delegatorGroupId": "(val)","docLabel": "(val)","responsibilityId": "(val)","docTitle": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","actionRequestCd": "(val)","docName": "(val)","delegationType": "(val)","roleName": "(val)","documentId": "(val)","id": "(val)","requestLabel": "(val)","dateAssigned": "(val)","actionRequestId": "(val)","_primaryKey": "(val)"},
              {"docHandlerURL": "(val)","groupId": "(val)","delegatorGroupId": "(val)","docLabel": "(val)","responsibilityId": "(val)","docTitle": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","actionRequestCd": "(val)","docName": "(val)","delegationType": "(val)","roleName": "(val)","documentId": "(val)","id": "(val)","requestLabel": "(val)","dateAssigned": "(val)","actionRequestId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Outbox Items [GET /research-sys/api/v1/outbox-items/]
	                                          
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
    
            {"columns":["docHandlerURL","groupId","delegatorGroupId","docLabel","responsibilityId","docTitle","principalId","delegatorPrincipalId","actionRequestCd","docName","delegationType","roleName","documentId","id","requestLabel","dateAssigned","actionRequestId"],"primaryKey":"id"}
		
### Get Blueprint API specification for Outbox Items [GET /research-sys/api/v1/outbox-items/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Outbox Items.md"
            transfer-encoding:chunked


### Update Outbox Items [PUT /research-sys/api/v1/outbox-items/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"docHandlerURL": "(val)","groupId": "(val)","delegatorGroupId": "(val)","docLabel": "(val)","responsibilityId": "(val)","docTitle": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","actionRequestCd": "(val)","docName": "(val)","delegationType": "(val)","roleName": "(val)","documentId": "(val)","id": "(val)","requestLabel": "(val)","dateAssigned": "(val)","actionRequestId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Outbox Items [PUT /research-sys/api/v1/outbox-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"docHandlerURL": "(val)","groupId": "(val)","delegatorGroupId": "(val)","docLabel": "(val)","responsibilityId": "(val)","docTitle": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","actionRequestCd": "(val)","docName": "(val)","delegationType": "(val)","roleName": "(val)","documentId": "(val)","id": "(val)","requestLabel": "(val)","dateAssigned": "(val)","actionRequestId": "(val)","_primaryKey": "(val)"},
              {"docHandlerURL": "(val)","groupId": "(val)","delegatorGroupId": "(val)","docLabel": "(val)","responsibilityId": "(val)","docTitle": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","actionRequestCd": "(val)","docName": "(val)","delegationType": "(val)","roleName": "(val)","documentId": "(val)","id": "(val)","requestLabel": "(val)","dateAssigned": "(val)","actionRequestId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Outbox Items [POST /research-sys/api/v1/outbox-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"docHandlerURL": "(val)","groupId": "(val)","delegatorGroupId": "(val)","docLabel": "(val)","responsibilityId": "(val)","docTitle": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","actionRequestCd": "(val)","docName": "(val)","delegationType": "(val)","roleName": "(val)","documentId": "(val)","id": "(val)","requestLabel": "(val)","dateAssigned": "(val)","actionRequestId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"docHandlerURL": "(val)","groupId": "(val)","delegatorGroupId": "(val)","docLabel": "(val)","responsibilityId": "(val)","docTitle": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","actionRequestCd": "(val)","docName": "(val)","delegationType": "(val)","roleName": "(val)","documentId": "(val)","id": "(val)","requestLabel": "(val)","dateAssigned": "(val)","actionRequestId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Outbox Items [POST /research-sys/api/v1/outbox-items/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"docHandlerURL": "(val)","groupId": "(val)","delegatorGroupId": "(val)","docLabel": "(val)","responsibilityId": "(val)","docTitle": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","actionRequestCd": "(val)","docName": "(val)","delegationType": "(val)","roleName": "(val)","documentId": "(val)","id": "(val)","requestLabel": "(val)","dateAssigned": "(val)","actionRequestId": "(val)","_primaryKey": "(val)"},
              {"docHandlerURL": "(val)","groupId": "(val)","delegatorGroupId": "(val)","docLabel": "(val)","responsibilityId": "(val)","docTitle": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","actionRequestCd": "(val)","docName": "(val)","delegationType": "(val)","roleName": "(val)","documentId": "(val)","id": "(val)","requestLabel": "(val)","dateAssigned": "(val)","actionRequestId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"docHandlerURL": "(val)","groupId": "(val)","delegatorGroupId": "(val)","docLabel": "(val)","responsibilityId": "(val)","docTitle": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","actionRequestCd": "(val)","docName": "(val)","delegationType": "(val)","roleName": "(val)","documentId": "(val)","id": "(val)","requestLabel": "(val)","dateAssigned": "(val)","actionRequestId": "(val)","_primaryKey": "(val)"},
              {"docHandlerURL": "(val)","groupId": "(val)","delegatorGroupId": "(val)","docLabel": "(val)","responsibilityId": "(val)","docTitle": "(val)","principalId": "(val)","delegatorPrincipalId": "(val)","actionRequestCd": "(val)","docName": "(val)","delegationType": "(val)","roleName": "(val)","documentId": "(val)","id": "(val)","requestLabel": "(val)","dateAssigned": "(val)","actionRequestId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Outbox Items by Key [DELETE /research-sys/api/v1/outbox-items/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Outbox Items [DELETE /research-sys/api/v1/outbox-items/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Outbox Items with Matching [DELETE /research-sys/api/v1/outbox-items/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + docHandlerURL
            + groupId
            + delegatorGroupId
            + docLabel
            + responsibilityId
            + docTitle
            + principalId
            + delegatorPrincipalId
            + actionRequestCd
            + docName
            + delegationType
            + roleName
            + documentId
            + id
            + requestLabel
            + dateAssigned
            + actionRequestId

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
