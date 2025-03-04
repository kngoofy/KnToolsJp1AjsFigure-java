package org.westclan.kntools.jp1ajsfigure.util;

import org.apache.poi.xssf.usermodel.TextAutofit;
import org.apache.poi.xssf.usermodel.XSSFChildAnchor;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFPicture;
import org.apache.poi.xssf.usermodel.XSSFShapeGroup;
import org.apache.poi.xssf.usermodel.XSSFTextBox;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.STEditAs;

/**
 * Excel オブジェクト グループ を作成するクラス
 */
public class CreateShapeGroupUnit {

    // Workbook workbook;
    XSSFDrawing drawing;
    XSSFShapeGroup shapeGroup;

    /**
     * 
     * @param drawing
     * @param groupUnit
     * @param picIdx
     * @param fullQualifiedName
     * @param unitName
     */
    public CreateShapeGroupUnit(XSSFDrawing drawing, AnchorGroupUnit groupUnit, int picIdx, String fullQualifiedName,
            String unitName, String unitComment) {
        // this.workbook = workbook;
        this.drawing = drawing;
        this.shapeGroup = createGroup(groupUnit);
        createPicture(groupUnit, picIdx, fullQualifiedName, unitName);
        createTextBox(groupUnit, unitName, unitComment);

        // 図形固定化 ex. <xdr:twoCellAnchor editAs="absolute">
        var listTCA = drawing.getCTDrawing().getTwoCellAnchorList();
        for (var tca : listTCA) {
            // System.out.println(tca.getEditAs());
            tca.setEditAs(STEditAs.ABSOLUTE);
        }
    }

    //
    /**
     * Jp1/AJSの Group Shape オブジェクトを貼る
     * 
     * @param groupUnit Groupのアンカー
     * @return
     */
    private XSSFShapeGroup createGroup(AnchorGroupUnit groupUnit) {
        //
        // XSSFClientAnchor anchor = new XSSFClientAnchor(
        // 0, 0, 0, 0, 2, 2, 6, 6);

        XSSFClientAnchor anchor = groupUnit.shapeGroup.getAnchor();
        XSSFShapeGroup shapeGroup = this.drawing.createGroup(anchor);

        // var a = shapeGroup.getCTGroupShape().getGrpSpPr();
        // System.out.println(a);
        // var b = shapeGroup.getCTGroupShape().getNvGrpSpPr();
        // System.out.println(b);

        return shapeGroup;
    }

    //
    /**
     * JP1/AJS の ユニットの画像を貼る
     * 
     * @param groupUnit         ユニット画像のアンカー
     * @param picIdx            ユニット画像のExcelBookの画像ID
     * @param fullQualifiedName 完全ユニット名
     * @param unitName          ユニット名
     */
    private void createPicture(AnchorGroupUnit groupUnit, int picIdx, String fullQualifiedName, String unitName) {
        //
        // XSSFClientAnchor anchor = new XSSFClientAnchor(
        // 0, 0, 0, 0, 2, 2, 3, 3);
        XSSFClientAnchor anchor = groupUnit.shapePicture.getAnchor();
        XSSFPicture picture = this.shapeGroup.createPicture((XSSFClientAnchor) anchor, picIdx);
        picture.resize();

        picture.getCTPicture().getNvPicPr().getCNvPr().setId(GeneratorID.getId(fullQualifiedName + "/" + unitName));
        picture.getCTPicture().getNvPicPr().getCNvPr().setName(unitName);
        ;
        // System.out.println(picture.getCTPicture().getNvPicPr().getCNvPr().getId());
        // picture.getCTPicture().getNvPicPr().getCNvPr().setde;

    }

    /**
     * Jp1/AJSのユニット名
     * 
     * @param groupUnit ユニット名のアンカー
     * @param text      ユニット名テキスト
     */
    private void createTextBox(AnchorGroupUnit groupUnit, String UnitName, String unitComment) {
        //
        // XSSFChildAnchor childAnchor = new XSSFChildAnchor(10, 230000, 750000,
        // 230000);
        XSSFChildAnchor childAnchor = groupUnit.shapeTextBox.getChildAnchor();
        XSSFTextBox textBox = this.shapeGroup.createTextbox(childAnchor);
        textBox.setLineWidth(1);
        textBox.setLineStyleColor(10, 20, 255);
        textBox.setFillColor(250, 250, 250);
        textBox.setText(UnitName + "\n" + unitComment);
        textBox.setTextAutofit(TextAutofit.SHAPE);
    }

    public String ToString() {

        return "CreateShapeGroupUnit";
    }
}
