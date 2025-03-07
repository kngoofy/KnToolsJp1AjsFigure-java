package org.westclan.kntools.jp1ajsfigure.excel;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.unclazz.jp1ajs2.unitdef.Unit;
import org.unclazz.jp1ajs2.unitdef.parameter.UnitType;
import org.unclazz.jp1ajs2.unitdef.query.Queries;
import org.westclan.kntools.jp1ajsfigure.builder.BuildCJobUnit;
import org.westclan.kntools.jp1ajsfigure.model.CJobUnit;

/**
 * Index シートの組み立てクラス
 */
public class UpdateSheetNet extends AUpdateSheet {

    /**
     * Index シートに Gropu Shapeを配置する
     * 
     * @param workbook  ワークブック
     * @param sheetName シート名
     * @param netUnit   Neto の Unit
     * @return
     * @throws IOException
     */
    public boolean updateSheetNetFigure(Workbook workbook, String sheetName, Unit netUnit) throws IOException {

        // 親クラスの updateSheetFigure
        updateSheetFigure(workbook, sheetName, netUnit);

        return true;
    }

    /**
     * ジョブ一覧のデータ組み立て
     * 
     * @param workbook  ワークブック
     * @param sheetName シート名
     * @param netUnit   Unit オブジェクト
     * @return
     * @throws IOException
     */
    public boolean updateSheetNetTable(Workbook workbook, String sheetName, Unit netUnit) throws IOException {

        // シートを指定
        Sheet sheet = workbook.getSheet(sheetName);

        // ジョブ組み立て用
        BuildCJobUnit opeUnit = new BuildCJobUnit();

        // ネット AJobUnitクラスのArrayList
        ArrayList<CJobUnit> listAJjobUnits = new ArrayList<>();

        // Figureの最も下のセルを保持
        int maxYCoord = 0;

        // エレメントでループ処理 AJobUnit ArrayListに積み上げる
        var els = netUnit.query(Queries.el());
        for (var el : els) {
            // System.out.println(el.getYCoord());

            // 最も下のセルを保持
            if (maxYCoord < el.getYCoord()) {
                maxYCoord = el.getYCoord();
            }

            //
            var AJobUnit = opeUnit.buildCJobUnit(netUnit.getSubUnit(el.getUnitName()));
            listAJjobUnits.add(AJobUnit);
        }

        // ジョブ一覧をプロット
        updateSheetNetAJobs(workbook, sheet, maxYCoord * 3 + 6, listAJjobUnits);

        return true;
    }

    /**
     * ジョブ一覧のデータプロット
     * 
     * @param workbook  ワークブック
     * @param sheet     シート
     * @param rowOffset 行オフセット
     * @param AJobUnits AJobUnit の ArrayList
     * @return
     * @throws IOException
     */
    public boolean updateSheetNetAJobs(Workbook workbook, Sheet sheet, int rowOffset, ArrayList<CJobUnit> AJobUnits)
            throws IOException {

        // セルスタイル作成
        CellStyle[] cellStyles = createCellStypes(workbook);

        // Sheet のプロットする起点
        int offsetRow = rowOffset;
        int offsetCell = 1;

        //
        Row row;
        Cell cell;
        int rowIndex = 0;

        // 構成ネットを作成
        for (var aJobUnit : AJobUnits) {

            // 対象シートの row を作成
            row = sheet.createRow(rowIndex + offsetRow);

            // ジョブの採番した表示上番号
            cell = row.createCell(0 + offsetCell);
            insertValueToCellAJobUnit(rowIndex, 0, workbook, cellStyles, cell, aJobUnit);

            // ジョブ名のセット
            cell = row.createCell(1 + offsetCell);
            insertValueToCellAJobUnit(rowIndex, 1, workbook, cellStyles, cell, aJobUnit);

            // ジョブのコメントセット
            cell = row.createCell(2 + offsetCell);
            insertValueToCellAJobUnit(rowIndex, 2, workbook, cellStyles, cell, aJobUnit);

            // UNIXジョブのコマンド or flwjジョブの受信ファイル or PCジョブのスクリプト
            cell = row.createCell(3 + offsetCell);
            insertValueToCellAJobUnit(rowIndex, 3, workbook, cellStyles, cell, aJobUnit);

            rowIndex++;
        }

        return true;

    }

    /**
     * ジョブ一覧をプロット
     * 
     * @param rowIndex   行インデックス int番号
     * @param colIndex   列インデックス int番号
     * @param workbook   ワークブック
     * @param cellStyles セルスタイル 配列
     * @param cell       セル
     * @param unit       ジョブユニット AJobUnit
     * @return
     */
    boolean insertValueToCellAJobUnit(int rowIndex, int colIndex, Workbook workbook, CellStyle[] cellStyles, Cell cell,
            CJobUnit unit) {

        // カラム 列別の処理
        switch (colIndex) {

            case 0: // ＃ 番号
                cell.setCellStyle(cellStyles[enumCellStyleType.def.getNo()]);
                cell.setCellValue(rowIndex + 1);
                break;

            case 1: // unit
                // セルにハイパーリンクを設定する
                cell.setCellStyle(cellStyles[enumCellStyleType.ajobunit.getNo()]);
                cell.setCellValue(unit.getNAME());
                // Hyperlink hyperlink =
                // workbook.getCreationHelper().createHyperlink(HyperlinkType.DOCUMENT);
                // // System.out.println(unit.getNAME() + "!A1");
                // hyperlink.setAddress(unit.getNAME() + "!A1");
                // cell.setHyperlink(hyperlink);
                break;

            case 2: // comment
                cell.setCellStyle(cellStyles[enumCellStyleType.comment.getNo()]);
                cell.setCellValue(unit.getCM());
                break;

            case 3: // te or flwf or sc
                cell.setCellStyle(cellStyles[enumCellStyleType.te.getNo()]);

                if (unit.getType() == UnitType.UNIX_JOB) {
                    cell.setCellValue(unit.getTE());
                } else if (unit.getType() == UnitType.FILE_WATCH_JOB) {
                    cell.setCellValue(unit.getflwf());
                } else if (unit.getType() == UnitType.PC_JOB) {
                    cell.setCellValue(unit.getSC());
                }
                break;

            default:
                break;
        }

        return true;
    }

}
