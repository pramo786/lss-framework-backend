package com.lss.framework.controller;

import com.lss.framework.dto.request.CommonPaginationRequest;
import com.lss.framework.dto.response.EditionResponse;
import com.lss.framework.service.EditionService;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Data
@Builder
@RestController
@RequestMapping("/Api/Edition")
@RequiredArgsConstructor
class EditionController {

    private final EditionService editionService;

    @PostMapping("/GetAllEditions")
    public ResponseEntity<EditionResponse> getAllEditions(@RequestBody CommonPaginationRequest request,
                                                          @RequestHeader(HttpHeaders.AUTHORIZATION) String token) {
        return ResponseEntity.ok(editionService.getAllEditions(token, request));
    }

}