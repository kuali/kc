-- KRACOEUS-2065
-- Combine the 'COEUS' and 'Grants.Gov' YNQ groups
-- and name the new group 'General Y/N Questions'.
UPDATE YNQ 
SET GROUP_NAME = 'General Y/N Questions'
WHERE GROUP_NAME IN ('COEUS', 'Grants.Gov');