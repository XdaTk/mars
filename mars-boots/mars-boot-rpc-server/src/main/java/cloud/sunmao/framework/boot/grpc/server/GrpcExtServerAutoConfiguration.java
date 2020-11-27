package cloud.sunmao.framework.boot.grpc.server;

import cloud.sunmao.framework.boot.grpc.server.interceptor.CloudPgvServerInterceptor;
import io.envoyproxy.pgv.ReflectiveValidatorIndex;
import net.devh.boot.grpc.server.autoconfigure.GrpcServerAutoConfiguration;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Grpc 扩展配置
 *
 * @author xdatk
 */
@Configuration
@EnableConfigurationProperties
@AutoConfigureBefore(value = {GrpcServerAutoConfiguration.class})
public class GrpcExtServerAutoConfiguration {

    public CloudPgvServerInterceptor cloudPgvServerInterceptor() {
        return new CloudPgvServerInterceptor(new ReflectiveValidatorIndex());
    }

}
