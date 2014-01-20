UPDATE KRCR_PARM_T
SET val = 'AWRD=Award;INPR=Institutional Proposal;NGT=Negotiation;PRDV=Proposal Development;PROT=Protocol;SAWD=SubAward'
where NMSPC_CD = 'KC-GEN' and PARM_NM = 'customAttributeDocumentType'
/
