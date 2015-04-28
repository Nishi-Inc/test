package com.nishionline.test.utils;

import java.io.*;
import java.util.Objects;

/**
 * @author shuklaalok7
 * @since 28/4/15 11:21
 */
public class FileUtils {

    // FIXME contains hard-coded string for directory name
    public static String getOutputFolder() {
        return System.getProperty("user.dir")+"/data/";
    }

    /**
     *
     * @param fileName    The name of file to append to. Must be in data folder.<br/>
     *                    File will be created if it doesn't exist.
     * @param content     The content to write
     * @return <code>true</code>, if appended successfully, <code>false</code> otherwise
     */
    public static boolean appendToFile(String fileName, String content) {
        return appendToFile(new File(getOutputFolder()+fileName), content);
    }

    /**
     * @param file       The file to append in
     * @param content    The content to write
     * @return <code>true</code>, if appended successfully, <code>false</code> otherwise
     */
    public static boolean appendToFile(File file, String content) {
        try(PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, true)));) {
            out.println(content);
        } catch (IOException e) {
            System.out.println("Exception while writing to file "+file.getName());
            e.printStackTrace();
            return false;
        }

        return true;
    }

    /**
     *
     * @param fileName    Name of the file, MUST be in data folder
     * @return The content of the file as String
     * @throws IOException
     */
    public static String readFile(String fileName) throws IOException {
        Objects.requireNonNull(fileName);
        if(fileName.isEmpty()) {
            throw new IOException("fileName cannot be empty.");
        }

        return readFile(new File(getOutputFolder()+fileName));
    }

    /**
     *
     * @param file    The file to read
     * @return The content of the file as String
     * @throws IOException
     */
    public static String readFile(File file) throws IOException {
        Objects.requireNonNull(file);
        if(file.isDirectory()) {
            throw new IOException("This is a directory not a file.");
        }

        if(!file.exists()) {
            throw new IOException("Given file "+ file.getName() + " doesn't exist.");
        }

        StringBuilder fileContentBuilder = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line = br.readLine(); line!=null; line=br.readLine()) {
                if(!line.isEmpty()) {
                    fileContentBuilder.append(line).append("\n");
                }
            }
        }

        return fileContentBuilder.toString();
    }
}
