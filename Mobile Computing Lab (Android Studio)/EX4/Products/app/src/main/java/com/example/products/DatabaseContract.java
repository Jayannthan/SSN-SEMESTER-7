// DatabaseContract.java
package com.example.products;

import android.provider.BaseColumns;

public final class DatabaseContract {

    private DatabaseContract() {}

    public static class ProductEntry implements BaseColumns {
        public static final String TABLE_NAME = "products";
        public static final String COLUMN_PRODUCT_ID = "product_id";
        public static final String COLUMN_PRODUCT_NAME = "product_name";
        public static final String COLUMN_PRODUCT_BRAND = "product_brand";
        public static final String COLUMN_PRODUCT_DESCRIPTION = "product_description";
        public static final String COLUMN_PRODUCT_PRICE = "product_price";
    }
}
