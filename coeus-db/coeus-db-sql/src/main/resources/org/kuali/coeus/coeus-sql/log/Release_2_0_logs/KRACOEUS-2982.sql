UPDATE KRNS_PARM_T set NMSPC_CD = 'KC-GEN', PARM_DTL_TYP_CD = 'A'
where NMSPC_CD = 'KRA-PD' and PARM_DTL_TYP_CD = 'D' and PARM_NM = 'sponsorGroupHierarchyName';

UPDATE KRNS_PARM_T set NMSPC_CD = 'KC-GEN', PARM_DTL_TYP_CD = 'A'
where NMSPC_CD = 'KRA-PD' and PARM_DTL_TYP_CD = 'D' and PARM_NM = 'sponsorLevelHierarchy';

UPDATE KRNS_PARM_T set NMSPC_CD = 'KC-GEN', PARM_DTL_TYP_CD = 'A', PARM_NM = 'personrole.pi'
where NMSPC_CD = 'KRA-PD' and PARM_DTL_TYP_CD = 'D' and PARM_NM = 'proposaldevelopment.personrole.pi';

UPDATE KRNS_PARM_T set NMSPC_CD = 'KC-GEN', PARM_DTL_TYP_CD = 'A', PARM_NM = 'personrole.nih.pi'
where NMSPC_CD = 'KRA-PD' and PARM_DTL_TYP_CD = 'D' and PARM_NM = 'proposaldevelopment.personrole.nonnih.pi';

UPDATE KRNS_PARM_T set NMSPC_CD = 'KC-GEN', PARM_DTL_TYP_CD = 'A', PARM_NM = 'personrole.kp'
where NMSPC_CD = 'KRA-PD' and PARM_DTL_TYP_CD = 'D' and PARM_NM = 'proposaldevelopment.personrole.kp';

UPDATE KRNS_PARM_T set NMSPC_CD = 'KC-GEN', PARM_DTL_TYP_CD = 'A', PARM_NM = 'personrole.nih.kp'
where NMSPC_CD = 'KRA-PD' and PARM_DTL_TYP_CD = 'D' and PARM_NM = 'proposaldevelopment.personrole.nonnih.kp';

UPDATE KRNS_PARM_T set NMSPC_CD = 'KC-GEN', PARM_DTL_TYP_CD = 'A', PARM_NM = 'personrole.coi'
where NMSPC_CD = 'KRA-PD' and PARM_DTL_TYP_CD = 'D' and PARM_NM = 'proposaldevelopment.personrole.coi';

UPDATE KRNS_PARM_T set NMSPC_CD = 'KC-GEN', PARM_DTL_TYP_CD = 'A', PARM_NM = 'personrole.nih.coi'
where NMSPC_CD = 'KRA-PD' and PARM_DTL_TYP_CD = 'D' and PARM_NM = 'proposaldevelopment.personrole.nonnih.coi';

UPDATE KRNS_PARM_T set NMSPC_CD = 'KC-GEN', PARM_DTL_TYP_CD = 'A', PARM_NM = 'personrole.readonly.roles'
where NMSPC_CD = 'KRA-PD' and PARM_DTL_TYP_CD = 'D' and PARM_NM = 'proposaldevelopment.personrole.readonly.roles';

commit;