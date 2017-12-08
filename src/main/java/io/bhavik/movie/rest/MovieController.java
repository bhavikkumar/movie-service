package io.bhavik.movie.rest;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.bhavik.movie.model.Movie;
import io.bhavik.movie.model.OnCreate;
import io.bhavik.movie.model.OnUpdate;
import io.bhavik.movie.service.MovieService;

@RestController
@RequestMapping("/movie")
public class MovieController {
	@Inject
	private MovieService service;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Movie> createMovie(@Validated(OnCreate.class) @RequestBody(required = true) Movie movie) {
		Movie createdMovie = service.createMovie(movie);
		URI location = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}")
				.buildAndExpand(createdMovie.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<Movie>(createdMovie, headers, HttpStatus.CREATED);
	}

	@GetMapping(path = "{id}")
	public Movie getMovie(@PathVariable("id") UUID id) {
		Movie genre = service.getMovie(id);
		if (genre != null) {
			return genre;
		}
		return null;
	}

	@GetMapping
	public List<Movie> searchMovie(@RequestParam("name") String name) {
		return service.searchMovie(name);
	}

	@PutMapping(path = "{id}")
	public ResponseEntity<Movie> updateMovie(@PathVariable("id") UUID id,
			@Validated(OnUpdate.class) @RequestBody(required = true) Movie movie) {
		movie.setId(id);
		HttpStatus status = service.getMovie(id) == null ? HttpStatus.CREATED : HttpStatus.OK;
		return new ResponseEntity<Movie>(service.updateMovie(movie), status);
	}

	@DeleteMapping(path = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteMovie(@PathVariable("id") UUID id) {
		service.deleteMovie(id);
	}
}
