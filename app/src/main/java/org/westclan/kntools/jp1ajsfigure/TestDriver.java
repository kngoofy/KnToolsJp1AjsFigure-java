package org.westclan.kntools.jp1ajsfigure;

import org.westclan.kntools.jp1ajsfigure.util.GeneratorID;

public class TestDriver {
    public static void main(String[] args) {

        System.out.println(GeneratorID.getId("str1"));
        System.out.println(GeneratorID.getId("str2"));
        System.out.println(GeneratorID.getId("str3"));

        System.out.println(GeneratorID.getMapID("str1"));
        System.out.println(GeneratorID.getMapID("str2"));
        System.out.println(GeneratorID.getMapID("str3"));

        System.exit(0);
    }
}
