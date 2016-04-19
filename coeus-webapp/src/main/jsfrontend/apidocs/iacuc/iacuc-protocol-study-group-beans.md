## Iacuc Protocol Study Group Beans [/iacuc/api/v1/iacuc-protocol-study-group-beans/]

### Get Iacuc Protocol Study Group Beans by Key [GET /iacuc/api/v1/iacuc-protocol-study-group-beans/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Protocol Study Group Beans [GET /iacuc/api/v1/iacuc-protocol-study-group-beans/]
	 
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

### Get All Iacuc Protocol Study Group Beans with Filtering [GET /iacuc/api/v1/iacuc-protocol-study-group-beans/]
    
+ Parameters

    + iacucProtocolStudyGroupHeaderId (optional) - Protocol Study Group Header Id. Maximum length is 22.
    + protocolId (optional) - Protocol Id. Maximum length is 22.
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + procedureCategoryCode (optional) - Procedure Category. Maximum length is 3.
    + procedureCode (optional) - Procedure Code. Maximum length is 4.

            
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
			
### Get Schema for Iacuc Protocol Study Group Beans [GET /iacuc/api/v1/iacuc-protocol-study-group-beans/]
	                                          
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
    
            {"columns":["iacucProtocolStudyGroupHeaderId","protocolId","protocolNumber","sequenceNumber","procedureCategoryCode","procedureCode"],"primaryKey":"iacucProtocolStudyGroupHeaderId"}
		
### Get Blueprint API specification for Iacuc Protocol Study Group Beans [GET /iacuc/api/v1/iacuc-protocol-study-group-beans/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Protocol Study Group Beans.md"
            transfer-encoding:chunked


### Update Iacuc Protocol Study Group Beans [PUT /iacuc/api/v1/iacuc-protocol-study-group-beans/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Protocol Study Group Beans [PUT /iacuc/api/v1/iacuc-protocol-study-group-beans/]

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

### Insert Iacuc Protocol Study Group Beans [POST /iacuc/api/v1/iacuc-protocol-study-group-beans/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"iacucProtocolStudyGroupHeaderId": "(val)","protocolId": "(val)","protocolNumber": "(val)","sequenceNumber": "(val)","procedureCategoryCode": "(val)","procedureCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Protocol Study Group Beans [POST /iacuc/api/v1/iacuc-protocol-study-group-beans/]

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
            
### Delete Iacuc Protocol Study Group Beans by Key [DELETE /iacuc/api/v1/iacuc-protocol-study-group-beans/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Study Group Beans [DELETE /iacuc/api/v1/iacuc-protocol-study-group-beans/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Protocol Study Group Beans with Matching [DELETE /iacuc/api/v1/iacuc-protocol-study-group-beans/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + iacucProtocolStudyGroupHeaderId (optional) - Protocol Study Group Header Id. Maximum length is 22.
    + protocolId (optional) - Protocol Id. Maximum length is 22.
    + protocolNumber (optional) - 
    + sequenceNumber (optional) - 
    + procedureCategoryCode (optional) - Procedure Category. Maximum length is 3.
    + procedureCode (optional) - Procedure Code. Maximum length is 4.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
