DROP SEQUENCE KRIM_ATTR_DATA_ID_S;
create SEQUENCE KRIM_ATTR_DATA_ID_S increment by 1 start with 10000; 

alter table KRIM_ENTITY_ENT_TYP_T disable constraint KRIM_ENTITY_ENT_TYP_TR1;
alter table KRIM_ENTITY_ADDR_T disable constraint KRIM_ENTITY_ADDR_TR1;
alter table KRIM_ENTITY_ADDR_T disable constraint KRIM_ENTITY_ADDR_TR2;
alter table KRIM_ENTITY_AFLTN_T disable constraint KRIM_ENTITY_AFLTN_TR1;
alter table KRIM_ENTITY_AFLTN_T disable constraint KRIM_ENTITY_AFLTN_TR2;
alter table KRIM_ENTITY_CTZNSHP_T disable constraint KRIM_ENTITY_CTZNSHP_TR1;
alter table KRIM_ENTITY_CTZNSHP_T disable constraint KRIM_ENTITY_CTZNSHP_TR2;
alter table KRIM_ENTITY_EMAIL_T disable constraint KRIM_ENTITY_EMAIL_TR1;
alter table KRIM_ENTITY_EMP_INFO_T disable constraint KRIM_ENTITY_EMP_INFO_TR1;
alter table KRIM_ENTITY_EMP_INFO_T disable constraint KRIM_ENTITY_EMP_INFO_TR2;
alter table KRIM_ENTITY_EMP_INFO_T disable constraint KRIM_ENTITY_EMP_INFO_TR3;
alter table KRIM_ENTITY_EMP_INFO_T disable constraint KRIM_ENTITY_EMP_INFO_TR4;
alter table KRIM_ENTITY_EXT_ID_T disable constraint KRIM_ENTITY_EXT_ID_TR1;
alter table KRIM_ENTITY_EXT_ID_T disable constraint KRIM_ENTITY_EXT_ID_TR2;
alter table KRIM_ENTITY_NM_T disable constraint KRIM_ENTITY_NM_TR1;
alter table KRIM_ENTITY_PHONE_T disable constraint KRIM_ENTITY_PHONE_TR1;
alter table KRIM_ENTITY_PHONE_T disable constraint KRIM_ENTITY_PHONE_TR2;
alter table KRIM_GRP_T disable constraint KRIM_GRP_TR1;
alter table KRIM_GRP_ATTR_DATA_T disable constraint KRIM_GRP_ATTR_DATA_TR1;
alter table KRIM_GRP_ATTR_DATA_T disable constraint KRIM_GRP_ATTR_DATA_TR2;
alter table KRIM_GRP_ATTR_DATA_T disable constraint KRIM_GRP_ATTR_DATA_TR3;
alter table KRIM_GRP_MBR_T disable constraint KRIM_GRP_MBR_TR1;
alter table KRIM_PERM_TMPL_T disable constraint KRIM_PERM_TMPL_TR1;
alter table KRIM_PERM_T disable constraint KRIM_PERM_TR1;
alter table KRIM_PERM_ATTR_DATA_T disable constraint KRIM_PERM_ATTR_DATA_TR1;
alter table KRIM_PERM_ATTR_DATA_T disable constraint KRIM_PERM_ATTR_DATA_TR2;
alter table KRIM_PERM_ATTR_DATA_T disable constraint KRIM_PERM_ATTR_DATA_TR3;
alter table KRIM_PRNCPL_T disable constraint KRIM_PRNCPL_TR1;
alter table KRIM_ROLE_MBR_ATTR_DATA_T disable constraint KRIM_ROLE_MBR_ATTR_DATA_TR1;
alter table KRIM_ROLE_MBR_ATTR_DATA_T disable constraint KRIM_ROLE_MBR_ATTR_DATA_TR2;
alter table KRIM_ROLE_T disable constraint KRIM_ROLE_TR1;
alter table KRIM_ROLE_MBR_T disable constraint KRIM_ROLE_MBR_TR1;
alter table KRIM_ROLE_PERM_T disable constraint KRIM_ROLE_PERM_TR1;
alter table KRIM_TYP_ATTR_T disable constraint KRIM_TYP_ATTR_TR2;

delete from KRIM_GRP_ATTR_DATA_T;
delete from KRIM_GRP_MBR_T;
delete from KRIM_ROLE_MBR_ATTR_DATA_T;
delete from KRIM_ROLE_MBR_T;
delete from KRIM_ROLE_PERM_T;
delete from KRIM_PERM_ATTR_DATA_T; 
delete from  KRIM_PERM_T; 
delete from KRIM_PERM_TMPL_T; 
delete from KRIM_ROLE_T; 
delete from KRIM_GRP_T; 
delete from PERSON_EXT_T; 
--delete from  KRIM_PRNCPL_T WHERE PRNCPL_NM NOT IN ('quickstart', 'admin', 'kr', 'superuser');
delete from  KRIM_PRNCPL_T WHERE PRNCPL_NM NOT IN ('quickstart');
delete from  KRIM_ENTITY_BIO_T; 
delete from  KRIM_ENTITY_CTZNSHP_T; 
delete from KRIM_ENTITY_ETHNIC_T; 
delete from  KRIM_ENTITY_EXT_ID_T; 
delete from  KRIM_ENTITY_EMP_INFO_T; 
delete from  KRIM_ENTITY_AFLTN_T; 
delete from  KRIM_ENTITY_EMAIL_T; 
delete from  KRIM_ENTITY_PHONE_T; 
delete from  KRIM_ENTITY_ADDR_T; 
delete from  KRIM_ENTITY_NM_T; 
delete from KRIM_ENTITY_ENT_TYP_T; 
--delete from KRIM_ENTITY_T WHERE ENTITY_ID NOT IN (SELECT ENTITY_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM IN ('quickstart', 'admin', 'kr', 'superuser'));
delete from KRIM_ENTITY_T WHERE ENTITY_ID NOT IN (SELECT ENTITY_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM IN ('quickstart'));
delete from KRIM_TYP_ATTR_T; 
delete from KRIM_ATTR_DEFN_T; 
delete from KRIM_TYP_T; 
delete from KRIM_EMP_STAT_T; 
delete from KRIM_CTZNSHP_STAT_T; 
delete from KRIM_EXT_ID_TYP_T; 
delete from KRIM_EMP_TYP_T; 
delete from KRIM_AFLTN_TYP_T; 
delete from KRIM_EMAIL_TYP_T; 
delete from KRIM_PHONE_TYP_T; 
delete from KRIM_ADDR_TYP_T; 
delete from KRIM_ENT_NM_TYP_T; 
delete from KRIM_ENT_TYP_T; 
delete from krns_nmspc_T where nmspc_cd in ('KR-SYS', 'KC-IDM', 'KC-UNT', 'KC-ADM', 'KC-SYS', 'KC-WKFLW');                   

