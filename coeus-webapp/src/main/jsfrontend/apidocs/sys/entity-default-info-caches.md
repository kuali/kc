## Entity Default Info Caches [/research-sys/api/v1/entity-default-info-caches/]

### Get Entity Default Info Caches by Key [GET /research-sys/api/v1/entity-default-info-caches/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"principalId": "(val)","principalName": "(val)","entityId": "(val)","entityTypeCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","name": "(val)","campusCode": "(val)","primaryDepartmentCode": "(val)","employeeId": "(val)","lastUpdateTimestamp": "(val)","_primaryKey": "(val)"}

### Get All Entity Default Info Caches [GET /research-sys/api/v1/entity-default-info-caches/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","entityTypeCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","name": "(val)","campusCode": "(val)","primaryDepartmentCode": "(val)","employeeId": "(val)","lastUpdateTimestamp": "(val)","_primaryKey": "(val)"},
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","entityTypeCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","name": "(val)","campusCode": "(val)","primaryDepartmentCode": "(val)","employeeId": "(val)","lastUpdateTimestamp": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Default Info Caches with Filtering [GET /research-sys/api/v1/entity-default-info-caches/]
    
+ Parameters

        + principalId
            + principalName
            + entityId
            + entityTypeCode
            + firstName
            + middleName
            + lastName
            + name
            + campusCode
            + primaryDepartmentCode
            + employeeId
            + lastUpdateTimestamp

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","entityTypeCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","name": "(val)","campusCode": "(val)","primaryDepartmentCode": "(val)","employeeId": "(val)","lastUpdateTimestamp": "(val)","_primaryKey": "(val)"},
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","entityTypeCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","name": "(val)","campusCode": "(val)","primaryDepartmentCode": "(val)","employeeId": "(val)","lastUpdateTimestamp": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Default Info Caches [GET /research-sys/api/v1/entity-default-info-caches/]
	                                          
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
    
            {"columns":["principalId","principalName","entityId","entityTypeCode","firstName","middleName","lastName","name","campusCode","primaryDepartmentCode","employeeId","lastUpdateTimestamp"],"primaryKey":"principalId"}
		
### Get Blueprint API specification for Entity Default Info Caches [GET /research-sys/api/v1/entity-default-info-caches/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Default Info Caches.md"
            transfer-encoding:chunked


### Update Entity Default Info Caches [PUT /research-sys/api/v1/entity-default-info-caches/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"principalId": "(val)","principalName": "(val)","entityId": "(val)","entityTypeCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","name": "(val)","campusCode": "(val)","primaryDepartmentCode": "(val)","employeeId": "(val)","lastUpdateTimestamp": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Default Info Caches [PUT /research-sys/api/v1/entity-default-info-caches/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","entityTypeCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","name": "(val)","campusCode": "(val)","primaryDepartmentCode": "(val)","employeeId": "(val)","lastUpdateTimestamp": "(val)","_primaryKey": "(val)"},
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","entityTypeCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","name": "(val)","campusCode": "(val)","primaryDepartmentCode": "(val)","employeeId": "(val)","lastUpdateTimestamp": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Entity Default Info Caches [POST /research-sys/api/v1/entity-default-info-caches/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"principalId": "(val)","principalName": "(val)","entityId": "(val)","entityTypeCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","name": "(val)","campusCode": "(val)","primaryDepartmentCode": "(val)","employeeId": "(val)","lastUpdateTimestamp": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"principalId": "(val)","principalName": "(val)","entityId": "(val)","entityTypeCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","name": "(val)","campusCode": "(val)","primaryDepartmentCode": "(val)","employeeId": "(val)","lastUpdateTimestamp": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Default Info Caches [POST /research-sys/api/v1/entity-default-info-caches/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","entityTypeCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","name": "(val)","campusCode": "(val)","primaryDepartmentCode": "(val)","employeeId": "(val)","lastUpdateTimestamp": "(val)","_primaryKey": "(val)"},
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","entityTypeCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","name": "(val)","campusCode": "(val)","primaryDepartmentCode": "(val)","employeeId": "(val)","lastUpdateTimestamp": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","entityTypeCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","name": "(val)","campusCode": "(val)","primaryDepartmentCode": "(val)","employeeId": "(val)","lastUpdateTimestamp": "(val)","_primaryKey": "(val)"},
              {"principalId": "(val)","principalName": "(val)","entityId": "(val)","entityTypeCode": "(val)","firstName": "(val)","middleName": "(val)","lastName": "(val)","name": "(val)","campusCode": "(val)","primaryDepartmentCode": "(val)","employeeId": "(val)","lastUpdateTimestamp": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Entity Default Info Caches by Key [DELETE /research-sys/api/v1/entity-default-info-caches/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Default Info Caches [DELETE /research-sys/api/v1/entity-default-info-caches/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Default Info Caches with Matching [DELETE /research-sys/api/v1/entity-default-info-caches/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + principalId
            + principalName
            + entityId
            + entityTypeCode
            + firstName
            + middleName
            + lastName
            + name
            + campusCode
            + primaryDepartmentCode
            + employeeId
            + lastUpdateTimestamp

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
