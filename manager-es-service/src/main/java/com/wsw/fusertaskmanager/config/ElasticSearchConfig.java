package com.wsw.fusertaskmanager.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * @Author WangSongWen
 * @Date: Created in 15:40 2020/12/29
 * @Description:
 */
@Configuration
public class ElasticSearchConfig extends AbstractElasticsearchConfiguration {
    @Value("${elasticsearch.rest.client.address}")
    private String restClientAddress;

    @Override
    @Bean
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(restClientAddress)
                .build();
        return RestClients.create(clientConfiguration).rest();
    }
}
