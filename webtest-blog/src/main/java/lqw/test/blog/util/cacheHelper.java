package lqw.test.blog.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class cacheHelper {
	
	public static void main(String[] args) throws Exception {
		cacheHelper.setValue( "haha");
		
		String value= (String) cacheHelper.getValue("aa");
		
		System.err.println(value);
		
		
		long a=new Date().getTime();
		Thread.sleep(3000);
		long b=new Date().getTime();
		System.err.println(Long.toString(b-a));
	}
	
	private static Map<String,Object> cacheMap= new HashMap<>();
	
	
	public static Object getValue(String key){
		return	cacheMap.get(key);
	}
	
	public static Object setValue(String key){
		String value =Long.toString(new Date().getTime());
		return	cacheMap.put(key , value);
	}
	
	public static boolean has(String key){
		if(cacheMap.containsKey(key)){
			long nowtime=new Date().getTime();
			long value=Long.parseLong((String) cacheMap.get(key));
			if(nowtime - value>1800000){
				cacheMap.remove(key);
				System.err.println("remove");
			}
		}
		return cacheMap.containsKey(key);		
	}

}
