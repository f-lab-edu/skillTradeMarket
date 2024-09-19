package com.flab.skilltrademarket.domain.category.dto.response;

import com.flab.skilltrademarket.domain.category.SubCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.data.domain.Slice;

import java.util.List;
@Schema(description = "하위카테고리 페이징 응답 리스트")
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
