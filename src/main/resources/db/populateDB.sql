DELETE FROM messages;
DELETE FROM users;

ALTER SEQUENCE user_seq RESTART WITH 1;
ALTER SEQUENCE message_seq RESTART WITH 1;

INSERT INTO users (email, name, password) VALUES
  ('user1@ya.ru', 'Timur', '111111'),
  ('user2@ya.ru', 'Dante', '222222'),
  ('user3@ya.ru', 'Wolverine', '333333');

INSERT INTO messages (text, user_id) VALUES
  ('I am Timur. I want to say that i am senior java developer', 1),
  ('I am Dante. I like Java, but at this moment i am junior java developer', 2),
  ('I am Wolverine. And know what Java is, and a dont want to know. I dont need Java, i am Wolverine. AAAAAA!', 3);
