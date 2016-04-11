## Award Approved Sub Awards [/research-sys/api/v1/award-approved-sub-awards/]

### Get Award Approved Sub Awards by Key [GET /research-sys/api/v1/award-approved-sub-awards/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardApprovedSubawardId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"}

### Get All Award Approved Sub Awards [GET /research-sys/api/v1/award-approved-sub-awards/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardApprovedSubawardId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"},
              {"awardApprovedSubawardId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Approved Sub Awards with Filtering [GET /research-sys/api/v1/award-approved-sub-awards/]
    
+ Parameters

        + awardApprovedSubawardId
            + awardId
            + awardNumber
            + sequenceNumber
            + organizationName
            + organizationId
            + amount

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardApprovedSubawardId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"},
              {"awardApprovedSubawardId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Approved Sub Awards [GET /research-sys/api/v1/award-approved-sub-awards/]
	                                          
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
    
            {"columns":["awardApprovedSubawardId","awardId","awardNumber","sequenceNumber","organizationName","organizationId","amount"],"primaryKey":"awardApprovedSubawardId"}
		
### Get Blueprint API specification for Award Approved Sub Awards [GET /research-sys/api/v1/award-approved-sub-awards/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Approved Sub Awards.md"
            transfer-encoding:chunked


### Update Award Approved Sub Awards [PUT /research-sys/api/v1/award-approved-sub-awards/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardApprovedSubawardId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Approved Sub Awards [PUT /research-sys/api/v1/award-approved-sub-awards/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardApprovedSubawardId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"},
              {"awardApprovedSubawardId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Approved Sub Awards [POST /research-sys/api/v1/award-approved-sub-awards/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardApprovedSubawardId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardApprovedSubawardId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Approved Sub Awards [POST /research-sys/api/v1/award-approved-sub-awards/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardApprovedSubawardId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"},
              {"awardApprovedSubawardId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardApprovedSubawardId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"},
              {"awardApprovedSubawardId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","organizationName": "(val)","organizationId": "(val)","amount": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Approved Sub Awards by Key [DELETE /research-sys/api/v1/award-approved-sub-awards/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Approved Sub Awards [DELETE /research-sys/api/v1/award-approved-sub-awards/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Approved Sub Awards with Matching [DELETE /research-sys/api/v1/award-approved-sub-awards/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + awardApprovedSubawardId
            + awardId
            + awardNumber
            + sequenceNumber
            + organizationName
            + organizationId
            + amount

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
