## Role Document Delegation Member Qualifiers [/research-sys/api/v1/role-document-delegation-member-qualifiers/]

### Get Role Document Delegation Member Qualifiers by Key [GET /research-sys/api/v1/role-document-delegation-member-qualifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"delegationMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}

### Get All Role Document Delegation Member Qualifiers [GET /research-sys/api/v1/role-document-delegation-member-qualifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"delegationMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"},
              {"delegationMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Role Document Delegation Member Qualifiers with Filtering [GET /research-sys/api/v1/role-document-delegation-member-qualifiers/]
    
+ Parameters

        + delegationMemberId
            + kimTypId
            + kimAttrDefnId
            + edit
            + documentNumber
            + attrVal
            + active
            + attrDataId

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"delegationMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"},
              {"delegationMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Role Document Delegation Member Qualifiers [GET /research-sys/api/v1/role-document-delegation-member-qualifiers/]
	                                          
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
    
            {"columns":["delegationMemberId","kimTypId","kimAttrDefnId","edit","documentNumber","attrVal","active","attrDataId"],"primaryKey":"attrDataId"}
		
### Get Blueprint API specification for Role Document Delegation Member Qualifiers [GET /research-sys/api/v1/role-document-delegation-member-qualifiers/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Role Document Delegation Member Qualifiers.md"
            transfer-encoding:chunked


### Update Role Document Delegation Member Qualifiers [PUT /research-sys/api/v1/role-document-delegation-member-qualifiers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"delegationMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Role Document Delegation Member Qualifiers [PUT /research-sys/api/v1/role-document-delegation-member-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"delegationMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"},
              {"delegationMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Role Document Delegation Member Qualifiers [POST /research-sys/api/v1/role-document-delegation-member-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"delegationMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"delegationMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Role Document Delegation Member Qualifiers [POST /research-sys/api/v1/role-document-delegation-member-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"delegationMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"},
              {"delegationMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"delegationMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"},
              {"delegationMemberId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Role Document Delegation Member Qualifiers by Key [DELETE /research-sys/api/v1/role-document-delegation-member-qualifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Role Document Delegation Member Qualifiers [DELETE /research-sys/api/v1/role-document-delegation-member-qualifiers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Role Document Delegation Member Qualifiers with Matching [DELETE /research-sys/api/v1/role-document-delegation-member-qualifiers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + delegationMemberId
            + kimTypId
            + kimAttrDefnId
            + edit
            + documentNumber
            + attrVal
            + active
            + attrDataId

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
