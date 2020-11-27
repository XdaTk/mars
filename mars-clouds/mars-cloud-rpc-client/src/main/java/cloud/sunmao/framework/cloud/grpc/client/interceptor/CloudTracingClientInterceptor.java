package cloud.sunmao.framework.cloud.grpc.client.interceptor;

import io.grpc.*;
import io.opentracing.Tracer;
import io.opentracing.contrib.grpc.TracingClientInterceptor;
import net.devh.boot.grpc.client.interceptor.GrpcGlobalClientInterceptor;
import net.devh.boot.grpc.common.util.InterceptorOrder;
import org.springframework.core.annotation.Order;

/**
 * 添加 opentracing 链路跟踪
 *
 * @author xdatk
 */
@GrpcGlobalClientInterceptor
@Order(InterceptorOrder.ORDER_TRACING_METRICS)
public class CloudTracingClientInterceptor implements ClientInterceptor {

    private final TracingClientInterceptor clientInterceptor;

    public CloudTracingClientInterceptor(Tracer tracer) {
        clientInterceptor =TracingClientInterceptor.newBuilder()
                .withTracer(tracer)
                .withStreaming()
                .withVerbosity()
                .build();
    }

    @Override
    public <ReqT, RespT> ClientCall<ReqT, RespT> interceptCall(MethodDescriptor<ReqT, RespT> methodDescriptor, CallOptions callOptions, Channel channel) {
        return clientInterceptor.interceptCall(methodDescriptor, callOptions, channel);
    }
}
