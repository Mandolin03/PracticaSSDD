package es.ssdd.PracticaSSDD;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
@Controller
public class IndexController {
        @GetMapping("/")
        public String index() {
            return "index";
        }
    }

