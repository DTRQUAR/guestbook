package test.guestbook.task.repository;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Qouer on 04.07.2016.
 */

@ContextConfiguration({
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringJUnit4ClassRunner.class)
//@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public abstract class AbstractRepositoryTest {

    @Rule
    public ExpectedException thrown = ExpectedException.none();

}
