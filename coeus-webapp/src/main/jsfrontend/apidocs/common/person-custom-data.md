## Person Custom Data [/research-sys/api/v1/person-custom-data/]

### Get Person Custom Data by Key [GET /research-sys/api/v1/person-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Person Custom Data [GET /research-sys/api/v1/person-custom-data/]
	 
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

### Get All Person Custom Data with Filtering [GET /research-sys/api/v1/person-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + personCustomDataId
            + personId
            + customAttributeId
            + value
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Custom Data [GET /research-sys/api/v1/person-custom-data/]
	 
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
		
### Get Blueprint API specification for Person Custom Data [GET /research-sys/api/v1/person-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Custom Data.md"
            transfer-encoding:chunked


### Update Person Custom Data [PUT /research-sys/api/v1/person-custom-data/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Custom Data [PUT /research-sys/api/v1/person-custom-data/]

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

### Insert Person Custom Data [POST /research-sys/api/v1/person-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"personCustomDataId": "(val)","personId": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Custom Data [POST /research-sys/api/v1/person-custom-data/]

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
            
### Delete Person Custom Data by Key [DELETE /research-sys/api/v1/person-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Custom Data [DELETE /research-sys/api/v1/person-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Person Custom Data with Matching [DELETE /research-sys/api/v1/person-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + personCustomDataId
            + personId
            + customAttributeId
            + value


+ Response 204
