-- View for Coeus compatibility 
CREATE OR REPLACE VIEW OSP$MINUTE_ENTRY_TYPE AS SELECT 
    MINUTE_ENTRY_TYPE_CODE, 
    SORT_ID, 
    DESCRIPTION, 
    UPDATE_TIMESTAMP, 
    UPDATE_USER
FROM MINUTE_ENTRY_TYPE;
