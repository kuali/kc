## Proposal Persons [/propdev/api/v1/proposal-persons/]

### Get Proposal Persons by Key [GET /propdev/api/v1/proposal-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"}

### Get All Proposal Persons [GET /propdev/api/v1/proposal-persons/]
	 
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

### Get All Proposal Persons with Filtering [GET /propdev/api/v1/proposal-persons/]
    
+ Parameters

    + conflictOfInterestFlag (optional) - Conflict of interest. Maximum length is 1.
    + otherSignificantContributorFlag (optional) - Other Significant Contributor. Maximum length is 1.
    + percentageEffort (optional) - Total Effort. Maximum length is 20.
    + fedrDebrFlag (optional) - FedrDebr. Maximum length is 1.
    + fedrDelqFlag (optional) - FedrDelq. Maximum length is 1.
    + rolodexId (optional) - Rolodex Id. Maximum length is 40.
    + proposalPersonNumber (optional) - Proposal Person Number. Maximum length is 40.
    + proposalPersonRoleId (optional) - Proposal Person Role Id. Maximum length is 40.
    + certifiedBy (optional) - Certified By.
    + lastNotification (optional) - Last Notification.
    + certifiedTime (optional) - Certified Time.
    + optInUnitStatus (optional) - Opt In Unit Status.
    + optInCertificationStatus (optional) - Opt In Certification Status.
    + projectRole (optional) - Key Person Role. Maximum length is 60.
    + ordinalPosition (optional) - Ordinal Position.
    + hierarchyProposalNumber (optional) - Hierarchy Proposal Number.
    + hiddenInHierarchy (optional) - Hidden In Hierarchy.
    + personId (optional) - KcPerson Id. Maximum length is 40.
    + socialSecurityNumber (optional) - Social Security Number. Maximum length is 9.
    + lastName (optional) - Last Name. Maximum length is 30.
    + firstName (optional) - First Name. Maximum length is 30.
    + middleName (optional) - Middle Name. Maximum length is 30.
    + fullName (optional) - Full Name. Maximum length is 90.
    + priorName (optional) - Prior Name. Maximum length is 30.
    + userName (optional) - User Name. Maximum length is 100.
    + emailAddress (optional) - Email Address. Maximum length is 60.
    + dateOfBirth (optional) - Date of Birth. Maximum length is 10.
    + age (optional) - Age. Maximum length is 3.
    + ageByFiscalYear (optional) - Age by Fiscal Year. Maximum length is 3.
    + gender (optional) - Gender. Maximum length is 30.
    + race (optional) - Race. Maximum length is 30.
    + educationLevel (optional) - Education Level. Maximum length is 30.
    + degree (optional) - Degree. Maximum length is 11.
    + major (optional) - Major. Maximum length is 30.
    + handicappedFlag (optional) - Is Handicapped. Maximum length is 1.
    + handicapType (optional) - Handicap Type. Maximum length is 30.
    + veteranFlag (optional) - Veteran. Maximum length is 1.
    + veteranType (optional) - Veteran Type. Maximum length is 30.
    + visaCode (optional) - Visa Code. Maximum length is 20.
    + visaType (optional) - Visa Type. Maximum length is 30.
    + visaRenewalDate (optional) - Visa Renewal Date. Maximum length is 10.
    + hasVisa (optional) - Has Visa. Maximum length is 1.
    + officeLocation (optional) - Office Location. Maximum length is 30.
    + officePhone (optional) - Office Phone. Maximum length is 20.
    + secondaryOfficeLocation (optional) - Secondary Office Location. Maximum length is 30.
    + secondaryOfficePhone (optional) - Secondary Office Phone. Maximum length is 20.
    + school (optional) - School. Maximum length is 50.
    + yearGraduated (optional) - Year Graduated. Maximum length is 30.
    + directoryDepartment (optional) - Directory Department. Maximum length is 30.
    + saluation (optional) - Salutation. Maximum length is 30.
    + countryOfCitizenship (optional) - Country Code. Maximum length is 3.
    + primaryTitle (optional) - PrimaryTitle. Maximum length is 51.
    + directoryTitle (optional) - Directory Title. Maximum length is 50.
    + homeUnit (optional) - Home Unit. Maximum length is 8.
    + facultyFlag (optional) - Faculty. Maximum length is 1.
    + graduateStudentStaffFlag (optional) - Create User. Maximum length is 1.
    + researchStaffFlag (optional) - Is Research Staff. Maximum length is 1.
    + serviceStaffFlag (optional) - Is Service Staff. Maximum length is 1.
    + supportStaffFlag (optional) - Is Support Staff. Maximum length is 1.
    + otherAcademicGroupFlag (optional) - Is Other Academic Group. Maximum length is 1.
    + medicalStaffFlag (optional) - Is Medical Staff. Maximum length is 1.
    + vacationAccrualFlag (optional) - Is Vacation Accrual. Maximum length is 1.
    + onSabbaticalFlag (optional) - Is on Sabbatical. Maximum length is 1.
    + idProvided (optional) - Id Provided. Maximum length is 30.
    + idVerified (optional) - Id Verified. Maximum length is 30.
    + addressLine1 (optional) - Address Line 1. Maximum length is 80.
    + addressLine2 (optional) - Address Line 2. Maximum length is 80.
    + addressLine3 (optional) - Address Line 3. Maximum length is 80.
    + city (optional) - City. Maximum length is 30.
    + county (optional) - County. Maximum length is 30.
    + state (optional) - State. Maximum length is 30.
    + postalCode (optional) - Postal Code. Maximum length is 15.
    + countryCode (optional) - Country Code. Maximum length is 3.
    + faxNumber (optional) - Fax Number. Maximum length is 20.
    + pagerNumber (optional) - Page Number. Maximum length is 20.
    + mobilePhoneNumber (optional) - Moble Phone Number. Maximum length is 20.
    + eraCommonsUserName (optional) - Era Commons User Name. Maximum length is 20.
    + division (optional) - Division. Maximum length is 100.
    + citizenshipTypeCode (optional) - Citzenship Type. Maximum length is 3.
    + academicYearEffort (optional) - Academic Year Effort. Maximum length is 7.
    + calendarYearEffort (optional) - Calendar Year Effort. Maximum length is 7.
    + summerEffort (optional) - Summer Effort. Maximum length is 7.

            
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
			
