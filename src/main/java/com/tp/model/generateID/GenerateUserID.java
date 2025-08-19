package com.tp.model.generateID;

import java.util.UUID;

public class GenerateUserID {
    public String generateID(){
        String prefix = "MEM";
        String unique = UUID.randomUUID().toString().substring(0,4);
        return prefix + unique;
    }
}
