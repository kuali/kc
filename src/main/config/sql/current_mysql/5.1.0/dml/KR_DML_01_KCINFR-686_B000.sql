DELIMITER /
update KRCR_PARM_T set PARM_DESC_TXT='The cfda cron job is set to run at 6am every morning as the default. For a detailed explanation on the cron trigger and to modify it, please view the documentation on cron triggers in the quartz scheduler documentation, here http://quartz-scheduler.org/documentation/quartz-1.x/tutorials/crontrigger' WHERE PARM_NM = 'CFDA_BATCH_JOB_CRON_EXPRESSION'
/

DELIMITER ;
