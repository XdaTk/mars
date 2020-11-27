package cloud.sunmao.framework.boot.rest;

import com.google.protobuf.Message;
import com.google.protobuf.util.JsonFormat;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;
import org.springframework.http.converter.protobuf.ProtobufJsonFormatHttpMessageConverter;

/**
 * ProtoBuf Rest Gloud 配置
 *
 * @author xdatk
 */
@Configuration
@AutoConfigureBefore(value = {HttpMessageConvertersAutoConfiguration.class})
public class RestAutoConfiguration {

    @Bean
    @ConditionalOnClass(value = {Message.class})
    public ProtobufJsonFormatHttpMessageConverter protobufJsonFormatHttpMessageConverter() {
        return new ProtobufJsonFormatHttpMessageConverter(
                JsonFormat.parser().ignoringUnknownFields(),
                JsonFormat.printer().printingEnumsAsInts());
    }


    @Bean
    @ConditionalOnClass(value = {Message.class})
    public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }



}
