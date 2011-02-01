-- Update the parameter to allow adding custom fields to Institutional Proposal documents
update KRNS_PARM_T
set txt = 'Modify Protocol:KC-PROTOCOL;Maintain Questionnaire Usage:KC-PD;Maintain Questionnaire Usage:KC-PROTOCOL;Edit Institutional Proposal:KC-IP'
where nmspc_cd = 'KC-QUESTIONNAIRE' and parm_nm = 'associateModuleQuestionnairePermission' and parm_dtl_typ_cd = 'P';

commit;
