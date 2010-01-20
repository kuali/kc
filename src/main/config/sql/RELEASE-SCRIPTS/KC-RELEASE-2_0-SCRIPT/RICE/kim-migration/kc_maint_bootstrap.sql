delete from krim_perm_attr_data_t t where t.perm_id in (select perm_id from krim_role_perm_t where role_id in (select role_id from krim_role_t t where t.role_nm = 'Manager' and t.nmspc_cd = 'KC-SYS'));
delete from krim_role_perm_t t where t.role_id in (select role_id from krim_role_t t where t.role_nm = 'Manager' and t.nmspc_cd = 'KC-SYS');
delete from krim_perm_t t where t.nm like 'Blanket Approve%' and nmspc_cd in ('KC-SYS', 'KRA-PD', 'KC-PROTOCOL', 'KC-AWARD') ;
delete from krim_perm_t t where t.nmspc_cd = 'KC-SYS' and t.nm = 'Initiate Document';
delete from krim_perm_t t where t.nmspc_cd = 'KC-SYS' and t.nm = 'Delete Note / Attachment';
delete from krim_perm_t t where t.nmspc_cd = 'KC-IDM' and t.nm = 'Assign Role';
delete from krim_perm_t t where t.nmspc_cd = 'KC-IDM' and t.nm = 'Grant Permission';
delete from krim_perm_t t where t.nmspc_cd = 'KC-IDM' and t.nm = 'Grant Responsibility';
delete from krim_perm_t t where t.nmspc_cd = 'KC-SYS' and t.nm = 'Maintain KC System Parameters';
delete from krim_perm_t where nm = 'Blanket Approve ProposalDevelopmentDocument' and nmspc_cd = 'KRA-PD';
delete from krim_role_t t where t.role_nm = 'Manager' and t.nmspc_cd = 'KC-SYS';

