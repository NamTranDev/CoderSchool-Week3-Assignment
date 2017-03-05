package net.fitken.mytwitter.models;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import net.fitken.mytwitter.database.MyDatabase;

import java.util.List;

/**
 * Created by Ken on 3/4/2017.
 */

@Table(database = MyDatabase.class)
public class TweetModel extends BaseModel {
    @Column
    @SerializedName("created_at")
    private String createdAt;
    @PrimaryKey
    @Column
    private long id;
    @Column
    @SerializedName("id_str")
    private String idStr;
    @Column
    private String text;
    private EntitiesModel entities;
    private UserModel user;
    @Column
    @SerializedName("retweet_count")
    private int retweetCount;
    @Column
    @SerializedName("favorite_count")
    private int favoriteCount;

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setIdStr(String idStr) {
        this.idStr = idStr;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setEntities(EntitiesModel entities) {
        this.entities = entities;
    }

    public void setUser(UserModel user) {
        this.user = user;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

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

    public class EntitiesModel extends BaseModel {
        @PrimaryKey
        @Column
        private int id;
        private List<UrlModel> urls;
        private List<MediaModel> media;
        @ForeignKey(stubbedRelationship = true)
        TweetModel owner;
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public void setUrls(List<UrlModel> urls) {
            this.urls = urls;
        }

        public void setMedia(List<MediaModel> media) {
            this.media = media;
        }

        public List<UrlModel> getUrls() {
            return urls;
        }

        public List<MediaModel> getMedia() {
            return media;
        }

        public class MediaModel extends BaseModel {
            @PrimaryKey
            @Column
            private long id;
            @Column
            @SerializedName("id_str")
            private String idStr;
            @Column
            @SerializedName("media_url")
            private String mediaUrl;
            @Column
            @SerializedName("media_url_https")
            private String mediaUrlHttps;
            @Column
            private String url;
            @Column
            @SerializedName("display_url")
            private String displayUrl;
            @Column
            @SerializedName("expanded_url")
            private String expandedUrl;
            @Column
            private String type;
            @Column
            private SizesModel sizes;

            public void setId(long id) {
                this.id = id;
            }

            public void setIdStr(String idStr) {
                this.idStr = idStr;
            }

            public void setMediaUrl(String mediaUrl) {
                this.mediaUrl = mediaUrl;
            }

            public void setMediaUrlHttps(String mediaUrlHttps) {
                this.mediaUrlHttps = mediaUrlHttps;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setDisplayUrl(String displayUrl) {
                this.displayUrl = displayUrl;
            }

            public void setExpandedUrl(String expandedUrl) {
                this.expandedUrl = expandedUrl;
            }

            public void setType(String type) {
                this.type = type;
            }

            public void setSizes(SizesModel sizes) {
                this.sizes = sizes;
            }

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

            public class SizesModel extends BaseModel {
                @PrimaryKey
                @Column
                private int id;
                @Column
                private SizeModel small;
                @Column
                private SizeModel large;
                @Column
                private SizeModel medium;
                @Column
                private SizeModel thumb;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public void setSmall(SizeModel small) {
                    this.small = small;
                }

                public void setLarge(SizeModel large) {
                    this.large = large;
                }

                public void setMedium(SizeModel medium) {
                    this.medium = medium;
                }

                public void setThumb(SizeModel thumb) {
                    this.thumb = thumb;
                }

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


            public class SizeModel extends BaseModel {
                @PrimaryKey
                @Column
                private int id;
                @Column
                private int w;
                @Column
                private int h;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public void setW(int w) {
                    this.w = w;
                }

                public void setH(int h) {
                    this.h = h;
                }

                public int getW() {
                    return w;
                }

                public int getH() {
                    return h;
                }
            }
        }

        public class UrlModel extends BaseModel {
            @PrimaryKey
            @Column
            private int id;
            @Column
            private String url;
            @Column
            @SerializedName("expanded_url")
            private String expandUrl;
            @Column
            @SerializedName("display_url")
            private String displayUrl;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public void setExpandUrl(String expandUrl) {
                this.expandUrl = expandUrl;
            }

            public void setDisplayUrl(String displayUrl) {
                this.displayUrl = displayUrl;
            }

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

    public class UserModel extends BaseModel {
        @PrimaryKey
        @Column
        private long id;
        @Column
        @SerializedName("id_str")
        private String idStr;
        @Column
        private String name;
        @Column
        @SerializedName("screen_name")
        private String screenName;
        @Column
        private String location;
        @Column
        private String description;
        @Column
        private String url;
        @Column
        @SerializedName("followers_count")
        private long followersCount;
        @Column
        @SerializedName("friends_count")
        private long friendsCount;
        @Column
        @SerializedName("listed_count")
        private long listedCount;
        @Column
        @SerializedName("created_at")
        private String createdAt;
        @Column
        @SerializedName("favourites_count")
        private long favouritesCount;
        @Column
        private boolean verified;
        @Column
        @SerializedName("statuses_count")
        private long statusesCount;
        @Column
        @SerializedName("profile_background_image_url")
        private String profileBgImgUrl;
        @Column
        @SerializedName("profile_background_image_url_https")
        private String profileBgImgUrlHttps;
        @Column
        @SerializedName("profile_image_url")
        private String profileImgUrl;
        @Column
        @SerializedName("profile_image_url_https")
        private String profileImgUrlHttps;
        @Column
        @SerializedName("profile_banner_url")
        private String profileBannerUrl;

        @ForeignKey(stubbedRelationship = true)
        TweetModel owner;

        public boolean isVerified() {
            return verified;
        }

        public void setVerified(boolean verified) {
            this.verified = verified;
        }

        public void setId(long id) {
            this.id = id;
        }

        public void setIdStr(String idStr) {
            this.idStr = idStr;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setScreenName(String screenName) {
            this.screenName = screenName;
        }

        public void setLocation(String location) {
            this.location = location;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setFollowersCount(long followersCount) {
            this.followersCount = followersCount;
        }

        public void setFriendsCount(long friendsCount) {
            this.friendsCount = friendsCount;
        }

        public void setListedCount(long listedCount) {
            this.listedCount = listedCount;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public void setFavouritesCount(long favouritesCount) {
            this.favouritesCount = favouritesCount;
        }

        public void setStatusesCount(long statusesCount) {
            this.statusesCount = statusesCount;
        }

        public void setProfileBgImgUrl(String profileBgImgUrl) {
            this.profileBgImgUrl = profileBgImgUrl;
        }

        public void setProfileBgImgUrlHttps(String profileBgImgUrlHttps) {
            this.profileBgImgUrlHttps = profileBgImgUrlHttps;
        }

        public void setProfileImgUrl(String profileImgUrl) {
            this.profileImgUrl = profileImgUrl;
        }

        public void setProfileImgUrlHttps(String profileImgUrlHttps) {
            this.profileImgUrlHttps = profileImgUrlHttps;
        }

        public void setProfileBannerUrl(String profileBannerUrl) {
            this.profileBannerUrl = profileBannerUrl;
        }

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
