--changeset heumn:2

ALTER TABLE education.public."user" ALTER COLUMN account_user TYPE text USING account_user::text;



