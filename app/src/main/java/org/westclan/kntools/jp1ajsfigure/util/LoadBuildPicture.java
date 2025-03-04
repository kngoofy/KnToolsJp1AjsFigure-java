package org.westclan.kntools.jp1ajsfigure.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.util.IOUtils;

/**
 * JP1/AJS の Unitのイメージ byte[] を組み立て、geImageを呼ばれるとイメージを返すクラス
 */
public class LoadBuildPicture {

    // イメージを格納した HashMap
    private Map<String, byte[]> images = new HashMap<String, byte[]>();

    /**
     * コンストラクタ イメージを HashMap にputしていく
     */
    public LoadBuildPicture() {

        String netImage = Util.getProperties("file.ext-net.png");
        String unixJobImage = Util.getProperties("file.ext-unixjob.png");
        String pcJobImage = Util.getProperties("file.ext-pcjob.png");
        String flwjImage = Util.getProperties("file.ext-flwj.png");

        images.put("JOBNET", getBytePicture(netImage));
        images.put("n", getBytePicture(netImage));

        images.put("UNIX_JOB", getBytePicture(unixJobImage));
        images.put("j", getBytePicture(unixJobImage));

        images.put("PC_JOB", getBytePicture(pcJobImage));
        images.put("pj", getBytePicture(pcJobImage));

        images.put("FILE_WATCH_JOB", getBytePicture(flwjImage));
        images.put("flwj", getBytePicture(flwjImage));

    }

    /**
     * key を指定したイメージ byte[]を返す
     * 
     * @param key
     * @return
     */
    public byte[] getImage(String key) {
        return images.get(key);
    }

    /**
     * JP/AJS の Unitのイメージ(.png)を読み込む
     * 
     * @param fileName ex.UnixJob.png
     * @return byte[] tytes
     */
    private byte[] getBytePicture(String fileName) {
        byte[] bytes = null;
        try (InputStream is = new FileInputStream(fileName)) {
            // bytes = IOUtils.toByteArray(is);
            bytes = IOUtils.toByteArray(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // バイトの配列
        return bytes;
    }

}
