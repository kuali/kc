UPDATE unit SET parent_unit_number = null WHERE unit_number = '000001';
DELETE FROM unit WHERE unit_number = '000000';
COMMIT; 