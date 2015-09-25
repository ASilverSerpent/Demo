package core.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo {

	public static void classMessage(Object obj) {
		Class<? extends Object> clazz = obj.getClass();

		Method[] methods = clazz.getMethods();//clazz.getDeclaredMethods()该类自己声明的方法
		for (Method method : methods) {
			Class<?> returnType = method.getReturnType();
			String str = returnType.getName() + " " + method.getName() + "(";
			//参数列表
			Class<?>[] paramTypes = method.getParameterTypes();
			for (Class<?> param : paramTypes) {
				str += param.getName() + ",";
			}
			if(str.endsWith(",")) {
				str = str.substring(0, str.length() - 1);
			}
			str += ")";
			System.out.println(str);
		}
		
		Field[] fields = clazz.getDeclaredFields();
		for (Field field : fields) {
			Class<?> fieldType = field.getType();
			String typeName = fieldType.getName();
			String fieldName = field.getName();
			System.out.println( typeName + " " + fieldName);
		}
	}
	
	public static void main(String[] args) {
		classMessage("str");
	}
}
