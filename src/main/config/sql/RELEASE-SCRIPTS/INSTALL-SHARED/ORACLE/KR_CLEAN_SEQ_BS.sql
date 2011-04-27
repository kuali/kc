set define off 
set sqlblanklines on 
spool KR_CLEAN_SEQ_BS-Oracle-Install.log

drop sequence KRIM_ROLE_MBR_ID_BS_S;
drop sequence KRIM_PERM_ID_BS_S;
drop sequence KRIM_ROLE_PERM_ID_BS_S;
drop sequence KRIM_ATTR_DATA_ID_BS_S;
drop sequence KRIM_GRP_ID_BS_S;
drop sequence KRIM_GRP_MBR_ID_BS_S;
drop sequence KRIM_ROLE_RSP_ID_BS_S;
drop sequence KRIM_ROLE_ID_BS_S;
drop sequence KRIM_ROLE_RSP_ACTN_ID_BS_S;