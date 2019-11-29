import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import org.junit.Test;


public class TestEventBus {

    @Test
    public void testReceiveEvent() throws Exception {

        EventBus eventBus = new EventBus("test");
        EventListener listener = new EventListener();

        eventBus.register(listener);

        eventBus.post(new TestEvent(200));
        eventBus.post(new TestEvent(300));
        eventBus.post(new TestEvent(400));

}

}
class EventListener{
    @Subscribe
    public void test(TestEvent testEvent){
        System.out.println("eventListener: " + testEvent.getMessage());
    }
}
class TestEvent {
    private final int message;
    public TestEvent(int message) {
        this.message = message;
        System.out.println("event: " + message);
    }
    public int getMessage() {
        return message;
    }
}
