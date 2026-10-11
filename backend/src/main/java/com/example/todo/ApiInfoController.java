package com.example.todo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class ApiInfoController {
    @GetMapping("/api/health")
    public Map<String, String> health() { return Map.of("status", "ok", "application", "Daylist Task API"); }
}
