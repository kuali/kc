## Award Persons [/research-sys/api/v1/award-persons/]

### Get Award Persons by Key [GET /research-sys/api/v1/award-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}

### Get All Award Persons [GET /research-sys/api/v1/award-persons/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Persons with Filtering [GET /research-sys/api/v1/award-persons/]
    
+ Parameters

        + awardContactId
            + personId
            + rolodexId
            + fullName
            + academicYearEffort
            + calendarYearEffort
            + summerEffort
            + totalEffort
            + faculty
            + roleCode
            + keyPersonRole
            + optInUnitStatus
            + awardId
            + awardNumber
            + sequenceNumber

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Persons [GET /research-sys/api/v1/award-persons/]
	                                          
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
    
            {"columns":["awardContactId","personId","rolodexId","fullName","academicYearEffort","calendarYearEffort","summerEffort","totalEffort","faculty","roleCode","keyPersonRole","optInUnitStatus","awardId","awardNumber","sequenceNumber"],"primaryKey":"awardContactId"}
		
### Get Blueprint API specification for Award Persons [GET /research-sys/api/v1/award-persons/]
	 
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


### Update Award Persons [PUT /research-sys/api/v1/award-persons/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Persons [PUT /research-sys/api/v1/award-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Persons [POST /research-sys/api/v1/award-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Persons [POST /research-sys/api/v1/award-persons/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"awardContactId": "(val)","personId": "(val)","rolodexId": "(val)","fullName": "(val)","academicYearEffort": "(val)","calendarYearEffort": "(val)","summerEffort": "(val)","totalEffort": "(val)","faculty": "(val)","roleCode": "(val)","keyPersonRole": "(val)","optInUnitStatus": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Persons by Key [DELETE /research-sys/api/v1/award-persons/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Persons [DELETE /research-sys/api/v1/award-persons/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Persons with Matching [DELETE /research-sys/api/v1/award-persons/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + awardContactId
            + personId
            + rolodexId
            + fullName
            + academicYearEffort
            + calendarYearEffort
            + summerEffort
            + totalEffort
            + faculty
            + roleCode
            + keyPersonRole
            + optInUnitStatus
            + awardId
            + awardNumber
            + sequenceNumber

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
