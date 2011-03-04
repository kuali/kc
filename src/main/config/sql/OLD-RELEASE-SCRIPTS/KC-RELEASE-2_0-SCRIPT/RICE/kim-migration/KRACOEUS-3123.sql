delete from krim_grp_mbr_t t where t.grp_id in (select r.grp_id from krim_grp_t r where r.grp_nm in ('WorkflowAdmin', 'NotificationAdmin', 'TestGroup1', 'TestGroup2', 'RiceTeam', 'Group0', 'Group1', 'Group2', 'Kuali Identity Managers', 'Kuali Administrators', 'Kuali Developers', 'eDoc.Example1.SuperUsers', 'eDoc.Example1.defaultExceptions', 'eDoc.Example1.IUB.Workgroup', 'eDoc.Example1.IUPUI.Workgroup', 'Departmental_Routing', 'OSP'));
delete from krim_role_mbr_t t where t.mbr_id in (select r.grp_id from krim_grp_t r where r.grp_nm in ('WorkflowAdmin', 'NotificationAdmin', 'TestGroup1', 'TestGroup2', 'RiceTeam', 'Group0', 'Group1', 'Group2', 'Kuali Identity Managers', 'Kuali Administrators', 'Kuali Developers', 'eDoc.Example1.SuperUsers', 'eDoc.Example1.defaultExceptions', 'eDoc.Example1.IUB.Workgroup', 'eDoc.Example1.IUPUI.Workgroup', 'Departmental_Routing', 'OSP')) and t.mbr_typ_cd = 'G';
delete from krim_grp_t r where r.grp_nm in ('WorkflowAdmin', 'NotificationAdmin', 'TestGroup1', 'TestGroup2', 'RiceTeam', 'Group0', 'Group1', 'Group2', 'Kuali Identity Managers', 'Kuali Administrators', 'Kuali Developers', 'eDoc.Example1.SuperUsers', 'eDoc.Example1.defaultExceptions', 'eDoc.Example1.IUB.Workgroup', 'eDoc.Example1.IUPUI.Workgroup', 'Departmental_Routing', 'OSP');
commit;

delete from krim_grp_mbr_t t where t.grp_id in (select t.grp_id from krim_grp_t t where t.nmspc_cd = 'KC') ;
delete from krim_role_mbr_t t where t.mbr_id in (select t.grp_id from krim_grp_t t where t.nmspc_cd = 'KC') and t.mbr_typ_cd = 'G';
delete from krim_grp_t r where r.grp_id in (select t.grp_id from krim_grp_t t where t.nmspc_cd = 'KC');
commit;

select * from krim_grp_t;
