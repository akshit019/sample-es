package com.sample.es;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class ElasticsearchRestClient {

    @Bean
    public RestHighLevelClient getClient() {
        return initRestClient();
    }

	public RestHighLevelClient initRestClient() {
//		client = new RestHighLevelClient(RestClient.builder(
//				new HttpHost("elastic-search.prod.sse-logging-db.samsclubdotcom.prod.walmart.com", 9200, "http"),
//				new HttpHost("elastic-search.prod.sse-logging-db.samsclubdotcom.prod.walmart.com", 9201, "http")));

		RestClientBuilder builder = RestClient.builder(new HttpHost("localhost", 9200))
		        .setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
		            @Override
		            public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
		                return requestConfigBuilder.setConnectTimeout(300000)
		                        .setSocketTimeout(300000);
		            }
		        })
		        .setMaxRetryTimeoutMillis(300000);
		return new RestHighLevelClient(builder);
	}

}
