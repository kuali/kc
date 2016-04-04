## Award Payment Schedules [/research-sys/api/v1/award-payment-schedules/]

### Get Award Payment Schedules by Key [GET /research-sys/api/v1/award-payment-schedules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardPaymentScheduleId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"}

### Get All Award Payment Schedules [GET /research-sys/api/v1/award-payment-schedules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardPaymentScheduleId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"},
              {"awardPaymentScheduleId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Payment Schedules with Filtering [GET /research-sys/api/v1/award-payment-schedules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
    
    + Parameters
    
            + awardPaymentScheduleId
            + awardId
            + awardNumber
            + sequenceNumber
            + dueDate
            + amount
            + submitDate
            + submittedBy
            + invoiceNumber
            + statusDescription
            + status
            + lastUpdateUser
            + lastUpdateTimestamp
            + overdue
            + reportStatusCode
            + submittedByPersonId
            + awardReportTermDescription
 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardPaymentScheduleId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"},
              {"awardPaymentScheduleId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Payment Schedules [GET /research-sys/api/v1/award-payment-schedules/]
	 
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
		
### Get Blueprint API specification for Award Payment Schedules [GET /research-sys/api/v1/award-payment-schedules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown
    
    + Parameters
    
            + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Payment Schedules.md"
            transfer-encoding:chunked


### Update Award Payment Schedules [PUT /research-sys/api/v1/award-payment-schedules/(key)]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardPaymentScheduleId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 204

### Update Multiple Award Payment Schedules [PUT /research-sys/api/v1/award-payment-schedules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardPaymentScheduleId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"},
              {"awardPaymentScheduleId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 204

### Insert Award Payment Schedules [POST /research-sys/api/v1/award-payment-schedules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            {"awardPaymentScheduleId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"}
			
+ Response 201
    
    + Body
            
            {"awardPaymentScheduleId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"}
            
### Insert Multiple Award Payment Schedules [POST /research-sys/api/v1/award-payment-schedules/]

+ Request

    + Headers

            Authorization: Bearer {api-key}   
            Content-Type: application/json

    + Body
    
            [
              {"awardPaymentScheduleId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"},
              {"awardPaymentScheduleId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"}
            ]
			
+ Response 201
    
    + Body
            
            [
              {"awardPaymentScheduleId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"},
              {"awardPaymentScheduleId": "(val)","awardId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"}
            ]
            
### Delete Award Payment Schedules by Key [DELETE /research-sys/api/v1/award-payment-schedules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 204

### Delete All Award Payment Schedules [DELETE /research-sys/api/v1/award-payment-schedules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation

+ Response 204

### Delete All Award Payment Schedules with Matching [DELETE /research-sys/api/v1/award-payment-schedules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json
            
    + Parameters
    
            + _allowMulti (boolean, required) - flag to allow multiple resources to be deleted in one operation
            + awardPaymentScheduleId
            + awardId
            + awardNumber
            + sequenceNumber
            + dueDate
            + amount
            + submitDate
            + submittedBy
            + invoiceNumber
            + statusDescription
            + status
            + lastUpdateUser
            + lastUpdateTimestamp
            + overdue
            + reportStatusCode
            + submittedByPersonId
            + awardReportTermDescription


+ Response 204
