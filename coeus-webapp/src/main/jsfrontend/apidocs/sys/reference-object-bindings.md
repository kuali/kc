## Reference Object Bindings [/research-sys/api/v1/reference-object-bindings/]

### Get Reference Object Bindings by Key [GET /research-sys/api/v1/reference-object-bindings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"id": "(val)","collectionName": "(val)","krmsDiscriminatorType": "(val)","krmsObjectId": "(val)","namespace": "(val)","referenceDiscriminatorType": "(val)","referenceObjectId": "(val)","active": "(val)","_primaryKey": "(val)"}

### Get All Reference Object Bindings [GET /research-sys/api/v1/reference-object-bindings/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"id": "(val)","collectionName": "(val)","krmsDiscriminatorType": "(val)","krmsObjectId": "(val)","namespace": "(val)","referenceDiscriminatorType": "(val)","referenceObjectId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","collectionName": "(val)","krmsDiscriminatorType": "(val)","krmsObjectId": "(val)","namespace": "(val)","referenceDiscriminatorType": "(val)","referenceObjectId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]

### Get All Reference Object Bindings with Filtering [GET /research-sys/api/v1/reference-object-bindings/]
    
+ Parameters

        + id
            + collectionName
            + krmsDiscriminatorType
            + krmsObjectId
            + namespace
            + referenceDiscriminatorType
            + referenceObjectId
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
              {"id": "(val)","collectionName": "(val)","krmsDiscriminatorType": "(val)","krmsObjectId": "(val)","namespace": "(val)","referenceDiscriminatorType": "(val)","referenceObjectId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","collectionName": "(val)","krmsDiscriminatorType": "(val)","krmsObjectId": "(val)","namespace": "(val)","referenceDiscriminatorType": "(val)","referenceObjectId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Reference Object Bindings [GET /research-sys/api/v1/reference-object-bindings/]
	                                          
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
    
            {"columns":["id","collectionName","krmsDiscriminatorType","krmsObjectId","namespace","referenceDiscriminatorType","referenceObjectId","active"],"primaryKey":"id"}
		
### Get Blueprint API specification for Reference Object Bindings [GET /research-sys/api/v1/reference-object-bindings/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Reference Object Bindings.md"
            transfer-encoding:chunked


### Update Reference Object Bindings [PUT /research-sys/api/v1/reference-object-bindings/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","collectionName": "(val)","krmsDiscriminatorType": "(val)","krmsObjectId": "(val)","namespace": "(val)","referenceDiscriminatorType": "(val)","referenceObjectId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Reference Object Bindings [PUT /research-sys/api/v1/reference-object-bindings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","collectionName": "(val)","krmsDiscriminatorType": "(val)","krmsObjectId": "(val)","namespace": "(val)","referenceDiscriminatorType": "(val)","referenceObjectId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","collectionName": "(val)","krmsDiscriminatorType": "(val)","krmsObjectId": "(val)","namespace": "(val)","referenceDiscriminatorType": "(val)","referenceObjectId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Reference Object Bindings [POST /research-sys/api/v1/reference-object-bindings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"id": "(val)","collectionName": "(val)","krmsDiscriminatorType": "(val)","krmsObjectId": "(val)","namespace": "(val)","referenceDiscriminatorType": "(val)","referenceObjectId": "(val)","active": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"id": "(val)","collectionName": "(val)","krmsDiscriminatorType": "(val)","krmsObjectId": "(val)","namespace": "(val)","referenceDiscriminatorType": "(val)","referenceObjectId": "(val)","active": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Reference Object Bindings [POST /research-sys/api/v1/reference-object-bindings/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"id": "(val)","collectionName": "(val)","krmsDiscriminatorType": "(val)","krmsObjectId": "(val)","namespace": "(val)","referenceDiscriminatorType": "(val)","referenceObjectId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","collectionName": "(val)","krmsDiscriminatorType": "(val)","krmsObjectId": "(val)","namespace": "(val)","referenceDiscriminatorType": "(val)","referenceObjectId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"id": "(val)","collectionName": "(val)","krmsDiscriminatorType": "(val)","krmsObjectId": "(val)","namespace": "(val)","referenceDiscriminatorType": "(val)","referenceObjectId": "(val)","active": "(val)","_primaryKey": "(val)"},
              {"id": "(val)","collectionName": "(val)","krmsDiscriminatorType": "(val)","krmsObjectId": "(val)","namespace": "(val)","referenceDiscriminatorType": "(val)","referenceObjectId": "(val)","active": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Reference Object Bindings by Key [DELETE /research-sys/api/v1/reference-object-bindings/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Reference Object Bindings [DELETE /research-sys/api/v1/reference-object-bindings/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Reference Object Bindings with Matching [DELETE /research-sys/api/v1/reference-object-bindings/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
      + id
            + collectionName
            + krmsDiscriminatorType
            + krmsObjectId
            + namespace
            + referenceDiscriminatorType
            + referenceObjectId
            + active

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
