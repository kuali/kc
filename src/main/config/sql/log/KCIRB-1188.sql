update protocol_status
set description = 'Deleted'
where PROTOCOL_STATUS_CODE = 303
/
commit