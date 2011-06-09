set sqlblanklines on
set define off
spool KC-Release-3_0-Clean-Full-Oracle-Install.log
@oracle_server_base_pre.sql
@oracle_server.sql
@oracle_server_base_post.sql
quit