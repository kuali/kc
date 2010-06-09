set sqlblanklines on
set define off
spool KC-Release-1_1_1-2_0-Patch.log
@UPGRADE_RICE_093_10KC.sql
@UPGRADE_KIM-KRIM.sql
@PRE_TABLES.sql
@TABLES.sql
@SEQUENCES.sql
@CONSTRAINTS.sql
@DML_PRE.sql
@DML_BS.sql
@DML_POST.sql
@INDEXES.sql
COMMIT;
EXIT