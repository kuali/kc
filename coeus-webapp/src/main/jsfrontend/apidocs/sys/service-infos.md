## Service Infos [/research-sys/api/v1/service-infos/]

### Get Service Infos by Key [GET /research-sys/api/v1/service-infos/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"serviceId": "(val)","serviceName": "(val)","endpointUrl": "(val)","instanceId": "(val)","applicationId": "(val)","serverIpAddress": "(val)","type": "(val)","serviceVersion": "(val)","statusCode": "(val)","serviceDescriptorId": "(val)","checksum": "(val)","_primaryKey": "(val)"}

### Get All Service Infos [GET /research-sys/api/v1/service-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"serviceId": "(val)","serviceName": "(val)","endpointUrl": "(val)","instanceId": "(val)","applicationId": "(val)","serverIpAddress": "(val)","type": "(val)","serviceVersion": "(val)","statusCode": "(val)","serviceDescriptorId": "(val)","checksum": "(val)","_primaryKey": "(val)"},
              {"serviceId": "(val)","serviceName": "(val)","endpointUrl": "(val)","instanceId": "(val)","applicationId": "(val)","serverIpAddress": "(val)","type": "(val)","serviceVersion": "(val)","statusCode": "(val)","serviceDescriptorId": "(val)","checksum": "(val)","_primaryKey": "(val)"}
            ]

### Get All Service Infos with Filtering [GET /research-sys/api/v1/service-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + serviceId
            + serviceName
            + endpointUrl
            + instanceId
            + applicationId
            + serverIpAddress
            + type
            + serviceVersion
            + statusCode
            + serviceDescriptorId
            + checksum
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"serviceId": "(val)","serviceName": "(val)","endpointUrl": "(val)","instanceId": "(val)","applicationId": "(val)","serverIpAddress": "(val)","type": "(val)","serviceVersion": "(val)","statusCode": "(val)","serviceDescriptorId": "(val)","checksum": "(val)","_primaryKey": "(val)"},
              {"serviceId": "(val)","serviceName": "(val)","endpointUrl": "(val)","instanceId": "(val)","applicationId": "(val)","serverIpAddress": "(val)","type": "(val)","serviceVersion": "(val)","statusCode": "(val)","serviceDescriptorId": "(val)","checksum": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Service Infos [GET /research-sys/api/v1/service-infos/]
	 
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
		
### Get Blueprint API specification for Service Infos [GET /research-sys/api/v1/service-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Service Infos.md"
            transfer-encoding:chunked


### Update Service Infos [PUT /research-sys/api/v1/service-infos/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"serviceId": "(val)","serviceName": "(val)","endpointUrl": "(val)","instanceId": "(val)","applicationId": "(val)","serverIpAddress": "(val)","type": "(val)","serviceVersion": "(val)","statusCode": "(val)","serviceDescriptorId": "(val)","checksum": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Service Infos [PUT /research-sys/api/v1/service-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"serviceId": "(val)","serviceName": "(val)","endpointUrl": "(val)","instanceId": "(val)","applicationId": "(val)","serverIpAddress": "(val)","type": "(val)","serviceVersion": "(val)","statusCode": "(val)","serviceDescriptorId": "(val)","checksum": "(val)","_primaryKey": "(val)"},
              {"serviceId": "(val)","serviceName": "(val)","endpointUrl": "(val)","instanceId": "(val)","applicationId": "(val)","serverIpAddress": "(val)","type": "(val)","serviceVersion": "(val)","statusCode": "(val)","serviceDescriptorId": "(val)","checksum": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Service Infos [POST /research-sys/api/v1/service-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"serviceId": "(val)","serviceName": "(val)","endpointUrl": "(val)","instanceId": "(val)","applicationId": "(val)","serverIpAddress": "(val)","type": "(val)","serviceVersion": "(val)","statusCode": "(val)","serviceDescriptorId": "(val)","checksum": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"serviceId": "(val)","serviceName": "(val)","endpointUrl": "(val)","instanceId": "(val)","applicationId": "(val)","serverIpAddress": "(val)","type": "(val)","serviceVersion": "(val)","statusCode": "(val)","serviceDescriptorId": "(val)","checksum": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Service Infos [POST /research-sys/api/v1/service-infos/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"serviceId": "(val)","serviceName": "(val)","endpointUrl": "(val)","instanceId": "(val)","applicationId": "(val)","serverIpAddress": "(val)","type": "(val)","serviceVersion": "(val)","statusCode": "(val)","serviceDescriptorId": "(val)","checksum": "(val)","_primaryKey": "(val)"},
              {"serviceId": "(val)","serviceName": "(val)","endpointUrl": "(val)","instanceId": "(val)","applicationId": "(val)","serverIpAddress": "(val)","type": "(val)","serviceVersion": "(val)","statusCode": "(val)","serviceDescriptorId": "(val)","checksum": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"serviceId": "(val)","serviceName": "(val)","endpointUrl": "(val)","instanceId": "(val)","applicationId": "(val)","serverIpAddress": "(val)","type": "(val)","serviceVersion": "(val)","statusCode": "(val)","serviceDescriptorId": "(val)","checksum": "(val)","_primaryKey": "(val)"},
              {"serviceId": "(val)","serviceName": "(val)","endpointUrl": "(val)","instanceId": "(val)","applicationId": "(val)","serverIpAddress": "(val)","type": "(val)","serviceVersion": "(val)","statusCode": "(val)","serviceDescriptorId": "(val)","checksum": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Service Infos by Key [DELETE /research-sys/api/v1/service-infos/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Service Infos [DELETE /research-sys/api/v1/service-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Service Infos with Matching [DELETE /research-sys/api/v1/service-infos/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + serviceId
            + serviceName
            + endpointUrl
            + instanceId
            + applicationId
            + serverIpAddress
            + type
            + serviceVersion
            + statusCode
            + serviceDescriptorId
            + checksum


+ Response 204
