package com.januszcorporation.muslibryjkedition.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
@Entity
public class Song {
                @Id
                @GeneratedValue(strategy = GenerationType.AUTO)
                private Long id;
                private String title;
                private String genre;
                private String ismn;
                private String year;
                private String publisher;

                public Song() {
                }

                public Song(String title, String genre, String ismn, String year, String publisher) {
                    this.title = title;
                    this.genre = genre;
                    this.ismn = ismn;
                    this.year = year;
                    this.publisher = publisher;
                }

                @ManyToMany
                private Set<Artist> artists = new HashSet<>();

                public Long getId() {
                    return id;
                }

                public void setId(Long id) {
                    this.id = id;
                }

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getGenre() {
                    return genre;
                }

                public void setGenre(String genre) {
                    this.genre = genre;
                }

                public String getIsmn() {
                    return ismn;
                }

                public void setIsmn(String ismn) {
                    this.ismn = ismn;
                }

                public String getYear() {
                    return year;
                }

                public void setYear(String year) {
                    this.year = year;
                }

                public String getPublisher() {
                    return publisher;
                }

                public void setPublisher(String publisher) {
                    this.publisher = publisher;
                }

                public Set<Artist> getArtists() {
                    return artists;
                }

                public void setArtists(Set<Artist> artists) {
                    this.artists = artists;
                }

                @Override
                public String toString() {
                    return "Song{" +
                            "id=" + id +
                            ", title='" + title + '\'' +
                            ", genre='" + genre + '\'' +
                            ", ismn='" + ismn + '\'' +
                            ", year='" + year + '\'' +
                            ", publisher='" + publisher + '\'' +
                            ", artists=" + artists +
                            '}';
                    }
                }
