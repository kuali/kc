DELIMITER /

UPDATE KRMS_FUNC_T set DESC_TXT = 'Check if the submit (logged in) user is the PI of the proposal'
WHERE NM = 'isUserProposalPI'
/

UPDATE KRMS_TERM_SPEC_T set DESC_TXT = 'Check if the submit (logged in) user is the PI of the proposal'
WHERE NM = (select FUNC_ID from KRMS_FUNC_T where  NM='isUserProposalPI' and NMSPC_CD='KC-PD')
/

UPDATE KRMS_TERM_T set DESC_TXT = 'Check if the submit (logged in) user is the PI of the proposal'
WHERE TERM_SPEC_ID = (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and
NM=(select FUNC_ID from KRMS_FUNC_T where  NM='isUserProposalPI' and NMSPC_CD='KC-PD'))
/

DELETE FROM KRMS_FUNC_PARM_T
WHERE NM = 'PrincipalId' AND FUNC_ID = (select FUNC_ID from KRMS_FUNC_T where  NM='isUserProposalPI' and NMSPC_CD='KC-PD')
AND SEQ_NO = 2
/

DELETE FROM KRMS_TERM_RSLVR_PARM_SPEC_T
WHERE TERM_RSLVR_ID = (select TERM_RSLVR_ID from KRMS_TERM_RSLVR_T where NM='Proposal PI Resolver' and NMSPC_CD='KC-PD') AND
NM = 'PrincipalId'
/

DELIMITER ;
