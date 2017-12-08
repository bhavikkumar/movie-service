package io.bhavik.movie.repoistory;

import java.util.UUID;

import javax.inject.Named;

import org.springframework.data.jpa.repository.JpaRepository;

import io.bhavik.movie.model.Movie;

@Named
public interface MovieRepository extends JpaRepository<Movie, UUID> {

}
