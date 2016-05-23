## Custom Attributes [/research-common/api/v1/custom-attributes/]

### Get Custom Attributes by Key [GET /research-common/api/v1/custom-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}

### Get All Custom Attributes [GET /research-common/api/v1/custom-attributes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]

### Get All Custom Attributes with Filtering [GET /research-common/api/v1/custom-attributes/]
    
+ Parameters

    + id (optional) - Id. Maximum length is 12.
    + dataLength (optional) - Data Length. Maximum length is 4.
    + dataTypeCode (optional) - Data Type Code. Maximum length is 3.
    + defaultValue (optional) - Default Value. Maximum length is 2000.
    + groupName (optional) - Group Name. Maximum length is 250.
    + label (optional) - Label. Maximum length is 30.
    + lookupClass (optional) - Lookup Class. Maximum length is 100.
    + lookupReturn (optional) - Lookup Return. Maximum length is 30.
    + name (optional) - Name. Maximum length is 30.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Custom Attributes [GET /research-common/api/v1/custom-attributes/]
	                                          
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
    
            {"columns":["id","dataLength","dataTypeCode","defaultValue","groupName","label","lookupClass","lookupReturn","name"],"primaryKey":"id"}
		
### Get Blueprint API specification for Custom Attributes [GET /research-common/api/v1/custom-attributes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Custom Attributes.md"
            transfer-encoding:chunked
### Update Custom Attributes [PUT /research-common/api/v1/custom-attributes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Custom Attributes [PUT /research-common/api/v1/custom-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Custom Attributes [POST /research-common/api/v1/custom-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Custom Attributes [POST /research-common/api/v1/custom-attributes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","dataLength": "(val)","dataTypeCode": "(val)","defaultValue": "(val)","groupName": "(val)","label": "(val)","lookupClass": "(val)","lookupReturn": "(val)","name": "(val)","_primaryKey": "(val)"}
            ]
### Delete Custom Attributes by Key [DELETE /research-common/api/v1/custom-attributes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Attributes [DELETE /research-common/api/v1/custom-attributes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Custom Attributes with Matching [DELETE /research-common/api/v1/custom-attributes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + id (optional) - Id. Maximum length is 12.
    + dataLength (optional) - Data Length. Maximum length is 4.
    + dataTypeCode (optional) - Data Type Code. Maximum length is 3.
    + defaultValue (optional) - Default Value. Maximum length is 2000.
    + groupName (optional) - Group Name. Maximum length is 250.
    + label (optional) - Label. Maximum length is 30.
    + lookupClass (optional) - Lookup Class. Maximum length is 100.
    + lookupReturn (optional) - Lookup Return. Maximum length is 30.
    + name (optional) - Name. Maximum length is 30.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
