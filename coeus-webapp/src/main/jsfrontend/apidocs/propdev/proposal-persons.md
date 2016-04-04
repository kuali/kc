## Proposal Persons [/research-sys/api/v1/proposal-persons/]

### Get Proposal Persons by Key [GET /research-sys/api/v1/proposal-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"}

### Get All Proposal Persons [GET /research-sys/api/v1/proposal-persons/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"},
              {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"}
            ]

### Get All Proposal Persons with Filtering [GET /research-sys/api/v1/proposal-persons/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + conflictOfInterestFlag
            + otherSignificantContributorFlag
            + percentageEffort
            + fedrDebrFlag
            + fedrDelqFlag
            + rolodexId
            + proposalPersonNumber
            + proposalPersonRoleId
            + certifiedBy
            + lastNotification
            + certifiedTime
            + optInUnitStatus
            + optInCertificationStatus
            + projectRole
            + ordinalPosition
            + hierarchyProposalNumber
            + hiddenInHierarchy
            + personId
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
            + division
            + citizenshipTypeCode
            + academicYearEffort
            + calendarYearEffort
            + summerEffort
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"},
              {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Proposal Persons [GET /research-sys/api/v1/proposal-persons/]
	 
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
		
### Get Blueprint API specification for Proposal Persons [GET /research-sys/api/v1/proposal-persons/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Persons.md"
            transfer-encoding:chunked


### Update Proposal Persons [PUT /research-sys/api/v1/proposal-persons/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Persons [PUT /research-sys/api/v1/proposal-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"},
              {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Proposal Persons [POST /research-sys/api/v1/proposal-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Persons [POST /research-sys/api/v1/proposal-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"},
              {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"},
              {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Proposal Persons by Key [DELETE /research-sys/api/v1/proposal-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Persons [DELETE /research-sys/api/v1/proposal-persons/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Proposal Persons with Matching [DELETE /research-sys/api/v1/proposal-persons/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + conflictOfInterestFlag
            + otherSignificantContributorFlag
            + percentageEffort
            + fedrDebrFlag
            + fedrDelqFlag
            + rolodexId
            + proposalPersonNumber
            + proposalPersonRoleId
            + certifiedBy
            + lastNotification
            + certifiedTime
            + optInUnitStatus
            + optInCertificationStatus
            + projectRole
            + ordinalPosition
            + hierarchyProposalNumber
            + hiddenInHierarchy
            + personId
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
            + division
            + citizenshipTypeCode
            + academicYearEffort
            + calendarYearEffort
            + summerEffort


+ Response 204
