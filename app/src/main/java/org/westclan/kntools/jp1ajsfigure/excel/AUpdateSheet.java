package org.westclan.kntools.jp1ajsfigure.excel;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Hyperlink;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFConnector;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndLength;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndType;
import org.openxmlformats.schemas.drawingml.x2006.main.STLineEndWidth;
import org.unclazz.jp1ajs2.unitdef.Unit;
import org.unclazz.jp1ajs2.unitdef.parameter.UnitType;
import org.unclazz.jp1ajs2.unitdef.query.Queries;
import org.westclan.kntools.jp1ajsfigure.model.NetUnit;
import org.westclan.kntools.jp1ajsfigure.util.AnchorGroupUnit;
import org.westclan.kntools.jp1ajsfigure.util.CreateShapeGroupUnit;
import org.westclan.kntools.jp1ajsfigure.util.GeneratorID;
import org.westclan.kntools.jp1ajsfigure.util.LoadBuildPicture;

/**
 * UpdateSheet 共通のアブストラクトクラス
 */
public abstract class AUpdateSheet {

    /**
     * シートに GroupShape をプロットする
     * 
     * @param workbook  ワークブック
     * @param sheetName シート名
     * @param netUnit   Unitオブジェクト
     * @return
     * @throws IOException
     */
    public boolean updateSheetFigure(Workbook workbook, String sheetName, Unit netUnit) throws IOException {

        // // unit pngのロード 組み立て
        LoadBuildPicture lbp = new LoadBuildPicture();

        // シートの指定
        Sheet sheet = workbook.getSheet(sheetName);

        // drawing を取得
        XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();

        // Elements ループ
        var els = netUnit.query(Queries.el());
        for (var el : els) {

            // エレメントの unitType
            UnitType unitType = netUnit.getSubUnit(el.getUnitName()).getType();

            // エレメントのユニット名
            String unitName = el.getUnitName();

            // エレメントのコメント
            String unitComment = netUnit.getSubUnit(el.getUnitName()).getComment().toString();

            // unitType の画像のインデックス No
            var pitureIdxNo = workbook.addPicture(lbp.getImage(unitType.getName()), Workbook.PICTURE_TYPE_PNG);

            // GroupShapeのアンカー取得
            AnchorGroupUnit groupUnit = new AnchorGroupUnit(
                    0, 0, 0, 0,
                    el.getXCoord(),
                    el.getYCoord() + 2,
                    el.getXCoord() + 2,
                    el.getYCoord() + 2 + 2);

            // GroupShapeの作成
            new CreateShapeGroupUnit(drawing, groupUnit,
                    pitureIdxNo,
                    netUnit.getFullQualifiedName().toString(),
                    unitName,
                    unitComment);

        }

        // XSSFClientAnchor cpmmectprAnchor;
        String arStr;
        String fromUnitName; // 先行ユニット名
        String toUnitName; // 後続ユニット名
        long fromUnitNameID; // 先行ユニットの採番したID
        long toUnitNameID; // 後続ユニットの採番したID
        String fullQualifiedName = netUnit.getFullQualifiedName().toString(); // 完全ユニット名

        // 先行・後続の取得 と ループ処理
        var ars = netUnit.query(Queries.ar());
        for (var ar : ars) {

            // 先行ユニット名
            fromUnitName = fullQualifiedName + "/" + ar.getFromUnitName();
            // 後続ユニット名
            toUnitName = fullQualifiedName + "/" + ar.getToUnitName();

            arStr = fromUnitName + " :@: " + toUnitName;

            // 先行ユニット名ID
            fromUnitNameID = GeneratorID.getMapID(fromUnitName);
            // 後続ユニット名ID
            toUnitNameID = GeneratorID.getMapID(toUnitName);

            //
            int fromX = 0, fromY = 0, toX = 0, toY = 0;

            // エレメントの座標を取得するループ
            var listEls = netUnit.query(Queries.el());
            for (var el : listEls) {

                if (el.getUnitName().equals(ar.getFromUnitName())) {
                    fromX = el.getXCoord();
                    fromY = el.getYCoord();
                    // System.out.println("fromX=" + fromX + "fromY=" + fromY);
                }
                if (el.getUnitName().equals(ar.getToUnitName())) {
                    toX = el.getXCoord();
                    toY = el.getYCoord();
                    // System.out.println("toX=" + toX + "toY=" + toY);
                }

            }

            // 画像のコネクタの位置の判定処理 ちょっと複雑 実際のシートを見て
            int fromIdx = 3, toIdx = 1; // デフォルト指定
            if (fromX == toX) {
                // 0 or 2
                if (fromY < toY) {
                    fromIdx = 2;
                    toIdx = 0;
                } else {
                    fromIdx = 0;
                    toIdx = 2;
                }
            } else if (fromX < toX) {
                fromIdx = 3;
                toIdx = 1;
            } else {
                fromIdx = 1;
                toIdx = 3;
            }

            // アンカー
            XSSFClientAnchor cpmmectprAnchor = drawing.createAnchor(200000, 100000, 200000, 0, 1, 1, 3, 1);

            //
            XSSFConnector connector = drawing.createConnector(cpmmectprAnchor);

            //
            // System.out.println("NetName=<" + netUnit.getName() + "> arStr : " + arStr);
            // System.out.println("NetName=<" + netUnit.getName() + ">"
            // + " fromUnitName:" + fromUnitName + " fromUnitNameID:[" + fromUnitNameID + "]
            // "
            // + " , "
            // + " toUnitName:" + toUnitName + " toUnitNameID:[" + toUnitNameID + "] ");

            connector.setLineWidth(1);
            connector.setLineStyleColor(128, 0, 0);

            connector.getCTConnector().getNvCxnSpPr().getCNvPr().setId(GeneratorID.getId(arStr));
            connector.getCTConnector().getNvCxnSpPr().getCNvPr().setName("connector nishi");

            // Connector start
            connector.getCTConnector().getNvCxnSpPr().getCNvCxnSpPr().addNewStCxn().setId(fromUnitNameID);
            // connector.getCTConnector().getNvCxnSpPr().getCNvCxnSpPr().getStCxn().setIdx(3);
            connector.getCTConnector().getNvCxnSpPr().getCNvCxnSpPr().getStCxn().setIdx(fromIdx);

            // Connector end
            connector.getCTConnector().getNvCxnSpPr().getCNvCxnSpPr().addNewEndCxn().setId(toUnitNameID);
            // connector.getCTConnector().getNvCxnSpPr().getCNvCxnSpPr().getEndCxn().setIdx(1);
            connector.getCTConnector().getNvCxnSpPr().getCNvCxnSpPr().getEndCxn().setIdx(toIdx);

            connector.getCTConnector().getSpPr().getLn().addNewTailEnd(); // getTailEnd().setW(STLineEndWidth.LG);
            connector.getCTConnector().getSpPr().getLn().getTailEnd().setType(STLineEndType.TRIANGLE);
            connector.getCTConnector().getSpPr().getLn().getTailEnd().setW(STLineEndWidth.MED);
            connector.getCTConnector().getSpPr().getLn().getTailEnd().setLen(STLineEndLength.SM);
        }

        return true;
    }