insert into krim_role_t (role_id, role_nm, nmspc_cd, desc_txt, kim_typ_id, actv_ind, last_updt_dt, obj_id) 
values (krim_role_id_s.nextval, 'Manager', 'KC-SYS', 'This role represents a collection of all the KC module manager roles and has permission to initiate simple maintenance documents.', (select kim_typ_id from krim_typ_t where nm = 'Default' and nmspc_cd = 'KUALI'), 'Y', sysdate, sys_guid());

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values (krim_perm_id_s.nextval, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Blanket Approve Document'), 'KC-SYS', 'Blanket Approve Document', 'Blanket Approve KC Documents', 'Y' , sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, (select kim_typ_id from krim_typ_t t where t.nm = 'Document Type (Permission)' and t.nmspc_cd = 'KR-SYS'), 
(select kim_attr_defn_id from krim_attr_defn_t where nm = 'documentTypeName' and nmspc_cd = 'KR-WKFLW') , 'KcMaintenanceDocument', sys_guid() );

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id)  
values (krim_perm_id_s.nextval, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Blanket Approve Document'), 'KRA-PD', 'Blanket Approve ProposalDevelopmentDocument', 'Blanket Approve ProposalDevelopmentDocument', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, (select kim_typ_id from krim_typ_t t where t.nm = 'Document Type (Permission)' and t.nmspc_cd = 'KR-SYS'), 
(select kim_attr_defn_id from krim_attr_defn_t where nm = 'documentTypeName' and nmspc_cd = 'KR-WKFLW') , 'ProposalDevelopmentDocument', sys_guid() );

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id)  
values (krim_perm_id_s.nextval, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Blanket Approve Document'), 'KC-PROTOCOL', 'Blanket Approve ProtocolDocument', 'Blanket Approve ProtocolDocument', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, (select kim_typ_id from krim_typ_t t where t.nm = 'Document Type (Permission)' and t.nmspc_cd = 'KR-SYS'), 
(select kim_attr_defn_id from krim_attr_defn_t where nm = 'documentTypeName' and nmspc_cd = 'KR-WKFLW') , 'ProtocolDocument', sys_guid() );

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id)  
values (krim_perm_id_s.nextval, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Blanket Approve Document'), 'KC-PROTOCOL', 'Blanket Approve CommitteeDocument', 'Blanket Approve CommitteeDocument', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, (select kim_typ_id from krim_typ_t t where t.nm = 'Document Type (Permission)' and t.nmspc_cd = 'KR-SYS'), 
(select kim_attr_defn_id from krim_attr_defn_t where nm = 'documentTypeName' and nmspc_cd = 'KR-WKFLW') , 'CommitteeDocument', sys_guid() );

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id)  
values (krim_perm_id_s.nextval, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Blanket Approve Document'), 'KC-AWARD', 'Blanket Approve AwardDocument', 'Blanket Approve AwardDocument', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, (select kim_typ_id from krim_typ_t t where t.nm = 'Document Type (Permission)' and t.nmspc_cd = 'KR-SYS'), 
(select kim_attr_defn_id from krim_attr_defn_t where nm = 'documentTypeName' and nmspc_cd = 'KR-WKFLW') , 'AwardDocument', sys_guid() );

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id)  
values (krim_perm_id_s.nextval, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Blanket Approve Document'), 'KC-AWARD', 'Blanket Approve AwardBudgetDocument', 'Blanket Approve AwardBudgetDocument', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, (select kim_typ_id from krim_typ_t t where t.nm = 'Document Type (Permission)' and t.nmspc_cd = 'KR-SYS'), 
(select kim_attr_defn_id from krim_attr_defn_t where nm = 'documentTypeName' and nmspc_cd = 'KR-WKFLW') , 'AwardBudgetDocument', sys_guid() );

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id)  
values (krim_perm_id_s.nextval, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NMSPC_CD = 'KR-WKFLW' AND NM = 'Blanket Approve Document'), 'KC-AWARD', 'Blanket Approve TimeAndMoneyDocument', 'Blanket Approve TimeAndMoneyDocument', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, (select kim_typ_id from krim_typ_t t where t.nm = 'Document Type (Permission)' and t.nmspc_cd = 'KR-SYS'), 
(select kim_attr_defn_id from krim_attr_defn_t where nm = 'documentTypeName' and nmspc_cd = 'KR-WKFLW') , 'TimeAndMoneyDocument', sys_guid() );

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id)  
values (krim_perm_id_s.nextval, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM = 'Initiate Document' and NMSPC_CD = 'KR-SYS'), 'KC-SYS', 'Initiate Document', 'Initiate KC Maintenance Documents', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, (select kim_typ_id from krim_typ_t t where t.nm = 'Document Type (Permission)' and t.nmspc_cd = 'KR-SYS'), 
(select kim_attr_defn_id from krim_attr_defn_t where nm = 'documentTypeName' and nmspc_cd = 'KR-WKFLW') , 'KcMaintenanceDocument', sys_guid() );

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id)  
values (krim_perm_id_s.nextval, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM = 'Assign Role' and NMSPC_CD = 'KR-IDM'), 'KC-IDM', 'Assign Role', 'Assign Role', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, (select kim_typ_id from krim_typ_t where nm = 'Role' and nmspc_cd = 'KR-IDM'), 
(select kim_attr_defn_id from krim_attr_defn_t where nm = 'namespaceCode' and nmspc_cd = 'KR-NS') , 'KC*', sys_guid() );

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id)  
values (krim_perm_id_s.nextval, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM = 'Grant Permission' and NMSPC_CD = 'KR-IDM'), 'KC-IDM', 'Grant Permission', 'Grant Permission', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, (select kim_typ_id from krim_typ_t where nm = 'Permission' and nmspc_cd = 'KR-IDM'), 
(select kim_attr_defn_id from krim_attr_defn_t where nm = 'namespaceCode' and nmspc_cd = 'KR-NS') , 'KC*', sys_guid() );

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id)  
values (krim_perm_id_s.nextval, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM = 'Grant Responsibility' and NMSPC_CD = 'KR-IDM'), 'KC-IDM', 'Grant Responsibility', 'Grant Responsibility', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, (select kim_typ_id from krim_typ_t where nm = 'Responsibility' and nmspc_cd = 'KR-IDM'), 
(select kim_attr_defn_id from krim_attr_defn_t where nm = 'namespaceCode' and nmspc_cd = 'KR-NS') , 'KC*', sys_guid() );

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id) 
values (krim_perm_id_s.nextval, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM = 'Maintain System Parameter' and NMSPC_CD = 'KR-NS'), 'KC-SYS', 'Maintain KC System Parameters', 'Maintain KC System Parameters', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, (select kim_typ_id from krim_typ_t where nm = 'Parameter' and nmspc_cd = 'KR-NS'), 
(select kim_attr_defn_id from krim_attr_defn_t where nm = 'namespaceCode' and nmspc_cd = 'KR-NS') , 'KC*', sys_guid() );

