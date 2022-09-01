package com.truckplast.analyzer.repository.query;

public final class BrandQuery {

    private BrandQuery(){}

    public static final String GET_BY_NAME = "SELECT * FROM brands WHERE brands.name = ?";

}
