DELETE FROM message_rates;
DELETE FROM user_roles;
DELETE FROM messages;
DELETE FROM users;

ALTER SEQUENCE rate_seq RESTART WITH 1;
ALTER SEQUENCE user_seq RESTART WITH 1;
ALTER SEQUENCE message_seq RESTART WITH 1;

INSERT INTO users (id, email, name, password) VALUES
  (1, 'user1@ya.ru', 'Timur', '111111'),
  (2, 'user2@ya.ru', 'Dante', '222222'),
  (3, 'user3@ya.ru', 'Wolverine', '333333');

INSERT INTO messages (id, datetime, text, user_id) VALUES
  (1, TIMESTAMP '2016-07-04 15:15:15', 'Java EE продолжает тонуть в хайпе микросервисов. Парни из IBM, RedHat и нескольких других компаний решили бросить ей спасательный круг, анонсировав инициативу MicroProfile.io. Задача — подогнать стандарты Java EE под современные тренды. Читай — натянуть Java EE на микросервисы. Причем в буквальном смысле. Например, предлагается чуть ли не стандартизировать максимальный размер джарника и время старта приложения. Что-то вроде «enlarge your ...», только наоборот.', 1),
  (2, TIMESTAMP '2016-07-04 15:15:15', 'Ребята из Google залили публичные репозитории GitHub в свой движок BigQuery, и открыли к нему доступ всем желающим. Остается положить какие-то 300$ на счет, и вы легко сможете собирать всевозможную статистику по этому огромному датасету. Например, найти все использования пакета sun.misc :-) ', 2),
  (3, TIMESTAMP '2016-07-04 15:15:15', 'Алексей Шипилев проделал титанический труд, и собрал воедино огромное количество наглядных примеров неправильного толкования JMM. Да, прямо такие, где «реордеринги на TSO», и вот это все. Размеру статьи позавидовал бы и Лев Николаевич, но ее прочтение очень важно. В первую с точки зрения веры — после прочтения вы наконец поверите, что заигрывать с JMM не стоит.!', 3);

INSERT INTO message_rates (id, rate, message_id, user_id) VALUES
  (1, TRUE, 1, 1),
  (2, TRUE, 1, 2),
  (3, TRUE, 1, 3);

ALTER SEQUENCE rate_seq RESTART WITH 4;
ALTER SEQUENCE user_seq RESTART WITH 4;
ALTER SEQUENCE message_seq RESTART WITH 4;

INSERT INTO user_roles (user_id, role) VALUES
  (1, 'ROLE_USER'),
  (2, 'ROLE_USER'),
  (3, 'ROLE_USER');