insert into KRIM_ADDR_TYP_T (ADDR_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('HM', '5B97C50B03706110E0404F8189D85213', 1, 'Home', 'Y', 'b', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_ADDR_TYP_T (ADDR_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('OTH', '5B97C50B03716110E0404F8189D85213', 1, 'Other', 'Y', 'c', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_ADDR_TYP_T (ADDR_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('WRK', '5B97C50B03726110E0404F8189D85213', 1, 'Work', 'Y', 'a', to_date('13-11-2008', 'dd-mm-yyyy'));

insert into KRIM_AFLTN_TYP_T (AFLTN_TYP_CD, OBJ_ID, VER_NBR, NM, EMP_AFLTN_TYP_IND, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('GRD_STDNT_STAFF', '78E12A0AFCF94F50A969F42CFFB39CD6', 1, 'Graduate Student Staff', 'Y', 'Y', 'e', to_date('07-10-2009 13:19:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_AFLTN_TYP_T (AFLTN_TYP_CD, OBJ_ID, VER_NBR, NM, EMP_AFLTN_TYP_IND, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('RSRCH_STAFF', '38FA5343233048C2B8B7C10F840AE404', 1, 'Research Staff', 'Y', 'Y', 'f', to_date('07-10-2009 13:19:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_AFLTN_TYP_T (AFLTN_TYP_CD, OBJ_ID, VER_NBR, NM, EMP_AFLTN_TYP_IND, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('SRVC_STAFF', '43D4AA8B88E64EEBB366326EA285BDF2', 1, 'Service Staff', 'Y', 'Y', 'g', to_date('07-10-2009 13:19:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_AFLTN_TYP_T (AFLTN_TYP_CD, OBJ_ID, VER_NBR, NM, EMP_AFLTN_TYP_IND, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('SUPPRT_STAFF', '838B930186414158A15CFE54BCB6F4A1', 1, 'Support Staff', 'Y', 'Y', 'h', to_date('07-10-2009 13:19:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_AFLTN_TYP_T (AFLTN_TYP_CD, OBJ_ID, VER_NBR, NM, EMP_AFLTN_TYP_IND, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('OTH_ACADMC_GRP', '9DD4B7096239493D8B6087A3018D4C8A', 1, 'Other Academic Group', 'N', 'Y', 'i', to_date('07-10-2009 13:19:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_AFLTN_TYP_T (AFLTN_TYP_CD, OBJ_ID, VER_NBR, NM, EMP_AFLTN_TYP_IND, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('MED_STAFF', '3817C1C7E528492BA36F3DA9E56EBB3F', 1, 'Medical Staff', 'Y', 'Y', 'j', to_date('07-10-2009 13:19:27', 'dd-mm-yyyy hh24:mi:ss'));

insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('1007', '7C4715B8ECFE42639D326C562F9079E6', 1, 'sectionName', null, 'Y', 'KC-SYS', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('1008', 'DB4F5017C6634DEF8315DC82D6D87882', 1, 'documentAction', null, 'Y', 'KC-SYS', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('1034', '0EE6F03068C84E14ABB6172EC20A29CE', 1, 'proposal', 'Proposal Number', 'Y', 'KC-SYS', 'org.kuali.kra.kim.bo.KcKimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('1035', '28C70DC2EC1648CFB8600B14CFB3A581', 1, 'protocol', 'Protocol Number', 'Y', 'KC-SYS', 'org.kuali.kra.kim.bo.KcKimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('1036', 'A26C4B4DB5DD4FD5B5A9F9F77365C441', 1, 'committee', 'Committee Number', 'Y', 'KC-SYS', 'org.kuali.kra.kim.bo.KcKimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('1037', '3166D61D78A7445AA9C996E36B773113', 1, 'award', 'Award Number', 'Y', 'KC-SYS', 'org.kuali.kra.kim.bo.KcKimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('1038', 'D070A6E993F247848FA1696AC92FD363', 1, 'timeandmoney', 'TimeAndMoney Number', 'Y', 'KC-SYS', 'org.kuali.kra.kim.bo.KcKimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('1005', '9F2F1D462E0C40CE8A3B215FC01CD9A2', 1, 'unitNumber', 'Unit Number', 'Y', 'KC-SYS', 'org.kuali.kfs.sys.identity.KcKimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('1006', 'FB23FA5EE83C47A4BF8C8727068E370D', 1, 'subunits', 'Descend Flag', 'Y', 'KC-SYS', 'org.kuali.kfs.sys.identity.KcKimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('1', '5ADF18B6D4887954E0404F8189D85002', 1, 'beanName', null, 'Y', 'KR-SYS', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('10', '5ADF18B6D4917954E0404F8189D85002', 1, 'editMode', null, 'Y', 'KR-NS', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('11', '5ADF18B6D4927954E0404F8189D85002', 1, 'parameterName', null, 'Y', 'KR-NS', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('12', '5ADF18B6D4937954E0404F8189D85002', 1, 'campusCode', null, 'Y', 'KR-NS', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('13', '5ADF18B6D4947954E0404F8189D85002', 1, 'documentTypeName', null, 'Y', 'KR-WKFLW', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('14', '5ADF18B6D4957954E0404F8189D85002', 1, 'actionRequestCd', null, 'Y', 'KR-WKFLW', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('15', '5ADF18B6D4967954E0404F8189D85002', 1, 'routeStatusCode', null, 'Y', 'KR-WKFLW', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('16', '5ADF18B6D4977954E0404F8189D85002', 1, 'routeNodeName', null, 'Y', 'KR-WKFLW', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('17', '5ADF18B6D4987954E0404F8189D85002', 1, 'entityTypeCode', null, 'Y', 'KR-IDM', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('18', '5ADF18B6D4997954E0404F8189D85002', 1, 'roleName', null, 'Y', 'KR-IDM', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('19', '5ADF18B6D49A7954E0404F8189D85002', 1, 'permissionName', null, 'Y', 'KR-IDM', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('2', '5ADF18B6D4897954E0404F8189D85002', 1, 'actionClass', null, 'Y', 'KR-SYS', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('20', '5ADF18B6D49B7954E0404F8189D85002', 1, 'responsibilityName', null, 'Y', 'KR-IDM', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('21', '5ADF18B6D49C7954E0404F8189D85002', 1, 'groupName', null, 'Y', 'KR-IDM', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('3', '5ADF18B6D48A7954E0404F8189D85002', 1, 'buttonName', null, 'Y', 'KR-SYS', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('4', '5ADF18B6D48B7954E0404F8189D85002', 1, 'namespaceCode', null, 'Y', 'KR-NS', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('40', '5C4970B2B2DF8277E0404F8189D80B30', 1, 'required', null, 'Y', 'KR-WKFLW', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('41', '5C4970B2B2E08277E0404F8189D80B30', 1, 'actionDetailsAtRoleMemberLevel', null, 'Y', 'KR-WKFLW', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('42', '5C7D997640635002E0404F8189D86F11', 1, 'documentNumber', null, 'Y', 'KR-NS', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('44', '603B735FA6BCFE21E0404F8189D8083B', 1, 'sectionId', null, 'Y', 'KR-NS', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('5', '5ADF18B6D48C7954E0404F8189D85002', 1, 'componentName', null, 'Y', 'KR-NS', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('6', '5ADF18B6D48D7954E0404F8189D85002', 1, 'propertyName', null, 'Y', 'KR-NS', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('7', '603B735FA6BAFE21E0404F8189D8083B', 1, 'existingRecordsOnly', null, 'Y', 'KR-NS', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('8', '5ADF18B6D48F7954E0404F8189D85002', 1, 'createdBySelfOnly', null, 'Y', 'KR-NS', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('9', '5ADF18B6D4907954E0404F8189D85002', 1, 'attachmentTypeCode', null, 'Y', 'KR-NS', 'org.kuali.rice.kim.bo.impl.KimAttributes');
insert into KRIM_ATTR_DEFN_T (KIM_ATTR_DEFN_ID, OBJ_ID, VER_NBR, NM, LBL, ACTV_IND, NMSPC_CD, CMPNT_NM)
values ('46', '69FA55ACC2EE2598E0404F8189D86880', 1, 'qualifierResolverProvidedIdentifier', null, 'Y', 'KR-IDM', 'org.kuali.rice.kim.bo.impl.KimAttributes');

insert into KRIM_EMAIL_TYP_T (EMAIL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('HM', '5B97C50B03776110E0404F8189D85213', 1, 'Home', 'Y', 'b', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_EMAIL_TYP_T (EMAIL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('OTH', '5B97C50B03786110E0404F8189D85213', 1, 'Other', 'Y', 'c', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_EMAIL_TYP_T (EMAIL_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('WRK', '5B97C50B03796110E0404F8189D85213', 1, 'Work', 'Y', 'a', to_date('13-11-2008', 'dd-mm-yyyy'));

insert into KRIM_EMP_STAT_T (EMP_STAT_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('A', '5B97C50B037A6110E0404F8189D85213', 1, 'Active', 'Y', '01', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_EMP_STAT_T (EMP_STAT_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('D', '5B97C50B037B6110E0404F8189D85213', 1, 'Deceased', 'Y', '99', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_EMP_STAT_T (EMP_STAT_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('L', '5B97C50B037C6110E0404F8189D85213', 1, 'On Non-Pay Leave', 'Y', '04', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_EMP_STAT_T (EMP_STAT_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('N', '5B97C50B037D6110E0404F8189D85213', 1, 'Status Not Yet Processed', 'Y', '03', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_EMP_STAT_T (EMP_STAT_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('P', '5B97C50B037E6110E0404F8189D85213', 1, 'Processing', 'Y', '02', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_EMP_STAT_T (EMP_STAT_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('R', '5B97C50B037F6110E0404F8189D85213', 1, 'Retired', 'Y', '10', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_EMP_STAT_T (EMP_STAT_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('T', '5B97C50B03806110E0404F8189D85213', 1, 'Terminated', 'Y', '97', to_date('13-11-2008', 'dd-mm-yyyy'));

insert into KRIM_EMP_TYP_T (EMP_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('N', '5B97C50B03826110E0404F8189D85213', 1, 'Non-Professional', 'Y', '02', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_EMP_TYP_T (EMP_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('O', '5B97C50B03836110E0404F8189D85213', 1, 'Other', 'Y', '99', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_EMP_TYP_T (EMP_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('P', '5B97C50B03846110E0404F8189D85213', 1, 'Professional', 'Y', '01', to_date('13-11-2008', 'dd-mm-yyyy'));

insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1', '5B1B6B919CC96496E0404F8189D822F2', 1, 'Y', to_date('07-11-2008 09:49:02', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1200', '595B0FE2D14D476AA6A5EE6108C1EC1C', 1, 'Y', to_date('07-10-2009 13:23:52', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1201', '59A1A220774D4F77BC87E735E2C1A2DD', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1202', '2E101CB3BED84A689A0F91BD57BB5105', 1, 'N', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1203', 'B93ED87101F042DB9B742E48F387483F', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1204', '1CBEAD437CE143F3B870A61F2D056C15', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1205', '40DEE21D0E49421993031B88EB758727', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1206', '63D867005FBE444F80DE5FDEBAC52210', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1207', '474AEA4DE15D42C6B1C16F37BA61B2B7', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1208', 'ECD1B5BB533144E7B510DEA34E9F2630', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1209', '81C957386C9746FD884241E4C57942E0', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1210', '316601045F7D4431B367E8093F8B85B5', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1211', '6AC9FFAC6EA344C3A3ED0D277F4D8355', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1212', 'B6EA255158AF45B1B555BF467CF9046A', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1213', 'E10725D7484E45F598232221590727B5', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1214', 'AD8034E2555A496696BA2EBB1D885DE7', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1215', '080CD6BBBF8C40BAB24202D3633866C7', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1216', '3F0E60835D824F0AAA239AD96A198AFB', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1217', 'E70E3CC82B4644CDA96E0ED16E2F870A', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1218', '9F96BA82CB9F41AC99403CC58CDC5CB7', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1219', '174FD432DFC441B2B91588BB89F75F65', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1220', '5C11A9D32E8447B18E2EF42EB28EC6AF', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1221', '231886D6E5D74A79BEC2FB3537347705', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1222', 'D6A936E6B77E4B3DB8FD52CB4AFE0C96', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1223', '395A147FDF484636B3ED989613619952', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1224', '48A151085389419784BD06C1A18E5734', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1225', '59AF450B90714F7498BB54B258C42225', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1226', '717FC5EDF6694D06BAF328FA5095CB36', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1227', '2973EF8D220143C480AB3803ED6A379F', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1228', 'D7F7988A1EFD495B83C078207010AD7D', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1229', '6F0B7E01EEB647F4AF7C99C3BDC93E51', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1230', '43D484E099AD48CDBA392B5467869B7B', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1231', '3E992993B64545628ADEB360366A0E7D', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1232', '73C5AA63CC9B45779E08DB710593DF22', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1233', 'FCF842FF6FCC4E128D4F25E7A40C1EBC', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1234', 'A1B423924E5A4E73A82A6C01019C0EDC', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1235', 'FAF39C1106C44BB3A8DA04AC3D4D9624', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1236', '785C2CBB12CF4251B57548389E167DE1', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1237', '2D9B86E683F84E7482F3692CDCEFE34F', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1238', 'C3E962B54AF14284A086381BF7DBB8AF', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1239', 'C1FD8A4F0535466AB1B6AD0DAF8F3691', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1240', 'ECE84DAE16B54555B8D24E161760E9E4', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1241', 'F25FA93547964346AB77B3B823A81428', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1242', '72838ECC8CBC4343B057CC5F7E348C5A', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1243', '0C4673013D254773A947434441CA1B98', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1244', 'E1C1CEB20ECB45EF9D9DE021E5E25496', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1245', '74EF34A646B145FCAC15728C84B468F3', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1246', '66D3FF4778DE4C8598C2CB7639C24D2F', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1247', '607C713FEB3B490FBFECEF9276055991', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1248', '99CA29E0E15A42A19DE3AB6713C73B2C', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1249', 'D97B1CFC1309472EB773E21C3891C1E5', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1250', 'CE706FB06F4044EE969FB81A5B9591A8', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1251', '13457C99EFF74E10979C55C4B1E92428', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1252', '26748B208E2E4424BFD8216C6549554E', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1253', '7A0E759CBF03417EB6F052C869C33274', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1254', 'A10675B51FC546A9B72AEFB4F239064A', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1255', '6E47D1CC270D4FF486E2F51F58E441A0', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1256', 'BE0D813EB9E0476C8F343CE6330357DF', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1257', '6F67EAC19AD6465EB75E940BEA78B5C4', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1258', '5C7A9B2787244F6684A78446688C46A0', 1, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));

insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-11-2008 09:49:02', 'dd-mm-yyyy hh24:mi:ss'), '5B1B6B919CCB6496E0404F8189D822F2', 1, 'SYSTEM', '1');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:52', 'dd-mm-yyyy hh24:mi:ss'), '640775B8AF1C402DB5FEF7C9E080FE12', 1, 'PERSON', '1200');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '5B6D4119F923441986149F4C70AAE795', 1, 'PERSON', '1201');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('N', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '042166910E4F4F82B4A11251420DA8B7', 1, 'PERSON', '1202');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '8D4D06BC01E24ADCB750FAFA4E2F2389', 1, 'PERSON', '1203');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '69EAC444CCD7409F8AEB7AD819D21A1F', 1, 'PERSON', '1204');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '0F044460E35547FCBEECF07A350BF2E4', 1, 'PERSON', '1205');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '9B34A926A1824081881B1B0B0A20B1A9', 1, 'PERSON', '1206');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '3FA751FF6C78455FA13D3A882D2DD929', 1, 'PERSON', '1207');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '57ACD91AE577495DA55430AC11CD5836', 1, 'PERSON', '1208');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '4CD01C6DCBD646699D87F1128A75F87F', 1, 'PERSON', '1209');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'E67D7F45019648ACB087393822BA732B', 1, 'PERSON', '1210');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '1A27274347E94E0F98E227D29B44C37D', 1, 'PERSON', '1211');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'A9C2B9EEA1044573811CB14B528FCC0F', 1, 'PERSON', '1212');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '8BCE49A5C6F342F5BCD8A00E22FC79F0', 1, 'PERSON', '1213');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '252C6581309842C7BB907C9E37F24BCB', 1, 'PERSON', '1214');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '7B7DF445CE8345EB8517BDFE66D39512', 1, 'PERSON', '1215');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'B083190773ED4F57850437268669DDA4', 1, 'PERSON', '1216');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '597534EF64B6467798BCFB83F810F62F', 1, 'PERSON', '1217');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '6F586C63EBDC438FA47E09149D010020', 1, 'PERSON', '1218');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '9A1D147E62AF40F7AE20F05D7B916B32', 1, 'PERSON', '1219');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '738B64CB12D34C3D943522B35BB08F85', 1, 'PERSON', '1220');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '2133FE64E7A34DADB55DF5064BB10B48', 1, 'PERSON', '1221');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '1E50AD50218E44DA98996DBA9AB90021', 1, 'PERSON', '1222');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'FB4F8AB72FDF457C876491C97F370ED0', 1, 'PERSON', '1223');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '452EF68A3A7644C2816C692B8EC43F28', 1, 'PERSON', '1224');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'D04DB12DB98848CC8EE32467DC4ECFC2', 1, 'PERSON', '1225');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'E408EA0547AF46B9BA1A44504FFCE265', 1, 'PERSON', '1226');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'E29DD2AD56A143DC85AD3FCC0EEB84F1', 1, 'PERSON', '1227');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'BC907A7944BC4761A3E394F1626E2CF4', 1, 'PERSON', '1228');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '09521A3AC7B54C128362BA50412122C0', 1, 'PERSON', '1229');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'D6011B340DA949CFB33D4805D2EA235A', 1, 'PERSON', '1230');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '6D5A6BB39F0E4DF0A22239626598881A', 1, 'PERSON', '1231');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '8707A5CC43714AAAA6AC998027ECCA8D', 1, 'PERSON', '1232');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '6540F2A90AD64589AA52FE2C9819D8C8', 1, 'PERSON', '1233');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'D870A34D60A74991A11A11D4F3C1D0E1', 1, 'PERSON', '1234');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '51919B1B62DA4BFE9361C82F322EDA65', 1, 'PERSON', '1235');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'A2873923F06941C98938C8C2533CED80', 1, 'PERSON', '1236');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'FC326B254C96442EA3A6E36F09F54C65', 1, 'PERSON', '1237');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '51E6DB286781450DB431A0F4ADC066BD', 1, 'PERSON', '1238');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'DB5A16E7BA7441759CB2579AF79053C7', 1, 'PERSON', '1239');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '8E145754BA07402ABD935D307C422594', 1, 'PERSON', '1240');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '1DA016A1132A4EAFA9E0E2DDBD1028D7', 1, 'PERSON', '1241');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '7B3C9C5F1AA74866A0F5C2D926E49801', 1, 'PERSON', '1242');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '7FF4ECE8C5424CD9B2D350F65D5ABC09', 1, 'PERSON', '1243');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'B92AA39960994446A58D7CDCDBC05319', 1, 'PERSON', '1244');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'A85B19B64E4E474A9551A3F21F69A88B', 1, 'PERSON', '1245');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '8BA96894EEC246AB816F6316DFAD0490', 1, 'PERSON', '1246');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '4926873819F3430CA5C7AD0CF366BD8B', 1, 'PERSON', '1247');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'B11F0F1B862B4FB2B35F7E18DCF8C8F5', 1, 'PERSON', '1248');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '9DDACADC833F4F43B825B840E0D7A512', 1, 'PERSON', '1249');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'B8DFC66DB3B640B186630565F834B98F', 1, 'PERSON', '1250');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '56B59EF731E24E029E80B387F4911B19', 1, 'PERSON', '1251');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '5D46EC74BDA84B74B1D2DD88AB433A43', 1, 'PERSON', '1252');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '94186FD44E4C449BB0EC76E08A80BF85', 1, 'PERSON', '1253');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '252B3D922C6246AAA4948936984CE034', 1, 'PERSON', '1254');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '271F4BFD31FC454F8191401AA08A38AF', 1, 'PERSON', '1255');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '3A158B44EEF543E3823996770770E4CC', 1, 'PERSON', '1256');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), 'A0B58387B56C4D8EA5ACF3B90D099114', 1, 'PERSON', '1257');
insert into KRIM_ENTITY_ENT_TYP_T (ACTV_IND, LAST_UPDT_DT, OBJ_ID, VER_NBR, ENT_TYP_CD, ENTITY_ID)
values ('Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '8684F527DD7146A4BCC7B39F340A9FE9', 1, 'PERSON', '1258');

insert into KRIM_EXT_ID_TYP_T (EXT_ID_TYP_CD, OBJ_ID, VER_NBR, NM, ENCR_REQ_IND, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('ERAC', 'B1DD3B3E4CE0405D8C1E0237E53E701D', 1, 'Electronic Research Admin Commons User', 'N', 'Y', '08', to_date('07-10-2009 13:19:27', 'dd-mm-yyyy hh24:mi:ss'));

insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1300', '71203B869EE8494DBFF91B98BEC397F5', 1, (SELECT ENTITY_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'PRFR', 'Geoff', null, 'McGregor', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:52', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1301', 'A7208FDDBEA24EC59937F42EB8A204C0', 1, '1201', 'PRFR', 'Joe', null, 'Tester', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1302', '463B3A369A9F4959A2BEE2D0BE5CE0A1', 1, '1202', 'PRFR', 'KULUSER', null, 'KULUSER', null, null, 'Y', 'N', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1303', '1BBEFC8EB2AA4B4FA4B40257A03DDDBB', 1, '1203', 'PRFR', 'Nicholas', null, 'Majors', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1304', '26AABC4CEE054809ADFC0C77125DB5E7', 1, '1204', 'PRFR', 'Inez', null, 'Chew', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1305', '0D97FB11A5D444EC80AA3492EBB5F0E1', 1, '1205', 'PRFR', 'Della', null, 'Woods', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1306', 'A1D73162454A4F889483BA434CB42848', 1, '1206', 'PRFR', 'Opal', null, 'Blood', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1307', 'EC9B78CB6967428D9B4BD32C0E552104', 1, '1207', 'PRFR', 'Allyson', null, 'Cate', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1308', '44FA726234D24D348FA3573176FF9EFB', 1, '1208', 'PRFR', 'JAY', null, 'HOULIHAN', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1309', '6A3F72C3EAE345CDB3E7EB02BE3D121E', 1, '1209', 'PRFR', 'RAYMOND', null, 'PAULIN', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1310', 'DC44F8E1779A4F49BF36EB54766A595C', 1, '1210', 'PRFR', 'DOMINIC', null, 'NUGENT', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1311', 'F788743F2EC945EAA2C549BB719BB6F3', 1, '1211', 'PRFR', 'MARLON', null, 'SHORTER', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1312', '2715F68EF3A34109A0572A86A1978143', 1, '1212', 'PRFR', 'MARGUERITE', null, 'UNDERWOOD', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1313', '988D0B4213BF4FFCAC142F88B6CA57F4', 1, '1213', 'PRFR', 'HIRAM', null, 'EUBANK', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1314', '40087F6EAB474C3E9BF479CC0FAFC601', 1, '1214', 'PRFR', 'LLOYD', null, 'BARGER', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1315', '0A3C9C7830D44DBEB0E371DD052C3C64', 1, '1215', 'PRFR', 'JUANA', null, 'KAHLER', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1316', '64DFBE17EEE9416A84EA34BE0155FC79', 1, '1216', 'PRFR', 'ELBA', null, 'BYLER', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1317', '70C3FF5DDCA54C0CB06B1D93E31A633F', 1, '1217', 'PRFR', 'ALAN', null, 'MCAFEE', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1318', '2C6DB9F09E2449A6B625A53D59ABD2BB', 1, '1218', 'PRFR', 'HENRY', null, 'WESTFALL', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1319', '2B6F84FD7AF24359BB1ED6A8FBF39D93', 1, '1219', 'PRFR', 'BURT', null, 'SHANKLE', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1320', '37F5FE7209E847AA8CA008AEEFE6613F', 1, '1220', 'PRFR', 'ROBERTA', null, 'FERLAND', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1321', 'C0AC9C33FFA745659D452E7982A3C4F0', 1, '1221', 'PRFR', 'KENDRICK', null, 'CRABTREE', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1322', '60C4940B720A4713835F74A3FAC07940', 1, '1222', 'PRFR', 'OLIVE', null, 'FELKER', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1323', '5CBF64B953E14656BDA279EF1476C2C9', 1, '1223', 'PRFR', 'AUDREY', null, 'MARTIN', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1324', '8B0F6AF96F9B4808817F5B232DECA775', 1, '1224', 'PRFR', 'EVERETTE', null, 'STERNER', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1325', '9599DA4A36A94130A0BFF3186959134B', 1, '1225', 'PRFR', 'CORINA', null, 'BAYLESS', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1326', '432DA87A7A5E43F9A1F358D672D04450', 1, '1226', 'PRFR', 'THEODORE', null, 'KATO', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1327', '3EE6CF26B163442A91462391406DC577', 1, '1227', 'PRFR', 'CLAUDIA', null, 'HOVIS', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1328', '581E6C117DF343AFB5948D7753F2A1BE', 1, '1228', 'PRFR', 'RAFAEL', null, 'MCINTYRE', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1329', 'D91D4A04C4F847778178F5C772355DE3', 1, '1229', 'PRFR', 'EMORY', null, 'EAGLE', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1330', '89ED6154551344BE97FF5EC315363147', 1, '1230', 'PRFR', 'MITZI', null, 'POND', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1331', 'FF9CAEAAD902492397929991BB46B650', 1, '1231', 'PRFR', 'ANGELICA', null, 'FLAHERTY', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1332', '29010EACB9D548E6AFC22A93E90410CE', 1, '1232', 'PRFR', 'MASON', null, 'KOSS', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1333', '19A4D63A337147A98821B257E339836A', 1, '1233', 'PRFR', 'ORA', null, 'MCEACHERN', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1334', 'A660CE075B7F446CA3FFEEBAAB8748A2', 1, '1234', 'PRFR', 'WM', null, 'ACUNA', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1335', 'AC1A71FD5C11491D8C95A28873856C70', 1, '1235', 'PRFR', 'MARITZA', null, 'SCHULTE', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1336', '4483FE38C9FE4DEC941654755F2E5DF7', 1, '1236', 'PRFR', 'OLIVER', null, 'DEVRIES', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1337', '62971313B2E944E59D6B4A31BA840625', 1, '1237', 'PRFR', 'DARREN', null, 'DEVANEY', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1338', '59C74EA04173420EA9D6E9652E1DCA81', 1, '1238', 'PRFR', 'PAULETTE', null, 'SMOCK', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1339', '281864CCB3154CE4B7C20A7A9126F5E3', 1, '1239', 'PRFR', 'LOGAN', null, 'CLINKSCALES', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1340', 'E9F62236D80047388D194D067FDAB50D', 1, '1240', 'PRFR', 'ANTWAN', null, 'BRUNELLE', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1341', '5F0E4FD6488E491D815F8F499CF600D4', 1, '1241', 'PRFR', 'CONCETTA', null, 'BERNAL', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1342', '89C763C29CAA48D8897D404BEC23CFB8', 1, '1242', 'PRFR', 'BARBRA', null, 'BORST', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1343', 'E026E8B6485E497786DA09554D89484A', 1, '1243', 'PRFR', 'WALLACE', null, 'SOILEAU', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1344', '3ACD56B6571E417FB2F6F6CCA161F9E3', 1, '1244', 'PRFR', 'ALTHEA', null, 'BURD', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1345', '237C3C4195E049A3B8DE9D2568F0396B', 1, '1245', 'PRFR', 'LOUELLA', null, 'ENGEL', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1346', '5FA23748162E45E389053E7C3387CDCE', 1, '1246', 'PRFR', 'FAYE', null, 'HEADLEY', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1347', '72E9E4D2C3454920A41D79795ACBAEFE', 1, '1247', 'PRFR', 'RONDA', null, 'GAILEY', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1348', '9D7242217F544D00AA5362811302BC67', 1, '1248', 'PRFR', 'IVAN', null, 'SHIELDS', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1349', '53E57C7D63E34E44A731644549C4D2BC', 1, '1249', 'PRFR', 'OLIVER', null, 'ELGIN', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1350', 'A5886DCD2BAC4D43B67B5B1D702627E8', 1, '1250', 'PRFR', 'BEATRIZ', null, 'BOMAN', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1351', 'BA360F5DD312486DB0B6A1A3C7B4F7C9', 1, '1251', 'PRFR', 'TREVOR', null, 'DUGGINS', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1352', 'F34C97B617C04582A4260E1EE523CF2F', 1, '1252', 'PRFR', 'KELLY', null, 'OWENS', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1353', 'E18476662E084ADD85BDCC5B943233AB', 1, '1253', 'PRFR', 'NOELLE', null, 'DARR', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1354', 'D25DFBEDF9514788A34C20AF072CB8CF', 1, '1254', 'PRFR', 'CECILE', null, 'CARY', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1355', 'ADE4EF82F55C472FB44E578801E91A21', 1, '1255', 'PRFR', 'CHARITY', null, 'MCBEE', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1356', 'A233988425A1421EB63BF160E473BF05', 1, '1256', 'PRFR', 'DAMON', null, 'RALSTON', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1357', '32389CC015DF468E842830FC1920DF80', 1, '1257', 'PRFR', 'MERRILL', null, 'MARTIN', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ENTITY_NM_T (ENTITY_NM_ID, OBJ_ID, VER_NBR, ENTITY_ID, NM_TYP_CD, FIRST_NM, MIDDLE_NM, LAST_NM, SUFFIX_NM, TITLE_NM, DFLT_IND, ACTV_IND, LAST_UPDT_DT)
values ('1358', 'E2ED08D2CCBD46918A01C92A1ED662E9', 1, '1258', 'PRFR', 'Roger', null, 'Rabbit', null, null, 'Y', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'));

insert into KRIM_PHONE_TYP_T (PHONE_TYP_CD, OBJ_ID, VER_NBR, PHONE_TYP_NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('FAX', '5093BE89D2E54F63B91B5AA7B6B6BF12', 1, 'Fax', 'Y', 'e', to_date('07-10-2009 13:19:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_PHONE_TYP_T (PHONE_TYP_CD, OBJ_ID, VER_NBR, PHONE_TYP_NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('PGR', 'DD82FF090BB142E5931EF4EF2D5B8EEA', 1, 'Pager', 'Y', 'f', to_date('07-10-2009 13:19:27', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_PHONE_TYP_T (PHONE_TYP_CD, OBJ_ID, VER_NBR, PHONE_TYP_NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('WRK', 'F6D778180F2D41A2AD5CA86B93003DAD', 1, 'Work Phone', 'Y', 'f', to_date('07-10-2009 13:19:27', 'dd-mm-yyyy hh24:mi:ss'));

insert into KRIM_ENT_NM_TYP_T (ENT_NM_TYP_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, DISPLAY_SORT_CD, LAST_UPDT_DT)
values ('PRIOR', '39E3B942191E46FDAB65774EB1CC1ED9', 1, 'Prior', 'Y', 'd', to_date('07-10-2009 13:19:27', 'dd-mm-yyyy hh24:mi:ss'));

insert into KRIM_ENT_TYP_T (ENT_TYP_CD, OBJ_ID, VER_NBR, NM, DISPLAY_SORT_CD, ACTV_IND)
values ('PERSON', '5B97C50B03886110E0404F8189D85213', 1, 'Person', '01', 'Y');
insert into KRIM_ENT_TYP_T (ENT_TYP_CD, OBJ_ID, VER_NBR, NM, DISPLAY_SORT_CD, ACTV_IND)
values ('SYSTEM', '5B97C50B03896110E0404F8189D85213', 1, 'System', '02', 'Y');

insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('1017', 'A2C8D80F850B4F349D58906BFD77DEFD', 1, 'Document Section', 'defaultPermissionTypeService', 'Y', ' KC-SYS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('1018', 'D2C825C9A2A54BE68CABC404728C2B17', 1, 'Document Action', 'defaultPermissionTypeService', 'Y', 'KC-SYS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('1019', 'F623831EFD0B420496889FF4CB441D1D', 1, 'Document Key', 'kimTypeService', 'Y', 'KC-SYS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('1040', '6E00452E0F42420A8358DEEF2DC2218C', 1, 'ProposalType', 'kimTypeService', 'Y', 'KC-SYS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('1041', '43CBE324B5B242B8993DC1C1140C60DA', 1, 'ProtocolType', 'kimTypeService', 'Y', 'KC-SYS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('1042', 'CEAB213CD4754F63AEBF9B40AA797E55', 1, 'CommitteeType', 'kimTypeService', 'Y', 'KC-SYS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('1043', '1FC654D245A94E4DBFC2A382863D4F64', 1, 'AwardType', 'kimTypeService', 'Y', 'KC-SYS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('1044', '9E3B35E4537A486E82517C30D8BD0AC5', 1, 'TimeAndMoneyType', 'kimTypeService', 'Y', 'KC-SYS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('1012', 'B27489CC2C8C437FA8AB19BB81467400', 1, 'Unit', 'unitRoleTypeService', 'Y', 'KC-SYS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('1013', '88E021C678224777B2708CBFD537FB53', 1, 'UnitHierarchy', 'unitHierarchyRoleTypeService', 'Y', 'KC-SYS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('1014', 'F9D1C8B9B30E4A6DADA01BDA3010E6B6', 1, 'Derived Role - PI', 'proposalRoleTypeService', 'Y', 'KC-WKFLW');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('1015', '67BE6479E68645DFB64ED0B8927E442B', 1, 'Derived Role - COI', 'proposalRoleTypeService', 'Y', 'KC-WKFLW');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('1016', 'DA383920921646B89951C9F4B20C1EE6', 1, 'Derived Role - KeyPerson', 'proposalRoleTypeService', 'Y', 'KC-WKFLW');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('1', '5ADF18B6D4827954E0404F8189D85002', 1, 'Default', null, 'Y', 'KUALI');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('10', '5ADF18B6D4D77954E0404F8189D85002', 1, 'Namespace or Component', 'namespaceOrComponentPermissionTypeService', 'Y', 'KR-NS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('11', '5ADF18B6D4DC7954E0404F8189D85002', 1, 'Component Field', 'componentFieldPermissionTypeService', 'Y', 'KR-NS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('12', '5ADF18B6D4E37954E0404F8189D85002', 1, 'Namespace or Action', 'namespaceOrActionPermissionTypeService', 'Y', 'KR-NS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('13', '5ADF18B6D4E77954E0404F8189D85002', 1, 'Button', 'buttonPermissionTypeService', 'Y', 'KR-NS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('14', '5ADF18B6D4EA7954E0404F8189D85002', 1, 'Edit Mode & Document Type', 'documentTypeAndEditModePermissionTypeService', 'Y', 'KR-NS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('15', '5ADF18B6D4ED7954E0404F8189D85002', 1, 'Batch Feed or Job', 'batchFeedOrJobPermissionTypeService', 'Y', 'KR-NS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('16', '5ADF18B6D4F27954E0404F8189D85002', 1, 'Parameter', 'parameterPermissionTypeService', 'Y', 'KR-NS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('18', '5ADF18B6D4F97954E0404F8189D85002', 1, 'Role', 'rolePermissionTypeService', 'Y', 'KR-IDM');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('19', '5ADF18B6D4FD7954E0404F8189D85002', 1, 'Permission', 'permissionPermissionTypeService', 'Y', 'KR-IDM');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('2', '5ADF18B6D4837954E0404F8189D85002', 1, 'Derived Role: Principal', 'activePrincipalRoleTypeService', 'Y', 'KR-IDM');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('20', '5ADF18B6D5017954E0404F8189D85002', 1, 'Responsibility', 'responsibilityPermissionTypeService', 'Y', 'KR-IDM');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('21', '5ADF18B6D5057954E0404F8189D85002', 1, 'Group', 'groupPermissionTypeService', 'Y', 'KR-IDM');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('22', '5ADF18B6D5097954E0404F8189D85002', 1, 'Entity Type & Field(s)', null, 'Y', 'KR-IDM');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('3', '5ADF18B6D4AC7954E0404F8189D85002', 1, 'Document Type (Permission)', 'documentTypePermissionTypeService', 'Y', 'KR-SYS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('4', '5ADF18B6D4B57954E0404F8189D85002', 1, 'Action Request Type', 'actionRequestTypePermissionTypeService', 'Y', 'KR-WKFLW');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('42', '5ADF18B6D53B7954E0404F8189D85002', 1, 'Derived Role: Action Request', 'actionRequestDerivedRoleTypeService', 'Y', 'KR-WKFLW');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('43', '5ADF18B6D53C7954E0404F8189D85002', 1, 'Derived Role: Route Log', 'routeLogDerivedRoleTypeService', 'Y', 'KR-WKFLW');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('45', '5B6013B3AD131A9CE0404F8189D87094', 1, 'Derived Role: Permission (Edit Document)', 'documentEditorRoleTypeService', 'Y', 'KR-NS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('5', '5ADF18B6D4B87954E0404F8189D85002', 1, 'Ad Hoc Review', 'adhocReviewPermissionTypeService', 'Y', 'KR-WKFLW');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('52', '5C997D14EAC2FE40E0404F8189D87DC5', 1, 'Document Type, Routing Node & Field(s)', 'documentTypeAndNodeAndFieldsPermissionTypeService', 'Y', 'KR-SYS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('54', '60432A73A6A29F65E0404F8189D86AA4', 1, 'Document Type (Responsibility)', 'documentTypeResponsibilityTypeService', 'Y', 'KR-KEW');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('56', '603B735FA6B9FE21E0404F8189D8083B', 1, 'Document Type & Existing Records Only', 'documentTypeAndExistingRecordsOnlyPermissionTypeService', 'Y', 'KR-NS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('57', '603B735FA6BDFE21E0404F8189D8083B', 1, 'Component Section', 'componentSectionPermissionTypeService', 'Y', 'KR-NS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('59', '606763510FBB96D3E0404F8189D857A2', 1, 'Document Type & Relationship to Note Author', 'documentTypeAndRelationshipToNoteAuthorPermissionTypeService', 'Y', 'KR-NS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('60', '606763510FBC96D3E0404F8189D857A2', 1, 'Derived Role: Permission (Open Document)', 'documentOpenerRoleTypeService', 'Y', 'KR-NS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('7', '5ADF18B6D4C07954E0404F8189D85002', 1, 'Document Type, Routing Node & Action Information', 'reviewResponsibilityTypeService', 'Y', 'KR-WKFLW');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('8', '5ADF18B6D4C67954E0404F8189D85002', 1, 'Document Type & Routing Node or State', 'documentTypeAndNodeOrStatePermissionTypeService', 'Y', 'KR-SYS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('9', '5ADF18B6D4CD7954E0404F8189D85002', 1, 'Document Type & Attachment Type', 'documentTypeAndAttachmentTypePermissionTypeService', 'Y', 'KR-NS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('66', '67F145466E8A9160E0404F8189D86771', 1, 'Derived Role: Permission (Initiate Document)', 'documentInitiatorRoleTypeService', 'Y', 'KR-SYS');
insert into KRIM_TYP_T (KIM_TYP_ID, OBJ_ID, VER_NBR, NM, SRVC_NM, ACTV_IND, NMSPC_CD)
values ('67', '67F145466E8F9160E0404F8189D86771', 1, 'Namespace', 'namespacePermissionTypeService', 'Y', 'KR-NS');

insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1000004', 'CB110D669DA84DC8B2EA302A71DBAB51', 1, 'KcAdmin', 'KC-WKFLW', 'Kuali Coeus Administrator', '1', 'Y', to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1000005', 'CE16790479764170964F4AECDE20E7F1', 1, 'OSP Superuser', 'KC-WKFLW', 'Kuali Coeus Administrator', '1', 'Y', to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1000006', '4870FB5A03D34006B82890E3F55B4E07', 1, 'Proposal Admin', 'KC-WKFLW', 'Proposal Workflow Administrator', '1', 'Y', to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1000007', '4A19232D0D044FD394DD383798B5DDD8', 1, 'IRBAdmin', 'KC-WKFLW', 'IRB Administrator', '1', 'Y', to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1', '5B97C50B04A16110E0404F8189D85213', 1, 'WorkflowAdmin', 'KR-WKFLW', 'WorkflowAdmin', null, 'Y', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('2000', '5B97C50B04A26110E0404F8189D85213', 1, 'NotificationAdmin', 'KR-WKFLW', 'Notification system admin group for automation.', null, 'Y', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('2001', '5B97C50B04A36110E0404F8189D85213', 1, 'TestGroup1', 'KFS', 'Test group 1 for KEN testing.', null, 'Y', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('2002', '5B97C50B04A46110E0404F8189D85213', 1, 'TestGroup2', 'KFS', 'Test group 2 for KEN testing.', null, 'Y', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('2003', '5B97C50B04A56110E0404F8189D85213', 1, 'RiceTeam', 'KFS', 'A test workgroup for KEN.', null, 'Y', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('2004', '5B97C50B04A66110E0404F8189D85213', 1, 'Group0', 'KFS', 'Group0', null, 'Y', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('2005', '5B97C50B04A76110E0404F8189D85213', 1, 'Group1', 'KFS', 'Group1', null, 'Y', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('2006', '5B97C50B04A86110E0404F8189D85213', 1, 'Group2', 'KFS', 'Group2', null, 'Y', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('2007', '5B97C50B04A96110E0404F8189D85213', 1, 'Kuali Identity Managers', 'KFS', 'Kuali Identity Managers', null, 'Y', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('2008', '5B97C50B04AA6110E0404F8189D85213', 1, 'Kuali Administrators', 'KFS', 'Kuali Administrators', null, 'Y', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('2009', '5B97C50B04AB6110E0404F8189D85213', 1, 'Kuali Developers', 'KFS', 'Kuali Developers', null, 'Y', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('2200', '5B97C50B04B06110E0404F8189D85213', 1, 'eDoc.Example1.SuperUsers', 'KFS', 'edoclite documentation uses this. Do not change or delete.', null, 'Y', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('2201', '5B97C50B04B16110E0404F8189D85213', 1, 'eDoc.Example1.defaultExceptions', 'KFS', 'edoclite documentation uses this. Do not change or delete.', null, 'Y', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('2202', '5B97C50B04B26110E0404F8189D85213', 1, 'eDoc.Example1.IUB.Workgroup', 'KFS', 'Edoclite Documentation workgroup for example edoclite named eDoc.Example1', null, 'Y', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('2203', '5B97C50B04B36110E0404F8189D85213', 1, 'eDoc.Example1.IUPUI.Workgroup', 'KFS', 'Edoclite Documentation workgroup for example edoclite named eDoc.Example1', null, 'Y', to_date('13-11-2008', 'dd-mm-yyyy'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('2301', '6881A5191EB63685E0404F8189D85963', 2, 'Departmental_Routing', 'KC', 'Departmental_Routing', null, 'Y', to_date('26-04-2009', 'dd-mm-yyyy'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('2416', '6881A5191EB73685E0404F8189D85963', 2, 'OSP Superuser', 'KC', 'OSP SuperUser to allow Blanket Approval for Proposal documents', null, 'Y', to_date('26-04-2009', 'dd-mm-yyyy'));
insert into KRIM_GRP_T (GRP_ID, OBJ_ID, VER_NBR, GRP_NM, NMSPC_CD, GRP_DESC, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('2425', '6881A5191EB83685E0404F8189D85963', 2, 'OSP', 'KC', 'OSP Office for Proposal Review and Approval', null, 'Y', to_date('26-04-2009', 'dd-mm-yyyy'));

insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1211', 1, '1BCF5FA61F7848D69AA4CDA5A44F8FFC', '1000004', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1212', 1, '8411B280321A49C09B2DFA19CE8B1088', '1000004', '10000000001', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1213', 1, 'C77EB0F2BA794D7D9FFAD8321082D958', '1000004', '10000000003', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1214', 1, '31719B64EE9F488EAA7516B78A9A5318', '1000004', '10000000004', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1215', 1, '5DF80A9E4FB44AEEB90249490CAA4A58', '1000004', '10000000005', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1216', 1, 'A749FE59374E47D6B502411DB4E2D3C7', '1000004', '10000000006', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1217', 1, '371544DFAAC24F77A40DEF29CAC852E3', '1000004', '10000000007', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1218', 1, '4DDBD0F198B44FE498B6D65B6DB0E93E', '1000004', '10000000008', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1219', 1, '19C6C59D364949469A463A9784D49DE2', '1000004', '10000000009', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1220', 1, '2FC1981933C74883AFB631FE4493AEFA', '1000004', '10000000010', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1221', 1, '776031FE69864307B7CC3ACFB1FFEC56', '1000004', '10000000011', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1222', 1, '68B8430676844D95870D06B88669AE45', '1000004', '10000000012', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1223', 1, 'F04E4C0FED0A437FAC9C573181EDF63A', '1000004', '10000000013', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1224', 1, 'D71FC0E96A1B498D8FF615E4BBED1D01', '1000004', '10000000014', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1225', 1, '3F817471392D4131B456D8A18B55B18A', '1000004', '10000000015', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1226', 1, '2B6C87742139432F8758BA8D3676365F', '1000004', '10000000016', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1227', 1, 'E35B133F91AF4861BA62804E77A7CB18', '1000004', '10000000017', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1228', 1, '9E38C4792B9848EF950DBEAC4DECF5DD', '1000004', '10000000018', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1229', 1, '2C9B05ADB25E48F4A5847CA37903434D', '1000004', '10000000019', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1230', 1, '10033962A2B342F289B5D9BE3080EFC7', '1000004', '10000000020', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1231', 1, '6641A2A8D4F84D0C84F52500DEA33C6E', '1000004', '10000000021', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1232', 1, 'EDF11CA8B94A478B8539736ACF0BF0ED', '1000004', '10000000022', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1233', 1, '72820266BF7B4B258E873F5A8E809F4B', '1000004', '10000000023', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1234', 1, '4BDB2B6F55C2452EA300FB03417211F1', '1000004', '10000000024', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1235', 1, '03BD2D8C3D654BBBA405ADC383AA778F', '1000004', '10000000025', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1236', 1, '134620BFAF86459CB85A3F80D1401066', '1000004', '10000000026', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1237', 1, '969674DE97994395BEBAF8A417518FE8', '1000004', '10000000027', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1238', 1, 'BBB30F6C41BE44A78BBC3E9413C66F29', '1000004', '10000000028', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1239', 1, 'BE8994BC98BA4C3D98A13A8A734E2D86', '1000004', '10000000029', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1240', 1, '1A44685C259B442984B3837E32801258', '1000004', '10000000030', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1241', 1, '7C678B1656184C52A559B73F323C5B09', '1000004', '10000000031', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1242', 1, '2F1562C3A2184912B82E099D3B473B9D', '1000004', '10000000032', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1243', 1, '14FC86F49ECA452FB369ED6AA16496C8', '1000004', '10000000033', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1244', 1, '6E64F241E8F04C52AE9CCE52230676AD', '1000004', '10000000034', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1245', 1, '69545B0310CC4EB39389AB05D4A9DF68', '1000004', '10000000035', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1246', 1, '400ECDA7383242A4961B0C6E980A11FD', '1000004', '10000000036', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1247', 1, '5DD93135506D436DAE3EE06B011FAA36', '1000004', '10000000037', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1248', 1, '49E9BA19EAD945BABBAB0BC38E34D01F', '1000004', '10000000038', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1249', 1, 'CEEB2E23F6964EA4A1BC2A0172CD7A59', '1000004', '10000000039', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1250', 1, '74034A3625394FAB8693EEB43EAAF1F2', '1000004', '10000000040', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1251', 1, '6A2F6F62167649A797C53F7E92CAD23C', '1000004', '10000000041', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1252', 1, '5D8AE780BFCA4DFE94B01FC6ADC06B91', '1000004', '10000000042', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1253', 1, 'BD7D890F93B9489DAFC309ED0D8051E6', '1000004', '10000000043', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1254', 1, '7BAF6144215745A995A5B5A5165BE186', '1000004', '10000000044', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1255', 1, 'BE4F2D315A34425583A81978D7F1D79D', '1000004', '10000000045', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1256', 1, '0CC8056FD7964C4C80D6D5E8FA4139C8', '1000004', '10000000046', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1257', 1, '826D8F0FF6D54FD0BFC873D188F9E308', '1000004', '10000000047', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1258', 1, 'AED722242E074483BBA6135C3249D5DA', '1000004', '10000000048', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1259', 1, '78733E8387024EBBA5C1C69D29F6CE25', '1000004', '10000000049', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1260', 1, '7B7F260EA8684AD681FC2A5DFD3BFAB9', '1000004', '10000000050', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1261', 1, 'DABAD2D150DB444CBD63568D92B46F9C', '1000004', '10000000051', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1262', 1, 'DF0028FACD304B80BA9A9C1EE042C14C', '1000004', '10000000052', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1263', 1, '286A3D2E7D824CC29E6928A3A18EE63C', '1000004', '10000000053', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1264', 1, '687236EFE2564BD6B57BF6196E28E9B9', '1000004', '10000000054', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1265', 1, 'B8B9C0DF0A86484B8338F15AAAE19602', '1000005', '10000000049', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1266', 1, '7DF12FFC6D7B40ECA822D8F1ACA196DB', '1000005', '10000000045', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1267', 1, 'B4BBCF81614640AA812951F17144E4CC', '1000006', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1268', 1, '752CA3A5968D4AA0BCA34C327E9DAC35', '1000006', '10000000001', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1269', 1, '72E1EA7EBF004B87A7C0607972ACBE03', '1000006', '10000000003', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1270', 1, '63F6DA84B5824AE1AB92D0CCDC76E9D8', '1000006', '10000000004', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1271', 1, 'B9580AC40C984B2B859F398C45DEC96E', '1000006', '10000000005', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1272', 1, '68E4593E9D624C35B4607C117899451B', '1000006', '10000000006', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1273', 1, '8B31B7DB76C940738FB5295EEA6EFB9A', '1000006', '10000000007', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1274', 1, '5EA586D382624EEB967F01528CEA3DB1', '1000006', '10000000008', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1275', 1, '40D8EEC9B4204838BE9F363F1FCCEEEB', '1000006', '10000000009', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1276', 1, '405F4006E5BE48E0B9550EB4CAEB6C74', '1000006', '10000000010', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1277', 1, '345E00F12CD548839EE9C3FC76AA34FC', '1000006', '10000000011', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1278', 1, '23A7F030BB37449996748C021DE06A08', '1000006', '10000000012', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1279', 1, '1E90381C7A1546DDBE3C9F4F9E707EC9', '1000006', '10000000013', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1280', 1, '61A69CE22A114178AEB30CE6CA636242', '1000006', '10000000014', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1281', 1, 'E9A0B2234DE34728A567C464C0014B6C', '1000006', '10000000015', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1282', 1, '8D360805F30447159210BE22EED82B3D', '1000006', '10000000016', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1283', 1, 'D928CB8DF4114B798ECCBD2B8DCCEBEA', '1000006', '10000000017', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1284', 1, '79FEB19E8794400AAC28DD34EE2622F0', '1000006', '10000000018', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1285', 1, 'BDF111C8A94B46FEAC403CFFE6F4A5D0', '1000006', '10000000019', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1286', 1, 'F1EFEE2793DE472C9D80986955EB4013', '1000006', '10000000020', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1287', 1, 'A987436051C9463F8E367D3FAA5B7573', '1000006', '10000000021', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1288', 1, '219A4E1178584D72903062ECC806E210', '1000006', '10000000022', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1289', 1, '16726DE073DE4AF8A35AFD9181B7C27E', '1000006', '10000000023', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1290', 1, '7E977E8BC3C4483F9CC5E352773BBEEA', '1000006', '10000000024', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1291', 1, 'C92A3BF0D00541C0A1DC785BB437817B', '1000006', '10000000025', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1292', 1, '51E7C085569F44C7A1001E47572BC045', '1000006', '10000000026', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1293', 1, '284F873A91BC4112851A38537D5DE838', '1000006', '10000000027', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1294', 1, 'C21D7B81233F4CE8ADDCB2E5262385A7', '1000006', '10000000028', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1295', 1, 'B920D3BD73FB45E2878749931B754670', '1000006', '10000000029', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1296', 1, '5760B8BB79C5427CB432067FCA96EA4C', '1000006', '10000000030', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1297', 1, '9BC22FE5E039417FBF672DA242297FDA', '1000006', '10000000031', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1298', 1, '9835348D981D4FEA8A9B794F76500967', '1000006', '10000000032', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1299', 1, '8418068607CB4EEA9D6F3EAB6AF491E3', '1000006', '10000000033', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1300', 1, '9E9C50F51634474D999D23925A32F011', '1000006', '10000000034', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1301', 1, '406880DF6B5E49C8ACA37162FB619644', '1000006', '10000000035', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1302', 1, 'CFF7BFAB224D4821865D3A5B21B3993C', '1000006', '10000000036', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1303', 1, 'F4F0A7D6793549BABD02BAB766C0E5D0', '1000006', '10000000037', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));

insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1304', 1, '5552D1B5EB8348AA9F0A2BF001BA9B32', '1000006', '10000000038', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1305', 1, 'A61B01CAB20A49A1B524A67FBFE90119', '1000006', '10000000039', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1306', 1, '7D748737D2A4429295C9D72C266DAD83', '1000006', '10000000040', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1307', 1, '0857FEABB848451E8340A158A2D78FA4', '1000006', '10000000041', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1308', 1, 'A3BA64CB0EEC437B944994AC6510ADAB', '1000006', '10000000042', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1309', 1, 'D661672FDBEA46998DEBFE5F9662AF77', '1000006', '10000000043', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1310', 1, 'ECA8CB1FB2CB433AA2F7FEE560515F1F', '1000006', '10000000044', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1311', 1, '8802D00B6BB64F72B527410576EFD5F8', '1000006', '10000000045', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1312', 1, 'BD35C0C39D084222AC1E970B7768293C', '1000006', '10000000046', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1313', 1, '43B3E009FB2943B7842E422264466181', '1000006', '10000000047', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1314', 1, '11B6DA95843C423788DC3A94B9DEE9CF', '1000006', '10000000048', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1315', 1, 'BC45CFB3F81448BCAC72E4609A7F05FF', '1000006', '10000000049', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1316', 1, '407366B900BD464B9914C025F4377BF8', '1000006', '10000000050', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1317', 1, '03092DA8AFF2467F99E169F2D1A5C81E', '1000006', '10000000051', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1318', 1, '5B97B9EA37C44E24BC7E5AE0B378BDDF', '1000006', '10000000052', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1319', 1, '1FB370B001764932A3F129474A7174E2', '1000006', '10000000053', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1320', 1, 'CF1A843EF37B4F1CA9C697E3FDFE2A9F', '1000006', '10000000054', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1321', 1, 'DB01B0564D0142FB9A6D6731C8444FAF', '1000007', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1322', 1, '6E6D6DB691674A10BDA886359F480EC7', '1000007', '10000000001', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1323', 1, 'EE7FA87A0EBE48BEB9A142BDC67BE061', '1000007', '10000000003', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1324', 1, '8F3E1F31EED5464490629DFF23B6E590', '1000007', '10000000004', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1325', 1, 'F3E62641C8D14C4985EE249DD690FEDF', '1000007', '10000000005', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1326', 1, 'C0F301A101EF497ABFBEE61A2782106F', '1000007', '10000000006', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1327', 1, '03804CA0D89E4093B11531B5338CA084', '1000007', '10000000007', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1328', 1, 'C30C4F37613247AF81533CD78EB35649', '1000007', '10000000008', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1329', 1, '3E7E60CDFAF34D76929203042B23B1AC', '1000007', '10000000009', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1330', 1, '65E2AEE1FDB7475C86F9E885AF6B9579', '1000007', '10000000010', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1331', 1, '62D02EA7AD5D41F6ACD8A98E306EA9B8', '1000007', '10000000011', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1332', 1, '68EAA5565C394FA09BFF1BE0C63C83ED', '1000007', '10000000012', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1333', 1, '4965FF57CD0B4A449921FAEAAA366D3E', '1000007', '10000000013', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1334', 1, '6F13C9FF9D1147CA83103B558B9EE257', '1000007', '10000000014', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1335', 1, 'E5F14DF85EC647D395D6A24EC45BB1C9', '1000007', '10000000015', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1336', 1, '85816057D4B94BAFBD633202AC8332FD', '1000007', '10000000016', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1337', 1, 'FAB30146876F40A7B8101CEDFA071C15', '1000007', '10000000017', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1338', 1, '93E7C763DC444938B8781933CC2ED2F6', '1000007', '10000000018', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1339', 1, '5C90BE3E023645D38C22F512F8A248B4', '1000007', '10000000019', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1340', 1, 'CB25536BD08E4381BE075BE76E27D6DA', '1000007', '10000000020', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1341', 1, '385D13785470450A83CD60C266A68D79', '1000007', '10000000021', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1342', 1, 'EF8B7931A70A4958B157AF78C05603F0', '1000007', '10000000022', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1343', 1, '5ABA182BAA46493DB2B8AC49C2E5B48E', '1000007', '10000000023', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1344', 1, '59EA2503B672461CB9125EDDA5EAD51D', '1000007', '10000000024', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1345', 1, '239FBAC4D303476E963BD316DD79ECA9', '1000007', '10000000025', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1346', 1, '3D84F5B23E81404FA8DE78EA6D49AE4A', '1000007', '10000000026', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1347', 1, '86AF805F321746C5AFC293AA8146DCB6', '1000007', '10000000027', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1348', 1, '5FB3728330F543A1B6889BB7A601379E', '1000007', '10000000028', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1349', 1, '989EA0EE1FFF4285BAF571F3469FC2ED', '1000007', '10000000029', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1350', 1, '4DBD87534C4845C4BD68517A0882991C', '1000007', '10000000030', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1351', 1, '361D37CAB61843C7991671DCE770E3B2', '1000007', '10000000031', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1352', 1, '5C928EF5AE984610A5C9585590AACB5A', '1000007', '10000000032', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1353', 1, 'CB29655BCD4C4A2BBC4A8D4C94D01765', '1000007', '10000000033', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1354', 1, 'FFC1D71651194F1B96E470DEC73FBA66', '1000007', '10000000034', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1355', 1, '927FED17334B42C3B587118F636F32B6', '1000007', '10000000035', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1356', 1, '8C117C691C584BD9B206A5803EFA5664', '1000007', '10000000036', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1357', 1, '99B12A4C578C443F90D249A97C24F1F9', '1000007', '10000000037', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1358', 1, '5635054C8CB648AFB9439A94EC16C31F', '1000007', '10000000038', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1359', 1, '7433A46480654649A85EB844E33BD738', '1000007', '10000000039', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1360', 1, '343BD1829B0348D4A107CBC0072F8F1B', '1000007', '10000000040', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1361', 1, 'BF0512E56D2F4FE5BFDF4DDC54073CD0', '1000007', '10000000041', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1362', 1, 'E0309DCAA0304E3EB435EB274BF0DF22', '1000007', '10000000042', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1363', 1, '272EFB07E3194F46BEB5E000C636E481', '1000007', '10000000043', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1364', 1, 'F44DEDFE8B014C20987DA37503DED66A', '1000007', '10000000044', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1365', 1, 'FA48D062B8C740D6929B50CDE73B93E0', '1000007', '10000000045', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1366', 1, '2289DC06A3D6462FBBAD24EF4CB0F13D', '1000007', '10000000046', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1367', 1, '367EF06D303D4AC78EE5A432D24CB3DE', '1000007', '10000000047', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1368', 1, 'FE7B584FEDF947AEA74E4F8B4C9200E7', '1000007', '10000000048', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1369', 1, '82DB2409E04448F4BC8DEEF5A4287544', '1000007', '10000000049', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));

insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1370', 1, 'DA4449ACBC414FE5899C86B10807FB99', '1000007', '10000000050', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1371', 1, '5D155F61A7D54478964FAF886FCE67CF', '1000007', '10000000051', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1372', 1, 'FDDB474AD8E4453382EFE7A1B877629B', '1000007', '10000000052', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1373', 1, '77B90BA4641B4B72B948E361BB195B56', '1000007', '10000000053', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_GRP_MBR_T (GRP_MBR_ID, VER_NBR, OBJ_ID, GRP_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1374', 1, '1527908B39204594B708A89F7D8EF30E', '1000007', '10000000054', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));

insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('1000', '3533DA8F95EB412A86ECB75DE80ECE5F', 1, 'KC-IDM', 'Edit Document Section', 'Edit Document Section', '1017', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('1001', '7FF26020978E4BC7A804D3CDA711E212', 1, 'KC-IDM', 'View Document Section', 'View Document Section', '1017', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('1002', '0C3F90DE8DAA450DB64576F620FAAFEC', 1, 'KC-IDM', 'Perform Document Action', 'Perform Document Action', '1017', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('1003', '2279C69C52604084AA1A1C15A6FB4C89', 1, 'KC-IDM', 'Modify All Documents', 'Modify All Documents', '3', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('1004', '38EDB170B6A845FAB5A71E5C16C8BEEE', 1, 'KC-IDM', 'View All Documents', 'View All Documents', '3', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('1005', 'ADB0C5DA77A44DDC8381C9ECAF1D0564', 1, 'KC-IDM', 'Perform Any Document Action', 'Perform Any Document Action', '3', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('1006', 'EC53C11D5CC5442C9008FA738B73514E', 1, 'KC-IDM', 'Route All Documents', 'Route All Documents', '3', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('1028', '77BC20590A4D319DE0404F8189D80DB4', 1, 'KC-IDM', 'Question Permission', 'Modify/View Question', '3', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('1', '5ADF18B6D4857954E0404F8189D85002', 1, 'KUALI', 'Default', null, '1', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('10', '5ADF18B6D4BF7954E0404F8189D85002', 1, 'KR-SYS', 'Initiate Document', null, '3', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('14', '5ADF18B6D4CA7954E0404F8189D85002', 1, 'KR-WKFLW', 'Cancel Document', null, '8', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('15', '5ADF18B6D4CB7954E0404F8189D85002', 1, 'KR-WKFLW', 'Save Document', null, '8', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('16', '5ADF18B6D4CC7954E0404F8189D85002', 1, 'KR-NS', 'Edit Document', null, '8', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('2', '5ADF18B6D4AF7954E0404F8189D85002', 1, 'KR-NS', 'Copy Document', null, '3', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('23', '5ADF18B6D4DA7954E0404F8189D85002', 1, 'KR-NS', 'Look Up Records', null, '10', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('24', '5ADF18B6D4DB7954E0404F8189D85002', 1, 'KR-NS', 'Inquire Into Records', null, '10', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('25', '5ADF18B6D4DF7954E0404F8189D85002', 1, 'KR-NS', 'View Inquiry or Maintenance Document Field', null, '11', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('26', '5ADF18B6D4E07954E0404F8189D85002', 1, 'KR-NS', 'Modify Maintenance Document Field', null, '11', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('27', '5ADF18B6D4E17954E0404F8189D85002', 1, 'KR-NS', 'Full Unmask Field', null, '11', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('28', '5ADF18B6D4E27954E0404F8189D85002', 1, 'KR-NS', 'Partial Unmask Field', null, '11', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('29', '5ADF18B6D4E67954E0404F8189D85002', 1, 'KR-NS', 'Use Screen', null, '12', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('3', '5ADF18B6D4B07954E0404F8189D85002', 1, 'KR-WKFLW', 'Administer Routing for Document', null, '3', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('30', '5ADF18B6D4E97954E0404F8189D85002', 1, 'KR-NS', 'Perform Custom Maintenance Document Function', null, '13', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('31', '5ADF18B6D4EC7954E0404F8189D85002', 1, 'KR-NS', 'Use Transactional Document', null, '14', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('32', '5ADF18B6D4F07954E0404F8189D85002', 1, 'KR-NS', 'Modify Batch Job', null, '15', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('33', '5ADF18B6D4F17954E0404F8189D85002', 1, 'KR-NS', 'Upload Batch Input File(s)', null, '15', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('34', '5ADF18B6D4F67954E0404F8189D85002', 1, 'KR-NS', 'Maintain System Parameter', null, '16', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('35', '5ADF18B6D4FC7954E0404F8189D85002', 1, 'KR-IDM', 'Assign Role', null, '18', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('36', '5ADF18B6D5007954E0404F8189D85002', 1, 'KR-IDM', 'Grant Permission', null, '19', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('37', '5ADF18B6D5047954E0404F8189D85002', 1, 'KR-IDM', 'Grant Responsibility', null, '20', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('38', '5ADF18B6D5087954E0404F8189D85002', 1, 'KR-IDM', 'Populate Group', null, '21', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('39', '5ADF18B6D50B7954E0404F8189D85002', 1, 'KR-IDM', 'Modify Entity', null, '22', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('4', '5ADF18B6D4B17954E0404F8189D85002', 1, 'KR-WKFLW', 'Blanket Approve Document', null, '3', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('40', '5ADF18B6D4AE7954E0404F8189D85002', 1, 'KR-NS', 'Open Document', null, '3', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('42', '603B735FA6C4FE21E0404F8189D8083B', 1, 'KR-NS', 'Create / Maintain Record(s)', null, '56', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('43', '603B735FA6C0FE21E0404F8189D8083B', 1, 'KR-NS', 'View Inquiry or Maintenance Document Section', null, '57', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('44', '603B735FA6C1FE21E0404F8189D8083B', 1, 'KR-NS', 'Modify Maintenance Document Section', null, '57', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('45', '606763510FC096D3E0404F8189D857A2', 1, 'KR-NS', 'Add Note / Attachment', null, '9', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('46', '606763510FC196D3E0404F8189D857A2', 1, 'KR-NS', 'View Note / Attachment', null, '9', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('47', '606763510FC296D3E0404F8189D857A2', 1, 'KR-NS', 'Delete Note / Attachment', null, '59', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('5', '5ADF18B6D4B27954E0404F8189D85002', 1, 'KR-WKFLW', 'Route Document', null, '3', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('70', '607A7155BC6D864EE0404F8189D85925', 1, 'KR-WKFLW', 'View Other Action List', null, '1', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('71', '607A7155BC70864EE0404F8189D85925', 1, 'KR-WKFLW', 'Unrestricted Document Search', null, '1', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('8', '5ADF18B6D4B77954E0404F8189D85002', 1, 'KR-NS', 'Take Requested Action', null, '4', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('9', '5ADF18B6D4BB7954E0404F8189D85002', 1, 'KR-WKFLW', 'Ad Hoc Review Document', null, '5', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('49', '662384B381B867A1E0404F8189D868A6', 1, 'KR-NS', 'Send Ad Hoc Request', null, '5', 'Y');
insert into KRIM_PERM_TMPL_T (PERM_TMPL_ID, OBJ_ID, VER_NBR, NMSPC_CD, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('1029', '77BC20590A4D319DE0404F81891029', 1, 'KC-IDM', 'Questionnaire Permission', 'Modify/View Questionnaire', '3', 'Y');
 


insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1000', '60B1DE23785042809EF2E45C0F6710C1', 1, '10', 'KRA-PD', 'Create ProposalDevelopmentDocument', 'Create Proposal Development Document', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1001', 'ABC3CB6BC523429CB8C02FA1F571B279', 1, '10', 'KC-PROTOCOL', 'Create ProtocolDocument', 'Create Protocol Document', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1002', 'F3DA175660FA4E02934027BDEFB75664', 1, '10', 'KC-PROTOCOL', 'Create CommitteeDocument', 'Add new committees in a unit', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1003', '3C55B6D5720F48549B7D5EFAFECD0545', 1, '10', 'KC-AWARD', 'Create Award', 'Create Award', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1004', 'C45B2EFF270946A98CFCC8C1BFA7053A', 1, '10', 'KC-AWARD', 'Create Award Budget', 'Create Award Budget', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1005', 'E7B2A128FD0147A8B901671D3BBF06C1', 1, '5', 'KRA-PD', 'Submit ProposalDevelopmentDocument', 'Submit a Proposal for approval', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1006', 'EFF02F1A0618433C9AB3A994597EB3B6', 1, '5', 'KC-PROTOCOL', 'Submit Protocol', 'Submit a Protocol to IRB for review', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1007', 'D9DDA28C46984E50835FC5DBEC26E8DF', 1, '5', 'KC-AWARD', 'Submit AwardBudget', 'Submit Award Budget', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1008', 'CD6871083A63499D818F4401B65A73D4', 1, '1000', 'KRA-PD', 'Modify Proposal Development Document', 'Modify Proposal Development Document', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1009', '165249BF2ECC473B99218933217481E3', 1, '1000', 'KRA-PD', 'Modify Budget', 'Create/Modify Proposal Budget', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1010', '600E18B27AE84BC88E6DB3E0D9AFE719', 1, '1000', 'KRA-PD', 'Modify Narrative', 'Create/Modify Proposal Narrative', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1011', '7809630ACF4242439D2ADA20817FF1C0', 1, '1000', 'KRA-PD', 'Modify ProposalPermissions', 'Assign Users to Proposal Roles', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1012', '39B7AF17ED134C33BA2FE5D2F7AB777F', 1, '1000', 'KC-PROTOCOL', 'Modify Protocol', 'Modify Protocol Document', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1013', '788501963A434072A1CCEEB8C71523BC', 1, '1000', 'KC-PROTOCOL', 'Modify ProtocolPermissions', 'Assign Users to Protocol Roles', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1014', '43EA3AD46BE5418381B63984AF10A8FF', 1, '1000', 'KC-PROTOCOL', 'Modify Committee', 'Modify existing committees in a unit', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1015', '0A2BEAD1E238428F8F9B6E5A98BDC9F8', 1, '1000', 'KC-PROTOCOL', 'Modify Schedule', 'Modify schedule details for committees in a unit', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1016', '09DE695B64E4468A86796E17A136D05C', 1, '1000', 'KC-PROTOCOL', 'Maintain Memberships', 'Maintain membership details in committees in a unit', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1017', '23F8140F39684F45B945705FEE8B46EF', 1, '1000', 'KC-PROTOCOL', 'Maintain Minutes', 'Add/modify/delete minute entries in any schedule for committees in a unit', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1018', 'A55F9A096F464B779440EFBA57C27EE6', 1, '1000', 'KC-AWARD', 'Modify Award', 'Modify Award', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1019', '991D7296D0CB463CB6C6962238BD7D70', 1, '1000', 'KC-AWARD', 'Modify AwardBudget', 'Modify Award Budget', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1020', '277D6AF3EC584C639465348F5D6FDCDC', 1, '1000', 'KC-AWARD', 'Maintain Award Documents', 'Maintain Award Documents', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1021', '03820D14B51649B286198AF4441ECD12', 1, '1001', 'KRA-PD', 'View Proposal', 'View Proposal Development Document', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1022', '8AD5C6DE27584C1A8AD91D2BE902027F', 1, '1001', 'KRA-PD', 'View Budget', 'View Proposal Budget', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1023', '0F3D3FBD7F524B778563767790F53305', 1, '1001', 'KRA-PD', 'View Narratives', 'View Proposal Narrative', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1024', '4B3B7499DFC64C218D5267CC510BCB32', 1, '1001', 'KC-PROTOCOL', 'View Protocol', 'View Protocol Document', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1025', '2B1B7E147B31401A9BC163E7DD15BA79', 1, '1001', 'KC-PROTOCOL', 'View Minutes', 'View Minutes', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1026', '5C78BDBCA8A74A61AE1249852B3F56C5', 1, '1001', 'KC-PROTOCOL', 'View Committee', 'View all committees in a unit', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1027', '525BFFAEBBB24DE1B1B485FD424DF5A8', 1, '1001', 'KC-PROTOCOL', 'View Schedule', 'View schedule details of committees in a unit', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1028', '2A02FA9A31DD43D0AFB5CAF0A5FCA2A4', 1, '1001', 'KC-PROTOCOL', 'View Agenda', 'View Agenda', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1029', 'F0EBF27FF40F42F398408568FC9991E6', 1, '1001', 'KC-AWARD', 'View Award', 'View Award', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1030', '4551B33847244D6A870996F59EE82B34', 1, '1001', 'KC-AWARD', 'View AwardBudget', 'View Award Budget', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1031', '78AE21DFC8C44CF3AF5BAEE6647482F3', 1, '1001', 'KC-AWARD', 'View Award Documents', 'View Award Documents', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1032', '4E2E1E9AB9714C249EDF6E0B9D9AD7E9', 1, '1002', 'KRA-PD', 'Alter Proposal Data', 'Change proposal master data once the proposal is locked', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1033', '96637C2DF1E64F42AD5002087D6B896E', 1, '1002', 'KRA-PD', 'Print Proposal', 'Print proposal on a sponsor specific path', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1034', 'CAEE635F4064440FB7741523DB8D2FFD', 1, '1002', 'KRA-PD', 'Certify', 'Certify', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1035', 'AC0582AC3C0545F6BB03B7EEF6AEF2FA', 1, '1002', 'KRA-PD', 'Submit to Sponsor', 'Submit a Proposal to Grants.gov', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1036', 'DA089E0574DE4C0CA1D6BC791546DA87', 1, '1002', 'KRA-PD', 'Add Proposal Viewer', 'Assign User to Proposal Viewer Role', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1037', '23A7B61D68A64B9ABA208A2B85A247B5', 1, '1002', 'KC-PROTOCOL', 'Create Ammendment', 'Create a new ammendment for a protocol', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1038', 'BEBECCDD06B8446F9FE49D6196EBD0C1', 1, '1002', 'KC-PROTOCOL', 'Create Renewal', 'Create a new renewal for a protocol', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1039', 'BB6A63D03CDA46DD8FF4DC9E687D7808', 1, '1002', 'KC-PROTOCOL', 'Administrative Correction', 'Perform Administrative Corrections on Protocols', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1040', '6EC614EC6D764B41A0B15F88E9F16767', 1, '1002', 'KC-PROTOCOL', 'Maintain Protocol Submissions', 'Modify Protocol Submission details', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1041', 'C5C5EEF9EE0F478DA33E1C472701CA28', 1, '1002', 'KC-PROTOCOL', 'Add Notes', 'Add Protocol Notes', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1042', 'EC57241C3177459789CF65551B76C69E', 1, '1002', 'KC-PROTOCOL', 'View Restricted Notes', 'View Restricted Notes in Protocols', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1043', '7515E05EEB15412D86A464018243BD56', 1, '1002', 'KC-PROTOCOL', 'Generate Agenda', 'Generate Agenda', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1044', '0ED00C33EDDA45E8AD217CF1F14EFF3A', 1, '1002', 'KC-PROTOCOL', 'Generate Minutes', 'Generate Minutes', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1045', '5B568E09028A4CDD9EA6BDCE4F551000', 1, '1002', 'KC-PROTOCOL', 'Generate Minutes', 'Generate Schedule', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1046', 'F800A75E0D8742CDA0C32B63A29EFEC8', 1, '1002', 'KC-PROTOCOL', 'View Member Details', 'View membership details for a member in a committee', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1047', 'F42B3D3ECBF4490290FB57BE02FB2928', 1, '1002', 'KC-AWARD', 'Post AwardBudget', 'Post Award Budget', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1048', '7F55E8A89B374A5C879E4FC418EF147E', 1, '1003', 'KC-PROTOCOL', 'Modify Any Protocol', 'Modify Any Protocol Document', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1049', 'F3E58EFC75F640E29093A8BDFD1049D4', 1, '1004', 'KC-AWARD', 'View Any Proposal', 'View Any Proposal', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1050', '6080E5F11BC7417693D1A198308B1035', 1, '1005', 'KC-PROTOCOL', 'Perform IRB Actions on a Protocol', 'Perform any IRB action on a protocol submitted to a committee', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1051', 'EA35F312EFCE43EFA5651A9C2CC2CC1C', 1, '1006', 'KC-AWARD', 'Submit Any Award Budget', 'Submit Any Award Budget', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1199', '77BC20590A4E319DE0404F8189D80DB4', 1, '1028', 'KC-QUESTIONNAIRE', 'Modify Question', 'Modify Question', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD,NM, DESC_TXT, ACTV_IND)
values ('1200', '77BC20590A51319DE0404F8189D80DB4', 1, '1028', 'KC-QUESTIONNAIRE', 'View Question', 'View Question', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('140', '5B4F09744944EF33E0404F8189D84F24', 1, '29', 'KR-WKFLW', 'Use Screen', 'Allows users to access the Document Operation screen.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('141', '5B4F09744945EF33E0404F8189D84F24', 1, '29', 'KR-BUS', 'Use Screen', 'Allows users to access the Java Security Management screen.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('142', '5B4F09744946EF33E0404F8189D84F24', 1, '29', 'KR-BUS', 'Use Screen', 'Allows users to access the Message Queue screen.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('143', '5B4F09744947EF33E0404F8189D84F24', 1, '29', 'KR-BUS', 'Use Screen', 'Allows users to access the Service Registry screen.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('144', '5B4F09744948EF33E0404F8189D84F24', 1, '29', 'KR-BUS', 'Use Screen', 'Allows users to access the Thread Pool screen.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('145', '5B4F09744949EF33E0404F8189D84F24', 1, '29', 'KR-BUS', 'Use Screen', 'Allows users to access the Quartz Queue screen.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('146', '5B4F0974494AEF33E0404F8189D84F24', 1, '9', 'KR-SYS', 'Ad Hoc Review Document', 'Allows a user to receive ad hoc requests for RICE Documents.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('147', '5B4F0974494BEF33E0404F8189D84F24', 1, '3', 'KR-SYS', 'Administer Routing for Document', 'Allows users to open RICE Documents via the Super search option in Document Search and take Administrative workflow actions on them (such as approving the document, approving individual requests, or sending the document to a specified route node).', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('148', '5B4F0974494CEF33E0404F8189D84F24', 1, '4', 'KR-SYS', 'Blanket Approve Document', 'Allows access to the Blanket Approval button on RICE Documents.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('149', '5B4F0974494DEF33E0404F8189D84F24', 1, '10', 'KR-SYS', 'Initiate Document', 'Authorizes the initiation of RICE Documents.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('150', '5B4F0974494EEF33E0404F8189D84F24', 1, '35', 'KR-SYS', 'Assign Role', 'Authorizes users to modify the information on the Assignees Tab of the Role Document and the Roles section of the Membership Tab on the Person Document for Roles with a Module Code beginning with KR.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('151', '5B4F0974494FEF33E0404F8189D84F24', 1, '36', 'KR-SYS', 'Grant Permission', 'Authorizes users to modify the information on the Permissions tab of the Role Document for roles with a module code beginning with KR.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('152', '5B4F09744950EF33E0404F8189D84F24', 1, '37', 'KR-SYS', 'Grant Responsibility', 'Authorizes users to modify the information on the Responsibility tab of the Role Document for roles with a Module Code that begins with KR.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('153', '5B4F09744951EF33E0404F8189D84F24', 1, '39', 'KR-SYS', 'Modify Entity', null, 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('154', '5B4F09744952EF33E0404F8189D84F24', 1, '39', 'KR-SYS', 'Modify Entity', null, 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('155', '5B4F09744953EF33E0404F8189D84F24', 1, '38', 'KR-SYS', 'Populate Group', 'Authorizes users to modify the information on the Assignees Tab of the Group Document and the Group section of the Membership Tab on the Person Document for groups with namespaces beginning with KR.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('156', '5B4F09744954EF33E0404F8189D84F24', 1, '2', 'KR-SYS', 'Copy Document', 'Authorizes users to copy RICE Documents.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('161', '5B4F09744959EF33E0404F8189D84F24', 1, '24', 'KR-SYS', 'Inquire Into Records', 'Allows users to access Kuali RICE inquiries.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('162', '5B4F0974495AEF33E0404F8189D84F24', 1, '23', 'KR-SYS', 'Look Up Records', 'Allow users to access Kuali RICE lookups.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('163', '5B4F0974495BEF33E0404F8189D84F24', 1, '34', 'KR-SYS', 'Maintain System Parameter', 'Authorizes to initiate and edit the Parameter document for parameters with a module code beginning with KR.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('164', '5B4F0974495CEF33E0404F8189D84F24', 1, '32', 'KR-SYS', 'Modify Batch Job', 'Allows users to access and run Batch Jobs associated with KR modules via the Schedule link.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('165', '5B4F0974495DEF33E0404F8189D84F24', 1, '40', 'KR-SYS', 'Open Document', 'Authorizes users to open RICE Documents.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('166', '5B4F0974495EEF33E0404F8189D84F24', 1, '29', 'KR-SYS', 'Use Screen', 'Allows users to access all RICE screens.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('167', '5B4F0974495FEF33E0404F8189D84F24', 1, '14', 'KUALI', 'Cancel Document', 'Authorizes users to cancel a document prior to it being submitted for routing.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('168', '5B4F09744960EF33E0404F8189D84F24', 1, '5', 'KUALI', 'Route Document', 'Authorizes users to submit a document for routing.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('290', '5B4F09744961EF33E0404F8189D84F24', 1, '15', 'KR-SYS', 'Save Document', 'Users who can save RICE documents', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('170', '5B4F09744962EF33E0404F8189D84F24', 1, '8', 'KUALI', 'Take Requested Action', 'Authorizes users to take the Approve action on documents routed to them.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('174', '5B4F09744966EF33E0404F8189D84F24', 1, '1', 'KUALI', 'Log In', 'Authorizes users to login to the Kuali portal.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('180', '5B4F0974496CEF33E0404F8189D84F24', 1, '16', 'KUALI', 'Edit Document', 'Allows users to edit Kuali documents that are in ENROUTE status.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('181', '5B4F0974496DEF33E0404F8189D84F24', 1, '16', 'KUALI', 'Edit Document', 'Allows users to edit Kuali documents that are in ENROUTE status.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('183', '5B4F0974496FEF33E0404F8189D84F24', 1, '27', 'KR-SYS', 'Full Unmask Field', 'Authorizes users to view the entire Tax Identification Number on the Payee ACH document and Inquiry.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('259', '606763510FC396D3E0404F8189D857A2', 1, '45', 'KUALI', 'Add Note / Attachment', 'Users who can add notes and attachments to any document answering to the Kuali Document parent document type.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('261', '606763510FC696D3E0404F8189D857A2', 1, '46', 'KUALI', 'View Note / Attachment', 'Authorizes users to view notes and attachments on documents answering to the KualiDocument parent document type.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('264', '606763510FCD96D3E0404F8189D857A2', 1, '47', 'KR-SYS', 'Delete Note / Attachment', 'Authorizes users to delete notes and attachments created by any user on documents answering to the RICE Document parent document type.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('289', '611BE30E404E5818E0404F8189D801C2', 1, '1', 'KR-NS', 'Administer Pessimistic Locking', 'Administer Pessimistic Locking', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('701', '04C7706012554535AF8DC48DC6CC331C', 1, '23', 'KR-WKFLW', 'Look Up Records', 'Allow users to access the Rule Template lookup.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('702', '471FF4B19A4648D4B84194530AE22158', 1, '23', 'KR-WKFLW', 'Look Up Records', 'Allow users to access the Stylesheet lookup.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('714', '77C20443ECBF4F9CA4E0D1E03CADCF15', 1, '23', 'KR-NS', 'Look Up Records', 'Allow users to access the Pessimistic Lock lookup.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('719', '45F0E8E1B9784756A3C0582980912991', 1, '23', 'KR-NS', 'Look Up Records', 'Allow users to access the Parameter Component lookup.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('721', 'B1BD57EE64274E62AC9425C7FF185A44', 1, '23', 'KR-NS', 'Look Up Records', 'Allow users to access the Parameter Type lookup.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('802', '606763510FD496D3E0404F8189D857A2', 1, '24', 'KR-WKFLW', 'Inquire Into Records', 'Allow users to access the Stylesheet inquiry.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('803', '606763510FD796D3E0404F8189D857A2', 1, '24', 'KR-WKFLW', 'Inquire Into Records', 'Allow users to access the eDocLite inquiry.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('814', '606763510FE196D3E0404F8189D857A2', 1, '24', 'KR-NS', 'Inquire Into Records', 'Allow users to access the Pessimistic Lock inquiry.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('820', '606763510FE796D3E0404F8189D857A2', 1, '24', 'KR-NS', 'Inquire Into Records', 'Allow users to access the Namespace inquiry.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('821', '606763510FEA96D3E0404F8189D857A2', 1, '24', 'KR-NS', 'Inquire Into Records', 'Allow users to access the Parameter Type inquiry.', 'Y');

insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('265', '606763510FF296D3E0404F8189D857A2', 1, '29', 'KR-WKFLW', 'Use Screen', 'Allows users to access the XML Ingester screen.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('830', '607A7155BC6E864EE0404F8189D85925', 1, '70', 'KR-WKFLW', 'View Other Action List', null, 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('831', '607A7155BC71864EE0404F8189D85925', 1, '71', 'KR-WKFLW', 'Unrestricted Document Search', null, 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('307', '638DD46953F9BCD5E0404F8189D86240', 1, '1', 'KR-IDM', 'Modify Entity', 'Users who can modify entity records in Kuali Identity Management.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('332', '662384B381B967A1E0404F8189D868A6', 1, '49', 'KR-SYS', 'Send Ad Hoc Request', 'Authorizes users to send FYI ad hoc requests for Kuali Documents', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('333', '662384B381BD67A1E0404F8189D868A6', 1, '49', 'KR-SYS', 'Send Ad Hoc Request', 'Authorizes users to send Acknowledge ad hoc requests for Kuali Documents', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('334', '662384B381C167A1E0404F8189D868A6', 1, '49', 'KR-SYS', 'Send Ad Hoc Request', 'Authorizes users to send Approve ad hoc requests for Kuali Documents', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('836', '5B4F09744953EF33E0404F8189D84F28', 1, '37', 'KR-SYS', 'Grant Responsibility', 'Authorizes users to modify the information on the Responsibility tab of the Role Document for roles with the KUALI namespace.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('378', '5B4F09744953EF33E0404F8189D84F29', 1, '1', 'KR-IDM', 'Override Entity Privacy Preferences', 'Allows a user to override entity privacy preferences', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('306', '6314CC58CF58B7B5E0404F8189D84439', 1, '27', 'KR-SYS', 'Full Unmask Field', 'Authorizes users to view the entire Tax Identification Number on the Person document and inquiry.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('172', '5B4F09744964EF33E0404F8189D84F24', 1, '8', 'KUALI', 'Take Requested Action', 'Authorizes users to take the FYI action on documents routed to them.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('173', '5B4F09744965EF33E0404F8189D84F24', 1, '8', 'KUALI', 'Take Requested Action', 'Authorizes users to take the Acknowledge action on documents routed to them.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('703', 'E6875070DC5B4FD59193F7445C33E7AB', 1, '23', 'KR-WKFLW', 'Look Up Records', 'Allow users to access the eDocLite lookup.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('707', '28CE0127B8A14AB4BFD39920C5398A69', 1, '23', 'KR-WKFLW', 'Look Up Records', 'Allow users to access the Rule Attribute lookup.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('720', 'FFF2C6639C6041F1B148AA9901C8A1F7', 1, '23', 'KR-NS', 'Look Up Records', 'Allow users to access the Namespace lookup.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('801', '606763510FD196D3E0404F8189D857A2', 1, '24', 'KR-WKFLW', 'Inquire Into Records', 'Allow users to access the Rule Template inquiry.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('807', '606763510FDA96D3E0404F8189D857A2', 1, '24', 'KR-WKFLW', 'Inquire Into Records', 'Allow users to access the Rule Attribute inquiry.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('819', '606763510FE496D3E0404F8189D857A2', 1, '24', 'KR-NS', 'Inquire Into Records', 'Allow users to access the Parameter Component inquiry.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('833', '5B4F09744953EF33E0404F8189D84F25', 1, '38', 'KR-SYS', 'Populate Group', 'Authorizes users to modify the information on the Assignees Tab of the Group Document and the Group section of the Membership Tab on the Person Document for groups with the KUALI namespace.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('834', '5B4F09744953EF33E0404F8189D84F26', 1, '35', 'KR-SYS', 'Assign Role', 'Authorizes users to modify the information on the Assignees Tab of the Role Document and the Roles section of the Membership Tab on the Person Document for Roles with the KUALI namespace.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('835', '5B4F09744953EF33E0404F8189D84F27', 1, '36', 'KR-SYS', 'Grant Permission', 'Authorizes users to modify the information on the Permissions tab of the Role Document for roles with the KUALI namespace.', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD, NM, DESC_TXT, ACTV_IND)
values ('1201', '77BC20590A4E319DE0404F8189D81201', 1, '1029', 'KC-QUESTIONNAIRE', 'Modify Questionnaire', 'Modify Questionnaire', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD,NM, DESC_TXT, ACTV_IND)
values ('1202', '77BC20590A51319DE0404F8189D81202', 1, '1029', 'KC-QUESTIONNAIRE', 'View Questionnaire', 'View Questionnaire', 'Y');

insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD,NM, DESC_TXT, ACTV_IND)
values ('1203', '77BC20590A51319DE0404F8189D81203', 1, '10', 'KC-IP', 'Create Proposal Log', 'Initiate a new Proposal Log', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD,NM, DESC_TXT, ACTV_IND)
values ('1204', '77BC20590A51319DE0404F8189D81204', 1, '16', 'KC-IP', 'Edit Proposal Log', 'Edit a Proposal Log', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD,NM, DESC_TXT, ACTV_IND)
values ('1205', '77BC20590A51319DE0404F8189D81205', 1, '15', 'KC-IP', 'Save Proposal Log', 'Save a Proposal Log', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD,NM, DESC_TXT, ACTV_IND)
values ('1206', '77BC20590A51319DE0404F8189D81206', 1, '5', 'KC-IP', 'Submit Proposal Log', 'Submit a Proposal Log', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD,NM, DESC_TXT, ACTV_IND)
values ('1207', '77BC20590A51319DE0404F8189D81207', 1, '40', 'KC-IP', 'Open Proposal Log', 'Open a Proposal Log', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD,NM, DESC_TXT, ACTV_IND)
values ('1208', '77BC20590A51319DE0404F8189D81208', 1, '14', 'KC-IP', 'Cancel Proposal Log', 'Cancel a Proposal Log', 'Y');

insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD,NM, DESC_TXT, ACTV_IND)
values ('1209', '77BC20590A51319DE0404F8189D81209', 1, '10', 'KC-IP', 'Create Institutional Proposal', 'Initiate a new Institutional Proposal', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD,NM, DESC_TXT, ACTV_IND)
values ('1210', '77BC20590A51319DE0404F8189D81210', 1, '16', 'KC-IP', 'Edit Institutional Proposal', 'Edit an Institutional Proposal', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD,NM, DESC_TXT, ACTV_IND)
values ('1211', '77BC20590A51319DE0404F8189D81211', 1, '15', 'KC-IP', 'Save Institutional Proposal', 'Save an Institutional Proposal', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD,NM, DESC_TXT, ACTV_IND)
values ('1212', '77BC20590A51319DE0404F8189D81212', 1, '5', 'KC-IP', 'Submit Institutional Proposal', 'Submit an Institutional Proposal', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD,NM, DESC_TXT, ACTV_IND)
values ('1213', '77BC20590A51319DE0404F8189D81213', 1, '40', 'KC-IP', 'Open Institutional Proposal', 'Open an Institutional Proposal', 'Y');
insert into KRIM_PERM_T (PERM_ID, OBJ_ID, VER_NBR, PERM_TMPL_ID, NMSPC_CD,NM, DESC_TXT, ACTV_IND)
values ('1214', '77BC20590A51319DE0404F8189D81214', 1, '14', 'KC-IP', 'Cancel Institutional Proposal', 'Cancel an Institutional Proposal', 'Y');

-- add standard permissions for IP Review
insert into KRIM_PERM_T values ('1215', sys_guid(), 1, '10', 'KC-IP', 'Create Intellectual Property Review', 'Initiate a new Intellectual Property Review', 'Y');
insert into KRIM_PERM_T values ('1216', sys_guid(), 1, '16', 'KC-IP', 'Edit Intellectual Property Review', 'Edit a Intellectual Property Review', 'Y');
insert into KRIM_PERM_T values ('1217', sys_guid(), 1, '15', 'KC-IP', 'Save Intellectual Property Review', 'Save a Intellectual Property Review', 'Y');
insert into KRIM_PERM_T values ('1218', sys_guid(), 1, '5', 'KC-IP', 'Submit Intellectual Property Review', 'Submit a Intellectual Property Review', 'Y');
insert into KRIM_PERM_T values ('1219', sys_guid(), 1, '40', 'KC-IP', 'Open Intellectual Property Review', 'Open a Intellectual Property Review', 'Y');
insert into KRIM_PERM_T values ('1220', sys_guid(), 1, '14', 'KC-IP', 'Cancel Intellectual Property Review', 'Cancel a Intellectual Property Review', 'Y');


insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1000', '66AC72A924E64E31977BECE28F7CB6A1', 1, '1000', '3', '13', 'ProposalDevelopmentDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1001', 'BCB0EA943AEA449C8DF59062246B8C58', 1, '1001', '3', '13', 'ProtocolDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1002', 'AEAC3B4C5D4F43B3B46FA2E1CA92A826', 1, '1002', '3', '13', 'CommitteeDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1003', '45F3E6D77F654D6AB6272C8BFC17EAF4', 1, '1003', '3', '13', 'AwardDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1004', '10DA4120D9C444D28D99EE698B24A192', 1, '1004', '3', '13', 'BudgetDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1005', '75027183DF1B4B03B1DFFD592AAB5D23', 1, '1005', '3', '13', 'ProposalDevelopmentDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1006', 'D5E9CAC1D8E14FD4919360652E215646', 1, '1006', '3', '13', 'ProtocolDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1007', '50203F290F6C40499F5F70E802BC2619', 1, '1007', '3', '13', 'BudgetDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1008', '9DE3B9DFA7C447A08335FEA11B80272F', 1, '1008', '1017', '1007', 'proposal');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1009', '5CCE2A59ED98424CB458D05E283F96B3', 1, '1008', '1017', '13', 'ProposalDevelopmentDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1010', '8082C3F9974745AF956533C1487A53E3', 1, '1009', '1017', '1007', 'budget');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1011', 'A9CC43A3BD8C4BBEAD9063112EBF010D', 1, '1009', '1017', '13', 'ProposalDevelopmentDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1012', '24C0E706F00A484F9F6D1ADB3BBAE7F7', 1, '1010', '1017', '1007', 'narrative');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1013', '5452E94C4F2544BCB0C297D26A3AA3D0', 1, '1010', '1017', '13', 'ProposalDevelopmentDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1014', 'DAC6DFBAB91E4570A7448E1431F1A34A', 1, '1011', '1017', '1007', 'proposal_permissions');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1015', 'F430E3B15CC04E6C9D250394E4EC13F0', 1, '1011', '1017', '13', 'ProposalDevelopmentDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1016', 'B17BDA348E184262A8D2F5E1C8F95F01', 1, '1012', '1017', '1007', 'protocol');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1017', '8A6E7C9BB5304EB08D4AF6441D9A599D', 1, '1012', '1017', '13', 'ProtocolDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1018', 'CAE0E7473D5F4F70B4D053F7DACAE0B2', 1, '1013', '1017', '1007', 'protocol_permissions');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1019', '5D51FA0B6E4F4710B5079674A29E7CF9', 1, '1013', '1017', '13', 'ProtocolDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1020', '0DEF4890656546D9BE8A6C7D124A8742', 1, '1014', '1017', '1007', 'committee');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1021', 'C4981CB4A8EA4352A1102062F221D976', 1, '1014', '1017', '13', 'CommitteeDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1022', '1CC3B402D77949EEAC82DB5F231AAC9B', 1, '1015', '1017', '1007', 'schedule');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1023', '6C5EFCA3B2F94610857884BE56343651', 1, '1015', '1017', '13', 'CommitteeDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1024', 'CA592F6C99634653B544C0176FDB030D', 1, '1016', '1017', '1007', 'members');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1025', 'A040B83FF0664C919C48D74A2BA27573', 1, '1016', '1017', '13', 'CommitteeDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1026', '72CCC9B8697B42C08E6E66CEF4D377F4', 1, '1017', '1017', '1007', 'minutes');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1027', 'B202216324D946D49F28B6D060AA7B9C', 1, '1017', '1017', '13', 'MeetingManagementDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1028', '51D5507FBD5545C5A694222D25C94EF8', 1, '1018', '1017', '1007', 'award');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1029', 'B2DCE016F6DD4CBAA7A7640E0728C397', 1, '1018', '1017', '13', 'AwardDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1030', 'CCB10D7B2AAA463A9E4C282F6874F331', 1, '1019', '1017', '1007', 'award_budget');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1031', '6D5CD9FCE0D8401EBE5D4CC884E2DC72', 1, '1019', '1017', '13', 'BudgetDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1032', 'F171979D76AD4E95A87159809C4092FD', 1, '1020', '1017', '1007', 'attachments');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1033', '45D0A7B7BE0A4021A8E69F1158DFCF71', 1, '1020', '1017', '13', 'AwardDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1034', '3AA6216219AD4ABA8CD819307B7E5898', 1, '1021', '1017', '1007', 'proposal');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1035', '1043A89267E54C8DB23666BF3DA4F0C4', 1, '1021', '1017', '13', 'ProposalDevelopmentDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1036', '3CBEC6477FDA4AE9A74D71143A88EDA6', 1, '1022', '1017', '1007', 'budget');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1037', 'D58536B4D97D4ACD960EE51A005DF490', 1, '1022', '1017', '13', 'ProposalDevelopmentDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1038', '30397F89657F43899D88645E16772280', 1, '1023', '1017', '1007', 'narrative');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1039', '297F26F77BAE427F8C45BCB076DF227B', 1, '1023', '1017', '13', 'ProposalDevelopmentDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1040', 'BF8D4B74DD8348F6AA0C75D8CF3BD96F', 1, '1024', '1017', '1007', 'protocol');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1041', '6FBD6155DE4E4F03A011EC2269AFE119', 1, '1024', '1017', '13', 'ProtocolDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1042', '24AB8C56B75048B796B82D0AFC9B4048', 1, '1025', '1017', '1007', 'minutes');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1043', '5EF948D242C845558A023880EEE27431', 1, '1025', '1017', '13', 'MeetingManagementDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1044', '042929D275D74FF09CD2EE77503599D5', 1, '1026', '1017', '1007', 'committee');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1045', 'D44B17FC155E445883C5F1F92F47DBDA', 1, '1026', '1017', '13', 'CommitteeDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1046', 'DF476A4DCD88472F8011E1B181270EEA', 1, '1027', '1017', '1007', 'schedule');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1047', 'BB9D4E1EDF074412A3597F2D4A656AB9', 1, '1027', '1017', '13', 'CommitteeDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1048', '3796E298F1D64EBE8B2C2B4EB904FE64', 1, '1028', '1017', '1007', 'agenda');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1049', '07B8F1C2F2AA416982D33211C77101B0', 1, '1028', '1017', '13', 'MeetingManagementDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1050', '8C991D1756E54724A7A37EE7586F5FC9', 1, '1029', '1017', '1007', 'award');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1051', 'EB75B8752F64418D9E57BAB6ACD1FC6D', 1, '1029', '1017', '13', 'AwardDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1052', '36A4415D0FE74179B9B34C0FBBF5F5F8', 1, '1030', '1017', '1007', 'award_budget');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1053', '23FA33ECB83E4EB7A7A8FA2A85696CC9', 1, '1030', '1017', '13', 'BudgetDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1054', '181C18D45839440FA08CEB893BEBDF57', 1, '1031', '1017', '1007', 'attachments');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1055', 'EC8CADEA6E644C419EDAD02E843BF910', 1, '1031', '1017', '13', 'AwardDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1056', '651C958E172749B7B098C6AC30DCD0A1', 1, '1032', '1018', '1008', 'alter_master_data');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1057', 'BA381972519D4230A24FC38D903BD24D', 1, '1032', '1018', '13', 'ProposalDevelopmentDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1058', '83FECF9963CB480B81AA6685DB4FD584', 1, '1033', '1018', '1008', 'print');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1059', 'B73FB9A8E87846D2900F58AC68EFA52D', 1, '1033', '1018', '13', 'ProposalDevelopmentDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1060', '425207170A034D739CB2A60A06B212BC', 1, '1034', '1018', '1008', 'certify');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1061', '65B69FC857DE4883B9C15849187EBFB9', 1, '1034', '1018', '13', 'ProposalDevelopmentDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1062', 'D6AB414C556E4D148079A72DCA12B4AD', 1, '1035', '1018', '1008', 'submit_to_sponsor');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1063', '939E2468ED1D4CF9AD2FB06E73E7FE1F', 1, '1035', '1018', '13', 'ProposalDevelopmentDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1064', 'FD4FEBF82084471EA0631F65598FB8FC', 1, '1036', '1018', '1008', 'add_viewer');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1065', 'E2FA2FA1CB664D94858A47CD0AF252E3', 1, '1036', '1018', '13', 'ProposalDevelopmentDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1066', '1AF3FAF8DB0B4DEB90FCC13D84C70702', 1, '1037', '1018', '1008', 'create_ammendment');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1067', 'EEE8DE50BD5B4CC5BF5212DDDB77FEE3', 1, '1037', '1018', '13', 'ProtocolDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1068', 'FEDC1874591C47FCAE30FA3C58C4E3A5', 1, '1038', '1018', '1008', 'create_renewal');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1069', '5CE3D0126C254EB3B3BAB689410DDCAC', 1, '1038', '1018', '13', 'ProtocolDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1070', 'F795DC5205524D3589424C180BB585B5', 1, '1039', '1018', '1008', 'admin_correction');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1071', '936B92C5D4CF4C3CA152D59FD81C60CC', 1, '1039', '1018', '13', 'ProtocolDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1072', 'B95466D4CCE74584AEC26515F81DB866', 1, '1040', '1018', '1008', 'modify_submission_details');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1073', 'CA7E0F9FF8FF46F09CD12DC58C99135C', 1, '1040', '1018', '13', 'ProtocolDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1074', '5212EF26E9CE47C98B7617C784FF73EA', 1, '1041', '1018', '1008', 'notes');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1075', '3FB94077CDAA40AC8AD5EB8CEF2C9707', 1, '1041', '1018', '13', 'ProtocolDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1076', '9C5F22CE08214664A5CF90C9FCDE6FE7', 1, '1042', '1018', '1008', 'view_restricted_notes');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1077', '83DE56292B2B4D8884A41FA85049E058', 1, '1042', '1018', '13', 'ProtocolDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1078', '059109D1270F404590610DC07F618FFC', 1, '1043', '1018', '1008', 'generate_agenda');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1079', '012DB09FC01A4AD5BBFC628742A48A3E', 1, '1043', '1018', '13', 'MeetingManagementDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1080', '175201A21CEB49F5BE66EBDF6B7431B2', 1, '1044', '1018', '1008', 'generate_minutes');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1081', 'D5F27F8BC0E042FCAE47B2376046374A', 1, '1044', '1018', '13', 'MeetingManagementDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1082', '046A3A9878C849AF851BE70C03E95D2D', 1, '1045', '1018', '1008', 'generate_schedule');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1083', '3E9805D2EFDC48A3A7C59457924DF5DD', 1, '1045', '1018', '13', 'CommitteeDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1084', '7AB4C2703EFF42D692EF68AAE73DF63C', 1, '1046', '1018', '1008', 'view_member_details');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1085', '73477EA5A6AC4CA4BB1FF01D320084E9', 1, '1046', '1018', '13', 'CommitteeDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1086', '65052A3A24424C558A22BDD4AD8D80EA', 1, '1047', '1018', '1008', 'post_award_budget');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1087', '5F9FB7380FFD467AA2957BC234A7463F', 1, '1047', '1018', '13', 'BudgetDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1088', '520C8800A45A46E6855721C16EC7A574', 1, '1048', '1018', '13', 'ProtocolDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1089', 'EFEE62BB345F4C1CA2F014EF5556F371', 1, '1049', '1018', '13', 'ProposalDevelopmentDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1090', 'D80DC07375D4474D84B0D28EE001CDAB', 1, '1050', '1018', '13', 'ProtocolDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1091', 'AC50EBC8642943F2ADE85761BC12A0A5', 1, '1051', '1018', '13', 'BudgetDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1092', '77BC20590A4F319DE0404F8189D80DB4', 1, '1199', '3', '13', 'QuestionMaintenanceDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1093', '77BC20590A52319DE0404F8189D80DB4', 1, '1200', '3', '13', 'QuestionMaintenanceDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('181', '5B4F09744A25EF33E0404F8189D84F24', 1, '140', '12', '2', 'org.kuali.rice.kew.documentoperation.web.DocumentOperationAction');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('183', '5B4F09744A27EF33E0404F8189D84F24', 1, '141', '12', '2', 'org.kuali.rice.ksb.security.admin.web.JavaSecurityManagementAction');

insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('185', '5B4F09744A29EF33E0404F8189D84F24', 1, '142', '12', '2', 'org.kuali.rice.ksb.messaging.web.MessageQueueAction');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('187', '5B4F09744A2BEF33E0404F8189D84F24', 1, '143', '12', '2', 'org.kuali.rice.ksb.messaging.web.ServiceRegistryAction');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('189', '5B4F09744A2DEF33E0404F8189D84F24', 1, '144', '12', '2', 'org.kuali.rice.ksb.messaging.web.ThreadPoolAction');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('191', '5B4F09744A2FEF33E0404F8189D84F24', 1, '145', '12', '2', 'org.kuali.rice.ksb.messaging.web.QuartzQueueAction');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('192', '5B4F09744A30EF33E0404F8189D84F24', 1, '146', '5', '13', 'RiceDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('193', '5B4F09744A31EF33E0404F8189D84F24', 1, '147', '3', '13', 'RiceDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('194', '5B4F09744A32EF33E0404F8189D84F24', 1, '148', '3', '13', 'RiceDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('195', '5B4F09744A33EF33E0404F8189D84F24', 1, '149', '3', '13', 'RiceDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('196', '5B4F09744A34EF33E0404F8189D84F24', 1, '150', '18', '4', 'KR*');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('197', '5B4F09744A35EF33E0404F8189D84F24', 1, '151', '19', '4', 'KR*');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('198', '5B4F09744A36EF33E0404F8189D84F24', 1, '152', '20', '4', 'KR*');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('199', '5B4F09744A37EF33E0404F8189D84F24', 1, '153', '22', '17', 'Person');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('200', '5B4F09744A38EF33E0404F8189D84F24', 1, '154', '22', '17', 'System');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('201', '5B4F09744A39EF33E0404F8189D84F24', 1, '155', '21', '4', 'KR*');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('202', '5B4F09744A3AEF33E0404F8189D84F24', 1, '156', '3', '13', 'RiceDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('211', '5B4F09744A43EF33E0404F8189D84F24', 1, '161', '10', '4', 'KR*');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('213', '5B4F09744A45EF33E0404F8189D84F24', 1, '162', '10', '4', 'KR*');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('204', '5B4F09744A39EF33E0404F8189D84F26', 1, '834', '18', '4', 'KUALI');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('216', '5B4F09744A48EF33E0404F8189D84F24', 1, '163', '16', '4', 'KR*');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('217', '5B4F09744A49EF33E0404F8189D84F24', 1, '164', '15', '4', 'KR*');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('218', '5B4F09744A4AEF33E0404F8189D84F24', 1, '165', '3', '13', 'RiceDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('219', '5B4F09744A4BEF33E0404F8189D84F24', 1, '166', '12', '4', 'KR*');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('220', '5B4F09744A4CEF33E0404F8189D84F24', 1, '167', '8', '13', 'KualiDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('221', '5B4F09744A4DEF33E0404F8189D84F24', 1, '168', '3', '13', 'KualiDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('222', '5B4F09744A4EEF33E0404F8189D84F24', 1, '290', '8', '13', 'RiceDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('223', '5B4F09744A4FEF33E0404F8189D84F24', 1, '170', '4', '14', 'A');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('225', '5B4F09744A51EF33E0404F8189D84F24', 1, '172', '4', '14', 'F');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('226', '5B4F09744A52EF33E0404F8189D84F24', 1, '173', '4', '14', 'K');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('237', '5B4F09744A5DEF33E0404F8189D84F24', 1, '180', '8', '13', 'KualiDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('238', '5B4F09744A5EEF33E0404F8189D84F24', 1, '180', '8', '16', 'PreRoute');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('239', '5B4F09744A5FEF33E0404F8189D84F24', 1, '181', '8', '13', 'KualiDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('240', '5B4F09744A60EF33E0404F8189D84F24', 1, '181', '8', '15', 'R');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('243', '5B4F09744A63EF33E0404F8189D84F24', 1, '183', '11', '5', 'KimPrincipalImpl');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('244', '5B4F09744A64EF33E0404F8189D84F24', 1, '183', '11', '6', 'password');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('249', '5B6013B3AD121A9CE0404F8189D87094', 1, '167', '8', '16', 'PreRoute');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('376', '606763510FC496D3E0404F8189D857A2', 1, '259', '9', '13', 'KualiDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('379', '606763510FC796D3E0404F8189D857A2', 1, '261', '9', '13', 'KualiDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('384', '606763510FCE96D3E0404F8189D857A2', 1, '264', '59', '13', 'RiceDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('385', '606763510FCF96D3E0404F8189D857A2', 1, '264', '59', '8', 'false');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('701', 'A207FBACC3764793896D0D769F6DBBFF', 1, '701', '10', '5', 'RuleTemplate');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('702', '37F35D51EE714A8B8A66802F89626C50', 1, '702', '10', '5', 'EDocLiteStyle');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('703', '950DE6A28C144A9B87CA2CFD9FF337C8', 1, '703', '10', '5', 'EDocLiteDefinition');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('707', 'D270B9BA7E494507AD0B5D184AB13479', 1, '707', '10', '5', 'RuleAttribute');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('714', '5BEE5EF7C03348D49125B0E87F764F28', 1, '714', '10', '5', 'PessimisticLock');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('719', '45EEFA6F23FD4015B927D1D19F482888', 1, '719', '10', '5', 'ParameterDetailType');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('720', 'FFF2C6639C6041F1B148AA9901C8A1F7', 1, '720', '10', '5', 'ParameterNamespace');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('721', '6D489E39C0BC4890803E3A5F412297BE', 1, '721', '10', '5', 'ParameterType');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('751', 'A3142D53EC1541BE92223181852C37CF', 1, '701', '10', '4', 'KR-WKFLW');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('752', '6250F01B4CA243FCBA64A93FE4EAFE83', 1, '702', '10', '4', 'KR-WKFLW');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('753', 'E0F57E480CF646A585BC92E91AB5FB1D', 1, '703', '10', '4', 'KR-WKFLW');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('757', '266BD230C3AA40EB9FC8079FBB960DF0', 1, '707', '10', '4', 'KR-WKFLW');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('764', '2FF0786F86D845F097688A5F83ACF0A7', 1, '714', '10', '4', 'KR-NS');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('768', '160C75C4C8A3411D8856A50F38487EB2', 1, '719', '10', '4', 'KR-NS');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('769', '992DE7BE4C3C46F8AEDBE7925CBEE6B3', 1, '720', '10', '4', 'KR-NS');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('770', '330AAEB4459F4A34BBF8FECEA9E23404', 1, '721', '10', '4', 'KR-NS');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('800', '606763510FD296D3E0404F8189D857A2', 1, '801', '10', '4', 'KR-WKFLW');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('801', '606763510FD396D3E0404F8189D857A2', 1, '801', '10', '5', 'RuleTemplate');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('802', '606763510FD596D3E0404F8189D857A2', 1, '802', '10', '4', 'KR-WKFLW');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('803', '606763510FD696D3E0404F8189D857A2', 1, '802', '10', '5', 'EDocLiteStyle');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('804', '606763510FD896D3E0404F8189D857A2', 1, '803', '10', '4', 'KR-WKFLW');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('805', '606763510FD996D3E0404F8189D857A2', 1, '803', '10', '5', 'EDocLiteDefinition');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('806', '606763510FDB96D3E0404F8189D857A2', 1, '807', '10', '4', 'KR-WKFLW');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('807', '606763510FDC96D3E0404F8189D857A2', 1, '807', '10', '5', 'RuleAttribute');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('814', '606763510FE396D3E0404F8189D857A2', 1, '814', '10', '5', 'PessimisticLock');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('819', '606763510FE696D3E0404F8189D857A2', 1, '819', '10', '5', 'ParameterDetailType');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('820', '606763510FE996D3E0404F8189D857A2', 1, '820', '10', '5', 'ParameterNamespace');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('821', '606763510FEC96D3E0404F8189D857A2', 1, '821', '10', '5', 'ParameterType');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('864', '606763510FE296D3E0404F8189D857A2', 1, '814', '10', '4', 'KR-NS');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('868', '606763510FE596D3E0404F8189D857A2', 1, '819', '10', '4', 'KR-NS');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('869', '606763510FE896D3E0404F8189D857A2', 1, '820', '10', '4', 'KR-NS');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('870', '606763510FEB96D3E0404F8189D857A2', 1, '821', '10', '4', 'KR-NS');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('873', '606763510FF396D3E0404F8189D857A2', 1, '265', '12', '4', 'KR-WKFLW');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('874', '606763510FF496D3E0404F8189D857A2', 1, '265', '12', '2', 'org.kuali.rice.kew.batch.web.IngesterAction');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('432', '6314CC58CF5AB7B5E0404F8189D84439', 1, '306', '11', '6', 'taxId');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('478', '662384B381BA67A1E0404F8189D868A6', 1, '332', '5', '13', 'KualiDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('479', '662384B381BB67A1E0404F8189D868A6', 1, '332', '5', '14', 'F');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('480', '662384B381BE67A1E0404F8189D868A6', 1, '333', '5', '13', 'KualiDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('481', '662384B381BF67A1E0404F8189D868A6', 1, '333', '5', '14', 'K');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('482', '662384B381C267A1E0404F8189D868A6', 1, '334', '5', '13', 'KualiDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('483', '662384B381C367A1E0404F8189D868A6', 1, '334', '5', '14', 'A');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('203', '5B4F09744A39EF33E0404F8189D84F25', 1, '833', '21', '4', 'KUALI');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('205', '5B4F09744A39EF33E0404F8189D84F27', 1, '835', '19', '4', 'KUALI');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('206', '5B4F09744A39EF33E0404F8189D84F28', 1, '836', '20', '4', 'KUALI');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('431', '6314CC58CF59B7B5E0404F8189D84439', 1, '306', '11', '5', 'IdentityManagementPersonDocument');

insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1147', '6314CC58CF59B7B5E0404F8189D81147', 1, '1201', '1013', '13', 'QuestionnaireMaintenanceDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1148', '6314CC58CF59B7B5E0404F8189D81148', 1, '1202', '1013', '13', 'QuestionnaireMaintenanceDocument');

insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1149', '6314CC58CF59B7B5E0404F8189D81149', 1, (select PERM_ID from KRIM_PERM_T where NM='Create Proposal Log'), '3', '13', 'ProposalLogMaintenanceDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1150', '6314CC58CF59B7B5E0404F8189D81150', 1, (select PERM_ID from KRIM_PERM_T where NM='Edit Proposal Log'), '3', '13', 'ProposalLogMaintenanceDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1151', '6314CC58CF59B7B5E0404F8189D81151', 1, (select PERM_ID from KRIM_PERM_T where NM='Save Proposal Log'), '3', '13', 'ProposalLogMaintenanceDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1152', '6314CC58CF59B7B5E0404F8189D81152', 1, (select PERM_ID from KRIM_PERM_T where NM='Submit Proposal Log'), '3', '13', 'ProposalLogMaintenanceDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1153', '6314CC58CF59B7B5E0404F8189D81153', 1, (select PERM_ID from KRIM_PERM_T where NM='Open Proposal Log'), '3', '13', 'ProposalLogMaintenanceDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1154', '6314CC58CF59B7B5E0404F8189D81154', 1, (select PERM_ID from KRIM_PERM_T where NM='Cancel Proposal Log'), '3', '13', 'ProposalLogMaintenanceDocument');

insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1155', '6314CC58CF59B7B5E0404F8189D81155', 1, (select PERM_ID from KRIM_PERM_T where NM='Create Institutional Proposal'), '3', '13', 'InstitutionalProposalDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1156', '6314CC58CF59B7B5E0404F8189D81156', 1, (select PERM_ID from KRIM_PERM_T where NM='Edit Institutional Proposal'), '3', '13', 'InstitutionalProposalDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1157', '6314CC58CF59B7B5E0404F8189D81157', 1, (select PERM_ID from KRIM_PERM_T where NM='Save Institutional Proposal'), '3', '13', 'InstitutionalProposalDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1158', '6314CC58CF59B7B5E0404F8189D81158', 1, (select PERM_ID from KRIM_PERM_T where NM='Submit Institutional Proposal'), '3', '13', 'InstitutionalProposalDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1159', '6314CC58CF59B7B5E0404F8189D81159', 1, (select PERM_ID from KRIM_PERM_T where NM='Open Institutional Proposal'), '3', '13', 'InstitutionalProposalDocument');
insert into KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1160', '6314CC58CF59B7B5E0404F8189D81160', 1, (select PERM_ID from KRIM_PERM_T where NM='Cancel Institutional Proposal'), '3', '13', 'InstitutionalProposalDocument');

-- specify doc type qualifier for IP Review permissions
insert into KRIM_PERM_ATTR_DATA_T values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Create Intellectual Property Review'), '3', '13', 'IntellectualPropertyReviewMaintenanceDocument');
insert into KRIM_PERM_ATTR_DATA_T values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Edit Intellectual Property Review'), '3', '13', 'IntellectualPropertyReviewMaintenanceDocument');
insert into KRIM_PERM_ATTR_DATA_T values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Save Intellectual Property Review'), '3', '13', 'IntellectualPropertyReviewMaintenanceDocument');
insert into KRIM_PERM_ATTR_DATA_T values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Submit Intellectual Property Review'), '3', '13', 'IntellectualPropertyReviewMaintenanceDocument');
insert into KRIM_PERM_ATTR_DATA_T values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Open Intellectual Property Review'), '3', '13', 'IntellectualPropertyReviewMaintenanceDocument');
insert into KRIM_PERM_ATTR_DATA_T values (KRIM_ATTR_DATA_ID_S.nextval, sys_guid(), 1, (select PERM_ID from KRIM_PERM_T where NM='Cancel Intellectual Property Review'), '3', '13', 'IntellectualPropertyReviewMaintenanceDocument');

--Insert into KRNS_PARM_T (NMSPC_CD,PARM_DTL_TYP_CD,PARM_NM,OBJ_ID,VER_NBR,PARM_TYP_CD,TXT,PARM_DESC_TXT,CONS_CD,APPL_NMSPC_CD) values ('KR-WKFLW','All','KIM_PRIORITY_ON_DOC_TYP_PERMS_IND',SYS_GUID(),1,'CONFG','N','Flag for enabling/disabling document type permission checks to use KIM Permissions as priority over Document Type policies.','A','KUALI');

insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('5B1B6B919CCA6496E0404F8189D822F2', 1, 'kr', '1', null, 'Y', to_date('07-11-2008 09:49:02', 'dd-mm-yyyy hh24:mi:ss'), '1');
--insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
--values ('8B8C898C872B44859522FE27B6E4B53F', 1, 'quickstart', '1200', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:52', 'dd-mm-yyyy hh24:mi:ss'), '10000000000');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('8A69C8405F8B410C9EACB52956E85AA6', 1, 'jtester', '1201', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000001');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('6CBE066CC28044A0A0866DD14E4D7624', 1, 'KULUSER', '1202', 'fK69ATFsAydwQuteang+xMva+Tc=', 'N', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000002');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('37229022CA404E80BC8F373203F10B8D', 1, 'majors', '1203', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000003');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('15F6FAF1323E412983314D4A79CB9918', 1, 'chew', '1204', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000004');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('A209EF698E81442FAF48D097FC62B952', 1, 'woods', '1205', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000005');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('F3BCF1D661E149CBB2D2C8AABAD38908', 1, 'oblood', '1206', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000006');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('07203A78E996473FB26C349AED6F5EDA', 1, 'cate', '1207', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000007');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('1A382F71CCAB4BF1AF5D24EBFD67E0BC', 1, 'houlihan', '1208', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000008');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('4AC6ACB3C4A84F5E8F4E51ABD6766EF7', 1, 'rbpaulin', '1209', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000009');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('43DE84156AF84144957C166141B366AD', 1, 'donugent', '1210', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000010');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('AF6097148096469C9BDF5BCBF9F30B7E', 1, 'mbshorte', '1211', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000011');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('87101C9FA53D47A588EFA86E5108EF88', 1, 'underwoo', '1212', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000012');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('FC2D946C23604050B91A6AED069A7377', 1, 'hweubank', '1213', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000013');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('28043DCEC980482CBF8C1070C07CED10', 1, 'lvbarger', '1214', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000014');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('DD5C56C2D6BD4862BC9D0FC03FA49C6C', 1, 'jckahler', '1215', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000015');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('63A2D567A0324B1BBCDD0EC4325D0724', 1, 'byler', '1216', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000016');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('9454D2E0D71E4CAC8057A713A012ACBB', 1, 'aemcafee', '1217', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000017');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('B632C6413AAE46B8AAE255654820325C', 1, 'westfall', '1218', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000018');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('C19AD543F1044C7F86F58CE12EF09536', 1, 'bbshankl', '1219', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000019');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('FFCC34DDA8D3494798F3CE0C53AE971B', 1, 'ferland', '1220', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000020');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('5936C559C40B46F39E9DFE6027B4BB5C', 1, 'khcrabtr', '1221', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000021');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('ED083BF160474FB39666A80F36846575', 1, 'felker', '1222', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000022');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('619177AFADA5482DA084CC1E8ABC7764', 1, 'martin', '1223', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000023');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('64B542B23C594D409DF07D7872A5A7A7', 1, 'sterner', '1224', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000024');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('683EFDC0536348D08B8AAA8C8D9500A7', 1, 'chbayles', '1225', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000025');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('8D85C4BC33DF49699D919062C679E5CB', 1, 'tmkato', '1226', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000026');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('A20EF4192F82429693219B70AED46FC0', 1, 'cphovis', '1227', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000027');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('F4FDB068324C4017B94378BB1FACA418', 1, 'rnmcinty', '1228', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000028');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('3E821ADB6B3B4E5A8E5F3C9DDEBCABE1', 1, 'eagle', '1229', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000029');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('53A9A8A7A7D54D82BC806C2465DD303B', 1, 'mjpond', '1230', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000030');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('1C655562782448A5A8F77182DAFD9DF1', 1, 'flaherty', '1231', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000031');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('DF8B9D5BB7B24F90A0A3A13B4B59A718', 1, 'mukoss', '1232', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000032');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('4E0C766400AB432B84516C267F41186C', 1, 'mceacher', '1233', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000033');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('38BA68FD4D0E4AC39E8B7642AB11EAD1', 1, 'wacuna', '1234', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000034');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('B34CAB8B1E33498C8DB18812DD2EC2AD', 1, 'schulte', '1235', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000035');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('7282512234DE42F7AE15CC24C3096F19', 1, 'odevries', '1236', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000036');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('DF518032101846639BF9CEF26D73846E', 1, 'dxdevane', '1237', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000037');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('8988DA304BD2457DABB6FF7FFF3A010E', 1, 'psmock', '1238', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000038');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('4C61FA89EE69415484687E2B8515953C', 1, 'lvclinks', '1239', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000039');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('905FF02BFBF84232A6F54652E0AE4164', 1, 'brunelle', '1240', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000040');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('B371B865B1714996A4D6249B673D8AA7', 1, 'cbernal', '1241', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000041');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('C5801943429F4C4F92A5D90CFAC33FDE', 1, 'borst', '1242', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000042');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('7099D1D98E244DC9A5000027EDB1CEA9', 1, 'wsoileau', '1243', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000043');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('EFF1E1F6DF814736A406D11D2259EB02', 1, 'burd', '1244', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000044');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('136515E99D13482A8E848CAB068B3963', 1, 'engel', '1245', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000045');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('5D9CD81A81C34B768B7C417DF070F9F6', 1, 'headley', '1246', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000046');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('7A856A1BA13E465986760F359ABCC621', 1, 'rpgailey', '1247', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000047');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('BD032A0C1D424147A5FC6FE78CEA69BA', 1, 'shields', '1248', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000048');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('8C73A8BC731349B883B7CF9311864C0A', 1, 'elgin', '1249', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000049');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('A38B27B02C484C7C9F5CC244622B3230', 1, 'boman', '1250', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000050');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('80665B61BF7F4ECBB6A2F4F27EEF90DA', 1, 'duggins', '1251', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000051');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('027AA071B5E246D0AEA90D520BDCEFEA', 1, 'khowens', '1252', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000052');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('1C648141EC394B9C89D9A00CB4A1E3AB', 1, 'ndarr', '1253', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000053');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('49A1FF0AEEBA4255A7639C4657B42EAF', 1, 'cary', '1254', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000054');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('09FA38B277EC436486D1239EC31D0DAA', 1, 'clmcbee', '1255', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000055');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('881429CE536B44C5953A19C2E6AA9C4F', 1, 'ralston', '1256', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000056');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('89C22897E8B64882A68276D5F7D6B00A', 1, 'mwmartin', '1257', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000057');
insert into KRIM_PRNCPL_T (OBJ_ID, VER_NBR, PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID)
values ('71EE6B88A0EE422CACEA18481C88CF04', 1, 'rrabbit', '1258', null, 'Y', to_date('07-10-2009 13:23:53', 'dd-mm-yyyy hh24:mi:ss'), '10000000058');

insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1321', 'E26D3E301AC8473294D4580DD1FDE87A', 1, '1490', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1322', '725F285038ED460CBB831E370712706C', 1, '1490', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1323', '63E71E674B7D4B65B849DB3AEAD4B8A4', 1, '1491', '1013', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1324', '77C1DA9BAA314FAD88A89C014E53C0D4', 1, '1491', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1325', '070BBED387DF43EFB1E10DF2CAF24696', 1, '1492', '1013', '1005', 'IN-CARD');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1326', '1203323D78224B598FD7289CF78F1612', 1, '1492', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1327', 'A456E69BF7034C8AACA2644CBCDC6FDC', 1, '1493', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1328', '470A8E43E4924FC897A82EA36EFA2C30', 1, '1493', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1329', '0D35E87B109A4860A6146B50B4F21D55', 1, '1494', '1013', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1330', 'D8E64FE2368E438DB00356B138ADD49D', 1, '1494', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1331', '6F01459BBB944245A06A9C691F2F2174', 1, '1495', '1013', '1005', 'IN-CARD');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1332', 'E6CD72218CF041D18348A1709B965DAB', 1, '1495', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1333', '3F817D8232B749389B12414115757D0D', 1, '1496', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1334', '42C6150CE14541BAA61E488D162BC7FA', 1, '1496', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1335', '2E5DB88E50FC41AEA4117DD5A1B968F1', 1, '1497', '1013', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1336', '4D4B26E61FEA49CF9FFE00C775E17A8C', 1, '1497', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1337', '208ECF2639234A3AA8CB67D6595EB9E9', 1, '1498', '1013', '1005', 'IN-CARD');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1338', '66CCE555ACF84DDA8F0680831CAC9636', 1, '1498', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1339', 'EC4A7E8C89DA4D33AACA20013A0450D5', 1, '1499', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1340', 'FE6742A369A74836892EB64608CE51A8', 1, '1499', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1341', 'FE86425D4AB34B07B5E85BDBAA4D1C68', 1, '1500', '1013', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1342', '978683FA4CC246BDA10116CF64AD452A', 1, '1500', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1343', '5AC3D06C064944648452632EA2ADAC4E', 1, '1501', '1013', '1005', 'IN-CARD');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1344', '4B3617AF58F943B39656AD7BA0946BD4', 1, '1501', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1345', '752A88B4A0B7418D9CB680EEB70DE944', 1, '1502', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1346', 'C8540670EB21458F86134BC5373CC270', 1, '1502', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1347', '093FD10CF0D94D288AA5C7A676E147E0', 1, '1503', '1013', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1348', '9C943907E5224DF8A4DE26DA689E36A1', 1, '1503', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1349', 'F1B050EE1EA24C08B5649E986A540509', 1, '1504', '1013', '1005', 'IN-CARD');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1350', '4198C15A0A6A417AAA951734F763A731', 1, '1504', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1351', 'D5F317AD75D042A888E63BAA28C8AB3A', 1, '1505', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1352', 'C5566B069C6740259C808A33B078F667', 1, '1505', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1353', 'A12A453F5C5B495FA32BF43266BE522F', 1, '1506', '1013', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1354', '774D3C15B1024FA5AA5FC1E1DC5417C8', 1, '1506', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1355', '4B10FBFE52A64E16BFDF6658E90B964B', 1, '1507', '1013', '1005', 'IN-CARD');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1356', '693770F30C784579860C9F024CC880FE', 1, '1507', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1357', '519E82488813480F8F0FF8A7C5C918DE', 1, '1508', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1358', '3A2E9A46828E42C9B92B0F11CD50FB46', 1, '1508', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1359', 'D524148A4B7B4DF392207FCAD8CA05EF', 1, '1509', '1013', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1360', 'BCC484277A63461B86AC59310DEC69C9', 1, '1509', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1361', '5FF39D2C3E72474190C7232203742F5F', 1, '1510', '1013', '1005', 'IN-CARD');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1362', '532C24AE3FB14FCAA08D7A2F9D5FA933', 1, '1510', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1363', 'D01E86B52B644B71BEBAFCDD4C65B916', 1, '1511', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1364', 'F88D74719FD8458BA3A8FECC2828929E', 1, '1511', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1365', '3FC595CA77B841C996D3BEAF12A0A7C0', 1, '1512', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1366', 'AE5CE79AFC23453BBBEB9E6DD24F9227', 1, '1512', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1367', '2F0F12ABB5B14949B3CADC27A3750659', 1, '1513', '1013', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1368', '20E1C1231AD54E48B1B6E567821F9D6A', 1, '1513', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1369', 'F5EB0CFDB276459799E2DF02BD1F975F', 1, '1514', '1013', '1005', 'IN-MDEP');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1370', 'A6A5DCC732A4490CA99155D5F42A5F39', 1, '1514', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1371', '82719820BC34450EAF744E5BDA34B059', 1, '1515', '1013', '1005', 'IN-PED');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1372', 'F07DCBF9CA6C40D7B174CF962F77CA75', 1, '1515', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1373', '63FD7003BEDF469391ED774B1108E01D', 1, '1516', '1013', '1005', 'BL-BL');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1374', 'B14A09BFD5A64C54A14FEFDF0F32BD7C', 1, '1516', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1375', '1EA737BCAD4348BF8B8B748CEB2DDB78', 1, '1517', '1013', '1005', 'IN-MED');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1376', 'E0EA237FB89E4BA0906542F316AD5AB6', 1, '1517', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1377', 'ACBEED76469B450BBA799B57510C4BB8', 1, '1518', '1013', '1005', 'IN-CARR');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1378', '7485C7EC1E034C05B3444C5D8EE12166', 1, '1518', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1379', '17CF6A7262CB4DC49CE6E26F97301294', 1, '1519', '1013', '1005', 'IN-CARR');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1380', 'C478D5382DE648C2BAD771AFACAC0FEA', 1, '1519', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1381', '15AD81068CB94CC4AD64CA00894E0BEA', 1, '1520', '1013', '1005', 'IN-CARR');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1382', 'DB402890176B41298F5F00F17D330D2E', 1, '1520', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1383', '7C1D2819FB024B71B21C4D80C35BECE9', 1, '1521', '1013', '1005', 'IN-CARR');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1384', '233E65C5ACA24874BEA5B38AB676CE73', 1, '1521', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1385', '7EB1F1FBBE024274929FC72008A62488', 1, '1522', '1013', '1005', 'IN-CARR');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1386', '8AA8459BBB974F549D1C5D91839F30A6', 1, '1522', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1387', '49657D5AA6E44C9A95E1A4F67FA921B7', 1, '1523', '1013', '1005', 'IN-CARR');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1388', '53F4154D7C2F4E62BB2C43DAA6F59E74', 1, '1523', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1389', 'DF45F3784D494199B4489C2EE81D619C', 1, '1524', '1013', '1005', 'IN-CARR');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1390', '5D699A7B7A2B486684E24BBB0C8773B9', 1, '1524', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1391', 'BEB444A1F0824F26898F5D45E8C0E33C', 1, '1525', '1013', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1392', 'CD1C2D059F03426E874DDFD188A0FAE6', 1, '1525', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1393', 'CDCC4B015A594984A331FA046B71FF33', 1, '1526', '1013', '1005', 'IN-CARD');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1394', 'EBBDBDF44B484DB7B645EC2002B887C3', 1, '1526', '1013', '1006', 'Y');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1395', '4610A35917E14E358D88E02C02974745', 1, '1527', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1396', '78A5EAFD8B094CAC9FF56A9B3AD04941', 1, '1527', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1397', '1F616C38124A48FBB4EA0F2A7DB82357', 1, '1528', '1013', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1398', '77865D0BE1E94028B62A38B16C2969BC', 1, '1528', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1399', '7CECBB75EB2E4A1F8CE825D4C6FE1A24', 1, '1529', '1013', '1005', 'IN-CARD');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1400', '935D996A3BC844B1B4F2554242000289', 1, '1529', '1013', '1006', 'Y');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1401', 'CA80DD534A274FC68FC8D03607B69FA2', 1, '1530', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1402', '58E9EEFEAA5F472BBB556F73CD0EBC1B', 1, '1530', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1403', '06C0A91DA51843648C0D7DCFD9C60F68', 1, '1531', '1013', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1404', '152F127B8F784CAB90C306FE1D5A96A2', 1, '1531', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1405', '5CEAF077BDB04FA4A91E4C3480E564BF', 1, '1532', '1013', '1005', 'IN-CARD');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1406', '5F5126C4AFBF4D10950859492EE9C910', 1, '1532', '1013', '1006', 'Y');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1407', '4224F5BC27D24C65AD99C888C950E4D8', 1, '1533', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1408', 'E366BDE5D0CF461181D8F18295D378E3', 1, '1533', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1409', 'C84D179936CE41DDADC7F715B529ECD7', 1, '1534', '1013', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1410', '53C599C4D6534601980886E7353093BA', 1, '1534', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1411', '2C9F7B8BDCA74B7892DFAEF4DCECF819', 1, '1535', '1013', '1005', 'IN-CARD');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1412', 'B7227B45D36249B18E271FB267662561', 1, '1535', '1013', '1006', 'Y');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1413', '3ACD42D60EF64657B693E8AF9681669E', 1, '1536', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1414', 'D1964B2C7FBE41C38BAEB5E4494EBE62', 1, '1536', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1415', 'BAD6D61D24914BD2B6FD868693463EC2', 1, '1537', '1013', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1416', '8E71F257166841E38FD753BE46612D53', 1, '1537', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1417', '764C9BA9645A4534830763FE33261FF0', 1, '1538', '1013', '1005', 'IN-CARD');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1418', '44C7AF64B9A843EDA63A6531A3F4F04E', 1, '1538', '1013', '1006', 'Y');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1419', '61C095A09FDF4687A704C025B3A0CEA7', 1, '1539', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1420', 'FB1AA645D57B4A77A7C0C944F6DA0EE2', 1, '1539', '1013', '1006', 'N');

insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1421', '86A87D0201B54F5C9C53BF921EDA5627', 1, '1540', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1422', 'CE6640370D144AF89B8028BDE79625DD', 1, '1540', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1423', '3ACEDD2EAF554B98A37A9D5400D81DDF', 1, '1541', '1013', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1424', '4F5AC889CD4E447EA2BC9DCC02AE9193', 1, '1541', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1425', '351ECD144ABC47FDA01BE8198A562F33', 1, '1542', '1013', '1005', 'IN-CARD');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1426', '1814977D5705476E9AB4491893ACE3AF', 1, '1542', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1427', '4C3B106B285E45EC856450734E265294', 1, '1543', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1428', 'BEB286ABC6DB458883396368344A19A4', 1, '1543', '1013', '1006', 'Y');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1429', '76943C2B89F343C7B2B81472E8E30673', 1, '1544', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1430', '4B007E3C88144851897DCCAF1DCEBC59', 1, '1544', '1013', '1006', 'Y');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1016', '6945209017D74D4F81B34D309A932F7C', 1, '1291', '1012', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1431', '05726E8A497448E7888C3B187DBA5D32', 1, '1545', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1432', '353F3EC332534AF197D70868BB3425B1', 1, '1545', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1433', '3F011048BADD4B60932847E74561FF2D', 1, '1546', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1434', 'E4C3EB75430C4BC68154C152CEC1B815', 1, '1546', '1013', '1006', 'Y');

insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1435', SYS_GUID(), 1, '1590', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1436', SYS_GUID(), 1, '1590', '1013', '1006', 'Y');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1437', SYS_GUID(), 1, '1591', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1438', SYS_GUID(), 1, '1591', '1013', '1006', 'Y');

insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1114', '212F5EAD08584C76AD098A9E040B5B55', 1, 'Proposal Creator', 'KC-UNT', 'Proposal Creator', '1012', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1115', '9B78D4A5F17045D29BD58573F715E9E9', 1, 'Aggregator', 'KRA-PD', 'Proposal Aggregator', '1012', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1116', 'FCD180EE55D74436AED6EF22B276A9AB', 1, 'Narrative Writer', 'KRA-PD', 'Proposal Narrative Writer', '1012', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1117', 'AD250D7F46144A43A1E8D01B623D26DA', 1, 'Budget Creator', 'KRA-PD', 'Proposal Budget Creator', '1012', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1118', '5BAFF0D96A844723B9A3AFE3CCA410AA', 1, 'Viewer', 'KRA-PD', 'Proposal Viewer', '1012', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1119', 'E707A732B0B04F4FB28A8C92C041A588', 1, 'unassigned', 'KRA-PD', 'Unassigned role - no permissions', '1012', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1120', 'CAE0ECF0CE284E45974FABF286BAD8CE', 1, 'OSP Administrator', 'KC-ADM', 'OSP Administrator', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1121', 'C3B09CF6AC464010ADBF4EA92CE53479', 1, 'Proposal Submission', 'KC-ADM', 'Proposal Submission', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1122', 'EFE01BE9E1424615A4AF38B792DA7A81', 1, 'Protocol Creator', 'KC-UNT', 'Protocol Creator', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1123', '62015972B5FB42BAB8AA3F192F579FDB', 1, 'Protocol Aggregator', 'KC-PROTOCOL', 'Protocol Aggregator', '1012', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1124', '1260789FAF40419FAD39E8FAEF4C3DEF', 1, 'Protocol Viewer', 'KC-PROTOCOL', 'Protocol Viewer', '1012', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1125', '9F66AED40E3A46208CDAF5B6C206ED61', 1, 'IRB Administrator', 'KC-UNT', 'IRB Administrator', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1126', '8CB65D5D9C07460385460BA322579248', 1, 'IRB Reviewer', 'KC-UNT', 'IRB Reviewer', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1127', '2CBCA566906744148FB094F403A96FA4', 1, 'Protocol Unassigned', 'KC-PROTOCOL', 'Protocol Unassigned - no permissions', '1012', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1128', '98EB4A4604A542A3993B4DD786060E85', 1, 'Award Budget Approver', 'KC-AWARD', 'Award Budget Approver', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1129', '979AAB61EE4E4C5B947E73FC127B8F80', 1, 'Award Budget Admnistrator', 'KC-AWARD', 'Award Budget Admnistrator', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1130', '047E4C952F2344A189B1B994A7B58579', 1, 'Award Modifier', 'KC-AWARD', 'Award Modifier', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1131', 'FAFE9A0676894785B8FB5129F84286D1', 1, 'Award Budget Aggregator', 'KC-AWARD', 'Award Budget Aggregator', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1132', '647648832722455992149EDD5F5FB5C6', 1, 'approver', 'KRA-PD', 'approver', '1012', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1133', '558F04D7BD5C448DA10C36DEF69671CC', 1, 'Application Administrator', 'KC-AWARD', 'Application Administrator', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1134', '795DF896B95D4D8397B8C293A10E9449', 1, 'Award Budget Maintainer', 'KC-AWARD', 'Award Budget Maintainer', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1135', 'B729132629F54EB8A5D7D3925970F642', 1, 'Award Documents Maintainer', 'KC-AWARD', 'Award Documents Maintainer', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1136', '4029012A43524567B0732496E7190F4E', 1, 'Award Budget Modifier', 'KC-AWARD', 'Award Budget Modifier', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1137', 'FCD0115C9AF146B6B5E7D04B679D67CA', 1, 'Award Viewer', 'KC-AWARD', 'Award Viewer', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1138', '7341FBA639394716BDF144C25AA0DB87', 1, 'Award Budget Viewer', 'KC-AWARD', 'Award Budget Viewer', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1139', '9CFD39CFF2854CA28FC327BA19546ADF', 1, 'Award Documents Viewer', 'KC-AWARD', 'Award Documents Viewer', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1140', '0010601F1BAD4835B17A3010E6B5FC63', 1, 'Departments Awards Viewer', 'KC-AWARD', 'Departments Awards Viewer', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1141', 'A39580605AD44A1DB650D8A9DFFC2482', 1, 'Template Viewer', 'KC-AWARD', 'Template Viewer', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1142', 'D69A675BBD5B4390B9CDEE73B58BFE21', 1, 'Award Unassigned', 'KC-AWARD', 'Award Unassigned', '1013', 'Y', to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1103', '73BA94FA7C0543C8AE3FDC9F7A213DF1', 1, 'OSPApprover', 'KC-WKFLW', 'Office of Sponsored Projects Approver', '1', 'Y', to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1104', '8B3406C3EF6A44A493510D61FE988850', 1, 'PrimaryInvestigator', 'KC-WKFLW', 'Proposal Primary Investigator', '1014', 'Y', to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1105', 'FC3EF40FCB284250BAA9FD36404E95D3', 1, 'CoInvestigator', 'KC-WKFLW', 'Proposal Co-Investigator', '1015', 'Y', to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1106', 'FA2616690401440FA22C9EF4AC82A34B', 1, 'KeyPerson', 'KC-WKFLW', 'Proposal Key Persons', '1016', 'Y', to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1107', 'E53F7FA9FF924C928C6E8CFD88332ED0', 1, 'CustomReviewer', 'KC-WKFLW', 'Proposal Custom Reviewer', '1016', 'Y', to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1108', '60249FF9AAD14C81B48D328F3DC07DCF', 1, 'DepartmentReviewer', 'KC-WKFLW', 'Proposal Departmental Reviewer', '1016', 'Y', to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1109', 'EA87BD3BB1A242818F065DF1E32B2A05', 1, 'IRBApprover', 'KC-WKFLW', 'IRB Approver', '1', 'Y', to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('1', '5ADF18B6D4847954E0404F8189D85002', 1, 'User', 'KUALI', 'This role derives its members from the users in the Principal table. This role gives users high-level permissions to interact with RICE documents and to login to KUALI.', '2', 'Y', to_date('04-11-2008', 'dd-mm-yyyy'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('59', '5BABFACC4F62A8EEE0404F8189D8770F', 1, 'Approve Request Recipient', 'KR-WKFLW', 'This role derives its members from users with that have received an action request for a given document.', '42', 'Y', to_date('14-11-2008', 'dd-mm-yyyy'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('60', '5BABFACC4F63A8EEE0404F8189D8770F', 1, 'Initiator', 'KR-WKFLW', 'This role derives its members from the initiator listed within the route log of a given document.', '43', 'Y', to_date('14-11-2008', 'dd-mm-yyyy'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('61', '5BABFACC4F64A8EEE0404F8189D8770F', 1, 'Initiator or Reviewer', 'KR-WKFLW', 'This role derives its members from the initiator and action request recipients listed within the route log of a given document.', '43', 'Y', to_date('14-11-2008', 'dd-mm-yyyy'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('97', '67F145466EB09160E0404F8189D86771', 1, 'Non-Ad Hoc Approve Request Recipient', 'KR-WKFLW', 'This role derives its members from users with an Approval action request (that was not generated via the ad-hoc recipients tab) in the route log of a given document.', '42', 'Y', null);
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('63', '5B31640F0105ADF1E0404F8189D84647', 1, 'Technical Administrator', 'KR-SYS', 'This role can take superuser actions and blanket approve RICE documents as well as being able to modify and assign permissions, responsibilities and roles belonging to the KR namespaces.', '1', 'Y', to_date('08-11-2008', 'dd-mm-yyyy'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('66', '5BABFACC4F61A8EEE0404F8189D8770F', 1, 'Document Editor', 'KR-NS', 'This role derives its members from users with the Edit Document permission for a given document type.,', '45', 'Y', to_date('14-11-2008', 'dd-mm-yyyy'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('67', '5BABFACC4F65A8EEE0404F8189D8770F', 1, 'Router', 'KR-WKFLW', 'This role derives its members from the user who took the Complete action on a given document.', '43', 'Y', to_date('14-11-2008', 'dd-mm-yyyy'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('83', '606763510FBF96D3E0404F8189D857A2', 1, 'Document Opener', 'KR-NS', 'This role derives its members from users with the Open Document permission for a given document type.,', '60', 'Y', to_date('13-01-2009', 'dd-mm-yyyy'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('88', '6102F3FA08CE45CFE0404F8189D8317E', 1, 'Acknowledge Request Recipient', 'KR-WKFLW', 'This role derives its members from users with an acknowledge action request in the route log of a given document.', '42', 'Y', to_date('21-01-2009', 'dd-mm-yyyy'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('89', '6102F3FA08CF45CFE0404F8189D8317E', 1, 'FYI Request Recipient', 'KR-WKFLW', 'This role derives its members from users with an FYI action request in the route log of a given document.', '42', 'Y', to_date('21-01-2009', 'dd-mm-yyyy'));
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('90', '61815E6C62D0B647E0404F8189D873B3', 1, 'System User', 'KR-SYS', 'This role represents the KR System User, that is the user ID RICE uses when it takes programmed actions.', '1', 'Y', null);
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND, LAST_UPDT_DT)
values ('95', '67F145466E8B9160E0404F8189D86771', 1, 'Document Initiator', 'KR-SYS', 'This role derives its members from users with the Initiate Document permission for a given document type.', '66', 'Y', null);

-- create new role: Institutional Proposal Maintainer
insert into KRIM_ROLE_T (ROLE_ID, OBJ_ID, VER_NBR, ROLE_NM, NMSPC_CD, DESC_TXT, KIM_TYP_ID, ACTV_IND)
values ('96', sys_guid(), 1, 'Institutional Proposal Maintainer', 'KC-IP', 'Maintain Institutional Proposals', (select KIM_TYP_ID from KRIM_TYP_T where NM='UnitHierarchy'), 'Y');

insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1490', 1, '847B746162AB4699BA6C070F2BED4635', '1114', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1491', 1, '25D73E277FD649CB85B0E512552A6DEC', '1114', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1492', 1, '01A9D779841D4BF2B79E350461970FBA', '1114', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1493', 1, 'E1CFE95D00CF40F58B8ECAA10429A2E2', '1114', '000000004', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1494', 1, 'C304C8803E0C4C60A15F353796AFB88F', '1114', '000000004', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1495', 1, '5B41A8DB5D494AA5AA966A4325D37524', '1114', '000000004', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1496', 1, '45E4CBF5F06C4414B9FF183E2865DB3C', '1114', '000000005', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1497', 1, '3407C9B9D33A4B118EA0F638D63AC2D8', '1114', '000000005', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1498', 1, 'B3E7166C0FB34B37B29E2C558ED6E060', '1114', '000000005', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1499', 1, 'E0CD03C8365F4AE998EE5172DA2D70E6', '1114', '000000006', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1500', 1, 'E97DE1D80AC543BC90D55D7375937962', '1114', '000000006', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1501', 1, 'FDBD18EA6CE54CB3B81A7CC65DDA6939', '1114', '000000006', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1502', 1, 'DB35E8DF4CC94C3594C4E01F4800F1C5', '1114', '000000001', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1503', 1, '457C4984AEB34CAA98B1A22AB1AD9101', '1114', '000000001', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1504', 1, 'F3865292DD3F4B0CB6AE5FC6E9A9F401', '1114', '000000001', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1505', 1, '15063A8CEBA444BFB179431F430E5178', '1114', '000000002', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1506', 1, '0E60B3AA26234EAB86A690F53BCC7B7F', '1114', '000000002', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1507', 1, '3DDAB38563F44850A6554E48CC2127F4', '1114', '000000002', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1508', 1, '68100CE8A36A417CA5FD063425892A8F', '1114', '10000000001', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1509', 1, '423F61CCA13D4AC69AD42ECF4099EDC4', '1114', '10000000001', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1510', 1, '89A7C8074B584F9C980A61726C62499E', '1114', '10000000001', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1511', 1, '70E11F8A746F4931BC94D2B03A2F2776', '1114', '10000000003', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1512', 1, 'BBC6AAF14B95497C84D978182674690B', '1114', '10000000006', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1513', 1, '3E9847111B134C5DA165466B13310EE9', '1114', '10000000005', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1514', 1, 'D0C454AAD6A240CE87E9105A41A32E6A', '1114', '10000000005', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1515', 1, 'E217F5447AAF4985AA10930EFDB6FDBE', '1114', '10000000005', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1516', 1, '633094B27F6549718E5E70C4DE36940A', '1114', '10000000004', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1517', 1, 'E33A0449787E4F179FABAD80F695B5F6', '1114', '10000000007', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1518', 1, 'EE7179CC31644C98B186586A6B6958E1', '1114', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1519', 1, '3F260346A4FA412182FD1C34ADA64061', '1114', '000000004', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1520', 1, 'D9F857F4FB8F4D26A7ECDF3ECBCC80E9', '1114', '000000005', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1521', 1, 'ABACD800E6914D3285ED02D43EF60561', '1114', '000000006', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1522', 1, 'C5D9687DF34C4DA7B7D76D1D0CB2ABD9', '1114', '000000001', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1523', 1, '477DD8FD7ABB487CB618208B40CCB06D', '1114', '000000002', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1524', 1, '5BF21772693642AC8EE9A6A086F34476', '1114', '10000000001', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1525', 1, '45123CFAB0A944C8B30F0374BC1112AD', '1114', '2', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1526', 1, 'D6BD9F2976BD42EBB9D71D43B5E09251', '1114', '2', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1527', 1, 'E04F2395435F41AC8AE3F2D70D0B01D8', '1114', '3', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1528', 1, 'E7622699892E4517995685AC263B81E8', '1114', '3', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1529', 1, '0EFCFD63FEFA4CC299A2CC1245FC3B8F', '1114', '3', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1530', 1, '739DA6C9D50243A08EF4842F9AB3C4FC', '1114', '4', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1531', 1, '5CB7919BF7294A6C84A7CD6BDC82134B', '1114', '4', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1532', 1, '9B327A74EEB8420F9C0DA9D88EB91DA8', '1114', '4', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1533', 1, '9D09611E5CE844C19F8D20F968912AA6', '1114', '5', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1534', 1, '5190BFAB17F94CD0BB63AB71071E7178', '1114', '5', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1535', 1, 'EE81453A1ECA49A1A84FC35F90168DF5', '1114', '5', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1536', 1, '3D2C60B613114D94A4208B773A913A25', '1114', '6', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1537', 1, '22B563AA1F0447FCA6C4D8BDCB11C577', '1114', '6', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1538', 1, '70819A0CD98746A18F91C9E1C4B79EDF', '1114', '6', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1539', 1, '8DEA7464D4E74D30B96CA91753A0FDBD', '1114', '2', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1540', 1, '76EC3CC14F7A4FFDA3778939ACDAFD3B', '1121', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1541', 1, 'C2FB26B2EE524DC3A02AC9EE02BF82D6', '1121', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1542', 1, 'DE19E738327F4BFF8768E7F40E59B079', '1121', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1543', 1, '795A2B4B912C42D39D12B54E2E74808C', '1122', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1544', 1, '036372D2615640A5B152C777202E975D', '1125', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1545', 1, 'CC41524234B2489FAAC0E673579CCCA8', '1126', '10000000001', 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1546', 1, 'FBFEC988421B4A208B71B99D6D8E0824', '1130', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1288', 1, '48F2597F74A64A1F8014EEBF2EBE542E', '1103', '10000000048', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1289', 1, '36B89E91B9DA4E8AB49B3980B77CAB27', '1103', '10000000058', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1290', 1, 'DBBEF76263A24CB190721D0754E18CEA', '1107', '10000000035', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1291', 1, '984DAA1C7AFA4ED2A3AF266ECA1FA156', '1108', '10000000035', 'P', null, null, to_date('07-10-2009 15:26:09', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1292', 1, '5A40BA81572644E7B6E1266DD9272346', '1109', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1293', 1, '17499E17140149819ACC9860D4F70B83', '1109', '10000000001', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1294', 1, '8316FBE5B46E44ED8EC37E4E94728C14', '1109', '10000000003', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1295', 1, 'AD86C8B168744D8EA6EE99540813CE5B', '1109', '10000000004', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1296', 1, '2E322FD6271B465B9B470AE462275D58', '1109', '10000000005', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1297', 1, 'F915FCA79D274F22A4392B19C9F34B1E', '1109', '10000000006', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1298', 1, '75D8907D00BC40C987D72D19C359E6C4', '1109', '10000000007', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1299', 1, '4E886C36DAF14F2F972B42E18BEA89A7', '1109', '10000000008', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1300', 1, 'F11E9A41618548B4ADB44ED50AB861E3', '1109', '10000000009', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1301', 1, '9415B3EF2CDB4600AAAE426D81FE75D3', '1109', '10000000010', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1302', 1, 'C4815D155897497B890305D14FFC6DDA', '1109', '10000000011', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1303', 1, 'AAF92ED5D94447D4929B5720A77F97D4', '1109', '10000000012', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1304', 1, '96CEB5B6C7D84C75B6E85FBAE4EE90A9', '1109', '10000000013', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1305', 1, 'FBC1906C0B764FBA85D98C2D2BFD9752', '1109', '10000000014', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1306', 1, '6860B2EFA2CD4199BEC30BEFF4344F92', '1109', '10000000015', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1307', 1, 'E6CAB8EAE8734E78A71A649919E579B5', '1109', '10000000016', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1308', 1, '618FFB5FF6304CA8842FC91092315B4C', '1109', '10000000017', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1309', 1, 'DEC086B399944E928250BF91DAB329CA', '1109', '10000000018', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1310', 1, 'FFD792E36D564F6A9A5E78CF94BE446D', '1109', '10000000019', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1311', 1, 'EDAC773867CA4584BB0602A449441AA4', '1109', '10000000020', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1312', 1, '798B3FE1246B4F5786F1FF1976DE9F62', '1109', '10000000021', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1313', 1, '242A1EB8B908404886D3103A26819AD1', '1109', '10000000022', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1314', 1, '38AE817D6D6E4582B4A7EE20F4ABF5DB', '1109', '10000000023', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1315', 1, '5E824900CB7E4D9B854452A8739236A0', '1109', '10000000024', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1316', 1, 'A4493079821248F9A670457886B9998E', '1109', '10000000025', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1317', 1, '2717D97AF4BE4D969331227DFC6DF13D', '1109', '10000000026', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1318', 1, '98B32336365848C1859BAD85F1ECC06F', '1109', '10000000027', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1319', 1, 'F3DBB1D04A3E43A0BC783F7F6E4AF0E6', '1109', '10000000028', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1320', 1, 'CA2534C8094340E994BCE7B9B5E61698', '1109', '10000000029', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1321', 1, '07252B74F68E4D24910DDD5016FE3D50', '1109', '10000000030', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1322', 1, '65A36B90BCAA40F4A6ECC09C14E4FEE6', '1109', '10000000031', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1323', 1, '62788DDB113643C8865CFFAFE7FA7480', '1109', '10000000032', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1324', 1, '68EC27B57EB745138D4EFE73471AE492', '1109', '10000000033', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1325', 1, '54A5546865D141FC9BE3ACA651E8D2A1', '1109', '10000000034', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1326', 1, '1A6D1646451E4569B9C31B29D0A4A278', '1109', '10000000035', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1327', 1, '1044CB1F295540E3AF59BC36BADFB05E', '1109', '10000000036', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1328', 1, '0DB7AE0440F74854BD86AC3CE8847A85', '1109', '10000000037', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1329', 1, '41E79F5C1ACF4E5CA879F407079A8370', '1109', '10000000038', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1330', 1, '177C43B0D28E4C7FB9881E2BFB22571F', '1109', '10000000039', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));

insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1331', 1, '7AF03C47C4484E82B31027BF319C8C9C', '1109', '10000000040', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1332', 1, 'B689870B65934B3998F27041C1648E6F', '1109', '10000000041', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1333', 1, '6FEBEAAC1B52437DA399E030CA178654', '1109', '10000000042', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1334', 1, 'D7164418E6D54DD693A427DED61B9007', '1109', '10000000043', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1335', 1, 'D2071E74D57C4F37BD1A0E1ABAD32628', '1109', '10000000044', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1336', 1, 'E0D22376B5B144039D16E0260C88D7DB', '1109', '10000000045', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1337', 1, '8FE59553D8114F38970C05C9D107854F', '1109', '10000000046', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1338', 1, '323C488EB3AF4BBCA1617567330A54A8', '1109', '10000000047', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1339', 1, '15D9C783BDC04D80BB5EF7F20FA998EC', '1109', '10000000048', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1340', 1, '549264602396433BBE6942B8D1B88FD4', '1109', '10000000049', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1341', 1, '91D77555199F43A9AF4E10BFBED8DA9A', '1109', '10000000050', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1342', 1, '1492C40EE94B4DABB8B0AFEFBB6A9F21', '1109', '10000000051', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1343', 1, '5C45E441CC8C47B19FA10C4DAC650A1A', '1109', '10000000052', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1344', 1, '454D2E239D024BFBAECAE221737EDDF9', '1109', '10000000053', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1345', 1, 'ED19C22054C84591992234EE09D35EC6', '1109', '10000000054', 'P', null, null, to_date('07-10-2009 15:26:10', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1283', 1, 'D0B057F8E7B949EFA94AE8CDA47DE41B', '63', 'admin', 'P', null, null, to_date('08-12-2008', 'dd-mm-yyyy'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1282', 1, '5B4B421E43857717E0404F8189D821F7', '90', '1', 'P', null, null, null);

-- assign Institutional Proposal Maintainer role to quickstart
insert into KRIM_ROLE_MBR_T (role_mbr_id, ver_nbr, obj_id, role_id, mbr_id, mbr_typ_cd) 
values ('1284', 1, sys_guid(), (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PRNCPL_ID from KRIM_PRNCPL_T where PRNCPL_NM='quickstart'), 'P');

insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10002', '77BC20590A50319DE0404F8189D80DB4', 1, '1119', '1199', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10003', '77BC20590A53319DE0404F8189D80DB4', 1, '1120', '1200', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10004', 'A5D1A7D09E7D412085A6F59FA72BE834', 1, '1114', '1000', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10005', 'E5E25615EBB5454DBD55D930AF9E3D63', 1, '1115', '1008', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10006', 'D6C953768CFB42ABA5350724D9C1496F', 1, '1115', '1021', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10007', '4C8F98FD69844EA291238BC2E32A87F6', 1, '1115', '1010', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10008', '90BF899809334D389DB580FF9C58CB4C', 1, '1115', '1023', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10009', '3A2EBAB2BF0A4201A57F8DD62A504236', 1, '1115', '1009', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10010', 'E08C3D1580384A7DA7024761A7BBAACA', 1, '1115', '1022', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10011', 'A599DDCA7A604B4ABE1737F2BEB25727', 1, '1115', '1011', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10012', 'C93A44CF61EE4ABF98F979512ECC5791', 1, '1115', '1033', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10013', '01F4B55BDB6646A5BC076C9B00B2EDC5', 1, '1115', '1034', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10014', '69A77DCC02A44130892ABF7FE2669EB9', 1, '1115', '1005', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10015', 'C7C35F40BD5A4141ABF7B870250C7F48', 1, '1115', '1036', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10016', '8E5336F987A74C398F844626F91AE446', 1, '1116', '1008', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10017', 'AD5EB04CA2064E58BD05D573E9542BA0', 1, '1116', '1021', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10018', '243752809FD6460099820CA7A2E22D04', 1, '1116', '1010', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10019', '342F2266BD2741A199FE163A42F2D813', 1, '1116', '1023', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10020', '1D8F740207C445049CE4FD4D494EFFA9', 1, '1116', '1022', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10021', '8AB1F65BDBB64C82943B92595397A475', 1, '1116', '1033', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10022', 'C7C0AD1990B8421A92BC8E8DEF3834B4', 1, '1117', '1008', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10023', '0EC0679410FD43EF98B9F9C13C6FF7C0', 1, '1117', '1021', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10024', 'EE317A71245F44C6BEC42E5369A6641F', 1, '1117', '1023', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10025', '706ACFF03AEA4C1CA09823BA0D989374', 1, '1117', '1009', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10026', 'D963DBDF79454013803CF293BFE9B3F9', 1, '1117', '1022', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10027', 'C998652248AC4231B5C1499106722489', 1, '1117', '1033', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10028', '1D51ED7509D44967A8405A04B359083D', 1, '1118', '1021', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10029', '78DD63A731D540CF8493F996EFB2F9EC', 1, '1118', '1023', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10030', '061145A7C55B48CAB4193E19D3478A28', 1, '1118', '1022', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10031', 'B00C1E1206434652A6148869B25DB6E7', 1, '1118', '1033', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10032', 'D224337273CE43F18D0E3E0B8FA7E238', 1, '1120', '1021', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10033', '48C328BBD75243858E11BF1A270DDB3A', 1, '1120', '1023', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10034', '0E59B55953F544638E84C70839DAD847', 1, '1120', '1022', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10035', '6AAE9A68189E4C8CA3CA34243ECE1499', 1, '1120', '1032', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10036', 'B4A3B2FE3DE64694BCCD40ED1FB3EB43', 1, '1120', '1036', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10037', 'DA618CDB4CBE4C2AA989C057DB7674EC', 1, '1121', '1021', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10038', '51172E7BD7E241738F01AAF7CBEC51A1', 1, '1121', '1023', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10039', 'E8F972EE76094660B224D7871D518983', 1, '1121', '1022', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10040', '4BDAE8B77B9C42748BEC5F9065E490A6', 1, '1121', '1032', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10041', 'BF56A26B9B714DA29FBA5B5ED8020857', 1, '1121', '1033', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10042', '27B8D50C14874757B57255D634013782', 1, '1121', '1035', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10043', '94931BE9226647D08448A6964698F01D', 1, '1122', '1001', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10044', '452A9A7003974F6CA54085BCB2805205', 1, '1123', '1012', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10045', 'E0683185407E4B059F1A8EC3A41AFAD1', 1, '1123', '1024', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10046', '2FFB9E20840D4C958C755379E82AA4E1', 1, '1123', '1013', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10047', '59855503213F4AC981F3FE44AEE11BBF', 1, '1123', '1041', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10048', '27D59E7579DE4A829F09DD60F044265A', 1, '1123', '1037', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10049', 'A157539E73C14D4292F247CA8DA86D08', 1, '1123', '1038', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10050', 'B5CF16792634486389998AE6AFE158F3', 1, '1123', '1006', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10051', '862C70B962E64B429014C8B3F3D1F148', 1, '1123', '1048', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10052', '06B6DD27AB0F4B06BB55013E61943655', 1, '1124', '1024', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10053', '68EEF8233400445BB8F81955D8DB9849', 1, '1125', '1012', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10054', 'D4E6F1B5E80D4246A213FF3D17513B66', 1, '1125', '1002', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10055', '549D8966C2EF46E0987F8CB1A2DE7598', 1, '1125', '1039', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10056', 'EC7C6F8622EF4874A22C78EB6824D83F', 1, '1125', '1043', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10057', '8509B1FF7DBE4BC398DA7F0705903ED4', 1, '1125', '1044', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10058', 'B3D58707E495474ABBA747E80B7002F7', 1, '1125', '1045', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10062', '2D7961CE9AD94FE1A4B20B3FEC62DB38', 1, '1125', '1016', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10063', '2A3063D7891F4D7CA9BA0B59866D50CE', 1, '1125', '1017', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10064', 'E578F45D817A479FA940DABC24BE5D75', 1, '1125', '1040', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10065', 'D110D5EE7FAA4E3BA1845A60441DBCC9', 1, '1125', '1014', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10066', '25BF6A3240214616AFA0A7C8861214E8', 1, '1125', '1015', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10067', '71D8F44B8D854012859CAE5A575F632A', 1, '1125', '1050', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10068', 'CD484D9FBD1D475B819DC5BE018F8BE2', 1, '1125', '1042', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10069', '6E121E809978459BA82EA81657547E6C', 1, '1125', '1028', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10070', 'C755C2F6DBFF4B90911BBA9470A622BA', 1, '1125', '1026', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10071', '0DB432B1207B4BDFA98488FFF693076F', 1, '1125', '1046', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10072', '7DDE8D8C922B4283BCAE4F31D5D07598', 1, '1125', '1025', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10073', '996DBB638E8E4BB6AE949DF03FB38260', 1, '1125', '1027', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10074', 'DA8E5CE9C183438FAF5CB6C9300A5B1E', 1, '1126', '1028', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10075', '44F51CFBCE8A41899B599E1BB60F2C50', 1, '1126', '1026', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10076', 'FB1A2D9337694D0BAAD8C435C3802CD5', 1, '1126', '1046', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10077', '088C113DE55349E79D03540317B56C6C', 1, '1126', '1025', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10078', '03FB39A872C4408EA6DCD42ABC45CE70', 1, '1126', '1027', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10079', '1664F98441AA494DAAEBB2EB30320698', 1, '1129', '1004', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10080', '8ACAC3313D9C465CA84FE0A295304918', 1, '1129', '1047', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10081', '9CDC448159FC4A5587714FC1324B13EB', 1, '1129', '1051', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10082', 'DFC934DC6D1C4E9ABAAAD47B6CBC1367', 1, '1130', '1003', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10083', '64D45E8951BF464C9E976DE55A41519F', 1, '1130', '1018', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10084', 'A29EB7C01D774E29823F331E2D55EFDD', 1, '1130', '1029', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10085', 'EACCE09358E948FA9B6DB3DE05CB72E3', 1, '1131', '1019', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10086', '435E9381601646A482FABC5B21F7EC47', 1, '1131', '1007', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10087', '26BBC43EA08F490F8DEB9812049B90B7', 1, '1133', '1049', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10088', '087638055E884AB2A340FA809995AF1C', 1, '1134', '1019', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10089', 'E80B2169AF024057850385173F0D46E0', 1, '1134', '1007', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10090', '73274AD38A5545918E0EEE81E7406A22', 1, '1134', '1030', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10091', '24429CF231B34B9DB9D80D5F729E0FC0', 1, '1135', '1020', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10092', 'E3B384F706314985B1A81B5595BCA621', 1, '1136', '1019', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10093', '3EB34FE157DB48D8AA792C744915F83E', 1, '1137', '1029', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10094', '3A12D8BBC9A4409F990CE6AA2652B5D6', 1, '1138', '1030', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10095', 'A65A16A16C844F3585FC4EB4D287925E', 1, '1139', '1031', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('183', '5C27A267EF5C7417E0404F8189D830A9', 1, '63', '140', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('184', '5C27A267EF5D7417E0404F8189D830A9', 1, '63', '141', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('185', '5C27A267EF5E7417E0404F8189D830A9', 1, '63', '142', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('186', '5C27A267EF5F7417E0404F8189D830A9', 1, '63', '143', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('187', '5C27A267EF607417E0404F8189D830A9', 1, '63', '144', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('188', '5C27A267EF617417E0404F8189D830A9', 1, '63', '145', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('189', '5C27A267EF627417E0404F8189D830A9', 1, '1', '146', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('190', '5C27A267EF637417E0404F8189D830A9', 1, '63', '147', 'Y');

insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('191', '5C27A267EF647417E0404F8189D830A9', 1, '63', '148', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('193', '5C27A267EF667417E0404F8189D830A9', 1, '1', '149', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('194', '5C27A267EF677417E0404F8189D830A9', 1, '63', '150', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('195', '5C27A267EF687417E0404F8189D830A9', 1, '63', '151', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('196', '5C27A267EF697417E0404F8189D830A9', 1, '63', '152', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('197', '5C27A267EF6A7417E0404F8189D830A9', 1, '63', '153', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('199', '5C27A267EF6C7417E0404F8189D830A9', 1, '63', '154', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('200', '5C27A267EF6D7417E0404F8189D830A9', 1, '63', '155', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('201', '5C27A267EF6E7417E0404F8189D830A9', 1, '1', '156', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('206', '5C27A267EF737417E0404F8189D830A9', 1, '1', '161', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('207', '5C27A267EF747417E0404F8189D830A9', 1, '1', '162', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('208', '5C27A267EF757417E0404F8189D830A9', 1, '63', '163', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('210', '5C27A267EF777417E0404F8189D830A9', 1, '63', '164', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('211', '5C27A267EF787417E0404F8189D830A9', 1, '1', '165', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('212', '5C27A267EF797417E0404F8189D830A9', 1, '66', '167', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('213', '5C27A267EF7A7417E0404F8189D830A9', 1, '66', '168', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('214', '5C27A267EF7B7417E0404F8189D830A9', 1, '60', '290', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('251', '70086A2DF17E62E4E0404F8189D863CD', 1, '97', '181', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('216', '5C27A267EF7D7417E0404F8189D830A9', 1, '59', '170', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('218', '5C27A267EF7F7417E0404F8189D830A9', 1, '89', '172', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('219', '5C27A267EF807417E0404F8189D830A9', 1, '88', '173', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('220', '5C27A267EF817417E0404F8189D830A9', 1, '1', '174', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('226', '5C27A267EF877417E0404F8189D830A9', 1, '60', '180', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('227', '5C27A267EF887417E0404F8189D830A9', 1, '59', '181', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('229', '5C27A267EF8A7417E0404F8189D830A9', 1, '63', '183', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('512', '606763510FC596D3E0404F8189D857A2', 1, '61', '259', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('516', '606763510FC896D3E0404F8189D857A2', 1, '83', '261', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('552', '61815E6C62D3B647E0404F8189D873B3', 1, '90', '290', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('519', '606763510FD096D3E0404F8189D857A2', 1, '63', '264', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('550', '611BE30E404F5818E0404F8189D801C2', 1, '63', '289', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('701', 'C7C5063258494135A27DFDA4868F1D8B', 1, '63', '701', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('702', '6F5D842F42DC4018B6D9E8909A841062', 1, '63', '702', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('703', '70D86205B39A411F8A179555254E3737', 1, '63', '703', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('707', 'B27306E137C94947BA8AFFD2892722D8', 1, '63', '707', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('714', 'C1CFC68930134585835995DDE651B2C7', 1, '63', '714', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('719', '4B02BCD0AA764732997C77D139300784', 1, '63', '719', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('720', '7B920FA6145B47F6BF1B67B8EF3E96F4', 1, '63', '720', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('721', 'A2794759AE9E47CB970DEABA625EA64C', 1, '63', '721', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('730', '05993B68C8034FD2A758E3441C6AD961', 1, '1', '161', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('731', '25F3589FEFFE4FFCBB4C0577AD722538', 1, '1', '162', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('732', '5AFD381C59B34E9D9BA26B70A739C9A2', 1, '1', '163', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('808', '606763510FDD96D3E0404F8189D857A2', 1, '63', '801', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('809', '606763510FDE96D3E0404F8189D857A2', 1, '63', '802', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('810', '606763510FDF96D3E0404F8189D857A2', 1, '63', '803', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('811', '606763510FE096D3E0404F8189D857A2', 1, '63', '807', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('814', '606763510FED96D3E0404F8189D857A2', 1, '63', '814', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('819', '606763510FEE96D3E0404F8189D857A2', 1, '63', '819', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('820', '606763510FEF96D3E0404F8189D857A2', 1, '63', '820', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('821', '606763510FF096D3E0404F8189D857A2', 1, '63', '821', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('826', '606763510FF196D3E0404F8189D857A2', 1, '1', '166', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('827', '606763510FF596D3E0404F8189D857A2', 1, '63', '265', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('828', '606763510FF696D3E0404F8189D857A2', 1, '63', '140', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('829', '606763510FF796D3E0404F8189D857A2', 1, '63', '141', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('830', '606763510FF896D3E0404F8189D857A2', 1, '63', '142', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('831', '606763510FF996D3E0404F8189D857A2', 1, '63', '143', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('832', '606763510FFA96D3E0404F8189D857A2', 1, '63', '144', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('833', '606763510FFB96D3E0404F8189D857A2', 1, '63', '145', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('835', '607A7155BC6F864EE0404F8189D85925', 1, '63', '830', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('836', '607A7155BC72864EE0404F8189D85925', 1, '63', '831', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('578', '6314CC58CF5BB7B5E0404F8189D84439', 1, '63', '306', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('618', '662384B381BC67A1E0404F8189D868A6', 1, '83', '332', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('616', '662384B381C067A1E0404F8189D868A6', 1, '83', '333', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('617', '662384B381C467A1E0404F8189D868A6', 1, '66', '334', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('683', '74382C90B075049FE0404F8189D84C5D', 1, '59', '334', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('838', '5C27A267EF6D7417E0404F8189D830AA', 1, '63', '833', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('839', '5C27A267EF6D7417E0404F8189D830AB', 1, '63', '834', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('840', '5C27A267EF6D7417E0404F8189D830AC', 1, '63', '835', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('841', '5C27A267EF6D7417E0404F8189D830AD', 1, '63', '835', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('230', '5C27A267EF5C7417E0404F8189D830AA', 1, '63', '378', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('850', '70086A2DF17C62E4E0404F8189D863CD', 1, '63', '307', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('250', '70086A2DF17D62E4E0404F8189D863CD', 1, '95', '156', 'Y');

insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10096', '77BC20590A50319DE0404F8189D10096', 1, '1125', '1201', 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10097', '77BC20590A53319DE0404F8189D10097', 1, '1126', '1202', 'Y');

insert into krim_role_perm_t (role_perm_id, obj_id, ver_nbr, role_id, perm_id, actv_ind)
 values ('10098', sys_guid(), '1',
   (select role_id from krim_role_t t where t.role_nm = 'User' and t.nmspc_cd = 'KUALI'),
   (select perm_id from krim_perm_t u where u.nm = 'Open Document' and u.nmspc_cd = 'KC-SYS'),
   'Y');

insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10099', '77BC20590A53319DE0404F8189D10098', 1, '1120', (select PERM_ID from KRIM_PERM_T where NM='Create Proposal Log'), 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10100', '77BC20590A53319DE0404F8189D10099', 1, '1120', (select PERM_ID from KRIM_PERM_T where NM='Edit Proposal Log'), 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10101', '77BC20590A53319DE0404F8189D10100', 1, '1120', (select PERM_ID from KRIM_PERM_T where NM='Save Proposal Log'), 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10102', '77BC20590A53319DE0404F8189D10101', 1, '1120', (select PERM_ID from KRIM_PERM_T where NM='Submit Proposal Log'), 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10103', '77BC20590A53319DE0404F8189D10102', 1, '1120', (select PERM_ID from KRIM_PERM_T where NM='Open Proposal Log'), 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10104', '77BC20590A53319DE0404F8189D10103', 1, '1120', (select PERM_ID from KRIM_PERM_T where NM='Cancel Proposal Log'), 'Y');

insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10105', '77BC20590A53319DE0404F8189D10104', 1, '1120', (select PERM_ID from KRIM_PERM_T where NM='Create Institutional Proposal'), 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10106', '77BC20590A53319DE0404F8189D10105', 1, '1120', (select PERM_ID from KRIM_PERM_T where NM='Edit Institutional Proposal'), 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10107', '77BC20590A53319DE0404F8189D10106', 1, '1120', (select PERM_ID from KRIM_PERM_T where NM='Save Institutional Proposal'), 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10108', '77BC20590A53319DE0404F8189D10107', 1, '1120', (select PERM_ID from KRIM_PERM_T where NM='Submit Institutional Proposal'), 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10109', '77BC20590A53319DE0404F8189D10108', 1, '1120', (select PERM_ID from KRIM_PERM_T where NM='Open Institutional Proposal'), 'Y');
insert into KRIM_ROLE_PERM_T (ROLE_PERM_ID, OBJ_ID, VER_NBR, ROLE_ID, PERM_ID, ACTV_IND)
values ('10110', '77BC20590A53319DE0404F8189D10109', 1, '1120', (select PERM_ID from KRIM_PERM_T where NM='Cancel Institutional Proposal'), 'Y');

-- assign institutional proposal (mod) & IP permissions (mod) to Modify Institutional Proposal role
insert into KRIM_ROLE_PERM_T values ('10125', sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Create Institutional Proposal'), 'Y');
insert into KRIM_ROLE_PERM_T values ('10126', sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Edit Institutional Proposal'), 'Y');
insert into KRIM_ROLE_PERM_T values ('10127', sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Save Institutional Proposal'), 'Y');
insert into KRIM_ROLE_PERM_T values ('10128', sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Submit Institutional Proposal'), 'Y');
insert into KRIM_ROLE_PERM_T values ('10129', sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Open Institutional Proposal'), 'Y');
insert into KRIM_ROLE_PERM_T values ('10130', sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Cancel Institutional Proposal'), 'Y');
insert into KRIM_ROLE_PERM_T values ('10131', sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Create Intellectual Property Review'), 'Y');
insert into KRIM_ROLE_PERM_T values ('10132', sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Edit Intellectual Property Review'), 'Y');
insert into KRIM_ROLE_PERM_T values ('10133', sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Save Intellectual Property Review'), 'Y');
insert into KRIM_ROLE_PERM_T values ('10134', sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Submit Intellectual Property Review'), 'Y');
insert into KRIM_ROLE_PERM_T values ('10135', sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Open Intellectual Property Review'), 'Y');
insert into KRIM_ROLE_PERM_T values ('10136', sys_guid(), 1, (select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer'), (select PERM_ID from KRIM_PERM_T where NM='Cancel Intellectual Property Review'), 'Y');

insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('1009', '9E8C6BBF7C38443088C2B420363C5200', 1, 'a', '1017', '1007', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('1010', '8067AED0B0864D3C8ADB30B79AB5492F', 1, 'b', '1017', '13', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('1011', '6D56C1DD6A0143C584AF160EF5AF4D3B', 1, 'a', '1018', '1008', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('1012', '754CF36D2BA1454893051682C0913E23', 1, 'b', '1018', '13', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('1033', '5C36DB3EB5DA403F869B22050C4049B8', 1, 'A', '1040', '1034', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('1034', 'B8E786101AF14DAE891BAF1618F8090A', 1, 'A', '1041', '1035', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('1035', 'E34ADAC488A64952A2E3219ADBA50780', 1, 'A', '1042', '1036', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('1036', '070028B9213E4BDFB9C150DE6B4F8BE1', 1, 'A', '1043', '1037', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('1037', 'E0D5534DBA8D4EEB8A05140475437F09', 1, 'A', '1044', '1038', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('1006', '813AFE8DC3FF4EAA82D8EC6B63996402', 1, 'A', '1012', '1005', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('1007', '9ABD9F3AC2714C01AB5E7C685812A3AB', 1, 'A', '1013', '1005', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('1008', 'B9D2C1DF5FF04E4E8A12F2AC09965012', 1, 'A', '1013', '1006', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('1', '5ADF18B6D4AD7954E0404F8189D85002', 1, 'a', '3', '13', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('10', '5ADF18B6D4C87954E0404F8189D85002', 1, 'c', '8', '15', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('101', '603B735FA6BBFE21E0404F8189D8083B', 1, 'a', '56', '13', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('102', '603B735FA6C2FE21E0404F8189D8083B', 1, 'b', '56', '7', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('103', '603B735FA6BEFE21E0404F8189D8083B', 1, 'a', '57', '5', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('104', '603B735FA6BFFE21E0404F8189D8083B', 1, 'b', '57', '44', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('107', '60432A73A6A39F65E0404F8189D86AA4', 1, 'a', '54', '13', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('108', '606763510FBD96D3E0404F8189D857A2', 1, 'a', '59', '13', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('109', '606763510FBE96D3E0404F8189D857A2', 1, 'b', '59', '8', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('11', '5ADF18B6D4C97954E0404F8189D85002', 1, 'b', '8', '16', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('12', '5ADF18B6D4CE7954E0404F8189D85002', 1, 'a', '9', '13', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('14', '5ADF18B6D4D07954E0404F8189D85002', 1, 'b', '9', '9', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('15', '5ADF18B6D4D87954E0404F8189D85002', 1, 'a', '10', '4', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('16', '5ADF18B6D4D97954E0404F8189D85002', 1, 'b', '10', '5', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('17', '5ADF18B6D4DD7954E0404F8189D85002', 1, 'a', '11', '5', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('18', '5ADF18B6D4DE7954E0404F8189D85002', 1, 'b', '11', '6', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('19', '5ADF18B6D4E47954E0404F8189D85002', 1, 'a', '12', '4', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('2', '5ADF18B6D4B67954E0404F8189D85002', 1, 'a', '4', '14', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('20', '5ADF18B6D4E57954E0404F8189D85002', 1, 'b', '12', '2', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('21', '5ADF18B6D4E87954E0404F8189D85002', 1, 'a', '13', '3', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('22', '5ADF18B6D4EB7954E0404F8189D85002', 1, 'a', '14', '10', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('23', '5ADF18B6D4EE7954E0404F8189D85002', 1, 'a', '15', '4', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('24', '5ADF18B6D4EF7954E0404F8189D85002', 1, 'b', '15', '1', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('25', '5ADF18B6D4F37954E0404F8189D85002', 1, 'a', '16', '4', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('26', '5ADF18B6D4F47954E0404F8189D85002', 1, 'b', '16', '5', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('27', '5ADF18B6D4F57954E0404F8189D85002', 1, 'c', '16', '11', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('29', '5ADF18B6D4FA7954E0404F8189D85002', 1, 'a', '18', '4', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('3', '5ADF18B6D4B97954E0404F8189D85002', 1, 'a', '5', '13', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('30', '5ADF18B6D4FB7954E0404F8189D85002', 1, 'b', '18', '18', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('31', '5ADF18B6D4FE7954E0404F8189D85002', 1, 'a', '19', '4', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('32', '5ADF18B6D4FF7954E0404F8189D85002', 1, 'b', '19', '19', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('33', '5ADF18B6D5027954E0404F8189D85002', 1, 'a', '20', '4', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('34', '5ADF18B6D5037954E0404F8189D85002', 1, 'b', '20', '20', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('35', '5ADF18B6D5067954E0404F8189D85002', 1, 'a', '21', '4', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('36', '5ADF18B6D5077954E0404F8189D85002', 1, 'b', '21', '21', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('37', '5ADF18B6D50A7954E0404F8189D85002', 1, 'a', '22', '17', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('4', '5ADF18B6D4BA7954E0404F8189D85002', 1, 'b', '5', '14', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('7', '5ADF18B6D4C17954E0404F8189D85002', 1, 'a', '7', '13', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('72', '5B47A17D07C2E43DE0404F8189D873F1', 1, 'c', '22', '6', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('8', '5ADF18B6D4C27954E0404F8189D85002', 1, 'b', '7', '16', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('80', '5C4970B2B2E18277E0404F8189D80B30', 1, 'c', '7', '40', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('81', '5C4970B2B2E28277E0404F8189D80B30', 1, 'd', '7', '41', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('89', '5C7D997640695002E0404F8189D86F11', 1, 'a', '42', '42', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('9', '5ADF18B6D4C77954E0404F8189D85002', 1, 'a', '8', '13', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('91', '5C7D9976406B5002E0404F8189D86F11', 1, 'a', '43', '42', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('111', '67F145466E909160E0404F8189D86771', 1, 'a', '67', '4', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('112', '67F145466E959160E0404F8189D86771', 1, 'b', '14', '13', 'Y');
--insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
--values ('28', '5ADF18B6D4F87954E0404F8189D85002', 1, 'a', '17', '12', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('95', '5C997D14EAC3FE40E0404F8189D87DC5', 1, 'a', '52', '13', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('96', '5C997D14EAC4FE40E0404F8189D87DC5', 1, 'b', '52', '16', 'Y');
insert into KRIM_TYP_ATTR_T (KIM_TYP_ATTR_ID, OBJ_ID, VER_NBR, SORT_CD, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ACTV_IND)
values ('97', '5C997D14EAC5FE40E0404F8189D87DC5', 1, 'c', '52', '6', 'Y');

--insert into KRNS_NMSPC_T (NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_NMSPC_CD)
--values ('KR-SYS', '13A6C8A1B3C246CAB7E7C21F4AB8D6E1', 1, 'Enterprise Infrastructure', 'Y', null);
--insert into KRNS_NMSPC_T (NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_NMSPC_CD)
--values ('KC-IDM', '0419CEC95A6E46CAA02EC22217DAB876', 1, 'Kuali Coeus KIM IDM & AuthZ', 'Y', null);
--insert into KRNS_NMSPC_T (NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_NMSPC_CD)
--values ('KC-UNT', 'EFD011D89B4440758EDD1A5B37E7EE7C', 1, 'Kuali Coeus - Department', 'Y', null);
--insert into KRNS_NMSPC_T (NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_NMSPC_CD)
--values ('KC-ADM', 'BA78E4A18A4B4082BDBEAB00CEF56828', 1, 'Kuali Coeus-Office of Sponsored Projects', 'Y', null);
--insert into KRNS_NMSPC_T (NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_NMSPC_CD)
--values ('KC-SYS', '280E9FA803AD4CFB9F09D88E2AFBA942', 1, 'Kuali Coeus System', 'Y', null);
--insert into KRNS_NMSPC_T (NMSPC_CD, OBJ_ID, VER_NBR, NM, ACTV_IND, APPL_NMSPC_CD)
--values ('KC-WKFLW', '54F9C5BB46DF4E1B90DB825BCF5A335C', 1, 'KC Workflow Infrastructure', 'Y', null);

insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1560', 1, 'C10BFC3DA54F41C7B3870574BBF587EF', '1114', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1561', 1, 'FA80C332CA7F455288202D987053923C', '1114', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1562', 1, '888231658CF044338A9FC667FBBC72C4', '1114', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1563', 1, '054DAD9010964D87AB9A3114F7DA18DF', '1114', '10000000001', 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1564', 1, 'A5CB50A052444119B4F6AFF605140F55', '1114', '10000000001', 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1565', 1, '06F2296D80C149A2AD51177B5DF5D646', '1114', '10000000001', 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1566', 1, '0C0F411FA1734A388ADF0519ED9DABF5', '1114', '10000000003', 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1567', 1, 'A9EE2D977B5C47798C33F476D4EDFEBF', '1114', '10000000006', 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1568', 1, '2CEE18C1CCFD41B58F1016DEFA4C469A', '1114', '10000000005', 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1569', 1, '7DBF29B069E24DC8957E4BF84A8D2FAF', '1114', '10000000005', 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1570', 1, 'ABB52556B8994494BB4AF79FC1261FEA', '1114', '10000000005', 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1571', 1, 'AA135C8CA1BA41B180B6ADC94527E0E4', '1114', '10000000004', 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1572', 1, 'DDB1502F52854FA4824042A4248B7DEB', '1114', '10000000007', 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1573', 1, 'A4A4FD39BF7F49F5B831DA1E16859011', '1114', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1574', 1, 'BB373D18C2C04ACE8394ECAD5DFFBFEE', '1114', '10000000001', 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1575', 1, 'CAAEEB3252824294AE502631DEA0EE61', '1121', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1576', 1, '0309772432C3472E939C5764D26C6330', '1121', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1577', 1, '9A848B7DFEAC40ACA36A1F8EF1BD3E62', '1121', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1578', 1, '59C232B35F374C0EB225FEEE3BDD2425', '1122', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1579', 1, '756E0C2AA16340C3A98664068CC33775', '1125', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1580', 1, 'B05C982FF5414F5DBDDBB663A1740E66', '1130', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1581', 1, '80314909F2DA47348EF2492CBD3339FC', '1126', '10000000001', 'P', null, null, to_date('01-11-2009 19:41:44', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1590', 1, SYS_GUID(), '1122', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'chew'), 'P', null, null, SYSDATE);
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1591', 1, SYS_GUID(), '1125', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'chew'), 'P', null, null, SYSDATE);
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1592', 1, SYS_GUID(), '1120', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, SYSDATE);

insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1029', 'EA604AC4C6BD419291CC8FADDD75C72B', 1, '1560', '1012', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1030', 'D2F3BD4FE3AE43A48F7964A4E00D3698', 1, '1561', '1012', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1031', '852F009FF7F7420595D7F3309EBBB340', 1, '1562', '1012', '1005', 'IN-CARD');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1032', 'BE60C8A7164D429D88920E5BE98FF999', 1, '1563', '1012', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1033', 'DCE3CE16E4564DB5A02B5679442BD84E', 1, '1564', '1012', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1034', '78E0D1C6CDD4438D85BE47A157FFB9BC', 1, '1565', '1012', '1005', 'IN-CARD');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1035', '0C2EA71BD7F545D2BFC3A4C4C60A6622', 1, '1566', '1012', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1036', '7F838E8732834D899C1CE3B766FCB1AB', 1, '1567', '1012', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1037', '7CFB18C132EE47909AD1ACA544EF5904', 1, '1568', '1012', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1038', '211848C165C8452CBE429440678867CD', 1, '1569', '1012', '1005', 'IN-MDEP');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1039', '2AA1212EDEEB4E05855D6C3C8639C2C4', 1, '1570', '1012', '1005', 'IN-PED');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1040', '4AB00164ABBD48468533A102818D9BDB', 1, '1571', '1012', '1005', 'BL-BL');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1041', 'E4EF7713D711466B9DE15505FCDF2ED8', 1, '1572', '1012', '1005', 'IN-MED');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1042', 'B892E843954144FA9CFAAAE6063C8E89', 1, '1573', '1012', '1005', 'IN-CARR');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1043', 'EEF00DB3972843D298DF150FA69BD04F', 1, '1574', '1012', '1005', 'IN-CARR');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1044', '9B4FE342DC9644109E908371ADBD0103', 1, '1575', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1045', 'D23F9FE9BC3E477794EE27646A939C3E', 1, '1575', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1046', 'C84C0E3AF7EE4CD6B9BA15CDE84261A0', 1, '1576', '1013', '1005', 'BL-IIDC');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1047', '7CD2C1F14DE14500A7AA1818426788CB', 1, '1576', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1048', '1F4751CE1EC6428FA3B94690F1BDCB0A', 1, '1577', '1013', '1005', 'IN-CARD');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1049', 'D8D27B6C682F4571A92A5ECC70E4B977', 1, '1577', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1050', 'B7221EF365BF44778659860966D73207', 1, '1578', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1051', 'FEB4F422585245D2AFDE5EA35E3FE2A2', 1, '1578', '1013', '1006', 'Y');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1052', 'FB71242E1B1B4189AC8C99425C8716C6', 1, '1579', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1053', '9B291412D02240239932421F224A72D1', 1, '1579', '1013', '1006', 'Y');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1054', 'CE432236E5D94B8E88B5D1539093A7A3', 1, '1580', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1055', 'F5AA3D5DE4B149369001955A34C0D21E', 1, '1580', '1013', '1006', 'Y');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1056', 'B21B83CFBAF94C6FA3568F5A5202D79C', 1, '1581', '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1057', '234BDE9A732A43DFB64402532CB686BF', 1, '1581', '1013', '1006', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1058', sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID='1120' and MBR_ID='10000000001'), '1013', '1005', 'N');
insert into KRIM_ROLE_MBR_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, ROLE_MBR_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
values ('1059', sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID='1120' and MBR_ID='10000000001'), '1013', '1006', 'N');

-- qualify role assignment at unit 000001 with descend on
insert into KRIM_ROLE_MBR_ATTR_DATA_T 
values ('1060', sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID=(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer') and MBR_ID=(select PRNCPL_ID from KRIM_PRNCPL_T where PRNCPL_NM='quickstart')), '1013', '1005', '000001');
insert into KRIM_ROLE_MBR_ATTR_DATA_T 
values ('1061', sys_guid(), 1, (select ROLE_MBR_ID from KRIM_ROLE_MBR_T where ROLE_ID=(select ROLE_ID from KRIM_ROLE_T where ROLE_NM='Institutional Proposal Maintainer') and MBR_ID=(select PRNCPL_ID from KRIM_PRNCPL_T where PRNCPL_NM='quickstart')), '1013', '1006', 'Y');

INSERT INTO KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1300', SYS_GUID(), 1, 'Y', SYSDATE);
INSERT INTO KRIM_PRNCPL_T (PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID, OBJ_ID, VER_NBR) 
VALUES ('admin', '1300', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', SYSDATE, '10000000060', SYS_GUID(), 1);
INSERT INTO KRIM_ENTITY_ENT_TYP_T (ENTITY_ID, ENT_TYP_CD, LAST_UPDT_DT, ACTV_IND, OBJ_ID, VER_NBR) 
VALUES ('1300', 'PERSON', SYSDATE, 'Y', SYS_GUID(), 1);
UPDATE KRIM_ROLE_MBR_T T SET T.MBR_ID = '10000000060' WHERE T.MBR_ID = 'admin';

INSERT INTO KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1301', SYS_GUID(), 1, 'Y', SYSDATE);
INSERT INTO KRIM_PRNCPL_T (PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID, OBJ_ID, VER_NBR) 
VALUES ('superuser', '1301', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', SYSDATE, '10000000061', SYS_GUID(), 1);
INSERT INTO KRIM_ENTITY_ENT_TYP_T (ENTITY_ID, ENT_TYP_CD, LAST_UPDT_DT, ACTV_IND, OBJ_ID, VER_NBR) 
VALUES ('1301', 'PERSON', SYSDATE, 'Y', SYS_GUID(), 1);

INSERT INTO KRIM_ENTITY_T (ENTITY_ID, OBJ_ID, VER_NBR, ACTV_IND, LAST_UPDT_DT)
values ('1302', SYS_GUID(), 1, 'Y', SYSDATE);
INSERT INTO KRIM_PRNCPL_T (PRNCPL_NM, ENTITY_ID, PRNCPL_PSWD, ACTV_IND, LAST_UPDT_DT, PRNCPL_ID, OBJ_ID, VER_NBR) 
VALUES ('user4', '1302', 'fK69ATFsAydwQuteang+xMva+Tc=', 'Y', SYSDATE, '10000000062', SYS_GUID(), 1);
INSERT INTO KRIM_ENTITY_ENT_TYP_T (ENTITY_ID, ENT_TYP_CD, LAST_UPDT_DT, ACTV_IND, OBJ_ID, VER_NBR) 
VALUES ('1302', 'PERSON', SYSDATE, 'Y', SYS_GUID(), 1);

insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1600', 1, SYS_GUID(), (SELECT ROLE_ID FROM KRIM_ROLE_T WHERE ROLE_NM = 'Technical Administrator'), (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'quickstart'), 'P', null, null, to_date('07-10-2009 15:41:46', 'dd-mm-yyyy hh24:mi:ss'));
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1601', 1, SYS_GUID(), '1130', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'jtester'), 'P', null, null, sysdate);
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1602', 1, SYS_GUID(), '1130', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'majors'), 'P', null, null, sysdate);
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1603', 1, SYS_GUID(), '1130', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'cate'), 'P', null, null, sysdate);
insert into KRIM_ROLE_MBR_T (ROLE_MBR_ID, VER_NBR, OBJ_ID, ROLE_ID, MBR_ID, MBR_TYP_CD, ACTV_FRM_DT, ACTV_TO_DT, LAST_UPDT_DT)
values ('1604', 1, SYS_GUID(), '1130', (SELECT PRNCPL_ID FROM KRIM_PRNCPL_T WHERE PRNCPL_NM = 'oblood'), 'P', null, null, sysdate);


-- Questionnaire answer permission
INSERT INTO KRIM_PERM_TMPL_T (PERM_TMPL_ID, NM, DESC_TXT, KIM_TYP_ID, ACTV_IND, NMSPC_CD, OBJ_ID) 
VALUES ('1030', 'Answer Questionnaire Permission', 'Answer Questionnaire', 3, 'Y', 'KC-IDM', SYS_GUID());

INSERT INTO KRIM_PERM_T (PERM_ID, PERM_TMPL_ID, NM, DESC_TXT, ACTV_IND, NMSPC_CD, OBJ_ID) 
VALUES ('1221', '1030', 'Answer Protocol Questionnaire', null, 'Y', 'KC-PROTOCOL', SYS_GUID());

INSERT INTO KRIM_ROLE_PERM_T(ROLE_PERM_ID, ROLE_ID, PERM_ID, ACTV_IND, OBJ_ID) 
VALUES ('851', '1125','1221', 'Y', SYS_GUID());

INSERT INTO KRIM_PERM_ATTR_DATA_T (ATTR_DATA_ID, OBJ_ID, VER_NBR, PERM_ID, KIM_TYP_ID, KIM_ATTR_DEFN_ID, ATTR_VAL)
VALUES('1162', SYS_GUID(), '1', '1221', '3', '13', 'ProtocolDocument') ;


alter table KRIM_ENTITY_ENT_TYP_T enable constraint KRIM_ENTITY_ENT_TYP_TR1;
alter table KRIM_ENTITY_ADDR_T enable constraint KRIM_ENTITY_ADDR_TR1;
alter table KRIM_ENTITY_ADDR_T enable constraint KRIM_ENTITY_ADDR_TR2;
alter table KRIM_ENTITY_AFLTN_T enable constraint KRIM_ENTITY_AFLTN_TR1;
alter table KRIM_ENTITY_AFLTN_T enable constraint KRIM_ENTITY_AFLTN_TR2;
alter table KRIM_ENTITY_CTZNSHP_T enable constraint KRIM_ENTITY_CTZNSHP_TR1;
alter table KRIM_ENTITY_CTZNSHP_T enable constraint KRIM_ENTITY_CTZNSHP_TR2;
alter table KRIM_ENTITY_EMAIL_T enable constraint KRIM_ENTITY_EMAIL_TR1;
alter table KRIM_ENTITY_EMP_INFO_T enable constraint KRIM_ENTITY_EMP_INFO_TR1;
alter table KRIM_ENTITY_EMP_INFO_T enable constraint KRIM_ENTITY_EMP_INFO_TR2;
alter table KRIM_ENTITY_EMP_INFO_T enable constraint KRIM_ENTITY_EMP_INFO_TR3;
alter table KRIM_ENTITY_EMP_INFO_T enable constraint KRIM_ENTITY_EMP_INFO_TR4;
alter table KRIM_ENTITY_EXT_ID_T enable constraint KRIM_ENTITY_EXT_ID_TR1;
alter table KRIM_ENTITY_EXT_ID_T enable constraint KRIM_ENTITY_EXT_ID_TR2;
alter table KRIM_ENTITY_NM_T enable constraint KRIM_ENTITY_NM_TR1;
alter table KRIM_ENTITY_PHONE_T enable constraint KRIM_ENTITY_PHONE_TR1;
alter table KRIM_ENTITY_PHONE_T enable constraint KRIM_ENTITY_PHONE_TR2;
alter table KRIM_GRP_T enable constraint KRIM_GRP_TR1;
alter table KRIM_GRP_ATTR_DATA_T enable constraint KRIM_GRP_ATTR_DATA_TR1;
alter table KRIM_GRP_ATTR_DATA_T enable constraint KRIM_GRP_ATTR_DATA_TR2;
alter table KRIM_GRP_ATTR_DATA_T enable constraint KRIM_GRP_ATTR_DATA_TR3;
alter table KRIM_GRP_MBR_T enable constraint KRIM_GRP_MBR_TR1;
alter table KRIM_PERM_TMPL_T enable constraint KRIM_PERM_TMPL_TR1;
alter table KRIM_PERM_T enable constraint KRIM_PERM_TR1;
alter table KRIM_PERM_ATTR_DATA_T enable constraint KRIM_PERM_ATTR_DATA_TR1;
alter table KRIM_PERM_ATTR_DATA_T enable constraint KRIM_PERM_ATTR_DATA_TR2;
alter table KRIM_PERM_ATTR_DATA_T enable constraint KRIM_PERM_ATTR_DATA_TR3;
alter table KRIM_PRNCPL_T enable constraint KRIM_PRNCPL_TR1;
alter table KRIM_ROLE_MBR_ATTR_DATA_T enable constraint KRIM_ROLE_MBR_ATTR_DATA_TR1;
alter table KRIM_ROLE_MBR_ATTR_DATA_T enable constraint KRIM_ROLE_MBR_ATTR_DATA_TR2;
alter table KRIM_ROLE_T enable constraint KRIM_ROLE_TR1;
alter table KRIM_ROLE_MBR_T enable constraint KRIM_ROLE_MBR_TR1;
alter table KRIM_ROLE_PERM_T enable constraint KRIM_ROLE_PERM_TR1;
alter table KRIM_TYP_ATTR_T enable constraint KRIM_TYP_ATTR_TR2;
DROP SEQUENCE KRIM_ROLE_MBR_ID_S;
create SEQUENCE KRIM_ROLE_MBR_ID_S increment by 1 start with 2000; 

DROP SEQUENCE KRIM_ATTR_DATA_ID_S;
create SEQUENCE KRIM_ATTR_DATA_ID_S increment by 1 start with 10000; 