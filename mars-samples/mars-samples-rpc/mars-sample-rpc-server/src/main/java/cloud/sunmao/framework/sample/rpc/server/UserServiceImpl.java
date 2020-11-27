package cloud.sunmao.framework.sample.rpc.server;

import cloud.sunmao.framework.sample.rpc.api.UserGrpc;
import cloud.sunmao.framework.sample.rpc.api.UserOuterClass;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

/**
 * UserService 具体实现
 *
 * @author xdatk
 */
@GrpcService
public class UserServiceImpl extends UserGrpc.UserImplBase {

    @Override
    public void sayHello(UserOuterClass.HelloRequest request, StreamObserver<UserOuterClass.HelloReply> responseObserver) {
        responseObserver.onNext(UserOuterClass.HelloReply.getDefaultInstance());
        responseObserver.onCompleted();
    }
}
