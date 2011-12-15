DELIMITER /
UPDATE KREW_DOC_TYP_T 
SET LBL = 'Negotiation Agreement Type'
WHERE DOC_TYP_NM = 'NegotiationAgreementTypeMaintenanceDocument'
/
UPDATE KREW_DOC_TYP_T 
SET LBL = 'Negotiation Association Type'
WHERE DOC_TYP_NM = 'NegotiationAssociationTypeMaintenanceDocument'
/
DELIMITER ;
