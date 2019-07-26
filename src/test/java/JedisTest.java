import com.yangxj.cache.RedisUtils;
import redis.clients.jedis.Jedis;

/**
 * @author yangxj
 * @date 2019/7/26-23:42
 */
public class JedisTest {
    public static void main(String[] args) {
        Jedis jedis = RedisUtils.getConnection();
         RedisUtils.close(jedis);
    }
}
