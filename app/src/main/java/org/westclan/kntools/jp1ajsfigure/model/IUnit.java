package org.westclan.kntools.jp1ajsfigure.model;

import java.util.List;

import org.unclazz.jp1ajs2.unitdef.Parameter;

/**
 * インターフェース
 */
public interface IUnit {

    /**
     * パラメータを持つ
     * 
     * @param parameters
     */
    public void setParameters(List<Parameter> parameters);

}
