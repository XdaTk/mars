package cloud.sunmao.framework.boot.grpc.client;

import cloud.sunmao.framework.boot.grpc.client.interceptor.CloudPgvClientInterceptor;
import io.envoyproxy.pgv.ReflectiveValidatorIndex;
import net.devh.boot.grpc.client.autoconfigure.GrpcClientAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Grpc 扩展 配置
 *
 * @author xdatk
 */
@Configuration
@EnableConfigurationProperties
@AutoConfigureBefore(value = {GrpcClientAutoConfiguration.class})
public class GrpcExtClientAutoConfiguration {

    @Bean
    public CloudPgvClientInterceptor cloudPgvClientInterceptor() {
        return new CloudPgvClientInterceptor(new ReflectiveValidatorIndex());
    }


}
