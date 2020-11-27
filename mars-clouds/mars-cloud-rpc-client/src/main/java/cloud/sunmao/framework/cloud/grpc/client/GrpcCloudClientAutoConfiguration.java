package cloud.sunmao.framework.cloud.grpc.client;

import cloud.sunmao.framework.cloud.grpc.client.interceptor.CloudTracingClientInterceptor;
import io.opentracing.Tracer;
import io.opentracing.contrib.spring.tracer.configuration.TracerAutoConfiguration;
import net.devh.boot.grpc.client.autoconfigure.GrpcClientAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Grpc Gloud 配置
 *
 * @author xdatk
 */
@Configuration
@EnableConfigurationProperties
@AutoConfigureBefore(value = {GrpcClientAutoConfiguration.class})
@AutoConfigureAfter(value = {TracerAutoConfiguration.class})
public class GrpcCloudClientAutoConfiguration {

    @Bean
    public CloudTracingClientInterceptor cloudTracingClientInterceptor(Tracer tracer) {
        return new CloudTracingClientInterceptor(tracer);
    }


}
