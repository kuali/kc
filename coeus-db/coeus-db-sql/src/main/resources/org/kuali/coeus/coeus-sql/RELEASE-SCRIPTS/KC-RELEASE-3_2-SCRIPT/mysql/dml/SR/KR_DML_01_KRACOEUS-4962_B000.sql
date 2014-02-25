DELIMITER /
--
--


UPDATE KRCR_PARM_T SET VAL = 'AWRD=Award;INPR=Institutional Proposal;NGT=Negotiation;PRDV=Proposal Development;PROT=Protocol;SAWD=Subaward' where NMSPC_CD = 'KC-GEN' and PARM_NM = 'customAttributeDocumentType'
/
commit
/

DELIMITER ;
