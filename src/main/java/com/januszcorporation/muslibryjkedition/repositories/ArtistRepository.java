package com.januszcorporation.muslibryjkedition.repositories;

import com.januszcorporation.muslibryjkedition.model.Artist;
import org.springframework.data.repository.CrudRepository;

public interface ArtistRepository extends CrudRepository<Artist, Long> {
}
