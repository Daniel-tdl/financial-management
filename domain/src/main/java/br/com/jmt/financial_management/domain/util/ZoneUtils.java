package br.com.jmt.financial_management.domain.util;

import java.time.ZoneId;

public class ZoneUtils {
    public static final String TIMEZONE_SAO_PAULO = "America/Sao_Paulo";

    public static ZoneId getSaoPauloZone() {
        return ZoneId.of(TIMEZONE_SAO_PAULO);
    }
}
