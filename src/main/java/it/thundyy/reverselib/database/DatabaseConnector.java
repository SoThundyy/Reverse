package it.thundyy.reverselib.database;

public interface DatabaseConnector<T> {
    
    void connect();
    
    void disconnect();
    
    T getConnection();
    
    boolean isClosed();
}
