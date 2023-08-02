package dev.belavirag.arrayandstring;

import java.util.Arrays;

public class NameRepository {
    private static String[] names = new String[0];

    public static int getSize() {
        return names.length;
    }

    public static void setNames(String[] names) {
        NameRepository.names = names;
    }

    public static void clear() {
        names = new String[0];
    }

    public static String[] findAll() {
        return names;
    }

    public static String find(final String fullName) {
        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(fullName)) {
                return names[i];
            }
        }

        return null;
    }

    private static String[] addToArray(String[] arr, String str) {
        String[] copyOf = Arrays.copyOf(arr, arr.length + 1);
        copyOf[copyOf.length - 1] = str;
        return copyOf;
    }

    public static boolean add(final String fullName) {
        // exists
        if (find(fullName) != null) {
            return false;
        }

        names = addToArray(names, fullName);
        return true;
    }

    public static String[] findByFirstName(final String firstName) {
        String[] result = new String[0];

        for (String name : names) {
            String[] nameSplit = name.split(" ");
            if (nameSplit[0].equals(firstName)) {
                // first name matches, append
                result = addToArray(result, name);
            }
        }

        return result;
    }

    public static String[] findByLastName(final String lastName) {
        String[] result = new String[0];

        for (String name : names) {
            String[] nameSplit = name.split(" ");
            if (nameSplit[nameSplit.length - 1].equals(lastName)) {
                // last name matches, append
                result = addToArray(result, name);
            }
        }

        return result;
    }

    public static boolean remove(final String fullName) {
        // check first if name actually exists
        boolean exists = false;
        for (String name : names) {
            if (name.equals(fullName)) {
                exists = true;
                break;
            }
        }

        if (!exists) {
            return false;
        }

        String[] newNames = new String[0];

        for (String name : names) {
            if (name.equals(fullName)) {
                continue;
            }

            newNames = addToArray(newNames, name);
        }

        setNames(newNames);
        return true;
    }

    public static boolean update(final String original, final String updatedName) {
        if (find(original) == null) {
            return false;
        }

        if (find(updatedName) != null) {
            return false;
        }

        for (int i = 0; i < names.length; i++) {
            if (names[i].equals(original)) {
                names[i] = updatedName;
                return true;
            }
        }

        return false;
    }
}
