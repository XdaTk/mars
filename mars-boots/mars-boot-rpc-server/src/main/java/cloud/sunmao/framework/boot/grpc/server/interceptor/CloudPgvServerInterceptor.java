package cloud.sunmao.framework.boot.grpc.server.interceptor;

import io.envoyproxy.pgv.ValidatorIndex;
import io.envoyproxy.pgv.grpc.ValidatingServerInterceptor;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;

/**
 * 添加 opentracing 链路跟踪
 *
 * @author xdatk
 */
@GrpcGlobalServerInterceptor
public class CloudPgvServerInterceptor extends ValidatingServerInterceptor {

    public CloudPgvServerInterceptor(ValidatorIndex index) {
        super(index);
    }

}
