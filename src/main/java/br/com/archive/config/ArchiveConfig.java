package br.com.archive.config;

import com.univocity.parsers.fixed.FixedWidthFields;
import com.univocity.parsers.fixed.FixedWidthParser;
import com.univocity.parsers.fixed.FixedWidthParserSettings;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ArchiveConfig {

    @Bean
    public FixedWidthParser fixedWidthParser() {
        FixedWidthFields lengths = new FixedWidthFields(10, 2);

        FixedWidthParserSettings settings = new FixedWidthParserSettings(lengths);
        settings.getFormat().setLineSeparator("\n");
        return new FixedWidthParser(settings);
    }
}
