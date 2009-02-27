 -- Change the system parameter name
 
update sh_parm_t set sh_parm_nm='pessimisticLocking.expirationAge'
where sh_parm_nm='pessimisticLocking.timeout';