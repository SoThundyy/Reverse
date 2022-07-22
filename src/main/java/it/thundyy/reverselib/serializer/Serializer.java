package it.thundyy.reverselib.serializer;

public interface Serializer<T> {
    
    String SERIALIZER_SEPARATOR = ";";
    
    String serialize(T object);
    
    T deserialize(String object);
}
