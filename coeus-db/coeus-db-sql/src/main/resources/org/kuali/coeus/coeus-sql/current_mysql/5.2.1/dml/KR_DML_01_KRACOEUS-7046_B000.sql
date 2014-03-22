DELIMITER /

UPDATE KRMS_FUNC_T SET DESC_TXT = 'Applies to all proposals' WHERE NM = 'allProposalsRule' AND NMSPC_CD = 'KC-PD'
/

UPDATE KRMS_TERM_SPEC_T SET NM = 
    (SELECT FUNC_ID FROM KRMS_FUNC_T WHERE NM = 'allProposalsRule' AND NMSPC_CD = 'KC-PD') 
        WHERE NM = 'allProposals' AND NMSPC_CD = 'KC-PD'
/

INSERT INTO KRMS_TERM_RSLVR_S VALUES(NULL)
/

INSERT INTO KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_TERM_RSLVR_S)),'KC-PD','All proposals resolver',
            (SELECT TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' AND NMSPC_CD='KC-KRMS'),
                (SELECT TERM_SPEC_ID from KRMS_TERM_SPEC_T WHERE NMSPC_CD='KC-PD' AND 
                    NM=(SELECT FUNC_ID from KRMS_FUNC_T WHERE  NM='allProposalsRule' AND NMSPC_CD='KC-PD')),
                    'Y',1)
/
DELIMITER ;

