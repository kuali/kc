set define off 
set sqlblanklines on 
spool KR-Server-Release-1_0_3_1-Upgrade-Oracle-Install.log
@RICE-1_0_3-1_0_4/update_final_oracle.sql
commit;
exit;
