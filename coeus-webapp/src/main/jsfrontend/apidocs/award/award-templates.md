## Award Templates [/award/api/v1/award-templates/]

### Get Award Templates by Key [GET /award/api/v1/award-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"templateCode": "(val)","statusCode": "(val)","primeSponsorCode": "(val)","description": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","_primaryKey": "(val)"}

### Get All Award Templates [GET /award/api/v1/award-templates/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"templateCode": "(val)","statusCode": "(val)","primeSponsorCode": "(val)","description": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","_primaryKey": "(val)"},
              {"templateCode": "(val)","statusCode": "(val)","primeSponsorCode": "(val)","description": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Templates with Filtering [GET /award/api/v1/award-templates/]
    
+ Parameters

    + templateCode (optional) - Sponsor Template Code. Maximum length is 5.
    + statusCode (optional) - Status Code. Maximum length is 22.
    + primeSponsorCode (optional) - Prime Sponsor Code. Maximum length is 6.
    + description (optional) - Description. Maximum length is 200.
    + basisOfPaymentCode (optional) - Payment Basis. Maximum length is 22.
    + methodOfPaymentCode (optional) - Payment Method. Maximum length is 22.

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"templateCode": "(val)","statusCode": "(val)","primeSponsorCode": "(val)","description": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","_primaryKey": "(val)"},
              {"templateCode": "(val)","statusCode": "(val)","primeSponsorCode": "(val)","description": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Templates [GET /award/api/v1/award-templates/]
	                                          
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
    
            {"columns":["templateCode","statusCode","primeSponsorCode","description","basisOfPaymentCode","methodOfPaymentCode"],"primaryKey":"templateCode"}
		
### Get Blueprint API specification for Award Templates [GET /award/api/v1/award-templates/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Templates.md"
            transfer-encoding:chunked


### Update Award Templates [PUT /award/api/v1/award-templates/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"templateCode": "(val)","statusCode": "(val)","primeSponsorCode": "(val)","description": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Templates [PUT /award/api/v1/award-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"templateCode": "(val)","statusCode": "(val)","primeSponsorCode": "(val)","description": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","_primaryKey": "(val)"},
              {"templateCode": "(val)","statusCode": "(val)","primeSponsorCode": "(val)","description": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Templates [POST /award/api/v1/award-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"templateCode": "(val)","statusCode": "(val)","primeSponsorCode": "(val)","description": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"templateCode": "(val)","statusCode": "(val)","primeSponsorCode": "(val)","description": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Templates [POST /award/api/v1/award-templates/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"templateCode": "(val)","statusCode": "(val)","primeSponsorCode": "(val)","description": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","_primaryKey": "(val)"},
              {"templateCode": "(val)","statusCode": "(val)","primeSponsorCode": "(val)","description": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"templateCode": "(val)","statusCode": "(val)","primeSponsorCode": "(val)","description": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","_primaryKey": "(val)"},
              {"templateCode": "(val)","statusCode": "(val)","primeSponsorCode": "(val)","description": "(val)","basisOfPaymentCode": "(val)","methodOfPaymentCode": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Templates by Key [DELETE /award/api/v1/award-templates/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Templates [DELETE /award/api/v1/award-templates/]

+ Parameters

      + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Templates with Matching [DELETE /award/api/v1/award-templates/]

+ Parameters

    + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
    + templateCode (optional) - Sponsor Template Code. Maximum length is 5.
    + statusCode (optional) - Status Code. Maximum length is 22.
    + primeSponsorCode (optional) - Prime Sponsor Code. Maximum length is 6.
    + description (optional) - Description. Maximum length is 200.
    + basisOfPaymentCode (optional) - Payment Basis. Maximum length is 22.
    + methodOfPaymentCode (optional) - Payment Method. Maximum length is 22.

      
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204
