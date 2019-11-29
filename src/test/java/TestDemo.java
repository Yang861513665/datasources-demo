import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.yangxj.Application;
import com.yangxj.common.TestDynamicDataSource;
import com.yangxj.entity.User;
//import com.yangxj.entity.User_;
import com.yangxj.mapper1.UserMapper1;
import com.yangxj.mapper2.UserMapper2;
//import com.yangxj.repository.UserRepository;
import com.yangxj.service.UserService;
import com.yangxj.service.UserService2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * @author yangxj
 * @date 2019/5/20-21:29
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringRunner.class)
public class TestDemo {
    @Autowired
    UserService userService;
    @Autowired
    UserService2 userService2;
    @Test
    public void add() throws SQLException {
        userService.add();
        userService2.add();
    }
}
