package org.giavacms.expo.repository;

import org.giavacms.api.model.Search;
import org.giavacms.api.util.IdUtils;
import org.giavacms.base.repository.BaseRepository;
import org.giavacms.expo.model.Artist;
import org.giavacms.expo.model.Exhibition;
import org.giavacms.expo.model.Participation;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Named;
import java.util.List;
import java.util.Map;

@Named
@Stateless
@LocalBean
public class ArtistRepository extends BaseRepository<Artist>
{

   private static final long serialVersionUID = 1L;

   @Override
   protected String getDefaultOrderBy()
   {
      return "id desc";
   }

   @Override
   protected void applyRestrictions(Search<Artist> search, String alias, String separator, StringBuffer sb,
            Map<String, Object> params) throws Exception
   {
      // NAME LIKE
      if (search.getLike() != null && search.getLike().getName() != null
               && !search.getLike().getName().trim().isEmpty())
      {
         sb.append(separator).append(" upper ( ").append(alias).append(".name ) like :likeName ");
         params.put("likeName", likeParam(search.getLike().getName().trim().toUpperCase()));
         separator = " and ";
      }

      // SURNAME LIKE
      if (search.getLike() != null && search.getLike().getSurname() != null
               && !search.getLike().getSurname().trim().isEmpty())
      {
         sb.append(separator).append(" upper ( ").append(alias).append(".surname ) like :likeSurname ");
         params.put("likeSurname", likeParam(search.getLike().getSurname().trim().toUpperCase()));
         separator = " and ";
      }

      // STAGENAME LIKE
      if (search.getLike() != null && search.getLike().getStagename() != null
               && !search.getLike().getStagename().trim().isEmpty())
      {
         sb.append(separator).append(" upper ( ").append(alias).append(".stagename ) like :likeStagename ");
         params.put("likeStagename", likeParam(search.getLike().getStagename().trim().toUpperCase()));
         separator = " and ";
      }

      super.applyRestrictions(search, alias, separator, sb, params);
   }

   public void importYear(String year) throws Exception
   {
      int bimCounter = 0;
      Exhibition exhibition = getEm().find(Exhibition.class, "arte-insieme-" + year);
      @SuppressWarnings("unchecked")
      List<Object[]> results = getEm()
               .createNativeQuery(
                        "SELECT "
                                 // ..0.......1........2...........3..........4...........5..........6............7.........8...........9..........10.................11..................12..................13...........14.......15..........16.............17...........18.........19.......20..............21......22......
                                 + " A.id, A.nome, A.cognome, A.nomedarte, A.telefono, A.email, A.biografia, A.nomeopera, A.data, A.dimensioni, A.materiale, A.descrizione_sintetica, A.disciplina, A.revisionata, A.consegnata, A.note, A.partecipazione, A.sitoWeb, A.facebook, A.twitter, A.instagram, A.catalogo, F.foto "
                                 +
                                 " FROM arte_insieme" + year + " A left join foto F on (A.id=F.id_art" + year + ")")
               .getResultList();
      for (Object[] row : results)
      {
         String name = (String) row[1];
         String surname = (String) row[2];
         String stagename = (String) row[3];
         Artist artist = new Artist(name, surname, stagename);

         if (artist.getArtistName() == null || artist.getArtistName().trim().isEmpty())
         {
            logger.info("no name, no surname, no stageName");
            continue;

         }

         String artistId = IdUtils.createPageId(artist.getArtistName());
         // * @return the found entity instance or null if the entity does
         // * not exist
         artist = find(artistId);
         if (artist == null)
         {
            artist = new Artist(name, surname, stagename);
            artist.setTelephone((String) row[4]);
            artist.setEmail((String) row[5]);
            artist.setBiography((String) row[6]);
            artist.setFacebook((String) row[18]);
            artist.setInstagram((String) row[20]);
            artist.setTwitter((String) row[19]);
            artist.setInstagram((String) row[20]);
            artist.setWebsite((String) row[17]);
            persist(artist);
         }
         Participation p = new Participation();
         p.setArtist(artist);
         p.setArtifactname((String) row[7]);
         p.setCreationdate((String) row[8]);
         p.setDimensions((String) row[9]);
         p.setMaterial((String) row[10]);
         p.setPreview((String) row[11]);
         p.setDiscipline((String) row[12]);
         p.setReviewed(((Integer) row[13]) > 0 ? true : false);
         p.setDelivered(((Integer) row[14]) > 0 ? true : false);
         p.setNote((String) row[15]);
         p.setParticipationtype((String) row[16]);
         p.setContest("bim".equals(row[16]) ? true : false);
         p.setExhibitionName(exhibition.getName());
         p.setCatalogues((Integer) row[21]);
         p.setPhoto((String) row[22]);

         if (p.isContest())
         {
            p.setCustom(++bimCounter + "");
         }

         p.setArtistname(artist.getArtistName());
         String id = IdUtils.createPageId(p.getArtifactname());
         String idFinal = makeUniqueKey(id, Participation.TABLE_NAME);
         p.setId(idFinal);
         p.setExhibition(exhibition);

         getEm().persist(p);
      }
   }

   @Override
   protected Artist prePersist(Artist n)
   {
      String id = IdUtils.createPageId(n.getArtistName());
      String idFinal = makeUniqueKey(id, Artist.TABLE_NAME);
      n.setId(idFinal);
      return n;
   }

   @Override
   protected Artist preUpdate(Artist n)
   {
      return n;
   }
}
