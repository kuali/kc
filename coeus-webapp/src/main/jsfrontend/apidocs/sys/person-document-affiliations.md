## Person Document Affiliations [/research-sys/api/v1/person-document-affiliations/]

### Get Person Document Affiliations by Key [GET /research-sys/api/v1/person-document-affiliations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"entityAffiliationId": "(val)","affiliationTypeCode": "(val)","campusCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Person Document Affiliations [GET /research-sys/api/v1/person-document-affiliations/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"entityAffiliationId": "(val)","affiliationTypeCode": "(val)","campusCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityAffiliationId": "(val)","affiliationTypeCode": "(val)","campusCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Person Document Affiliations with Filtering [GET /research-sys/api/v1/person-document-affiliations/]
    
+ Parameters

        + entityAffiliationId
            + affiliationTypeCode
            + campusCode
            + dflt
            + edit
            + documentNumber
            + active

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"entityAffiliationId": "(val)","affiliationTypeCode": "(val)","campusCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityAffiliationId": "(val)","affiliationTypeCode": "(val)","campusCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Person Document Affiliations [GET /research-sys/api/v1/person-document-affiliations/]
	                                          
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
    
            {"columns":["entityAffiliationId","affiliationTypeCode","campusCode","dflt","edit","documentNumber","active"],"primaryKey":"entityAffiliationId"}
		
### Get Blueprint API specification for Person Document Affiliations [GET /research-sys/api/v1/person-document-affiliations/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Person Document Affiliations.md"
            transfer-encoding:chunked


### Update Person Document Affiliations [PUT /research-sys/api/v1/person-document-affiliations/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityAffiliationId": "(val)","affiliationTypeCode": "(val)","campusCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Person Document Affiliations [PUT /research-sys/api/v1/person-document-affiliations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityAffiliationId": "(val)","affiliationTypeCode": "(val)","campusCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityAffiliationId": "(val)","affiliationTypeCode": "(val)","campusCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Person Document Affiliations [POST /research-sys/api/v1/person-document-affiliations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"entityAffiliationId": "(val)","affiliationTypeCode": "(val)","campusCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"entityAffiliationId": "(val)","affiliationTypeCode": "(val)","campusCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Person Document Affiliations [POST /research-sys/api/v1/person-document-affiliations/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"entityAffiliationId": "(val)","affiliationTypeCode": "(val)","campusCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityAffiliationId": "(val)","affiliationTypeCode": "(val)","campusCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"entityAffiliationId": "(val)","affiliationTypeCode": "(val)","campusCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"entityAffiliationId": "(val)","affiliationTypeCode": "(val)","campusCode": "(val)","dflt": "(val)","edit": "(val)","documentNumber": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Person Document Affiliations by Key [DELETE /research-sys/api/v1/person-document-affiliations/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Affiliations [DELETE /research-sys/api/v1/person-document-affiliations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Person Document Affiliations with Matching [DELETE /research-sys/api/v1/person-document-affiliations/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + entityAffiliationId
            + affiliationTypeCode
            + campusCode
            + dflt
            + edit
            + documentNumber
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
