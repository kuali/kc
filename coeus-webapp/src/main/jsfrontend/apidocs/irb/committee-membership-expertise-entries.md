## Committee Membership Expertise Entries [/research-sys/api/v1/committee-membership-expertise-entries/]

### Get Committee Membership Expertise Entries by Key [GET /research-sys/api/v1/committee-membership-expertise-entries/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"committeeMembershipExpertiseId": "(val)","committeeMembershipIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}

### Get All Committee Membership Expertise Entries [GET /research-sys/api/v1/committee-membership-expertise-entries/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"committeeMembershipExpertiseId": "(val)","committeeMembershipIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"},
              {"committeeMembershipExpertiseId": "(val)","committeeMembershipIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Committee Membership Expertise Entries with Filtering [GET /research-sys/api/v1/committee-membership-expertise-entries/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + committeeMembershipExpertiseId
            + committeeMembershipIdFk
            + researchAreaCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"committeeMembershipExpertiseId": "(val)","committeeMembershipIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"},
              {"committeeMembershipExpertiseId": "(val)","committeeMembershipIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Committee Membership Expertise Entries [GET /research-sys/api/v1/committee-membership-expertise-entries/]
	 
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
		
### Get Blueprint API specification for Committee Membership Expertise Entries [GET /research-sys/api/v1/committee-membership-expertise-entries/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committee Membership Expertise Entries.md"
            transfer-encoding:chunked


### Update Committee Membership Expertise Entries [PUT /research-sys/api/v1/committee-membership-expertise-entries/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeMembershipExpertiseId": "(val)","committeeMembershipIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Membership Expertise Entries [PUT /research-sys/api/v1/committee-membership-expertise-entries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"committeeMembershipExpertiseId": "(val)","committeeMembershipIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"},
              {"committeeMembershipExpertiseId": "(val)","committeeMembershipIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Committee Membership Expertise Entries [POST /research-sys/api/v1/committee-membership-expertise-entries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"committeeMembershipExpertiseId": "(val)","committeeMembershipIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"committeeMembershipExpertiseId": "(val)","committeeMembershipIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Membership Expertise Entries [POST /research-sys/api/v1/committee-membership-expertise-entries/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"committeeMembershipExpertiseId": "(val)","committeeMembershipIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"},
              {"committeeMembershipExpertiseId": "(val)","committeeMembershipIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"committeeMembershipExpertiseId": "(val)","committeeMembershipIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"},
              {"committeeMembershipExpertiseId": "(val)","committeeMembershipIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Committee Membership Expertise Entries by Key [DELETE /research-sys/api/v1/committee-membership-expertise-entries/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Membership Expertise Entries [DELETE /research-sys/api/v1/committee-membership-expertise-entries/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Committee Membership Expertise Entries with Matching [DELETE /research-sys/api/v1/committee-membership-expertise-entries/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + committeeMembershipExpertiseId
            + committeeMembershipIdFk
            + researchAreaCode


+ Response 204
