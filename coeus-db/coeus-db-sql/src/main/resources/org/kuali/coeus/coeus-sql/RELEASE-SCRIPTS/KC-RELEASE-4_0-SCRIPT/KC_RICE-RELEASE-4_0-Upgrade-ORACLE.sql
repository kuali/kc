set define off
set sqlblanklines on
spool KC_RICE-RELEASE-4_0-Upgrade-ORACLE-Install.log
@oracle/rice/KC_RICE_01_QUESTION_B000.sql
commit;
exit
