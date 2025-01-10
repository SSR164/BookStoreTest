package config;

public class ConfigBookStore {
    public static String getUserName(){
        return System.getenv("USERNAME");
    }
    public static String getPassword(){
        return System.getenv("PASSWORD");
    }
}
