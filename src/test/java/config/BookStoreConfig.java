package config;

public class BookStoreConfig {
    public static String getUserName(){
        return System.getProperty("USERNAME");
    }
    public static String getPassword(){
        return System.getProperty("PASSWORD");
    }
}
