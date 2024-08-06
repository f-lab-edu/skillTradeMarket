package com.flab.skilltrademarket.domain.category.dto.response;

import com.flab.skilltrademarket.domain.category.SubCategory;
import org.springframework.data.domain.Slice;

import java.util.List;

public record SubCategorySliceListResponse(
        List<SubCategorySliceResponse> subCategorySliceResponses,
        boolean hasNext
) {
    public static SubCategorySliceListResponse from(Slice<SubCategory> subCategories) {
        List<SubCategorySliceResponse> sliceResponseList = subCategories.stream()
                .map(SubCategorySliceResponse::from)
                .toList();
        return new SubCategorySliceListResponse(sliceResponseList, subCategories.hasNext());
    }
}
