package dohun.kim.runaway.util;

public class StringUtil {

    /**
     * @param string 첫글자를 소문자로 바꾸고 싶은 문자열
     * @return 첫글자가 소문자로 변환된 문자열을 반환
     */
    public static String getFirstLowerString(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }

        char[] c = string.toCharArray();
        c[0] = Character.toLowerCase(c[0]);

        return new String(c);
    }

    /**
     * @param string 첫글자를 대문자로 바꾸고 싶은 문자열
     * @return 첫글자가 대문자로 변환된 문자열을 반환
     */
    public static String getFirstUpperString(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }

        char[] c = string.toCharArray();
        c[0] = Character.toUpperCase(c[0]);

        return new String(c);
    }

    /**
     * @param classPackage 클래스 패키지 정보가 담긴 문자열 예) dohun.kim.Hello
     * @return 패키지에서 클래스 이름만 추출한 결과 반환
     */
    public static String getSimpleNameFromClassPackage(String classPackage) {
        String[] splited = classPackage.split("\\.");

        if (splited.length == 0) {
            return classPackage;
        }

        return getFirstLowerString(splited[splited.length - 1]);
    }
}
