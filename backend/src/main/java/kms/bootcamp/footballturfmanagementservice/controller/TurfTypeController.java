package kms.bootcamp.footballturfmanagementservice.controller;

import kms.bootcamp.footballturfmanagementservice.dto.TurfTypeRequest;
import kms.bootcamp.footballturfmanagementservice.dto.TurfTypeResponse;
import kms.bootcamp.footballturfmanagementservice.service.impl.TurfTypeServiceImpl;
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
@RequestMapping("/turfType")
public class TurfTypeController {

    @Autowired
    TurfTypeServiceImpl turfTypeService;

    @GetMapping()
    public ResponseEntity<TurfTypeResponse> retrieveTurfTypeList(@RequestBody TurfTypeRequest request) {
        return ResponseEntity.ok(turfTypeService.retrieveTurfTypeList(request));
    }

    @PostMapping()
    public ResponseEntity<TurfTypeResponse> performCreate(@RequestBody TurfTypeRequest request) {
        return ResponseEntity.ok(turfTypeService.performCreate(request));
    }

    @PutMapping()
    public ResponseEntity<TurfTypeResponse> performUpdate(@RequestBody TurfTypeRequest request) {
        return ResponseEntity.ok(turfTypeService.performUpdate(request));
    }

    @DeleteMapping()
    public ResponseEntity<TurfTypeResponse> performDelete(@RequestBody TurfTypeRequest request) {
        return ResponseEntity.ok(turfTypeService.performDelete(request));
    }
}
