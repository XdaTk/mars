package cloud.sunmao.framework.cloud.grpc.server;

import cloud.sunmao.framework.cloud.grpc.server.interceptor.CloudPgvServerInterceptor;
import cloud.sunmao.framework.cloud.grpc.server.interceptor.CloudTracingServerInterceptor;
import io.envoyproxy.pgv.ReflectiveValidatorIndex;
import io.opentracing.Tracer;
import io.opentracing.contrib.spring.tracer.configuration.TracerAutoConfiguration;
import net.devh.boot.grpc.server.autoconfigure.GrpcServerAutoConfiguration;
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
@AutoConfigureBefore(value = {GrpcServerAutoConfiguration.class})
@AutoConfigureAfter(value = {TracerAutoConfiguration.class})
public class GrpcCloudServerAutoConfiguration {

    @Bean
    public CloudTracingServerInterceptor cloudTracingServerInterceptor(Tracer tracer) {
        return new CloudTracingServerInterceptor(tracer);
    }

    public CloudPgvServerInterceptor cloudPgvServerInterceptor() {
        return new CloudPgvServerInterceptor(new ReflectiveValidatorIndex());
    }

}
