## Action Request Values [/research-sys/api/v1/action-request-values/]

### Get Action Request Values by Key [GET /research-sys/api/v1/action-request-values/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"actionRequestId": "(val)","actionRequested": "(val)","documentId": "(val)","ruleBaseValuesId": "(val)","status": "(val)","responsibilityId": "(val)","groupId": "(val)","roleName": "(val)","qualifiedRoleName": "(val)","qualifiedRoleNameLabel": "(val)","recipientTypeCd": "(val)","priority": "(val)","routeLevel": "(val)","docVersion": "(val)","createDate": "(val)","responsibilityDesc": "(val)","annotation": "(val)","jrfVerNbr": "(val)","principalId": "(val)","forceAction": "(val)","currentIndicator": "(val)","approvePolicy": "(val)","delegationTypeCode": "(val)","requestLabel": "(val)","_primaryKey": "(val)"}

### Get All Action Request Values [GET /research-sys/api/v1/action-request-values/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"actionRequestId": "(val)","actionRequested": "(val)","documentId": "(val)","ruleBaseValuesId": "(val)","status": "(val)","responsibilityId": "(val)","groupId": "(val)","roleName": "(val)","qualifiedRoleName": "(val)","qualifiedRoleNameLabel": "(val)","recipientTypeCd": "(val)","priority": "(val)","routeLevel": "(val)","docVersion": "(val)","createDate": "(val)","responsibilityDesc": "(val)","annotation": "(val)","jrfVerNbr": "(val)","principalId": "(val)","forceAction": "(val)","currentIndicator": "(val)","approvePolicy": "(val)","delegationTypeCode": "(val)","requestLabel": "(val)","_primaryKey": "(val)"},
              {"actionRequestId": "(val)","actionRequested": "(val)","documentId": "(val)","ruleBaseValuesId": "(val)","status": "(val)","responsibilityId": "(val)","groupId": "(val)","roleName": "(val)","qualifiedRoleName": "(val)","qualifiedRoleNameLabel": "(val)","recipientTypeCd": "(val)","priority": "(val)","routeLevel": "(val)","docVersion": "(val)","createDate": "(val)","responsibilityDesc": "(val)","annotation": "(val)","jrfVerNbr": "(val)","principalId": "(val)","forceAction": "(val)","currentIndicator": "(val)","approvePolicy": "(val)","delegationTypeCode": "(val)","requestLabel": "(val)","_primaryKey": "(val)"}
            ]

### Get All Action Request Values with Filtering [GET /research-sys/api/v1/action-request-values/]
    
