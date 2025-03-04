package org.westclan.kntools.jp1ajsfigure.util;

import org.apache.poi.xssf.usermodel.XSSFChildAnchor;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;

/**
 * ユニットの Grouop Shape クラス
 * 内部メンバクラスあり
 * ShapePicture と ShapeTextBoxをグループにした Shapeアンカー作成クラス
 */
public class AnchorGroupUnit {
    // public getShapeGroupAnchor(){
    // return ShapeGroup.
    // };

    public ShapeGroup shapeGroup; // = new ShapeGroup(0, 0, 0, 0, 0, 0, 0, 0);
    public ShapePicture shapePicture; // = new ShapePicture(0, 0, 0, 0, 0, 0, 0, 0);
    public ShapeTextBox shapeTextBox; // = new ShapeTextBox(0, 0, 0, 0);

    /**
     * コンストラクタ GroupShape の座標と、
     * コンテンツのShapePictureとShapeTextBoxの座標を決める
     * 
     * @param dx1
     * @param dy1
     * @param dx2
     * @param dy2
     * @param col1
     * @param row1
     * @param col2
     * @param row2
     */
    public AnchorGroupUnit(
            int dx1, int dy1, int dx2, int dy2,
            int col1, int row1, int col2, int row2) {
        // shapeGroup = new ShapeGroup(dx1, dy1, dx2, dy2, col1, row1, col2, row2);
        shapeGroup = new ShapeGroup(dx1, dy1, dx2, dy2, col1 + 1, row1 * 3 - 4, col2 + 1, row2 * 3 - 4);
        shapePicture = new ShapePicture(0, 0, 0, 0, 2, 2, 3, 3);
        shapeTextBox = new ShapeTextBox(0, 210000, 1150000, 210000);

    };

    /**
     * 内部クラス Shape Group
     */
    public class ShapeGroup {
        // XSSFClientAnchor anchor = new XSSFClientAnchor(
        // 0, 0, 0, 0, 2, 2, 6, 6);
        XSSFClientAnchor anchor;

        // コンストラクタ
        public ShapeGroup(
                int dx1, int dy1, int dx2, int dy2,
                int col1, int row1, int col2, int row2) {
            anchor = new XSSFClientAnchor(
                    dx1, dy1, dx2, dy2, col1, row1, col2, row2);
        }

        /**
         * ゲッター ChildAnchor を返す
         * 
         * @return XSSFClientAnchor
         */
        public XSSFClientAnchor getAnchor() {
            return anchor;
        }
    }

    /**
     * 内部クラス Shape Picture
     */
    public class ShapePicture {
        // XSSFClientAnchor anchor = new XSSFClientAnchor(
        // 0, 0, 0, 0, 2, 2, 3, 3);
        XSSFClientAnchor anchor;

        // コンストラクタ
        public ShapePicture(
                int dx1, int dy1, int dx2, int dy2,
                int col1, int row1, int col2, int row2) {
            anchor = new XSSFClientAnchor(
                    dx1, dy1, dx2, dy2, col1, row1, col2, row2);
        }

        /**
         * ゲッター ChildAnchor を返す
         * 
         * @return XSSFClientAnchor
         */
        public XSSFClientAnchor getAnchor() {
            return anchor;
        }

        public int getId() {
            return 0;
        }
    }

    /**
     * 内部クラス Shape TextBox
     */
    public class ShapeTextBox {
        // XSSFChildAnchor childAnchor = new XSSFChildAnchor(10, 230000, 750000,
        // 230000);
        XSSFChildAnchor childAnchor;

        /**
         * コンストラクタ ShapeTextBox
         * 
         * @param x
         * @param y
         * @param cx
         * @param cy
         */
        public ShapeTextBox(
                int x,
                int y,
                int cx,
                int cy) {
            childAnchor = new XSSFChildAnchor(x, y, cx, cy);
        };

        /**
         * ゲッター ChildAnchor を返す
         * 
         * @return XSSFChildAnchor
         */
        public XSSFChildAnchor getChildAnchor() {
            return childAnchor;
        }
    }

    public String ToString() {

        return "Anchor";
    }

}
