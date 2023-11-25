package com.med.demo.functionInterface;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class FunctionInterfaceTest {

    @FunctionalInterface
    interface ActivityFunction<T, R> {
        R deal(T a);
    }
    @Test
    public void ft() {
        Map<String ,ActivityFunction<String, String>> functionMap = new HashMap<>();
        functionMap.put("function0" , FunctionInterfaceTest::function0);
        functionMap.put("function1" , FunctionInterfaceTest::function1);

        String functionKey = "function0";
        ActivityFunction function = functionMap.get(functionKey);
        if(function != null){
            function.deal("tt");
        }

    }

    public static String function0(String str) {
        System.out.println(str);
        return str;
    }

    public static String function1(String str) {
        return null;
    }
}
