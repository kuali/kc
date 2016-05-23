## Award Persons [/award/api/v1/award-persons/]

### Get Award Persons by Key [GET /award/api/v1/award-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}

### Get All Award Persons [GET /award/api/v1/award-persons/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","award.awardId": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Persons with Filtering [GET /award/api/v1/award-persons/]
    
+ Parameters

    + awardContactId (optional) - Award Contact ID. Maximum length is 8.
    + personId (optional) - 
    + rolodexId (optional) - 
    + fullName (optional) - Full Name. Maximum length is 90.
    + academicYearEffort (optional) - Academic Year Effort. Maximum length is 7.
    + calendarYearEffort (optional) - Calendar Year Effort. Maximum length is 7.
    + summerEffort (optional) - Summer Effort. Maximum length is 7.
    + totalEffort (optional) - Total Effort. Maximum length is 7.
    + faculty (optional) - Faculty flag. Maximum length is 1.
    + roleCode (optional) - 
    + keyPersonRole (optional) - Project Role. Maximum length is 60.
    + optInUnitStatus (optional) - 
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.
    + award.awardId (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","award.awardId": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","award.awardId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Persons [GET /award/api/v1/award-persons/]
	                                          
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
    
            {"columns":["awardContactId","personId","rolodexId","fullName","academicYearEffort","calendarYearEffort","summerEffort","totalEffort","faculty","roleCode","keyPersonRole","optInUnitStatus","awardNumber","sequenceNumber","award.awardId"],"primaryKey":"awardContactId"}
		
### Get Blueprint API specification for Award Persons [GET /award/api/v1/award-persons/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Persons.md"
            transfer-encoding:chunked
