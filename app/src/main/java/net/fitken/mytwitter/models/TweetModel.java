package net.fitken.mytwitter.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Ken on 3/4/2017.
 */

public class TweetModel {
    @SerializedName("created_at")
    private String createdAt;
    private long id;
    @SerializedName("id_str")
    private String idStr;
    private String text;
    private EntitiesModel entities;
    private UserModel user;
    @SerializedName("retweet_count")
    private int retweetCount;
    @SerializedName("favorite_count")
    private int favoriteCount;

    public String getCreatedAt() {
        return createdAt;
    }

    public long getId() {
        return id;
    }

    public String getIdStr() {
        return idStr;
    }

    public String getText() {
        return text;
    }

    public EntitiesModel getEntities() {
        return entities;
    }

    public UserModel getUser() {
        return user;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public class EntitiesModel {
        private List<UrlModel> urls;
        private List<MediaModel> media;

        public List<UrlModel> getUrls() {
            return urls;
        }

        public List<MediaModel> getMedia() {
            return media;
        }

        public class MediaModel {
            private long id;
            @SerializedName("id_str")
            private String idStr;
            @SerializedName("media_url")
            private String mediaUrl;
            @SerializedName("media_url_https")
            private String mediaUrlHttps;
            private String url;
            @SerializedName("display_url")
            private String displayUrl;
            @SerializedName("expanded_url")
            private String expandedUrl;
            private String type;
            private SizesModel sizes;

            public long getId() {
                return id;
            }

            public String getIdStr() {
                return idStr;
            }

            public String getMediaUrl() {
                return mediaUrl;
            }

            public String getMediaUrlHttps() {
                return mediaUrlHttps;
            }

            public String getUrl() {
                return url;
            }

            public String getDisplayUrl() {
                return displayUrl;
            }

            public String getExpandedUrl() {
                return expandedUrl;
            }

            public String getType() {
                return type;
            }

            public SizesModel getSizes() {
                return sizes;
            }

            public class SizesModel {
                private SizeModel small;
                private SizeModel large;
                private SizeModel medium;
                private SizeModel thumb;

                public SizeModel getSmall() {
                    return small;
                }

                public SizeModel getLarge() {
                    return large;
                }

                public SizeModel getMedium() {
                    return medium;
                }

                public SizeModel getThumb() {
                    return thumb;
                }
            }


            public class SizeModel {
                private int w;
                private int h;

                public int getW() {
                    return w;
                }

                public int getH() {
                    return h;
                }
            }
        }

        public class UrlModel {
            private String url;
            @SerializedName("expanded_url")
            private String expandUrl;
            @SerializedName("display_url")
            private String displayUrl;

            public String getUrl() {
                return url;
            }

            public String getExpandUrl() {
                return expandUrl;
            }

            public String getDisplayUrl() {
                return displayUrl;
            }
        }
    }

    public class UserModel {
        private long id;
        @SerializedName("id_str")
        private String idStr;
        private String name;
        @SerializedName("screen_name")
        private String screenName;
        private String location;
        private String description;
        private String url;
        @SerializedName("followers_count")
        private long followersCount;
        @SerializedName("friends_count")
        private long friendsCount;
        @SerializedName("listed_count")
        private long listedCount;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("favourites_count")
        private long favouritesCount;
        @SerializedName("statuses_count")
        private long statusesCount;
        @SerializedName("profile_background_image_url")
        private String profileBgImgUrl;
        @SerializedName("profile_background_image_url_https")
        private String profileBgImgUrlHttps;
        @SerializedName("profile_image_url")
        private String profileImgUrl;
        @SerializedName("profile_image_url_https")
        private String profileImgUrlHttps;
        @SerializedName("profile_banner_url")
        private String profileBannerUrl;

        public long getId() {
            return id;
        }

        public String getIdStr() {
            return idStr;
        }

        public String getName() {
            return name;
        }

        public String getScreenName() {
            return screenName;
        }

        public String getLocation() {
            return location;
        }

        public String getDescription() {
            return description;
        }

        public String getUrl() {
            return url;
        }

        public long getFollowersCount() {
            return followersCount;
        }

        public long getFriendsCount() {
            return friendsCount;
        }

        public long getListedCount() {
            return listedCount;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public long getFavouritesCount() {
            return favouritesCount;
        }

        public long getStatusesCount() {
            return statusesCount;
        }

        public String getProfileBgImgUrl() {
            return profileBgImgUrl;
        }

        public String getProfileBgImgUrlHttps() {
            return profileBgImgUrlHttps;
        }

        public String getProfileImgUrl() {
            return profileImgUrl;
        }

        public String getProfileImgUrlHttps() {
            return profileImgUrlHttps;
        }

        public String getProfileBannerUrl() {
            return profileBannerUrl;
        }
    }
}
