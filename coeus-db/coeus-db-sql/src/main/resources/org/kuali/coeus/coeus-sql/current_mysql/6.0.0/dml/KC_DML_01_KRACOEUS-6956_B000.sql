DELIMITER /
UPDATE KC_KRMS_TERM_FUN_PARAM SET PARAM_TYPE='org.kuali.coeus.propdev.impl.core.DevelopmentProposal' WHERE PARAM_NAME='DevelopmentProposal'
/
UPDATE EPS_PROP_COLUMNS_TO_ALTER SET LOOKUP_ARGUMENT='org.kuali.coeus.common.framework.type.ActivityType' WHERE COLUMN_NAME ='ACTIVITY_TYPE_CODE'
/
UPDATE EPS_PROP_COLUMNS_TO_ALTER SET LOOKUP_ARGUMENT='org.kuali.coeus.common.framework.type.DeadlineType' WHERE COLUMN_NAME ='DEADLINE_DATE'
/
UPDATE EPS_PROP_COLUMNS_TO_ALTER SET LOOKUP_ARGUMENT='org.kuali.coeus.common.framework.type.ProposalType' WHERE COLUMN_NAME ='PROPOSAL_TYPE_CODE'
/

DELIMITER ;
