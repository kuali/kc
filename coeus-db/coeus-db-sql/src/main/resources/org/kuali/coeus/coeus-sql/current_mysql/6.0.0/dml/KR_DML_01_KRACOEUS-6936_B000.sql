DELIMITER /
-- checkProposalPersonIsPi(DevelopmentProposal developmentProposal);
INSERT INTO KRMS_FUNC_S VALUES(NULL)
/
INSERT INTO  KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_FUNC_S)),'checkProposalPersonIsPi','Check ProposalPerson is PI','java.lang.Boolean',1,'Y', 
(select TYP_ID from KRMS_TYP_T where NM='ProposalDevelopment Java Function Term Service' and NMSPC_CD='KC-PD'),'KC-PD')
/

INSERT INTO KRMS_TERM_SPEC_S VALUES(NULL)
/
INSERT INTO KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_TERM_SPEC_S)),'KC-PD',(select FUNC_ID from KRMS_FUNC_T where  NM='checkProposalPersonIsPi'and NMSPC_CD='KC-PD'), 
'Check ProposalPerson is PI','java.lang.Boolean','Y',1)
/

INSERT INTO KRMS_TERM_S VALUES(NULL)
/
INSERT INTO KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_TERM_S)),(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
NM=(select FUNC_ID from KRMS_FUNC_T where  NM='checkProposalPersonIsPi' and NMSPC_CD='KC-PD')), 1,'Check ProposalPerson is PI') 
/

INSERT INTO KRMS_CNTXT_VLD_TERM_SPEC_S VALUES(NULL)
/
INSERT INTO KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_CNTXT_VLD_TERM_SPEC_S)),'KC-PD-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and 
NM=(select FUNC_ID from KRMS_FUNC_T where  NM='checkProposalPersonIsPi' and NMSPC_CD='KC-PD')), 'Y') 
/
INSERT INTO KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
VALUES ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM=(select FUNC_ID from KRMS_FUNC_T where NM='checkProposalPersonIsPi' 
and NMSPC_CD='KC-PD')), (select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Function')) 
/

INSERT INTO KRMS_TERM_RSLVR_S VALUES(NULL)
/
INSERT INTO KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_TERM_RSLVR_S)),'KC-PD','Check ProposalPerson is PI Resolver', 
(select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' and NMSPC_CD='KC-KRMS'), 
(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM=(select FUNC_ID from KRMS_FUNC_T where  NM='checkProposalPersonIsPi' and NMSPC_CD='KC-PD')), 'Y',1) 
/

INSERT INTO KRMS_FUNC_PARM_S VALUES(NULL)
/
INSERT INTO KRMS_FUNC_PARM_T (FUNC_PARM_ID,NM,DESC_TXT,TYP,FUNC_ID,SEQ_NO) 
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_FUNC_PARM_S)),'DevelopmentProposal','DevelopmentProposal','org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
(select FUNC_ID from KRMS_FUNC_T where  NM='checkProposalPersonIsPi' and NMSPC_CD='KC-PD'), 1)
/

-- checkProposalPersonIsCoi(DevelopmentProposal developmentProposal);
INSERT INTO KRMS_FUNC_S VALUES(NULL) 
/
INSERT INTO KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_FUNC_S)),'checkProposalPersonIsCoi','Check ProposalPerson is CoInvestigator','java.lang.Boolean',1,'Y', 
(select TYP_ID from KRMS_TYP_T where NM='ProposalDevelopment Java Function Term Service' and NMSPC_CD='KC-PD'),'KC-PD') 
/ 

INSERT INTO KRMS_TERM_SPEC_S VALUES(NULL)
/
INSERT INTO KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_TERM_SPEC_S),'KC-PD',(select FUNC_ID from KRMS_FUNC_T where  NM='checkProposalPersonIsCoi'and NMSPC_CD='KC-PD'), 
'Check ProposalPerson is CoInvestigator','java.lang.Boolean','Y',1)
/

INSERT INTO KRMS_TERM_S VALUES(NULL)
/
INSERT INTO KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_TERM_S)),(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM=(select FUNC_ID 
from KRMS_FUNC_T where  NM='checkProposalPersonIsCoi' and NMSPC_CD='KC-PD')), 1,'Check ProposalPerson is CoInvestigator')
/

INSERT INTO KRMS_CNTXT_VLD_TERM_SPEC_S VALUES(NULL)
/
INSERT INTO KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_CNTXT_VLD_TERM_SPEC_S)),'KC-PD-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where 
NMSPC_CD='KC-PD' and NM=(select FUNC_ID from KRMS_FUNC_T where  NM='checkProposalPersonIsCoi' and NMSPC_CD='KC-PD')), 'Y')
/
INSERT INTO KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
VALUES ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM=(select FUNC_ID from KRMS_FUNC_T where NM='checkProposalPersonIsCoi' and NMSPC_CD='KC-PD')), 
(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Function'))
/

INSERT INTO KRMS_TERM_RSLVR_S VALUES(NULL)
/
INSERT INTO KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_TERM_RSLVR_S)),'KC-PD','Check ProposalPerson is CoInvestigator Resolver', (select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' 
and NMSPC_CD='KC-KRMS'), (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM=(select FUNC_ID from KRMS_FUNC_T where  NM='checkProposalPersonIsCoi' and NMSPC_CD='KC-PD')), 'Y',1)
/
   
