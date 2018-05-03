package gmu.isa681.project.othelloserver.rest;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import gmu.isa681.project.othelloserver.entity.GameEntity;
import gmu.isa681.project.othelloserver.entity.PlayerEntity;
import gmu.isa681.project.othelloserver.exception.PlayerNotFoundException;
import gmu.isa681.project.othelloserver.model.request.PlayingRequest;
import gmu.isa681.project.othelloserver.model.request.game.play.NewGameRequest;
import gmu.isa681.project.othelloserver.model.response.game.PlayingResponse;
import gmu.isa681.project.othelloserver.repository.GameRepository;
import gmu.isa681.project.othelloserver.repository.PageableGameRepository;
import gmu.isa681.project.othelloserver.repository.PlayerRepository;

@RestController
@RequestMapping(ResourceConstants.GAME_PLAYING_V1)
public class PlayingResource {

	@Autowired
	PageableGameRepository pageableGameRepository;

	@Autowired
	GameRepository gameRepository;

	@Autowired
	PlayerRepository playerRepository;

	@Autowired
	ConversionService conversionService;

	@RequestMapping(path = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public Page<PlayingResponse> getAvailableGames(Pageable pageable) {

		Page<GameEntity> gameEntityList = pageableGameRepository.findAll(pageable);

		return gameEntityList.map((e -> conversionService.convert(e, PlayingResponse.class)));
	}

	@RequestMapping(path = "/{gameId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<GameEntity> getGameById(@PathVariable Long gameId) {
		Optional<GameEntity> gameEntity = gameRepository.findById(gameId);

		return new ResponseEntity<>(gameEntity.get(), HttpStatus.OK);
	}

	@RequestMapping(path = "", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlayingResponse> createNewGame(@RequestBody NewGameRequest newGameRequest) {
		Optional<PlayerEntity> player = playerRepository.findById(newGameRequest.getRequestSenderPlayerId());
		if (!player.isPresent()) {
			throw new PlayerNotFoundException(newGameRequest.getRequestSenderPlayerId().toString());
		}
		GameEntity game = conversionService.convert(newGameRequest, GameEntity.class);
		gameRepository.save(game);
		PlayingResponse playingResponse = conversionService.convert(game, PlayingResponse.class);
		return new ResponseEntity<>(playingResponse, HttpStatus.CREATED);
	}

	@RequestMapping(path = "", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_UTF8_VALUE, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<PlayingResponse> joinGame(@RequestBody PlayingRequest playingRequest) {
		return new ResponseEntity<>(new PlayingResponse(), HttpStatus.OK);
	}

}
