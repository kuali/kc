## Entity Employments [/research-sys/api/v1/entity-employments/]

### Get Entity Employments by Key [GET /research-sys/api/v1/entity-employments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","primaryDepartmentCode": "(val)","active": "(val)","employeeId": "(val)","entityId": "(val)","entityAffiliationId": "(val)","employeeStatusCode": "(val)","employeeTypeCode": "(val)","employmentRecordId": "(val)","baseSalaryAmount": "(val)","primary": "(val)","_primaryKey": "(val)"}

### Get All Entity Employments [GET /research-sys/api/v1/entity-employments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","primaryDepartmentCode": "(val)","active": "(val)","employeeId": "(val)","entityId": "(val)","entityAffiliationId": "(val)","employeeStatusCode": "(val)","employeeTypeCode": "(val)","employmentRecordId": "(val)","baseSalaryAmount": "(val)","primary": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","primaryDepartmentCode": "(val)","active": "(val)","employeeId": "(val)","entityId": "(val)","entityAffiliationId": "(val)","employeeStatusCode": "(val)","employeeTypeCode": "(val)","employmentRecordId": "(val)","baseSalaryAmount": "(val)","primary": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Employments with Filtering [GET /research-sys/api/v1/entity-employments/]
    
+ Parameters

    + id (optional) - 
    + primaryDepartmentCode (optional) - 
    + active (optional) - 
    + employeeId (optional) - 
    + entityId (optional) - 
    + entityAffiliationId (optional) - 
    + employeeStatusCode (optional) - 
    + employeeTypeCode (optional) - 
    + employmentRecordId (optional) - 
    + baseSalaryAmount (optional) - 
    + primary (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","primaryDepartmentCode": "(val)","active": "(val)","employeeId": "(val)","entityId": "(val)","entityAffiliationId": "(val)","employeeStatusCode": "(val)","employeeTypeCode": "(val)","employmentRecordId": "(val)","baseSalaryAmount": "(val)","primary": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","primaryDepartmentCode": "(val)","active": "(val)","employeeId": "(val)","entityId": "(val)","entityAffiliationId": "(val)","employeeStatusCode": "(val)","employeeTypeCode": "(val)","employmentRecordId": "(val)","baseSalaryAmount": "(val)","primary": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Employments [GET /research-sys/api/v1/entity-employments/]
	                                          
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
    
            {"columns":["id","primaryDepartmentCode","active","employeeId","entityId","entityAffiliationId","employeeStatusCode","employeeTypeCode","employmentRecordId","baseSalaryAmount","primary"],"primaryKey":"id"}
		
### Get Blueprint API specification for Entity Employments [GET /research-sys/api/v1/entity-employments/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Employments.md"
            transfer-encoding:chunked


### Update Entity Employments [PUT /research-sys/api/v1/entity-employments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","primaryDepartmentCode": "(val)","active": "(val)","employeeId": "(val)","entityId": "(val)","entityAffiliationId": "(val)","employeeStatusCode": "(val)","employeeTypeCode": "(val)","employmentRecordId": "(val)","baseSalaryAmount": "(val)","primary": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Employments [PUT /research-sys/api/v1/entity-employments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","primaryDepartmentCode": "(val)","active": "(val)","employeeId": "(val)","entityId": "(val)","entityAffiliationId": "(val)","employeeStatusCode": "(val)","employeeTypeCode": "(val)","employmentRecordId": "(val)","baseSalaryAmount": "(val)","primary": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","primaryDepartmentCode": "(val)","active": "(val)","employeeId": "(val)","entityId": "(val)","entityAffiliationId": "(val)","employeeStatusCode": "(val)","employeeTypeCode": "(val)","employmentRecordId": "(val)","baseSalaryAmount": "(val)","primary": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Entity Employments [POST /research-sys/api/v1/entity-employments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","primaryDepartmentCode": "(val)","active": "(val)","employeeId": "(val)","entityId": "(val)","entityAffiliationId": "(val)","employeeStatusCode": "(val)","employeeTypeCode": "(val)","employmentRecordId": "(val)","baseSalaryAmount": "(val)","primary": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","primaryDepartmentCode": "(val)","active": "(val)","employeeId": "(val)","entityId": "(val)","entityAffiliationId": "(val)","employeeStatusCode": "(val)","employeeTypeCode": "(val)","employmentRecordId": "(val)","baseSalaryAmount": "(val)","primary": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Employments [POST /research-sys/api/v1/entity-employments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","primaryDepartmentCode": "(val)","active": "(val)","employeeId": "(val)","entityId": "(val)","entityAffiliationId": "(val)","employeeStatusCode": "(val)","employeeTypeCode": "(val)","employmentRecordId": "(val)","baseSalaryAmount": "(val)","primary": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","primaryDepartmentCode": "(val)","active": "(val)","employeeId": "(val)","entityId": "(val)","entityAffiliationId": "(val)","employeeStatusCode": "(val)","employeeTypeCode": "(val)","employmentRecordId": "(val)","baseSalaryAmount": "(val)","primary": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","primaryDepartmentCode": "(val)","active": "(val)","employeeId": "(val)","entityId": "(val)","entityAffiliationId": "(val)","employeeStatusCode": "(val)","employeeTypeCode": "(val)","employmentRecordId": "(val)","baseSalaryAmount": "(val)","primary": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","primaryDepartmentCode": "(val)","active": "(val)","employeeId": "(val)","entityId": "(val)","entityAffiliationId": "(val)","employeeStatusCode": "(val)","employeeTypeCode": "(val)","employmentRecordId": "(val)","baseSalaryAmount": "(val)","primary": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Entity Employments by Key [DELETE /research-sys/api/v1/entity-employments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Employments [DELETE /research-sys/api/v1/entity-employments/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Employments with Matching [DELETE /research-sys/api/v1/entity-employments/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - 
    + primaryDepartmentCode (optional) - 
    + active (optional) - 
    + employeeId (optional) - 
    + entityId (optional) - 
    + entityAffiliationId (optional) - 
    + employeeStatusCode (optional) - 
    + employeeTypeCode (optional) - 
    + employmentRecordId (optional) - 
    + baseSalaryAmount (optional) - 
    + primary (optional) - 

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
