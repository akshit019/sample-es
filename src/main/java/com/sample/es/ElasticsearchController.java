package com.sample.es;

import java.io.IOException;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/elasticsearch")
public class ElasticsearchController {

    @Autowired
    RestHighLevelClient restHighLevelClient;

	@GetMapping(value = "/matchall/{index}/{count}")
	public SearchResponse matchAllResponse(@PathVariable String index, @PathVariable int count) {
		SearchRequest searchRequest = new SearchRequest(index);
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		searchSourceBuilder.size(count);
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = null;
		try {
			searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            restHighLevelClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return searchResponse;

	}

	@GetMapping(value = "/matchall/{index}/{type}/{count}")
	public SearchResponse matchAllResponse(@PathVariable String index, @PathVariable String type, @PathVariable int count) {
		SearchRequest searchRequest = new SearchRequest(index);
		searchRequest.types(type);
		SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
		searchSourceBuilder.query(QueryBuilders.matchAllQuery());
		searchSourceBuilder.size(count);
		searchRequest.source(searchSourceBuilder);
		SearchResponse searchResponse = null;
		try {
			searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            restHighLevelClient.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return searchResponse;
	}
}
