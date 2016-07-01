DROP TABLE IF EXISTS messages;
DROP TABLE IF EXISTS users;

CREATE SEQUENCE message_seq START WITH 1;
CREATE SEQUENCE user_seq START WITH 1;

CREATE TABLE messages
(
  id INTEGER PRIMARY KEY DEFAULT nextval('message_seq'),
  text VARCHAR NOT NULL,
  datetime TIMESTAMP DEFAULT now(),
  user_id INTEGER NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id)
);

CREATE TABLE users
(
  id INTEGER PRIMARY KEY DEFAULT nextval('user_seq'),
  email VARCHAR NOT NULL,
  name VARCHAR NOT NULL,
  password VARCHAR NOT NULL
)
