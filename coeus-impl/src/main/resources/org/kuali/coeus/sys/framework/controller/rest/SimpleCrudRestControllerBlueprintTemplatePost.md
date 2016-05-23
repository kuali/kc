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
