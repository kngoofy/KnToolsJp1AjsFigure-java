package org.westclan.kntools.jp1ajsfigure.role;

import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.unclazz.jp1ajs2.unitdef.Unit;
import org.unclazz.jp1ajs2.unitdef.Units;
import org.unclazz.jp1ajs2.unitdef.query.Queries;
import org.westclan.kntools.jp1ajsfigure.excel.UpdateSheetNet;

/**
 * ネット シート組み立て 役割クラス
 */
public class RoleNetSheet {

    /**
     * ネットシートを組み立てる
     * 
     * @param workbook   ワークブック
     * @param unitString JP1/AJS 定義の文字列
     * @return
     * @throws IOException
     */
    public boolean roleSheetNet(Workbook workbook, String unitString) throws IOException {

        // データ組み立て
        // ネット用シートの更新クラスを new
        UpdateSheetNet sheetNet = new UpdateSheetNet();

        // テンプレートシートはこれです
        var templateSheet = workbook.getSheetIndex("Templeate-sheet");

        // (A-1) トップユニットを取り出す
        Unit topUnit = Units.fromCharSequence(unitString).get(0);

        // (A-2) 下位の子供ユニットを取り出す 主に Net
        Iterable<Unit> childUnits = topUnit.query(Queries.children());
        // ループ処理
        for (Unit childUnit : childUnits) {

            // テンプレートシートから Net シートを作成
            Sheet clonedSHeet = workbook.cloneSheet(templateSheet);
            workbook.setSheetName(workbook.getSheetIndex(clonedSHeet), childUnit.getName());

            // (A-3) Net シートにオブジェクトをプロットする
            sheetNet.updateSheetNetFigure(workbook, childUnit.getName(), childUnit);

        }

        // (B-1) もう一度トップユニットを取り出す。 バグにあったため冗長だが頭から
        topUnit = Units.fromCharSequence(unitString).get(0);

        // (B-2) 下位の子供ユニットを取り出す 再度
        childUnits = topUnit.query(Queries.children());
        // ループ処理
        for (Unit childUnit : childUnits) {

            // (b-3) Net シートにユニット一覧をプロットする
            sheetNet.updateSheetNetTable(workbook, childUnit.getName(), childUnit);

        }

        return true;

    }
}