+ Parameters

        + actionRequestId
            + actionRequested
            + documentId
            + ruleBaseValuesId
            + status
            + responsibilityId
            + groupId
            + roleName
            + qualifiedRoleName
            + qualifiedRoleNameLabel
            + recipientTypeCd
            + priority
            + routeLevel
            + docVersion
            + createDate
            + responsibilityDesc
            + annotation
            + jrfVerNbr
            + principalId
            + forceAction
            + currentIndicator
            + approvePolicy
            + delegationTypeCode
            + requestLabel

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"actionRequestId": "(val)","actionRequested": "(val)","documentId": "(val)","ruleBaseValuesId": "(val)","status": "(val)","responsibilityId": "(val)","groupId": "(val)","roleName": "(val)","qualifiedRoleName": "(val)","qualifiedRoleNameLabel": "(val)","recipientTypeCd": "(val)","priority": "(val)","routeLevel": "(val)","docVersion": "(val)","createDate": "(val)","responsibilityDesc": "(val)","annotation": "(val)","jrfVerNbr": "(val)","principalId": "(val)","forceAction": "(val)","currentIndicator": "(val)","approvePolicy": "(val)","delegationTypeCode": "(val)","requestLabel": "(val)","_primaryKey": "(val)"},
              {"actionRequestId": "(val)","actionRequested": "(val)","documentId": "(val)","ruleBaseValuesId": "(val)","status": "(val)","responsibilityId": "(val)","groupId": "(val)","roleName": "(val)","qualifiedRoleName": "(val)","qualifiedRoleNameLabel": "(val)","recipientTypeCd": "(val)","priority": "(val)","routeLevel": "(val)","docVersion": "(val)","createDate": "(val)","responsibilityDesc": "(val)","annotation": "(val)","jrfVerNbr": "(val)","principalId": "(val)","forceAction": "(val)","currentIndicator": "(val)","approvePolicy": "(val)","delegationTypeCode": "(val)","requestLabel": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Action Request Values [GET /research-sys/api/v1/action-request-values/]
	                                          
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
    
            {"columns":["actionRequestId","actionRequested","documentId","ruleBaseValuesId","status","responsibilityId","groupId","roleName","qualifiedRoleName","qualifiedRoleNameLabel","recipientTypeCd","priority","routeLevel","docVersion","createDate","responsibilityDesc","annotation","jrfVerNbr","principalId","forceAction","currentIndicator","approvePolicy","delegationTypeCode","requestLabel"],"primaryKey":"actionRequestId"}
		
### Get Blueprint API specification for Action Request Values [GET /research-sys/api/v1/action-request-values/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Action Request Values.md"
            transfer-encoding:chunked


### Update Action Request Values [PUT /research-sys/api/v1/action-request-values/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"actionRequestId": "(val)","actionRequested": "(val)","documentId": "(val)","ruleBaseValuesId": "(val)","status": "(val)","responsibilityId": "(val)","groupId": "(val)","roleName": "(val)","qualifiedRoleName": "(val)","qualifiedRoleNameLabel": "(val)","recipientTypeCd": "(val)","priority": "(val)","routeLevel": "(val)","docVersion": "(val)","createDate": "(val)","responsibilityDesc": "(val)","annotation": "(val)","jrfVerNbr": "(val)","principalId": "(val)","forceAction": "(val)","currentIndicator": "(val)","approvePolicy": "(val)","delegationTypeCode": "(val)","requestLabel": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Action Request Values [PUT /research-sys/api/v1/action-request-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"actionRequestId": "(val)","actionRequested": "(val)","documentId": "(val)","ruleBaseValuesId": "(val)","status": "(val)","responsibilityId": "(val)","groupId": "(val)","roleName": "(val)","qualifiedRoleName": "(val)","qualifiedRoleNameLabel": "(val)","recipientTypeCd": "(val)","priority": "(val)","routeLevel": "(val)","docVersion": "(val)","createDate": "(val)","responsibilityDesc": "(val)","annotation": "(val)","jrfVerNbr": "(val)","principalId": "(val)","forceAction": "(val)","currentIndicator": "(val)","approvePolicy": "(val)","delegationTypeCode": "(val)","requestLabel": "(val)","_primaryKey": "(val)"},
              {"actionRequestId": "(val)","actionRequested": "(val)","documentId": "(val)","ruleBaseValuesId": "(val)","status": "(val)","responsibilityId": "(val)","groupId": "(val)","roleName": "(val)","qualifiedRoleName": "(val)","qualifiedRoleNameLabel": "(val)","recipientTypeCd": "(val)","priority": "(val)","routeLevel": "(val)","docVersion": "(val)","createDate": "(val)","responsibilityDesc": "(val)","annotation": "(val)","jrfVerNbr": "(val)","principalId": "(val)","forceAction": "(val)","currentIndicator": "(val)","approvePolicy": "(val)","delegationTypeCode": "(val)","requestLabel": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Action Request Values [POST /research-sys/api/v1/action-request-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"actionRequestId": "(val)","actionRequested": "(val)","documentId": "(val)","ruleBaseValuesId": "(val)","status": "(val)","responsibilityId": "(val)","groupId": "(val)","roleName": "(val)","qualifiedRoleName": "(val)","qualifiedRoleNameLabel": "(val)","recipientTypeCd": "(val)","priority": "(val)","routeLevel": "(val)","docVersion": "(val)","createDate": "(val)","responsibilityDesc": "(val)","annotation": "(val)","jrfVerNbr": "(val)","principalId": "(val)","forceAction": "(val)","currentIndicator": "(val)","approvePolicy": "(val)","delegationTypeCode": "(val)","requestLabel": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"actionRequestId": "(val)","actionRequested": "(val)","documentId": "(val)","ruleBaseValuesId": "(val)","status": "(val)","responsibilityId": "(val)","groupId": "(val)","roleName": "(val)","qualifiedRoleName": "(val)","qualifiedRoleNameLabel": "(val)","recipientTypeCd": "(val)","priority": "(val)","routeLevel": "(val)","docVersion": "(val)","createDate": "(val)","responsibilityDesc": "(val)","annotation": "(val)","jrfVerNbr": "(val)","principalId": "(val)","forceAction": "(val)","currentIndicator": "(val)","approvePolicy": "(val)","delegationTypeCode": "(val)","requestLabel": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Action Request Values [POST /research-sys/api/v1/action-request-values/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"actionRequestId": "(val)","actionRequested": "(val)","documentId": "(val)","ruleBaseValuesId": "(val)","status": "(val)","responsibilityId": "(val)","groupId": "(val)","roleName": "(val)","qualifiedRoleName": "(val)","qualifiedRoleNameLabel": "(val)","recipientTypeCd": "(val)","priority": "(val)","routeLevel": "(val)","docVersion": "(val)","createDate": "(val)","responsibilityDesc": "(val)","annotation": "(val)","jrfVerNbr": "(val)","principalId": "(val)","forceAction": "(val)","currentIndicator": "(val)","approvePolicy": "(val)","delegationTypeCode": "(val)","requestLabel": "(val)","_primaryKey": "(val)"},
              {"actionRequestId": "(val)","actionRequested": "(val)","documentId": "(val)","ruleBaseValuesId": "(val)","status": "(val)","responsibilityId": "(val)","groupId": "(val)","roleName": "(val)","qualifiedRoleName": "(val)","qualifiedRoleNameLabel": "(val)","recipientTypeCd": "(val)","priority": "(val)","routeLevel": "(val)","docVersion": "(val)","createDate": "(val)","responsibilityDesc": "(val)","annotation": "(val)","jrfVerNbr": "(val)","principalId": "(val)","forceAction": "(val)","currentIndicator": "(val)","approvePolicy": "(val)","delegationTypeCode": "(val)","requestLabel": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"actionRequestId": "(val)","actionRequested": "(val)","documentId": "(val)","ruleBaseValuesId": "(val)","status": "(val)","responsibilityId": "(val)","groupId": "(val)","roleName": "(val)","qualifiedRoleName": "(val)","qualifiedRoleNameLabel": "(val)","recipientTypeCd": "(val)","priority": "(val)","routeLevel": "(val)","docVersion": "(val)","createDate": "(val)","responsibilityDesc": "(val)","annotation": "(val)","jrfVerNbr": "(val)","principalId": "(val)","forceAction": "(val)","currentIndicator": "(val)","approvePolicy": "(val)","delegationTypeCode": "(val)","requestLabel": "(val)","_primaryKey": "(val)"},
              {"actionRequestId": "(val)","actionRequested": "(val)","documentId": "(val)","ruleBaseValuesId": "(val)","status": "(val)","responsibilityId": "(val)","groupId": "(val)","roleName": "(val)","qualifiedRoleName": "(val)","qualifiedRoleNameLabel": "(val)","recipientTypeCd": "(val)","priority": "(val)","routeLevel": "(val)","docVersion": "(val)","createDate": "(val)","responsibilityDesc": "(val)","annotation": "(val)","jrfVerNbr": "(val)","principalId": "(val)","forceAction": "(val)","currentIndicator": "(val)","approvePolicy": "(val)","delegationTypeCode": "(val)","requestLabel": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Action Request Values by Key [DELETE /research-sys/api/v1/action-request-values/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Action Request Values [DELETE /research-sys/api/v1/action-request-values/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Action Request Values with Matching [DELETE /research-sys/api/v1/action-request-values/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + actionRequestId
            + actionRequested
            + documentId
            + ruleBaseValuesId
            + status
            + responsibilityId
            + groupId
            + roleName
            + qualifiedRoleName
            + qualifiedRoleNameLabel
            + recipientTypeCd
            + priority
            + routeLevel
            + docVersion
            + createDate
            + responsibilityDesc
            + annotation
            + jrfVerNbr
            + principalId
            + forceAction
            + currentIndicator
            + approvePolicy
            + delegationTypeCode
            + requestLabel

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
