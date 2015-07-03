package org.giavacms.expo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by fiorenzo on 04/06/15.
 */
@Entity
@Table(name = Participation.TABLE_NAME)
public class Participation implements Serializable
{

   public static final String TABLE_NAME = "participations";
   private static final long serialVersionUID = 1L;

   private String id;
   private String artistname;
   private String artifactname;
   private String creationdate;
   private String dimensions;
   private String material;

   private String preview;

   private String discipline;
   private boolean reviewed;
   private boolean delivered;
   private String note;
   private String participationtype;
   private int catalogues;
   private boolean contest;
   private String custom;

   private String artistId;
   private String exhibitionId;
   private String exhibitionName;
   private String photo;

   private Exhibition exhibition;
   private Artist artist;

   @Id
   public String getId()
   {
      return id;
   }

   public void setId(String id)
   {
      this.id = id;
   }

   public String getArtifactname()
   {
      return artifactname;
   }

   public void setArtifactname(String artifactname)
   {
      this.artifactname = artifactname;
   }

   public String getCreationdate()
   {
      return creationdate;
   }

   public void setCreationdate(String creationdate)
   {
      this.creationdate = creationdate;
   }

   public String getDimensions()
   {
      return dimensions;
   }

   public void setDimensions(String dimensions)
   {
      this.dimensions = dimensions;
   }

   public String getMaterial()
   {
      return material;
   }

   public void setMaterial(String material)
   {
      this.material = material;
   }

   public String getPreview()
   {
      return preview;
   }

   public void setPreview(String preview)
   {
      this.preview = preview;
   }

   public String getDiscipline()
   {
      return discipline;
   }

   public void setDiscipline(String discipline)
   {
      this.discipline = discipline;
   }

   public boolean isReviewed()
   {
      return reviewed;
   }

   public void setReviewed(boolean reviewed)
   {
      this.reviewed = reviewed;
   }

   public boolean isDelivered()
   {
      return delivered;
   }

   public void setDelivered(boolean delivered)
   {
      this.delivered = delivered;
   }

   public String getNote()
   {
      return note;
   }

   public void setNote(String note)
   {
      this.note = note;
   }

   public String getParticipationtype()
   {
      return participationtype;
   }

   public void setParticipationtype(String participationtype)
   {
      this.participationtype = participationtype;
   }

   public int getCatalogues()
   {
      return catalogues;
   }

   public void setCatalogues(int catalogues)
   {
      this.catalogues = catalogues;
   }

   public boolean isContest()
   {
      return contest;
   }

   public void setContest(boolean contest)
   {
      this.contest = contest;
   }

   @JsonIgnore
   @ManyToOne
   public Exhibition getExhibition()
   {
      if (exhibition == null)
         this.exhibition = new Exhibition();
      return exhibition;
   }

   public void setExhibition(Exhibition exhibition)
   {
      this.exhibition = exhibition;
   }

   @JsonIgnore
   @ManyToOne
   public Artist getArtist()
   {
      if (artist == null)
         this.artist = new Artist();
      return artist;
   }

   public void setArtist(Artist artist)
   {
      this.artist = artist;
   }

   public String getArtistname()
   {
      return artistname;
   }

   public void setArtistname(String artistname)
   {
      this.artistname = artistname;
   }

   @Column(name = "artist_id", insertable = false, updatable = false)
   public String getArtistId()
   {
      return artistId;
   }

   public void setArtistId(String artistId)
   {
      this.artistId = artistId;
   }

   @Column(name = "exhibition_id", insertable = false, updatable = false)
   public String getExhibitionId()
   {
      return exhibitionId;
   }

   public void setExhibitionId(String exhibitionId)
   {
      this.exhibitionId = exhibitionId;
   }

   public String getCustom()
   {
      return custom;
   }

   public void setCustom(String custom)
   {
      this.custom = custom;
   }

   public String getPhoto()
   {
      return photo;
   }

   public void setPhoto(String photo)
   {
      this.photo = photo;
   }

   public String getExhibitionName()
   {
      return exhibitionName;
   }

   public void setExhibitionName(String exhibitionName)
   {
      this.exhibitionName = exhibitionName;
   }

   @Override
   public String toString()
   {
      return "Participation [id=" + id + ", artistname=" + artistname + ", photo=" + photo + ", artifactname="
               + artifactname
               + ", creationdate=" + creationdate + ", dimensions=" + dimensions + ", material=" + material
               + ", preview=" + preview + ", discipline=" + discipline + ", reviewed=" + reviewed
               + ", delivered=" + delivered + ", note=" + note + ", participationtype=" + participationtype
               + ", catalogues=" + catalogues + ", contest=" + contest + ", exhibition=" + exhibition + ", artist="
               + artist + "]";
   }

}
