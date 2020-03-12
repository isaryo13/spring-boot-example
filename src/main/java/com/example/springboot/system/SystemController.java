package com.example.springboot.system;

import static java.util.stream.Collectors.toMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.StreamSupport;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.boot.info.InfoProperties.Entry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/system")
@Slf4j
public class SystemController
{
    private final BuildProperties buildProperties;

    @Autowired
    SystemController(BuildProperties buildProperties) {
        this.buildProperties = buildProperties;
    }

    @GetMapping
    public Map<String, Object> get() {
        return Map.of("properties", getSystemProperties(), "buildInfo", getBuildProperties());
    }

    @GetMapping("/properties")
    public Map<String, Object> getSystemProperties() {
        return System.getProperties()
            .keySet()
            .stream()
            .collect(toMap(Object::toString, System.getProperties()::get, (a, b) -> b, TreeMap::new));
    }

    @GetMapping({
        "/build-info",
        "/buildInfo"
    })
    public Map<String, String> getBuildProperties() {
        return StreamSupport.stream(buildProperties.spliterator(), false)
            .collect(toMap(Entry::getKey, Entry::getValue, (a, b) -> b, TreeMap::new));
    }
}