INSERT INTO KRMS_FUNC_PARM_S VALUES(NULL)
/
INSERT INTO KRMS_FUNC_PARM_T (FUNC_PARM_ID,NM,DESC_TXT,TYP,FUNC_ID,SEQ_NO) 
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_FUNC_PARM_S)),'DevelopmentProposal','DevelopmentProposal','org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal', 
(select FUNC_ID from KRMS_FUNC_T where  NM='checkProposalPersonIsCoi' and NMSPC_CD='KC-PD'), 1)
/ 

-- checkProposalPersonIsKeyPerson(DevelopmentProposal developmentProposal);
INSERT INTO KRMS_FUNC_S VALUES(NULL)
/
INSERT INTO KRMS_FUNC_T (FUNC_ID,NM,DESC_TXT,RTRN_TYP,VER_NBR,ACTV,TYP_ID,NMSPC_CD) 
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_FUNC_S)),'checkProposalPersonIsKeyPerson','Check ProposalPerson is KeyPerson','java.lang.Boolean',1,'Y', 
(select TYP_ID from KRMS_TYP_T where NM='ProposalDevelopment Java Function Term Service' and NMSPC_CD='KC-PD'),'KC-PD') 
/

INSERT INTO KRMS_TERM_SPEC_S VALUES(NULL)
/
INSERT INTO KRMS_TERM_SPEC_T (TERM_SPEC_ID, NMSPC_CD, NM, DESC_TXT, TYP, ACTV, VER_NBR) 
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_TERM_SPEC_S)),'KC-PD',(select FUNC_ID from KRMS_FUNC_T where  NM='checkProposalPersonIsKeyPerson'and NMSPC_CD='KC-PD'),
'Check ProposalPerson is KeyPerson','java.lang.Boolean','Y',1) 
/

INSERT INTO KRMS_TERM_S VALUES(NULL)
/
INSERT INTO KRMS_TERM_T(TERM_ID,TERM_SPEC_ID,VER_NBR,DESC_TXT) 
VALUES (CONCAT('KC',(SELECT (MAX(ID)) FROM KRMS_TERM_S)),(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM=(select FUNC_ID 
from KRMS_FUNC_T where  NM='checkProposalPersonIsKeyPerson' and NMSPC_CD='KC-PD')), 1,'Check ProposalPerson is KeyPerson') 
/

INSERT INTO KRMS_CNTXT_VLD_TERM_SPEC_S VALUES(NULL)
/
INSERT INTO KRMS_CNTXT_VLD_TERM_SPEC_T (CNTXT_TERM_SPEC_PREREQ_ID, CNTXT_ID, TERM_SPEC_ID, PREREQ) 
VALUES (CONCAT('KC', (SELECT (MAX(ID)) FROM KRMS_CNTXT_VLD_TERM_SPEC_S)),'KC-PD-CONTEXT',(select TERM_SPEC_ID from KRMS_TERM_SPEC_T where 
NMSPC_CD='KC-PD' and NM=(select FUNC_ID from KRMS_FUNC_T where  NM='checkProposalPersonIsKeyPerson' and NMSPC_CD='KC-PD')), 'Y')
/
INSERT INTO KRMS_TERM_SPEC_CTGRY_T (TERM_SPEC_ID, CTGRY_ID) 
VALUES ((select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM=(select FUNC_ID from KRMS_FUNC_T where NM='checkProposalPersonIsKeyPerson' and NMSPC_CD='KC-PD')),
(select CTGRY_ID from KRMS_CTGRY_T where NMSPC_CD='KC-PD' and NM='Function'))
/

INSERT INTO KRMS_TERM_RSLVR_S VALUES(NULL) 
/ 
INSERT INTO KRMS_TERM_RSLVR_T (TERM_RSLVR_ID, NMSPC_CD, NM, TYP_ID, OUTPUT_TERM_SPEC_ID, ACTV, VER_NBR) 
VALUES (CONCAT('KC',(SELECT (MAX(ID)) FROM KRMS_TERM_RSLVR_S)),'KC-PD','Check ProposalPerson is KeyPerson Resolver', (select TYP_ID from KRMS_TYP_T where NM='Function Term Resolver Type Service' 
and NMSPC_CD='KC-KRMS'), (select TERM_SPEC_ID from KRMS_TERM_SPEC_T where NMSPC_CD='KC-PD' and NM=(select FUNC_ID from KRMS_FUNC_T where  NM='checkProposalPersonIsKeyPerson' and NMSPC_CD='KC-PD')), 'Y',1) alter
/

INSERT INTO KRMS_FUNC_PARM_S VALUES(NULL)
/
INSERT INTO KRMS_FUNC_PARM_T (FUNC_PARM_ID,NM,DESC_TXT,TYP,FUNC_ID,SEQ_NO) 
VALUES (CONCAT('KC',(SELECT (MAX(ID)) FROM KRMS_FUNC_PARM_S)),'DevelopmentProposal','DevelopmentProposal','org.kuali.kra.proposaldevelopment.bo.DevelopmentProposal',
(select FUNC_ID from KRMS_FUNC_T where  NM='checkProposalPersonIsKeyPerson' and NMSPC_CD='KC-PD'), 1) 
/

DELIMITER ;

