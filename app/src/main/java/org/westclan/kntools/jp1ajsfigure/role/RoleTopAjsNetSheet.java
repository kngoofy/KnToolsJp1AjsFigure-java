package org.westclan.kntools.jp1ajsfigure.role;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Workbook;
import org.unclazz.jp1ajs2.unitdef.Unit;
import org.unclazz.jp1ajs2.unitdef.Units;
import org.westclan.kntools.jp1ajsfigure.excel.UpdateSheetTopAjs;

/**
 * TopAjs シート組み立て 役割クラス
 */
public class RoleTopAjsNetSheet {

    /**
     * TopAjs シートを組み立てるクラス
     * 
     * @param workbook   ワークブック
     * @param unitString JP1/AJS 定義の文字列
     * @return
     * @throws IOException
     */
    public boolean roleTopAjsNet(Workbook workbook, String unitString) throws IOException {

        // データ組み立て
        // NetUnit クラスのデータ組み立て
        UpdateSheetTopAjs sheetTopAjsNet = new UpdateSheetTopAjs();

        // (A) トップユニットを取り出す
        Unit topUnit = Units.fromCharSequence(unitString).get(0);

        sheetTopAjsNet.updateSheetTopAjsFigure(workbook, topUnit);

        // (B) もう一度トップユニットを取り出す。 バグにあったため冗長だが頭から
        topUnit = Units.fromCharSequence(unitString).get(0);

        sheetTopAjsNet.updateSheetTopAjsNetTable(workbook, topUnit);

        return true;
    }
}
