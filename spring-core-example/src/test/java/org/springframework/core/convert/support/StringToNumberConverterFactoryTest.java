package org.springframework.core.convert.support;

import org.junit.Test;
import org.springframework.core.convert.converter.ConverterFactory;

public class StringToNumberConverterFactoryTest {

    @Test
    public void test() {
        System.out.println("----------------StringToNumberConverterFactory---------------");
        ConverterFactory<String, Number> converterFactory = new StringToNumberConverterFactory();
        // 注意：这里不能写基本数据类型。如int.class将抛错
        System.out.println(converterFactory.getConverter(Integer.class).convert("1").getClass());
        System.out.println(converterFactory.getConverter(Double.class).convert("1.1").getClass());
        System.out.println(converterFactory.getConverter(Byte.class).convert("0x11").getClass());
    }

}
