## Iacuc Committee Research Areas [/research-sys/api/v1/iacuc-committee-research-areas/]

### Get Iacuc Committee Research Areas by Key [GET /research-sys/api/v1/iacuc-committee-research-areas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","committeeIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}

### Get All Iacuc Committee Research Areas [GET /research-sys/api/v1/iacuc-committee-research-areas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","committeeIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","committeeIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Iacuc Committee Research Areas with Filtering [GET /research-sys/api/v1/iacuc-committee-research-areas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + id
            + committeeIdFk
            + researchAreaCode
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","committeeIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","committeeIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Iacuc Committee Research Areas [GET /research-sys/api/v1/iacuc-committee-research-areas/]
	 
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
		
### Get Blueprint API specification for Iacuc Committee Research Areas [GET /research-sys/api/v1/iacuc-committee-research-areas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Iacuc Committee Research Areas.md"
            transfer-encoding:chunked


### Update Iacuc Committee Research Areas [PUT /research-sys/api/v1/iacuc-committee-research-areas/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","committeeIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Iacuc Committee Research Areas [PUT /research-sys/api/v1/iacuc-committee-research-areas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","committeeIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","committeeIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Iacuc Committee Research Areas [POST /research-sys/api/v1/iacuc-committee-research-areas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","committeeIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","committeeIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Iacuc Committee Research Areas [POST /research-sys/api/v1/iacuc-committee-research-areas/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","committeeIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","committeeIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","committeeIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","committeeIdFk": "(val)","researchAreaCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Iacuc Committee Research Areas by Key [DELETE /research-sys/api/v1/iacuc-committee-research-areas/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Iacuc Committee Research Areas [DELETE /research-sys/api/v1/iacuc-committee-research-areas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Iacuc Committee Research Areas with Matching [DELETE /research-sys/api/v1/iacuc-committee-research-areas/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + id
            + committeeIdFk
            + researchAreaCode


+ Response 204
