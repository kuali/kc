## Award Approved Foreign Travel [/award/api/v1/award-approved-foreign-travel/]

### Get Award Approved Foreign Travel by Key [GET /award/api/v1/award-approved-foreign-travel/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"approvedForeignTravelId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}

### Get All Award Approved Foreign Travel [GET /award/api/v1/award-approved-foreign-travel/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"approvedForeignTravelId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"approvedForeignTravelId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Approved Foreign Travel with Filtering [GET /award/api/v1/award-approved-foreign-travel/]
    
+ Parameters

    + approvedForeignTravelId (optional) - Approved Foreign Travel  ID. Maximum length is 8.
    + personId (optional) - Person Id. Maximum length is 40.
    + rolodexId (optional) - Rolodex Id. Maximum length is 6.
    + travelerName (optional) - Traveler Name. Maximum length is 90.
    + destination (optional) - Destination. Maximum length is 30.
    + startDate (optional) - Start Date. Maximum length is 10.
    + endDate (optional) - End Date. Maximum length is 10.
    + amount (optional) - Amount. Maximum length is 15.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 4.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"approvedForeignTravelId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"approvedForeignTravelId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Approved Foreign Travel [GET /award/api/v1/award-approved-foreign-travel/]
	                                          
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
    
            {"columns":["approvedForeignTravelId","personId","rolodexId","travelerName","destination","startDate","endDate","amount","awardNumber","sequenceNumber"],"primaryKey":"approvedForeignTravelId"}
		
### Get Blueprint API specification for Award Approved Foreign Travel [GET /award/api/v1/award-approved-foreign-travel/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Approved Foreign Travel.md"
            transfer-encoding:chunked
