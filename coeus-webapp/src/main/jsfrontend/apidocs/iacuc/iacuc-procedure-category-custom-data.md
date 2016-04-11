## Iacuc Procedure Category Custom Data [/research-sys/api/v1/iacuc-procedure-category-custom-data/]

### Get Iacuc Procedure Category Custom Data by Key [GET /research-sys/api/v1/iacuc-procedure-category-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Procedure Category Custom Data [GET /research-sys/api/v1/iacuc-procedure-category-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Procedure Category Custom Data with Filtering [GET /research-sys/api/v1/iacuc-procedure-category-custom-data/]
    
+ Parameters

        + id
            + procedureCategoryCode
            + dataLength
            + dataTypeCode
            + defaultValue
            + label
            + lookupClass
            + lookupReturn
            + name
            + active
            + sortId

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Procedure Category Custom Data [GET /research-sys/api/v1/iacuc-procedure-category-custom-data/]
	                                          
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
    
            {"columns":["id","procedureCategoryCode","dataLength","dataTypeCode","defaultValue","label","lookupClass","lookupReturn","name","active","sortId"],"primaryKey":"id"}
		
### Get Blueprint API specification for Iacuc Procedure Category Custom Data [GET /research-sys/api/v1/iacuc-procedure-category-custom-data/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Procedure Category Custom Data.md"
            transfer-encoding:chunked


### Update Iacuc Procedure Category Custom Data [PUT /research-sys/api/v1/iacuc-procedure-category-custom-data/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Procedure Category Custom Data [PUT /research-sys/api/v1/iacuc-procedure-category-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Procedure Category Custom Data [POST /research-sys/api/v1/iacuc-procedure-category-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Procedure Category Custom Data [POST /research-sys/api/v1/iacuc-procedure-category-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","procedureCategoryCode": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","active": "(val)","sortId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Procedure Category Custom Data by Key [DELETE /research-sys/api/v1/iacuc-procedure-category-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Procedure Category Custom Data [DELETE /research-sys/api/v1/iacuc-procedure-category-custom-data/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Procedure Category Custom Data with Matching [DELETE /research-sys/api/v1/iacuc-procedure-category-custom-data/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + procedureCategoryCode
            + dataLength
            + dataTypeCode
            + defaultValue
            + label
            + lookupClass
            + lookupReturn
            + name
            + active
            + sortId

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
