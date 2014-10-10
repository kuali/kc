DELIMITER /

DELETE FROM KRCR_PARM_T WHERE NMSPC_CD = 'KC-PD' AND PARM_NM = 'pessimisticLocking.timeout'
/
UPDATE KRCR_PARM_T SET NMSPC_CD = 'KC-SYS', CMPNT_CD = 'All' WHERE NMSPC_CD = 'KC-PD' AND PARM_NM in ('pessimisticLocking.cronExpression', 'pessimisticLocking.expirationAge')
/

DELIMITER ;
