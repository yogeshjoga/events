package org.api.events.designpattern_DemoNotUsedInProject;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Setter
@Getter
public class ProtoType implements Cloneable{

    private String type;
    private String value;
    private String name;
    private String description;


    public ProtoType() {

    }

    public ProtoType(final String type, final String value, final String name, final String description) {
        this.type = type;
        this.value = value;
        this.name = name;
        this.description = description;
    }

    public ProtoType(ProtoType protoType) {
        this.type = protoType.type;
        this.value = protoType.value;
        this.name = protoType.name;
        this.description = protoType.description;
    }

    @Override
    public ProtoType clone() {
        return new ProtoType(this);
    }


    static class Registry{

        Map<String, ProtoType> protoTypes = new ConcurrentHashMap<>();

        // implement Crud operations

        public ProtoType getProtoType(String type){
            return protoTypes.get(type);
        }

        public void registerProtoType(String type, ProtoType protoType){
            protoTypes.put(type, protoType);
        }

        public Map<String, ProtoType> getProtoTypes(){
            return protoTypes;
        }

        public void unregisterProtoType(String type){
            protoTypes.remove(type);
        }


    }



}
