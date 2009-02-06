-- needed to add this col for KC impl (not in the original Coues table).
ALTER TABLE PROTOCOL_FUNDING_SOURCE ADD (FUNDING_SOURCE_NAME VARCHAR2(200) NULL);
