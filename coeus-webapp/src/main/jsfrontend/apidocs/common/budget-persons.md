## Budget Persons [/research-common/api/v1/budget-persons/]

### Get Budget Persons by Key [GET /research-common/api/v1/budget-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}

### Get All Budget Persons [GET /research-common/api/v1/budget-persons/]
	 
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

### Get All Budget Persons with Filtering [GET /research-common/api/v1/budget-persons/]
    
+ Parameters

    + personSequenceNumber (optional) - Person Sequence Number.
    + budgetId (optional) - Budget Id.
    + effectiveDate (optional) - Salary Effective Date. Maximum length is 21.
    + jobCode (optional) - Job Code. Maximum length is 6.
    + nonEmployeeFlag (optional) - Non Employee Flag. Maximum length is 1.
    + personId (optional) - KcPerson Id. Maximum length is 40.
    + rolodexId (optional) - Rolodex Id.
    + tbnId (optional) - Tbn Id.
    + appointmentTypeCode (optional) - Appointment Type. Maximum length is 2.
    + calculationBase (optional) - Calculation Base. Maximum length is 15.
    + personName (optional) - Personnel. Maximum length is 90.
    + salaryAnniversaryDate (optional) - Salary Anniversary Date. Maximum length is 21.
    + hierarchyProposalNumber (optional) - Hierarchy Proposal Number.
    + hiddenInHierarchy (optional) - Hidden In Hierarchy.

            
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
			
### Get Schema for Budget Persons [GET /research-common/api/v1/budget-persons/]
	                                          
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
		
### Get Blueprint API specification for Budget Persons [GET /research-common/api/v1/budget-persons/]
	 
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


### Update Budget Persons [PUT /research-common/api/v1/budget-persons/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Persons [PUT /research-common/api/v1/budget-persons/]

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

### Insert Budget Persons [POST /research-common/api/v1/budget-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personSequenceNumber": "(val)","budgetId": "(val)","effectiveDate": "(val)","jobCode": "(val)","nonEmployeeFlag": "(val)","personId": "(val)","rolodexId": "(val)","tbnId": "(val)","appointmentTypeCode": "(val)","calculationBase": "(val)","personName": "(val)","salaryAnniversaryDate": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Persons [POST /research-common/api/v1/budget-persons/]

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
            
### Delete Budget Persons by Key [DELETE /research-common/api/v1/budget-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Persons [DELETE /research-common/api/v1/budget-persons/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Persons with Matching [DELETE /research-common/api/v1/budget-persons/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + personSequenceNumber (optional) - Person Sequence Number.
    + budgetId (optional) - Budget Id.
    + effectiveDate (optional) - Salary Effective Date. Maximum length is 21.
    + jobCode (optional) - Job Code. Maximum length is 6.
    + nonEmployeeFlag (optional) - Non Employee Flag. Maximum length is 1.
    + personId (optional) - KcPerson Id. Maximum length is 40.
    + rolodexId (optional) - Rolodex Id.
    + tbnId (optional) - Tbn Id.
    + appointmentTypeCode (optional) - Appointment Type. Maximum length is 2.
    + calculationBase (optional) - Calculation Base. Maximum length is 15.
    + personName (optional) - Personnel. Maximum length is 90.
    + salaryAnniversaryDate (optional) - Salary Anniversary Date. Maximum length is 21.
    + hierarchyProposalNumber (optional) - Hierarchy Proposal Number.
    + hiddenInHierarchy (optional) - Hidden In Hierarchy.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
