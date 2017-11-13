package allen.gong.redis;


public class RedisTest {

	public static void main(String[] args) {
	}

//	public static void tryJedis() throws URISyntaxException {
//		Jedis jedis = new Jedis(new URI("redis://10.58.5.74:6379/0"));
//		String value = jedis.get("name");
//		System.out.println(value);
//		jedis.close();
//	}

//	public static void tryLettuce() {
//		RedisConnection connection = null;
//		LettuceConnectionFactory redisFactory = null;
//		try{
//			RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("10.58.5.74", 6379);
//			config.setDatabase(0);
//			redisFactory = new LettuceConnectionFactory(config);
//			redisFactory.initConnection();
//			connection = redisFactory.getConnection();
//			Object result = connection.execute("get", "name".getBytes());
//			System.out.println(result);
//		} finally {
//			if(connection != null){
//				connection.close();
//			}
//			if(redisFactory != null){
//				redisFactory.destroy();
//			}
//		}
//	}
}
