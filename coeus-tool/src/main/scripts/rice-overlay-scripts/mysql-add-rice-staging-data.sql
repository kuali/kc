/*Need staging data is unique to integration*/
use krdev;
update KRCR_PARM_T set VAL = 'ON' where parm_nm = 'FIN_SYSTEM_INTEGRATION_ON';
update KRCR_PARM_T set VAL = '2' where parm_nm = 'enable.award.FnA.validation';
-- update KRCR_PARM_T set VAL = 'http://test.kc.kuali.org/kc-wkly' where parm_nm = 'kuali.docHandler.url.prefix';
update KRCR_PARM_T set VAL = '0 0 0/2 * * ?' WHERE PARM_NM = 'CFDA_BATCH_JOB_CRON_EXPRESSION';
commit;