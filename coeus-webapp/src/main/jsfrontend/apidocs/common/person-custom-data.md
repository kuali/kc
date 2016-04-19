## Person Custom Data [/research-common/api/v1/person-custom-data/]

### Get Person Custom Data by Key [GET /research-common/api/v1/person-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Person Custom Data [GET /research-common/api/v1/person-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Custom Data with Filtering [GET /research-common/api/v1/person-custom-data/]
    
+ Parameters

    + personCustomDataId (optional) - Person Custom Data Id. Maximum length is 22.
    + personId (optional) - KcPersonExtendedAttributes Id. Maximum length is 40.
    + customAttributeId (optional) - Custom Attribute Id. Maximum length is 22.
    + value (optional) - Value. Maximum length is 2000.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Custom Data [GET /research-common/api/v1/person-custom-data/]
	                                          
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
    
            {"columns":["personCustomDataId","personId","customAttributeId","value"],"primaryKey":"personCustomDataId"}
		
### Get Blueprint API specification for Person Custom Data [GET /research-common/api/v1/person-custom-data/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Custom Data.md"
            transfer-encoding:chunked


### Update Person Custom Data [PUT /research-common/api/v1/person-custom-data/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Custom Data [PUT /research-common/api/v1/person-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Person Custom Data [POST /research-common/api/v1/person-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Custom Data [POST /research-common/api/v1/person-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Person Custom Data by Key [DELETE /research-common/api/v1/person-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Custom Data [DELETE /research-common/api/v1/person-custom-data/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Custom Data with Matching [DELETE /research-common/api/v1/person-custom-data/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + personCustomDataId (optional) - Person Custom Data Id. Maximum length is 22.
    + personId (optional) - KcPersonExtendedAttributes Id. Maximum length is 40.
    + customAttributeId (optional) - Custom Attribute Id. Maximum length is 22.
    + value (optional) - Value. Maximum length is 2000.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
