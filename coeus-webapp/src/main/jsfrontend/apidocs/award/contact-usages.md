## Contact Usages [/research-sys/api/v1/contact-usages/]

### Get Contact Usages by Key [GET /research-sys/api/v1/contact-usages/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}

### Get All Contact Usages [GET /research-sys/api/v1/contact-usages/]
	 
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

### Get All Contact Usages with Filtering [GET /research-sys/api/v1/contact-usages/]
    
+ Parameters

        + contactUsageId
            + contactTypeCode
            + moduleCode

            
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
			
### Get Schema for Contact Usages [GET /research-sys/api/v1/contact-usages/]
	                                          
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
		
### Get Blueprint API specification for Contact Usages [GET /research-sys/api/v1/contact-usages/]
	 
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


### Update Contact Usages [PUT /research-sys/api/v1/contact-usages/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Contact Usages [PUT /research-sys/api/v1/contact-usages/]

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

### Insert Contact Usages [POST /research-sys/api/v1/contact-usages/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"contactUsageId": "(val)","contactTypeCode": "(val)","moduleCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Contact Usages [POST /research-sys/api/v1/contact-usages/]

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
            
### Delete Contact Usages by Key [DELETE /research-sys/api/v1/contact-usages/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Contact Usages [DELETE /research-sys/api/v1/contact-usages/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Contact Usages with Matching [DELETE /research-sys/api/v1/contact-usages/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + contactUsageId
            + contactTypeCode
            + moduleCode

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
