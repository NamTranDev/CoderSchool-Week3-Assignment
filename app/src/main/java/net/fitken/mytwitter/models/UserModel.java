package net.fitken.mytwitter.models;

import com.google.gson.annotations.SerializedName;
import com.raizlabs.android.dbflow.annotation.Column;
import com.raizlabs.android.dbflow.annotation.ForeignKey;
import com.raizlabs.android.dbflow.annotation.PrimaryKey;
import com.raizlabs.android.dbflow.annotation.Table;
import com.raizlabs.android.dbflow.structure.BaseModel;

import net.fitken.mytwitter.database.MyDatabase;

/**
 * Created by hung.nguyen on 3/7/2017.
 */
@Table(database = MyDatabase.class)
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