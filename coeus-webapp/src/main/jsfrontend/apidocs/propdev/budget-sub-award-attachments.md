## Budget Sub Award Attachments [/research-sys/api/v1/budget-sub-award-attachments/]

### Get Budget Sub Award Attachments by Key [GET /research-sys/api/v1/budget-sub-award-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"data": "(val)","name": "(val)","type": "(val)","id": "(val)","_primaryKey": "(val)"}

### Get All Budget Sub Award Attachments [GET /research-sys/api/v1/budget-sub-award-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"data": "(val)","name": "(val)","type": "(val)","id": "(val)","_primaryKey": "(val)"},
              {"data": "(val)","name": "(val)","type": "(val)","id": "(val)","_primaryKey": "(val)"}
            ]

### Get All Budget Sub Award Attachments with Filtering [GET /research-sys/api/v1/budget-sub-award-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + data
            + name
            + type
            + id
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"data": "(val)","name": "(val)","type": "(val)","id": "(val)","_primaryKey": "(val)"},
              {"data": "(val)","name": "(val)","type": "(val)","id": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Budget Sub Award Attachments [GET /research-sys/api/v1/budget-sub-award-attachments/]
	 
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
		
### Get Blueprint API specification for Budget Sub Award Attachments [GET /research-sys/api/v1/budget-sub-award-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Budget Sub Award Attachments.md"
            transfer-encoding:chunked


### Update Budget Sub Award Attachments [PUT /research-sys/api/v1/budget-sub-award-attachments/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"data": "(val)","name": "(val)","type": "(val)","id": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Budget Sub Award Attachments [PUT /research-sys/api/v1/budget-sub-award-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"data": "(val)","name": "(val)","type": "(val)","id": "(val)","_primaryKey": "(val)"},
              {"data": "(val)","name": "(val)","type": "(val)","id": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Budget Sub Award Attachments [POST /research-sys/api/v1/budget-sub-award-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"data": "(val)","name": "(val)","type": "(val)","id": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"data": "(val)","name": "(val)","type": "(val)","id": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Budget Sub Award Attachments [POST /research-sys/api/v1/budget-sub-award-attachments/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"data": "(val)","name": "(val)","type": "(val)","id": "(val)","_primaryKey": "(val)"},
              {"data": "(val)","name": "(val)","type": "(val)","id": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"data": "(val)","name": "(val)","type": "(val)","id": "(val)","_primaryKey": "(val)"},
              {"data": "(val)","name": "(val)","type": "(val)","id": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Budget Sub Award Attachments by Key [DELETE /research-sys/api/v1/budget-sub-award-attachments/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Budget Sub Award Attachments [DELETE /research-sys/api/v1/budget-sub-award-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Budget Sub Award Attachments with Matching [DELETE /research-sys/api/v1/budget-sub-award-attachments/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + data
            + name
            + type
            + id


+ Response 204
