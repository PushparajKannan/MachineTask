package com.sample.machinetask.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.Keep;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Keep
public class APIResponse implements Parcelable {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("totalResults")
    @Expose
    private Integer totalResults;
    @SerializedName("articles")
    @Expose
    private List<Article> articles = null;
    public final static Parcelable.Creator<APIResponse> CREATOR = new Creator<APIResponse>() {


        @SuppressWarnings({
                "unchecked"
        })
        public APIResponse createFromParcel(Parcel in) {
            return new APIResponse(in);
        }

        public APIResponse[] newArray(int size) {
            return (new APIResponse[size]);
        }

    };

    protected APIResponse(Parcel in) {
        this.status = ((String) in.readValue((String.class.getClassLoader())));
        this.totalResults = ((Integer) in.readValue((Integer.class.getClassLoader())));
        in.readList(this.articles, (Article.class.getClassLoader()));
    }

    public APIResponse() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(totalResults);
        dest.writeList(articles);
    }

    public int describeContents() {
        return 0;
    }

    @Keep
    public static class Article implements Parcelable {

        @SerializedName("source")
        @Expose
        private Source source;
        @SerializedName("author")
        @Expose
        private String author;
        @SerializedName("title")
        @Expose
        private String title;
        @SerializedName("description")
        @Expose
        private String description;
        @SerializedName("url")
        @Expose
        private String url;
        @SerializedName("urlToImage")
        @Expose
        private String urlToImage;
        @SerializedName("publishedAt")
        @Expose
        private String publishedAt;
        @SerializedName("content")
        @Expose
        private String content;
        public final static Parcelable.Creator<Article> CREATOR = new Creator<Article>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Article createFromParcel(Parcel in) {
                return new Article(in);
            }

            public Article[] newArray(int size) {
                return (new Article[size]);
            }

        };

        protected Article(Parcel in) {
            this.source = ((Source) in.readValue((Source.class.getClassLoader())));
            this.author = ((String) in.readValue((String.class.getClassLoader())));
            this.title = ((String) in.readValue((String.class.getClassLoader())));
            this.description = ((String) in.readValue((String.class.getClassLoader())));
            this.url = ((String) in.readValue((String.class.getClassLoader())));
            this.urlToImage = ((String) in.readValue((String.class.getClassLoader())));
            this.publishedAt = ((String) in.readValue((String.class.getClassLoader())));
            this.content = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Article() {
        }

        public Source getSource() {
            return source;
        }

        public void setSource(Source source) {
            this.source = source;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUrlToImage() {
            return urlToImage;
        }

        public void setUrlToImage(String urlToImage) {
            this.urlToImage = urlToImage;
        }

        public String getPublishedAt() {
            return publishedAt;
        }

        public void setPublishedAt(String publishedAt) {
            this.publishedAt = publishedAt;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(source);
            dest.writeValue(author);
            dest.writeValue(title);
            dest.writeValue(description);
            dest.writeValue(url);
            dest.writeValue(urlToImage);
            dest.writeValue(publishedAt);
            dest.writeValue(content);
        }

        public int describeContents() {
            return 0;
        }

    }

    @Keep
    public static class Source implements Parcelable {

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;
        public final static Parcelable.Creator<Source> CREATOR = new Creator<Source>() {


            @SuppressWarnings({
                    "unchecked"
            })
            public Source createFromParcel(Parcel in) {
                return new Source(in);
            }

            public Source[] newArray(int size) {
                return (new Source[size]);
            }

        };

        protected Source(Parcel in) {
            this.id = ((String) in.readValue((String.class.getClassLoader())));
            this.name = ((String) in.readValue((String.class.getClassLoader())));
        }

        public Source() {
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void writeToParcel(Parcel dest, int flags) {
            dest.writeValue(id);
            dest.writeValue(name);
        }

        public int describeContents() {
            return 0;
        }


    }
}
