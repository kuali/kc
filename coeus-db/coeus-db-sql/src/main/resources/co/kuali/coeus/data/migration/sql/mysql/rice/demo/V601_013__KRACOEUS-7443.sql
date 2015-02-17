UPDATE KRIM_ENTITY_EMAIL_T
SET EMAIL_ADDR = 'kcnotification@gmail.com'
WHERE ENTITY_ID=(select ENTITY_ID from KRIM_PRNCPL_T where PRNCPL_NM='quickstart');
