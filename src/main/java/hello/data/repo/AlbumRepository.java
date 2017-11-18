package hello.data.repo;

import hello.data.domain.Album;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlbumRepository extends JpaRepository<Album, Long> {
    Album findById(Long id);
    List<Album> findAll();
    Album findAlbumByAlbum(String album);
}