### Get Schema for Proposal Persons [GET /propdev/api/v1/proposal-persons/]
	                                          
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
    
            {"columns":["conflictOfInterestFlag","otherSignificantContributorFlag","percentageEffort","fedrDebrFlag","fedrDelqFlag","rolodexId","proposalPersonNumber","proposalPersonRoleId","certifiedBy","lastNotification","certifiedTime","optInUnitStatus","optInCertificationStatus","projectRole","ordinalPosition","hierarchyProposalNumber","hiddenInHierarchy","personId","socialSecurityNumber","lastName","firstName","middleName","fullName","priorName","userName","emailAddress","dateOfBirth","age","ageByFiscalYear","gender","race","educationLevel","degree","major","handicappedFlag","handicapType","veteranFlag","veteranType","visaCode","visaType","visaRenewalDate","hasVisa","officeLocation","officePhone","secondaryOfficeLocation","secondaryOfficePhone","school","yearGraduated","directoryDepartment","saluation","countryOfCitizenship","primaryTitle","directoryTitle","homeUnit","facultyFlag","graduateStudentStaffFlag","researchStaffFlag","serviceStaffFlag","supportStaffFlag","otherAcademicGroupFlag","medicalStaffFlag","vacationAccrualFlag","onSabbaticalFlag","idProvided","idVerified","addressLine1","addressLine2","addressLine3","city","county","state","postalCode","countryCode","faxNumber","pagerNumber","mobilePhoneNumber","eraCommonsUserName","division","citizenshipTypeCode","academicYearEffort","calendarYearEffort","summerEffort"],"primaryKey":"developmentProposal:proposalPersonNumber"}
		
### Get Blueprint API specification for Proposal Persons [GET /propdev/api/v1/proposal-persons/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Proposal Persons.md"
            transfer-encoding:chunked


### Update Proposal Persons [PUT /propdev/api/v1/proposal-persons/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Proposal Persons [PUT /propdev/api/v1/proposal-persons/]

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

