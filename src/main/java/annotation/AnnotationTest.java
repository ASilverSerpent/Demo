package annotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationTest {

	@SuppressWarnings("unused")
	public static void parseAnnotation() {
		try {
			Class<?> clazz = Class.forName("AnnotationTest");
			boolean isExist = clazz.isAnnotationPresent(Override.class);
			if (isExist) {
				Override o = (Override) clazz.getAnnotation(Override.class);
			}

			Method[] methods = clazz.getMethods();
			for (Method m : methods) {
				boolean isExist1 = m.isAnnotationPresent(Override.class);
				if (isExist1) {
					Override o = (Override) m.getAnnotation(Override.class);
				}
			}

			for (Method m : methods) {
				Annotation[] annotations = m.getAnnotations();
				for (Annotation a : annotations) {
					if (a instanceof Override) {
						Override o = (Override) a;
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
