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
    
+ Parameters

    ${sampleMatchCriteria}
            
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
			
### Get Schema for ${resourceName} [GET ${endpoint}]
	                                          
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
    
            ${sampleSchema}
		
### Get Blueprint API specification for ${resourceName} [GET ${endpoint}]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="${resourceName}.md"
            transfer-encoding:chunked
