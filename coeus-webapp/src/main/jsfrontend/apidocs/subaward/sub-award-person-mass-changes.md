## Sub Award Person Mass Changes [/subaward/api/v1/sub-award-person-mass-changes/]

### Get Sub Award Person Mass Changes by Key [GET /subaward/api/v1/sub-award-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"subawardPersonMassChangeId": "(val)","personMassChangeId": "(val)","requisitioner": "(val)","contact": "(val)","_primaryKey": "(val)"}

### Get All Sub Award Person Mass Changes [GET /subaward/api/v1/sub-award-person-mass-changes/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subawardPersonMassChangeId": "(val)","personMassChangeId": "(val)","requisitioner": "(val)","contact": "(val)","_primaryKey": "(val)"},
              {"subawardPersonMassChangeId": "(val)","personMassChangeId": "(val)","requisitioner": "(val)","contact": "(val)","_primaryKey": "(val)"}
            ]

### Get All Sub Award Person Mass Changes with Filtering [GET /subaward/api/v1/sub-award-person-mass-changes/]
    
+ Parameters

    + subawardPersonMassChangeId (optional) - Subaward Person Mass Change Id. Maximum length is 12.
    + personMassChangeId (optional) - Person Mass Change Id. Maximum length is 12.
    + requisitioner (optional) - Requisitioner. Maximum length is 1.
    + contact (optional) - Contact. Maximum length is 1.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"subawardPersonMassChangeId": "(val)","personMassChangeId": "(val)","requisitioner": "(val)","contact": "(val)","_primaryKey": "(val)"},
              {"subawardPersonMassChangeId": "(val)","personMassChangeId": "(val)","requisitioner": "(val)","contact": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Sub Award Person Mass Changes [GET /subaward/api/v1/sub-award-person-mass-changes/]
	                                          
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
    
            {"columns":["subawardPersonMassChangeId","personMassChangeId","requisitioner","contact"],"primaryKey":"subawardPersonMassChangeId"}
		
### Get Blueprint API specification for Sub Award Person Mass Changes [GET /subaward/api/v1/sub-award-person-mass-changes/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Sub Award Person Mass Changes.md"
            transfer-encoding:chunked
### Update Sub Award Person Mass Changes [PUT /subaward/api/v1/sub-award-person-mass-changes/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subawardPersonMassChangeId": "(val)","personMassChangeId": "(val)","requisitioner": "(val)","contact": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Sub Award Person Mass Changes [PUT /subaward/api/v1/sub-award-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subawardPersonMassChangeId": "(val)","personMassChangeId": "(val)","requisitioner": "(val)","contact": "(val)","_primaryKey": "(val)"},
              {"subawardPersonMassChangeId": "(val)","personMassChangeId": "(val)","requisitioner": "(val)","contact": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204
### Insert Sub Award Person Mass Changes [POST /subaward/api/v1/sub-award-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"subawardPersonMassChangeId": "(val)","personMassChangeId": "(val)","requisitioner": "(val)","contact": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"subawardPersonMassChangeId": "(val)","personMassChangeId": "(val)","requisitioner": "(val)","contact": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Sub Award Person Mass Changes [POST /subaward/api/v1/sub-award-person-mass-changes/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"subawardPersonMassChangeId": "(val)","personMassChangeId": "(val)","requisitioner": "(val)","contact": "(val)","_primaryKey": "(val)"},
              {"subawardPersonMassChangeId": "(val)","personMassChangeId": "(val)","requisitioner": "(val)","contact": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"subawardPersonMassChangeId": "(val)","personMassChangeId": "(val)","requisitioner": "(val)","contact": "(val)","_primaryKey": "(val)"},
              {"subawardPersonMassChangeId": "(val)","personMassChangeId": "(val)","requisitioner": "(val)","contact": "(val)","_primaryKey": "(val)"}
            ]
### Delete Sub Award Person Mass Changes by Key [DELETE /subaward/api/v1/sub-award-person-mass-changes/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Person Mass Changes [DELETE /subaward/api/v1/sub-award-person-mass-changes/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Sub Award Person Mass Changes with Matching [DELETE /subaward/api/v1/sub-award-person-mass-changes/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + subawardPersonMassChangeId (optional) - Subaward Person Mass Change Id. Maximum length is 12.
    + personMassChangeId (optional) - Person Mass Change Id. Maximum length is 12.
    + requisitioner (optional) - Requisitioner. Maximum length is 1.
    + contact (optional) - Contact. Maximum length is 1.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
