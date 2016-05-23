## Committee Membership Types [/research-common/api/v1/committee-membership-types/]

### Get Committee Membership Types by Key [GET /research-common/api/v1/committee-membership-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"membershipTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}

### Get All Committee Membership Types [GET /research-common/api/v1/committee-membership-types/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"membershipTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"membershipTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]

### Get All Committee Membership Types with Filtering [GET /research-common/api/v1/committee-membership-types/]
    
+ Parameters

    + membershipTypeCode (optional) - Membership Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"membershipTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"membershipTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Committee Membership Types [GET /research-common/api/v1/committee-membership-types/]
	                                          
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
    
            {"columns":["membershipTypeCode","description"],"primaryKey":"membershipTypeCode"}
		
### Get Blueprint API specification for Committee Membership Types [GET /research-common/api/v1/committee-membership-types/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Committee Membership Types.md"
            transfer-encoding:chunked
### Update Committee Membership Types [PUT /research-common/api/v1/committee-membership-types/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"membershipTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Committee Membership Types [PUT /research-common/api/v1/committee-membership-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"membershipTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"membershipTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Committee Membership Types [POST /research-common/api/v1/committee-membership-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"membershipTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"membershipTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Committee Membership Types [POST /research-common/api/v1/committee-membership-types/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"membershipTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"membershipTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"membershipTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"},
              {"membershipTypeCode": "(val)","description": "(val)","_primaryKey": "(val)"}
            ]
### Delete Committee Membership Types by Key [DELETE /research-common/api/v1/committee-membership-types/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Membership Types [DELETE /research-common/api/v1/committee-membership-types/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Committee Membership Types with Matching [DELETE /research-common/api/v1/committee-membership-types/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + membershipTypeCode (optional) - Membership Type Code. Maximum length is 3.
    + description (optional) - Description. Maximum length is 200.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
