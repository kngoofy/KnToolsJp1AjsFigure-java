package org.westclan.kntools.jp1ajsfigure.builder;

import java.util.List;

import org.unclazz.jp1ajs2.unitdef.Attributes;
import org.unclazz.jp1ajs2.unitdef.FullQualifiedName;
import org.unclazz.jp1ajs2.unitdef.Parameter;
import org.unclazz.jp1ajs2.unitdef.Unit;
import org.unclazz.jp1ajs2.unitdef.parameter.UnitType;
import org.westclan.kntools.jp1ajsfigure.model.CJobUnit;

/**
 * ジョブユニットのクラス
 */
public class BuildCJobUnit {

    /**
     * ジョブユニットのクラス組み立て
     * 
     * @param ComUnit Unit オブジェクト
     * @return boolean
     */
    public CJobUnit buildCJobUnit(Unit unit) {

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

        // ジョブクラスを生成して、データを設定していく
        CJobUnit unitJob = new CJobUnit();

        // UnitNetにデータをセット
        unitJob.setFullQualifiedName(fullQualifiedName);
        unitJob.setAttributes(attributes);
        unitJob.setNAME(name);
        unitJob.setType(type);
        unitJob.setCM(comment);
        unitJob.setParameters(parameters);

        // 生成してデータをセットしたジョブクラスを返す
        return unitJob;

    }

}
