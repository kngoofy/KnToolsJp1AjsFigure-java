package org.westclan.kntools.jp1ajsfigure.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.westclan.kntools.jp1ajsfigure.excel.CreateExcelBook;

/**
 * ファイルからExcelBook を作成するメインクラス
 */
public class FromFileMain {

    public static void main(String[] args) throws IOException {

        // Java Class 表示
        System.out.println("Java ClassPath :=> " + System.getProperty("java.class.path"));

        String netImage = Util.getProperties("file.ext-net.png");
        String unixJobImage = Util.getProperties("file.ext-unixjob.png");
        String pcJobImage = Util.getProperties("file.ext-pcjob.png");
        String flwjImage = Util.getProperties("file.ext-flwj.png");

        System.out.println("netImage :=" + netImage);
        System.out.println("unixJobImage :=" + unixJobImage);
        System.out.println("pcJobImage :=" + pcJobImage);
        System.out.println("flwjImage :=" + flwjImage);

        // 引数のチェック
        if (args.length < 3) {
            usage();
            System.exit(1);
        }

        String templateExcelBook = args[0];
        String createExcelBook = args[1];
        String defFile = args[2];
        System.out.println(templateExcelBook + " : templateExcelBook");
        System.out.println(createExcelBook + " : CreateExcelBook");
        System.out.println(defFile + " : defFile");

        String line = "";
        var builder = new StringBuilder();
        String unitString = "";

        File file = new File(templateExcelBook);
        if (!file.exists()) {
            System.out.print("ファイルが存在しません " + templateExcelBook);
            System.exit(1);
        }
        File file2 = new File(defFile);
        // ファイルが存在しない場合に例外が発生するので確認する
        if (!file2.exists()) {
            System.out.print("ファイルが存在しません " + defFile);
            System.exit(1);
        }

        // ajs 定義ファイルを読み込み
        try (var reader = new BufferedReader(new FileReader(defFile))) {

            while ((line = reader.readLine()) != null) {

                line = line.trim();
                builder.append(line + "\n");
            }

            unitString = builder.toString();

        } catch (Exception e) {
            // handle exception
        }

        // ExcelBook 作成
        var a = new CreateExcelBook();
        a.createExcelBook(templateExcelBook, createExcelBook, unitString);

    }

    public String readFromFileToString(String fileName) throws FileNotFoundException, IOException {

        if (!(new File(fileName)).exists()) {
            System.out.print("ファイルが存在しません " + fileName);
            System.exit(1);

        }

        // ワーク用
        String line = "";
        // ArrayList<String> arrayList = new ArrayList<>();

        StringBuffer strBuf = new StringBuffer();

        // ファイルをオープンしてバッファリード
        try (var reader = new BufferedReader(new FileReader(fileName))) {

            // 一行づつ
            while ((line = reader.readLine()) != null) {
                // arrayList.add(line);
                strBuf.append(line + "\n");
            }

        }

        // System.out.println(strBuf.toString());
        // 積み上げた ArrayList を返す
        // return arrayList;
        return strBuf.toString();
    }

    public static void usage() {
        String message = """
                Usage: 引数はこの2つです。
                  生成するExcelBook名
                  JP1/AJS定義ファイル
                ex. thisjava server.xlsx ajs.def
                  """;

        System.out.println(message);
    }

}
