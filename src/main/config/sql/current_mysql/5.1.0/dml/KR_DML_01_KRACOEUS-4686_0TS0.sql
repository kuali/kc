DELIMITER /
update KREW_DOC_TYP_T set AUTHORIZER = 'org.kuali.kra.proposaldevelopment.document.authorization.ProposalDevelopmentWorkflowDocumentAuthorizer' where  DOC_TYP_NM LIKE '%ProposalDevelopmentDocument%' and CUR_IND = 1
/

DELIMITER ;
