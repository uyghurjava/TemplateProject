package ut.converter;

import org.apache.struts2.util.StrutsTypeConverter;

import java.util.Map;

/**
 * Created by AUser on 2015-12-21 021.
 */
public class DateConverter extends StrutsTypeConverter {

    @Override
    public Object convertFromString(Map map, String[] strings, Class aClass) {
        System.out.println("" + aClass);
        return null;
    }

    @Override
    public String convertToString(Map map, Object o) {
        System.out.println(o);
        return null;
    }
}
