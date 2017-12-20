package com.zzz.support;

import lombok.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 要加入getter，setter才能正确的被转换为json
 * @author
 * @date 2017/11/2
 */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> implements Serializable {

    private static final long serialVersionUID = -2101017243893880821L;

    private int curPage;
    private int size;
    private int totalPages;
    private long totalElements;
    private List<T> content;

    /**
     * 将springdatajpa返回的page转换为自己封装的pageResult
     * @param page
     * @param <T>
     * @param <R>
     * @return
     */
    public static <T, R>PageResult<T> convert(Page<R> page, Class<T> targetClass) {
        PageResult<T> pageResult = PageResult.<T>builder()
                .curPage(page.getNumber() + 1)
                .size(page.getSize())
                .totalPages(page.getTotalPages())
                .totalElements(page.getTotalElements())
                .build();
        List<R> sourceList = page.getContent();

        if (CollectionUtils.isEmpty(sourceList)) {
            pageResult.setContent(Collections.emptyList());
            return pageResult;
        }

        List<T> targetList = sourceList.stream()
                .map(r -> {
                    try {
                        T t = targetClass.newInstance();
                        BeanUtils.copyProperties(r, t);
                        return t;
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new IllegalArgumentException(String.format("泛型转换出现异常！异常信息：%s", e.getMessage()));
                    }
                })
                .collect(Collectors.toList());

        pageResult.setContent(targetList);

        return pageResult;
    }

}
