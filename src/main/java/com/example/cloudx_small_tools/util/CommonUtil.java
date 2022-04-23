package com.example.cloudx_small_tools.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.Collection;
/**
 * @Description: List集合工具
 * @author: 10191
 * @date:2022/4/19 18:39
 **/
@Component
public class CommonUtil {

    public static final Long ZERO = 0L;

    public static boolean isNull(Collection<?> list){
        if (CollectionUtils.isEmpty(list)){
            return true;
        }
        return false;
    }

    public static void isNull(Collection<?> list, String e){
        if (CollectionUtils.isEmpty(list)){
            throw new IllegalArgumentException(e);
        }
    }

    public static boolean isNull(String str){
        if (StringUtils.isBlank(str)){
            return true;
        }
        return false;
    }

    public static void isNull(String str, String e){
        if (StringUtils.isBlank(str)){
            throw new IllegalArgumentException(e);
        }
    }

    public static boolean isNull(Object o){
        if (ObjectUtils.isEmpty(o)){
          return true;
        }
        return false;
    }

    public static void isNull(Object o, String e){
        if (ObjectUtils.isEmpty(o)){
            throw new IllegalArgumentException(e);
        }
    }

    public static boolean isZero(Long l){
        if (ZERO.compareTo(l) == 0) {
            return true;
        }
        return false;
    }
}
