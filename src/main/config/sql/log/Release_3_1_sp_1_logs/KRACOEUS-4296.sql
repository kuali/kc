--Update the parameter to allow adding custom fields to Institutional Proposal documents
--leaving in the commented out line to note the original value
update KRNS_PARM_T
--set txt = 'Modify ProposalDevelopmentDocument:KRA-PD;Modify Protocol:KC-PROTOCOL'
set txt = 'Modify ProposalDevelopmentDocument:KRA-PD;Modify Protocol:KC-PROTOCOL;Edit Institutional Proposal:KC-IP'
where nmspc_cd = 'KC-QUESTIONNAIRE' and parm_dtl_typ_cd = 'P'
/
commit