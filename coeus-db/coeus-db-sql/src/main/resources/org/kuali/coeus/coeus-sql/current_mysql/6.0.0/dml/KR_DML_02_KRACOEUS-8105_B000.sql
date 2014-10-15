DELIMITER /
DELETE FROM KRIM_ROLE_T WHERE NMSPC_CD='KC-PD' and ROLE_NM='unassigned Document Level'
/
DELETE FROM KRIM_ROLE_T WHERE NMSPC_CD='KC-PD' and ROLE_NM='Investigators Document Level'
/
DELETE FROM KRIM_ROLE_T WHERE NMSPC_CD='KC-PD' and ROLE_NM='All Unit Administrators Document Level'
/
DELETE FROM KRIM_ROLE_T WHERE NMSPC_CD='KC-PD' and ROLE_NM='Maintain Proposal Questionnaire Document Level'
/
DELIMITER ;
