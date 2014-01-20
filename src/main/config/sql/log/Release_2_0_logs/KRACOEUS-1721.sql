/* Adding Group Name to the Table */
ALTER TABLE BUDGET_DETAILS ADD (GROUP_NAME VARCHAR2(25));
/* Adding the System Parameter to turn on/off Job Code based validation */
insert into SH_PARM_T (SH_PARM_NMSPC_CD, SH_PARM_DTL_TYP_CD, SH_PARM_NM, SH_PARM_TYP_CD, SH_PARM_TXT, SH_PARM_DESC, SH_PARM_CONS_CD, WRKGRP_NM, ACTIVE_IND)
values ('KRA-B', 'D', 'JOBCODE_VALIDATION_ENABLED', 'CONFG', 'Y', 'Whether Job code based validation is enabled', 'A', 'WorkflowAdmin', 'Y');