set define off
set sqlblanklines on
spool KRC_RICE-RELEASE-5_0-Upgrade-ORACLE-Install.log
@../../current/5.0/rice/KRC_RICE_01_2.0.0-2.1.0_2012-04-19_B000.sql
@../../current/5.0/rice/KRC_RICE_02_2.0.0-2.1.0_2012-05-17_B000.sql
commit;
exit
