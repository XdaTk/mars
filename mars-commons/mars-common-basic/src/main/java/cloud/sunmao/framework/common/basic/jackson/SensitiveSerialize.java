package cloud.sunmao.framework.common.basic.jackson;

import cloud.sunmao.framework.common.basic.annotation.SensitiveInfo;
import cloud.sunmao.framework.common.basic.enums.SensitiveTypeEnum;
import cloud.sunmao.framework.common.basic.util.SensitiveUtil;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;

import java.io.IOException;
import java.util.Objects;

/**
 * Jsckson 数据脱敏注解处理
 *
 * @author xdatk
 */
public class SensitiveSerialize extends JsonSerializer<String> implements ContextualSerializer {

    private SensitiveTypeEnum type;

    public SensitiveSerialize() {
    }

    public SensitiveSerialize(final SensitiveTypeEnum type) {
        this.type = type;
    }

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        switch (this.type) {
            case CHINESE_NAME: {
                jsonGenerator.writeString(SensitiveUtil.chineseName(s));
                break;
            }
            case ID_CARD: {
                jsonGenerator.writeString(SensitiveUtil.idCardNum(s));
                break;
            }
            case FIXED_PHONE: {
                jsonGenerator.writeString(SensitiveUtil.fixedPhone(s));
                break;
            }
            case MOBILE_PHONE: {
                jsonGenerator.writeString(SensitiveUtil.mobilePhone(s));
                break;
            }
            case ADDRESS: {
                jsonGenerator.writeString(SensitiveUtil.address(s, 4));
                break;
            }
            case EMAIL: {
                jsonGenerator.writeString(SensitiveUtil.email(s));
                break;
            }
            case BANK_CARD: {
                jsonGenerator.writeString(SensitiveUtil.bankCard(s));
                break;
            }
            case CNAPS_CODE: {
                jsonGenerator.writeString(SensitiveUtil.cnapsCode(s));
                break;
            }
            default: {
                jsonGenerator.writeString(s);
            }
        }
    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {

        if (beanProperty == null) {
            return serializerProvider.findNullValueSerializer(beanProperty);
        }

        if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
            SensitiveInfo sensitiveInfo = beanProperty.getAnnotation(SensitiveInfo.class);
            if (sensitiveInfo == null) {
                sensitiveInfo = beanProperty.getContextAnnotation(SensitiveInfo.class);
            }
            if (sensitiveInfo != null) {
                return new SensitiveSerialize(sensitiveInfo.value());
            }
        }
        return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);


    }
}

