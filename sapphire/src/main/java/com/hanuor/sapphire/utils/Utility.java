package com.hanuor.sapphire.utils;

import java.util.ArrayList;

/**
 * Created by Shantanu Johri on 29-07-2016.
 */
public class Utility {
    public static void throwExceptionIfNullOrBlank(Object obj, String name) {
        if(obj == null) {
            throw new QuestException(name + " parameter can not be null ");
        } else if(obj instanceof String && ((String)obj).trim().equals("")) {
            throw new QuestException(name + " parameter can not be blank ");
        } else if(obj instanceof ArrayList && ((ArrayList)obj).size() == 0) {
            throw new QuestException(name + " cannot be empty");
        }
    }

    public static void throwExceptionIfNull(Object obj, String name) {
        if(obj == null) {
            throw new QuestException(name + " parameter can not be null ");
        } else if(obj instanceof ArrayList && ((ArrayList)obj).size() == 0) {
            throw new QuestException(name + " cannot be empty");
        }
    }
}
