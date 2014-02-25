-- View for Coeus compatibility
-- for backwards compatibility we are keeping the spelling error in the column name 
CREATE OR REPLACE VIEW OSP$PROTO_CORRESP_TEMPL AS SELECT 
    PROTO_CORRESP_TYPE_CODE, 
    COMMITTEE_ID, 
    CORRESPONDENCE_TEMPLATE AS CORESPONDENCE_TEMPLATE, 
    UPDATE_TIMESTAMP, 
    UPDATE_USER
FROM PROTO_CORRESP_TEMPL