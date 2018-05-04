package gmu.isa681.project.othelloserver.config;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ConversionServiceFactoryBean;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;

import gmu.isa681.project.othelloserver.convertor.GameEntityToPlayingResponseConverter;
import gmu.isa681.project.othelloserver.convertor.PlayerAccountRequestToPlayerEntity;
import gmu.isa681.project.othelloserver.convertor.PlayerEntityToAccountResponseConverter;
import gmu.isa681.project.othelloserver.convertor.game.BoardToBoardEntityConverter;
import gmu.isa681.project.othelloserver.convertor.game.NewGameRequestToGameEntityConverter;

@Configuration
public class ConversionConfig {
	private Set<Converter> getConverters() {
		Set<Converter> converters = new HashSet<Converter>();
		converters.add(new GameEntityToPlayingResponseConverter());
		converters.add(new PlayerEntityToAccountResponseConverter());
		converters.add(new NewGameRequestToGameEntityConverter());
		converters.add(new PlayerAccountRequestToPlayerEntity());
		converters.add(new BoardToBoardEntityConverter());
		return converters;
	}

	@Bean
	public ConversionService conversionService() {
		ConversionServiceFactoryBean bean = new ConversionServiceFactoryBean();
		bean.setConverters(getConverters());
		bean.afterPropertiesSet();

		return bean.getObject();
	}
}
