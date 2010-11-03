set define off
set sqlblanklines on
spool KC-Release-2_0-Base-Rice-Oracle-Install.log
PROMPT Running DML_BS_KRNS.sql...
@DML/DML_BS_KRNS.sql

PROMPT Running DML_BS_KRIM.sql...
@DML/DML_BS_KRIM.sql

COMMIT;
EXIT;