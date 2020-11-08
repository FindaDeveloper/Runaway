package dohun.kim.runaway.util;

public class StringUtil {

    public static String getFirstLowerString(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }

        char[] c = string.toCharArray();
        c[0] = Character.toLowerCase(c[0]);

        return new String(c);
    }

    public static String getFirstUpperString(String string) {
        if (string == null || string.length() == 0) {
            return string;
        }

        char[] c = string.toCharArray();
        c[0] = Character.toUpperCase(c[0]);

        return new String(c);
    }

    public static String getSimpleNameFromClassPackage(String classPackage) {
        String[] splited = classPackage.split("\\.");

        if (splited.length == 0) {
            return classPackage;
        }

        return getFirstLowerString(splited[splited.length - 1]);
    }
}
