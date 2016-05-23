## Award Payment Schedules [/award/api/v1/award-payment-schedules/]

### Get Award Payment Schedules by Key [GET /award/api/v1/award-payment-schedules/(key)]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            {"awardPaymentScheduleId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"}

### Get All Award Payment Schedules [GET /award/api/v1/award-payment-schedules/]
	 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardPaymentScheduleId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"},
              {"awardPaymentScheduleId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"}
            ]

### Get All Award Payment Schedules with Filtering [GET /award/api/v1/award-payment-schedules/]
    
+ Parameters

    + awardPaymentScheduleId (optional) - Award Payment Schedule Id. Maximum length is 22.
    + awardNumber (optional) - Award ID. Maximum length is 12.
    + sequenceNumber (optional) - Sequence Number. Maximum length is 22.
    + dueDate (optional) - Due Date. Maximum length is 10.
    + amount (optional) - Amount. Maximum length is 22.
    + submitDate (optional) - Activity Date. Maximum length is 10.
    + submittedBy (optional) - Preparer. Maximum length is 9.
    + invoiceNumber (optional) - Invoice ID. Maximum length is 10.
    + statusDescription (optional) - Comments. Maximum length is 200.
    + status (optional) - Status. Maximum length is 5.
    + lastUpdateUser (optional) - 
    + lastUpdateTimestamp (optional) - 
    + overdue (optional) - Overdue. Maximum length is 15.
    + reportStatusCode (optional) - Status. Maximum length is 22.
    + submittedByPersonId (optional) - Preparer. Maximum length is 40.
    + awardReportTermDescription (optional) - 

            
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json 

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
    
            [
              {"awardPaymentScheduleId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"},
              {"awardPaymentScheduleId": "(val)","awardNumber": "(val)","sequenceNumber": "(val)","dueDate": "(val)","amount": "(val)","submitDate": "(val)","submittedBy": "(val)","invoiceNumber": "(val)","statusDescription": "(val)","status": "(val)","lastUpdateUser": "(val)","lastUpdateTimestamp": "(val)","overdue": "(val)","reportStatusCode": "(val)","submittedByPersonId": "(val)","awardReportTermDescription": "(val)","_primaryKey": "(val)"}
            ]
			
### Get Schema for Award Payment Schedules [GET /award/api/v1/award-payment-schedules/]
	                                          
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
    
            {"columns":["awardPaymentScheduleId","awardNumber","sequenceNumber","dueDate","amount","submitDate","submittedBy","invoiceNumber","statusDescription","status","lastUpdateUser","lastUpdateTimestamp","overdue","reportStatusCode","submittedByPersonId","awardReportTermDescription"],"primaryKey":"awardPaymentScheduleId"}
		
### Get Blueprint API specification for Award Payment Schedules [GET /award/api/v1/award-payment-schedules/]
	 
+ Parameters

     + _blueprint (required) - will instruct the endpoint to return an api blueprint markdown file for the resource
                 
+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: text/markdown

+ Response 200
    + Headers

            Content-Type: text/markdown;charset=UTF-8
            Content-Disposition:attachment; filename="Award Payment Schedules.md"
            transfer-encoding:chunked
