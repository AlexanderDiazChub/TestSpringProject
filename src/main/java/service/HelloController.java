package service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Alexander on 08/05/2018.
 */
@RestController
public class HelloController {

    @RequestMapping("/hello")
    public Hello hello(
            @RequestParam(value = "name", defaultValue = "World") String name
    ) {
        return new Hello(
                String.format("Hello, %s!", name)
        );
    }

    public static class Hello {

        private final String content;

        public Hello(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }
    }

}
