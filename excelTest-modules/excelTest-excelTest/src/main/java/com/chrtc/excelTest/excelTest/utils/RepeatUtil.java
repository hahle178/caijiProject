package com.chrtc.excelTest.excelTest.utils;

import java.util.HashSet;
import java.util.Set;

public class RepeatUtil {
    private static Set<Integer> hashset=new HashSet();

    /**
     * 针对字符串进行性哈希值比较重复
     * @param key
     * @return
     */
    public boolean fileRepeat(String key){
        HashUtil hashUtil=new HashUtil();
        int hashcode=hashUtil.toHash(key);


        boolean hashboolean=false;

        if (hashset.isEmpty()){
            hashset.add(hashcode);
        }else{
            if (hashset.contains(hashcode)) {
                hashboolean=true;
                return hashboolean;
            }
            }
            hashset.add(hashcode);
        return  hashboolean;
    }

    public RepeatUtil() {
        hashset=new HashSet<Integer>();
    }
}




