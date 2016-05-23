## Activity Types [/research-common/api/v1/activity-types/]

### Get Activity Types by Key [GET /research-common/api/v1/activity-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}

### Get All Activity Types [GET /research-common/api/v1/activity-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Activity Types with Filtering [GET /research-common/api/v1/activity-types/]
    
+ Parameters

    + code (optional) - Activity Type. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + higherEducationFunctionCode (optional) - Higher education function code. Maximum length is 20.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Activity Types [GET /research-common/api/v1/activity-types/]
	                                          
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
    
            {"columns":["code","description","higherEducationFunctionCode"],"primaryKey":"code"}
		
### Get Blueprint API specification for Activity Types [GET /research-common/api/v1/activity-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Activity Types.md"
            transfer-encoding:chunked
### Update Activity Types [PUT /research-common/api/v1/activity-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Activity Types [PUT /research-common/api/v1/activity-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Activity Types [POST /research-common/api/v1/activity-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Activity Types [POST /research-common/api/v1/activity-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"},
              {"code": "(val)","description": "(val)","higherEducationFunctionCode": "(val)","_primaryKey": "(val)"}
            ]
### Delete Activity Types by Key [DELETE /research-common/api/v1/activity-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Activity Types [DELETE /research-common/api/v1/activity-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Activity Types with Matching [DELETE /research-common/api/v1/activity-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + code (optional) - Activity Type. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.
    + higherEducationFunctionCode (optional) - Higher education function code. Maximum length is 20.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
