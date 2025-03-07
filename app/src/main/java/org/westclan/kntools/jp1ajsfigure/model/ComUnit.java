package org.westclan.kntools.jp1ajsfigure.model;

import org.unclazz.jp1ajs2.unitdef.Attributes;
import org.unclazz.jp1ajs2.unitdef.FullQualifiedName;
import org.unclazz.jp1ajs2.unitdef.parameter.UnitType;

/**
 * ユニットの Abstract クラス
 */
public class ComUnit {

    // ユニット名
    String name = "";
    // Attribute ex. ユニットの所有者 ex. jp1user
    String jp1UserName = "";
    // ユニットタイプ
    UnitType ty;
    // ユニットコメント
    String cm = "";
    // 完全ユニット名
    String fullQualifiedName = "";

    /**
     * ユニット名をセット
     * 
     * @param name
     */
    public void setNAME(String name) {
        this.name = name;
    }

    /**
     * ユニット名を取得
     * 
     * @return
     */
    public String getNAME() {
        return this.name;
    }

    /**
     * アトリビュートをセット (JP1ユーザ名だけ設定)
     * 
     * @param attributes
     */
    public void setAttributes(Attributes attributes) {
        this.jp1UserName = attributes.getJP1UserName();
    }

    /**
     * ユニットタイプをセット
     * 
     * @param type
     */
    public void setType(UnitType type) {
        this.ty = type;
    }

    /**
     * ユニットタイプを取得
     * 
     * @return
     */
    public UnitType getType() {
        return this.ty;
    }

    /**
     * コメントをセット
     * 
     * @param comment
     */
    public void setCM(CharSequence comment) {
        String cmStr = (String) comment;
        this.cm = cmStr;
        // jobUnitArraynew.put("cm", cmStr);
    }

    /**
     * コメントをセット
     * 
     * @param comment
     */
    public void setCM(String cm) {
        this.cm = cm;
        // netUnitArraynew.put("cm", cm);
    }

    /**
     * コメントを取得
     * 
     * @return
     */
    public String getCM() {
        return cm;
    }

    /**
     * 完全ユニット名をセット
     * 
     * @param fullQualifiedName
     */
    public void setFullQualifiedName(FullQualifiedName fullQualifiedName) {
        this.fullQualifiedName = "/" + String.join("/", fullQualifiedName.getFragments());
        // System.out.println(this.fullQualifiedName);
    }

    /**
     * 完全ユニット名を取得
     * 
     * @return
     */
    public String getFullQualifiedName() {
        return this.fullQualifiedName;
    }
}
