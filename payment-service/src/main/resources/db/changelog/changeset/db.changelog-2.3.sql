--changeset heumn:2

ALTER TABLE education.public."user" ALTER COLUMN account_user TYPE double precision USING account_user::double precision;



