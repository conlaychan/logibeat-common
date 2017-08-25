package com.logibeat.cloud.common.utils;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.guava.GuavaModule;
import com.logibeat.cloud.common.model.Response;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * 简单封装Jackson，实现JSON String<->Java Object的Mapper.<br>
 */
public class JsonMapper {

    /**
     * 创建只输出非Null且非Empty(如List.isEmpty)的属性到Json字符串的Mapper,建议在外部接口中使用.
     */
    public static final JsonMapper NON_EMPTY = new JsonMapper(Include.NON_EMPTY);
    /**
     * 创建只输出初始值被改变的属性到Json字符串的Mapper, 最节约的存储方式，建议在内部接口中使用。
     */
    public static final JsonMapper NON_DEFAULT = new JsonMapper(Include.NON_DEFAULT);

    public static final JsonMapper ALWAYS = new JsonMapper(Include.ALWAYS);
    public static final JsonMapper NON_NULL = new JsonMapper(Include.NON_NULL);

    private final ObjectMapper mapper = new ObjectMapper();

    private JsonMapper(Include include) {
        //设置输出时包含属性的风格，此设置仅对序列化有效，反序列化时不同的 Include 并没有区别
        mapper.setSerializationInclusion(include);
        //设置输入时忽略在JSON字符串中存在但Java对象实际没有的属性
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        //禁止使用int代表Enum的order()來反序列化Enum,非常危险
        mapper.configure(DeserializationFeature.FAIL_ON_NUMBERS_FOR_ENUMS, true);
        // 注册要支持的第三方类库
        mapper.registerModule(new GuavaModule());
    }

    public String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化无泛型的简单 bean
     */
    public static <T> T fromJson(String jsonString, Class<T> clazz) {
        try {
            return JsonMapper.NON_NULL.getMapper().readValue(jsonString, clazz);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 反序列化带有泛型的复杂对象，如List<Bean>, 先使用函數 constructParametricType 构造类型,然后调用本函数.
     *
     * @see #constructParametricType(Class, Class...)
     */
    public static <T> T fromJson(String jsonString, JavaType javaType) {
        try {
            return JsonMapper.NON_NULL.getMapper().readValue(jsonString, javaType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> fromJsonToList(String json, Class<T> elementClass) {
        return fromJson(json, constructParametricType(List.class, elementClass));
    }

    public static <T> Response<T> fromJsonToResponse(String json, Class<T> dataClass) {
        return fromJson(json, constructParametricType(Response.class, dataClass));
    }

    public static <K, V> Map<K, V> fromJsonToMap(String json, Class<K> keyClass, Class<V> valueClass) {
        return fromJson(json, constructParametricType(Map.class, keyClass, valueClass));
    }

    /**
     * 构造泛型的 Java Type如:<br>
     * ArrayList<MyBean>, 则调用 constructParametricType(ArrayList.class,MyBean.class)<br>
     * HashMap<String,MyBean>, 则调用 constructParametricType(HashMap.class,String.class, MyBean.class)
     */
    public static JavaType constructParametricType(Class<?> parametrized, Class<?>... elementClasses) {
        return JsonMapper.NON_NULL.getMapper().getTypeFactory().constructParametricType(parametrized, elementClasses);
    }

    /**
     * 取出Mapper做进一步的设置或使用其他序列化API.
     */
    public ObjectMapper getMapper() {
        return mapper;
    }
}