### Insert Proposal Persons [POST /propdev/api/v1/proposal-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"conflictOfInterestFlag": "(val)","otherSignificantContributorFlag": "(val)","percentageEffort": "(val)","fedrDebrFlag": "(val)","fedrDelqFlag": "(val)","rolodexId": "(val)","proposalPersonNumber": "(val)","proposalPersonRoleId": "(val)","certifiedBy": "(val)","lastNotification": "(val)","certifiedTime": "(val)","optInUnitStatus": "(val)","optInCertificationStatus": "(val)","projectRole": "(val)","ordinalPosition": "(val)","hierarchyProposalNumber": "(val)","hiddenInHierarchy": "(val)","personId": "(val)","socialSecurityNumber": "(val)","lastName": "(val)","firstName": "(val)","middleName": "(val)","fullName": "(val)","priorName": "(val)","userName": "(val)","emailAddress": "(val)","dateOfBirth": "(val)","age": "(val)","ageByFiscalYear": "(val)","gender": "(val)","race": "(val)","educationLevel": "(val)","degree": "(val)","major": "(val)","handicappedFlag": "(val)","handicapType": "(val)","veteranFlag": "(val)","veteranType": "(val)","visaCode": "(val)","visaType": "(val)","visaRenewalDate": "(val)","hasVisa": "(val)","officeLocation": "(val)","officePhone": "(val)","secondaryOfficeLocation": "(val)","secondaryOfficePhone": "(val)","school": "(val)","yearGraduated": "(val)","directoryDepartment": "(val)","saluation": "(val)","countryOfCitizenship": "(val)","primaryTitle": "(val)","directoryTitle": "(val)","homeUnit": "(val)","facultyFlag": "(val)","graduateStudentStaffFlag": "(val)","researchStaffFlag": "(val)","serviceStaffFlag": "(val)","supportStaffFlag": "(val)","otherAcademicGroupFlag": "(val)","medicalStaffFlag": "(val)","vacationAccrualFlag": "(val)","onSabbaticalFlag": "(val)","idProvided": "(val)","idVerified": "(val)","addressLine1": "(val)","addressLine2": "(val)","addressLine3": "(val)","city": "(val)","county": "(val)","state": "(val)","postalCode": "(val)","countryCode": "(val)","faxNumber": "(val)","pagerNumber": "(val)","mobilePhoneNumber": "(val)","eraCommonsUserName": "(val)","division": "(val)","citizenshipTypeCode": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Proposal Persons [POST /propdev/api/v1/proposal-persons/]

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
            