    /**
     * セルスタイルの定義
     * 
     * @param workbook ワークブック
     * @return
     */
    public CellStyle[] createCellStypes(Workbook workbook) {

        // デフォルト セルスタイル
        CellStyle defCellStyle = workbook.createCellStyle();

        Font defFont = workbook.createFont();
        defFont.setFontName("Meiryo UI");
        defFont.setFontHeightInPoints((short) 10);
        defCellStyle.setFont(defFont);

        defCellStyle.setBorderTop(BorderStyle.THIN);
        defCellStyle.setBorderBottom(BorderStyle.THIN);
        defCellStyle.setBorderLeft(BorderStyle.THIN);
        defCellStyle.setBorderRight(BorderStyle.THIN);
        defCellStyle.setTopBorderColor(IndexedColors.BLACK.getIndex());
        defCellStyle.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        defCellStyle.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        defCellStyle.setRightBorderColor(IndexedColors.BLACK.getIndex());

        // net セルスタイル
        CellStyle netCellStyle = workbook.createCellStyle();
        netCellStyle.cloneStyleFrom(defCellStyle);

        netCellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        netCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // aJobUnit セルスタイル
        CellStyle aJobunitCellStyle = workbook.createCellStyle();
        aJobunitCellStyle.cloneStyleFrom(defCellStyle);

        aJobunitCellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        aJobunitCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // comment セルスタイル
        CellStyle commentCellStyle = workbook.createCellStyle();
        commentCellStyle.cloneStyleFrom(defCellStyle);

        commentCellStyle.setFillForegroundColor(IndexedColors.TAN.getIndex());
        commentCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // te セルスタイル
        CellStyle teCellStyle = workbook.createCellStyle();
        teCellStyle.cloneStyleFrom(defCellStyle);

        teCellStyle.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
        teCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        //
        CellStyle[] cellStyles = { defCellStyle, netCellStyle, aJobunitCellStyle, commentCellStyle, teCellStyle };

        return cellStyles;
    }

    /**
     * セルスタイルの enum
     */
    enum enumCellStyleType {
        def(0),
        net(1),
        ajobunit(2),
        comment(3),
        te(4);

        private int no;

        enumCellStyleType(int no) {
            this.no = no;
        }

        public int getNo() {
            return this.no;
        }
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
    public boolean updateSheetNets(Workbook workbook, Sheet sheet, ArrayList<NetUnit> units, int offsetRow,
            int offsetCell)
            throws IOException {

        // セルスタイル作成 親クラス
        CellStyle[] cellStyles = createCellStypes(workbook);
        //
        Row row;
        Cell cell;
        int rowIndex = 0;

        // 構成ネットを作成
        for (var unit : units) {
            row = sheet.createRow(rowIndex + offsetRow);

            // ネットの採番した表示上番号
            cell = row.createCell(0 + offsetCell);
            insertValueToCellNet(rowIndex, 0, workbook, cellStyles, cell, unit);

            // ネット名のセット
            cell = row.createCell(1 + offsetCell);
            insertValueToCellNet(rowIndex, 1, workbook, cellStyles, cell, unit);

            // ネットのコメントセット
            cell = row.createCell(2 + offsetCell);
            insertValueToCellNet(rowIndex, 2, workbook, cellStyles, cell, unit);

            rowIndex++;
        }
        //
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
