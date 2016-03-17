import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Demo {

	public static void main(String[] args) throws Exception{
		Long a = 2L;
//		test2();
//		String[] str = new String[]{};
//		System.out.println(str[0]);
//		try {
//			int i = 1/0;
//			System.out.println("try");
//		} catch(Exception ex) {
//			ex.printStackTrace();
////			throw new RuntimeException(ex);
//			System.out.println("catch");
//		}
//		System.out.println("other");
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(formatter.parse("2015-11"));
		long startTime = calendar.getTimeInMillis() / 1000;
		System.out.println(startTime);
		calendar.add(Calendar.MONTH, 1);
		System.out.println(calendar.getTime().toLocaleString());
//		calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		long endTime = calendar.getTimeInMillis() / 1000;
		System.out.println(new Date(1427817600000L).toLocaleString());//1438358400000
		
//		date();
	}
	
	//合并去重List
	public static void test2(){
		List<String> list1 = new ArrayList<String>();
		List<String> list2 = new ArrayList<String>();

		list1.add("11");
		list1.add("12");
		list1.add("13");
		list2.add("11");
		list2.add("14");
		list2.add("12");
		
		list1.removeAll(list2);
		list1.addAll(list2);
		System.out.println(list1);
		
		//-----------------------------
		
		Set<Object> set = new HashSet<Object>();  
		set.addAll(list1);  
		set.addAll(list2);
		List<Object> c = new ArrayList<Object>(set);
		System.out.println(c);
	}
	
	public static void test1(){
		Map<String, String> map = new LinkedHashMap<String, String>();
		map.put("1", "11");
		map.put("2", "12");
		map.put("3", "11");
		map.put("4", "12");
		map.put("5", "11");
		map.put("6", "12");
		map.put("7", "11");
		map.put("8", "12");
		List<String> list = new ArrayList<String>();
		list.add("11");
		list.add("12");
		List<String> keys = new ArrayList<String>();
		for (String key : map.keySet()) {
			for (String str : list) {
				if (map.get(key).equals(str)) {
					keys.add(key);
				}
			}
		}
		System.out.println(keys);
	}

	public static void date(){
		try {
			String str = "2015-07-02";
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date =  sdf.parse(str);
		
			
			Calendar calendar = Calendar.getInstance();   
			calendar.setTime(date);
			
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMinimum(Calendar.DAY_OF_MONTH)); 
//			calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMinimum(Calendar.HOUR_OF_DAY));
//			calendar.add(Calendar.HOUR, 1);
			calendar.set(Calendar.HOUR, 1);
			System.out.println(calendar.getTime().toLocaleString());
			System.out.println(calendar.getTimeInMillis());
			System.out.println(calendar.getTime().getTime());
			
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
//			calendar.set(Calendar.HOUR_OF_DAY, calendar.getActualMaximum(Calendar.HOUR_OF_DAY));
//			calendar.add(Calendar.HOUR, 1);
			calendar.add(Calendar.HOUR, 23);
			System.out.println(calendar.getTime().toLocaleString());
			System.out.println(calendar.getTimeInMillis());
			System.out.println(calendar.getTime().getTime());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
