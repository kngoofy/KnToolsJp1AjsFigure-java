package org.westclan.kntools.jp1ajsfigure;

import java.io.IOException;

import org.westclan.kntools.jp1ajsfigure.data.TestData01;
import org.westclan.kntools.jp1ajsfigure.excel.CreateExcelBook;
import org.westclan.kntools.jp1ajsfigure.util.Util;

public class MainDriver {
    public static void main(String[] args) throws IOException {

        // // プロパティを読み込んで

        String templateExcelBook = Util.getProperties("file.template-excelbook");
        String createExcelBook = Util.getProperties("file.create-excelbook");

        // テストデータ読み込み
        String unitdef = TestData01.Data;

        // ExcelBook 作成
        var a = new CreateExcelBook();
        a.createExcelBook(templateExcelBook, createExcelBook, unitdef);

        //
        System.exit(0);
    }

}
