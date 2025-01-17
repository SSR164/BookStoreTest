package config;

public class BookStoreConfig {
    public static String getUserName(){
        return System.getProperty("userName");
    }
    public static String getPassword(){
        return System.getProperty("password");
    }
}
