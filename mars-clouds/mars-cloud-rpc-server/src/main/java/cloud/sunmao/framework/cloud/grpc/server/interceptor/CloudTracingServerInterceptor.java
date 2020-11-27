package cloud.sunmao.framework.cloud.grpc.server.interceptor;

import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;
import io.opentracing.Tracer;
import io.opentracing.contrib.grpc.TracingServerInterceptor;
import net.devh.boot.grpc.common.util.InterceptorOrder;
import net.devh.boot.grpc.server.interceptor.GrpcGlobalServerInterceptor;
import org.springframework.core.annotation.Order;

/**
 * 添加 opentracing 链路跟踪
 *
 * @author xdatk
 */
@GrpcGlobalServerInterceptor
@Order(InterceptorOrder.ORDER_TRACING_METRICS)
public class CloudTracingServerInterceptor implements ServerInterceptor {

    private final TracingServerInterceptor serverInterceptor;

    public CloudTracingServerInterceptor(Tracer tracer) {
        serverInterceptor = TracingServerInterceptor.newBuilder()
                .withVerbosity()
                .withStreaming()
                .withTracer(tracer)
                .build();
    }

    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall, Metadata metadata, ServerCallHandler<ReqT, RespT> serverCallHandler) {
        return serverInterceptor.interceptCall(serverCall, metadata, serverCallHandler);
    }
}
