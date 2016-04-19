## Kim Document Role Responsibility Actions [/research-sys/api/v1/kim-document-role-responsibility-actions/]

### Get Kim Document Role Responsibility Actions by Key [GET /research-sys/api/v1/kim-document-role-responsibility-actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"roleResponsibilityActionId": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","priorityNumber": "(val)","forceAction": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}

### Get All Kim Document Role Responsibility Actions [GET /research-sys/api/v1/kim-document-role-responsibility-actions/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"roleResponsibilityActionId": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","priorityNumber": "(val)","forceAction": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"roleResponsibilityActionId": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","priorityNumber": "(val)","forceAction": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Kim Document Role Responsibility Actions with Filtering [GET /research-sys/api/v1/kim-document-role-responsibility-actions/]
    
+ Parameters

    + roleResponsibilityActionId (optional) - Role Responsibility Action Id. Maximum length is 40.
    + roleResponsibilityId (optional) - Role Responsibility Id. Maximum length is 40.
    + roleMemberId (optional) - Role Member Id. Maximum length is 40.
    + actionTypeCode (optional) - Action Type Code. Maximum length is 40.
    + actionPolicyCode (optional) - Action Policy Code. Maximum length is 40.
    + priorityNumber (optional) - Priority Number. Maximum length is 3.
    + forceAction (optional) - Require Redundant Action. Maximum length is 1.
    + edit (optional) - Edit.
    + documentNumber (optional) - Document Number.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"roleResponsibilityActionId": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","priorityNumber": "(val)","forceAction": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"roleResponsibilityActionId": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","priorityNumber": "(val)","forceAction": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Kim Document Role Responsibility Actions [GET /research-sys/api/v1/kim-document-role-responsibility-actions/]
	                                          
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
    
            {"columns":["roleResponsibilityActionId","roleResponsibilityId","roleMemberId","actionTypeCode","actionPolicyCode","priorityNumber","forceAction","edit","documentNumber"],"primaryKey":"roleResponsibilityActionId"}
		
### Get Blueprint API specification for Kim Document Role Responsibility Actions [GET /research-sys/api/v1/kim-document-role-responsibility-actions/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Kim Document Role Responsibility Actions.md"
            transfer-encoding:chunked


### Update Kim Document Role Responsibility Actions [PUT /research-sys/api/v1/kim-document-role-responsibility-actions/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleResponsibilityActionId": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","priorityNumber": "(val)","forceAction": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Kim Document Role Responsibility Actions [PUT /research-sys/api/v1/kim-document-role-responsibility-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"roleResponsibilityActionId": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","priorityNumber": "(val)","forceAction": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"roleResponsibilityActionId": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","priorityNumber": "(val)","forceAction": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Kim Document Role Responsibility Actions [POST /research-sys/api/v1/kim-document-role-responsibility-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"roleResponsibilityActionId": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","priorityNumber": "(val)","forceAction": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"roleResponsibilityActionId": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","priorityNumber": "(val)","forceAction": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Kim Document Role Responsibility Actions [POST /research-sys/api/v1/kim-document-role-responsibility-actions/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"roleResponsibilityActionId": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","priorityNumber": "(val)","forceAction": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"roleResponsibilityActionId": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","priorityNumber": "(val)","forceAction": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"roleResponsibilityActionId": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","priorityNumber": "(val)","forceAction": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"},
              {"roleResponsibilityActionId": "(val)","roleResponsibilityId": "(val)","roleMemberId": "(val)","actionTypeCode": "(val)","actionPolicyCode": "(val)","priorityNumber": "(val)","forceAction": "(val)","edit": "(val)","documentNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Kim Document Role Responsibility Actions by Key [DELETE /research-sys/api/v1/kim-document-role-responsibility-actions/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kim Document Role Responsibility Actions [DELETE /research-sys/api/v1/kim-document-role-responsibility-actions/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Kim Document Role Responsibility Actions with Matching [DELETE /research-sys/api/v1/kim-document-role-responsibility-actions/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + roleResponsibilityActionId (optional) - Role Responsibility Action Id. Maximum length is 40.
    + roleResponsibilityId (optional) - Role Responsibility Id. Maximum length is 40.
    + roleMemberId (optional) - Role Member Id. Maximum length is 40.
    + actionTypeCode (optional) - Action Type Code. Maximum length is 40.
    + actionPolicyCode (optional) - Action Policy Code. Maximum length is 40.
    + priorityNumber (optional) - Priority Number. Maximum length is 3.
    + forceAction (optional) - Require Redundant Action. Maximum length is 1.
    + edit (optional) - Edit.
    + documentNumber (optional) - Document Number.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
