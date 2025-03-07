package org.westclan.kntools.jp1ajsfigure.builder;

import org.unclazz.jp1ajs2.unitdef.Attributes;
import org.unclazz.jp1ajs2.unitdef.FullQualifiedName;
import org.unclazz.jp1ajs2.unitdef.Unit;
import org.unclazz.jp1ajs2.unitdef.parameter.UnitType;
import org.westclan.kntools.jp1ajsfigure.model.ComUnit;

/**
 * 共通のユニットのクラス
 */
public class BuildUnit {

    /**
     * 共通ユニットのクラスの組み立て
     * 
     * @param unit
     * @return
     */
    public ComUnit buildUnit(Unit unit) {

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

        // 共通ユニットクラスを生成して、データを設定していく
        ComUnit comUnit = new ComUnit();

        comUnit.setFullQualifiedName(fullQualifiedName);
        comUnit.setAttributes(attributes);
        comUnit.setNAME(name);
        comUnit.setType(type);
        comUnit.setCM(comment);

        return comUnit;
    }
}
