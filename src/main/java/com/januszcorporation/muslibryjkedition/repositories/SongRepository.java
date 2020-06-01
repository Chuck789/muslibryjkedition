package com.januszcorporation.muslibryjkedition.repositories;

import com.januszcorporation.muslibryjkedition.model.Artist;
import com.januszcorporation.muslibryjkedition.model.Song;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface SongRepository extends CrudRepository<Song, Long> {

    List<Song> getAllByArtistsIsContaining(Artist artist);
}
