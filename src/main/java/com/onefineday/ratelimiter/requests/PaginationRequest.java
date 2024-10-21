package com.onefineday.ratelimiter.requests;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

@Data
public class PaginationRequest {

    String search = "";

    String sortBy = "";

    String sortOrder = "asc";

    @Min(value = 1, message = "PageNo must be at least 1")
    @Max(value = 1000, message = "PageNo must be less than or equal to 1000")
    Integer pageNo = 1;

    @Min(value = 5, message = "pageCount must be at least 5")
    @Max(value = 100, message = "pageCount must be less than or equal to 100")
    Integer pageCount = 10;

    public PageRequest getPageRequest() {
        Sort sort = Sort.by("id").descending();
        if(!this.getSortBy().isEmpty()){
            if (!this.getSortOrder().isEmpty()) {
                sort = this.getSortOrder().equals("asc")  ? Sort.by(this.getSortBy()).ascending() : Sort.by(this.getSortBy()).descending();
            } else {
                sort = Sort.by(this.getSortBy()).ascending();
            }
        }
        return PageRequest.of(this.getPageNo()-1, this.getPageCount(), sort);
    }
}
