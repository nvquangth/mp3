package com.zing.crypto;

import android.content.Context;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;

public class Crypto {

    public static String a(JSONObject jSONObject) {
        int length = jSONObject.length();
        String[] strArr = new String[length];
        String[] strArr2 = new String[length];
        Iterator<String> keys = jSONObject.keys();
        int i = 0;
        while (keys.hasNext()) {
            try {
                strArr[i] = keys.next();
                strArr2[i] = String.valueOf(jSONObject.get(strArr[i]));
                i++;
            } catch (Exception unused) {
                return null;
            }
        }
        return encSig(strArr, strArr2);
    }

    public static String b(HashMap<String, String> hashMap) {
        int size = hashMap.keySet().size();
        String[] strArr = new String[size];
        String[] strArr2 = new String[size];
        int i = 0;
        for (String str : hashMap.keySet()) {
            try {
                strArr[i] = str;
                strArr2[i] = String.valueOf(hashMap.get(strArr[i]));
                i++;
            } catch (Exception unused) {
                return null;
            }
        }
        return encSig2(strArr, strArr2);
    }

    public static native String cnvId(String str);

    public static native String decData(String str);

    public static native int decFile(String str, String str2, String str3);

    public static native String encData(String str);

    public static native int encFile(String str, String str2, String str3);

    public static native String encSig(String[] strArr, String[] strArr2);

    public static native String encSig2(String[] strArr, String[] strArr2);

    public static native String getPubKey();

    public static native String getPubKey2();

    public static native void init(Context context);
}

