## Unit Correspondents [/research-common/api/v1/unit-correspondents/]

### Get Unit Correspondents by Key [GET /research-common/api/v1/unit-correspondents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Unit Correspondents [GET /research-common/api/v1/unit-correspondents/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Unit Correspondents with Filtering [GET /research-common/api/v1/unit-correspondents/]
    
+ Parameters

    + correspondentId (optional) - 
    + unitNumber (optional) - Unit Number. Maximum length is 8.
    + correspondentTypeCode (optional) - Correspondent Type Code. Maximum length is 22.
    + personId (optional) - This is a generic implementation of a 'SystemId' attribute. It should always be overriden on the label, shortLabel, summary, and description, as these are only generic placeholders. Maximum length is 40.
    + nonEmployeeFlag (optional) - Non Employee Flag. Maximum length is 1.
    + description (optional) - Description. Maximum length is 2000.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Unit Correspondents [GET /research-common/api/v1/unit-correspondents/]
	                                          
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
    
            {"columns":["correspondentId","unitNumber","correspondentTypeCode","personId","nonEmployeeFlag","description"],"primaryKey":"correspondentId"}
		
### Get Blueprint API specification for Unit Correspondents [GET /research-common/api/v1/unit-correspondents/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Unit Correspondents.md"
            transfer-encoding:chunked
### Update Unit Correspondents [PUT /research-common/api/v1/unit-correspondents/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Unit Correspondents [PUT /research-common/api/v1/unit-correspondents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Unit Correspondents [POST /research-common/api/v1/unit-correspondents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Unit Correspondents [POST /research-common/api/v1/unit-correspondents/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"correspondentId": "(val)","unitNumber": "(val)","correspondentTypeCode": "(val)","personId": "(val)","nonEmployeeFlag": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Unit Correspondents by Key [DELETE /research-common/api/v1/unit-correspondents/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Unit Correspondents [DELETE /research-common/api/v1/unit-correspondents/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Unit Correspondents with Matching [DELETE /research-common/api/v1/unit-correspondents/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + correspondentId (optional) - 
    + unitNumber (optional) - Unit Number. Maximum length is 8.
    + correspondentTypeCode (optional) - Correspondent Type Code. Maximum length is 22.
    + personId (optional) - This is a generic implementation of a 'SystemId' attribute. It should always be overriden on the label, shortLabel, summary, and description, as these are only generic placeholders. Maximum length is 40.
    + nonEmployeeFlag (optional) - Non Employee Flag. Maximum length is 1.
    + description (optional) - Description. Maximum length is 2000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
