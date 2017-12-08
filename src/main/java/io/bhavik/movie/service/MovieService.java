package io.bhavik.movie.service;

import java.util.List;
import java.util.UUID;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;

import io.bhavik.movie.model.Movie;
import io.bhavik.movie.repoistory.MovieRepository;

@Named
public class MovieService {

	@Inject
	private MovieRepository repository;

	public Movie createMovie(Movie movie) {
		return repository.save(movie);
	}

	public Movie getMovie(UUID id) {
		return repository.findOne(id);
	}

	public Movie updateMovie(Movie movie) {
		return repository.save(movie);
	}

	public void deleteMovie(UUID id) {
		repository.delete(id);
	}

	public List<Movie> searchMovie(String name) {
		Movie movie = new Movie();
		movie.setName(name);

		ExampleMatcher matcher = ExampleMatcher.matching().withMatcher("name", match -> match.startsWith());
		Example<Movie> example = Example.of(movie, matcher);
		return repository.findAll(example);
	}
}
