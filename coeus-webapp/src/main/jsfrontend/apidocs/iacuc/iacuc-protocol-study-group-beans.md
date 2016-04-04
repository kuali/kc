## Iacuc Protocol Study Group Beans [/research-sys/api/v1/iacuc-protocol-study-group-beans/]

### Get Iacuc Protocol Study Group Beans by Key [GET /research-sys/api/v1/iacuc-protocol-study-group-beans/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Study Group Beans [GET /research-sys/api/v1/iacuc-protocol-study-group-beans/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Protocol Study Group Beans with Filtering [GET /research-sys/api/v1/iacuc-protocol-study-group-beans/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + iacucProtocolStudyGroupHeaderId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + procedureCategoryCode
            + procedureCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Protocol Study Group Beans [GET /research-sys/api/v1/iacuc-protocol-study-group-beans/]
	 
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
		
### Get Blueprint API specification for Iacuc Protocol Study Group Beans [GET /research-sys/api/v1/iacuc-protocol-study-group-beans/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Study Group Beans.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Study Group Beans [PUT /research-sys/api/v1/iacuc-protocol-study-group-beans/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Study Group Beans [PUT /research-sys/api/v1/iacuc-protocol-study-group-beans/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Protocol Study Group Beans [POST /research-sys/api/v1/iacuc-protocol-study-group-beans/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Study Group Beans [POST /research-sys/api/v1/iacuc-protocol-study-group-beans/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"},
              {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Protocol Study Group Beans by Key [DELETE /research-sys/api/v1/iacuc-protocol-study-group-beans/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Study Group Beans [DELETE /research-sys/api/v1/iacuc-protocol-study-group-beans/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Protocol Study Group Beans with Matching [DELETE /research-sys/api/v1/iacuc-protocol-study-group-beans/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + iacucProtocolStudyGroupHeaderId
            + protocolId
            + protocolNumber
            + sequenceNumber
            + procedureCategoryCode
            + procedureCode


+ Response 204
