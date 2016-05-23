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
