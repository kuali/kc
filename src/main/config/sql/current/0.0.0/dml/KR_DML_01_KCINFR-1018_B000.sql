update KRIM_ROLE_T set ROLE_ID = 'KR1001' where ROLE_NM = 'Complete Request Recipient' and NMSPC_CD = 'KR-WKFLW'
/
UPDATE KRIM_ROLE_PERM_T SET ROLE_ID = (Select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Complete Request Recipient') where ROLE_PERM_ID = 'KR1000'
/
UPDATE KRIM_ROLE_PERM_T SET ROLE_ID = (Select ROLE_ID from KRIM_ROLE_T where ROLE_NM = 'Complete Request Recipient') where ROLE_PERM_ID = 'KR1001'
/

