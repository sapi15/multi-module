package com.multi.module.common.utils;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *     값 or 객체를 검사하고 값이 존재하지 않을경우,
 *     디폴트 값으로 치환하기 위한 기능 정의.
 * </pre>
 */
public class Nvls {

    /**
     * <pre>
     *     Validations.isNull wrapper
     * </pre>
     * @param target 검사 대상
     * @return boolean
     */
    private static boolean isNull(Object target) {

        return Validations.isNull(target);
    }

    /**
     * <pre>
     *     Validations.isNotNull wrapper
     * </pre>
     * @param target 검사 대상
     * @return boolean
     */
    private static boolean isNotNull(Object target) {

        return Validations.isNotNull(target);
    }

    /**
     * <pre>
     *     Validations.isEmpty wrapper
     * </pre>
     * @param target 검사 대상
     * @return boolean
     */
    private static boolean isEmpty(Object target) {

        return Validations.isEmpty(target);
    }

    /**
     * <pre>
     *     Validations.isNotEmpty wrapper
     * </pre>
     * @param target 검사 대상
     * @return boolean
     */
    private static boolean isNotEmpty(Object target) {

        return Validations.isNotEmpty(target);
    }

    /**
     * <pre>
     *     Validations.isArray wrapper
     * </pre>
     * @param target 검사 대상
     * @return boolean
     */
    private static boolean isArray(Object target) {

        return Validations.isArray(target);
    }

    /**
     * <pre>
     *     Validations.isList wrapper
     * </pre>
     * @param target 검사 대상
     * @return boolean
     */
    private static boolean isList(Object target) {

        return Validations.isList(target);
    }

    /**
     * <pre>
     *     대상맵안의 대상키와 맵핑되는 데이타가 존재하지 않을경우 디폴트 처리
     * </pre>
     * @param map 대상맵
     * @param key 대상키
     * @param def 디폴트
     * @return Object
     */
    public static Object nvl(Map map, String key, Object def) {

        Object value = isNotEmpty(map) && isNotEmpty(key) ? map.get(key) : def;

        return isNotEmpty(value) ? value : def;
    }

    /**
     * <pre>
     *     대상배열안의 인덱스와 맵핑되는 데이타가 존재하지 않을경우 디폴트 처리
     * </pre>
     * @param array 대상배열
     * @param index 대상인덱스
     * @param def 디폴트
     * @return Object
     */
    public static <T> T nvl(T[] array, int index, T def) {

        if (isNotEmpty(array) && index > -1 && array.length > index && isNotEmpty(array[index])) {

            return array[index];
        }

        return def;
    }

    /**
     * <pre>
     *     대상 문자열이 널일경우 디폴트 처리.
     * </pre>
     * @param target 대상문자열
     * @param def 디폴트문자열
     * @return String
     */
    public static String nvlStr(Object target, String def) {

        return isNotEmpty(target) ? target.toString() : def;
    }

    /**
     * <pre>
     *     대상 문자열이 널일경우 디폴트 처리("")
     * </pre>
     * @param target 대상문자열
     * @return String
     */
    public static String nvlStr(Object target) {

        return nvlStr(target, "");
    }

    /**
     * <pre>
     *     대상맵안의 대상키와 맵핑되는 문자열이 존재하지 않을경우 디폴트 처리
     * </pre>
     * @param map 대상맵
     * @param key 대상키
     * @param def 디폴트
     * @return String
     */
    public static String nvlStr(Map map, String key, String def) {

        return nvl(map, key, nvlStr(def)).toString();
    }

    /**
     * <pre>
     *     대상맵안의 대상키와 맵핑되는 문자열이 존재하지 않을경우 디폴트 처리("")
     * </pre>
     * @param map 대상맵
     * @param key 대상키
     * @return String
     */
    public static String nvlStr(Map map, String key) {

        return nvlStr(map, key, "");
    }

    /**
     * <pre>
     *     대상 데이타가 정수가 아닐경우 디폴트 처리
     * </pre>
     * @param target 대상데이타
     * @param def 디폴트정수
     * @return int
     */
    public static int nvlInt(Object target, int def) {

        return NumberUtils.toInt(nvlStr(target), def);
    }

