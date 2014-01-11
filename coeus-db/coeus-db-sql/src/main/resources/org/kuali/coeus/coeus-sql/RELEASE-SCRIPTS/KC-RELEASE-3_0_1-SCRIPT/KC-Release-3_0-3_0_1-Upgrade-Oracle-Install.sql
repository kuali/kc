set define off 
set sqlblanklines on 
spool KC-Release-3_0-3_0_1-Upgrade-Oracle-Install.log
@ORACLE/TABLES/TBL_AWARD_CUSTOM_DATA.sql 
@ORACLE/TABLES/TBL_PROPOSAL_CUSTOM_DATA.sql 
commit;
exit;
