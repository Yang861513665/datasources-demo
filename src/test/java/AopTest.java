import com.yangxj.controller.TestAopControlller;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author yangxj
 * @date 2019/7/28-11:38
 */
@ComponentScan("com.yangxj.*")
@EnableAspectJAutoProxy
public class AopTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AopTest.class);
        TestAopControlller controller = context.getBean(TestAopControlller.class);
        System.out.println(controller.testAop());
    }
}
