package test.guestbook.task;

import test.guestbook.task.model.Message;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Created by Qouer on 04.07.2016.
 */
public class MessageTestData {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

//    INSERT INTO messages (id, text, user_id) VALUES
//    (1, 'Java EE продолжает тонуть в хайпе микросервисов. Парни из IBM, RedHat и нескольких других компаний решили бросить ей спасательный круг, анонсировав инициативу MicroProfile.io. Задача — подогнать стандарты Java EE под современные тренды. Читай — натянуть Java EE на микросервисы. Причем в буквальном смысле. Например, предлагается чуть ли не стандартизировать максимальный размер джарника и время старта приложения. Что-то вроде «enlarge your ...», только наоборот.', 1),
//            (2, 'Ребята из Google залили публичные репозитории GitHub в свой движок BigQuery, и открыли к нему доступ всем желающим. Остается положить какие-то 300$ на счет, и вы легко сможете собирать всевозможную статистику по этому огромному датасету. Например, найти все использования пакета sun.misc :-) ', 2),
//            (3, 'Алексей Шипилев проделал титанический труд, и собрал воедино огромное количество наглядных примеров неправильного толкования JMM. Да, прямо такие, где «реордеринги на TSO», и вот это все. Размеру статьи позавидовал бы и Лев Николаевич, но ее прочтение очень важно. В первую с точки зрения веры — после прочтения вы наконец поверите, что заигрывать с JMM не стоит.!', 3);

    public static final Message MESSAGE_1 = new Message(1,
            null,
            "Java EE продолжает тонуть в хайпе микросервисов. " +
                    "Парни из IBM, RedHat и нескольких других компаний решили бросить ей спасательный круг, " +
                    "анонсировав инициативу MicroProfile.io. Задача — подогнать стандарты Java EE под современные тренды. " +
                    "Читай — натянуть Java EE на микросервисы. Причем в буквальном смысле. Например, " +
                    "предлагается чуть ли не стандартизировать максимальный размер джарника и время старта приложения. " +
                    "Что-то вроде «enlarge your ...», только наоборот.",
            LocalDateTime.parse("2016-07-04 15:15:15", formatter),
            UserTestData.USER_1);

    public static final Message MESSAGE_2 = new Message(2,
            null,
            "Ребята из Google залили публичные репозитории GitHub в свой движок BigQuery, и открыли к нему доступ всем желающим. " +
                    "Остается положить какие-то 300$ на счет, и вы легко сможете собирать всевозможную " +
                    "статистику по этому огромному датасету. Например, найти все использования пакета sun.misc :-) ",
            LocalDateTime.parse("2016-07-04 15:15:15", formatter),
            UserTestData.USER_2);

    public static final Message MESSAGE_3 = new Message(3,
            null,
            "Алексей Шипилев проделал титанический труд, и собрал воедино огромное количество наглядных " +
                    "примеров неправильного толкования JMM. Да, прямо такие, где «реордеринги на TSO», и вот это все. " +
                    "Размеру статьи позавидовал бы и Лев Николаевич, но ее прочтение очень важно. " +
                    "В первую с точки зрения веры — после прочтения вы наконец поверите, что заигрывать с JMM не стоит.!",
            LocalDateTime.parse("2016-07-04 15:15:15", formatter),
            UserTestData.USER_3);

}
