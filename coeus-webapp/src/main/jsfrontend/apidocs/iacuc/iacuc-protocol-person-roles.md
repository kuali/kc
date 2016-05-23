## Iacuc Protocol Person Roles [/iacuc/api/v1/iacuc-protocol-person-roles/]

### Get Iacuc Protocol Person Roles by Key [GET /iacuc/api/v1/iacuc-protocol-person-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Person Roles [GET /iacuc/api/v1/iacuc-protocol-person-roles/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Person Roles with Filtering [GET /iacuc/api/v1/iacuc-protocol-person-roles/]
    
+ Parameters

    + protocolPersonRoleId (optional) - IACUC Protocol Person Role Id. Maximum length is 12.
    + description (optional) - Description. Maximum length is 250.
    + unitDetailsRequired (optional) - Unit Details Required. Maximum length is 1.
    + affiliationDetailsRequired (optional) - Affiliation Details Required. Maximum length is 1.
    + trainingDetailsRequired (optional) - Training Details Required. Maximum length is 1.
    + commentsDetailsRequired (optional) - Comments Details Required. Maximum length is 1.
    + active (optional) - Active. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Person Roles [GET /iacuc/api/v1/iacuc-protocol-person-roles/]
	                                          
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
    
            {"columns":["protocolPersonRoleId","description","unitDetailsRequired","affiliationDetailsRequired","trainingDetailsRequired","commentsDetailsRequired","active"],"primaryKey":"protocolPersonRoleId"}
		
### Get Blueprint API specification for Iacuc Protocol Person Roles [GET /iacuc/api/v1/iacuc-protocol-person-roles/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Person Roles.md"
            transfer-encoding:chunked
### Update Iacuc Protocol Person Roles [PUT /iacuc/api/v1/iacuc-protocol-person-roles/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Person Roles [PUT /iacuc/api/v1/iacuc-protocol-person-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Iacuc Protocol Person Roles [POST /iacuc/api/v1/iacuc-protocol-person-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Person Roles [POST /iacuc/api/v1/iacuc-protocol-person-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
### Delete Iacuc Protocol Person Roles by Key [DELETE /iacuc/api/v1/iacuc-protocol-person-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Person Roles [DELETE /iacuc/api/v1/iacuc-protocol-person-roles/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Person Roles with Matching [DELETE /iacuc/api/v1/iacuc-protocol-person-roles/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolPersonRoleId (optional) - IACUC Protocol Person Role Id. Maximum length is 12.
    + description (optional) - Description. Maximum length is 250.
    + unitDetailsRequired (optional) - Unit Details Required. Maximum length is 1.
    + affiliationDetailsRequired (optional) - Affiliation Details Required. Maximum length is 1.
    + trainingDetailsRequired (optional) - Training Details Required. Maximum length is 1.
    + commentsDetailsRequired (optional) - Comments Details Required. Maximum length is 1.
    + active (optional) - Active. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
