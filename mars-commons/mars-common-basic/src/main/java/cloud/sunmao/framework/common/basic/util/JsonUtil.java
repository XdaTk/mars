package cloud.sunmao.framework.common.basic.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

/**
 * Json 工具类
 *
 * @author xdatk
 */
public class JsonUtil {


    private static final ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
    }

    private JsonUtil() {
    }

    /**
     * 获取标准 JSON 序列化方案
     */
    public static ObjectMapper jsonMapperConfig() {
        return mapper;
    }

    public static JsonNode readTree(InputStream stream) {
        try {
            return mapper.readTree(stream);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }


    public static JsonNode readTree(String json) {
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static <T> T treeToValue(JsonNode jsonNode, Class<T> valueType) {
        try {
            return mapper.treeToValue(jsonNode, valueType);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 使用泛型方法，把json字符串转换为相应的JavaBean对象。
     * (1)转换为普通JavaBean：readValue(json,Student.class)
     * (2)转换为List:readValue(json,List.class).但是如果我们想把json转换为特定类型的List，比如List<Student>，就不能直接进行转换了。
     * 因为readValue(json,List.class)返回的其实是List<Map>类型，你不能指定readValue()的第二个参数是List<Student>.class，所以不能直接转换。
     * 我们可以把readValue()的第二个参数传递为Student[].class.然后使用Arrays.asList();方法把得到的数组转换为特定类型的List。
     * (3)转换为Map：readValue(json,Map.class)
     * 我们使用泛型，得到的也是泛型
     *
     * @param jsonStr   要转换的JavaBean类型
     * @param valueType 原始json字符串数据
     * @return JavaBean对象
     */
    public static <T> T fromJson(String jsonStr, Class<T> valueType) {
        try {
            return mapper.readValue(jsonStr, valueType);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static <T> T fromJson(String jsonStr, TypeReference<T> valueType) {
        try {
            return mapper.readValue(jsonStr, valueType);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }


    public static <T> T fromJson(InputStream inputStream, Class<T> valueType) {
        try {
            return mapper.readValue(inputStream, valueType);
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * 把JavaBean转换为json字符串
     * (1)普通对象转换：toJSON(Student)
     * (2)List转换：toJSON(List)
     * (3)Map转换:toJSON(Map)
     * 我们发现不管什么类型，都可以直接传入这个方法
     *
     * @param object JavaBean对象
     * @return json字符串
     */
    public static String toJson(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

