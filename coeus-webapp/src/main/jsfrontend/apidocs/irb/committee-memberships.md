## Committee Memberships [/irb/api/v1/committee-memberships/]

### Get Committee Memberships by Key [GET /irb/api/v1/committee-memberships/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"}

### Get All Committee Memberships [GET /irb/api/v1/committee-memberships/]
	 
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

### Get All Committee Memberships with Filtering [GET /irb/api/v1/committee-memberships/]
    
+ Parameters

    + committeeMembershipId (optional) - Committee Membership Id. Maximum length is 12.
    + committeeIdFk (optional) - Committee Id Fk. Maximum length is 12.
    + personId (optional) - Person Id. Maximum length is 40.
    + rolodexId (optional) - Rolodex Id. Maximum length is 12.
    + personName (optional) - Person Name. Maximum length is 90.
    + membershipId (optional) - Membership Id. Maximum length is 10.
    + paidMember (optional) - Paid Member. Maximum length is 1.
    + termStartDate (optional) - Term Start Date. Maximum length is 10.
    + termEndDate (optional) - Term End Date. Maximum length is 10.
    + membershipTypeCode (optional) - Membership Type Code. Maximum length is 3.
    + comments (optional) - Comments. Maximum length is 200.
    + contactNotes (optional) - Contact Notes. Maximum length is 200.
    + trainingNotes (optional) - Training Notes. Maximum length is 200.

            
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
			
### Get Schema for Committee Memberships [GET /irb/api/v1/committee-memberships/]
	                                          
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
    
            {"columns":["committeeMembershipId","committeeIdFk","personId","rolodexId","personName","membershipId","paidMember","termStartDate","termEndDate","membershipTypeCode","comments","contactNotes","trainingNotes"],"primaryKey":"committeeMembershipId"}
		
### Get Blueprint API specification for Committee Memberships [GET /irb/api/v1/committee-memberships/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committee Memberships.md"
            transfer-encoding:chunked
### Update Committee Memberships [PUT /irb/api/v1/committee-memberships/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Memberships [PUT /irb/api/v1/committee-memberships/]

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
### Insert Committee Memberships [POST /irb/api/v1/committee-memberships/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"committeeMembershipId": "(val)","committeeIdFk": "(val)","personId": "(val)","rolodexId": "(val)","personName": "(val)","membershipId": "(val)","paidMember": "(val)","termStartDate": "(val)","termEndDate": "(val)","membershipTypeCode": "(val)","comments": "(val)","contactNotes": "(val)","trainingNotes": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Memberships [POST /irb/api/v1/committee-memberships/]

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
### Delete Committee Memberships by Key [DELETE /irb/api/v1/committee-memberships/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Memberships [DELETE /irb/api/v1/committee-memberships/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Memberships with Matching [DELETE /irb/api/v1/committee-memberships/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + committeeMembershipId (optional) - Committee Membership Id. Maximum length is 12.
    + committeeIdFk (optional) - Committee Id Fk. Maximum length is 12.
    + personId (optional) - Person Id. Maximum length is 40.
    + rolodexId (optional) - Rolodex Id. Maximum length is 12.
    + personName (optional) - Person Name. Maximum length is 90.
    + membershipId (optional) - Membership Id. Maximum length is 10.
    + paidMember (optional) - Paid Member. Maximum length is 1.
    + termStartDate (optional) - Term Start Date. Maximum length is 10.
    + termEndDate (optional) - Term End Date. Maximum length is 10.
    + membershipTypeCode (optional) - Membership Type Code. Maximum length is 3.
    + comments (optional) - Comments. Maximum length is 200.
    + contactNotes (optional) - Contact Notes. Maximum length is 200.
    + trainingNotes (optional) - Training Notes. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
