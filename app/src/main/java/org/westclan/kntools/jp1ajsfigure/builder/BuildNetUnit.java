package org.westclan.kntools.jp1ajsfigure.builder;

import java.util.ArrayList;
import java.util.List;

import org.unclazz.jp1ajs2.unitdef.Attributes;
import org.unclazz.jp1ajs2.unitdef.FullQualifiedName;
import org.unclazz.jp1ajs2.unitdef.Parameter;
import org.unclazz.jp1ajs2.unitdef.Unit;
import org.unclazz.jp1ajs2.unitdef.Units;
import org.unclazz.jp1ajs2.unitdef.parameter.UnitType;
import org.unclazz.jp1ajs2.unitdef.query.Queries;
import org.westclan.kntools.jp1ajsfigure.model.NetUnit;

/**
 * ネットユニットのクラス
 */
public class BuildNetUnit {

    /**
     * トップユニット取り出し NetUnit に組み立て
     * 
     * @param unitString JP1/AJS 定義文字列
     * @return
     */
    public NetUnit getTopNetUnit(String unitString) {

        // トップユニット取り出し
        Unit unit = Units.fromCharSequence(unitString).get(0);

        // BuildNetUnit buildNet = new BuildNetUnit();
        // NetUnit topNetUnit = buildNet.buildNetUnit(unit);

        // トップユニットを NetUnitに組み立て
        NetUnit topNetUnit = buildNetUnit(unit);

        return topNetUnit;
    }

    /**
     * 下位ユニット取り出し NetUnit の ArrayList を組み立て
     * 
     * @param unitString JP1/AJS 定義文字列
     * @return
     */
    public ArrayList<NetUnit> getChildNetUnits(String unitString) {

        // トップユニット取り出し
        Unit unit = Units.fromCharSequence(unitString).get(0);

        // 下位ユニット取り出し
        // final
        Iterable<Unit> childUnits = unit.query(Queries.children());

        // NetUnit クラスの ArrayList 宣言
        ArrayList<NetUnit> listChildUnits = new ArrayList<>();

        // ループ処理
        for (var childUnit : childUnits) {
            // 組み立てた NetUnit ArrayList に add
            listChildUnits.add(buildNetUnit(childUnit));
        }

        return listChildUnits;

    }

    /**
     * ネットユニットのクラスの組み立て
     * 
     * @param Unit Unit オブジェクト
     * @return boolean
     */
    public NetUnit buildNetUnit(Unit unit) {

        // 完全ユニット名
        FullQualifiedName fullQualifiedName = unit.getFullQualifiedName();
        // Attributes
        Attributes attributes = unit.getAttributes();
        // ユニット名
        String name = unit.getName();
        // ユニットタイプ
        UnitType type = unit.getType();
        // ユニッコメント
        CharSequence comment = unit.getComment();
        // parameter
        List<Parameter> parameters = unit.getParameters();
        // サブユニット リスト
        List<Unit> subUnits = unit.getSubUnits();

        // ネットクラスを生成して、データを設定していく
        NetUnit unitNet = new NetUnit();

        // UnitNetにデータをセット
        unitNet.setFullQualifiedName(fullQualifiedName);
        unitNet.setAttributes(attributes);
        unitNet.setNAME(name);
        unitNet.setType(type);
        unitNet.setCM(comment);
        unitNet.setParameters(parameters);
        unitNet.setSubUnits(subUnits);

        // sz のクエリ ex. sz=10x8;
        unitNet.setSZIterable(unit.query(Queries.sz()));

        // ar のクエリ 先行・後続 ex. ar=(f=JOB01-NET01,t=JOB02-NET01,seq);
        unitNet.setARIterable(unit.query(Queries.ar()));

        // el のクエリ エレメント ex. el=JOB01-NET01,j,+240+144;
        unitNet.setELArrayList(unit.query(Queries.el()));

        // 生成してデータをセットしたネットクラスを返す
        return unitNet;

    }

}
