package cloud.sunmao.framework.boot.grpc.client.interceptor;

import io.envoyproxy.pgv.ValidatorIndex;
import io.envoyproxy.pgv.grpc.ValidatingClientInterceptor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;

/**
 * 添加参数验证
 *
 * @author xdatk
 */
@GrpcGlobalClientInterceptor
public class CloudPgvClientInterceptor extends ValidatingClientInterceptor {

    public CloudPgvClientInterceptor(ValidatorIndex index) {
        super(index);
    }

}
