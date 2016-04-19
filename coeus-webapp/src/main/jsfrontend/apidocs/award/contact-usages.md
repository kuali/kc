## Contact Usages [/award/api/v1/contact-usages/]

### Get Contact Usages by Key [GET /award/api/v1/contact-usages/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}

### Get All Contact Usages [GET /award/api/v1/contact-usages/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"},
              {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Contact Usages with Filtering [GET /award/api/v1/contact-usages/]
    
+ Parameters

    + contactUsageId (optional) - Contact Usage Id. Maximum length is 12.
    + contactTypeCode (optional) - Contact Type Code. Maximum length is 3.
    + moduleCode (optional) - Module Code. Maximum length is 3.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"},
              {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Contact Usages [GET /award/api/v1/contact-usages/]
	                                          
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
    
            {"columns":["contactUsageId","contactTypeCode","moduleCode"],"primaryKey":"contactTypeCode:contactUsageId:moduleCode"}
		
### Get Blueprint API specification for Contact Usages [GET /award/api/v1/contact-usages/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Contact Usages.md"
            transfer-encoding:chunked


### Update Contact Usages [PUT /award/api/v1/contact-usages/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Contact Usages [PUT /award/api/v1/contact-usages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"},
              {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Contact Usages [POST /award/api/v1/contact-usages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Contact Usages [POST /award/api/v1/contact-usages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"},
              {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"},
              {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Contact Usages by Key [DELETE /award/api/v1/contact-usages/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Contact Usages [DELETE /award/api/v1/contact-usages/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Contact Usages with Matching [DELETE /award/api/v1/contact-usages/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + contactUsageId (optional) - Contact Usage Id. Maximum length is 12.
    + contactTypeCode (optional) - Contact Type Code. Maximum length is 3.
    + moduleCode (optional) - Module Code. Maximum length is 3.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
