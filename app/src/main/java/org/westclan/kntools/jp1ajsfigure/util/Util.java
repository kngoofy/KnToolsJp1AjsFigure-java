package org.westclan.kntools.jp1ajsfigure.util;

import java.util.ResourceBundle;

/**
 * ユーティリティクラス
 */
public class Util {

    /**
     * プロパティファイルからプロパティを返す
     * 
     * @param key プロパティのキー
     * @return プロパティ値
     */
    public static String getProperties(String key) {

        ResourceBundle bundle = ResourceBundle.getBundle("config");
        return bundle.getString(key);

    }

    // public static String getPropertiesKey(String key) throws
    // MalformedURLException {

    // File dicDir = Paths.get(".\\").toFile(); // ★1

    // URLClassLoader urlLoader; // ★2
    // // urlLoader = new URLClassLoader(new URL[] { dicDir.toURI().toURL() }); //
    // ★3
    // urlLoader = new URLClassLoader(new URL[] { dicDir.toURI().toURL() }); // ★3

    // Locale localeJp = Locale.JAPAN;
    // // Locale localeUs = Locale.US;

    // // List<Locale> locales = new ArrayList<Locale>(Arrays.asList(localeJp,
    // // localeUs));
    // List<Locale> locales = new ArrayList<Locale>(Arrays.asList(localeJp));

    // // for (Locale locale : locales) {
    // ResourceBundle rb = ResourceBundle.getBundle("config", localeJp, urlLoader);
    // // ★4

    // System.out.println(rb.getString("file.ext-unixjob.png"));
    // System.out.println(rb.getString("file.ext-pcjob.png"));
    // System.out.println(rb.getString("file.ext-net.png"));
    // System.out.println(rb.getString("file.ext-flwj.png"));
    // // }

    // return rb.getString(key);
    // }

    // /**
    // * プロパティファイルからプロパティを読み込み HashMapに組み立てる
    // *
    // * @param propName プロパティファイル名
    // * @return HashMap<String, String>
    // */
    // public static HashMap<String, String> loadProperties(String propName) {
    // String templateExcelBook = "no-templateExcel.xlsx";
    // String updatedExcelBook = "no-createExcel.xlsx";
    // var names = new HashMap<String, String>();

    // Properties properties = new Properties();
    // try (FileInputStream input = new FileInputStream(propName)) {
    // properties.load(input);
    // templateExcelBook = properties.getProperty("file.template-excelbook");
    // updatedExcelBook = properties.getProperty("file.create-excelbook");
    // } catch (IOException e) {
    // e.printStackTrace();
    // }

    // // HashMap に put
    // names.put("templateExcelBook", templateExcelBook);
    // names.put("updatedExcelBook", updatedExcelBook);

    // return names;
    // }

}
