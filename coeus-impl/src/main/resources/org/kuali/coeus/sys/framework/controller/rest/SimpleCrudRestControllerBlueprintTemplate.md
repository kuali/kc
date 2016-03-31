## ${resourceName} [${endpoint}]

### Get ${resourceName} by Key [GET ${endpoint}${sampleKey}]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            ${sampleResource1}

### Get All ${resourceName} [GET ${endpoint}]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              ${sampleResource1},
              ${sampleResource2}
            ]

### Get All ${resourceName} with Filtering [GET ${endpoint}]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            ${sampleMatchCriteria} 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              ${sampleResource1},
              ${sampleResource2}
            ]
			
### Get Schema for ${resourceName} [GET ${endpoint}]
	 
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
		
### Get Blueprint API specification for ${resourceName} [GET ${endpoint}]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="${resourceName}.md"
            transfer-encoding:chunked


### Update ${resourceName} [PUT ${endpoint}${sampleKey}]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            ${sampleResource1}
			
+ Response 204

### Update Multiple ${resourceName} [PUT ${endpoint}]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              ${sampleResource1},
              ${sampleResource2}
            ]
			
+ Response 204

### Insert ${resourceName} [POST ${endpoint}]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            ${sampleResource1}
			
+ Response 201
    
    + Body
            
            ${sampleResource1}
            
### Insert Multiple ${resourceName} [POST ${endpoint}]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              ${sampleResource1},
              ${sampleResource2}
            ]
			
+ Response 201
    
    + Body
            
            [
              ${sampleResource1},
              ${sampleResource2}
            ]
            
### Delete ${resourceName} by Key [DELETE ${endpoint}${sampleKey}]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All ${resourceName} [DELETE ${endpoint}]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All ${resourceName} with Matching [DELETE ${endpoint}]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            ${sampleMatchCriteria}

+ Response 204
