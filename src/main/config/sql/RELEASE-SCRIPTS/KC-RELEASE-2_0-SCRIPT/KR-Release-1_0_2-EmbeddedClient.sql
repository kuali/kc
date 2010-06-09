set sqlblanklines on
set define off
SPOOL EMBEDDEDCLIENT_ORACLE.log
PROMPT sql/schema.sql
@RICE/EMBEDDEDCLIENT_ORACLE/sql/schema.sql
PROMPT datasql/KRNS_NTE_TYP_T.sql
@RICE/EMBEDDEDCLIENT_ORACLE/datasql/KRNS_NTE_TYP_T.sql
PROMPT datasql/KRSB_MSG_PYLD_T.sql
@RICE/EMBEDDEDCLIENT_ORACLE/datasql/KRSB_MSG_PYLD_T.sql
PROMPT datasql/KRSB_MSG_QUE_T.sql
@RICE/EMBEDDEDCLIENT_ORACLE/datasql/KRSB_MSG_QUE_T.sql
PROMPT datasql/KRSB_QRTZ_LOCKS.sql
@RICE/EMBEDDEDCLIENT_ORACLE/datasql/KRSB_QRTZ_LOCKS.sql
PROMPT sql/schema-constraints.sql
@RICE/EMBEDDEDCLIENT_ORACLE/sql/schema-constraints.sql
@RICE/EMBEDDEDCLIENT_ORACLE/demo-client-dataset-cleanup.sql
@RICE/EMBEDDEDCLIENT_ORACLE/bootstrap-client-dataset-cleanup.sql
quit
