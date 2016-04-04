## Iacuc Committee Memberships [/research-sys/api/v1/iacuc-committee-memberships/]

### Get Iacuc Committee Memberships by Key [GET /research-sys/api/v1/iacuc-committee-memberships/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Committee Memberships [GET /research-sys/api/v1/iacuc-committee-memberships/]
	 
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

### Get All Iacuc Committee Memberships with Filtering [GET /research-sys/api/v1/iacuc-committee-memberships/]
	 
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
			
### Get Schema for Iacuc Committee Memberships [GET /research-sys/api/v1/iacuc-committee-memberships/]
	 
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
		
### Get Blueprint API specification for Iacuc Committee Memberships [GET /research-sys/api/v1/iacuc-committee-memberships/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Committee Memberships.md"
            transfer-encoding:chunked


### Update Iacuc Committee Memberships [PUT /research-sys/api/v1/iacuc-committee-memberships/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Committee Memberships [PUT /research-sys/api/v1/iacuc-committee-memberships/]

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

### Insert Iacuc Committee Memberships [POST /research-sys/api/v1/iacuc-committee-memberships/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Committee Memberships [POST /research-sys/api/v1/iacuc-committee-memberships/]

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
            
### Delete Iacuc Committee Memberships by Key [DELETE /research-sys/api/v1/iacuc-committee-memberships/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Committee Memberships [DELETE /research-sys/api/v1/iacuc-committee-memberships/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Committee Memberships with Matching [DELETE /research-sys/api/v1/iacuc-committee-memberships/]
	 
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
