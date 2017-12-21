package com.zzz.utils;

import org.springframework.beans.BeanUtils;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by 胡胜钧 on 12/20 0020.
 */
public class ConvertUtils {

    /**
     * list po转vo
     * @param pos
     * @param voClass
     * @param <V>
     * @param <P>
     * @return
     */
    public static <V, P> List<V> convertPos2Vos(List<P> pos, Class<V> voClass) {
        return pos.stream()
                .map(po -> {
                    try {
                        V vo = voClass.newInstance();
                        BeanUtils.copyProperties(po, vo);
                        return vo;
                    } catch (InstantiationException | IllegalAccessException e) {
                        throw new IllegalArgumentException(String.format("pos转vos错误：%s", e.getMessage()));
                    }
                })
                .collect(Collectors.toList());
    }

}
