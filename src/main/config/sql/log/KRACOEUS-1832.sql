 -- System parameters for controlling the deletion of pessimistic locks.
 -- The CRON expression controls when the Quartz timer will trigger.
 -- The expiration timeout (in minutes) determines which locks will be deleted.
 -- Locks that are older than the expiration timeout value will be deleted.
 
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','pessimisticLocking.cronExpression','CONFG','0 0 1 * * ?','The Cron Expression for Quartz to activate a clearing of old locks','A','Y');
insert into sh_parm_t (sh_parm_nmspc_cd,sh_parm_dtl_typ_cd,sh_parm_nm,sh_parm_typ_cd,sh_parm_txt,sh_parm_desc,sh_parm_cons_cd,active_ind) values ('KRA-PD','D','pessimisticLocking.timeout','CONFG','1440','The expiration timeout in minutes; expired locks are deleted','A','Y');
