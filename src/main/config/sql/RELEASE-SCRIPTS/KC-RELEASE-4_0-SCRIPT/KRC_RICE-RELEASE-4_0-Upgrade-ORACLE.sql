set define off
set sqlblanklines on
spool KRC_RICE-RELEASE-4_0-Upgrade-ORACLE-Install.log
@oracle/rice/KRC_RICE_01_1.0.3.2-2.0.0_2010-04-15_B000.sql
@oracle/rice/KRC_RICE_02_1.0.3.2-2.0.0_2011-06-06_B000.sql
@oracle/rice/KRC_RICE_03_1.0.3.2-2.0.0_2012-01-19c_B000.sql
commit;
exit
