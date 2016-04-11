## Sub Award Custom Data [/research-sys/api/v1/sub-award-custom-data/]

### Get Sub Award Custom Data by Key [GET /research-sys/api/v1/sub-award-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subAwardCustomDataId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Custom Data [GET /research-sys/api/v1/sub-award-custom-data/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardCustomDataId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"subAwardCustomDataId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Custom Data with Filtering [GET /research-sys/api/v1/sub-award-custom-data/]
    
+ Parameters

        + subAwardCustomDataId
            + subAwardId
            + sequenceNumber
            + subAwardCode
            + customAttributeId
            + value

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subAwardCustomDataId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"subAwardCustomDataId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Custom Data [GET /research-sys/api/v1/sub-award-custom-data/]
	                                          
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
    
            {"columns":["subAwardCustomDataId","subAwardId","sequenceNumber","subAwardCode","customAttributeId","value"],"primaryKey":"subAwardCustomDataId"}
		
### Get Blueprint API specification for Sub Award Custom Data [GET /research-sys/api/v1/sub-award-custom-data/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Custom Data.md"
            transfer-encoding:chunked


### Update Sub Award Custom Data [PUT /research-sys/api/v1/sub-award-custom-data/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardCustomDataId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Custom Data [PUT /research-sys/api/v1/sub-award-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardCustomDataId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"subAwardCustomDataId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Sub Award Custom Data [POST /research-sys/api/v1/sub-award-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subAwardCustomDataId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subAwardCustomDataId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Custom Data [POST /research-sys/api/v1/sub-award-custom-data/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subAwardCustomDataId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"subAwardCustomDataId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"subAwardCustomDataId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"},
              {"subAwardCustomDataId": "(val)","subAwardId": "(val)","sequenceNumber": "(val)","subAwardCode": "(val)","customAttributeId": "(val)","value": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Sub Award Custom Data by Key [DELETE /research-sys/api/v1/sub-award-custom-data/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Custom Data [DELETE /research-sys/api/v1/sub-award-custom-data/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Custom Data with Matching [DELETE /research-sys/api/v1/sub-award-custom-data/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + subAwardCustomDataId
            + subAwardId
            + sequenceNumber
            + subAwardCode
            + customAttributeId
            + value

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