    /**
     * <pre>
     *     대상 데이타가 정수가 아닐경우 디폴트 처리(0)
     * </pre>
     * @param target 대상데이타
     * @return int
     */
    public static int nvlInt(Object target) {

        return nvlInt(target, 0);
    }

    /**
     * <pre>
     *     대상 데이타가 정수가 아닐경우 디폴트 처리
     * </pre>
     * @param map 대상맵
     * @param key 대상키
     * @param def 디폴트정수
     * @return long
     */
    public static long nvlLong(Map map, String key, long def) {

        return NumberUtils.toLong(nvlStr(map, key), def);
    }

    /**
     * <pre>
     *     대상 데이타가 정수가 아닐경우 디폴트 처리
     * </pre>
     * @param target 대상데이타
     * @param def 디폴트정수
     * @return long
     */
    public static long nvlLong(Object target, long def) {

        return NumberUtils.toLong(nvlStr(target), def);
    }

    /**
     * <pre>
     *     대상 데이타가 정수가 아닐경우 디폴트 처리(0)
     * </pre>
     * @param target 대상데이타
     * @return long
     */
    public static long nvlLong(Object target) {

        return nvlLong(target, 0);
    }

    /**
     * <pre>
     *     대상맵안의 대상키와 맵핑되는 정수가 존재하지 않을경우 디폴트 처리
     * </pre>
     * @param map 대상맵
     * @param key 대상키
     * @param def 디폴트
     * @return int
     */
    public static int nvlInt(Map map, String key, int def) {

        return nvlInt(nvlStr(map, key), def);
    }

    /**
     * <pre>
     *     대상맵안의 대상키와 맵핑되는 정수가 존재하지 않을경우 디폴트 처리(0)
     * </pre>
     * @param map 대상맵
     * @param key 대상키
     * @return int
     */
    public static int nvlInt(Map map, String key) {

        return nvlInt(map, key, 0);
    }

    /**
     * <pre>
     *     대상 데이타가 실수가 아닐경우 디폴트 처리
     * </pre>
     * @param target 대상데이타
     * @param def 디폴트실수
     * @return double
     */
    public static double nvlDouble(Object target, double def) {

        return NumberUtils.toDouble(nvlStr(target), def);
    }

    /**
     * <pre>
     *     대상 데이타가 실수가 아닐경우 디폴트 처리(0.0)
     * </pre>
     * @param target 대상데이타
     * @return double
     */
    public static double nvlDouble(Object target) {

        return nvlDouble(target, 0.0);
    }

    /**
     * <pre>
     *     대상맵안의 대상키와 맵핑되는 실수가 존재하지 않을경우 디폴트 처리
     * </pre>
     * @param map 대상맵
     * @param key 대상키
     * @param def 디폴트
     * @return double
     */
    public static double nvlDouble(Map map, String key, double def) {

        return nvlDouble(nvlStr(map, key), def);
    }

    /**
     * <pre>
     *     대상맵안의 대상키와 맵핑되는 실수가 존재하지 않을경우 디폴트 처리(0.0)
     * </pre>
     * @param map 대상맵
     * @param key 대상키
     * @return double
     */
    public static double nvlDouble(Map map, String key) {

        return nvlDouble(map, key, 0.0);
    }

    /**
     * <pre>
     *     대상 데이타가 참/거짓을 나타내는 데이타가 아닐경우 디폴트 처리
     *     (true  : Y or TRUE  or 1)
     *     (false : N or FALSE or 0)
     * </pre>
     * @param target 대상데이타
     * @param def 디폴트
     * @return boolean
     */
    public static boolean nvlBoolean(Object target, boolean def) {

        String booleanStr = nvlStr(target);

        if ("Y".equalsIgnoreCase(booleanStr) || "TRUE".equalsIgnoreCase(booleanStr) || "1".equals(booleanStr)) {

            return true;

        } else if ("N".equalsIgnoreCase(booleanStr) || "FALSE".equalsIgnoreCase(booleanStr) || "0".equals(booleanStr)) {

            return false;

        } else {

            return def;
        }
    }

