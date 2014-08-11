package com.derbysoft.chapaai.adapter.core.utils;

public abstract class HqlService {

    public static String selectHQLByProviderCode(Class entityType) {
        return "from " + entityType.getName() + " where providerHotelCode =?";
    }

    public static String deleteHQLByProviderCode(Class entityType) {
        return "delete " + selectHQLByProviderCode(entityType);
    }
}
