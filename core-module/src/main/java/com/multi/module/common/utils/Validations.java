package com.multi.module.common.utils;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     값 or 객체를 검사하기위한 관련기능 정의
 * </pre>
 */
public class Validations {

    /**
     * <pre>
     *     Nvls.nvlStr wrapper
     * </pre>
     * @param target 대상
     * @return String
     */
    private static String nvlStr(Object target) {

        return Nvls.nvlStr(target);
    }

    /**
     * <pre>
     *     대상이 널인지를 검사
     * </pre>
     * @param target 검사 대상
     * @return boolean
     */
    public static boolean isNull(Object target) {

        return target == null;
    }

    /**
     * <pre>
     *     대상이 널이 아닌지를 검사
     * </pre>
     * @param target 검사 대상
     * @return boolean
     */
    public static boolean isNotNull(Object target) {

        return !isNull(target);
    }

    /**
     * <pre>
     *     대상이 비어있는지를 검사
     * </pre>
     * @param target 검사 대상
     * @return boolean
     */
    public static boolean isEmpty(Object target) {

        if (isNull(target)) {

            return true;

        } else if (isString(target)) {

            return StringUtils.isEmpty((String) target);

        } else if (isArray(target)) {

            return ArrayUtils.isEmpty((Object[]) target);

        } else if (isList(target)) {

            return CollectionUtils.isEmpty((List) target);

        } else if (isMap(target)) {

            return MapUtils.isEmpty((Map) target);

        } else {

            return false;
        }
    }

    /**
     * 대상이 비어있지 않은지를 검사
     * @param target 검사 대상
     * @return boolean
     */
    public static boolean isNotEmpty(Object target) {

        return !isEmpty(target);
    }

    /**
     * <pre>
     *     대상이 문자열인지를 검사
     * </pre>
     * @param target 검사 대상
     * @return boolean
     */
    public static boolean isString(Object target) {

        return target instanceof String;
    }

    /**
     * <pre>
     *     대상이 숫자인지를 검사
     * </pre>
     * @param target 검사 대상
     * @return boolean
     */
    public static boolean isNumber(Object target) {

        return NumberUtils.isNumber(nvlStr(target));
    }

    /**
     * <pre>
     *     대상이 리스트인지를 검사
     * </pre>
     * @param target 검사 대상
     * @return boolean
     */
    public static boolean isList(Object target) {

        return target instanceof List;
    }

    /**
     * <pre>
     *     대상이 배열인지를 검사
     * </pre>
     * @param target 검사 대상
     * @return boolean
     */
    public static boolean isArray(Object target) {

        return isNotNull(target) && target.getClass().isArray();
    }

    /**
     * <pre>
     *     대상이 맵인지를 검사
     * </pre>
     * @param target 검사 대상
     * @return boolean
     */
    public static boolean isMap(Object target) {

        return target instanceof Map;
    }

    /**
     * <pre>
     *     대상이 불대수인지를 검사
     * </pre>
     * @param target 검사 대상
     * @return boolean
     */
    public static boolean isBoolean(Object target) {

        return target instanceof Boolean;
    }
}
