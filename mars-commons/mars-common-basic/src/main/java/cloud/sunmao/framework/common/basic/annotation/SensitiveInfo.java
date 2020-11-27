package cloud.sunmao.framework.common.basic.annotation;


import cloud.sunmao.framework.common.basic.enums.SensitiveTypeEnum;
import cloud.sunmao.framework.common.basic.jackson.SensitiveSerialize;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 数据脱敏注解
 * 可以参考 {@link SensitiveTypeEnum}
 *
 * @author xdatk
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveSerialize.class)
public @interface SensitiveInfo {
    /**
     * 脱敏类型
     */
    SensitiveTypeEnum value();
}
