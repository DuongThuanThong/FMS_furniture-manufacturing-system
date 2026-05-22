package com.uth.fms.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {
    private List<T> content;        // Dữ liệu trang hiện tại
    private long totalElements;     // Tổng số records
    private int totalPages;         // Tổng số trang
    private int size;               // Size mỗi trang
    private int number;             // Trang hiện tại (0-indexed)


    /**
     *  Dữ liệu là Entity, cần map sang DTO
     */
    public static <T, R> PageResponse<R> from(Page<T> page, java.util.function.Function<T, R> mapper) {
        List<R> mappedContent = page.getContent()
                                    .stream()
                                    .map(mapper)
                                    .collect(Collectors.toList());

        return new PageResponse<>(
                mappedContent,
                page.getTotalElements(),
                page.getTotalPages(),
                page.getSize(),
                page.getNumber()
        );
    }
}


