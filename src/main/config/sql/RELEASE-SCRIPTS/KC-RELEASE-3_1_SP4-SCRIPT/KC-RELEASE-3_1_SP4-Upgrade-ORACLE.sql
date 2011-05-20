set define off
set sqlblanklines on
spool KC-RELEASE-3_1_SP4-Upgrade-ORACLE-Install.log
@ORACLE/DML/KC_DML_BS1_TRAINING_STIPEND_RATES.sql
commit;
exit