    /**
     * <pre>
     *     대상 데이타가 참/거짓을 나타내는 데이타가 아닐경우 디폴트 처리(false)
     * </pre>
     * @param target 대상데이타
     * @return boolean
     */
    public static boolean nvlBoolean(Object target) {

        return nvlBoolean(target, false);
    }

    /**
     * <pre>
     *     대상맵안의 대상키와 맵핑되는 실수가 존재하지 않을경우 디폴트 처리
     * </pre>
     * @param map 대상맵
     * @param key 대상키
     * @param def 디폴트
     * @return boolean
     */
    public static boolean nvlBoolean(Map map, String key, boolean def) {

        return nvlBoolean(nvlStr(map, key), def);
    }

    /**
     * <pre>
     *     대상맵안의 대상키와 맵핑되는 실수가 존재하지 않을경우 디폴트 처리(false)
     * </pre>
     * @param map 대상맵
     * @param key 대상키
     * @return boolean
     */
    public static boolean nvlBoolean(Map map, String key) {

        return nvlBoolean(map, key, false);
    }

    /**
     * <pre>
     *     대상데이타가 배열이 아니거나 사이즈가 0일경우 디폴트 처리
     * </pre>
     * @param target 대상데이타
     * @param def 디폴트배열
     * @return Object[]
     */
    public static Object[] nvlArray(Object target, Object[] def) {

        return isArray(target) && isNotEmpty(target) ? ((Object[]) target) : def;
    }

    /**
     * <pre>
     *     대상데이타가 배열이 아니거나 사이즈가 0일경우 디폴트 처리(new Object[]{})
     * </pre>
     * @param target 대상데이타
     * @return Object[]
     */
    public static Object[] nvlArray(Object target) {

        return nvlArray(target, new Object[]{});
    }

    /**
     * <pre>
     *     대상맵안의 데이타가 배열이 아니거나 사이즈가 0일경우 디폴트 처리
     * </pre>
     * @param map 대상맵
     * @param key 대상키
     * @param def 디폴트
     * @return Object[]
     */
    public static Object[] nvlArray(Map map, String key, Object[] def) {

        return nvlArray(nvl(map, key, null), def);
    }

    /**
     * <pre>
     *     대상맵안의 데이타가 배열이 아니거나 사이즈가 0일경우 디폴트 처리(new Object[]{})
     * </pre>
     * @param map 대상맵
     * @param key 대상키
     * @return Object[]
     */
    public static Object[] nvlArray(Map map, String key) {

        return nvlArray(map, key, new Object[]{});
    }

    /**
     * <pre>
     *     대상데이타가 리스트가 아니거나 사이즈가 0일경우 디폴트 처리
     * </pre>
     * @param target 대상데이타
     * @param def 디폴트리스트
     * @return List
     */
    public static List nvlList(Object target, List def) {

        return isList(target) && isNotEmpty(target) ? ((List) target) : def;
    }

    /**
     * <pre>
     *     대상데이타가 배열이 아니거나 사이즈가 0일경우 디폴트 처리(new ArrayList())
     * </pre>
     * @param target 대상데이타
     * @return List
     */
    public static List nvlList(Object target) {

        return nvlList(target, new ArrayList());
    }

    /**
     * <pre>
     *     대상맵안의 데이타가 리스트가 아니거나 사이즈가 0일경우 디폴트 처리
     * </pre>
     * @param map 대상맵
     * @param key 대상키
     * @param def 디폴트
     * @return List
     */
    public static List nvlList(Map map, String key, List def) {

        return nvlList(nvl(map, key, null), def);
    }

    /**
     * <pre>
     *     대상맵안의 데이타가 리스트가 아니거나 사이즈가 0일경우 디폴트 처리(new ArrayList())
     * </pre>
     * @param map 대상맵
     * @param key 대상키
     * @return List
     */
    public static List nvlList(Map map, String key) {

        return nvlList(map, key, new ArrayList());
    }
}