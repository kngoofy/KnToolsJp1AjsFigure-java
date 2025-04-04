package org.westclan.kntools.jp1ajsfigure.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.unclazz.jp1ajs2.unitdef.Parameter;
import org.unclazz.jp1ajs2.unitdef.Unit;
import org.unclazz.jp1ajs2.unitdef.parameter.AnteroposteriorRelationship;
import org.unclazz.jp1ajs2.unitdef.parameter.Element;
import org.unclazz.jp1ajs2.unitdef.parameter.MapSize;

/**
 * ネット定義情報 クラス
 */
public class NetUnit extends ComUnit implements IUnit {

    EJp1AjsUnitType enumtype = EJp1AjsUnitType.UNIT_JOBNET;
    String unitType = EJp1AjsUnitType.UNIT_JOBNET.getName(); // "job-fjwj";

    // ユニット サイズ ex. sz=10x8;
    String sz = "";

    // ハッシュ
    HashMap<String, String> unitNetArraynew = new HashMap<String, String>();

    // el リスト 要素 ex. el=JOB01-NET01,j,+240+144;
    ArrayList<String> els = new ArrayList<>();

    // ar リスト 先行・後続 ex. ar=(f=JOB01-NET01,t=JOB02-NET01,seq);
    ArrayList<String> ars = new ArrayList<>();

    // サブユニット
    List<Unit> subUnits;

    /**
     * Parameters 各種パラメータを設定していく
     * 
     * @param parameters
     */
    @Override
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
