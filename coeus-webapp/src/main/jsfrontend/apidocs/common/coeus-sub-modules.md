## Coeus Sub Modules [/research-sys/api/v1/coeus-sub-modules/]

### Get Coeus Sub Modules by Key [GET /research-sys/api/v1/coeus-sub-modules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"coeusSubModuleId": "(val)","moduleCode": "(val)","subModuleCode": "(val)","description": "(val)","requireUniqueQuestionnareUsage": "(val)","_primaryKey": "(val)"}

### Get All Coeus Sub Modules [GET /research-sys/api/v1/coeus-sub-modules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"coeusSubModuleId": "(val)","moduleCode": "(val)","subModuleCode": "(val)","description": "(val)","requireUniqueQuestionnareUsage": "(val)","_primaryKey": "(val)"},
              {"coeusSubModuleId": "(val)","moduleCode": "(val)","subModuleCode": "(val)","description": "(val)","requireUniqueQuestionnareUsage": "(val)","_primaryKey": "(val)"}
            ]

### Get All Coeus Sub Modules with Filtering [GET /research-sys/api/v1/coeus-sub-modules/]
    
+ Parameters

        + coeusSubModuleId
            + moduleCode
            + subModuleCode
            + description
            + requireUniqueQuestionnareUsage

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"coeusSubModuleId": "(val)","moduleCode": "(val)","subModuleCode": "(val)","description": "(val)","requireUniqueQuestionnareUsage": "(val)","_primaryKey": "(val)"},
              {"coeusSubModuleId": "(val)","moduleCode": "(val)","subModuleCode": "(val)","description": "(val)","requireUniqueQuestionnareUsage": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Coeus Sub Modules [GET /research-sys/api/v1/coeus-sub-modules/]
	                                          
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
    
            {"columns":["coeusSubModuleId","moduleCode","subModuleCode","description","requireUniqueQuestionnareUsage"],"primaryKey":"coeusSubModuleId"}
		
### Get Blueprint API specification for Coeus Sub Modules [GET /research-sys/api/v1/coeus-sub-modules/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Coeus Sub Modules.md"
            transfer-encoding:chunked


### Update Coeus Sub Modules [PUT /research-sys/api/v1/coeus-sub-modules/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"coeusSubModuleId": "(val)","moduleCode": "(val)","subModuleCode": "(val)","description": "(val)","requireUniqueQuestionnareUsage": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Coeus Sub Modules [PUT /research-sys/api/v1/coeus-sub-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"coeusSubModuleId": "(val)","moduleCode": "(val)","subModuleCode": "(val)","description": "(val)","requireUniqueQuestionnareUsage": "(val)","_primaryKey": "(val)"},
              {"coeusSubModuleId": "(val)","moduleCode": "(val)","subModuleCode": "(val)","description": "(val)","requireUniqueQuestionnareUsage": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Coeus Sub Modules [POST /research-sys/api/v1/coeus-sub-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"coeusSubModuleId": "(val)","moduleCode": "(val)","subModuleCode": "(val)","description": "(val)","requireUniqueQuestionnareUsage": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"coeusSubModuleId": "(val)","moduleCode": "(val)","subModuleCode": "(val)","description": "(val)","requireUniqueQuestionnareUsage": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Coeus Sub Modules [POST /research-sys/api/v1/coeus-sub-modules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"coeusSubModuleId": "(val)","moduleCode": "(val)","subModuleCode": "(val)","description": "(val)","requireUniqueQuestionnareUsage": "(val)","_primaryKey": "(val)"},
              {"coeusSubModuleId": "(val)","moduleCode": "(val)","subModuleCode": "(val)","description": "(val)","requireUniqueQuestionnareUsage": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"coeusSubModuleId": "(val)","moduleCode": "(val)","subModuleCode": "(val)","description": "(val)","requireUniqueQuestionnareUsage": "(val)","_primaryKey": "(val)"},
              {"coeusSubModuleId": "(val)","moduleCode": "(val)","subModuleCode": "(val)","description": "(val)","requireUniqueQuestionnareUsage": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Coeus Sub Modules by Key [DELETE /research-sys/api/v1/coeus-sub-modules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Coeus Sub Modules [DELETE /research-sys/api/v1/coeus-sub-modules/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Coeus Sub Modules with Matching [DELETE /research-sys/api/v1/coeus-sub-modules/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + coeusSubModuleId
            + moduleCode
            + subModuleCode
            + description
            + requireUniqueQuestionnareUsage

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
