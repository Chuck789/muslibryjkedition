package com.januszcorporation.muslibryjkedition.repositories;

import com.januszcorporation.muslibryjkedition.model.Song;
import org.springframework.data.repository.CrudRepository;

public interface SongRepository extends CrudRepository<Song, Long> {
}
