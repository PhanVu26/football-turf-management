package kms.bootcamp.footballturfmanagementservice.controller;

import kms.bootcamp.footballturfmanagementservice.dto.TurfRequest;
import kms.bootcamp.footballturfmanagementservice.dto.TurfResponse;
import kms.bootcamp.footballturfmanagementservice.service.impl.TurfServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/turfs")
public class TurfController {

    @Autowired
    TurfServiceImpl turfService;

    @GetMapping()
    public ResponseEntity<TurfResponse> getTurfs(@RequestBody TurfRequest request) {
        return ResponseEntity.ok(turfService.retrieveTurfList(request));
    }

    @PostMapping()
    public ResponseEntity<TurfResponse> performCreate(@RequestBody TurfRequest request) {
        return ResponseEntity.ok(turfService.performCreate(request));
    }

    @PutMapping()
    public ResponseEntity<TurfResponse> performUpdate(@RequestBody TurfRequest request) {
        return ResponseEntity.ok(turfService.performUpdate(request));
    }

    @DeleteMapping()
    public ResponseEntity<TurfResponse> performDelete(@RequestBody TurfRequest request) {
        return ResponseEntity.ok(turfService.performDelete(request));
    }

}
