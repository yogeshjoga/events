create database events_cha;
use events_cha;

# Configurationspresentations
SET foreign_key_checks = 0;
SET foreign_key_checks = 1;

# Dropping Tables from existing Database
DROP TABLE presentations;
DROP TABLE receiving;
DROP TABLE relative;
DROP TABLE otp;
DROP TABLE relative_presentations;
DROP TABLE relative_receivings;

SET SQL_SAFE_UPDATES = 0;
DELETE FROM presentations;
DELETE FROM receiving;
DELETE FROM otp;
DELETE FROM relative_presentations;
DELETE FROM relative_receivings;
DELETE FROM relative;
SET SQL_SAFE_UPDATES = 1;


create database search;

