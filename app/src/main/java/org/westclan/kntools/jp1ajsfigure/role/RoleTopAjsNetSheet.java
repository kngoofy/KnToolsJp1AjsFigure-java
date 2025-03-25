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
     * TopAjs シートを組み立てる
     * 
     * @param workbook   ワークブック
     * @param unitString JP1/AJS 定義の文字列
     * @return
     * @throws IOException
     */
    public boolean roleSheetTopAjs(Workbook workbook, String unitString) throws IOException {

        // データ組み立て
        // TopAjs用シートの更新クラスを new
        UpdateSheetTopAjs sheetTopAjsNet = new UpdateSheetTopAjs();

        // (A-1) トップユニットを取り出す
        Unit topUnit = Units.fromCharSequence(unitString).get(0);

        // (A-2) TopAksシートにオブジェクトをプロットする
        sheetTopAjsNet.updateSheetTopAjsFigure(workbook, topUnit);

        // (B-1) もう一度トップユニットを取り出す。 バグにあったため冗長だが頭から
        topUnit = Units.fromCharSequence(unitString).get(0);

        // (B-2) TopAksシートにネット一覧をプロットする
        sheetTopAjsNet.updateSheetTopAjsNetTable(workbook, topUnit);

        // (C-1) テンプレートの ajs-top シートを AJS名に変更
        sheetTopAjsNet.renameAjsSheet(workbook, topUnit);

        return true;
    }
}
