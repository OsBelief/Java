package com.java.builder.model;

public abstract class ShareContent {
    private final String contentUrl;
    private final String placeId;
    private final String ref;

    protected ShareContent(Builder<?> builder) {
        contentUrl = builder.contentUrl;
        placeId = builder.placeId;
        ref = builder.ref;
    }

    abstract static class Builder<T extends Builder<T>> {
        private final String contentUrl;
        private String placeId;
        private String ref;

        public Builder(String contentUrl) {
            this.contentUrl = contentUrl;
        }

        public T setPlaceId(String placeId) {
            this.placeId = placeId;
            return self();
        }

        public T setRef(String ref) {
            this.ref = ref;
            return self();
        }

        abstract ShareContent build();

        protected abstract T self();
    }
}
