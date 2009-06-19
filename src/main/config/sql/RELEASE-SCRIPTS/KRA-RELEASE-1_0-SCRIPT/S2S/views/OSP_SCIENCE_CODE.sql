
CREATE OR REPLACE VIEW OSP$SCIENCE_CODE ( SCIENCE_CODE, 
DESCRIPTION, UPDATE_TIMESTAMP, UPDATE_USER ) AS select science_keyword_code, description, update_timestamp, update_user
from science_keyword;
