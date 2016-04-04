## Entity Names [/research-sys/api/v1/entity-names/]

### Get Entity Names by Key [GET /research-sys/api/v1/entity-names/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","lastName": "(val)","defaultValue": "(val)","nameTitle": "(val)","active": "(val)","nameSuffix": "(val)","entityId": "(val)","noteMessage": "(val)","firstName": "(val)","nameCode": "(val)","namePrefix": "(val)","nameChangedDate": "(val)","middleName": "(val)","_primaryKey": "(val)"}

### Get All Entity Names [GET /research-sys/api/v1/entity-names/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","lastName": "(val)","defaultValue": "(val)","nameTitle": "(val)","active": "(val)","nameSuffix": "(val)","entityId": "(val)","noteMessage": "(val)","firstName": "(val)","nameCode": "(val)","namePrefix": "(val)","nameChangedDate": "(val)","middleName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","lastName": "(val)","defaultValue": "(val)","nameTitle": "(val)","active": "(val)","nameSuffix": "(val)","entityId": "(val)","noteMessage": "(val)","firstName": "(val)","nameCode": "(val)","namePrefix": "(val)","nameChangedDate": "(val)","middleName": "(val)","_primaryKey": "(val)"}
            ]

### Get All Entity Names with Filtering [GET /research-sys/api/v1/entity-names/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + lastName
            + defaultValue
            + nameTitle
            + active
            + nameSuffix
            + entityId
            + noteMessage
            + firstName
            + nameCode
            + namePrefix
            + nameChangedDate
            + middleName
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","lastName": "(val)","defaultValue": "(val)","nameTitle": "(val)","active": "(val)","nameSuffix": "(val)","entityId": "(val)","noteMessage": "(val)","firstName": "(val)","nameCode": "(val)","namePrefix": "(val)","nameChangedDate": "(val)","middleName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","lastName": "(val)","defaultValue": "(val)","nameTitle": "(val)","active": "(val)","nameSuffix": "(val)","entityId": "(val)","noteMessage": "(val)","firstName": "(val)","nameCode": "(val)","namePrefix": "(val)","nameChangedDate": "(val)","middleName": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Entity Names [GET /research-sys/api/v1/entity-names/]
	 
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
		
### Get Blueprint API specification for Entity Names [GET /research-sys/api/v1/entity-names/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Entity Names.md"
            transfer-encoding:chunked


### Update Entity Names [PUT /research-sys/api/v1/entity-names/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","lastName": "(val)","defaultValue": "(val)","nameTitle": "(val)","active": "(val)","nameSuffix": "(val)","entityId": "(val)","noteMessage": "(val)","firstName": "(val)","nameCode": "(val)","namePrefix": "(val)","nameChangedDate": "(val)","middleName": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Entity Names [PUT /research-sys/api/v1/entity-names/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","lastName": "(val)","defaultValue": "(val)","nameTitle": "(val)","active": "(val)","nameSuffix": "(val)","entityId": "(val)","noteMessage": "(val)","firstName": "(val)","nameCode": "(val)","namePrefix": "(val)","nameChangedDate": "(val)","middleName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","lastName": "(val)","defaultValue": "(val)","nameTitle": "(val)","active": "(val)","nameSuffix": "(val)","entityId": "(val)","noteMessage": "(val)","firstName": "(val)","nameCode": "(val)","namePrefix": "(val)","nameChangedDate": "(val)","middleName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Entity Names [POST /research-sys/api/v1/entity-names/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","lastName": "(val)","defaultValue": "(val)","nameTitle": "(val)","active": "(val)","nameSuffix": "(val)","entityId": "(val)","noteMessage": "(val)","firstName": "(val)","nameCode": "(val)","namePrefix": "(val)","nameChangedDate": "(val)","middleName": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","lastName": "(val)","defaultValue": "(val)","nameTitle": "(val)","active": "(val)","nameSuffix": "(val)","entityId": "(val)","noteMessage": "(val)","firstName": "(val)","nameCode": "(val)","namePrefix": "(val)","nameChangedDate": "(val)","middleName": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Entity Names [POST /research-sys/api/v1/entity-names/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","lastName": "(val)","defaultValue": "(val)","nameTitle": "(val)","active": "(val)","nameSuffix": "(val)","entityId": "(val)","noteMessage": "(val)","firstName": "(val)","nameCode": "(val)","namePrefix": "(val)","nameChangedDate": "(val)","middleName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","lastName": "(val)","defaultValue": "(val)","nameTitle": "(val)","active": "(val)","nameSuffix": "(val)","entityId": "(val)","noteMessage": "(val)","firstName": "(val)","nameCode": "(val)","namePrefix": "(val)","nameChangedDate": "(val)","middleName": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","lastName": "(val)","defaultValue": "(val)","nameTitle": "(val)","active": "(val)","nameSuffix": "(val)","entityId": "(val)","noteMessage": "(val)","firstName": "(val)","nameCode": "(val)","namePrefix": "(val)","nameChangedDate": "(val)","middleName": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","lastName": "(val)","defaultValue": "(val)","nameTitle": "(val)","active": "(val)","nameSuffix": "(val)","entityId": "(val)","noteMessage": "(val)","firstName": "(val)","nameCode": "(val)","namePrefix": "(val)","nameChangedDate": "(val)","middleName": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Entity Names by Key [DELETE /research-sys/api/v1/entity-names/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Entity Names [DELETE /research-sys/api/v1/entity-names/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Entity Names with Matching [DELETE /research-sys/api/v1/entity-names/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + lastName
            + defaultValue
            + nameTitle
            + active
            + nameSuffix
            + entityId
            + noteMessage
            + firstName
            + nameCode
            + namePrefix
            + nameChangedDate
            + middleName


+ Response 204
