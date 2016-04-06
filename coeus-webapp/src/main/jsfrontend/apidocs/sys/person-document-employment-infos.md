## Person Document Employment Infos [/research-sys/api/v1/person-document-employment-infos/]

### Get Person Document Employment Infos by Key [GET /research-sys/api/v1/person-document-employment-infos/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"entityEmploymentId": "(val)","entityAffiliationId": "(val)","employmentStatusCode": "(val)","employmentTypeCode": "(val)","primaryDepartmentCode": "(val)","baseSalaryAmount": "(val)","employeeId": "(val)","employmentRecordId": "(val)","primary": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Person Document Employment Infos [GET /research-sys/api/v1/person-document-employment-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"entityEmploymentId": "(val)","entityAffiliationId": "(val)","employmentStatusCode": "(val)","employmentTypeCode": "(val)","primaryDepartmentCode": "(val)","baseSalaryAmount": "(val)","employeeId": "(val)","employmentRecordId": "(val)","primary": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityEmploymentId": "(val)","entityAffiliationId": "(val)","employmentStatusCode": "(val)","employmentTypeCode": "(val)","primaryDepartmentCode": "(val)","baseSalaryAmount": "(val)","employeeId": "(val)","employmentRecordId": "(val)","primary": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Document Employment Infos with Filtering [GET /research-sys/api/v1/person-document-employment-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + entityEmploymentId
            + entityAffiliationId
            + employmentStatusCode
            + employmentTypeCode
            + primaryDepartmentCode
            + baseSalaryAmount
            + employeeId
            + employmentRecordId
            + primary
            + edit
            + documentNumber
            + active
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"entityEmploymentId": "(val)","entityAffiliationId": "(val)","employmentStatusCode": "(val)","employmentTypeCode": "(val)","primaryDepartmentCode": "(val)","baseSalaryAmount": "(val)","employeeId": "(val)","employmentRecordId": "(val)","primary": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityEmploymentId": "(val)","entityAffiliationId": "(val)","employmentStatusCode": "(val)","employmentTypeCode": "(val)","primaryDepartmentCode": "(val)","baseSalaryAmount": "(val)","employeeId": "(val)","employmentRecordId": "(val)","primary": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Document Employment Infos [GET /research-sys/api/v1/person-document-employment-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters

            + _schema (required) - will instruct the endpoint to return a schema data structure for the resource

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleSchema}
		
### Get Blueprint API specification for Person Document Employment Infos [GET /research-sys/api/v1/person-document-employment-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Document Employment Infos.md"
            transfer-encoding:chunked


### Update Person Document Employment Infos [PUT /research-sys/api/v1/person-document-employment-infos/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityEmploymentId": "(val)","entityAffiliationId": "(val)","employmentStatusCode": "(val)","employmentTypeCode": "(val)","primaryDepartmentCode": "(val)","baseSalaryAmount": "(val)","employeeId": "(val)","employmentRecordId": "(val)","primary": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Document Employment Infos [PUT /research-sys/api/v1/person-document-employment-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityEmploymentId": "(val)","entityAffiliationId": "(val)","employmentStatusCode": "(val)","employmentTypeCode": "(val)","primaryDepartmentCode": "(val)","baseSalaryAmount": "(val)","employeeId": "(val)","employmentRecordId": "(val)","primary": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityEmploymentId": "(val)","entityAffiliationId": "(val)","employmentStatusCode": "(val)","employmentTypeCode": "(val)","primaryDepartmentCode": "(val)","baseSalaryAmount": "(val)","employeeId": "(val)","employmentRecordId": "(val)","primary": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Person Document Employment Infos [POST /research-sys/api/v1/person-document-employment-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityEmploymentId": "(val)","entityAffiliationId": "(val)","employmentStatusCode": "(val)","employmentTypeCode": "(val)","primaryDepartmentCode": "(val)","baseSalaryAmount": "(val)","employeeId": "(val)","employmentRecordId": "(val)","primary": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"entityEmploymentId": "(val)","entityAffiliationId": "(val)","employmentStatusCode": "(val)","employmentTypeCode": "(val)","primaryDepartmentCode": "(val)","baseSalaryAmount": "(val)","employeeId": "(val)","employmentRecordId": "(val)","primary": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Document Employment Infos [POST /research-sys/api/v1/person-document-employment-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityEmploymentId": "(val)","entityAffiliationId": "(val)","employmentStatusCode": "(val)","employmentTypeCode": "(val)","primaryDepartmentCode": "(val)","baseSalaryAmount": "(val)","employeeId": "(val)","employmentRecordId": "(val)","primary": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityEmploymentId": "(val)","entityAffiliationId": "(val)","employmentStatusCode": "(val)","employmentTypeCode": "(val)","primaryDepartmentCode": "(val)","baseSalaryAmount": "(val)","employeeId": "(val)","employmentRecordId": "(val)","primary": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"entityEmploymentId": "(val)","entityAffiliationId": "(val)","employmentStatusCode": "(val)","employmentTypeCode": "(val)","primaryDepartmentCode": "(val)","baseSalaryAmount": "(val)","employeeId": "(val)","employmentRecordId": "(val)","primary": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityEmploymentId": "(val)","entityAffiliationId": "(val)","employmentStatusCode": "(val)","employmentTypeCode": "(val)","primaryDepartmentCode": "(val)","baseSalaryAmount": "(val)","employeeId": "(val)","employmentRecordId": "(val)","primary": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Person Document Employment Infos by Key [DELETE /research-sys/api/v1/person-document-employment-infos/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Employment Infos [DELETE /research-sys/api/v1/person-document-employment-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Person Document Employment Infos with Matching [DELETE /research-sys/api/v1/person-document-employment-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + entityEmploymentId
            + entityAffiliationId
            + employmentStatusCode
            + employmentTypeCode
            + primaryDepartmentCode
            + baseSalaryAmount
            + employeeId
            + employmentRecordId
            + primary
            + edit
            + documentNumber
            + active


+ Response 204
