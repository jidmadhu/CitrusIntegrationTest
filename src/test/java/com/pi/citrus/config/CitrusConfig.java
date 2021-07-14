package com.pi.citrus.config;

import com.consol.citrus.dsl.endpoint.CitrusEndpoints;
import com.consol.citrus.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Jidhu Madhu
 *
 */
@Configuration
@PropertySource("classpath:citrus-application.properties")
@ComponentScan(basePackages = {"com.pi.*"})
public class CitrusConfig {


    @Value("${google.url}")
    private String googleUrl;

    @Value("${google.search.url}")
    private String googleSearchUrl;

    @Value("${yahoo.url}")
    private String yahooUrl;

    @Value("${yahoo.search.url}")
    private String yahooSearchUrl;

    @Bean
    public HttpClient navigateToGoogle() {
        return CitrusEndpoints.http()
                .client()
                .requestMethod(HttpMethod.GET)
                .requestUrl(googleUrl)
                .timeout(2500)
                .build();
    }

    @Bean
    public HttpClient navigateToYahoo() {
        return CitrusEndpoints.http()
                .client()
                .requestMethod(HttpMethod.GET)
                .requestUrl(yahooUrl)
                .timeout(2500)
                .build();
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate template = new RestTemplate();

        List<HttpMessageConverter<?>> httpMessageConverters = new ArrayList<>();

        StringHttpMessageConverter stringConverter = new StringHttpMessageConverter();
        stringConverter.setWriteAcceptCharset(false);

        List<MediaType> mediaTypes = new ArrayList<>();
        mediaTypes.add(MediaType.APPLICATION_JSON);
        mediaTypes.add(MediaType.TEXT_PLAIN);
        mediaTypes.add(MediaType.TEXT_XML);
        stringConverter.setSupportedMediaTypes(mediaTypes);
        httpMessageConverters.add(stringConverter);
        template.setMessageConverters(httpMessageConverters);

        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();

        Proxy serverProxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("", 8080));
        requestFactory.setProxy(serverProxy);
        template.setRequestFactory(requestFactory);
        return template;
    }
}
