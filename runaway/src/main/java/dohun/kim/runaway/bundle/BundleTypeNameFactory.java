package dohun.kim.runaway.bundle;

import com.squareup.javapoet.TypeName;

public class BundleTypeNameFactory {

    public static String getNameForBundle(TypeName typeName) {
        if (typeName.isBoxedPrimitive() || typeName.isPrimitive()) {
            return capitalize(typeName.toString());
        } else if (typeName.toString().equals("java.lang.String")) {
            return "String";
        } else {
            return "Serializable";
        }
    }

    private static String capitalize(String name) {
        if (name != null && name.length() != 0) {
            char[] chars = name.toCharArray();
            chars[0] = Character.toUpperCase(chars[0]);
            return new String(chars);
        } else {
            return name;
        }
    }
}
