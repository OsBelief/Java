package com.java.designpattern.builder.model;

import java.util.List;

public class SharePhotoContent extends ShareContent {
    private final List<String> photos;

    private SharePhotoContent(Builder builder) {
        super(builder);
        this.photos = builder.photos;
    }

    @Override
    public String toString() {
        return "SharePhotoContent{" +
                "photos=" + photos +
                '}';
    }

    public static class Builder extends ShareContent.Builder<Builder> {
        private List<String> photos;

        public Builder(String contentUrl) {
            super(contentUrl);
        }

        public Builder setPhotos(List<String> photos) {
            this.photos = photos;
            return this;
        }

        @Override
        public SharePhotoContent build() {
            return new SharePhotoContent(this);
        }

        @Override
        protected Builder self() {
            return this;
        }
    }
}
