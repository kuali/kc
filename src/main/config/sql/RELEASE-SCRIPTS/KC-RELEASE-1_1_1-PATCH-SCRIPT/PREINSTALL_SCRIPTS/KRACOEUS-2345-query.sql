set feedback off
spool install_kc_release-1_1_1-Patch_DeadlineType.log

PROMPT The following deadline types have multiple character deadline type codes.
PROMPT Please change the multiple character deadline type codes to a single 
PROMPT character code for the records listed below.
PROMPT

select * from deadline_type where length(deadline_type_code) > 1;
spool off
