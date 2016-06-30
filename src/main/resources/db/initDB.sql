DROP TABLE IF EXISTS messages;
DROP SEQUENCE IF EXISTS message_seq;

CREATE SEQUENCE message_seq START WITH 1;

CREATE TABLE messages
(
  id INTEGER PRIMARY KEY DEFAULT nextval('message_seq'),
  name VARCHAR NOT NULL,
  text VARCHAR NOT NULL,
  datetime TIMESTAMP DEFAULT now()
);
