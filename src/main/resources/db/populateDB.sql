DELETE FROM message_rates;
DELETE FROM messages;
DELETE FROM users;

ALTER SEQUENCE rate_seq RESTART WITH 1;
ALTER SEQUENCE user_seq RESTART WITH 1;
ALTER SEQUENCE message_seq RESTART WITH 1;

INSERT INTO users (id, email, name, password) VALUES
  (1, 'trimmerx@yandex.ru', 'Ildar', '1admin2'),
  (2, 'user1@ya.ru', 'Timur', '111111'),
  (3, 'user2@ya.ru', 'Dante', '222222'),
  (4, 'user3@ya.ru', 'Wolverine', '333333');

INSERT INTO messages (id, text, user_id) VALUES
  (1, 'I am Timur. I want to say that i am senior java developer', 2),
  (2, 'I am Dante. I like Java, but at this moment i am junior java developer', 3),
  (3, 'I am Wolverine. And know what Java is, and a dont want to know. I dont need Java, i am Wolverine. AAAAAA!', 4);

INSERT INTO message_rates (id, rate, message_id, user_id) VALUES
  (1, TRUE, 1, 1),
  (2, TRUE, 1, 2),
  (3, TRUE, 1, 3),
  (4, FALSE, 1, 4);

ALTER SEQUENCE rate_seq RESTART WITH 5;
ALTER SEQUENCE user_seq RESTART WITH 5;
ALTER SEQUENCE message_seq RESTART WITH 4;

INSERT INTO user_roles (user_id, role) VALUES
  (1, 'ROLE_ADMIN'),
  (2, 'ROLE_USER'),
  (3, 'ROLE_USER'),
  (4, 'ROLE_USER');