### Delete Proposal Persons by Key [DELETE /propdev/api/v1/proposal-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Persons [DELETE /propdev/api/v1/proposal-persons/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Proposal Persons with Matching [DELETE /propdev/api/v1/proposal-persons/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + conflictOfInterestFlag (optional) - Conflict of interest. Maximum length is 1.
    + otherSignificantContributorFlag (optional) - Other Significant Contributor. Maximum length is 1.
    + percentageEffort (optional) - Total Effort. Maximum length is 20.
    + fedrDebrFlag (optional) - FedrDebr. Maximum length is 1.
    + fedrDelqFlag (optional) - FedrDelq. Maximum length is 1.
    + rolodexId (optional) - Rolodex Id. Maximum length is 40.
    + proposalPersonNumber (optional) - Proposal Person Number. Maximum length is 40.
    + proposalPersonRoleId (optional) - Proposal Person Role Id. Maximum length is 40.
    + certifiedBy (optional) - Certified By.
    + lastNotification (optional) - Last Notification.
    + certifiedTime (optional) - Certified Time.
    + optInUnitStatus (optional) - Opt In Unit Status.
    + optInCertificationStatus (optional) - Opt In Certification Status.
    + projectRole (optional) - Key Person Role. Maximum length is 60.
    + ordinalPosition (optional) - Ordinal Position.
    + hierarchyProposalNumber (optional) - Hierarchy Proposal Number.
    + hiddenInHierarchy (optional) - Hidden In Hierarchy.
    + personId (optional) - KcPerson Id. Maximum length is 40.
    + socialSecurityNumber (optional) - Social Security Number. Maximum length is 9.
    + lastName (optional) - Last Name. Maximum length is 30.
    + firstName (optional) - First Name. Maximum length is 30.
    + middleName (optional) - Middle Name. Maximum length is 30.
    + fullName (optional) - Full Name. Maximum length is 90.
    + priorName (optional) - Prior Name. Maximum length is 30.
    + userName (optional) - User Name. Maximum length is 100.
    + emailAddress (optional) - Email Address. Maximum length is 60.
    + dateOfBirth (optional) - Date of Birth. Maximum length is 10.
    + age (optional) - Age. Maximum length is 3.
    + ageByFiscalYear (optional) - Age by Fiscal Year. Maximum length is 3.
    + gender (optional) - Gender. Maximum length is 30.
    + race (optional) - Race. Maximum length is 30.
    + educationLevel (optional) - Education Level. Maximum length is 30.
    + degree (optional) - Degree. Maximum length is 11.
    + major (optional) - Major. Maximum length is 30.
    + handicappedFlag (optional) - Is Handicapped. Maximum length is 1.
    + handicapType (optional) - Handicap Type. Maximum length is 30.
    + veteranFlag (optional) - Veteran. Maximum length is 1.
    + veteranType (optional) - Veteran Type. Maximum length is 30.
    + visaCode (optional) - Visa Code. Maximum length is 20.
    + visaType (optional) - Visa Type. Maximum length is 30.
    + visaRenewalDate (optional) - Visa Renewal Date. Maximum length is 10.
    + hasVisa (optional) - Has Visa. Maximum length is 1.
    + officeLocation (optional) - Office Location. Maximum length is 30.
    + officePhone (optional) - Office Phone. Maximum length is 20.
    + secondaryOfficeLocation (optional) - Secondary Office Location. Maximum length is 30.
    + secondaryOfficePhone (optional) - Secondary Office Phone. Maximum length is 20.
    + school (optional) - School. Maximum length is 50.
    + yearGraduated (optional) - Year Graduated. Maximum length is 30.
    + directoryDepartment (optional) - Directory Department. Maximum length is 30.
    + saluation (optional) - Salutation. Maximum length is 30.
    + countryOfCitizenship (optional) - Country Code. Maximum length is 3.
    + primaryTitle (optional) - PrimaryTitle. Maximum length is 51.
    + directoryTitle (optional) - Directory Title. Maximum length is 50.
    + homeUnit (optional) - Home Unit. Maximum length is 8.
    + facultyFlag (optional) - Faculty. Maximum length is 1.
    + graduateStudentStaffFlag (optional) - Create User. Maximum length is 1.
    + researchStaffFlag (optional) - Is Research Staff. Maximum length is 1.
    + serviceStaffFlag (optional) - Is Service Staff. Maximum length is 1.
    + supportStaffFlag (optional) - Is Support Staff. Maximum length is 1.
    + otherAcademicGroupFlag (optional) - Is Other Academic Group. Maximum length is 1.
    + medicalStaffFlag (optional) - Is Medical Staff. Maximum length is 1.
    + vacationAccrualFlag (optional) - Is Vacation Accrual. Maximum length is 1.
    + onSabbaticalFlag (optional) - Is on Sabbatical. Maximum length is 1.
    + idProvided (optional) - Id Provided. Maximum length is 30.
    + idVerified (optional) - Id Verified. Maximum length is 30.
    + addressLine1 (optional) - Address Line 1. Maximum length is 80.
    + addressLine2 (optional) - Address Line 2. Maximum length is 80.
    + addressLine3 (optional) - Address Line 3. Maximum length is 80.
    + city (optional) - City. Maximum length is 30.
    + county (optional) - County. Maximum length is 30.
    + state (optional) - State. Maximum length is 30.
    + postalCode (optional) - Postal Code. Maximum length is 15.
    + countryCode (optional) - Country Code. Maximum length is 3.
    + faxNumber (optional) - Fax Number. Maximum length is 20.
    + pagerNumber (optional) - Page Number. Maximum length is 20.
    + mobilePhoneNumber (optional) - Moble Phone Number. Maximum length is 20.
    + eraCommonsUserName (optional) - Era Commons User Name. Maximum length is 20.
    + division (optional) - Division. Maximum length is 100.
    + citizenshipTypeCode (optional) - Citzenship Type. Maximum length is 3.
    + academicYearEffort (optional) - Academic Year Effort. Maximum length is 7.
    + calendarYearEffort (optional) - Calendar Year Effort. Maximum length is 7.
    + summerEffort (optional) - Summer Effort. Maximum length is 7.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
