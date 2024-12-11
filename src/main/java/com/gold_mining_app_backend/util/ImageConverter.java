package com.gold_mining_app_backend.util;

import java.util.Base64;

public class ImageConverter {
public static String convertToString(byte [] image){
    if(image!=null){
        return "data:image/png;base64,"+Base64.getEncoder().encodeToString(image);
    }else return null;
}
public static byte [] convertToBase64(String image){
    if(image.equals("")) return null;
    else return Base64.getDecoder().decode(image.replaceFirst("^data:image/.*;base64,",""));
}
}
