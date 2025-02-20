create database events_cha;
use events_cha;

# Configurationspresentations
SET foreign_key_checks = 0;
SET foreign_key_checks = 1;

# Dropping Tables from existing Databaserelativereceivingrelative
DROP TABLE presentations;
DROP TABLE receiving;
DROP TABLE relative;
DROP TABLE otp;
DROP TABLE relative_presentations;
DROP TABLE relative_receivings;
DROP TABLE user;


SET SQL_SAFE_UPDATES = 0;
DELETE FROM presentations;
DELETE FROM receiving;
DELETE FROM otp;
DELETE FROM relative_presentations;
DELETE FROM relative_receivings;
DELETE FROM relative;
DELETE FROM user;
SET SQL_SAFE_UPDATES = 1;



ALTER TABLE relative MODIFY phone VARCHAR(255) NULL;

-- Step 1: Show indexes to find the unique constraint name
SHOW INDEXES FROM relative;

-- Step 2: Drop the unique constraint (replace `constraint_name` with the actual name)
ALTER TABLE relative DROP INDEX UK6km56y82uxk7h7t56rl4ffewb;

-- Step 3: Ensure the `phone` column can have duplicate values
ALTER TABLE relative MODIFY phone VARCHAR(255) NULL;


create database search;



