## Protocol Persons [/research-sys/api/v1/protocol-persons/]

### Get Protocol Persons by Key [GET /research-sys/api/v1/protocol-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"protocolPersonId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","personId": "(val)","personName": "(val)","protocolPersonRoleId": "(val)","rolodexId": "(val)","affiliationTypeCode": "(val)","comments": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","_primaryKey": "(val)"}

### Get All Protocol Persons [GET /research-sys/api/v1/protocol-persons/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolPersonId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","personId": "(val)","personName": "(val)","protocolPersonRoleId": "(val)","rolodexId": "(val)","affiliationTypeCode": "(val)","comments": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","_primaryKey": "(val)"},
              {"protocolPersonId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","personId": "(val)","personName": "(val)","protocolPersonRoleId": "(val)","rolodexId": "(val)","affiliationTypeCode": "(val)","comments": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","_primaryKey": "(val)"}
            ]

### Get All Protocol Persons with Filtering [GET /research-sys/api/v1/protocol-persons/]
    
+ Parameters

        + protocolPersonId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + personId
            + personName
            + protocolPersonRoleId
            + rolodexId
            + affiliationTypeCode
            + comments
            + socialSecurityNumber
            + lastName
            + firstName
            + middleName
            + fullName
            + priorName
            + userName
            + emailAddress
            + dateOfBirth
            + age
            + ageByFiscalYear
            + gender
            + race
            + educationLevel
            + degree
            + major
            + handicappedFlag
            + handicapType
            + veteranFlag
            + veteranType
            + visaCode
            + visaType
            + visaRenewalDate
            + hasVisa
            + officeLocation
            + officePhone
            + secondaryOfficeLocation
            + secondaryOfficePhone
            + school
            + yearGraduated
            + directoryDepartment
            + saluation
            + countryOfCitizenship
            + primaryTitle
            + directoryTitle
            + homeUnit
            + facultyFlag
            + graduateStudentStaffFlag
            + researchStaffFlag
            + serviceStaffFlag
            + supportStaffFlag
            + otherAcademicGroupFlag
            + medicalStaffFlag
            + vacationAccrualFlag
            + onSabbaticalFlag
            + idProvided
            + idVerified
            + addressLine1
            + addressLine2
            + addressLine3
            + city
            + county
            + state
            + postalCode
            + countryCode
            + faxNumber
            + pagerNumber
            + mobilePhoneNumber
            + eraCommonsUserName

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"protocolPersonId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","personId": "(val)","personName": "(val)","protocolPersonRoleId": "(val)","rolodexId": "(val)","affiliationTypeCode": "(val)","comments": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","_primaryKey": "(val)"},
              {"protocolPersonId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","personId": "(val)","personName": "(val)","protocolPersonRoleId": "(val)","rolodexId": "(val)","affiliationTypeCode": "(val)","comments": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Protocol Persons [GET /research-sys/api/v1/protocol-persons/]
	                                          
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
    
            {"columns":["protocolPersonId","protocolId","protocolNumber","sequenceNumber","personId","personName","protocolPersonRoleId","rolodexId","affiliationTypeCode","comments","socialSecurityNumber","lastName","firstName","middleName","fullName","priorName","userName","emailAddress","dateOfBirth","age","ageByFiscalYear","gender","race","educationLevel","degree","major","handicappedFlag","handicapType","veteranFlag","veteranType","visaCode","visaType","visaRenewalDate","hasVisa","officeLocation","officePhone","secondaryOfficeLocation","secondaryOfficePhone","school","yearGraduated","directoryDepartment","saluation","countryOfCitizenship","primaryTitle","directoryTitle","homeUnit","facultyFlag","graduateStudentStaffFlag","researchStaffFlag","serviceStaffFlag","supportStaffFlag","otherAcademicGroupFlag","medicalStaffFlag","vacationAccrualFlag","onSabbaticalFlag","idProvided","idVerified","addressLine1","addressLine2","addressLine3","city","county","state","postalCode","countryCode","faxNumber","pagerNumber","mobilePhoneNumber","eraCommonsUserName"],"primaryKey":"protocolPersonId"}
		
### Get Blueprint API specification for Protocol Persons [GET /research-sys/api/v1/protocol-persons/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Protocol Persons.md"
            transfer-encoding:chunked


### Update Protocol Persons [PUT /research-sys/api/v1/protocol-persons/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolPersonId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","personId": "(val)","personName": "(val)","protocolPersonRoleId": "(val)","rolodexId": "(val)","affiliationTypeCode": "(val)","comments": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Protocol Persons [PUT /research-sys/api/v1/protocol-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolPersonId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","personId": "(val)","personName": "(val)","protocolPersonRoleId": "(val)","rolodexId": "(val)","affiliationTypeCode": "(val)","comments": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","_primaryKey": "(val)"},
              {"protocolPersonId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","personId": "(val)","personName": "(val)","protocolPersonRoleId": "(val)","rolodexId": "(val)","affiliationTypeCode": "(val)","comments": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Protocol Persons [POST /research-sys/api/v1/protocol-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"protocolPersonId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","personId": "(val)","personName": "(val)","protocolPersonRoleId": "(val)","rolodexId": "(val)","affiliationTypeCode": "(val)","comments": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"protocolPersonId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","personId": "(val)","personName": "(val)","protocolPersonRoleId": "(val)","rolodexId": "(val)","affiliationTypeCode": "(val)","comments": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Protocol Persons [POST /research-sys/api/v1/protocol-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"protocolPersonId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","personId": "(val)","personName": "(val)","protocolPersonRoleId": "(val)","rolodexId": "(val)","affiliationTypeCode": "(val)","comments": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","_primaryKey": "(val)"},
              {"protocolPersonId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","personId": "(val)","personName": "(val)","protocolPersonRoleId": "(val)","rolodexId": "(val)","affiliationTypeCode": "(val)","comments": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"protocolPersonId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","personId": "(val)","personName": "(val)","protocolPersonRoleId": "(val)","rolodexId": "(val)","affiliationTypeCode": "(val)","comments": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","_primaryKey": "(val)"},
              {"protocolPersonId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","personId": "(val)","personName": "(val)","protocolPersonRoleId": "(val)","rolodexId": "(val)","affiliationTypeCode": "(val)","comments": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Protocol Persons by Key [DELETE /research-sys/api/v1/protocol-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Persons [DELETE /research-sys/api/v1/protocol-persons/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Protocol Persons with Matching [DELETE /research-sys/api/v1/protocol-persons/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + protocolPersonId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + personId
            + personName
            + protocolPersonRoleId
            + rolodexId
            + affiliationTypeCode
            + comments
            + socialSecurityNumber
            + lastName
            + firstName
            + middleName
            + fullName
            + priorName
            + userName
            + emailAddress
            + dateOfBirth
            + age
            + ageByFiscalYear
            + gender
            + race
            + educationLevel
            + degree
            + major
            + handicappedFlag
            + handicapType
            + veteranFlag
            + veteranType
            + visaCode
            + visaType
            + visaRenewalDate
            + hasVisa
            + officeLocation
            + officePhone
            + secondaryOfficeLocation
            + secondaryOfficePhone
            + school
            + yearGraduated
            + directoryDepartment
            + saluation
            + countryOfCitizenship
            + primaryTitle
            + directoryTitle
            + homeUnit
            + facultyFlag
            + graduateStudentStaffFlag
            + researchStaffFlag
            + serviceStaffFlag
            + supportStaffFlag
            + otherAcademicGroupFlag
            + medicalStaffFlag
            + vacationAccrualFlag
            + onSabbaticalFlag
            + idProvided
            + idVerified
            + addressLine1
            + addressLine2
            + addressLine3
            + city
            + county
            + state
            + postalCode
            + countryCode
            + faxNumber
            + pagerNumber
            + mobilePhoneNumber
            + eraCommonsUserName

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
