package org.westclan.kntools.jp1ajsfigure.model;

import java.util.HashMap;
import java.util.List;

// import org.unclazz.jp1ajs2.unitdef.Attributes;
// import org.unclazz.jp1ajs2.unitdef.FullQualifiedName;
import org.unclazz.jp1ajs2.unitdef.Parameter;
// import org.unclazz.jp1ajs2.unitdef.parameter.UnitType;

/**
 * ジョブ定義情報 クラス
 */
public class CJobUnit extends ComUnit implements IUnit {

    EJp1AjsUnitType enumtype = EJp1AjsUnitType.UNIT_UNIX_JOB;
    String unitType = EJp1AjsUnitType.UNIT_UNIX_JOB.getName(); // "job-fjwj"

    String te = ""; // "コマンドテキスト";
    String sc = ""; // "スクリプトファイル名";
    String un = ""; // "実行ユーザー名";
    String tho = ""; // n;
    String ha = ""; // {y|n};
    String eu = ""; // {ent|def};
    String flwf = "";

    // ハッシュ
    HashMap<String, String> unitJobArraynew = new HashMap<String, String>();

    /**
     * コマンド文字列をセット
     * 
     * @param te
     */
    public void setTE(String te) {
        this.te = te;
    }

    /**
     * コマンド文字列を取得
     * 
     * @return
     */
    public String getTE() {
        return this.te;
    }

    /**
     * ファイル受信ファイルを取得
     * 
     * @return
     */
    public String getflwf() {
        return this.flwf;
    }

    /**
     * PCジョブスクリプトを取得
     * 
     * @return
     */
    public String getSC() {
        return this.sc;
    }

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
            ;
            // System.out.println(name + "=" + str);

            switch (name) {
                case "te":
                    this.te = str;
                    break;
                case "sc":
                    this.sc = str;
                    break;
                case "un":
                    this.un = str;
                    break;
                case "tho":
                    this.tho = str;
                    break;
                case "eu":
                    this.eu = str;
                    break;
                case "flwf":
                    this.flwf = str;
                    break;
                default:
                    break;
            }
        }

    }

    /**
     * toString Override
     */
    @Override
    public String toString() {
        return fullQualifiedName + "(" + name + ") : ";
    }

    /**
     * JP1/AJSのジョブユニットの仕様サイトリンク表示
     */
    public void printLinkInfo() {
        String link = """
                https://itpfdoc.hitachi.co.jp/manuals/3020/30203k2543/AJSO0141.HTM
                """;
        System.out.println(link);
    }
}
