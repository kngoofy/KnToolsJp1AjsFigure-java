package org.westclan.kntools.jp1ajsfigure.model;

/**
 * JP1/AJS のユニットタイプの Enumeration
 */
public enum EJp1AjsUnitType {

    // 定数
    /** ジョブグループ. */
    UNIT_JOB_GROUP("g", "g：ジョブグループまたはプランニンググループ"),

    /** ジョブネット. */
    UNIT_JOBNET("n", "n：ジョブネット"),

    /** UNIXジョブ. */
    UNIT_UNIX_JOB("j", "j：UNIXジョブ"),

    /** PCジョブ. */
    UNIT_PC_JOB("pj", "pj：PCジョブ"),

    /** ファイル監視ジョブ. */
    UNIT_FILE_WATCH_JOB("flwj", "flwj：ファイル監視ジョブ"),

    PARAMETER_AR("ar", "先行・後続パラメータ");

    // フィールド
    private final String name; // 名前
    private final String description; // 説明

    // コンストラクタ
    private EJp1AjsUnitType(final String name, final String description) {
        this.name = name;
        this.description = description;
    }

    // メソッド
    /**
     * ユニット種別の名前（{@code "pj"}など）を返す.
     * 
     * @return 名前
     */
    public final String getName() {
        return name;
    }

    /**
     * ユニット種別の長い名前（{@code "PC_JOB"}など）を返す.
     * 
     * @return 長い名前
     */
    public final String getLongName() {
        return name();
    }

    /**
     * ユニット種別の説明テキストを返す.
     * 
     * @return 説明テキスト
     */
    public final String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public void printInfo() {

        String comment = """
                 https://itpfdoc.hitachi.co.jp/manuals/3020/30203k2543/AJSO0136.HTM
                ty=
                ユニット種別を指定します。
                ◆ g：ジョブグループまたはプランニンググループを定義します。
                mg：マネージャージョブグループを定義します。
                ◆ n：ジョブネットを定義します。
                rn：リカバリージョブネットを定義します。
                rm：リモートジョブネットを定義します。
                rr：リカバリーリモートジョブネットを定義します。
                rc：ルートジョブネットに起動条件を定義します。
                mn：マネージャージョブネットを定義します。
                ◆ j：UNIXジョブを定義します。
                rj：リカバリーUNIXジョブを定義します。
                ◆ pj：PCジョブを定義します。
                rp：リカバリーPCジョブを定義します。
                qj：QUEUEジョブを定義します。
                rq：リカバリーQUEUEジョブを定義します。
                jdj：判定ジョブを定義します。
                rjdj：リカバリー判定ジョブを定義します。
                orj：ORジョブを定義します。
                rorj：リカバリーORジョブを定義します。
                evwj：JP1イベント受信監視ジョブを定義します。
                revwj：リカバリーJP1イベント受信監視ジョブを定義します。
                flwj：ファイル監視ジョブを定義します。
                rflwj：リカバリーファイル監視ジョブを定義します。
                mlwj：メール受信監視ジョブを定義します。
                rmlwj：リカバリーメール受信監視ジョブを定義します。
                mqwj：メッセージキュー受信監視ジョブを定義します。
                rmqwj：リカバリーメッセージキュー受信監視ジョブを定義します。
                mswj：MSMQ受信監視ジョブを定義します。
                rmswj：リカバリーMSMQ受信監視ジョブを定義します。
                lfwj：ログファイル監視ジョブを定義します。
                rlfwj：リカバリーログファイル監視ジョブを定義します。
                ntwj：Windowsイベントログ監視ジョブを定義します。
                rntwj：リカバリーWindowsイベントログ監視ジョブを定義します。
                tmwj：実行間隔制御ジョブを定義します。
                rtmwj：リカバリー実行間隔制御ジョブを定義します。
                evsj：JP1イベント送信ジョブを定義します。
                revsj：リカバリーJP1イベント送信ジョブを定義します。
                mlsj：メール送信ジョブを定義します。
                rmlsj：リカバリーメール送信ジョブを定義します。
                mqsj：メッセージキュー送信ジョブを定義します。
                rmqsj：リカバリーメッセージキュー送信ジョブを定義します。
                mssj：MSMQ送信ジョブを定義します。
                rmssj：リカバリーMSMQ送信ジョブを定義します。
                cmsj：JP1/Cm2状態通知ジョブを定義します。
                rcmsj：リカバリーJP1/Cm2状態通知ジョブを定義します。
                pwlj：ローカル電源制御ジョブを定義します。
                rpwlj：リカバリーローカル電源制御ジョブを定義します。
                pwrj：リモート電源制御ジョブを定義します。
                rpwrj：リカバリーリモート電源制御ジョブを定義します。
                cj：カスタムUNIXジョブを定義します。
                rcj：リカバリーカスタムUNIXジョブを定義します。
                cpj：カスタムPCジョブを定義します。
                rcpj：リカバリーカスタムPCジョブを定義します。
                hln：ホストリンクジョブネットを定義します。
                nc：ジョブネットコネクタを定義します
                """;
        System.out.println(comment);

    }

}
