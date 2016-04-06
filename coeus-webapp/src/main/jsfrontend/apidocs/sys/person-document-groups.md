## Person Document Groups [/research-sys/api/v1/person-document-groups/]

### Get Person Document Groups by Key [GET /research-sys/api/v1/person-document-groups/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"groupMemberId": "(val)","groupType": "(val)","groupId": "(val)","groupName": "(val)","namespaceCode": "(val)","principalId": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Person Document Groups [GET /research-sys/api/v1/person-document-groups/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"groupMemberId": "(val)","groupType": "(val)","groupId": "(val)","groupName": "(val)","namespaceCode": "(val)","principalId": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"groupMemberId": "(val)","groupType": "(val)","groupId": "(val)","groupName": "(val)","namespaceCode": "(val)","principalId": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Document Groups with Filtering [GET /research-sys/api/v1/person-document-groups/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + groupMemberId
            + groupType
            + groupId
            + groupName
            + namespaceCode
            + principalId
            + activeFromDate
            + activeToDate
            + edit
            + documentNumber
            + active
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"groupMemberId": "(val)","groupType": "(val)","groupId": "(val)","groupName": "(val)","namespaceCode": "(val)","principalId": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"groupMemberId": "(val)","groupType": "(val)","groupId": "(val)","groupName": "(val)","namespaceCode": "(val)","principalId": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Document Groups [GET /research-sys/api/v1/person-document-groups/]
	 
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
		
### Get Blueprint API specification for Person Document Groups [GET /research-sys/api/v1/person-document-groups/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Document Groups.md"
            transfer-encoding:chunked


### Update Person Document Groups [PUT /research-sys/api/v1/person-document-groups/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"groupMemberId": "(val)","groupType": "(val)","groupId": "(val)","groupName": "(val)","namespaceCode": "(val)","principalId": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Document Groups [PUT /research-sys/api/v1/person-document-groups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"groupMemberId": "(val)","groupType": "(val)","groupId": "(val)","groupName": "(val)","namespaceCode": "(val)","principalId": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"groupMemberId": "(val)","groupType": "(val)","groupId": "(val)","groupName": "(val)","namespaceCode": "(val)","principalId": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Person Document Groups [POST /research-sys/api/v1/person-document-groups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"groupMemberId": "(val)","groupType": "(val)","groupId": "(val)","groupName": "(val)","namespaceCode": "(val)","principalId": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"groupMemberId": "(val)","groupType": "(val)","groupId": "(val)","groupName": "(val)","namespaceCode": "(val)","principalId": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Document Groups [POST /research-sys/api/v1/person-document-groups/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"groupMemberId": "(val)","groupType": "(val)","groupId": "(val)","groupName": "(val)","namespaceCode": "(val)","principalId": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"groupMemberId": "(val)","groupType": "(val)","groupId": "(val)","groupName": "(val)","namespaceCode": "(val)","principalId": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"groupMemberId": "(val)","groupType": "(val)","groupId": "(val)","groupName": "(val)","namespaceCode": "(val)","principalId": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"groupMemberId": "(val)","groupType": "(val)","groupId": "(val)","groupName": "(val)","namespaceCode": "(val)","principalId": "(val)","activeFromDate": "(val)","activeToDate": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Person Document Groups by Key [DELETE /research-sys/api/v1/person-document-groups/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Groups [DELETE /research-sys/api/v1/person-document-groups/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Person Document Groups with Matching [DELETE /research-sys/api/v1/person-document-groups/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + groupMemberId
            + groupType
            + groupId
            + groupName
            + namespaceCode
            + principalId
            + activeFromDate
            + activeToDate
            + edit
            + documentNumber
            + active


+ Response 204
