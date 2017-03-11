package net.fitken.mytwitter.models;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.OneToMany;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.sql.language.SQLite;
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
    @Column
    @ForeignKey(saveForeignKeyModel = true)
    private EntitiesModel entities;
    @Column
    @ForeignKey(saveForeignKeyModel = true)
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

    @Table(database = MyDatabase.class)
    public static class EntitiesModel extends BaseModel {
        @PrimaryKey(autoincrement = true)
        @Column
        private int id;
        List<UrlModel> urls;
        List<MediaModel> media;

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

        @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "urls")
        public List<UrlModel> getUrls() {
            if (urls == null || urls.isEmpty()) {
                urls = SQLite.select()
                        .from(UrlModel.class)
                        .where(UrlModel_Table.entitiesId.eq(id))
                        .queryList();
            }
            return urls;
        }

        @OneToMany(methods = {OneToMany.Method.ALL}, variableName = "media")
        public List<MediaModel> getMedia() {
            if (media == null || media.isEmpty()) {
                media = SQLite.select()
                        .from(MediaModel.class)
                        .where(MediaModel_Table.entitiesId.eq(id))
                        .queryList();
            }
            return media;
        }

        @Table(database = MyDatabase.class)
        public static class MediaModel extends BaseModel {
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
            @ForeignKey(saveForeignKeyModel = true)
            private SizesModel sizes;

            @Column
            private int entitiesId;

            public int getEntitiesId() {
                return entitiesId;
            }

            public void setEntitiesId(int entitiesId) {
                this.entitiesId = entitiesId;
            }

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

            @Table(database = MyDatabase.class)
            public static class SizesModel extends BaseModel {
                @PrimaryKey(autoincrement = true)
                @Column
                private int id;
                @Column
                @ForeignKey(saveForeignKeyModel = true)
                private SizeModel small;
                @Column
                @ForeignKey(saveForeignKeyModel = true)
                private SizeModel large;
                @Column
                @ForeignKey(saveForeignKeyModel = true)
                private SizeModel medium;
                @Column
                @ForeignKey(saveForeignKeyModel = true)
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

            @Table(database = MyDatabase.class)
            public static class SizeModel extends BaseModel {
                @PrimaryKey(autoincrement = true)
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

        @Table(database = MyDatabase.class)
        public static class UrlModel extends BaseModel {
            @PrimaryKey(autoincrement = true)
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

            @Column
            private int entitiesId;

            public int getEntitiesId() {
                return entitiesId;
            }

            public void setEntitiesId(int entitiesId) {
                this.entitiesId = entitiesId;
            }

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

}
