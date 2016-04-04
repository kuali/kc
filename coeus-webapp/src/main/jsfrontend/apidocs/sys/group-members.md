## Group Members [/research-sys/api/v1/group-members/]

### Get Group Members by Key [GET /research-sys/api/v1/group-members/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","groupId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}

### Get All Group Members [GET /research-sys/api/v1/group-members/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","groupId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","groupId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Group Members with Filtering [GET /research-sys/api/v1/group-members/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + groupId
            + activeFromDateValue
            + activeToDateValue
            + memberId
            + typeCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","groupId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","groupId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Group Members [GET /research-sys/api/v1/group-members/]
	 
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
		
### Get Blueprint API specification for Group Members [GET /research-sys/api/v1/group-members/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Group Members.md"
            transfer-encoding:chunked


### Update Group Members [PUT /research-sys/api/v1/group-members/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","groupId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Group Members [PUT /research-sys/api/v1/group-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","groupId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","groupId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Group Members [POST /research-sys/api/v1/group-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","groupId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","groupId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Group Members [POST /research-sys/api/v1/group-members/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","groupId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","groupId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","groupId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","groupId": "(val)","activeFromDateValue": "(val)","activeToDateValue": "(val)","memberId": "(val)","typeCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Group Members by Key [DELETE /research-sys/api/v1/group-members/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Group Members [DELETE /research-sys/api/v1/group-members/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Group Members with Matching [DELETE /research-sys/api/v1/group-members/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + groupId
            + activeFromDateValue
            + activeToDateValue
            + memberId
            + typeCode


+ Response 204
