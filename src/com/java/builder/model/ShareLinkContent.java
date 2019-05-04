package com.java.builder.model;

public class ShareLinkContent extends ShareContent {
    private final String imageUrl;

    @Override
    public String toString() {
        return "ShareLinkContent{" +
                "imageUrl='" + imageUrl + '\'' +
                '}';
    }

    private ShareLinkContent(Builder builder) {
        super(builder);
        imageUrl = builder.imageUrl;
    }

    public static class Builder extends ShareContent.Builder<Builder> {
        private String imageUrl;

        public Builder(String contentUrl) {
            super(contentUrl);
        }

        public Builder setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        @Override
        public ShareLinkContent build() {
            return new ShareLinkContent(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
