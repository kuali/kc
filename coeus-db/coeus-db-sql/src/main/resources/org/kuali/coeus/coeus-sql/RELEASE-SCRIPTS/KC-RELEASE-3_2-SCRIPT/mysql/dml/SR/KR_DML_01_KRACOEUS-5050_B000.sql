DELIMITER /
--
--


UPDATE KRCR_PARM_T
SET VAL = 'AWRD=Award;INPR=Institutional Proposal;NGT=Negotiation;PRDV=Proposal Development;PROT=Protocol'
where NMSPC_CD = 'KC-GEN' and PARM_NM = 'customAttributeDocumentType'
/

DELIMITER ;
