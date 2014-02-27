set define off
set sqlblanklines on
spool KRC_RICE-RELEASE-5_2_0-Upgrade-ORACLE-Install.log
@../../current/5.2.0/rice/KRC_RICE_01_2.2.1-2.2.2_2013-03-18_B000.sql
@../../current/5.2.0/rice/KRC_RICE_02_2.2.1-2.2.2_CLEANUP-2013-03-18_B000.sql
commit;
exit
