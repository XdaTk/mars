package cloud.sunmao.framework.sample.rpc.client;

import cloud.sunmao.framework.sample.rpc.api.UserGrpc;
import cloud.sunmao.framework.sample.rpc.api.UserOuterClass;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * 测试用Controller
 *
 * @author xdatk
 */
@RestController
@RequestMapping
public class IndexController {

    @GrpcClient("user")
    private UserGrpc.UserBlockingStub userBlockingStub;

    @GetMapping("/")
    public ResponseEntity<UserOuterClass.HelloReply> index(String name) {
        return ResponseEntity.ok(userBlockingStub.sayHello(UserOuterClass.HelloRequest.newBuilder().setName(name).build()));
    }


    @GetMapping("/date")
    public ResponseEntity<Date> date(String name) {
        return ResponseEntity.ok(new Date());
    }


}
