set sqlblanklines on
set define off
spool DML_POST.log
@DML/DML_POST_BUDGET_DETAILS.sql
@DML/DML_POST_KRNS_PARM_T.sql
