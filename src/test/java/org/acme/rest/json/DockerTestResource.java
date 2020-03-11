package org.acme.rest.json;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;

import java.lang.reflect.Field;
import java.util.Collections;
import java.util.Map;

public class DockerTestResource implements QuarkusTestResourceLifecycleManager {


    @Override
    public Map<String, String> start() {
        System.out.println("############### Resource started! ###################");
        return Collections.emptyMap();
    }

    @Override
    public void stop() {

    }

    @Override
    public void inject(Object testInstance) {
        Class<?> c = testInstance.getClass();
        while (c != Object.class) {
            System.out.println("Find class: " + c.getName());
            for (Field f : c.getDeclaredFields()) {
                System.out.println("Find field: " + f.getName());
                DockerService ano = f.getAnnotation(DockerService.class);
                System.out.println("Find annotation: " + ano);
                if (ano != null) {
                    if (!DockerComposeService.class.isAssignableFrom(f.getType())) {
                        throw new RuntimeException("@DockerService can only be used on fields of type DockerComposeService");
                    }

                    f.setAccessible(true);
                    try {
                        f.set(testInstance, new DockerComposeService(ano.value()));
                        return;
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
            c = c.getSuperclass();
        }
    }
}
