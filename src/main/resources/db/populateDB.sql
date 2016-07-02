DELETE FROM messages;
DELETE FROM users;

ALTER SEQUENCE user_seq RESTART WITH 1;
ALTER SEQUENCE message_seq RESTART WITH 1;

INSERT INTO users (email, name, password) VALUES
  ('trimmerx@yandex.ru', 'Ildar', '1admin2'),
  ('user1@ya.ru', 'Timur', '111111'),
  ('user2@ya.ru', 'Dante', '222222'),
  ('user3@ya.ru', 'Wolverine', '333333');

INSERT INTO messages (text, user_id) VALUES
  ('I am Timur. I want to say that i am senior java developer', 1),
  ('I am Dante. I like Java, but at this moment i am junior java developer', 2),
  ('I am Wolverine. And know what Java is, and a dont want to know. I dont need Java, i am Wolverine. AAAAAA!', 3);

INSERT INTO user_roles (user_id, role) VALUES
  (1, 'ROLE_ADMIN'),
  (2, 'ROLE_USER'),
  (3, 'ROLE_USER'),
  (4, 'ROLE_USER');
