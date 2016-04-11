## Group Document Qualifiers [/research-sys/api/v1/group-document-qualifiers/]

### Get Group Document Qualifiers by Key [GET /research-sys/api/v1/group-document-qualifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"groupId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}

### Get All Group Document Qualifiers [GET /research-sys/api/v1/group-document-qualifiers/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"groupId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"},
              {"groupId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            ]

### Get All Group Document Qualifiers with Filtering [GET /research-sys/api/v1/group-document-qualifiers/]
    
+ Parameters

        + groupId
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
              {"groupId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"},
              {"groupId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Group Document Qualifiers [GET /research-sys/api/v1/group-document-qualifiers/]
	                                          
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
    
            {"columns":["groupId","kimTypId","kimAttrDefnId","edit","documentNumber","attrVal","active","attrDataId"],"primaryKey":"attrDataId"}
		
### Get Blueprint API specification for Group Document Qualifiers [GET /research-sys/api/v1/group-document-qualifiers/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Group Document Qualifiers.md"
            transfer-encoding:chunked


### Update Group Document Qualifiers [PUT /research-sys/api/v1/group-document-qualifiers/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"groupId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Group Document Qualifiers [PUT /research-sys/api/v1/group-document-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"groupId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"},
              {"groupId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Group Document Qualifiers [POST /research-sys/api/v1/group-document-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"groupId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"groupId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Group Document Qualifiers [POST /research-sys/api/v1/group-document-qualifiers/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"groupId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"},
              {"groupId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"groupId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"},
              {"groupId": "(val)","kimTypId": "(val)","kimAttrDefnId": "(val)","edit": "(val)","documentNumber": "(val)","attrVal": "(val)","active": "(val)","attrDataId": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Group Document Qualifiers by Key [DELETE /research-sys/api/v1/group-document-qualifiers/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Group Document Qualifiers [DELETE /research-sys/api/v1/group-document-qualifiers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Group Document Qualifiers with Matching [DELETE /research-sys/api/v1/group-document-qualifiers/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + groupId
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
