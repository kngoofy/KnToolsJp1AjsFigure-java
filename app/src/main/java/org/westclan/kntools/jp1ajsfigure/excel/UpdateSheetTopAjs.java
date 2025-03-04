package org.westclan.kntools.jp1ajsfigure.excel;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.common.usermodel.HyperlinkType;
// import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
// import org.apache.poi.ss.usermodel.FillPatternType;
// import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
// import org.apache.poi.ss.usermodel.IndexedColors;
// import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
// import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
// import org.apache.poi.xssf.usermodel.XSSFConnector;
// import org.apache.poi.xssf.usermodel.XSSFDrawing;
// import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndLength;
// import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndType;
// import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndWidth;
import org.unclazz.jp1ajs2.unitdef.Unit;
// import org.unclazz.jp1ajs2.unitdef.parameter.UnitType;
import org.unclazz.jp1ajs2.unitdef.query.Queries;
import org.westclan.kntools.jp1ajsfigure.builder.BuildNetUnit;
import org.westclan.kntools.jp1ajsfigure.model.NetUnit;
// import org.westclan.kntools.jp1ajsfigure.util.AnchorGroupUnit;
// import org.westclan.kntools.jp1ajsfigure.util.CreateShapeGroupUnit;
// import org.westclan.kntools.jp1ajsfigure.util.GeneratorID;
// import org.westclan.kntools.jp1ajsfigure.util.LoadBuildPicture;

/**
 * TopAjs シートの組み立てクラス
 */
public class UpdateSheetTopAjs extends AUpdateSheet {

    /**
     * TopAjs シートのシート名を TopAjsネット名に変更する
     * 
     * @param workbook   ワークシート
     * @param topAjsUnit TopAjsネット名
     * @return
     */
    public boolean renameAjsSheet(Workbook workbook, NetUnit topAjsUnit) {

        // TopAjsネット名を取得
        String topUnitName = topAjsUnit.getNAME();

        // ajs-topシートを TopAjsネット名に変更
        int ajstopSheet = workbook.getSheetIndex("ajs-top");
        workbook.setSheetName(ajstopSheet, topUnitName);

        return true;
    }

    /**
     * TopAjs シートに Group Shapeを配置する
     * 
     * @param workbook ワークブック
     * @param topUnit  TopAjs の Unit
     * @return
     * @throws IOException
     */
    public boolean updateSheetTopAjsFigure(Workbook workbook, Unit topUnit) throws IOException {

        // 親クラスの updateSheetFigure
        updateSheetFigure(workbook, "ajs-top", topUnit);

        return true;
    }

    /**
     * ネット一覧のデータ組み立て
     * 
     * @param workbook ワークブック
     * @param netUnit  Unit オブジェクト
     * @return
     * @throws IOException
     */
    public boolean updateSheetTopAjsNetTable(Workbook workbook, Unit netUnit) throws IOException {

        // ネット組み立て用
        BuildNetUnit opeUnit = new BuildNetUnit();

        // ネット NetUnitクラスのArrayList
        ArrayList<NetUnit> listNetUnits = new ArrayList<>();

        // Figureの最も下のセルを保持
        int maxYCoord = 0;

        // エレメントでループ処理 NetUnit ArrayListに積み上げる
        var els = netUnit.query(Queries.el());
        for (var el : els) {
            // System.out.println(el.getYCoord());

            // 最も下のセルを保持
            if (maxYCoord < el.getYCoord()) {
                maxYCoord = el.getYCoord();
            }

            //
            var AJobUnit = opeUnit.buildNetUnit(netUnit.getSubUnit(el.getUnitName()));
            listNetUnits.add(AJobUnit);
        }

        // ネット一覧をプロット
        updateSheetTopAjsNets(workbook, maxYCoord * 3 + 6, listNetUnits);

        return true;
    }

    /**
     * ネット一覧のデータプロット
     * 
     * @param workbook  ワークブック
     * @param rowOffset 行オフセット
     * @param units     NetUnit の ArrayList
     * @return
     * @throws IOException
     */
    public boolean updateSheetTopAjsNets(Workbook workbook, int rowOffset, ArrayList<NetUnit> units)
            throws IOException {

        // [Index]シートを対象に
        Sheet sheet = workbook.getSheet("ajs-top");

        // Sheet のプロットする起点
        int offsetRow = rowOffset;
        int offsetCell = 1;

        // ネット一覧のデータプロット 親クラス
        updateSheetNets(workbook, sheet, units, offsetRow, offsetCell);

        return true;

    }

    /**
     * 
     * @param rowIndex   行インデックス int番号
     * @param colIndex   列インデックス int番号
     * @param workbook   ワークブック
     * @param cellStyles セルスタイル 配列
     * @param cell       セル
     * @param unit       ネットユニット
     * @return
     */
    boolean insertValueToCellNet(int rowIndex, int colIndex,
            Workbook workbook, CellStyle[] cellStyles, Cell cell, NetUnit unit) {

        // カラム 列別の処理
        switch (colIndex) {

            case 0: // ＃ 番号
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(rowIndex + 1);
                break;

            case 1: // unit
                // セルにハイパーリンクを設定する
                cell.setCellStyle(cellStyles[enumCellStyleType.net.getNo()]);
                cell.setCellValue(unit.getNAME());
                Hyperlink hyperlink = workbook.getCreationHelper().createHyperlink(HyperlinkType.DOCUMENT);
                // System.out.println(unit.getNAME() + "!A1");
                hyperlink.setAddress(unit.getNAME() + "!A1");
                cell.setHyperlink(hyperlink);
                break;

            case 2: // comment
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(unit.getCM());
                break;

            default:
                break;
        }

        return true;
    }

}