insert into krim_perm_t (perm_id, perm_tmpl_id, nmspc_cd, nm, desc_txt, actv_ind, obj_id)  
values (krim_perm_id_s.nextval, (SELECT PERM_TMPL_ID FROM KRIM_PERM_TMPL_T WHERE NM = 'Delete Note / Attachment' and NMSPC_CD = 'KR-NS'), 'KC-SYS', 'Delete Note / Attachment', 'Delete Note / Attachment', 'Y', sys_guid());

insert into krim_perm_attr_data_t (attr_data_id, perm_id, kim_typ_id, kim_attr_defn_id, attr_val, obj_id) 
values(krim_attr_data_id_s.nextval, krim_perm_id_s.currval, (select kim_typ_id from krim_typ_t where nm = 'Document Type & Relationship to Note Author' and nmspc_cd = 'KR-NS'), 
(select kim_attr_defn_id from krim_attr_defn_t where nm = 'documentTypeName' and nmspc_cd = 'KR-WKFLW') , 'KcMaintenanceDocument', sys_guid() );

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, (select perm_id from krim_perm_t where nm = 'Blanket Approve Document' and nmspc_cd = 'KC-SYS' ), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, (select perm_id from krim_perm_t where nm = 'Initiate Document' and nmspc_cd = 'KC-SYS' ), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, (select perm_id from krim_perm_t where nm = 'Maintain System Parameter' and nmspc_cd = 'KC-SYS' ), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, (select perm_id from krim_perm_t where nm = 'Initiate Document' and nmspc_cd = 'KR-SYS' ), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, (select perm_id from krim_perm_t where nm = 'Maintain System Parameter' and nmspc_cd = 'KR-SYS' ), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, (select perm_id from krim_perm_t where nm = 'Full Unmask Field' and nmspc_cd = 'KR-SYS' AND ROWNUM = 1 ), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, (select perm_id from krim_perm_t where nm = 'Full Unmask Field' and nmspc_cd = 'KR-SYS' and perm_id not in (select perm_id from krim_perm_t where nm = 'Full Unmask Field' and nmspc_cd = 'KR-SYS' AND ROWNUM = 1)
 ), 'Y', sys_guid());

insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, (select perm_id from krim_perm_t where nm = 'Blanket Approve ProposalDevelopmentDocument' and nmspc_cd = 'KRA-PD'), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, (select perm_id from krim_perm_t where nm = 'Blanket Approve ProtocolDocument' and nmspc_cd = 'KC-PROTOCOL' ), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, (select perm_id from krim_perm_t where nm = 'Blanket Approve CommitteeDocument' and nmspc_cd = 'KC-PROTOCOL' ), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, (select perm_id from krim_perm_t where nm = 'Blanket Approve AwardDocument' and nmspc_cd = 'KC-AWARD'), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, (select perm_id from krim_perm_t where nm = 'Blanket Approve AwardBudgetDocument' and nmspc_cd = 'KC-AWARD' ), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, (select perm_id from krim_perm_t where nm = 'Blanket Approve TimeAndMoneyDocument' and nmspc_cd = 'KC-AWARD' ), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, (select perm_id from krim_perm_t where nm = 'Delete Note / Attachment' and nmspc_cd = 'KC-SYS'), 'Y', sys_guid());
insert into krim_role_perm_t (role_perm_id, role_id, perm_id, actv_ind, obj_id) values (krim_role_perm_id_s.nextval, krim_role_id_s.currval, (select perm_id from krim_perm_t where nm = 'Unrestricted Document Search' and nmspc_cd = 'KR-WKFLW' ), 'Y', sys_guid());

insert into krim_role_mbr_t (role_mbr_id, role_id, mbr_id, mbr_typ_cd, actv_frm_dt, actv_to_dt, last_updt_dt, ver_nbr, obj_id) 
values (krim_role_mbr_id_s.nextval, krim_role_id_s.currval, (select grp_id from krim_grp_t where grp_nm = 'KcAdmin' and nmspc_cd = 'KC-WKFLW'), 'G', null, null, sysdate, 1, sys_guid());
