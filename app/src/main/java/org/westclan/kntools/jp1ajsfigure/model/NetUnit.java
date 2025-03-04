package org.westclan.kntools.jp1ajsfigure.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.unclazz.jp1ajs2.unitdef.Attributes;
import org.unclazz.jp1ajs2.unitdef.FullQualifiedName;
import org.unclazz.jp1ajs2.unitdef.Parameter;
import org.unclazz.jp1ajs2.unitdef.Unit;
import org.unclazz.jp1ajs2.unitdef.parameter.AnteroposteriorRelationship;
import org.unclazz.jp1ajs2.unitdef.parameter.Element;
import org.unclazz.jp1ajs2.unitdef.parameter.MapSize;
import org.unclazz.jp1ajs2.unitdef.parameter.UnitType;

/**
 * ネット定義情報 クラス
 */
public class NetUnit {

    EJp1AjsUnitType enumtype = EJp1AjsUnitType.UNIT_JOBNET;
    String unitType = EJp1AjsUnitType.UNIT_JOBNET.getName(); // "job-fjwj";

    // ユニットタイプ
    UnitType ty;
    // 完全ユニット名
    String fullQualifiedName = "";
    // ユニット名
    String name = "";
    // ユニット サイズ ex. sz=10x8;
    String sz = "";
    // ユニットコメント
    String cm = "";

    // Attribute ex. ユニットの所有者 ex. jp1user
    String jp1UserName = "";

    // ハッシュ
    HashMap<String, String> unitNetArraynew = new HashMap<String, String>();

    // el リスト 要素 ex. el=JOB01-NET01,j,+240+144;
    ArrayList<String> els = new ArrayList<>();
    // ar リスト 先行・後続 ex. ar=(f=JOB01-NET01,t=JOB02-NET01,seq);
    ArrayList<String> ars = new ArrayList<>();

    // サブユニット
    List<Unit> subUnits;

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

    /**
     * アトリビュートをセット (JP1ユーザ名だけ設定)
     * 
     * @param attributes
     */
    public void setAttributes(Attributes attributes) {
        this.jp1UserName = attributes.getJP1UserName();
    }

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
     * メントをセット
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
     * Parameters 各種パラメータを設定していく
     * 
     * @param parameters
     */
    public void setParameters(List<Parameter> parameters) {

        for (var parameter : parameters) {

            var name = parameter.getName();
            // System.out.println(parameter.getName());
            // System.out.println(parameter);
            var values = parameter.getValues();
            var str = "";
            for (var jj : values) {
                // System.out.println(jj.getStringValue());
                str += jj.getStringValue();
            }
            // System.out.println(name + "=" + str);

            switch (name) {
                case "el":
                    els.add(str);
                    break;
                case "ar":
                    ars.add(str);
                    break;
                case "sz":
                    this.sz = str;
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * サブユニット List<Unit> をセット
     * 
     * @param subUnits
     */
    public void setSubUnits(List<Unit> subUnits) {
        this.subUnits = subUnits;
    }

    /**
     * サブユニットを取得
     * 
     * @return
     */
    public List<Unit> getSubUnits() {
        return this.subUnits;
    }

    /**
     * ユニットサイズ String をセット
     * 
     * @param sz
     */
    public void setSZ(String sz) {
        this.sz = sz;
        // netUnitArraynew.put("sz", sz);
    }

    // サイズ Iterable
    Iterable<MapSize> szIterable;

    /**
     * sz サイズの Iterable<MapSize> をセット
     * 
     * @param sz
     */
    public void setSZIterable(Iterable<MapSize> sz) {
        // for (var ii : sz) {
        // // System.out.println(ii.getHeight() + "," + ii.getWidth());
        // }
        this.szIterable = sz;
    }

    /**
     * SZを取得
     * 
     * @return
     */
    public Iterable<MapSize> getSZIterable() {
        return this.szIterable;
    }

    /**
     * SZ の 高さを取得
     * 
     * @return
     */
    public int getSzHeight() {
        int height = 10;
        for (var ii : getSZIterable()) {
            // System.out.println(ii.getHeight() + "," + ii.getWidth());
            height = ii.getHeight();
        }
        return height;

    }

    // ar リスト 先行・後続 Iterable
    Iterable<AnteroposteriorRelationship> arRes;

    /**
     * 先行・後続を取得
     * 
     * @return
     */
    public Iterable<AnteroposteriorRelationship> getARIterable() {
        return this.arRes;
    }

    /**
     * ar 先行・後続の Iterable<AnteroposteriorRelationship> をセット
     * 
     * @param ar
     */
    public void setARIterable(Iterable<AnteroposteriorRelationship> ar) {
        // for (var ii : ar) {
        // System.out.println(ii.getFromUnitName() + ":" + ii.getToUnitName());
        // }
        this.arRes = ar;
    }

    // Element Iterable
    Iterable<Element> elElements;

    /**
     * Element の Iterable<Element> を返す
     * 
     * @return
     */
    public Iterable<Element> getelElements() {
        return this.elElements;
    };

    /**
     * el Element Iterable<Element>をセット
     * 
     * @param els
     */
    public void setELArrayList(Iterable<Element> els) {
        int yCoord = 0;
        for (var ii : els) {
            // System.out.println(ii.getUnitName() + ":" + ii.getUnitType() + "*" +
            // ii.getHPixel() + "," + ii.getVPixel());

            if (yCoord < ii.getYCoord()) {
                yCoord = ii.getYCoord();
            }
        }
        // System.out.println(yCoord);
        this.elElements = els;
        this.MaxYCoord = yCoord;
    }

    // 最大 YCoord 値保持用
    int MaxYCoord;

    /**
     * 最大 YCoodを取得
     * 
     * @return
     */
    public int getMaxYCoord() {
        return this.MaxYCoord;
    }

    /**
     * toString Override
     */
    @Override
    public String toString() {
        return fullQualifiedName + "(" + name + ") : ";
    }

    /**
     * JP1/AJSのネットユニットの仕様サイトリンク表示
     */
    public void printLinkInfo() {
        String link = """
                https://itpfdoc.hitachi.co.jp/manuals/3020/30203k2543/AJSO0139.HTM
                """;
        System.out.println(link);
    }
}
