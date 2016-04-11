## Award Approved Foreign Travel [/research-sys/api/v1/award-approved-foreign-travel/]

### Get Award Approved Foreign Travel by Key [GET /research-sys/api/v1/award-approved-foreign-travel/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"approvedForeignTravelId": "(val)","awardId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}

### Get All Award Approved Foreign Travel [GET /research-sys/api/v1/award-approved-foreign-travel/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"approvedForeignTravelId": "(val)","awardId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"approvedForeignTravelId": "(val)","awardId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Approved Foreign Travel with Filtering [GET /research-sys/api/v1/award-approved-foreign-travel/]
    
+ Parameters

        + approvedForeignTravelId
            + awardId
            + personId
            + rolodexId
            + travelerName
            + destination
            + startDate
            + endDate
            + amount
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
              {"approvedForeignTravelId": "(val)","awardId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"approvedForeignTravelId": "(val)","awardId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Approved Foreign Travel [GET /research-sys/api/v1/award-approved-foreign-travel/]
	                                          
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
    
            {"columns":["approvedForeignTravelId","awardId","personId","rolodexId","travelerName","destination","startDate","endDate","amount","awardNumber","sequenceNumber"],"primaryKey":"approvedForeignTravelId"}
		
### Get Blueprint API specification for Award Approved Foreign Travel [GET /research-sys/api/v1/award-approved-foreign-travel/]
	 
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


### Update Award Approved Foreign Travel [PUT /research-sys/api/v1/award-approved-foreign-travel/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"approvedForeignTravelId": "(val)","awardId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Approved Foreign Travel [PUT /research-sys/api/v1/award-approved-foreign-travel/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"approvedForeignTravelId": "(val)","awardId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"approvedForeignTravelId": "(val)","awardId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Approved Foreign Travel [POST /research-sys/api/v1/award-approved-foreign-travel/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"approvedForeignTravelId": "(val)","awardId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"approvedForeignTravelId": "(val)","awardId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Approved Foreign Travel [POST /research-sys/api/v1/award-approved-foreign-travel/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"approvedForeignTravelId": "(val)","awardId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"approvedForeignTravelId": "(val)","awardId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"approvedForeignTravelId": "(val)","awardId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"},
              {"approvedForeignTravelId": "(val)","awardId": "(val)","personId": "(val)","rolodexId": "(val)","travelerName": "(val)","destination": "(val)","startDate": "(val)","endDate": "(val)","amount": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Approved Foreign Travel by Key [DELETE /research-sys/api/v1/award-approved-foreign-travel/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Approved Foreign Travel [DELETE /research-sys/api/v1/award-approved-foreign-travel/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Approved Foreign Travel with Matching [DELETE /research-sys/api/v1/award-approved-foreign-travel/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + approvedForeignTravelId
            + awardId
            + personId
            + rolodexId
            + travelerName
            + destination
            + startDate
            + endDate
            + amount
            + awardNumber
            + sequenceNumber

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
