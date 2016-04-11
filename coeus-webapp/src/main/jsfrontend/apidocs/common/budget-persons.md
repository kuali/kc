## Budget Persons [/research-sys/api/v1/budget-persons/]

### Get Budget Persons by Key [GET /research-sys/api/v1/budget-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}

### Get All Budget Persons [GET /research-sys/api/v1/budget-persons/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Persons with Filtering [GET /research-sys/api/v1/budget-persons/]
    
+ Parameters

        + personSequenceNumber
            + budgetId
            + effectiveDate
            + jobCode
            + nonEmployeeFlag
            + personId
            + rolodexId
            + tbnId
            + appointmentTypeCode
            + calculationBase
            + personName
            + salaryAnniversaryDate
            + hierarchyProposalNumber
            + hiddenInHierarchy

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Persons [GET /research-sys/api/v1/budget-persons/]
	                                          
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
    
            {"columns":["personSequenceNumber","budgetId","effectiveDate","jobCode","nonEmployeeFlag","personId","rolodexId","tbnId","appointmentTypeCode","calculationBase","personName","salaryAnniversaryDate","hierarchyProposalNumber","hiddenInHierarchy"],"primaryKey":"budget:personSequenceNumber"}
		
### Get Blueprint API specification for Budget Persons [GET /research-sys/api/v1/budget-persons/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Persons.md"
            transfer-encoding:chunked


### Update Budget Persons [PUT /research-sys/api/v1/budget-persons/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Persons [PUT /research-sys/api/v1/budget-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Persons [POST /research-sys/api/v1/budget-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Persons [POST /research-sys/api/v1/budget-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"},
              {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Persons by Key [DELETE /research-sys/api/v1/budget-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Persons [DELETE /research-sys/api/v1/budget-persons/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Persons with Matching [DELETE /research-sys/api/v1/budget-persons/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + personSequenceNumber
            + budgetId
            + effectiveDate
            + jobCode
            + nonEmployeeFlag
            + personId
            + rolodexId
            + tbnId
            + appointmentTypeCode
            + calculationBase
            + personName
            + salaryAnniversaryDate
            + hierarchyProposalNumber
            + hiddenInHierarchy

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
