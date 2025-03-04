package org.westclan.kntools.jp1ajsfigure.util;

import java.util.HashMap;

/**
 * ExcelのオブジェクトIDを採番するクラス
 */
public class GeneratorID {

    // Excel book オブジェクト 採番 ID
    private static long id = 10;

    // key と id の ハッシュマップ
    private static HashMap<String, Long> mapKeyToId = new HashMap<String, Long>();

    /**
     * ExcelのオブジェクトIDを採番する
     * 
     * @param key
     * @return id
     */
    public static long getId(String key) {

        if (mapKeyToId.containsKey(key)) {
            System.out.println("<key generator> 既に存在 [" + key + "] : [" + id + "]");
            return getMapID(key);
        }

        mapKeyToId.put(key, id);
        System.out.println("<key generator> [" + key + "] : [" + id + "]");
        return id++;
    };

    /**
     * ExcelのオブジェクトIDを取得
     * 
     * @param key
     * @return id
     */
    public static long getMapID(String key) {
        System.out.println("<request> [" + key + "] : [" + mapKeyToId.get(key) + "]");
        return mapKeyToId.get(key);
    }

}
