## Committee Memberships [/research-sys/api/v1/committee-memberships/]

### Get Committee Memberships by Key [GET /research-sys/api/v1/committee-memberships/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"}

### Get All Committee Memberships [GET /research-sys/api/v1/committee-memberships/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"},
              {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"}
            ]

### Get All Committee Memberships with Filtering [GET /research-sys/api/v1/committee-memberships/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + committeeMembershipId
            + committeeIdFk
            + personId
            + rolodexId
            + personName
            + membershipId
            + paidMember
            + termStartDate
            + termEndDate
            + membershipTypeCode
            + comments
            + contactNotes
            + trainingNotes
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"},
              {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Committee Memberships [GET /research-sys/api/v1/committee-memberships/]
	 
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
		
### Get Blueprint API specification for Committee Memberships [GET /research-sys/api/v1/committee-memberships/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committee Memberships.md"
            transfer-encoding:chunked


### Update Committee Memberships [PUT /research-sys/api/v1/committee-memberships/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Memberships [PUT /research-sys/api/v1/committee-memberships/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"},
              {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Committee Memberships [POST /research-sys/api/v1/committee-memberships/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Memberships [POST /research-sys/api/v1/committee-memberships/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"},
              {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"},
              {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Committee Memberships by Key [DELETE /research-sys/api/v1/committee-memberships/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Memberships [DELETE /research-sys/api/v1/committee-memberships/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Committee Memberships with Matching [DELETE /research-sys/api/v1/committee-memberships/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + committeeMembershipId
            + committeeIdFk
            + personId
            + rolodexId
            + personName
            + membershipId
            + paidMember
            + termStartDate
            + termEndDate
            + membershipTypeCode
            + comments
            + contactNotes
            + trainingNotes


+ Response 204
