package ua.vn.ffkr.article.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ua.vn.ffkr.article.persistence.converter.LocalDateTimeToStringConverter;
import ua.vn.ffkr.article.persistence.converter.StringToLocalDateTimeConverter;

import java.time.LocalDateTime;

import static java.util.Arrays.asList;

@Configuration
@EnableMongoRepositories("ua.vn.ffkr.article.persistence")
public class MongoConfig extends AbstractMongoConfiguration {
    @Override
    protected String getDatabaseName() {
        return "ffkr";
    }

    @Override
    public Mongo mongo() throws Exception {
        return new MongoClient("127.0.0.1", 7778);
    }

    @Override
    public CustomConversions customConversions() {
        return new CustomConversions(asList(stringToLocalDateTimeConverter(), localDateTimeToStringConverter()));
    }

    @Bean
    public Converter<String, LocalDateTime> stringToLocalDateTimeConverter() {
        return new StringToLocalDateTimeConverter();
    }

    @Bean
    public Converter<LocalDateTime, String> localDateTimeToStringConverter() {
        return new LocalDateTimeToStringConverter();
    }


}
