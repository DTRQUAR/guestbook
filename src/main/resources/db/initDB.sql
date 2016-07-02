DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS users;

DROP SEQUENCE IF EXISTS user_seq;
DROP SEQUENCE IF EXISTS message_seq;

CREATE SEQUENCE user_seq START WITH 1;
CREATE SEQUENCE message_seq START WITH 1;

CREATE TABLE users
(
  id INTEGER PRIMARY KEY DEFAULT nextval('user_seq'),
  email VARCHAR NOT NULL,
  name VARCHAR NOT NULL,
  password VARCHAR NOT NULL
);

CREATE TABLE messages
(
  id INTEGER PRIMARY KEY DEFAULT nextval('message_seq'),
  default_name VARCHAR,
  text VARCHAR NOT NULL,
  datetime TIMESTAMP DEFAULT now(),
  user_id INTEGER,
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role VARCHAR,
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
)


