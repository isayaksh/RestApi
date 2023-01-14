package study.restapi.factory;

import org.springframework.stereotype.Component;

@Component
public interface Factory {
    Object getInstance(String type);
}
