package org.westclan.kntools.jp1ajsfigure.role;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Workbook;
import org.westclan.kntools.jp1ajsfigure.builder.BuildNetUnit;
import org.westclan.kntools.jp1ajsfigure.excel.UpdateSheetIndex;
import org.westclan.kntools.jp1ajsfigure.model.NetUnit;

/**
 * Index シート組み立て 役割クラス
 */
public class RoleIndexSheet {

    /**
     * Index シートを組み立てる
     * 
     * @param workbook   ワークブック
     * @param unitString JP1/AJS 定義の文字列
     * @return
     * @throws IOException
     */
    public boolean roleSheetIndex(Workbook workbook, String unitString) throws IOException {

        // データ組み立て
        // NetUnit クラスのデータ組み立て
        BuildNetUnit opeUnit = new BuildNetUnit();

        // トップネットを NetUnit クラスに組み立て
        NetUnit topNetUnit = opeUnit.getTopNetUnit(unitString);

        // 下位のユニットの NetUnit クラスを組み立て ArrayListに
        ArrayList<NetUnit> childUnits = opeUnit.getChildNetUnits(unitString);

        // Index シートにデータをプロットするため クラスを new
        UpdateSheetIndex opeSheet = new UpdateSheetIndex();

        // (A) Indexシートに AJSトップ名をプロット
        opeSheet.updateSheetIndexAjsName(workbook, topNetUnit);

        // (b) Indexシートに 下位ネットを一覧プロット
        opeSheet.updateSheetIndexNets(workbook, childUnits);

        return true;

    }
}
