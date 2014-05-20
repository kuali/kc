DELIMITER /
update KRIM_ROLE_T set ROLE_ID = 'KR1001' where ROLE_NM = 'Complete Request Recipient' and NMSPC_CD = 'KR-WKFLW'
/
UPDATE KRIM_ROLE_PERM_T SET ROLE_ID = (Select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Complete Request Recipient') where 
PERM_ID = (Select PERM_ID from KRIM_PERM_T where NM = 'Take Requested Complete Action') and ROLE_ID = (Select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Complete Request Recipient')
/
UPDATE KRIM_ROLE_PERM_T SET ROLE_ID = (Select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Complete Request Recipient') where
PERM_ID =  (Select PERM_ID from KRIM_PERM_T where NM = 'Edit Kuali ENROUTE Document Route Status Code R') and ROLE_ID = (Select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Complete Request Recipient')
/
DELIMITER ;
