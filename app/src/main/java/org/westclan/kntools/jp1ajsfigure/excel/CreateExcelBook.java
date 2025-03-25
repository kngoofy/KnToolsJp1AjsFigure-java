package org.westclan.kntools.jp1ajsfigure.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.westclan.kntools.jp1ajsfigure.role.RoleIndexSheet;
import org.westclan.kntools.jp1ajsfigure.role.RoleNetSheet;
import org.westclan.kntools.jp1ajsfigure.role.RoleTopAjsNetSheet;
import org.westclan.kntools.jp1ajsfigure.util.LoadBuildPicture;

/**
 * ExcelBook を作成するクラス
 */
public class CreateExcelBook {

    String topUnitName = "";
    LoadBuildPicture lbp = null;

    /**
     * ExcelBook を作成するクラス
     * 
     * @param templateBook テンプレートExcelBook
     * @param destBook     作成するExcelBook
     * @param unitString   JP1/AJS 定義文字列
     * @return
     * @throws IOException
     */
    public boolean createExcelBook(String templateBook, String destBook, String unitString) throws IOException {

        //
        System.out.println("Template Book :> " + templateBook);
        System.out.println("Genarate Book :> " + destBook);

        // テンプレートのExcelBookを開いて、Sheetを作成していく
        try (FileInputStream fis = new FileInputStream(templateBook);
                Workbook workbook = new XSSFWorkbook(fis);) {

            // (1) Index シートのデータプロット
            // (0) Index シートにデータプをロットする
            RoleIndexSheet indexSheet = new RoleIndexSheet();
            indexSheet.roleSheetIndex(workbook, unitString);

            // (2) トップAJSシートのデータプロット
            RoleTopAjsNetSheet topAjsSheet = new RoleTopAjsNetSheet();
            topAjsSheet.roleSheetTopAjs(workbook, unitString);

            // (3) ネットシートのデータプロット
            RoleNetSheet nets = new RoleNetSheet();
            nets.roleSheetNet(workbook, unitString);

            // (4) テンプレートシートを削除
            workbook.removeSheetAt(workbook.getSheetIndex("Templeate-sheet"));

            // // 書き出し
            File file = new File(destBook);
            try (OutputStream out = new FileOutputStream(file)) {
                workbook.write(out);
            }
        }

        return true;

    }

}
