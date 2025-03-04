package org.westclan.kntools.jp1ajsfigure.excel;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.westclan.kntools.jp1ajsfigure.model.NetUnit;

/**
 * シート Index の組み立てクラス
 */
public class UpdateSheetIndex extends AUpdateSheet {

    /**
     * シート Index の トップユニット部分のセット
     * 
     * @param Workbook workbook
     * @param NetUnit  unit
     * @return
     */
    public boolean updateSheetIndexAjsName(Workbook workbook, NetUnit unit) {

        // [Index]シートを対象に
        Sheet sheet = workbook.getSheet("Index");

        // セルスタイル作成
        CellStyle[] cellStyles = createCellStypes(workbook);

        //
        Row row;
        Cell cell;

        // Sheet のプロットする起点
        int offsetRow = 3;
        int offsetCell = 1;

        // トップネットのセルへのデータプロット
        row = sheet.createRow(0 + offsetRow);
        cell = row.createCell(1 + offsetCell);
        insertValueToCellNet(0, 1, workbook, cellStyles, cell, unit);
        cell = row.createCell(2 + offsetCell);
        insertValueToCellNet(0, 2, workbook, cellStyles, cell, unit);

        // 作成日付をセルに
        row = sheet.createRow(1);
        cell = row.createCell(5);
        cell.setCellValue(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd")));

        return true;
    }

    /**
     * 
     * @param workbook ワークブック
     * @param units    NetUnit ArrayList
     * @return
     * @throws IOException
     */
    public boolean updateSheetIndexNets(Workbook workbook, ArrayList<NetUnit> units) throws IOException {

        // [Index]シートを対象に
        Sheet sheet = workbook.getSheet("Index");

        // Sheet のプロットする起点
        int offsetRow = 6;
        int offsetCell = 1;

        // ネット一覧のデータプロット 親クラス
        updateSheetNets(workbook, sheet, units, offsetRow, offsetCell);

        return true;
    }

    /**
     * セルへのデータプロット
     */

    /**
     * 
     * @param rowIndex   行インデックス int番号
     * @param colIndex   列インデックス int番号
     * @param workbook   ワークブック
     * @param cellStyles セルスタイル 配列
     * @param cell       セル
     * @param unit       ネットユニット NetUnit
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
