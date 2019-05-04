package com.java.builder;

import com.java.builder.model.ShareLinkContent;
import com.java.builder.model.SharePhotoContent;

import java.util.ArrayList;
import java.util.List;

/**
 * 重构Facebook share content model
 * 根据Effective Java中类层次结构的Builder模式, 重构Facebook Android SDK中share content的类层次结构, 这个实现确实较Facebook SDK中的更为简单
 */
public class FacebookShareModel {
    public static void main(String[] args) {
        ShareLinkContent shareLinkContent = new ShareLinkContent.Builder("https://github.com/OsBelief/Java")
                .setPlaceId("西安").setRef("北京").setImageUrl("https://www.baidu.com/").build();
        System.out.println(shareLinkContent.toString());

        List<String> photos = new ArrayList<>();
        photos.add("https://github.com/OsBelief/Java");
        SharePhotoContent sharePhotoContent = new SharePhotoContent.Builder("https://www.baidu.com/")
                .setPhotos(photos).build();
        System.out.println(sharePhotoContent.toString());
    }
}
