DELIMITER /

update KRCR_PARM_T set VAL = 'kcnotification@gmail.com' where PARM_NM = 'EMAIL_NOTIFICATION_FROM_ADDRESS' and NMSPC_CD = 'KC-GEN'
/

update KRCR_PARM_T set VAL = 'kcnotification@gmail.com' where PARM_NM = 'EMAIL_NOTIFICATION_TEST_ADDRESS' and NMSPC_CD = 'KC-GEN'
/

DELIMITER ;

