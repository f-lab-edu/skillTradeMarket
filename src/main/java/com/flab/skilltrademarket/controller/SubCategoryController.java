package com.flab.skilltrademarket.controller;

import com.flab.skilltrademarket.domain.category.dto.request.SubCategoryCreateRequest;
import com.flab.skilltrademarket.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SubCategoryController {

    private final SubCategoryService subCategoryService;

    @PostMapping("/stm/create/sub")
    public void create(@RequestBody SubCategoryCreateRequest createRequest) {
        subCategoryService.create(createRequest);
    }
}
