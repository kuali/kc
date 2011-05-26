set define off 
set sqlblanklines on 
spool RICE-1_0_3_1-1_0_3_2-Upgrade-ORACLE-Install.log
@RICE-1_0_3-1_0_4/update_final_oracle.sql
commit;
exit;
