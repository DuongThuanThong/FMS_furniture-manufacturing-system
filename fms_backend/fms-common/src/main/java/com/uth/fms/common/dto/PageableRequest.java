package com.uth.fms.common.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PageableRequest {

    //Default values
    private static final int DEFAULT_PAGE = 0;
    private static final int DEFAULT_SIZE = 10;
    private static final int MAX_SIZE = 100;
    private static final String DEFAULT_SORT_BY = "id";

    @Builder.Default
    private int page = DEFAULT_PAGE;

    @Builder.Default
    private int size = DEFAULT_SIZE;

    @Builder.Default
    private String sortBy = DEFAULT_SORT_BY;

    @Builder.Default
    private String direction = "ASC";


    /**
     * Validate và giới hạn size
     */
    void validPage(){
        if (page < 0 ) page = DEFAULT_PAGE;
        if (size <= 0) size = DEFAULT_SIZE;
        if (size > 100) size = MAX_SIZE;

        if (this.sortBy == null || this.sortBy.trim().isEmpty() || this.sortBy.contains(" ")) {
            this.sortBy = DEFAULT_SORT_BY;
        }

        if (this.direction == null || (!this.direction.equalsIgnoreCase("ASC") && !this.direction.equalsIgnoreCase("DESC"))) {
            this.direction = "ASC";
        }
    }

    /**
     * Chuyển đổi sang Spring Pageable
     */
    public Pageable toPageable() {
        validPage();

        Sort.Direction sortDirection = direction.equalsIgnoreCase("DESC")
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return PageRequest.of(this.page, this.size, Sort.by(sortDirection, this.sortBy));
    }

}
