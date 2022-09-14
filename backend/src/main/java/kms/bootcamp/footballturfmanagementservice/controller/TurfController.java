package kms.bootcamp.footballturfmanagementservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TurfController {

    @GetMapping(value = "/turfs")
    public List<String> getTurfs() {
        List<String> turfs = new ArrayList<>();
        turfs.add("A");
        return turfs;
    }
}
