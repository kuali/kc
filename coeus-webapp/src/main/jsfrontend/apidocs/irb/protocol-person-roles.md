## Protocol Person Roles [/irb/api/v1/protocol-person-roles/]

### Get Protocol Person Roles by Key [GET /irb/api/v1/protocol-person-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Protocol Person Roles [GET /irb/api/v1/protocol-person-roles/]
	 
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

### Get All Protocol Person Roles with Filtering [GET /irb/api/v1/protocol-person-roles/]
    
+ Parameters

    + protocolPersonRoleId (optional) - Protocol Person Role Id. Maximum length is 12.
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
			
### Get Schema for Protocol Person Roles [GET /irb/api/v1/protocol-person-roles/]
	                                          
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
		
### Get Blueprint API specification for Protocol Person Roles [GET /irb/api/v1/protocol-person-roles/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Person Roles.md"
            transfer-encoding:chunked
### Update Protocol Person Roles [PUT /irb/api/v1/protocol-person-roles/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Person Roles [PUT /irb/api/v1/protocol-person-roles/]

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
### Insert Protocol Person Roles [POST /irb/api/v1/protocol-person-roles/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolPersonRoleId": "(val)","description": "(val)","unitDetailsRequired": "(val)","affiliationDetailsRequired": "(val)","trainingDetailsRequired": "(val)","commentsDetailsRequired": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Person Roles [POST /irb/api/v1/protocol-person-roles/]

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
### Delete Protocol Person Roles by Key [DELETE /irb/api/v1/protocol-person-roles/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Person Roles [DELETE /irb/api/v1/protocol-person-roles/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Person Roles with Matching [DELETE /irb/api/v1/protocol-person-roles/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + protocolPersonRoleId (optional) - Protocol Person Role Id. Maximum length is 12.
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
