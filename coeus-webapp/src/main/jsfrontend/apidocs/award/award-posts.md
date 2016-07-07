## Awards [/award/api/v1/award-posts/]

### Get All Active Award Posts [GET /award/api/v1/award-posts/{?accountNumber=55555}]


+ Parameters
  	+ accountNumber: `55555` (number, optional) - Account number of the posts to be returned. If not included, will return all active award posts.

+ Request

    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

+ Response 200
    + Headers

            Content-Type: application/json;charset=UTF-8

    + Body
           [
             {
               "id": 1,
               "accountNumber": "0211405",
               "awardId": 2303,
               "posted": true,
               "active": true,
               "documentNumber": null,
               "updateUser": "admin",
               "updateTimestamp": 1467415795000
             },
             {
               "id": 2,
               "accountNumber": "55555",
               "awardId": 2609,
               "posted": false,
               "active": true,
               "documentNumber": "4437",
               "updateUser": "quickstart",
               "updateTimestamp": 1467417915000
             },
             {
               "id": 6,
               "accountNumber": "55555",
               "awardId": 2645,
               "posted": true,
               "active": true,
               "documentNumber": "4438",
               "updateUser": "admin",
               "updateTimestamp": 1467478319000
             },
             {
               "id": 7,
               "accountNumber": "55555",
               "awardId": 2681,
               "posted": true,
               "active": true,
               "documentNumber": "4439",
               "updateUser": "admin",
               "updateTimestamp": 1467479250000
             }
           ]

### Update Award Post [PUT /award/api/v1/award-posts/7]

Set various fields on an the award-post. Fields not provided will not be updated.

+ Request
    + Headers

            Authorization: Bearer {api-key}
            Content-Type: application/json

    + Body

            {
                "id": 7,
                "accountNumber": "55555",
                "awardId": 2681,
                "posted": true,
                "active": false,
                "documentNumber": "4439",
                "updateUser": "admin",
                "updateTimestamp": 1467479250000
              }

+ Response 200
    + Headers

            Content-Length:0




