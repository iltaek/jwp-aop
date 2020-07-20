package study.aop.cglib;

import static org.assertj.core.api.Assertions.assertThat;

import net.sf.cglib.proxy.Enhancer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Created by iltaek on 2020/07/20 Blog : http://blog.iltaek.me Github : http://github.com/iltaek
 */
class CglibProxyHelloTargetTest {

    private Enhancer enhancer;

    @BeforeEach
    void setUp() {
        this.enhancer = new Enhancer();
        enhancer.setSuperclass(HelloTarget.class);
    }

    @Test
    void toUpperCase() {
        enhancer.setCallback(new UpperCaseMethodInterceptor((m, targetClass, args) -> m.getName().startsWith("say")));

        HelloTarget target = (HelloTarget) enhancer.create();

        assertThat(target.sayHi("schulz")).isEqualTo("HI SCHULZ");
        assertThat(target.sayHello("schulz")).isEqualTo("HELLO SCHULZ");
        assertThat(target.sayThankYou("schulz")).isEqualTo("THANK YOU SCHULZ");
        assertThat(target.pingpong("schulz")).isEqualTo("Pong schulz");
    }
}
