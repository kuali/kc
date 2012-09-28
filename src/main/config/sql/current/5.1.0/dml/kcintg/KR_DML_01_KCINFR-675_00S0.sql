update KRCR_PARM_T set VAL = 'ON' where parm_nm = 'FIN_SYSTEM_INTEGRATION_ON'
/
update KRCR_PARM_T set VAL = '2' where parm_nm = 'enable.award.FnA.validation'
/
update KRCR_PARM_T set VAL = '0 0 0/2 * * ?' WHERE PARM_NM = 'CFDA_BATCH_JOB_CRON_EXPRESSION'
/
