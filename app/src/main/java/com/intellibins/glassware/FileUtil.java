package com.intellibins.glassware;

/**
 * Created by prt2121 on 10/9/14.
 */

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileUtil {

    private static final String TAG = FileUtil.class.getSimpleName();

    /**
     * Writes the string to the file with the given file name.
     *
     * @param context  the context used to write the string.
     * @param fileName the name of the file to write to.
     * @param text     the string to write.
     */
    public static void writeToFile(Context context, String fileName, String text) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(
                    context.openFileOutput(fileName, Context.MODE_PRIVATE));
            outputStreamWriter.write(text);
            outputStreamWriter.close();
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
    }

    /**
     * Reads all characters from a file with the given file name into a {@link String}.
     *
     * @param context  the context used to read the string.
     * @param fileName the name of the file to read from.
     * @return a string containing all the characters from the file, or {@code null} if there is any
     * exception.
     */
    public static String readFromFile(Context context, String fileName) {
        String ret = null;
        try {
            InputStream inputStream = context.openFileInput(fileName);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();
                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }
                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e(TAG, e.toString());
        } catch (IOException e) {
            Log.e(TAG, e.toString());
        }
        return ret;
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            if (dir != null && dir.isDirectory()) {
                deleteDir(dir);
            }
        } catch (Exception e) {
        }
    }

    public static boolean deleteDir(File file) {
        if (file == null) {
            return false;
        }
        if (file.isDirectory()) {
            String[] children = file.list();
            for (String child : children) {
                boolean success = deleteDir(new File(file, child));
                if (!success) {
                    return false;
                }
            }
        }
        return file.delete();
    }

    public static String getCacheFilePath(Context context, String fileName) {
        if (fileName == null) {
            return null;
        }
        File f = new File(context.getCacheDir(), fileName);
        return f.exists() ? f.getPath() : null;
    }
}
